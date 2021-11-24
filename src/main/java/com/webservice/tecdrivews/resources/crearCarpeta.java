package com.webservice.tecdrivews.resources;

import Models.Archivo;
import Models.Carpeta;
import com.webservice.json.JSONHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agust
 */

//crearArchivo?path=""&nombre=""

@Path("crearDirectorio/")
public class CrearCarpeta {

    @GET
    public Response crearDirectorioEndpoint(@QueryParam("path") String path, @QueryParam("nombre") String nombre, @QueryParam("sobreescribir") boolean sobreescribir)
    {        
        return Response
            .ok(crearCarpeta(path, nombre, sobreescribir)).header("Access-Control-Allow-Origin", "*")
            .build();
    }
    
    public JSONObject crearCarpeta( String path, String nombre, boolean sobreescribir){   
        
        
        if(sobreescribir){
        
            Carpeta newCarpeta = new Carpeta();
            
            JSONHandler handler = JSONHandler.getInstance(); 
        
            JSONObject carpeta = handler.getDirectorio(path, nombre);
            
            carpeta.replace("creacion", newCarpeta.getNowDate());
            
            carpeta.replace("modificacion", newCarpeta.getNowDate());
            
            carpeta.replace("contenido", new JSONArray());
            
            carpeta.replace("tamano", 0);
            
            JSONObject response = new JSONObject ();
            response.put("OK", "El archivo fue modificado con éxito");
            return response;
            
        }
        else{
            Carpeta newDirectorio = new Carpeta(nombre);  
            JSONObject carpetaJSON = newDirectorio.generateJSON();
            JSONHandler handler = JSONHandler.getInstance();        
            return handler.insertContenido(path, carpetaJSON);
        
        }   
    }  
}
