package soporte.controlador;

import general.bean.MensajeTransaccionBean;
import general.bean.ParametrosSesionBean;
import general.servicio.ParametrosAplicacionServicio;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import soporte.bean.UsuarioBean;
import soporte.servicio.UsuarioServicio;

public class CerrarSesionUsuarioControlador extends AbstractController{

	UsuarioServicio usuarioServicio = null;
	private ParametrosSesionBean parametrosSesionBean = null;
	ParametrosAplicacionServicio parametrosAplicacionServicio = null;
	

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 HttpServletResponse response)
	throws Exception {		
		
		return new ModelAndView("soporte/cerrarSesionUsuario", "listaResultado", null);
	}

	public void setParametrosSesionBean(ParametrosSesionBean parametrosSesionBean) {
		this.parametrosSesionBean = parametrosSesionBean;
	}

	public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}

	
	
}