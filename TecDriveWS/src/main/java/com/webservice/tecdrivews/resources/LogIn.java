package com.webservice.tecdrivews.resources;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("LogIn/{test}")
public class LogIn {
    
    @GET
    public String ping(@PathParam("test") String text){
        return "<h1>Prueba " + text + "</h1>";
    }
    
    
}
