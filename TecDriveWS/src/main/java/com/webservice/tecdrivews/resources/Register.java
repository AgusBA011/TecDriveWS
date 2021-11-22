/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;

import Models.Carpeta;
import com.webservice.json.JSONHandler;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author 
 */
@Path("Register/{user}")
public class Register {
    
    @GET
    public Response registerEndpoint(@PathParam("user") String user){
        
        JSONObject response = createUser(user);
        
        return Response
            .ok(response)
            .build();
    }
    
    
    private JSONObject createUser( String username ){
    
        JSONHandler handler = JSONHandler.getInstance();
        
        JSONObject drive = handler.getJson();
        
        JSONObject response = new JSONObject();
        
        if (! drive.containsKey(username)){ //Busca si la key ya existe.
        
            //Insertar el nuevo user

            //Crear carpeta root
            JSONObject value = new JSONObject();           
            Carpeta carpeta = new Carpeta("root");
            JSONObject json = carpeta.generateJSON();
            
            drive.put(username, json);//Insertar en el drive principal

            //Enviar response de éxito
            response.put("OK", "El usuario se ha creado con éxito");
            return drive; 
        }
        
        response.put("Error", "El nombre de usuario ya existe.");
        return response;  
    }
    
    
    
    
}
