package soporte.controlador;

import herramientas.Utileria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class SesionIvalidaController extends AbstractController{
	
	
	public SesionIvalidaController() {
		super();
		//TODO Auto-generated constructor stub
	}
	
	protected ModelAndView handleRequestInternal(
										HttpServletRequest request,
										HttpServletResponse response) throws Exception {		
		
		Utileria.escribelog("SesionIvalidaController");
		return new ModelAndView("soporte/sessionInvalida", "listaResultado", null);

	}

}
