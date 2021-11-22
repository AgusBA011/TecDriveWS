package com.webservice.tecdrivews.resources;

import com.webservice.json.JSONHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
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

//crearArchivo?path=""&nombre=""&extension=""&contenido=""

@Path("modificarArchivo/")
public class modificarArchivo {

    @GET
    public Response modificarArchivoEndpoint(@QueryParam("path") String path, @QueryParam("nombre") String nombre, 
                                            @QueryParam("extension") String extension, @QueryParam("contenido") String contenido)
    {        
        return Response
            .ok(modificarArchivo(path, nombre, extension, contenido))
            .build();
    }
    
    
    private JSONObject modificarArchivo( String path, String nombre, String extension, String contenido ){
        
        JSONHandler handler = JSONHandler.getInstance(); 
        
        JSONObject archivo = handler.getArchivo(path, nombre, extension);
        
        JSONObject response = new JSONObject ();
        
        try{
            archivo.replace("contenido", contenido);
            //FALTARÍA MODIFICAR EL ARCHIVO
            
            //Respuesta
            response.put("OK", "El archivo fue modificado con éxito");
            return response;
        
        }
        catch(Exception e){
            response.put("Error", e);
            return response;
        
        }
    }
 
}
