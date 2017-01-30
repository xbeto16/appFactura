package seguridad;

import herramientas.Utileria;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureCredentialsExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import soporte.bean.UsuarioBean;
import soporte.servicio.UsuarioServicio;

public class SeguridadListener implements ApplicationListener<AbstractAuthenticationEvent>{
	UsuarioServicio usuarioServicio = null;
	Logger log = Logger.getLogger( this.getClass());
	
	public void onApplicationEvent(AbstractAuthenticationEvent event) {				
		if(event instanceof AuthenticationFailureBadCredentialsEvent){			
			UsuarioBean usuario = new UsuarioBean();
			Utileria.escribelog("SeguridadListener. Login Fallido, Usuario ; " + usuario.getClave());
			usuario.setClave(((AuthenticationFailureBadCredentialsEvent)event).getAuthentication().getName());
			usuarioServicio.actualizaUsuario(UsuarioServicio.Enum_Act_Usuario.loginsFallidos, usuario);
			//TODO: Logear con Log4J			
		}else if(event instanceof AuthenticationFailureDisabledEvent){			
			UsuarioBean usuario = new UsuarioBean();
			usuario.setClave(((AuthenticationFailureDisabledEvent)event).getAuthentication().getName());
			//TODO: Logear con Log4J
		}else if (event instanceof AuthenticationFailureCredentialsExpiredEvent){			
			UsuarioBean usuario = new UsuarioBean();
			usuario.setClave(((AuthenticationFailureCredentialsExpiredEvent)event).getAuthentication().getName());
			Utileria.escribelog("SeguridadListener. 1. Credenciales Expiradas, Usuario ; " + usuario.getClave());
		}else if (event instanceof AuthenticationFailureExpiredEvent){			
			UsuarioBean usuario = new UsuarioBean();
			usuario.setClave(((AuthenticationFailureExpiredEvent)event).getAuthentication().getName());
			Utileria.escribelog("SeguridadListener. 2. Credenciales Expiradas, Usuario ; " + usuario.getClave());			
		}else if (event instanceof AuthenticationSuccessEvent){
			UsuarioBean usu = new UsuarioBean();
			usu.setClave(((AuthenticationSuccessEvent)event).getAuthentication().getName());				
			usuarioServicio.actualizaUsuario(UsuarioServicio.Enum_Act_Usuario.act_statusSesionAct, usu);		
		}			
					
	}

	public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}


	
	
}
