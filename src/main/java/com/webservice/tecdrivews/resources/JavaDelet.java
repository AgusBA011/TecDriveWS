/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;


import com.webservice.json.JSONHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import javax.ws.rs.QueryParam;
import org.json.simple.JSONObject;
/**
 *
 * @author Admin
 */
@Path("delete/")
public class JavaDelet {

    @GET
    public Response deleteArchivoEndpoint(@QueryParam("path") String path, @QueryParam("nombres") String nombres, 
                                            @QueryParam("tiposArchivo") String tiposArchivo)
    {   
        
        String[] nombresList = nombres.split("/");
        String[] tiposList = tiposArchivo.split("/");
        
        return Response
                .ok( deleteArchivos(path, nombresList , tiposList )  ).header("Access-Control-Allow-Origin", "*")
                .build();   
    }
   
    private JSONObject deleteArchivos( String path, String[] nombres, String[] tiposArchivo ){
    
        JSONObject response = new JSONObject();
        for (int i = 0; i < nombres.length; i++){
            
            JSONObject aux = JSONHandler.getInstance().deleteElement(path, nombres[i], tiposArchivo[i]); 
            response.put(i, aux);
            
        }
        return response;
    }
    
    
    
}
