package com.opticas.domains;

import java.util.List;

public class Cliente {
	String id;
    int sucursal;
    String nombre;
    String fechaNacimiento;
    String sexo;
    String aPaterno;
    String aMaterno;
    List<Contacto> contacto;
    String calle;
    String numero;
    String cp;
    String colonia;
    String mnpo;
    String estado;
    String observaciones;
    Examen ultimoExamen;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public List<Contacto> getContacto() {
		return contacto;
	}
	public void setContacto(List<Contacto> contacto) {
		this.contacto = contacto;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getMnpo() {
		return mnpo;
	}
	public void setMnpo(String mnpo) {
		this.mnpo = mnpo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Examen getUltimoExamen() {
		return ultimoExamen;
	}
	public void setUltimoExamen(Examen ultimoExamen) {
		this.ultimoExamen = ultimoExamen;
	}
    
    
}
