package soporte.controlador;

import general.bean.MensajeTransaccionBean;
import general.bean.ParametrosSesionBean;
import general.servicio.ParametrosAplicacionServicio;
import herramientas.Utileria;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import soporte.bean.UsuarioBean;
import soporte.servicio.UsuarioServicio;
import soporte.servicio.UsuarioServicio.Enum_Act_Usuario;

public class SessionExpiradaControlador extends AbstractController{

	UsuarioServicio usuarioServicio = null;

	

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 HttpServletResponse response)
	throws Exception {
		
		String clave = request.getParameter("claveUsu");	
		UsuarioBean usuarioBean = new UsuarioBean();		
		usuarioBean.setClave(clave);

		MensajeTransaccionBean mensaje = null;
		mensaje= usuarioServicio.actualizaUsuario(Enum_Act_Usuario.act_statusSesInact, usuarioBean);
		List listaResultado = (List)new ArrayList();
		listaResultado.add(mensaje);
		
		return new ModelAndView("soporte/sesionExpiradaVista", "listaResultado", listaResultado);
	}


	public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}

	
	
}