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
public class UnsubcribePayload implements Payload {
    
    private final String topic;
    
    public UnsubcribePayload(String topic){
        this.topic = topic;
    }

    @Override
    public OpCode GetOpCode() {
        return OpCode.UNSUBSCRIBE;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }
    
}
