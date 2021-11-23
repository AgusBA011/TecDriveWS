/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Admin
 */

@Path("rv")
public class SubirArchivo {
    
    
    
    @GET
    public Response ping() throws IOException, FileNotFoundException, ParseException{
        return Response
                .ok(JsonHandler.getInstancia().SubirArchivo("Usuario_1/Compartidos/Usuario 2" , "{\"nombre\": \"Nani\",\"tipo\": \"archivo\",\"extension\": \"txt\",\"creacion\": \"22/11/2021\",\"modificacion\": \"20/12/2021\",\"tamaño\": 5000,\"contenido\": \"Porque no funciona esta pichaaaa\",\"compartido\": []}"))
                .build();
    }
}
