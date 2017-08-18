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
import java.net.Socket;

/**
 *
 * @author Sergio
 */
public class MessageSender {

    private final String serverAddress;
    private final int serverPort;
    private Socket socket;

    public MessageSender(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void send(Message message) throws IOException {

        try {
            socket = new Socket(serverAddress, serverPort);
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            int code = message.getOpCode().code();
            outStream.writeByte(code);
            outStream.writeUTF(message.getGroup());
            outStream.writeUTF(message.getPayload());
            outStream.flush();
        }catch (IOException e){
            System.err.println("MessageSender error [" + serverAddress + "] : " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
