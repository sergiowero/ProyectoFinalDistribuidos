/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastserver;

import communication.multicast.MulticastServer;

/**
 *
 * @author Sergio
 */
public class MulticastServerStandalone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MulticastServer server = new MulticastServer();
        server.run();
    }
    
}
