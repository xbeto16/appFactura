package general.bean;

import java.sql.Date;

public interface ParametrosAuditoriaInterfaz {

	Date getFechaAplicacion();	
	void setFechaAplicacion(Date fechaAplicacion);	
	int getNumeroSucursalMatriz();	
	void setNumeroSucursalMatriz(int numeroSucursalMatriz);	
	String getNombreSucursalMatriz();
	void setNombreSucursalMatriz(String nombreSucursalMatriz);
	String getTelefonoLocal();
	void setTelefonoLocal(String telefonoLocal);	
	String getTelefonoInterior();	
	void setTelefonoInterior(String telefonoInterior);	
	int getNumeroInstitucion();
	void setNumeroInstitucion(int numeroInstitucion);
	String getNombreInstitucion();
	void setNombreInstitucion(String nombreInstitucion);	
	String getRepresentanteLegal();
	void setRepresentanteLegal(String representanteLegal);
	String getRfcRepresentante();
	void setRfcRepresentante(String rfcRepresentante);
	int getNumeroMonedaBase();
	void setNumeroMonedaBase(int numeroMonedaBase);
	String getNombreMonedaBase();
	void setNombreMonedaBase(String nombreMonedaBase);
	String getDesCortaMonedaBase();
	void setDesCortaMonedaBase(String desCortaMonedaBase);
	String getSimboloMonedaBase();
	void setSimboloMonedaBase(String simboloMonedaBase);
	int getNumeroUsuario();
	void setNumeroUsuario(int numeroUsuario);
	String getClaveUsuario();
	void setClaveUsuario(String claveUsuario);
	int getPerfilUsuario();
	void setPerfilUsuario(int perfilUsuario);
	String getNombreUsuario();
	void setNombreUsuario(String nombreUsuario);
	String getCorreoUsuario();
	void setCorreoUsuario(String correoUsuario);
	int getSucursal();
	void setSucursal(int sucursal);
	Date getFechaSucursal();
	void setFechaSucursal(Date fechaSucursal);
	String getNombreSucursal();
	void setNombreSucursal(String nombreSucursal);
	String getGerenteSucursal();
	void setGerenteSucursal(String gerenteSucursal);
	String getNumeroCaja();
	void setNumeroCaja(String numeroCaja);
	int getLoginsFallidos();
	void setLoginsFallidos(int loginsFallidos);
	String getMensajeLogin();
	void setMensajeLogin(String mensajeLogin);
	float getTasaISR();
	void setTasaISR(float tasaISR);
	int getEmpresaID();
	void setEmpresaID(int empresaID);
	String getRutaArchivos();
	void setRutaArchivos(String rutaArchivos);
	
	int getCajaID();
	void setCajaID(int cajaID);
	String getTipoCaja();
	void setTipoCaja(String tipoCaja);
	String getSaldoEfecMN();
	void setSaldoEfecMN(String saldoEfecMN);
	String getSaldoEfecME();
	void setSaldoEfecME(String saldoEfecME);
	String getLimiteEfectivoMN();
	void setLimiteEfectivoMN(String limiteEfectivoMN);
	String getTipoCajaDes();
	void setTipoCajaDes(String tipoCajaDes);	
	
}
