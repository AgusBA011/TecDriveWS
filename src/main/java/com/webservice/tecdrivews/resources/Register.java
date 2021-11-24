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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author 
 */
@Path("Register/")
public class Register {
    
    @GET
    public Response registerEndpoint(@QueryParam("nombre") String user, @QueryParam("bytes") long bytes){
        
        JSONObject response = createUser(user, bytes);
        
        return Response
            .ok(response).header("Access-Control-Allow-Origin", "*")
            .build();
    }
    
    
    private JSONObject createUser( String username, long bytes ){
    
        JSONHandler handler = JSONHandler.getInstance();
        
        JSONObject drive = handler.getJson();
        
        JSONObject response = new JSONObject();
        
        if ( drive.containsKey(username) == false){ //Busca si la key ya existe.
        
        //Insertar el nuevo user

            //Crear carpeta root         
            Carpeta carpeta = new Carpeta("root");
            
            Carpeta compartidos = new Carpeta("Compartidos");
            
            JSONObject compJSON = compartidos.generateJSON();
            
            JSONObject json = carpeta.generateJSON();
            
            JSONArray content = (JSONArray) json.get("contenido");
            
            content.add(compJSON);
            
            json.put("limite", bytes); //Agrega el limite de bytes del usuario.
            
            drive.put(username, json);//Insertar en el drive principal
            handler.updateJSONFile(username);//Actualizar JSON
            
            //Enviar response de éxito
            response.put("OK", "El usuario se ha creado con éxito");
            return json; 
        }
        
        response.put("Error", "El nombre de usuario ya existe.");
        return response;  
    }
    
    
    
    
}
