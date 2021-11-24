/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;

import com.webservice.json.JSONHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author agust
 */
@Path("CopiarVV/")
public class CopiarArchivo {
    
    @GET
    public Response copiarVVEndpoint(@QueryParam("path") String path, @QueryParam("nombre") String nombre, 
                                        @QueryParam("tipoArchivo") String tipoArchivo, @QueryParam("nuevoPath") String nuevoPath)
    {   
        
        return Response
                .ok( CopiarArchivo(path, nombre, tipoArchivo, nuevoPath )  ).header("Access-Control-Allow-Origin", "*")
                .build();   
    }
   
    public JSONObject CopiarArchivo( String path, String nombre, String tipoArchivo, String nuevoPath ){
    
        //Se obtiene el file que se quiere copiar
            JSONObject fileCopiado;        
            if ( tipoArchivo.equals("carpeta") ){ //En caso de insertar una carpeta            
                fileCopiado = JSONHandler.getInstance().getDirectorio(path, nombre );
            }
            else{
                fileCopiado = JSONHandler.getInstance().getArchivo(path, nombre, tipoArchivo );
            }
            //En caso de no encontrarlo.
            if ( fileCopiado.containsKey("Error")) {
                return fileCopiado;
            }        
        //Se copia el file con éxito
            JSONObject response = JSONHandler.getInstance().insertContenido(nuevoPath, fileCopiado);
            if ( response.containsKey("Error")) {
                return response;
            }
        //Mensaje de que todo salió bien  
            JSONObject responseSucces = new JSONObject();
            responseSucces.put("OK","Se copió el elemento con éxito");
            
            return responseSucces;
    }
}
