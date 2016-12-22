
package picontrol;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NodeJsConnectionThread extends Thread{
    
    volatile boolean run = true;
    BufferedReader reader;
    
    public NodeJsConnectionThread(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
    }
    
    public void run(){
        while(run){
            try{
                if(reader.ready()){
                    System.out.println(reader.readLine());
                }
            }catch(Exception ex){
                    System.out.println(ex);
            }      
        }
    }
}
