/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import communication.messages.PublishPayload;
import communication.messages.SubscribePayload;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laura
 */
public class TopicFile {

    public TopicList posts;
    public String fileName;

    public TopicFile(String fileName) {
        posts = new TopicList();
        this.fileName = fileName;
    }

    public void load() {
        File file = new File(fileName);
        String data = Storage.loadFile(file);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        posts = gson.fromJson(data, TopicList.class);
        if (posts == null) {
            posts = new TopicList();
        }
    }

    public void update(byte[] data) {
        try {
            File file = new File(fileName);
            if (data.length > Files.readAllBytes(file.toPath()).length) {
                Storage.updateFile(file, data);
                load();
            } else if (data.length == Files.readAllBytes(file.toPath()).length) {
                System.out.println("Son iguales");
            }
        } catch (IOException ex) {
            Logger.getLogger(TopicFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void add(PublishPayload publishPayload) {
        posts.posts.add(publishPayload);
    }

    public synchronized byte[] save() {
        try {
            File file = new File(fileName);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            String data = gson.toJson(posts);
            Storage.saveFile(file, data);

            return Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            Logger.getLogger(TopicFile.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        
//        String data = gson.toJson(posts);
        if (posts != null) {
            for (int i = 0; i < posts.posts.size(); i++) {
                PublishPayload publishPayload = posts.posts.get(i);
                stringBuilder.append("\t" + publishPayload.getTitle() + "\n");
                stringBuilder.append(publishPayload.getContent() + "\n\n");
            }
        }
        return stringBuilder.toString();
    }

}

class TopicList {

    public List<PublishPayload> posts;

    public TopicList() {
        posts = new ArrayList<>();
    }
}
