package com.opticas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opticas.domains.Anteojo;
import com.opticas.domains.Receta;


@Service
public class ObtenerRecetasService {
	Logger logger = Logger.getLogger("");

	public List<Receta> getRecetas(String idCliente) {
		List<Receta> listRecetas = new ArrayList<>();
		String response = "";
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		// http://power01.lux.mx:8810/LUX/rest/LUXService/Recetas?mcliente=0000200003
		String url = "http://power01.lux.mx:8810/LUX/rest/LUXService/Recetas" + "?mcliente=" +idCliente;
		logger.info("obtenerRecetasService url obtenerRecetas: " + url);
		try {
			 response = restTemplate.getForObject(url, String.class);
			 //response = "{\"recetas\":[{\"sucursal\":\"2\",\"optometrista\":\"1480\",\"fecha\":\"08/02/04\",\"uso\":\"l\",\"esfera_od\":\"-2.00\",\"esfera_oi\":\"-2.25\",\"cilindro_od\":\"\",\"cilindro_oi\":\"\",\"eje_od\":\"\",\"eje_oi\":\"\",\"adicion_od\":\"\",\"adicion_oi\":\"\",\"d_i_binocular\":\"\",\"alt_seg\":\"\",\"observaciones\":\"\"}]}";
			logger.info("obtenerRecetasService response: " + response);
			JsonObject objectResponse = parser.parse(response).getAsJsonObject();
			JsonArray arrayRecetas = objectResponse.get("recetas").getAsJsonArray();

			for (int i = 0; i < arrayRecetas.size(); i++) {
				Receta receta = new Receta();
				JsonObject jsonO = arrayRecetas.get(i).getAsJsonObject();
				if(jsonO.has("d_i_binocular")){
					receta.setD_i_binocular(jsonO.get("d_i_binocular").getAsString());
				}
				if(jsonO.has("alt_seg")){
					receta.setAlt_seg(jsonO.get("alt_seg").getAsString());
				}
				if(jsonO.has("fecha")){
					receta.setFecha(jsonO.get("fecha").getAsString());
				}
				if(jsonO.has("observaciones")){
					receta.setObservaciones(jsonO.get("observaciones").getAsString());
				}
				if(jsonO.has("sucursal")){
					receta.setSucursal(jsonO.get("sucursal").getAsString());
				}
				if(jsonO.has("optometrista")){
					receta.setId_optometrista(jsonO.get("optometrista").getAsString());
				}
				if(jsonO.has("esfera_od")){
					receta.setEsfera_derecho(jsonO.get("esfera_od").getAsString());
				}
				if(jsonO.has("esfera_oi")){
					receta.setEsfera_izquierdo(jsonO.get("esfera_oi").getAsString());
				}
				if(jsonO.has("cilindro_od")){
					receta.setCilindro_derecho(jsonO.get("cilindro_od").getAsString());
				}
				if(jsonO.has("cilindro_oi")){
					receta.setCilindro_izquierdo(jsonO.get("cilindro_oi").getAsString());
				}
				if(jsonO.has("eje_od")){
					receta.setEje_derecho(jsonO.get("eje_od").getAsString());
				}
				
				if(jsonO.has("eje_oi")){
					receta.setEje_izquierdo(jsonO.get("eje_oi").getAsString());
				}
				if(jsonO.has("adicion_od")){
					receta.setAdicion_derecho(jsonO.get("adicion_od").getAsString());
				}
				if(jsonO.has("adicion_oi")){
					receta.setAdicion_izquierdo(jsonO.get("adicion_oi").getAsString());
				}
				listRecetas.add(receta);
			}
		} catch (Exception e) {
			logger.info ("ObtenerRecetasService error: " + e.getMessage());
	        return listRecetas;
		}

		return listRecetas;
	}

}
