package picontrol;


import com.eclipsesource.json.JsonObject;
import nodejsconnection.NodeJsConnection;
import nodejsconnection.WebCommandInterface;


public class PiControl implements WebCommandInterface{

    public static void main(String[] args) {
        System.out.println("hello world");
        NodeJsConnection.setup();
        GPIO.setup();
        PiControl obj = new PiControl();
        Lighting lightning = new Lighting();
        
        Web.sendMessage("hello");
        try{
            Thread.sleep(10000);
        }catch(Exception ex){
            
        }
    }

    public PiControl(){
        NodeJsConnection.thread.addEventListener(this);
    }
    
    
    @Override
    public void commandReceived(JsonObject json) {
        System.out.println("PiControl Object received: " + json.toString());
        
    } 
}
