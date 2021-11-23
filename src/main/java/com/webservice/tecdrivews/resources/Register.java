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
@Path("Register/")
public class Register {
    
    @GET
    public Response registerEndpoint(@QueryParam("nombre") String user, @QueryParam("bytes") int bytes){
        
        JSONObject response = createUser(user, bytes);
        
        return Response
            .ok(response)
            .build();
    }
    
    
    private JSONObject createUser( String username, int bytes ){
    
        JSONHandler handler = JSONHandler.getInstance();
        
        JSONObject drive = handler.getJson();
        
        JSONObject response = new JSONObject();
        
        if (! drive.containsKey(username)){ //Busca si la key ya existe.
        
            //Insertar el nuevo user

            //Crear carpeta root
            JSONObject value = new JSONObject();           
            Carpeta carpeta = new Carpeta("root");
            JSONObject json = carpeta.generateJSON();
            
            json.put("limite", bytes); //Agrega el limite de bytes del usuario.
            
            drive.put(username, json);//Insertar en el drive principal
            handler.updateJSONFile();//Actualizar JSON, CASO UNICO DONDE SE UTILIZA ESTA FUNCION.
            
            //Enviar response de éxito
            response.put("OK", "El usuario se ha creado con éxito");
            return drive; 
        }
        
        response.put("Error", "El nombre de usuario ya existe.");
        return response;  
    }
    
    
    
    
}
