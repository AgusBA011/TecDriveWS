/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author agust
 */
public class Archivo {
   
    String nombre;
    String tipo;
    String  creacion;
    String extension;
    String  modificacion;
    long tamano;
    String contenido;
    ArrayList <String> compartido;

    public Archivo() { //Contructor por JSON
    }

    public Archivo(String nombre, String extension, String contenido) {
        
        setNombre(nombre);
        
        setTipo("archivo");
             
        setCreacion( getNowDate());
        
        setModificacion(this.creacion);
        
        ArrayList <String> empty = new ArrayList <String>();
        
        setCompartido( empty );
       
        setContenido (contenido);
        
        setTamano(this.contenido);
        
        setExtension(extension);
        
    }
    
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }

    public String getModificacion() {
        return modificacion;
    }

    public void setModificacion(String modificacion) {
        this.modificacion = modificacion;
    }

    public long getTamano() {
        return tamano;
    }

    public void setTamano(String texto){
        
        this.tamano = getStringSizeFile(texto);  
   
    }
    
    public long getStringSizeFile(String str){

        try {       
            File tempFile = new File("temp.txt");           
            FileWriter writer = new FileWriter("temp");
            writer.write(str);                 
            writer.close();           
            Path path = Paths.get("temp");
            long size = Files.size(path);
            tempFile.deleteOnExit();            
            return size;            
        }
        catch(Exception e){
        
            return 0;
        }   
    }
    
    public String getNowDate(){
    
        LocalDateTime now = LocalDateTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        return now.format(formatter);
    
    }

    public ArrayList<String> getCompartido() {
        return compartido;
    }

    public void setCompartido(ArrayList<String> compartido) {
        this.compartido = compartido;
    }
    
    public JSONObject generateJSON(){
    
        JSONObject json = new JSONObject();
        
        json.put("nombre",this.nombre);  //Nombre
        
        json.put("tipo",this.tipo);  //Tipo
        
        json.put("extension", this.extension);
        
        json.put("creacion",this.creacion);  //Fecha de creación
        
        json.put("modificacion",this.modificacion);  //modificacion
        
        json.put("tamano",this.tamano);  //Tamaño
        
        json.put("compartido",this.compartido);  //Compartido
        
        json.put("contenido",this.contenido);
        
        return json;
    }
}
