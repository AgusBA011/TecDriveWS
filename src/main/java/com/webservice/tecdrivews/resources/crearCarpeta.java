package com.webservice.tecdrivews.resources;

import Models.Archivo;
import Models.Carpeta;
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

//crearArchivo?path=""&nombre=""

@Path("crearDirectorio/")
public class crearCarpeta {

    @GET
    public Response crearDirectorioEndpoint(@QueryParam("path") String path, @QueryParam("nombre") String nombre)
    {        
        return Response
            .ok(crearCarpeta(path, nombre))
            .build();
    }
    
    
    private JSONObject crearCarpeta( String path, String nombre){   
        
        Carpeta newDirectorio = new Carpeta(nombre);  
        JSONObject carpetaJSON = newDirectorio.generateJSON();
        JSONHandler handler = JSONHandler.getInstance();        
        return handler.insertContenido(path, carpetaJSON);
    }  
}
