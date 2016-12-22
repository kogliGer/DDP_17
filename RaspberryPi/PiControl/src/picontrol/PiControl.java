/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picontrol;


/**
 *
 * @author alexa
 */
public class PiControl implements WebCommandInterface{

    public static void main(String[] args) {
        System.out.println("hello world");
        NodeJsConnection.setup();
        GPIO.setup();
        PiControl obj = new PiControl();
        Lighting lightning = new Lighting();
        
        NodeJsConnection.sendMessage("hello");
        try{
            Thread.sleep(10000);
        }catch(Exception ex){
            
        }
    }

    public PiControl(){
        NodeJsConnection.thread.addEventListener(this);
    }
    @Override
    public void commandReceived(String command) {
        System.out.println("PiControl Object received: " + command);
        
    }
    
}
