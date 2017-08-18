/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.multicast;

import communication.MessageListener;
import communication.MessageReceiver;
import communication.MessageSender;
import communication.messages.JoinGroupPayload;
import communication.messages.LeaveGroupPayload;
import communication.messages.Message;
import communication.messages.MessageCreator;
import communication.messages.PublishPayload;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import storage.Storage;
import ui.AppWindow;

/**
 *
 * @author Sergio
 * @author Laura
 */
public class MulticastClient implements MessageListener {

    public final static int PORT = 19999;

    private final MessageSender sender;
    private final MessageReceiver receiver;

    public MulticastClient(String serverAddress) {
        sender = new MessageSender(serverAddress, MulticastServer.PORT);
        receiver = new MessageReceiver(PORT);
    }
    
    public MulticastClient(String serverAddress, int serverPort) {
        sender = new MessageSender(serverAddress, serverPort);
        receiver = new MessageReceiver(PORT);
    }

    public MulticastClient() {
        sender = new MessageSender("127.0.0.1", MulticastServer.PORT);
        receiver = new MessageReceiver(PORT);
    }

    public void start() {
        receiver.addListener(this);
        receiver.start();
    }
    
    public void start(MessageListener listener) {
        receiver.addListener(this);
        receiver.addListener(listener);
        receiver.start();
    }

    public void stop() {
        receiver.removeListener(this);
        receiver.stopReceiving();
        receiver.interrupt();
    }

    public void joinGroup(String gruop) {
        try {
            String address = Inet4Address.getLocalHost().getHostAddress();
            Message message = MessageCreator.CreateMessageFromPayload(new JoinGroupPayload(address, PORT, gruop));
            sender.send(message);
        } catch (IOException e) {
            System.out.println("Error sending join group message " + e.getMessage());
        }
    }

    public void leaveGroup(String gruop) {
        try {
            String address = Inet4Address.getLocalHost().getHostAddress();
            Message message = MessageCreator.CreateMessageFromPayload(new LeaveGroupPayload(address, PORT, gruop));
            sender.send(message);
        } catch (IOException e) {
            System.out.println("Error sending leave group message " + e.getMessage());
        }
    }

    public void send(String group, Message message) {
        try {
            message.setGroup(group);
            sender.send(message);
        } catch (IOException e) {
            System.out.println("Error sending message to group " + group + " : " + e.getMessage());
        }
    }

    @Override
    public void messageReceived(Message message) {
        //usar funciones de java
        
        
//        System.out.println("Client - Message received");
//        System.out.println("         From    :" + message.getAddress() + ":" + message.getPort());
//        System.out.println("         Group   :" + message.getGroup());
//        System.out.println("         OpCode  :" + message.getOpCode());
//        System.out.println("         Payload :" + message.getPayload());
        
//        PublishPayload publishPayload = (PublishPayload)MessageCreator.CreatePayloadFromMessage(message);
//        File file = new File( AppWindow.getTopic(publishPayload.getTopic()) + ".json");
//        Storage.saveFile(file, message.getPayload());
        
        
    }
}
