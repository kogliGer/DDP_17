
package picontrol;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Vector;

public class NodeJsConnectionThread extends Thread{
    
    volatile boolean run = true;
    BufferedReader reader;
    protected static Vector _listeners;//listening objects
     
    public NodeJsConnectionThread(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
    }
    
    public void run(){
        String msg;
        while(run){
            try{
                if(reader.ready()){
                    msg = reader.readLine();
                    fireCommandEvent(msg);
                }
            }catch(Exception ex){
                    System.out.println(ex);
            }      
        }
    }
    
    
     
    // Method for listener classes to register themselves
    public  void addEventListener(WebCommandInterface listener)
    {
        //System.out.println("adding event listener");
        if (_listeners == null)
            _listeners = new Vector();
             
        _listeners.addElement(listener);
    }
     
    // "fires" the event
    protected  void fireCommandEvent(String command)
    {
        //System.out.println("PHPConnection: fire:" + command);
        //System.out.println("listening "+ _listeners.toString()+ _listeners.isEmpty());
        if (_listeners != null && !_listeners.isEmpty())
        {
            Enumeration e = _listeners.elements();
            while (e.hasMoreElements())
            {
                WebCommandInterface interfaceVar = (WebCommandInterface) e.nextElement();
                interfaceVar.commandReceived(command);
            }
        }else{
            System.out.println("no listeners");
        }
    }
}
