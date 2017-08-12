/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 *
 * @author Laura
 */
public class MessageCreator {
    
    public static Message CreateMessageFromPayload(Payload payload){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String payloadData = gson.toJson(payload);
        return new Message(payload.GetOpCode(), payloadData);
    }
    
    public static Payload CreatePayloadFromMessage(Message message){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        String payloadText = message.getPayload();
        Payload payload = null;
        switch(message.getOpCode()){
            case SUBSCRIBE:
                payload = gson.fromJson(payloadText, SubscribePayload.class);
                break;
            case PUBLISH:
                payload = gson.fromJson(payloadText, PublishPayload.class);
                break;
            case UNSUBSCRIBE:
                payload = gson.fromJson(payloadText, UnsubcribePayload.class);
                break;
            case NOTIFY:
                payload = gson.fromJson(payloadText, NotifyPayload.class);
                break;
        }
        
        return payload;
    }
}
