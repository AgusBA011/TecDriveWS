package com.webservice.tecdrivews.resources;

import com.webservice.json.JSONHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author agust
 */
@Path("Mover/")
public class Mover {
    
    @GET
    public Response MoverEndpoint(@QueryParam("path") String path, @QueryParam("nombre") String nombre, 
                                        @QueryParam("tipoArchivo") String tipoArchivo, @QueryParam("nuevoPath") String nuevoPath)
    {   
        
        return Response
                .ok( Mover(path, nombre ,  tipoArchivo, nuevoPath )  ).header("Access-Control-Allow-Origin", "*")
                .build();   
    }
   
    private JSONObject Mover( String path, String nombre, String tipoArchivo, String nuevoPath ){
    
        //Se obtiene el file que se quiere copiar
            JSONObject fileCopiado;        
            if ( tipoArchivo.equals("carpeta") ){ //En caso de insertar una carpeta            
                fileCopiado = JSONHandler.getInstance().getDirectorio(path, nombre );
            }
            else{
                fileCopiado = JSONHandler.getInstance().getArchivo(path, nombre, tipoArchivo );
            }
            //En caso de no encontrarlo.
            if ( fileCopiado.containsKey("Error")) {
                return fileCopiado;
            }        
        //Se copia el file con éxito
            JSONObject response = JSONHandler.getInstance().insertContenido(nuevoPath, fileCopiado);
            if ( response.containsKey("Error")) {
                return response;
            }
        //Se elimina el pasado
            response = JSONHandler.getInstance().deleteElement(path, nombre, tipoArchivo);
            if ( response.containsKey("Error") ) {
                return response;
            }
        //Mensaje de que todo salió bien  
            JSONObject responseSucces = new JSONObject();
            responseSucces.put("OK","Se movió el elemento con éxito");
            
            return responseSucces;
            
        
    }
}
