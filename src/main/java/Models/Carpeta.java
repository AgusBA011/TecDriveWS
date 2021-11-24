/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;

/**
 *
 * @author agust
 */
public class Carpeta {
   
    String nombre;
    String tipo;
    String  creacion;
    String  modificacion;
    int tamanno;
    ArrayList <JSONObject> contenido;
    ArrayList <String> compartido;
    
    static int fileSize = 100;

    public Carpeta() { //Contructor por JSON
    }

    public Carpeta(String nombre) {
        
        setNombre(nombre);
        
        setTipo("carpeta");
        
        setTamanno(fileSize);
        
        setCreacion( getNowDate());
        
        setModificacion( getNowDate());
        
        setCompartido( new ArrayList <String>());
        
        setContenido (new JSONArray());
        
    }

    public ArrayList<JSONObject> getContenido() {
        return contenido;
    }

    public void setContenido(ArrayList<JSONObject> contenido) {
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

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
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
        
        json.put("creacion",this.creacion);  //Fecha de creación
        
        json.put("modificacion",this.modificacion);  //modificacion
        
        json.put("tamano",this.tamanno);  //Tamaño
        
        json.put("contenido",this.contenido);
        
        json.put("compartido",this.compartido);  //Compartido
        
        return json;
    }
    
    public String getNowDate(){
    
        LocalDateTime now = LocalDateTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        return now.format(formatter);
    
    }
}
