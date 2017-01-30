package soporte.bean;

import java.util.Date;

import general.bean.BaseBean;


public class OperacionesFechasBean extends BaseBean {

	private String primerFecha;
	private String segundaFecha;
	private int numeroDias;
	
	private String fechaResultado;
	private int diasEntreFechas;
	
	public String getPrimerFecha() {
		return primerFecha;
	}
	public void setPrimerFecha(String primerFecha) {
		this.primerFecha = primerFecha;
	}
	public String getSegundaFecha() {
		return segundaFecha;
	}
	public void setSegundaFecha(String segundaFecha) {
		this.segundaFecha = segundaFecha;
	}
	public int getNumeroDias() {
		return numeroDias;
	}
	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}
	public String getFechaResultado() {
		return fechaResultado;
	}
	public void setFechaResultado(String fechaResultado) {
		this.fechaResultado = fechaResultado;
	}
	public int getDiasEntreFechas() {
		return diasEntreFechas;
	}
	public void setDiasEntreFechas(int diasEntreFechas) {
		this.diasEntreFechas = diasEntreFechas;
	}
	
	
	
	
	
}
