/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Admin
 */
public final class JsonHandler {
    
    private static JsonHandler instancia;
    private final JSONParser parser = new JSONParser();
    JSONObject prueba;
    
    private JsonHandler() throws FileNotFoundException, IOException, ParseException{
        
        try {
			Object obj = parser.parse(new FileReader("D:/User/Escritorio/prueba.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			prueba = (JSONObject) obj;
        }
        catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public JSONArray getPath(String path){
        String[] listaPath = path.split("/");
        JSONObject UsuarioJson = (JSONObject) prueba.get((Object) listaPath[0]);
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
                errorJson.put("error", "Nada que borrar");
                return errorJson;
            }
        }
        
                JSONObject errorJson = new JSONObject();
                errorJson.put("msj", "Elemento(s) removido(s) correctamente");
                return errorJson;
    }
    
    public static JsonHandler getInstancia() throws IOException, FileNotFoundException, ParseException{
        if(instancia == null){
            instancia = new JsonHandler();
        }
        return instancia;
    }
    
    
    public JSONArray SubirArchivo(String path, String Archivo){
        
        try {
            JSONObject JsonArchivo = (JSONObject) parser.parse(Archivo);
            JSONArray temp = getPath(path);
            temp.add(JsonArchivo);

            JSONObject msjJson = new JSONObject();
            msjJson.put("msj", "Se inserto el archivo correctamente");
            return temp;
            
        } catch (ParseException ex) {
           
        }
        return new JSONArray();
    }
    
    
    
}
