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
import com.opticas.utils.GetCurrentDate;

@Service
public class AgregarRecetaService {
	Logger logger = Logger.getLogger("");
	
	public String add(String requestString){
		String response = "";
		String id_sucursal = "10";
		String id_usuario = "2053";
		String fecha = "";
		String id_cliente= "";
		String uso = "";
		String esfera_derecho = "";
		String esfera_izquierdo = "";
		String cilindro_derecho = "";
		String cilindro_izquierdo = "";
		String eje_derecho = "";
		String eje_izquierdo ="";
		String adicion_derecho = "";
		String adicion_izquierdo = "";
		String d_i_bonicular = "";
		String observaciones = "";
		String alt_seg = "";
		String m_d_izquierdo = "";
		String m_d_derecho = "";
		GetCurrentDate gcd = new GetCurrentDate();
		fecha = gcd.getDate();
		
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String url = "http://power01.lux.mx:8810/LUX/rest/LUXService/Recetas";
        JsonObject requestJsonObject = parser.parse(requestString).getAsJsonObject();
        
        if(requestJsonObject.has("id_cliente")){
        	id_cliente = requestJsonObject.get("id_cliente").getAsString(); 
        }
        if(requestJsonObject.has("uso")){
        	uso = requestJsonObject.get("uso").getAsString(); 
        }
        if(requestJsonObject.has("esfera_derecho")){
        	esfera_derecho = requestJsonObject.get("esfera_derecho").getAsString(); 
        }
        if(requestJsonObject.has("esfera_izquierdo")){
        	esfera_izquierdo = requestJsonObject.get("esfera_izquierdo").getAsString(); 
        }
        if(requestJsonObject.has("cilindro_derecho")){
        	cilindro_derecho = requestJsonObject.get("cilindro_derecho").getAsString(); 
        }
        if(requestJsonObject.has("cilindro_izquierdo")){
        	cilindro_izquierdo = requestJsonObject.get("cilindro_izquierdo").getAsString(); 
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
        	adicion_derecho = requestJsonObject.get("adicion_izquierdo").getAsString(); 
        }
        if(requestJsonObject.has("m_d_izquierdo")){
        	m_d_izquierdo = requestJsonObject.get("m_d_izquierdo").getAsString(); 
        }
        if(requestJsonObject.has("m_d_derecho")){
        	m_d_derecho = requestJsonObject.get("m_d_derecho").getAsString(); 
        }
        if(requestJsonObject.has("d_i_bonicular")){
        	d_i_bonicular = requestJsonObject.get("d_i_bonicular").getAsString(); 
        }
        if(requestJsonObject.has("observaciones")){
        	observaciones = requestJsonObject.get("observaciones").getAsString(); 
        }
        if(requestJsonObject.has("alt_seg")){
        	alt_seg = requestJsonObject.get("alt_seg").getAsString(); 
        }
        
        
        try {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        	String jsonEntity = "{\"id_sucursal\":\"" + id_sucursal + "\",\"id_usuario\":\"" + id_usuario + 
        	"\",\"id_cliente\":\"" + id_cliente  + "\",\"fecha\":\""+ fecha + 
        	" \",\"uso\":\"" + uso + "\",\"esfera_derecho\":\"" + esfera_derecho +
        	"\",\"esfera_izquierdo\":\"" + esfera_izquierdo + "\",\"cilindro_izquierdo\":\"" + cilindro_izquierdo +
        	"\",\"cilindro_derecho\":\"" + cilindro_derecho + "\",\"eje_derecho\":\"" + eje_derecho + "\",\"eje_izquierdo\":\"" + eje_izquierdo +
        	"\",\"adicion_derecho\":\"" + adicion_derecho + "\",\"adicion_izquierdo\":\"" + adicion_izquierdo +
        	"\",\"d_i_binocular\":\"" + d_i_bonicular + "\",\"alt_seg\":\""+ alt_seg +"\", \"observaciones\": \""+ observaciones + "\"}";
        	
        	logger.info("json to send agregarRecetaService:" + jsonEntity);
			HttpEntity<String> entity = new HttpEntity<String>(jsonEntity,headers);
			response = restTemplate.postForObject(url, entity, String.class);
			logger.info("response of remote service" + response);
        }catch (Exception e) {
			logger.warning("error agregarReceta service: " + e.getMessage());
			return "{'estatus':'0','mensaje':'error en el servidor'}";
		}
		return response;
	}

}
