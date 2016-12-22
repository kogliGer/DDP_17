/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picontrol;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.WriterConfig;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author alexa
 */
public final class NodeJsConnection{
    
    final static String HOST = "localhost";
    final static int PORT = 5000;
    static OutputStream outputStream;
    static InputStream inputStream;
    static Writer out;
    static Socket socket;
     static NodeJsConnectionThread thread;
        
    public static void setup(){
        try {
            SocketAddress address = new InetSocketAddress(HOST, PORT);
            socket = new Socket();
            socket.setReuseAddress(true);
            socket.connect(address);
            outputStream = socket.getOutputStream();
            out = new BufferedWriter(new OutputStreamWriter(outputStream));
            inputStream = socket.getInputStream();
            thread = new NodeJsConnectionThread(inputStream);
            thread.start();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void sendMessage(String message){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("message", message);
        try{
            jsonObject.writeTo(out);
            //out.append("{\"message:\"" + message + "\"}").append("\n");
            out.flush(); 
        }catch(Exception ex){
            System.out.println(ex);
        }
        
    }
}
