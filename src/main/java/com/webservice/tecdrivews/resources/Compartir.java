/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;

import com.webservice.json.JSONHandler;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author agust
 */
@Path("Compartir/")
public class Compartir {
    
    @GET
    public Response compartirEndpoint(@QueryParam("usuario") String usuario, @QueryParam("nuevoUsuario") String nuevoUsuario, 
                                            @QueryParam("path") String path, @QueryParam("nombre") String nombre,
                                            @QueryParam("tipoArchivo") String tipoArchivo)
    {  
        return Response
                .ok( compartirArchivo(usuario, nuevoUsuario, path, nombre , tipoArchivo )  ).header("Access-Control-Allow-Origin", "*")
                .build();   
    }
   
    private JSONObject compartirArchivo( String usuario, String nuevoUsuario, String path, String nombre, String tipoArchivo ){
     
        JSONObject response = new JSONObject();
        
        //Validar si el usuario existe.
        response = JSONHandler.getInstance().getDrive(nuevoUsuario);
        if( response.containsKey("Error") ){
            return response;
        }
        
        //Revisar si ya existe el directorio.
        
        response = checkDirectorioUser(usuario, nuevoUsuario);
       
        //Insertar archivo en carpeta de compartidos al usuario que quiere.
       
        CopiarArchivo ca = new CopiarArchivo();
        //Insertar en el path proveído

        String newPath = nuevoUsuario + "/" + "Compartidos" + "/" + usuario ;
        
        response = ca.CopiarArchivo(path, nombre, tipoArchivo, newPath );
        
        if (response.containsKey("Error") == false){
            //Obtener archivo que se quiere compartir y agregar a la lista de compartidos el nuevo usuario.
            JSONObject archivo = JSONHandler.getInstance().getElemento(path, nombre, tipoArchivo);
            ArrayList <String> comp = (ArrayList <String>) archivo.get("Compartidos");       
            //comp.add(nuevoUsuario); 
        }
        return response; 
          
    }
    
    private JSONObject checkDirectorioUser(String usuario, String nuevoUsuario){
        
        JSONObject response = new JSONObject();
        //Revisar si existe ya una carpeta del usuario.
        String newPath = nuevoUsuario + "/" + "Compartidos";
        
        //En caso de que no exista una carpeta, crearla.
        if ( JSONHandler.getInstance().getElemento(newPath, usuario, "carpeta").containsKey("Error") ){
            CrearCarpeta carp = new CrearCarpeta();
            response = carp.crearCarpeta(newPath, usuario);
            response.put("OK", "Se creó la carpeta");
        }          
        return response;
    }    
}
