package com.webservice.tecdrivews.resources;

import javax.ws.rs.core.Response;
import com.webservice.json.JSONHandler;

import javax.ws.rs.*;

/**
 *
 * @author 
 */
@Path("getDrive/{username}")
public class getDrive {
            
    @GET
    public Response ping(@PathParam("username") String username){
        
        JSONHandler handler = JSONHandler.getInstance();
        
        return Response
            .ok(handler.getDrive(username)).header("Access-Control-Allow-Origin", "*")
            .build();
    }
}