/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import communication.OpCode;
/**
 *
 * @author Laura
 */
public class Message {
    private final OpCode opCode;
    private final String payload;
    
    public Message(OpCode opCode, String payload){
        this.opCode = opCode;
        this.payload = payload;
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
    
    
}
