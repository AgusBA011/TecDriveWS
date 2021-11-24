/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import org.json.simple.JSONObject;

/**
 *
 * @author agust
 */
public class modelArchivo {
    
    String contenido;
    
    String extension;
    
    String path;
    
    String nombre;
    
    public modelArchivo() {
    }
    

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    public JSONObject toJSON(){
    
        JSONObject json = new JSONObject();
        
        json.put("path",this.path);
        
        json.put("nombre",this.nombre);
        
        json.put("extension",this.extension);
        
        json.put("contenido",this.contenido);
        
        return json;
    
    
    }
    
    
    
    
    
}
