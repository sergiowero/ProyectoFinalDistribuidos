/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import communication.messages.MessageCreator;
import communication.messages.NotifyPayload;
import communication.multicast.MulticastClient;

/**
 *
 * @author Sergio
 */
public class MulticastClientTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MulticastClient client = new MulticastClient();
        client.joinGroup("ola");
        client.joinGroup("ke");
        client.joinGroup("ace");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        client.start();
        client.send("ola", MessageCreator.CreateMessageFromPayload(new NotifyPayload(1)));
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        client.stop();
    }

}
