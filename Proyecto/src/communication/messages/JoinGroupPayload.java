/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.messages;

import communication.OpCode;

/**
 *
 * @author Laura
 */
public class JoinGroupPayload implements Payload {

    private final String address;
    private final int port;
    private final String group;

    public JoinGroupPayload(String address, int port, String group) {
        this.address = address;
        this.port = port;
        this.group = group;
    }

    @Override
    public OpCode GetOpCode() {
        return OpCode.JOIN_GROUP;
    }

    /**
     * @return the groupId
     */
    public String getGroup() {
        return group;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

}
