package com.opticas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.opticas.domains.Anteojo;
import com.opticas.domains.Receta;

@Service
public class ObtenerAnteojosService {
	Logger logger = Logger.getLogger("");
	
	
	public List<Anteojo> getAnteojos(String id){
	    List<Anteojo> listAnteojo = new ArrayList<>();
	    List<Receta> listReceta;
	        String response = "";
	        RestTemplate restTemplate = new RestTemplate();
	        Gson gson = new Gson();
	        JsonParser parser = new JsonParser();
	        String url = "http://power01.lux.mx:8810/LUX/rest/LUXService/Anteojos" + "?mcliente=" + id;
	        logger.info ("obtenerAnteojoService url obtenerAnteojos: " + url);
	        
	        try {
	            //response = restTemplate.getForObject(url, String.class);
	            response = "{\"tt\":[{\"id_cliente\":\"0001597664\",\"id_anteojo\":\"\",\"uso\":\"\",\"origen\":\"OTRA\",\"antiguedad\":\"3 aÃ±os 0 meses\",\"esfera_derecho\":\"-1.50\",\"esfera_izquierdo\":\"-1.75\",\"cilindro_derecho\":\"\",\"cilindro_izquierdo\":\"\",\"eje_derecho\":\"\",\"eje_izquierdo\":\"\",\"adicion_derecho\":\"\",\"adicion_izquierdo\":\"\",\"av_derecho\":\"20\",\"av_izquierdo\":\"20\"}]}";
	            logger.info ("obtenerAnteojoService response: " + response);
	            
	            JsonObject objectResponse = parser.parse(response).getAsJsonObject();
	            JsonArray arrayAntejos = objectResponse.get("tt").getAsJsonArray();
	            for(int i = 0; i < arrayAntejos.size(); i++){
	                Anteojo anteojo = new Anteojo();
	                listReceta = new ArrayList<>();
	                Receta receta = new Receta();
	                JsonObject jsonO = arrayAntejos.get(i).getAsJsonObject();
	                anteojo.setId_cliente(jsonO.get("id_cliente").getAsString());
	                anteojo.setId_anteojo(jsonO.get("id_anteojo").getAsString());
	                anteojo.setUso(jsonO.get("uso").getAsString());
	                anteojo.setOrigen(jsonO.get("origen").getAsString());
	                anteojo.setAntiguedad(jsonO.get("antiguedad").getAsString());
	                receta.setEsfera_derecho(jsonO.get("esfera_derecho").getAsString());
	                receta.setEsfera_izquierdo(jsonO.get("esfera_izquierdo").getAsString());
	                receta.setCilindro_derecho(jsonO.get("cilindro_derecho").getAsString());
	                receta.setCilindro_izquierdo(jsonO.get("cilindro_izquierdo").getAsString());
	                receta.setEje_derecho(jsonO.get("eje_derecho").getAsString());
	                receta.setEje_izquierdo(jsonO.get("eje_izquierdo").getAsString());
	                receta.setAdicion_derecho(jsonO.get("adicion_derecho").getAsString());
	                receta.setAdicion_izquierdo(jsonO.get("adicion_izquierdo").getAsString());
	                receta.setAv_derecho(jsonO.get("av_derecho").getAsString());
	                receta.setAv_izquierdo(jsonO.get("av_izquierdo").getAsString());
	                listReceta.add(receta);
	                anteojo.setRecetas(listReceta);
	                listAnteojo.add(anteojo);
	            }
	        }catch(Exception ex){
	        	logger.info ("ObtenerAnteojos Service error: " + ex.getMessage());
		        return listAnteojo;
	        }
	        return listAnteojo;
	    }
}
