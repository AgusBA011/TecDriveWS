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
    private final JsonHandler parser;

    public JsonExampleReturn() throws IOException, FileNotFoundException, ParseException {
        this.parser = new JsonHandler();
    }
    @GET
    public Response ping(){
        
        return Response
                .ok(parser.patata())
                .build();
    }
}
