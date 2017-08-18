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
import communication.messages.Payload;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author AlumnosA14
 */
public class MulticastServer implements MessageListener {

    public final static int PORT = 9999;

    private final ArrayList<RemoteGroup> remotes = new ArrayList<>();
    private final int port;
    private final MessageReceiver receiver;

    public MulticastServer() {
        port = PORT;
        receiver = new MessageReceiver(port);
    }

    public MulticastServer(int port) {
        this.port = port;
        receiver = new MessageReceiver(port);
    }

    public void run() {
        receiver.addListener(this);
        System.out.println("MULTICAST SERVER RUNNING AR PORT : " + port);
        receiver.start();
    }

    public RemoteGroup getGroup(String name) {
        for (RemoteGroup temp : remotes) {
            if (temp.name.equals(name)) {
                return temp;
            }
        }
        return null;
    }

    public RemoteGroup addGroup(String name) {
        RemoteGroup group = new RemoteGroup(name);
        remotes.add(group);
        System.out.println("Added Group " + name);
        return group;
    }

    @Override
    public void messageReceived(Message message) {
        System.out.println("MESSAGE RECEIVED : " + message);
        Payload payload = MessageCreator.CreatePayloadFromMessage(message);
        RemoteGroup group = null;
        switch (payload.GetOpCode()) {
            case JOIN_GROUP:
                JoinGroupPayload jgp = (JoinGroupPayload) payload;
                group = getGroup(jgp.getGroup());
                if (group != null) {
                    group.JoinGroup(jgp.getAddress(), jgp.getPort());
                } else {
                    group = addGroup(jgp.getGroup());
                    group.JoinGroup(jgp.getAddress(), jgp.getPort());
                }
                System.out.println("Client at " + jgp.getAddress() + ":" + jgp.getPort() + " joined group \"" + jgp.getGroup() + "\"");
                break;

            case LEAVE_GROUP:
                LeaveGroupPayload lgp = (LeaveGroupPayload) payload;
                group = getGroup(lgp.getGroup());
                if (group != null) {
                    group.LeaveGroup(lgp.getAddress(), lgp.getPort());
                    System.out.println("Client at " + lgp.getAddress() + ":" + lgp.getPort() + " leave group \"" + lgp.getGroup() + "\"");
                } else {
                    System.out.println("Group not found " + lgp.getGroup());
                }
                break;
            default: 
                // normal message
                group = getGroup(message.getGroup());
                if (group != null) {
                    group.Clients.forEach((tempclient) -> {
                        
                        MessageSender sender = new MessageSender(tempclient.ipAddress, tempclient.port);
                        try {
                            sender.send(message);
                        } catch (IOException e){
                            System.out.println("Error sending message to " + message.getAddress() + ":" + message.getPort());
                        }
                    });
                } else {
                    System.out.println("Group " + message.getGroup() + " not found");
                }
                break;
        }
    }
}
