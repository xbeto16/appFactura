package facturacion.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import facturacion.bean.FacturaBean;
import facturacion.servicio.FacturaServicio;
import general.bean.MensajeTransaccionBean;

public class GeneraFacturaControlador extends SimpleFormController {
	
	FacturaServicio facturaServicio = null;
	public GeneraFacturaControlador() {
		setCommandClass(FacturaBean.class);
		setCommandName("facturaBean");
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors) throws Exception {

		facturaServicio.getFacturaDAO().getParametrosAuditoriaBean().setNombrePrograma(request.getRequestURI().toString());

		FacturaBean cliente = (FacturaBean) command;
		int tipoTransaccion = Integer.parseInt(request.getParameter("tipoTransaccion"));
		MensajeTransaccionBean mensaje = null;
		mensaje = facturaServicio.grabaTransaccion(tipoTransaccion,cliente);
										
		return new ModelAndView(getSuccessView(), "mensaje", mensaje);
	}

	public FacturaServicio getFacturaServicio() {
		return facturaServicio;
	}

	public void setFacturaServicio(FacturaServicio facturaServicio) {
		this.facturaServicio = facturaServicio;
	}
}