package com.opticas.controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opticas.domains.Antecedente;
import com.opticas.domains.Anteojo;
import com.opticas.domains.Cliente;
import com.opticas.domains.Receta;
import com.opticas.services.AgregarAnteojosService;
import com.opticas.services.AgregarClienteService;
import com.opticas.services.AgregarRecetaService;
import com.opticas.services.BuscaClientesService;
import com.opticas.services.ObtenerAnteojosService;
import com.opticas.services.ObtenerClienteService;
import com.opticas.services.ObtenerRecetasService;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

@Controller
public class MainController {
	Logger logger = Logger.getLogger("");
	
	@Autowired 
    BuscaClientesService buscaClientesService;
	
	@Autowired 
	ObtenerClienteService obtenerClienteService;
	
	@Autowired 
    ObtenerAnteojosService obtenerAnteojosService;
	
	@Autowired 
    AgregarClienteService agregarClienteService;
	
	@Autowired 
    ObtenerRecetasService obtenerRecetasService;
	
	@Autowired 
    AgregarRecetaService agregarRecetaService;
	
	@Autowired 
    AgregarAnteojosService agregarAnteojosService;
	
	@RequestMapping("/agregarCliente")
    public String clientes(){
        return "agregarCliente";
    }
	
	@RequestMapping(value="/modificaCliente",method = RequestMethod.GET)
    public String modificaCliente(@RequestParam("cli") String id, Model model){
		logger.info ("MainController modoficaCliente idCLiente: " + id);
		Cliente client = new Cliente(); 
		client = obtenerClienteService.getClient(id);
		Gson gson = new Gson();
		model.addAttribute("clienteJson", gson.toJson(client));
		model.addAttribute("cliente", client);
        return "modificarCliente";
    }
	
	
	@RequestMapping("/agregarAnteojo")
    public String agregarAnteojo(){
        return "agregarAnteojo";
    }
	
	@RequestMapping("/ventas")
    public String ventas() {
        return "ventas";
    }
	
	@RequestMapping(value="/anteojosIniciales", method = RequestMethod.GET)
    public String anteojosIniciales(@RequestParam("cli") String id, Model model) {
		logger.info ("MainController anteojosIniciales idCLiente: " + id);
		Gson gson = new Gson();
        List<Anteojo> listAnteojo = obtenerAnteojosService.getAnteojos(id);
        model.addAttribute("idCliente_anteojos",id);
        model.addAttribute("anteojos", listAnteojo);
        model.addAttribute("anteojosJson", gson.toJson(listAnteojo));
        logger.info ("MainController anteojosIniciales sizeResponse: " + listAnteojo.size());
        return "anteojosIniciales";
    }
	
	@RequestMapping(value="/antecedentes",method = RequestMethod.GET)
    public String antecedentes(@RequestParam("cli") String id, Model model){
		logger.info ("MainController antecedentes idCLiente: " + id);
		Gson gson = new Gson();
        //List<Antecedente> listAntecedentes = obtenerAntecedentesService.getAnteojos(id);
        //model.addAttribute("antecedentes", listAntecedentes);
        //model.addAttribute("antecedentes", gson.toJson(listAntecedentes));
        //logger.info ("MainController antecedentes sizeResponse: " + listAntecedentes.size());
        
        return "antecedentes";
    }
	
	@RequestMapping(value = "/buscarClientes", consumes="application/json", method = RequestMethod.POST)
    public @ResponseBody String buscarClientes(@RequestBody String jsonRequest) {
        String jsonList = "";
        List<Cliente> listaClientes = buscaClientesService.busca(jsonRequest);
        Gson gson = new Gson();
        jsonList = gson.toJson(listaClientes);
        if (listaClientes.size() == 0){
            return "[{\"mensaje\":\"Sin resultados\"}]";
        }else{
        	logger.info ("MainController buscarClientes response: " + jsonList);
            return jsonList;
        }
    }
	
	@RequestMapping(value = "/agregarCliente", consumes="application/json", method = RequestMethod.POST)
    public @ResponseBody String agregarCliente(@RequestBody String jsonRequest) {
        String jsonList = "";
        String response = agregarClienteService.add(jsonRequest);
        //return "{\"salida\":[{\"estatus\":\"1\",\"mensaje\":\"Operación exitosa\",\"id_cliente\":\"0102911313\"}]}";
        return response;
    }
	
	@RequestMapping(value = "/agregarRx", consumes="application/json", method = RequestMethod.POST)
    public @ResponseBody String agregarRx(@RequestBody String jsonRequest) {
		logger.info("agregarRx controller jsonRequest:" + jsonRequest);
        String jsonList = "";
        String response = agregarRecetaService.add(jsonRequest);
        return response;
    }
	
	@RequestMapping(value="/obtenerRecetas", method = RequestMethod.GET)
    public @ResponseBody String recetas(@RequestParam("cli") String id) {
		logger.info ("MainController obtenerRecteas idCLiente: " + id);
		Gson gson = new Gson();
        List<Receta> listrecetas = obtenerRecetasService.getRecetas(id);
        logger.info ("MainController recetas sizeResponse: " + listrecetas.size());
        return gson.toJson(listrecetas);
    }
	
	@RequestMapping(value = "/agregarAnteojos", consumes="application/json", method = RequestMethod.POST)
    public @ResponseBody String agregarAnteojos(@RequestBody String jsonRequest) {
		logger.info ("MainController agregarAnteojos jsonRequest: " + jsonRequest);
        String response = agregarAnteojosService.add(jsonRequest);
        return response;
    }
}
