package com.opticas.services;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.opticas.domains.Cliente;
import com.opticas.domains.Contacto;
import com.opticas.domains.Examen;
import com.opticas.utils.URL;

@Service
public class BuscaClientesService {

	Logger logger = Logger.getLogger("");

	public List<Cliente> busca(String jsonRequest) {
		URL url_p = new URL();

		logger.info("buscaClientesService ...getting jsonRequest: " + jsonRequest);
		List<Cliente> listClient = new ArrayList<Cliente>();
		int counTel = 0;
		Cliente cliente;
		Examen objectExam;
		String response = "";
		String fecha = "", ap_paterno = "", nombre = "";
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		String url = url_p.getValue("url.busqueda.clientes");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JsonObject requestJsonObject = parser.parse(jsonRequest).getAsJsonObject();
		if (requestJsonObject.has("fechaNacimiento")) {
			fecha = requestJsonObject.get("fechaNacimiento").getAsString();
		}
		if (requestJsonObject.has("aPaterno")) {
			ap_paterno = requestJsonObject.get("aPaterno").getAsString();
		}
		if (requestJsonObject.has("nombre")) {
			nombre = requestJsonObject.get("nombre").getAsString();
		}
		logger.info("json send to buscaClientes: {\"ttEntrada\":[{\"id_suc\": \"12001\",\"fecha\":\"" + fecha + "\",\"ap_paterno\":\""
				+ ap_paterno + "\",\"nombre\":\"" + nombre + "\"}]}");
		try {
			HttpEntity<String> entity = new HttpEntity<String>("{\"ttEntrada\":[{\"fecha\":\"" + fecha

					+ "\",\"ap_paterno\":\"" + ap_paterno + "\",\"nombre\":\"" + nombre + "\"}]}", headers);

			response = restTemplate.postForObject(url, entity, String.class);

			logger.info("buscaClientesService response rest: " + response);

			JsonObject objectResponse = parser.parse(response).getAsJsonObject();
			JsonObject objectdcli = objectResponse.get("dcli").getAsJsonObject();
			if (objectdcli.has("t-clientes")) {
				JsonArray arrayClientes = objectdcli.get("t-clientes").getAsJsonArray();
				JsonArray arrayContacto = objectdcli.get("t-contacto").getAsJsonArray();
				JsonArray arrayExamen = objectdcli.get("examen").getAsJsonArray();
				for (int i = 0; i < arrayClientes.size(); i++) {
					counTel = 0;

					List<Contacto> listContacto = new ArrayList<Contacto>();

					cliente = new Cliente();
					// jsonobject client
					JsonObject jsonO = arrayClientes.get(i).getAsJsonObject();
					if (jsonO.has("id_cliente")) {
						cliente.setId(jsonO.get("id_cliente").getAsString());
					}
					if (jsonO.has("sucursal")) {
						cliente.setSucursal(jsonO.get("sucursal").getAsInt());
					}
					if (jsonO.has("nombre")) {
						cliente.setNombre(jsonO.get("nombre").getAsString());
					}
					if (jsonO.has("apellido_paterno")) {
						cliente.setaPaterno(jsonO.get("apellido_paterno").getAsString());
					}
					if (jsonO.has("apellido_materno")) {
						cliente.setaMaterno(jsonO.get("apellido_materno").getAsString());
					}
					if (jsonO.has("calle")) {
						cliente.setCalle(jsonO.get("calle").getAsString());
					}
					if (jsonO.has("numero")) {
						cliente.setNumero(jsonO.get("numero").getAsString());
					}
					if (jsonO.has("cp")) {
						cliente.setCp(jsonO.get("cp").getAsString());
					}
					if (jsonO.has("colonia")) {
						cliente.setColonia(jsonO.get("colonia").getAsString());
					}
					if (jsonO.has("municipio")) {
						cliente.setMnpo(jsonO.get("municipio").getAsString());
					}
					if (jsonO.has("estado")) {
						cliente.setEstado(jsonO.get("estado").getAsString());
					}
					for (int j = 0; j < arrayContacto.size(); j++) {

						Contacto contacto = new Contacto();
						JsonObject jsonOContacto = arrayContacto.get(j).getAsJsonObject();
						if (jsonOContacto.get("id_cliente").getAsString()
								.equals(jsonO.get("id_cliente").getAsString())) {
							if (jsonOContacto.get("valor").getAsString().indexOf("@") != -1) {
								contacto.setTipo("Correo");

							} else {
								counTel++;
								contacto.setTipo("Tel " + counTel);
							}
							contacto.setValor(jsonOContacto.get("valor").getAsString());
							listContacto.add(contacto);
						}
					}
					for (int j = 0; j < arrayExamen.size(); j++) {
						objectExam = new Examen();
						JsonObject objectResponseExam = arrayExamen.get(j).getAsJsonObject();
						if (objectResponseExam.get("id_cliente").getAsString()
								.equals(jsonO.get("id_cliente").getAsString())) {
							if (objectResponseExam.has("esfera_derecho")) {
								objectExam.setEsfera_derecho(objectResponseExam.get("esfera_derecho").getAsString());
							}
							if (objectResponseExam.has("esfera_izquierdo")) {
								objectExam
										.setEsfera_izquierdo(objectResponseExam.get("esfera_izquierdo").getAsString());
							}
							if (objectResponseExam.has("cilindro_derecho")) {
								objectExam
										.setCilindro_derecho(objectResponseExam.get("cilindro_derecho").getAsString());
							}
							if (objectResponseExam.has("cilindro_izquierdo")) {
								objectExam.setCilindro_izquierdo(
										objectResponseExam.get("cilindro_izquierdo").getAsString());
							}
							if (objectResponseExam.has("eje_derecho")) {
								objectExam.setEje_derecho(objectResponseExam.get("eje_derecho").getAsString());
							}
							if (objectResponseExam.has("eje_izquierdo")) {
								objectExam.setEje_izquierdo(objectResponseExam.get("eje_izquierdo").getAsString());
							}
							if (objectResponseExam.has("ad_derecho")) {
								objectExam.setAv_derecho(objectResponseExam.get("ad_derecho").getAsString());
							}
							if (objectResponseExam.has("ad_izquierdo")) {
								objectExam.setAv_izquierdo(objectResponseExam.get("ad_izquierdo").getAsString());
							}
							if (objectResponseExam.has("fecha")) {

								objectExam.setFecha(objectResponseExam.get("fecha").getAsString());
							}
							if (objectResponseExam.has("optometrista")) {
								objectExam.setOptometrista(objectResponseExam.get("optometrista").getAsString());
							}
							if (objectResponseExam.has("observaciones")) {
								objectExam.setObservaciones(objectResponseExam.get("observaciones").getAsString());
							}
						}
						cliente.setUltimoExamen(objectExam);
					}

					
					cliente.setContacto(listContacto);
					listClient.add(cliente);
				}
			}
		} catch (Exception ex) {

			logger.warning("buscarClientesService error" + ex.getMessage());
			return listClient;

		}
		return listClient;
	}
}
