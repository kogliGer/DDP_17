package picontrol;

import com.eclipsesource.json.JsonObject;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;
import nodejsconnection.NodeJsConnection;
import nodejsconnection.WebCommandInterface;


public final class GPIO implements WebCommandInterface{
    
    public final static GpioController GPIO_CONTROLLER = GpioFactory.getInstance();
    public final static GpioPinDigitalOutput LED1 = GPIO_CONTROLLER.provisionDigitalOutputPin(RaspiPin.GPIO_02, "LED1", PinState.LOW);
    
    public static void setup(){
        GPIO gpio = new GPIO();
        
        SoftPwm.softPwmCreate(1, 0, 100);
    }
    
    public GPIO(){
        NodeJsConnection.thread.addEventListener(this);
    }

    @Override
    public void commandReceived(JsonObject jsonObject) {
        if(jsonObject.get("GPIO") != null){
            JsonObject GPIOJsonObject = jsonObject.get("GPIO").asObject();
            controlGPIO(GPIOJsonObject);
        }
    }
    
    private void controlGPIO(JsonObject jsonObject){
        if(jsonObject.get("1") != null){
            int GPIO_1_value = jsonObject.get("1").asInt();
            System.out.println("setting pin 1 to " + GPIO_1_value);
            SoftPwm.softPwmWrite(1, GPIO_1_value);
        }
        
        if(jsonObject.get("2") != null){
            boolean GPIO_1_value = jsonObject.get("2").asBoolean();
            if(GPIO_1_value == true) GPIO.LED1.setState(PinState.HIGH);
            else GPIO.LED1.setState(PinState.LOW);
        }
    }
}
