/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import communication.messages.Message;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergio
 */
public class MessageReceiver extends Thread {

    private ServerSocket serverSocket;
    private final List<MessageListener> listeners;
    private boolean running;
    private final int port;

    public MessageReceiver(int port) {
        listeners = new ArrayList<>();
        this.port = port;
    }

    @Override
    public void start() {
        running = true;
        super.start();
    }

    private synchronized boolean running() {
        return running;
    }

    public synchronized void stopReceiving() {
        running = false;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (running()) {
                Socket clientSocket = serverSocket.accept();
                MessageDispatcher messageDispatcher = new MessageDispatcher(this, clientSocket);
                messageDispatcher.start();
            }
        } catch (IOException e) {

        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket : " + e.getMessage());
                }
            }
        }
    }

    public void addListener(MessageListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(MessageListener listener) {
        listeners.remove(listener);
    }

    public synchronized void dispatchMessage(Message message) {
        listeners.forEach((listener) -> {
            listener.messageReceived(message);
        });
    }
}

class MessageDispatcher extends Thread {

    private final MessageReceiver receiver;
    private final Socket socket;

    public MessageDispatcher(MessageReceiver receiver, Socket socket) {
        this.receiver = receiver;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            byte opcodeRaw = inStream.readByte();
            String group = inStream.readUTF();
            String payload = inStream.readUTF();
            OpCode opcode = OpCode.fromCode(opcodeRaw);
            if (opcode == OpCode.NONE) {
                System.out.println("Umknown opcode received");
            } else {
                Message message = new Message(opcode, payload, socket.getInetAddress().getHostAddress(), socket.getPort());
                message.setGroup(group);
                receiver.dispatchMessage(message);
            }
        } catch (IOException e) {
            System.out.println("Error dispatching message : " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error closing socket : " + e.getMessage());
                }
            }
        }
    }
}
