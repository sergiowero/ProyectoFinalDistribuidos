package communication;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.UUID;

public class MulticastPeer {

    private int port = 6789;
    private String group = "228.5.6.7";
    private MulticastServer server;

    public MulticastPeer() {
        try {
            server = new MulticastServer(group, port);
            server.start();
        } catch (Exception ex) {

        }
    }

    class MulticastServer extends Thread {

        private String group;
        private int port;
        private HashMap<InetAddress, String> connections;
        private MulticastSocket socketServer;

        public MulticastServer(String group, int port) {
            this.group = group;
            this.port = port;
            this.connections=new HashMap<InetAddress,String>();
        }

        @Override
        public void run() {
            super.run();
            try {
                InetAddress groupAddress = InetAddress.getByName(this.group);
                socketServer = new MulticastSocket(this.port);
                socketServer.joinGroup(groupAddress);
                byte buff[] = new byte[1000];
                String hellow="Hola";
                byte [] hellowbytes=hellow.getBytes();
                DatagramPacket hellowPacket=new DatagramPacket(hellowbytes,0,hellowbytes.length,groupAddress,this.port);
                socketServer.send(hellowPacket);
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buff, 0, buff.length, groupAddress, this.port);
                    socketServer.receive(packet);
                    if (!connections.containsKey(packet.getAddress())) {
                        System.out.println(packet.getAddress().toString());
                        connections.put(packet.getAddress(), UUID.randomUUID().toString());
                        byte[] table=connections.toString().getBytes();
                        socketServer.send(new DatagramPacket(table,0,table.length,groupAddress,this.port));
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }

    }
}
