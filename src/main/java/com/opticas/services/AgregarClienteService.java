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
import java.util.logging.Logger;

@Service
public class AgregarClienteService {
	Logger logger = Logger.getLogger("");
	
	public String add(String requestString){
		String response= "";
		String nombre = "";
		String apellido_paterno = "";
		String apellido_materno = "";
		String fecha_nacimiento = "";
		String sexo = "";
		String calle = "";
		String cp = "";
		String colonia = "";
		String mnpo = "";
		String estado = "";
		String obs = "";
		String contactoCorreo = "";
		String contactoCellular = "";
		String contactoTelefono = "";
		String contactoCorreoCompania = "";
		
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String url = "http://power01.lux.mx:8810/LUX/rest/LUXService/agregarCliente";
        JsonObject requestJsonObject = parser.parse(requestString).getAsJsonObject();
        if(requestJsonObject.has("nombre")){
        	nombre = requestJsonObject.get("nombre").getAsString(); 
        }
        if(requestJsonObject.has("apellido_paterno")){
        	apellido_paterno = requestJsonObject.get("apellido_paterno").getAsString(); 
        }
        if(requestJsonObject.has("apellido_materno")){
        	apellido_materno = requestJsonObject.get("apellido_materno").getAsString(); 
        }
        if(requestJsonObject.has("fecha_nacimiento")){
        	fecha_nacimiento = requestJsonObject.get("fecha_nacimiento").getAsString(); 
        }
        if(requestJsonObject.has("sexo")){
        	sexo = requestJsonObject.get("sexo").getAsString(); 
        }
        if(requestJsonObject.has("calle")){
        	calle = requestJsonObject.get("calle").getAsString(); 
        }
        if(requestJsonObject.has("cp")){
        	cp = requestJsonObject.get("cp").getAsString(); 
        }
        if(requestJsonObject.has("colonia")){
        	colonia = requestJsonObject.get("colonia").getAsString(); 
        }
        if(requestJsonObject.has("mnpo")){
        	mnpo = requestJsonObject.get("mnpo").getAsString(); 
        }
        if(requestJsonObject.has("estado")){
        	estado = requestJsonObject.get("estado").getAsString(); 
        }
        if(requestJsonObject.has("cp")){
        	cp = requestJsonObject.get("cp").getAsString(); 
        }
        if(requestJsonObject.has("obs")){
        	obs = requestJsonObject.get("obs").getAsString(); 
        }
        if(requestJsonObject.has("contactoCorreo")){
        	contactoCorreo = requestJsonObject.get("contactoCorreo").getAsString(); 
        }
        if(requestJsonObject.has("contactoCellular")){
        	contactoCellular = requestJsonObject.get("contactoCellular").getAsString(); 
        }
        if(requestJsonObject.has("contactoTelefono")){
        	contactoTelefono = requestJsonObject.get("contactoTelefono").getAsString(); 
        }
        if(requestJsonObject.has("contactoCorreoCompania")){
        	contactoCorreoCompania = requestJsonObject.get("contactoCorreoCompania").getAsString(); 
        }
        
        try {
        	HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
        	String jsonEntity = "{\"t-clientes\":[{\"id_sucursal\":\"" + 10 + "\",\"id_usuario\":\"" + 2053 + "\"," +
        	        "\"nombre\":\""+ nombre +"\",\"apellido_paterno\":\""+ apellido_paterno +"\",\"apellido_materno\":\"" +apellido_materno + "\","+
        	        		"\"fecha_nac\":\"" + fecha_nacimiento + "\",\"sexo\":\"" + sexo + "\",\"calle\":\""+ calle + "\"," +
        	        "\"cp\":\"" + cp +"\",\"colonia\":\"" + colonia + "\",\"municipio\":\"" + mnpo  + "\",\"estado\":\""+ estado + "\","+
        	        "\"observaciones\":\"" + obs + "\",\"t-contacto\":[{\"id_cliente\":\"\",\"tipo\":\"CORREO\",\"valor\":\""+ contactoCorreo + "@" + contactoCorreoCompania +"\"},{"+
        	        "\"id_cliente\":\"\",\"tipo\":\"TELEFONO\",\"valor\":\"" + contactoTelefono + "\"},{"+
        	        "\"id_cliente\":\"\",\"tipo\":\"CELULAR\",\"valor\":\"" + contactoCellular + "\"}]}]}";
        	logger.info("json to send agregarClienteService:" + jsonEntity);
			HttpEntity<String> entity = new HttpEntity<String>(jsonEntity,headers);
			response = restTemplate.postForObject(url, entity, String.class);
			logger.info("response of remote service" + response);
        }catch (Exception e) {
			logger.warning("error agregarCliente service: " + e.getMessage());
			return "{'estatus':'0','mensaje':'error en el servidor'}";
		}
        
        
		return response;
	}

}
