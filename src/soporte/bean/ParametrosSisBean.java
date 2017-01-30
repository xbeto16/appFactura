
package soporte.bean;

import general.bean.BaseBean;

public class ParametrosSisBean extends BaseBean {
	
	private String empresaID;
	private String fechaSistema;	
	private String sucursalMatrizID;
	private String telefonoLocal;
	private String telefonoInterior;	
	private String institucionID;
	private String empresaDefault;
	private String nombreRepresentante;
	private String RFCRepresentante	;
	private String monedaBaseID;
	private String	monedaExtrangeraID;
	private String tasaISR;
	private String tasaIDE;
	private String montoExcIDE;
	private String ejercicioVigente	;
	private String periodoVigente;
	private String diasInversion;
	private String diasCredito;
	private String diasCambioPass;
	private String lonMinCaracPass;
	private String	clienteInstitucion;
	private String cuentaInstituc;		
	private String manejaCaptacion;
	private String bancoCaptacion;
	private String rutaRPTEdoCta;
	private String rutaPDFEdoCta;
	private String tipoCuenta;
	private String rutaArchivos	;
	private String rolTesoreria;
	private String rolAdminTeso	;
	private String oficialCumID	;
	private String dirGeneralID;
	private String dirOperID	;
	private String rutaArchivosPLD;
	
	private String remitente;
	private String servidorCorreo;
	private String puerto;
	private String	usuarioCorreo;
	private String contrasenia;

	
	
	
	private String usuario;
	private String fechaActual;
	private String direccionIP	;
	private String programaID;
	private String sucursal	;
	private String numTransaccion;	
	
	// auxiliares del Bean 
	 private String nombreInstitucion;
	
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}
	
	
	public String getEmpresaID() {
		return empresaID;
	}
	public String getFechaSistema() {
		return fechaSistema;
	}
	public String getSucursalMatrizID() {
		return sucursalMatrizID;
	}
	public String getTelefonoLocal() {
		return telefonoLocal;
	}
	public String getTelefonoInterior() {
		return telefonoInterior;
	}
	public String getInstitucionID() {
		return institucionID;
	}
	public String getEmpresaDefault() {
		return empresaDefault;
	}
	public String getNombreRepresentante() {
		return nombreRepresentante;
	}
	public String getRFCRepresentante() {
		return RFCRepresentante;
	}
	public String getMonedaBaseID() {
		return monedaBaseID;
	}
	public String getMonedaExtrangeraID() {
		return monedaExtrangeraID;
	}
	public String getTasaISR() {
		return tasaISR;
	}
	public String getTasaIDE() {
		return tasaIDE;
	}
	public String getMontoExcIDE() {
		return montoExcIDE;
	}
	public String getEjercicioVigente() {
		return ejercicioVigente;
	}
	public String getPeriodoVigente() {
		return periodoVigente;
	}
	public String getDiasInversion() {
		return diasInversion;
	}
	public String getDiasCredito() {
		return diasCredito;
	}
	public String getDiasCambioPass() {
		return diasCambioPass;
	}
	public String getLonMinCaracPass() {
		return lonMinCaracPass;
	}
	public String getClienteInstitucion() {
		return clienteInstitucion;
	}
	public String getCuentaInstituc() {
		return cuentaInstituc;
	}
	public String getManejaCaptacion() {
		return manejaCaptacion;
	}
	public String getBancoCaptacion() {
		return bancoCaptacion;
	}
	public String getRutaRPTEdoCta() {
		return rutaRPTEdoCta;
	}
	public String getRutaPDFEdoCta() {
		return rutaPDFEdoCta;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public String getRutaArchivos() {
		return rutaArchivos;
	}
	public String getRolTesoreria() {
		return rolTesoreria;
	}
	public String getRolAdminTeso() {
		return rolAdminTeso;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getFechaActual() {
		return fechaActual;
	}
	public String getDireccionIP() {
		return direccionIP;
	}
	public String getProgramaID() {
		return programaID;
	}
	public String getSucursal() {
		return sucursal;
	}
	public String getNumTransaccion() {
		return numTransaccion;
	}
	public void setEmpresaID(String empresaID) {
		this.empresaID = empresaID;
	}
	public void setFechaSistema(String fechaSistema) {
		this.fechaSistema = fechaSistema;
	}
	public void setSucursalMatrizID(String sucursalMatrizID) {
		this.sucursalMatrizID = sucursalMatrizID;
	}
	public void setTelefonoLocal(String telefonoLocal) {
		this.telefonoLocal = telefonoLocal;
	}
	public void setTelefonoInterior(String telefonoInterior) {
		this.telefonoInterior = telefonoInterior;
	}
	public void setInstitucionID(String institucionID) {
		this.institucionID = institucionID;
	}
	public void setEmpresaDefault(String empresaDefault) {
		this.empresaDefault = empresaDefault;
	}
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}
	public void setRFCRepresentante(String rFCRepresentante) {
		RFCRepresentante = rFCRepresentante;
	}
	public void setMonedaBaseID(String monedaBaseID) {
		this.monedaBaseID = monedaBaseID;
	}
	public void setMonedaExtrangeraID(String monedaExtrangeraID) {
		this.monedaExtrangeraID = monedaExtrangeraID;
	}
	public void setTasaISR(String tasaISR) {
		this.tasaISR = tasaISR;
	}
	public void setTasaIDE(String tasaIDE) {
		this.tasaIDE = tasaIDE;
	}
	public void setMontoExcIDE(String montoExcIDE) {
		this.montoExcIDE = montoExcIDE;
	}
	public void setEjercicioVigente(String ejercicioVigente) {
		this.ejercicioVigente = ejercicioVigente;
	}
	public void setPeriodoVigente(String periodoVigente) {
		this.periodoVigente = periodoVigente;
	}
	public void setDiasInversion(String diasInversion) {
		this.diasInversion = diasInversion;
	}
	public void setDiasCredito(String diasCredito) {
		this.diasCredito = diasCredito;
	}
	public void setDiasCambioPass(String diasCambioPass) {
		this.diasCambioPass = diasCambioPass;
	}
	public void setLonMinCaracPass(String lonMinCaracPass) {
		this.lonMinCaracPass = lonMinCaracPass;
	}
	public void setClienteInstitucion(String clienteInstitucion) {
		this.clienteInstitucion = clienteInstitucion;
	}
	public void setCuentaInstituc(String cuentaInstituc) {
		this.cuentaInstituc = cuentaInstituc;
	}
	public void setManejaCaptacion(String manejaCaptacion) {
		this.manejaCaptacion = manejaCaptacion;
	}
	public void setBancoCaptacion(String bancoCaptacion) {
		this.bancoCaptacion = bancoCaptacion;
	}
	public void setRutaRPTEdoCta(String rutaRPTEdoCta) {
		this.rutaRPTEdoCta = rutaRPTEdoCta;
	}
	public void setRutaPDFEdoCta(String rutaPDFEdoCta) {
		this.rutaPDFEdoCta = rutaPDFEdoCta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public void setRutaArchivos(String rutaArchivos) {
		this.rutaArchivos = rutaArchivos;
	}
	public void setRolTesoreria(String rolTesoreria) {
		this.rolTesoreria = rolTesoreria;
	}
	public void setRolAdminTeso(String rolAdminTeso) {
		this.rolAdminTeso = rolAdminTeso;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}
	public void setProgramaID(String programaID) {
		this.programaID = programaID;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public void setNumTransaccion(String numTransaccion) {
		this.numTransaccion = numTransaccion;
	}
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}


	public String getOficialCumID() {
		return oficialCumID;
	}


	public String getDirGeneralID() {
		return dirGeneralID;
	}


	public String getDirOperID() {
		return dirOperID;
	}


	public void setOficialCumID(String oficialCumID) {
		this.oficialCumID = oficialCumID;
	}


	public void setDirGeneralID(String dirGeneralID) {
		this.dirGeneralID = dirGeneralID;
	}


	public void setDirOperID(String dirOperID) {
		this.dirOperID = dirOperID;
	}


	public String getRutaArchivosPLD() {
		return rutaArchivosPLD;
	}


	public void setRutaArchivosPLD(String rutaArchivosPLD) {
		this.rutaArchivosPLD = rutaArchivosPLD;
	}


	public String getRemitente() {
		return remitente;
	}


	

	public String getPuerto() {
		return puerto;
	}


	



	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}





	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}


	


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	public String getServidorCorreo() {
		return servidorCorreo;
	}


	public void setServidorCorreo(String servidorCorreo) {
		this.servidorCorreo = servidorCorreo;
	}


	public String getUsuarioCorreo() {
		return usuarioCorreo;
	}


	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}
	
		
}
