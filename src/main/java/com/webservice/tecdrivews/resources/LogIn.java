package com.webservice.tecdrivews.resources;


import com.webservice.json.JSONHandler;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("LogIn/")
public class LogIn {
    
    @GET
    public Response logInEndpoint(@QueryParam("username") String user){
        JSONHandler handler = JSONHandler.getInstance();
        return Response
            .ok(handler.getDrive(user)).header("Access-Control-Allow-Origin", "*")
            .build();
    }
    
}
