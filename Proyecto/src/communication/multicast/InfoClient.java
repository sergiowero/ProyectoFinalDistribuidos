/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.multicast;

/**
 *
 * @author AlumnosA14
 */
public class InfoClient {

    public String ipAddress;
    public String group;
    public int port;

    InfoClient() {
        ipAddress = null;
        group = null;
    }

    InfoClient(String address, int port) {
        this.ipAddress = address;
        this.port = port;
    }
}
