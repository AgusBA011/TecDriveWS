/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.json;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONHandler {
    
    JSONObject json;
       
    public JSONHandler (){ //Cargar JSON
        readJSON();
    }
    
    public void readJSON () { //Leer JSON en la ruta especificada
        
        JSONParser parser = new JSONParser();
     
        try {
			Object obj = parser.parse(new FileReader("C:\\Users\\agust\\OneDrive\\Documents\\NetBeansProjects\\TecDriveWS\\TecDriveWS\\src\\main\\java\\com\\webservice\\json\\TecDrive.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
                        
			this.json = (JSONObject) obj;
        }
        catch (Exception e) { //Capturar error y desplegarlo en pantalla
            JSONObject obj = new JSONObject();
            obj.put("Error", e.toString());
            this.json = obj;
	}
    }
    
    
    public JSONObject getDrive(String key){ //Obtener Drive de un usuario dado
        
        JSONObject obj = (JSONObject) this.json.get((Object) key);
        
        
        if ( obj != null){
            return obj;
        }
        
        obj = new JSONObject();
        obj.put("Error", "El usuario consultado no fue encontrado ");
        return obj;    
    }
    
    public JSONObject getJson() { //Retornar toda la Database
        return json;
    }
    
    
    
      
    
    
}
