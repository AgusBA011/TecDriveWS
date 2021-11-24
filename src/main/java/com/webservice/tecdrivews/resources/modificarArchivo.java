package com.webservice.tecdrivews.resources;

import Models.Archivo;
import Models.modelArchivo;
import com.webservice.json.JSONHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

//modificarArchivo?path=""&nombre=""&extension=""&contenido=""

@Path("modificarArchivo/")
public class modificarArchivo {

    @GET
    public Response modificarArchivoEndpoint(@QueryParam("path") String path, @QueryParam("nombre") String nombre, 
                                        @QueryParam("extension") String extension, @QueryParam("contenido") String contenido, @QueryParam("sobreescribir") boolean sobreescribir)
    {        
        //
        contenido.replaceAll("%0A", "\n");
                
        return Response
            .ok(modificarArchivo(path, nombre, extension, contenido, sobreescribir)).header("Access-Control-Allow-Origin", "*")
            .build();
    }
    
    private JSONObject modificarArchivo( String path, String nombre, String extension, String contenido, boolean sobreescribir ){
        
        JSONHandler handler = JSONHandler.getInstance(); 
        
        JSONObject archivo = handler.getArchivo(path, nombre, extension);
        
        JSONObject response = new JSONObject ();
        
        if ( archivo.containsKey("Error")  ) {
            response.put("Error", "El archivo '" + nombre + "' no fue encontrado.");
            return response;
        }
        
        if (sobreescribir){
            Archivo newArchivo = new Archivo(nombre, extension, contenido);
            
            archivo.replace("contenido", contenido);
            archivo.replace("modificacion",newArchivo.getNowDate());
            archivo.replace("creacion",newArchivo.getNowDate());
            archivo.replace("tamano",newArchivo.getStringSizeFile(contenido));
            
            ArrayList <String> empty = new ArrayList <String>();
            archivo.replace("compartido", empty );
            
        }
        else{
            Archivo newArchivo = new Archivo();
            archivo.replace("contenido", contenido);
            archivo.replace("modificacion",newArchivo.getNowDate());
            archivo.replace("tamano",newArchivo.getStringSizeFile(contenido));  
        }
        
        handler.updateJSONFile(path.split("/")[0]);

        //Respuesta
        response.put("OK", "El archivo fue modificado con éxito");
        return response;
    }
 
}
