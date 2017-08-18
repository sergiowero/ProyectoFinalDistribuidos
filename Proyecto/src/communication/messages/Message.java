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
public class Message {

    private final OpCode opCode;
    private final String payload;
    private String group;
    private final String address;
    private final int port;

    public Message(OpCode opCode, String payload) {
        this.opCode = opCode;
        this.payload = payload;
        this.group = "";
        this.address = "";
        this.port = 0;
    }

    public Message(OpCode opCode, String payload, String address, int port) {
        this.opCode = opCode;
        this.payload = payload;
        this.group = "";
        this.address = address;
        this.port = port;
    }

    /**
     * @return the opCode
     */
    public OpCode getOpCode() {
        return opCode;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
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

    
    @Override
    public String toString(){
        return opCode + " | " + group + " | " + address + ":" + port + " | " + payload;
    }
}
