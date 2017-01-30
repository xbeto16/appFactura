package soporte.servicio;

import general.bean.MensajeTransaccionBean;
import general.servicio.BaseServicio;

import java.util.List;
import soporte.bean.UsuarioBean;
import soporte.dao.UsuarioDAO;


public class UsuarioServicio extends BaseServicio {

	//---------- Variables ------------------------------------------------------------------------
	UsuarioDAO usuarioDAO = null;

	//---------- Tipo de Consulta ----------------------------------------------------------------
	public static interface Enum_Con_Usuario {
		int principal = 1;
		int foranea = 2;
		int clave = 3;
		int bloqueadoDesbloq = 4;
		int cancela = 5;
		int contrasenia = 6;
		int consultaWS = 7;
	}
	
	//---------- Tipo de Lista ----------------------------------------------------------------
	public static interface Enum_Lis_Usuario {
		int principal = 1;
	}

	//---------- Tipo de Lista ----------------------------------------------------------------	
	public static interface Enum_Tra_Usuario {
		int alta = 1;
		int modificacion = 2;
		
	}
	//---------- calculo de fechas de password ----------------------------------------------------------------	
	public static interface Enum_Cal_fechPass {
		int resta = 1;
		
	}
	
	//---------- Tipo de Actualizacion ----------------------------------------------------------------
	public static interface Enum_Act_Usuario {
		int loginsFallidos = 1;
		int actualizaBloDes = 2;
		int actualizaCancel = 3;
		int resetPassword = 4;
		int act_statusSesionAct =5;
		int act_statusSesInact =6;
	}
	
	
	public UsuarioServicio() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MensajeTransaccionBean altaUsuario(UsuarioBean usuario){
		MensajeTransaccionBean mensaje = null;
		mensaje = usuarioDAO.altaUsuario(usuario);
		
		return mensaje;
	}

	public MensajeTransaccionBean modificaUsuario(UsuarioBean usuario) {
		MensajeTransaccionBean mensaje = null;
		mensaje = usuarioDAO.modificaUsuario(usuario);
		
		return mensaje;
	}
	
	public UsuarioBean consulta(int tipoConsulta, UsuarioBean usuarioBean){
		UsuarioBean usuario = null;
		switch (tipoConsulta) { 
			case Enum_Con_Usuario.principal:		
				usuario = usuarioDAO.consultaPrincipal(usuarioBean,tipoConsulta);				
			break;				
			case Enum_Con_Usuario.foranea:
				usuario = usuarioDAO.consultaForanea(usuarioBean, tipoConsulta);
			break;
			case Enum_Con_Usuario.clave:				
				usuario = usuarioDAO.consultaPorClave(usuarioBean, tipoConsulta);
			break;
			case Enum_Con_Usuario.bloqueadoDesbloq:				
				usuario = usuarioDAO.consultaBloDesbloqueo(usuarioBean, tipoConsulta);
			break;	
			case Enum_Con_Usuario.cancela:				
				usuario = usuarioDAO.consultaCancelaUsuario(usuarioBean, tipoConsulta);
			break;	
			case Enum_Con_Usuario.contrasenia:				
				usuario = usuarioDAO.consultaContraseniaUsuario(usuarioBean, tipoConsulta);
			break;
			case Enum_Con_Usuario.consultaWS:	
				usuario = usuarioDAO.consultaWS(usuarioBean, tipoConsulta);
			break;
		}
		
		return usuario;
	}

	
	
	
	public List lista(int tipoLista, UsuarioBean usuario){		
		List listaUsuarios = null;
		switch (tipoLista) {
			case Enum_Lis_Usuario.principal:		
				listaUsuarios = usuarioDAO.listaPrincipal(usuario,tipoLista);				
				break;				
		}		
		return listaUsuarios;
	}	
	
	public MensajeTransaccionBean grabaTransaccion(int tipoTransaccion, UsuarioBean usuario) {
		MensajeTransaccionBean mensaje = null;
		switch (tipoTransaccion) {
			case Enum_Tra_Usuario.alta:		
				mensaje = altaUsuario(usuario);				
			break;				
			case Enum_Tra_Usuario.modificacion:
				mensaje = modificaUsuario(usuario);
			break;
			
		}
		return mensaje;
	}
	
	

	public MensajeTransaccionBean actualizaUsuario(int tipoActualizacion, UsuarioBean usuario){
		MensajeTransaccionBean mensaje = null;
		switch (tipoActualizacion) {
			case Enum_Act_Usuario.loginsFallidos:
				mensaje = usuarioDAO.actualizaIntentosFallidos(usuario, tipoActualizacion);				
				break;	
			case Enum_Act_Usuario.actualizaBloDes:
				mensaje = usuarioDAO.actBloqDesbloqueoUsuario(usuario, tipoActualizacion);
			break;
			case Enum_Act_Usuario.actualizaCancel:
				mensaje = usuarioDAO.actCancelaUsuario(usuario, tipoActualizacion);
			break;
			case Enum_Act_Usuario.resetPassword:
				mensaje = ValidaPassword(usuario, tipoActualizacion);
			break;
			case Enum_Act_Usuario.act_statusSesionAct:
				mensaje = usuarioDAO.actStatusSesionActivoUsuario(usuario,Enum_Act_Usuario.act_statusSesionAct);
			break;
			case Enum_Act_Usuario.act_statusSesInact:
				mensaje = usuarioDAO.actStatusSesionInactivoUsuario(usuario, tipoActualizacion);
			break;
		}
		return mensaje;
	}
		
	
	public MensajeTransaccionBean ValidaPassword(UsuarioBean usuario,int tipoActualizacion){
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		String password= usuario.getContrasenia();

		String pass[]=new String[password.length()];
		for(int i=0;i<password.length();i++){
			pass[i]=password.substring(i,i+1);
		}
		
		boolean may=false;
		boolean min=false;
		boolean numCar=false;
	
		String mayusculas[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String minusculas [] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"};
		String numsCarac [] = {"0","1","2","3","4","5","6","7","8","9","!","#","%","&","/","(",")","=","?","¬","¿","+","±","*","[","]",
							   "{","}","÷","$",".","_","-","|","°"};
		
		if (password.length()<6){
			mensaje.setNumero(999);
			mensaje.setDescripcion("La contrasenia debe tener como minimo 6 caracteres");
			mensaje.setNombreControl("nuevaContra");
			return mensaje;
		}else
			if (password.length()>=6){
				
				
				do {
					int i;
					for(i=0;i<pass.length;i++){
						for(int j=0;j<mayusculas.length;j++){
							if(pass[i].equals(mayusculas[j])){;
								may=true;
							}
						}
					}
					if(i == pass.length && may == false){
						may=true;
						mensaje.setNumero(999);
						mensaje.setDescripcion("Se requiere al menos 1 Caracter Alfabetico Mayuscula.");
						mensaje.setNombreControl("nuevaContra");
						return mensaje;
					}
					
					} while (may==false);
				
				
				do {
						int i;
						for(i=0;i<pass.length;i++){
							for(int j=0;j<minusculas.length;j++){
								if(pass[i].equals(minusculas[j])){
								min=true;
								}
							}
						}
						if(i == pass.length && min == false){
							min=true;
							mensaje.setNumero(999);
							mensaje.setDescripcion("Se requiere al menos 1 Caracter Alfabetico Minuscula.");
							mensaje.setNombreControl("nuevaContra");
							return mensaje;
						}

						} while (min==false);
				
				do {
					int i;
					for(i=0;i<pass.length;i++){
						for(int j=0;j<numsCarac.length;j++){
							if(pass[i].equals(numsCarac[j])){
							numCar=true;
							}
							
						}
					}
					if(i == pass.length && numCar == false){
						numCar=true;
						mensaje.setNumero(999);
						mensaje.setDescripcion("Se requiere al menos 1 Numero o Caracter Especial.");
						mensaje.setNombreControl("nuevaContra");
						return mensaje;
					}
					} while (numCar==false);
					
				
			}
			
		
		
		
		if(may==true && min==true && numCar==true){	
			mensaje = usuarioDAO.resetPasswordUsuario(usuario, Enum_Act_Usuario.resetPassword);
		}else{
			mensaje.setNumero(999);
			mensaje.setDescripcion("Se requiere al menos 1 Caracter Alfabetico Mayuscula, 1 Caracter Alfabetico Miniscula, 1 Numero o Caracter Especial.");
			mensaje.setNombreControl("nuevaContra");
		}
		
		
		return mensaje;
	}
	
	
	//------------------ Geters y Seters ------------------------------------------------------	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	
	
}
