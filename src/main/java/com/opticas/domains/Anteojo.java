package com.opticas.domains;

import java.util.List;

public class Anteojo {
	String id_cliente;
    String id_anteojo;
    String uso;
    String origen;
    String antiguedad;
    List<Receta> recetas;
    
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getId_anteojo() {
		return id_anteojo;
	}
	public void setId_anteojo(String id_anteojo) {
		this.id_anteojo = id_anteojo;
	}
	public String getUso() {
		return uso;
	}
	public void setUso(String uso) {
		this.uso = uso;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}
	public List<Receta> getRecetas() {
		return recetas;
	}
	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}
    
    
    
	
}
