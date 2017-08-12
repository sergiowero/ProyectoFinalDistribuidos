/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import communication.OpCode;
import java.util.ArrayList;

/**
 *
 * @author Laura
 */
public class SubscribePayload implements Payload {
    
    private final ArrayList<String> topics;

    public SubscribePayload(){
         topics = new ArrayList<>();
    }
    
    @Override
    public OpCode GetOpCode() {
        return OpCode.SUBSCRIBE;
    }

    /**
     * @return the topics
     */
    public ArrayList<String> getTopics() {
        return topics;
    }
    
}
