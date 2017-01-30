package facturacion.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import facturacion.bean.FacturaBean;
import facturacion.servicio.FacturaServicio;

public class ConceptosFactGridControlador extends AbstractCommandController{

	FacturaServicio facturaServicio = null;
	
	public ConceptosFactGridControlador() {
		setCommandClass(FacturaBean.class);
		setCommandName("facturaBean");
	}

	protected ModelAndView handle(HttpServletRequest request,
			  HttpServletResponse response,
			  Object command,
			  BindException errors) throws Exception {

		FacturaBean facturaBean= (FacturaBean) command;
		int tipoLista = Integer.parseInt(request.getParameter("tipoLista"));
		List conceptosFactList = facturaServicio.lista(tipoLista, facturaBean);

		return new ModelAndView("facturacion/conceptosFacturaGridVista", "conceptosLista", conceptosFactList);
	}

	public void setFacturaServicio(FacturaServicio facturaServicio) {
		this.facturaServicio = facturaServicio;
	}	
}