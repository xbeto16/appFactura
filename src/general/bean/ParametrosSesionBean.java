package general.bean;

import java.sql.Date;

public class ParametrosSesionBean extends BaseBean  implements ParametrosAuditoriaInterfaz {

	private Date fechaAplicacion;
	private int numeroSucursalMatriz;
    private String nombreSucursalMatriz;
    private String telefonoLocal;
    private String telefonoInterior;	
    private int numeroInstitucion;
    private String nombreInstitucion;
    private String representanteLegal;
    private String rfcRepresentante;
	
    private int numeroMonedaBase;
    private String nombreMonedaBase;	
    private String desCortaMonedaBase;
    private String simboloMonedaBase;
	
    private int numeroUsuario;
    private String claveUsuario;
    private int perfilUsuario;
    private String nombreUsuario;
    private String correoUsuario;
	
    private int sucursal;
    private Date fechaSucursal;
    private String nombreSucursal;
    private String gerenteSucursal;
	
    private String numeroCaja;
    private int loginsFallidos;
    private String mensajeLogin;
    private float tasaISR;
	
    private int empresaID;
    private int diasBaseInversion;
    private int diasCambioPass;
    private String cambioPassword;
    private String estatusSesion;
    private String IPsesion;
    private int clienteInstitucion;
    private int CuentaInstitucion;
    
    private String rutaArchivos;
	
    private int promotorID;
    	
    private int cajaID;
    private String tipoCaja;
    private String saldoEfecMN;
    private String saldoEfecME;
    private String limiteEfectivoMN;
    private String tipoCajaDes;
    	
	public String getIPsesion() {
		return IPsesion;
	}
	public void setIPsesion(String iPsesion) {
		IPsesion = iPsesion;
	}
	public int getDiasCambioPass() {
		return diasCambioPass;
	}
	public void setDiasCambioPass(int diasCambioPass) {
		this.diasCambioPass = diasCambioPass;
	}
	Date fechUltimAcces;
	Date fechUltPass;
	
	public Date getFechUltimAcces() {
		return fechUltimAcces;
	}
	public void setFechUltimAcces(Date fechUltimAcces) {
		this.fechUltimAcces = fechUltimAcces;
	}
	public Date getFechUltPass() {
		return fechUltPass;
	}
	public void setFechUltPass(Date fechUltPass) {
		this.fechUltPass = fechUltPass;
	}
	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public int getNumeroSucursalMatriz() {
		return numeroSucursalMatriz;
	}
	public void setNumeroSucursalMatriz(int numeroSucursalMatriz) {
		this.numeroSucursalMatriz = numeroSucursalMatriz;
	}
	public String getNombreSucursalMatriz() {
		return nombreSucursalMatriz;
	}
	public void setNombreSucursalMatriz(String nombreSucursalMatriz) {
		this.nombreSucursalMatriz = nombreSucursalMatriz;
	}
	public String getTelefonoLocal() {
		return telefonoLocal;
	}
	public void setTelefonoLocal(String telefonoLocal) {
		this.telefonoLocal = telefonoLocal;
	}
	public String getTelefonoInterior() {
		return telefonoInterior;
	}
	public void setTelefonoInterior(String telefonoInterior) {
		this.telefonoInterior = telefonoInterior;
	}
	public int getNumeroInstitucion() {
		return numeroInstitucion;
	}
	public void setNumeroInstitucion(int numeroInstitucion) {
		this.numeroInstitucion = numeroInstitucion;
	}
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}
	public String getRepresentanteLegal() {
		return representanteLegal;
	}
	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	public String getRfcRepresentante() {
		return rfcRepresentante;
	}
	public void setRfcRepresentante(String rfcRepresentante) {
		this.rfcRepresentante = rfcRepresentante;
	}
	public int getNumeroMonedaBase() {
		return numeroMonedaBase;
	}
	public void setNumeroMonedaBase(int numeroMonedaBase) {
		this.numeroMonedaBase = numeroMonedaBase;
	}
	public String getNombreMonedaBase() {
		return nombreMonedaBase;
	}
	public void setNombreMonedaBase(String nombreMonedaBase) {
		this.nombreMonedaBase = nombreMonedaBase;
	}
	public String getDesCortaMonedaBase() {
		return desCortaMonedaBase;
	}
	public void setDesCortaMonedaBase(String desCortaMonedaBase) {
		this.desCortaMonedaBase = desCortaMonedaBase;
	}
	public String getSimboloMonedaBase() {
		return simboloMonedaBase;
	}
	public void setSimboloMonedaBase(String simboloMonedaBase) {
		this.simboloMonedaBase = simboloMonedaBase;
	}
	public int getNumeroUsuario() {
		return numeroUsuario;
	}
	public void setNumeroUsuario(int numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}
	public String getClaveUsuario() {
		return claveUsuario;
	}
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}
	public int getPerfilUsuario() {
		return perfilUsuario;
	}
	public void setPerfilUsuario(int perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getCorreoUsuario() {
		return correoUsuario;
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public Date getFechaSucursal() {
		return fechaSucursal;
	}
	public void setFechaSucursal(Date fechaSucursal) {
		this.fechaSucursal = fechaSucursal;
	}
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	public String getGerenteSucursal() {
		return gerenteSucursal;
	}
	public void setGerenteSucursal(String gerenteSucursal) {
		this.gerenteSucursal = gerenteSucursal;
	}
	public String getNumeroCaja() {
		return numeroCaja;
	}
	public void setNumeroCaja(String numeroCaja) {
		this.numeroCaja = numeroCaja;
	}
	public int getLoginsFallidos() {
		return loginsFallidos;
	}
	public void setLoginsFallidos(int loginsFallidos) {
		this.loginsFallidos = loginsFallidos;
	}
	public String getMensajeLogin() {
		return mensajeLogin;
	}
	public void setMensajeLogin(String mensajeLogin) {
		this.mensajeLogin = mensajeLogin;
	}
	public float getTasaISR() {
		return tasaISR;
	}
	public void setTasaISR(float tasaISR) {
		this.tasaISR = tasaISR;
	}
	public int getClienteInstitucion() {
		return clienteInstitucion;
	}
	public void setClienteInstitucion(int clienteInstitucion) {
		this.clienteInstitucion = clienteInstitucion;
	}
	public int getCuentaInstitucion() {
		return CuentaInstitucion;
	}
	public void setCuentaInstitucion(int cuentaInstitucion) {
		CuentaInstitucion = cuentaInstitucion;
	}
	public int getEmpresaID() {
		return empresaID;
	}
	public void setEmpresaID(int empresaID) {
		this.empresaID = empresaID;
	}
	public int getDiasBaseInversion() {
		return diasBaseInversion;
	}
	public void setDiasBaseInversion(int diasBaseInversion) {
		this.diasBaseInversion = diasBaseInversion;
	}
	public String getCambioPassword() {
		return cambioPassword;
	}
	public void setCambioPassword(String cambioPassword) {
		this.cambioPassword = cambioPassword;
	}
	public String getEstatusSesion() {
		return estatusSesion;
	}
	public void setEstatusSesion(String estatusSesion) {
		this.estatusSesion = estatusSesion;
	}
	public int getPromotorID() {
		return promotorID;
	}
	public void setPromotorID(int promotorID) {
		this.promotorID = promotorID;
	}
	public String getRutaArchivos() {
		return rutaArchivos;
	}
	public void setRutaArchivos(String rutaArchivos) {
		this.rutaArchivos = rutaArchivos;
	}
	public int getCajaID() {
		return cajaID;
	}
	public void setCajaID(int cajaID) {
		this.cajaID = cajaID;
	}
	public String getTipoCaja() {
		return tipoCaja;
	}
	public void setTipoCaja(String tipoCaja) {
		this.tipoCaja = tipoCaja;
	}
	public String getSaldoEfecMN() {
		return saldoEfecMN;
	}
	public void setSaldoEfecMN(String saldoEfecMN) {
		this.saldoEfecMN = saldoEfecMN;
	}
	public String getSaldoEfecME() {
		return saldoEfecME;
	}
	public void setSaldoEfecME(String saldoEfecME) {
		this.saldoEfecME = saldoEfecME;
	}
	public String getLimiteEfectivoMN() {
		return limiteEfectivoMN;
	}
	public void setLimiteEfectivoMN(String limiteEfectivoMN) {
		this.limiteEfectivoMN = limiteEfectivoMN;
	}
	public String getTipoCajaDes() {
		return tipoCajaDes;
	}
	public void setTipoCajaDes(String tipoCajaDes) {
		this.tipoCajaDes = tipoCajaDes;
	}
}
