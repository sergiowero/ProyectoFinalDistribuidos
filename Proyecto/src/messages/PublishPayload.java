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
public class PublishPayload implements Payload {

    private final String topic;
    private final String title;
    private final String content;
    
    public PublishPayload(String topic, String title, String content){
        this.topic = topic;
        this.title = title;
        this.content = content;
    }
    
    @Override
    public OpCode GetOpCode() {
        return OpCode.PUBLISH;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    
}
