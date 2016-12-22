/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picontrol;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Lighting implements WebCommandInterface{
    
    public Lighting(){
        NodeJsConnection.thread.addEventListener(this);
    }

    @Override
    public void commandReceived(String command) {
        System.out.println("Lighting Object received: " + command);
        if(JsonObject.readFrom(command).get("lighting") != null){
            updatePinStates(JsonObject.readFrom(command).get("lighting").asObject());
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
