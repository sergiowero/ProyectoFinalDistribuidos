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
public class NotifyPayload implements Payload {

    private final int topic;
    private final String action;
    private final byte[] data;

    
        public NotifyPayload(int topic) {
        this.topic = topic;
        this.action="";
        this.data=null;
    }
    
    public NotifyPayload(int topic,String action,byte[] data) {
        this.topic = topic;
        this.action=action;
        this.data=data;
    }

    @Override
    public OpCode GetOpCode() {
        return OpCode.NOTIFY;
    }

    /**
     * @return the topic
     */
    public int getTopic() {
        return topic;
    }

    public String getAction() {
        return action;
    }

    public byte[] getData() {
        return data;
    }
    
    
}
