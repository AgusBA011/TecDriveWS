/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Admin
 */
@Path("delete")
public class JavaDelet {
    
    
    @GET
    public Response ping() throws IOException, FileNotFoundException, ParseException{
        return Response
                .ok(JsonHandler.getInstancia().deleteElement("Usuario_1/Compartidos/Usuario 2/", new String[]{"Ejemplo 2"} , new String[]{"txt"}))
                .build();
    }
}
