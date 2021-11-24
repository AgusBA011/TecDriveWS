/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;

import Models.Archivo;
import com.webservice.json.JSONHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author agust
 */
@Path("CopiarRV/")
public class SubirArchivo {
    
    
    @GET
    public Response copiarRVEndpoint(@QueryParam("JSON") String json, @QueryParam("tipoArchivo") String tipoArchivo, 
                                            @QueryParam("path") String path)
    {   
        
        return Response
                .ok( SubirArchivo(path, json , tipoArchivo )  ).header("Access-Control-Allow-Origin", "*")
                .build();   
    }
   
    private JSONObject SubirArchivo( String path, String archivo, String tipoArchivo ){
    
        try{
        
            JSONParser parser = new JSONParser();
            
            JSONObject JsonArchivo = (JSONObject) parser.parse(archivo);
            
            Archivo aux = new Archivo();
            
            JsonArchivo.replace("creacion", parser);
            
            JSONObject response = JSONHandler.getInstance().insertContenido(path, JsonArchivo);
            
            return response;
            
        }
        catch(Exception e){
            JSONObject res = new JSONObject();
            res.put("Error", e);
            return res;
        }
       
    } 
    
    
    
}
