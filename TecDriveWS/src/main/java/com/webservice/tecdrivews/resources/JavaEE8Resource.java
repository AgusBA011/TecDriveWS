package com.webservice.tecdrivews.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.webservice.json.JSONHandler;
import org.json.JSONObject;

/**
 *
 * @author 
 */
@Path("javaee8")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok(test())
                .build();
    }
    
    
    private JSONObject test(){
        JSONHandler handler = new JSONHandler();
        return handler.readJSON();
    }
    
    
}
