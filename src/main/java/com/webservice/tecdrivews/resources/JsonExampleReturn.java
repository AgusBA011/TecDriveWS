package com.webservice.tecdrivews.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 
 */
@Path("jsonR")
public class JsonExampleReturn {
    
    
    
    @GET
    public Response ping() throws IOException, FileNotFoundException, ParseException{
        
        return Response
                .ok(JsonHandler.getInstancia().getPath("Usuario_1/Compartidos/Usuario 2/"))
                .build();
    }
}
