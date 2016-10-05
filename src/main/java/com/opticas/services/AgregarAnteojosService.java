package com.opticas.services;

import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class AgregarAnteojosService {
	Logger logger = Logger.getLogger("");
	
	public String add(String jsonRequest){
		String id_sucursal = "10";
		String id_usuario = "2053";
		String response = "";
		String id_client = "";
		String uso = "";
		String origen = "";
		String antiguedad = "";
		String esfera_derecho = "";
		String esfera_izquierdo = "";
		String cilindro_derecho = "";
		String cilindro_izquierdo = "";
		String eje_derecho = "";
		String eje_izquierdo = "";
		String adicion_derecho = "";
		String adicion_izquierdo = "";
		String av_derecho = "";
		String av_izquierdo = "";
		String observaciones = "";
		
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String url = "http://power01.lux.mx:8810/LUX/rest/LUXService/Anteojos";
        JsonObject requestJsonObject = parser.parse(jsonRequest).getAsJsonObject();
        
        if(requestJsonObject.has("id_client")){
        	id_client = requestJsonObject.get("id_client").getAsString(); 
        }
        if(requestJsonObject.has("uso")){
        	uso = requestJsonObject.get("uso").getAsString(); 
        }
        if(requestJsonObject.has("origen")){
        	origen = requestJsonObject.get("origen").getAsString(); 
        }
        if(requestJsonObject.has("antiguedad")){
        	antiguedad = requestJsonObject.get("antiguedad").getAsString(); 
        }
        if(requestJsonObject.has("esfera_derecho")){
        	esfera_derecho = requestJsonObject.get("esfera_derecho").getAsString(); 
        }
        if(requestJsonObject.has("esfera_izquierdo")){
        	esfera_izquierdo = requestJsonObject.get("esfera_izquierdo").getAsString(); 
        }
        if(requestJsonObject.has("eje_derecho")){
        	eje_derecho = requestJsonObject.get("eje_derecho").getAsString(); 
        }
        if(requestJsonObject.has("eje_izquierdo")){
        	eje_izquierdo = requestJsonObject.get("eje_izquierdo").getAsString(); 
        }
        if(requestJsonObject.has("adicion_derecho")){
        	adicion_derecho = requestJsonObject.get("adicion_derecho").getAsString(); 
        }
        if(requestJsonObject.has("adicion_izquierdo")){
        	adicion_izquierdo = requestJsonObject.get("adicion_izquierdo").getAsString(); 
        }
        if(requestJsonObject.has("observaciones")){
        	observaciones = requestJsonObject.get("observaciones").getAsString(); 
        }
        
        try {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
        	String jsonEntity = "{\"id_usuario\":\"" + id_usuario + "\",\"id_sucursal\":\""+ id_sucursal +
        			"\",\"id_cliente\":\""+ id_client +"\",\"uso\":\"" + uso + "\",\"origen\":\"" + origen +
        			"\",\"antiguedad\":\"" + antiguedad + "\",\"esfera_derecho\":\"" + esfera_derecho +
        			"\",\"esfera_izquierdo\":\"" + esfera_izquierdo + "\",\"cilindro_derecho\":\"" + cilindro_derecho +
        			"\",\"cilindro_izquierdo\":\"" + cilindro_izquierdo + "\",\"eje_derecho\":\"" + eje_derecho +
        			"\",\"eje_izquierdo\":\"" + eje_izquierdo + "\",\"adicion_derecho\":\"" + adicion_derecho + 
        			"\",\"adicion_izquierdo\":\"" + adicion_izquierdo + "\",\"av_derecho\":\"" + av_derecho +
        			"\",\"av_izquierdo\":\"" + av_izquierdo + "\"}";
        	
        	logger.info("json to send agregarAnteojosService:" + jsonEntity);
        	
			HttpEntity<String> entity = new HttpEntity<String>(jsonEntity,headers);
			response = restTemplate.postForObject(url, entity, String.class);
			logger.info("response of remote service" + response);
			
        }catch (Exception e) {
			logger.warning("error agregarCliente service: " + e.getMessage());
			return "{\"salida\":[{\"estatus\":\"0\",\"mensaje\",\"error en el servidor\"}]}";
		}
        
		return response;
	}

}
