/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picontrol;

import com.eclipsesource.json.JsonObject;
import com.pi4j.io.gpio.PinState;
import nodejsconnection.NodeJsConnection;
import nodejsconnection.WebCommandInterface;

public class Lighting implements WebCommandInterface{
    
    public Lighting(){
        NodeJsConnection.thread.addEventListener(this);
    }

    @Override
    public void commandReceived(JsonObject jsonObject) {
        System.out.println("Lighting Object received: " + jsonObject.toString());
        if(jsonObject.get("lighting") != null){
            updatePinStates(jsonObject.get("lighting").asObject());
        }
    }
    
    public void updatePinStates(JsonObject jsonObject){
        boolean value = JsonObject.readFrom("1").get("lighting").asBoolean();
        if(value == false){
            GPIO.LED1.setState(PinState.LOW);
        }else if(value == true){
            GPIO.LED1.setState(PinState.HIGH);
        }
    }
}
