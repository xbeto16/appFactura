package soporte.bean;

import general.bean.BaseBean;
import herramientas.Utileria;

public class UsuarioBean extends BaseBean {
	
	//Declaracion de Constantes
	public static int LONGITUD_ID = 7;	
	public static String STATUS_ACTIVO = "A";
	public static int act_statusSesion = 5;
	public static int calcFechaPass= 1;
	public static String STAT_SES_INACT = "I";


	//Declaracion de Atributos o Variables	
	private String usuarioID;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private String nombreCompleto;
	private String estatusSesion;
	private String fechUltAcces;
	private String fechUltPass;
	private String clave;
	private String contrasenia;
	private String estatus;
	private String rolID;
	private String sucursalUsuario;
	private String nombreRol;
	private String ipSesion;
	private String correo;
	private int loginsFallidos;
	private String motivoBloqueo;
	private String fechaBloqueo;
	private String motivoCancel;
	private String fechaCancel;
	private String salt;
	private String EmpresaID;
	private String Usuario;
	private String FechaActual;
	private String DireccionIP;
	private String ProgramaID;
	private long NumTransaccion;
	
	// fecha del sistema, usuada en WS 
	private String fechaSistema;
	
	public String getUsuarioID() {
		return usuarioID;
	}
	public void setUsuarioID(String usuarioID) {
		this.usuarioID = usuarioID;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getEstatusSesion() {
		return estatusSesion;
	}
	public void setEstatusSesion(String estatusSesion) {
		this.estatusSesion = estatusSesion;
	}
	public String getFechUltAcces() {
		return fechUltAcces;
	}
	public void setFechUltAcces(String fechUltAcces) {
		this.fechUltAcces = fechUltAcces;
	}
	public String getFechUltPass() {
		return fechUltPass;
	}
	public void setFechUltPass(String fechUltPass) {
		this.fechUltPass = fechUltPass;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getRolID() {
		return rolID;
	}
	public void setRolID(String rolID) {
		this.rolID = rolID;
	}
	public String getSucursalUsuario() {
		return sucursalUsuario;
	}
	public void setSucursalUsuario(String sucursalUsuario) {
		this.sucursalUsuario = sucursalUsuario;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	public String getIpSesion() {
		return ipSesion;
	}
	public void setIpSesion(String ipSesion) {
		this.ipSesion = ipSesion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getLoginsFallidos() {
		return loginsFallidos;
	}
	public void setLoginsFallidos(int loginsFallidos) {
		this.loginsFallidos = loginsFallidos;
	}
	public String getMotivoBloqueo() {
		return motivoBloqueo;
	}
	public void setMotivoBloqueo(String motivoBloqueo) {
		this.motivoBloqueo = motivoBloqueo;
	}
	public String getFechaBloqueo() {
		return fechaBloqueo;
	}
	public void setFechaBloqueo(String fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}
	public String getMotivoCancel() {
		return motivoCancel;
	}
	public void setMotivoCancel(String motivoCancel) {
		this.motivoCancel = motivoCancel;
	}
	public String getFechaCancel() {
		return fechaCancel;
	}
	public void setFechaCancel(String fechaCancel) {
		this.fechaCancel = fechaCancel;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}	
	public String getEmpresaID() {
		return EmpresaID;
	}
	public void setEmpresaID(String empresaID) {
		EmpresaID = empresaID;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getFechaActual() {
		return FechaActual;
	}
	public void setFechaActual(String fechaActual) {
		FechaActual = fechaActual;
	}
	public String getDireccionIP() {
		return DireccionIP;
	}
	public void setDireccionIP(String direccionIP) {
		DireccionIP = direccionIP;
	}
	public String getProgramaID() {
		return ProgramaID;
	}
	public void setProgramaID(String programaID) {
		ProgramaID = programaID;
	}
	public long getNumTransaccion() {
		return NumTransaccion;
	}
	public void setNumTransaccion(long numTransaccion) {
		NumTransaccion = numTransaccion;
	}
	public String getFechaSistema() {
		return fechaSistema;
	}
	public void setFechaSistema(String fechaSistema) {
		this.fechaSistema = fechaSistema;
	}	
}
