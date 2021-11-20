/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.json;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONHandler {

    public JSONHandler() {
               
    }
    
    public JSONObject readJSON(){
        
        
        String resourceName = "C:\\Users\\agust\\OneDrive\\Documents\\NetBeansProjects\\TecDriveWS\\TecDriveWS\\src\\main\\java\\com\\webservice\\json\\TecDrive.json";
        
        InputStream is;
        try {
            is = new FileInputStream(resourceName);
        
            if (is == null) {
               throw new NullPointerException("Cannot find resource file " + resourceName);
           }
           JSONTokener tokener = new JSONTokener(is);
           JSONObject object = new JSONObject(tokener);

           return object;
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(JSONHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
