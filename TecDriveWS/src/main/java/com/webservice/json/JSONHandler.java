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
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONHandler {
    
    private static JSONHandler instance;
    
    JSONObject json;
    
    final String pathFile = "C:\\Users\\agust\\OneDrive\\Documents\\NetBeansProjects\\TecDriveWS\\TecDriveWS\\src\\main\\java\\com\\webservice\\json\\TecDrive.json";
    
    private JSONHandler (){ //Constructor, cargar JSON
        readJSON();
    }
    
    public static JSONHandler getInstance (){
    
        if (instance == null){
            instance = new JSONHandler();
        }
        return instance;
    }    
    
    public void readJSON () { //Leer JSON en la ruta especificada
        
        JSONParser parser = new JSONParser();
     
        try {
			Object obj = parser.parse(new FileReader(this.pathFile));
 
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
    
    public JSONObject insertContenido(String path, JSONObject contenido){
    
        JSONArray carpeta = getPath(path);
        JSONObject response = new JSONObject();
        
        if (!checkRepeat( (String) contenido.get("nombre"), carpeta) || carpeta == null){
            response.put("Error", "Ya existe un elemento con el mismo nombre");
            return response;
        }
        
        carpeta.add(contenido);
        response.put("OK", "Se insertó el contenido con éxito");
        updateJSONFile();
        return response; 
    }
    
    public JSONArray getPath(String path){
        String[] listaPath = path.split("/");
        
        JSONObject UsuarioJson = getDrive(listaPath[0]);
  
        //JSONObject UsuarioJson = (JSONObject) prueba.get((Object) listaPath[0]);
        JSONArray ContenidoJson = (JSONArray) UsuarioJson.get((Object) "contenido");
        JSONObject aux = null;
        
        for(int num = 1; num < listaPath.length; num++){
            
            for(int i = 0; i < ContenidoJson.size(); i++){
                aux = (JSONObject) ContenidoJson.get(i);
                if(aux.get((Object) "nombre").toString().equals(listaPath[num])){
                    ContenidoJson = (JSONArray) aux.get((Object) "contenido");
                   }
            }         
        }
        return ContenidoJson;
    }
    
    public Boolean checkRepeat(String newName, JSONArray jsonArray){
          
        for(  int i = 0; i < jsonArray.size(); i++ ){
        
            JSONObject objectInArray = (JSONObject) jsonArray.get(i);
            
            if ( newName.equals((String) objectInArray.get("name"))){
            
                return false;
            }
        }
        return true;
    }
    
    public void updateJSONFile(){
    
        //Write into the file
            try (FileWriter file = new FileWriter(this.pathFile)) 
            {
                file.write(this.json.toJSONString());                
            } 
            catch (IOException ex) {
                Logger.getLogger(JSONHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
      
    
    
}
