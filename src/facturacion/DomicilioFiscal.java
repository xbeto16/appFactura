package facturacion;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "DomicilioFiscal")
// If you want you can define the order in which the fields are written
// Optional
@XmlType(propOrder = {"calle", "noExterior", "colonia", "localidad", "municipio", "estado", "pais", "codigopostal"})

public class DomicilioFiscal {

	private String calle;
	private String noExterior;
	private String colonia;
	private String localidad;
	private String municipio;
	private String estado;
	private String pais;
	private String codigopostal;
	
	public DomicilioFiscal(){
	}
	
	public DomicilioFiscal(String calle, String  noExterior, String colonia, String localidad, String municipio, 
			String estado, String pais, String codigopostal){
		 this.calle = calle;
	     this.noExterior = noExterior;
	     this.colonia = colonia;
	     this.localidad = localidad;
	     this.municipio = municipio;
	     this.estado = estado;
	     this.pais = pais;
	     this.codigopostal = codigopostal;
	     
	}

	@XmlAttribute
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@XmlAttribute
	public String getNoExterior() {
		return noExterior;
	}

	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}

	@XmlAttribute
	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	@XmlAttribute
	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@XmlAttribute
	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@XmlAttribute
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@XmlAttribute
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@XmlAttribute
	public String getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	
}