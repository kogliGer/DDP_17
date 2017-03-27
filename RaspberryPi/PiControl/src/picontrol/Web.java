/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picontrol;

import com.eclipsesource.json.JsonObject;

/**
 *
 * @author Alexander
 */
public class Web {
    
    public static void sendMessage(String msg){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("message", msg);
        nodejsconnection.NodeJsConnection.sendJson(jsonObject);
    }
}
