/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.tecdrivews.resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Admin
 */
public class JsonHandler {
    
    private final JSONParser parser = new JSONParser();
    JSONObject prueba;
    
    public JsonHandler() throws FileNotFoundException, IOException, ParseException{
        try {
			Object obj = parser.parse(new FileReader("D:/User/Escritorio/prueba.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			prueba = (JSONObject) obj;
        }
        catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public JSONObject patata(){
    return prueba;
    }
    
    
}
