package com.webservice.tecdrivews.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.webservice.json.JSONHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 
 */
@Path("javaee8")
public class JavaEE8Resource {
    
    private final JSONHandler handler;
    
    public JavaEE8Resource() throws IOException, FileNotFoundException, ParseException{
        this.handler = new JSONHandler();
    }
    
    @GET
    public Response ping(){
        return Response
            .ok(handler.getJson())
            .build();
    }
}
