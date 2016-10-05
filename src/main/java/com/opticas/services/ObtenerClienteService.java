package com.opticas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.opticas.domains.Cliente;
import com.opticas.domains.Contacto;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ObtenerClienteService {
	Logger logger = Logger.getLogger("");

	public Cliente getClient(String id) {
		Cliente cliente = new Cliente();
		String response = "";
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		String url = "http://power01.lux.mx:8810/LUX/rest/LUXService/ProcesaClientes" + "?mcliente=" + id;
		logger.info("url obtenerCliente: " + url);
		int counTel = 0;
		List<Contacto> listaContactos = new ArrayList<Contacto>();
		try {
			response = restTemplate.getForObject(url, String.class);
			// response =
			// "{\"dcli\":{\"t-clientes\":[{\"id_cliente\":\"0001597664\",\"nombre\":\"GISELL\",\"apellido_paterno\":\"ALANIZ\",\"fecha_nac\":\"sexo\",\"\":\"\",\"apellido_materno\":\"ROJAS\",\"calle\":\"CERRO
			// SAN FRANCISCO
			// 26\",\"numero\":\"\",\"cp\":\"04200\",\"colonia\":\"CAMPESTRE
			// CHURUBUSCO\",\"municipio\":\"COYOACAN\",\"estado\":\"DISTRITO
			// FEDERAL\"}],\"t-contacto\":[{\"id_cliente\":\"0001597664\",\"tipo\":\"TEL
			// ADICIONAL\",\"valor\":\"5531844148\"}]}}";
			logger.info("obtenerClienteService respuesta: " + response);
			JsonObject objectResponse = parser.parse(response).getAsJsonObject();
			JsonObject dcliJsonObject = objectResponse.get("dcli").getAsJsonObject();
			JsonArray arrayClientes = dcliJsonObject.get("t-clientes").getAsJsonArray();
			JsonObject jsonCliente = arrayClientes.get(0).getAsJsonObject();
			if (jsonCliente.has("id_cliente")) {
				cliente.setId(jsonCliente.get("id_cliente").getAsString());
			}
			if (jsonCliente.has("nombre")) {
				cliente.setNombre(jsonCliente.get("nombre").getAsString());
			}
			if (jsonCliente.has("apellido_paterno")) {
				cliente.setaPaterno(jsonCliente.get("apellido_paterno").getAsString());
			}
			if (jsonCliente.has("apellido_materno")) {
				cliente.setaMaterno(jsonCliente.get("apellido_materno").getAsString());
			}
			if (jsonCliente.has("fecha_nacimiento")) {
				cliente.setFechaNacimiento(jsonCliente.get("fecha_nacimiento").getAsString());
			}
			if (jsonCliente.has("sexo")) {
				cliente.setSexo(jsonCliente.get("sexo").getAsString());
			}
			if (jsonCliente.has("calle")) {
				cliente.setCalle(jsonCliente.get("calle").getAsString());
			}
			if (jsonCliente.has("cp")) {
				cliente.setCp(jsonCliente.get("cp").getAsString());
			}
			if (jsonCliente.has("colonia")) {
				cliente.setColonia(jsonCliente.get("colonia").getAsString());
			}
			if (jsonCliente.has("municipio")) {
				cliente.setMnpo(jsonCliente.get("municipio").getAsString());
			}
			if (jsonCliente.has("estado")) {
				cliente.setEstado(jsonCliente.get("estado").getAsString());
			}
			if (jsonCliente.has("observaciones")) {
				cliente.setObservaciones(jsonCliente.get("observaciones").getAsString());
			}
			if (dcliJsonObject.has("t-contacto")) {
				JsonArray arrayContactos = dcliJsonObject.get("t-contacto").getAsJsonArray();
				for (int j = 0; j < arrayContactos.size(); j++) {
					Contacto contacto = new Contacto();
					JsonObject jsonOContacto = arrayContactos.get(j).getAsJsonObject();
					if (jsonOContacto.get("valor").getAsString().indexOf("@") != -1) {
						contacto.setTipo("Correo");

					} else {
						counTel++;
						contacto.setTipo("Tel " + counTel);
					}
					contacto.setValor(jsonOContacto.get("valor").getAsString());
					listaContactos.add(contacto);
				}
				cliente.setContacto(listaContactos);
			}
		} catch (Exception ex) {
			logger.info("ObtenerCliente Service error" + ex.getMessage());
			return cliente;
		}
		logger.info("from service response: " + cliente.getId());
		return cliente;
	}
}
