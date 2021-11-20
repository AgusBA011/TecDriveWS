/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author agust
 */
public class Archivo {
   
    String nombre;
    String tipo;
    String extension;
    Date creacion;
    Date modificacion;
    int tamanno;
    String contenido;
    String[] compartido;
    
    static int fileSize = 100;

    public Archivo() { //Contructor por JSON
    }

    public Archivo(String nombre, String extension, String contenido) {
        
        setNombre(nombre);
        
        setExtension(extension);
        
        setContenido(contenido);
        
        setTipo("Archivo");
        
        setTamanno(fileSize);
       
        setCreacion( new Date());
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Date getModificacion() {
        return modificacion;
    }

    public void setModificacion(Date modificacion) {
        this.modificacion = modificacion;
    }

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String[] getCompartido() {
        return compartido;
    }

    public void setCompartido(String[] compartido) {
        this.compartido = compartido;
    }
    
    
    
}
