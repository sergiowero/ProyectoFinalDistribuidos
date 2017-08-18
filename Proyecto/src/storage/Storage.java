/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.io.*;
/**
 *
 * @author Sergio
 * @author Laura
 */
public class Storage {
    
    public static void saveFile(File file, String data)
    {
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
        
        
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            
            bufferedWriter.write(data);
            bufferedWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }       
    }
    
    public static String loadFile(File file)
    {
        String data = "";
        
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            
            Reader reader = new InputStreamReader(new FileInputStream(file));
            
            int c = 0;
            StringBuilder stringBuilder = new StringBuilder(512);
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            
            data = stringBuilder.toString();
            reader.close();
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return data;
    
    }
    
    public static void updateFile(File oldFile,byte[] newData){
        
        try{
        FileOutputStream fileOutputStream=new FileOutputStream(oldFile,false);
        fileOutputStream.write(newData);
        fileOutputStream.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
}
