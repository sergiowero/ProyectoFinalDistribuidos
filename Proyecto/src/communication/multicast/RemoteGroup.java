/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.multicast;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AlumnosA14
 */
public class RemoteGroup {

    public String name;
    public List<InfoClient> Clients = new ArrayList<>();

    RemoteGroup(String name) {
        this.name = name;
    }

    public void JoinGroup(String address, int port) {
        boolean boFound = false;
        for (int i = 0; i < Clients.size(); i++) {
            InfoClient client = Clients.get(i);
            if (client.port == port
                    && client.ipAddress.equals(address)) {
                boFound = true;
                break;
            }
        }

        if (!boFound) {
            Clients.add(new InfoClient(address, port));
        }
    }

    public void LeaveGroup(String address, int port) {
        for (int i = 0; i < Clients.size(); i++) {
            InfoClient client = Clients.get(i);
            if (client.port == port && client.ipAddress.equals(address)) {
                Clients.remove(i);
                break;
            }
        }
    }
}
