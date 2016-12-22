/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picontrol;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author alexa
 */
public class NodeJsConnection extends Thread {
    
    final static String HOST = "localhost";
    final static int PORT = 5000;
    static OutputStream outputStream;
    static InputStream inputStream;
    static Socket socket;
        
    public NodeJsConnection(){
        try {
            SocketAddress address = new InetSocketAddress(HOST, PORT);
            socket = new Socket();
            socket.setReuseAddress(true);
            socket.connect(address);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            outputStream.write(121);
            NodeJsConnectionThread thread = new NodeJsConnectionThread(inputStream);
            thread.run();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void run(){
        
    }
}
