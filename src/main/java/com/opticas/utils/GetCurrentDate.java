package com.opticas.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetCurrentDate {
	
	public String getDate(){
		String mes_s = "";
		String dia_s = "";
		String response = "";
		//Instanciamos el objeto Calendar
        //en fecha obtenemos la fecha y hora del sistema
        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //usando el método get y el parámetro correspondiente
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        if(dia < 10){
        	dia_s = "0" + dia;
        }else{
        	dia_s = "" + dia;
        }
        if((mes+1) < 10){
        	mes_s = "0" + (mes+1);
        }else{
        	mes_s = "" + (mes+1);
        }
        response = dia_s + "/" + mes_s + "/" + ano;
		return response;
	}

}
