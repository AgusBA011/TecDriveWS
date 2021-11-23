package com.webservice.tecdrivews.resources;

import Models.Archivo;
import com.webservice.json.JSONHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

@Path("crearArchivo/")
public class crearArchivo {
    
    @GET
    public Response crearArchivoEndpoint(@QueryParam("path") String path, @QueryParam("nombre") String nombre, 
                                            @QueryParam("extension") String extension, @QueryParam("contenido") String contenido)
    {        
        return Response
            .ok(crearArchivo(path, nombre, extension, contenido)).header("Access-Control-Allow-Origin", "*")
            .build();
    }
    
    private JSONObject crearArchivo( String path, String nombre, String extension, String contenido ){    
        
        Archivo newArchivo = new Archivo(nombre, extension, contenido);        
        JSONObject archivoJSON = newArchivo.generateJSON();        
        JSONHandler handler = JSONHandler.getInstance();        
        
        return handler.insertContenido(path, archivoJSON);
    }  
}
