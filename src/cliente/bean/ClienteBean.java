package cliente.bean;

import general.bean.BaseBean;

public class ClienteBean extends BaseBean{

	private String ClienteID;
	private String TipoPersona;
	private String RazonSocial;
	private String RFC;
	private String Calle;
	private String NumInt;
	private String NumExt;
	private String Colonia;
	private String Localidad;
	private String Municipio;
	private String Estado;
	private String CodigoPostal;
	private String Telefono;
	private String Celular;
	private String Correo;
	private String estatus;
	
	public String getClienteID() {
		return ClienteID;
	}
	public void setClienteID(String clienteID) {
		ClienteID = clienteID;
	}
	public String getTipoPersona() {
		return TipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		TipoPersona = tipoPersona;
	}
	public String getRazonSocial() {
		return RazonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	public String getCalle() {
		return Calle;
	}
	public void setCalle(String calle) {
		Calle = calle;
	}
	public String getNumInt() {
		return NumInt;
	}
	public void setNumInt(String numInt) {
		NumInt = numInt;
	}
	public String getNumExt() {
		return NumExt;
	}
	public void setNumExt(String numExt) {
		NumExt = numExt;
	}
	public String getColonia() {
		return Colonia;
	}
	public void setColonia(String colonia) {
		Colonia = colonia;
	}
	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	public String getMunicipio() {
		return Municipio;
	}
	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getCodigoPostal() {
		return CodigoPostal;
	}	
	public void setCodigoPostal(String codigoPostal) {
		CodigoPostal = codigoPostal;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getCelular() {
		return Celular;
	}
	public void setCelular(String celular) {
		Celular = celular;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
}