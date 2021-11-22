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
    
    /*
    ==============================
        VARIABLES
    ==============================
    */
    
    private static JSONHandler instance;
    
    JSONObject json;
    
    final String pathFile = "C:\\Users\\agust\\OneDrive\\Documents\\NetBeansProjects\\TecDriveWS\\TecDriveWS\\src\\main\\java\\com\\webservice\\json\\TecDrive.json";
    
    /*
    ==============================
        SINGLETON
    ==============================
    */
    private JSONHandler (){ //Constructor, cargar JSON
        readJSON();
    }
    
    public static JSONHandler getInstance (){
    
        if (instance == null){
            instance = new JSONHandler();
        }
        return instance;
    }    
    
    /*
    ==============================
        FUNCIONES
    ==============================
    */
    
    private void readJSON () { //Leer JSON en la ruta especificada
        
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
    
    //Inserta un elemento en una ruta dada.
    public JSONObject insertContenido(String path, JSONObject contenido){
    
        JSONArray directorio = getPath(path);
        JSONObject response = new JSONObject();
               
        if (!checkRepeat( contenido, directorio) || directorio == null){
            response.put("Error", "El nombre '" + (String) contenido.get("nombre") + "' ya existe en la ruta actual.");
            return response;
        }
        
        directorio.add(contenido);
        response.put("OK", "Se insertó el contenido con éxito");
        updateJSONFile();
        return response; 
    }
    
    //Elimina un elemento en una ruta dada.
    public JSONObject deleteElement(String path, String[] nombre, String[] tiposArchivo){
        JSONArray Directorio = getPath(path);
        JSONObject resultado = new JSONObject();
        
        for(int i = 0; i < nombre.length; i++){
            if (Directorio.size() >= 1){
                resultado = (JSONObject) Directorio.get(i);
                if(resultado.get((Object) "nombre").toString().equals(nombre[i]) && (resultado.get((Object) "extension").toString().equals(tiposArchivo[i]) || resultado.get((Object) "tipo").toString().equals(tiposArchivo[i])))
                { 
                    Directorio.remove(i);
                }
            }else {
                JSONObject errorJson = new JSONObject();
                errorJson.put("Error", "Nada que borrar");
                return errorJson;
            }
        }
        
        JSONObject errorJson = new JSONObject();
        errorJson.put("msj", "Elemento(s) removido(s) correctamente");
        return errorJson;
    }
    
    //Obtiene la ruta dada.
    public JSONArray getPath(String path){
        String[] listaPath = path.split("/");
        JSONObject UsuarioJson = getDrive(listaPath[0]);
        JSONArray ContenidoJson = (JSONArray) UsuarioJson.get((Object) "contenido");
        JSONObject aux = null;
        
        for(int num = 1; num < listaPath.length; num++){
            for(int i = 0; i < ContenidoJson.size(); i++){
                aux = (JSONObject) ContenidoJson.get(i);
                if(aux.get((Object) "tipo").toString().equals("carpeta") && aux.get((Object) "nombre").toString().equals(listaPath[num])){
                    ContenidoJson = (JSONArray) aux.get((Object) "contenido");
                }
                else{
                    JSONArray errorJson = new JSONArray();
                    errorJson.add((Object) "{'error':'La ruta definida no fue encontrada'}");
                    return errorJson;
                }
            }   
        }
        return ContenidoJson;
    }
   
    
    
    /*
    ==============================
        FUNCIONES AUXILIARES
    ==============================
    */
    
    //Obtener Drive de un usuario dado
    public JSONObject getDrive(String key){ 
        
        JSONObject obj = (JSONObject) this.json.get((Object) key);
        
        if ( obj != null){
            return obj;
        }
        
        obj = new JSONObject();
        obj.put("Error", "El usuario consultado no fue encontrado ");
        return obj;    
    }
    
    //Agarra el JSON actual y lo escribe en la ruta
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
    
    //Retornar toda la Database
    public JSONObject getJson() { 
        return json;
    }
    
    
    //Funcion que revisa si hay un archivo con el mismo nombre en la carpeta
    private Boolean checkRepeat(JSONObject newElement, JSONArray jsonArray){
        
        String eleName = (String) newElement.get("nombre");        
        String eleTipo = (String) newElement.get("tipo");
        
        //Itera por cada archivo o carpeta en la ruta dada.
        for(  int i = 0; i < jsonArray.size(); i++ ){
            JSONObject objectInArray = (JSONObject) jsonArray.get(i);            
            if ( eleName.equals((String) objectInArray.get("name"))){
                if ( eleTipo.equals("archivo") ){ //Intentando insertar un archivo
                    if (  ((String) newElement.get("extension")).equals((String)objectInArray.get("extension"))){
                      return false; //Archivo con el mismo nombre y misma extensión                 
                    }
                }
                else{return false;} //Carpeta con el mismo nombre       
            }            
        }
        return true;
    }
}
