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
        
        JSONObject verify;
        
        if ( ((String) contenido.get("tipo")).equals("carpeta") ){ //En caso de insertar una carpeta
            verify = getDirectorio(path, (String) contenido.get("nombre") );
        }else{
            verify = getArchivo(path, (String) contenido.get("nombre"), (String) contenido.get("extension") );
        }

        if ( verify.containsKey("Error") == false  ) {
            response.put("Error", "El nombre '" + (String) contenido.get("nombre") + "' ya existe en la ruta actual.");
            return response;
        }
        
        directorio.add(contenido);
        response.put("OK", "Se insertó el contenido con éxito");
        updateJSONFile();
        return response; 
    }
    
    //Elimina un elemento en una ruta dada.
    public JSONObject deleteElement(String path, String nombre, String tiposArchivo){
        JSONArray Directorio = getPath(path);
        JSONObject resultado = new JSONObject();
        
        int flagDeleted = 0;
        
        for(int i = 0; i < Directorio.size(); i++){
            
            resultado = (JSONObject) Directorio.get(i);
            
            if( ( (String) resultado.get((Object) "nombre")).equals(nombre) && (resultado.get((Object) "extension").toString().equals(tiposArchivo) || resultado.get((Object) "tipo").toString().equals(tiposArchivo)))            { 
                Directorio.remove(i);
                flagDeleted = 1;
            }
        }
        
        JSONObject response = new JSONObject();
        if(flagDeleted == 1){
            updateJSONFile();
            response.put("OK", "Elemento(s) removido(s) correctamente");
            return response;
        }
        response.put("Error", "Hubo un problema al eliminar el elemento " + nombre);
        return response;
        //

    }
   

    public JSONArray getPath(String path){
        String[] listaPath = path.split("/");
        JSONObject UsuarioJson = getDrive(listaPath[0]);
        JSONArray ContenidoJson = (JSONArray) UsuarioJson.get((Object) "contenido");
        JSONObject aux = null;
        
        for(int num = 1; num < listaPath.length; num++){
            
            int errores = 0;
            
            for(int i = 0; i < ContenidoJson.size(); i++){
                aux = (JSONObject) ContenidoJson.get(i);
                if(aux.get((Object) "tipo").toString().equals("carpeta") && aux.get((Object) "nombre").toString().equals(listaPath[num])){
                    ContenidoJson = (JSONArray) aux.get((Object) "contenido");
                    System.out.println(aux.get((Object) "tipo").toString() + "  "+ aux.get((Object) "nombre").toString());
                    System.out.println("carpeta" + "  " + listaPath[num]);
                }
                else{
                    errores++;
                    if(errores == ContenidoJson.size()){
                        JSONArray errorJson = new JSONArray();
                        errorJson.add((Object) "{'error':'La ruta definida no fue encontrada'}");
                        return errorJson;
                    }
                
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
    
    
    public JSONObject getArchivo(String path, String nombre, String extension){
    
        JSONArray directorio = getPath(path);

        for (int i=0; i < directorio.size(); i++){
            
            JSONObject objectInArray = (JSONObject) directorio.get(i);
            
            String objName = (String) objectInArray.get("nombre");
            String objExt = (String) objectInArray.get("extension");
            
            if ( nombre.equals(objName) && extension.equals(objExt)){     
                //Se encontró el file
                return objectInArray;
            } 
            
        }        
        //No lo encontró
        JSONObject response = new JSONObject ();
        response.put("Error", "Hubo un problema");
        return response; 
    }
    
    public JSONObject getDirectorio(String path, String nombre){
    
        JSONArray directorio = getPath(path);

        for (int i=0; i < directorio.size(); i++){
            JSONObject objectInArray = (JSONObject) directorio.get(i);
            if ( nombre.equals((String) objectInArray.get("nombre")) ){     
                //Se encontró el directorio
                return objectInArray;
            }  
        }
        //No lo encontró
        JSONObject response = new JSONObject ();
        response.put("Error", "Hubo un problema");
        return response; 
    }
}
