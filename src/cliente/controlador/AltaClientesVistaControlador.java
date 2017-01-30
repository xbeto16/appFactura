package cliente.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import general.bean.MensajeTransaccionBean;
import cliente.bean.ClienteBean;
import cliente.servicio.ClienteServicio;

public class AltaClientesVistaControlador extends SimpleFormController{

	ClienteServicio clienteServicio = null;
	
	public AltaClientesVistaControlador() {
		setCommandClass(ClienteBean.class);
		setCommandName("clienteBean");
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
		HttpServletResponse response,
		Object command,
		BindException errors) throws Exception {

		clienteServicio.getClienteDAO().getParametrosAuditoriaBean().setNombrePrograma(request.getRequestURI().toString());

		ClienteBean clienteBean = (ClienteBean) command;
		int tipoTransaccion = Integer.parseInt(request.getParameter("tipoTransaccion"));
		MensajeTransaccionBean mensaje = null;
		mensaje = clienteServicio.grabaTransaccion(tipoTransaccion,clienteBean);
									
		return new ModelAndView(getSuccessView(), "mensaje", mensaje);
	}

	public void setClienteServicio(ClienteServicio clienteServicio) {
		this.clienteServicio = clienteServicio;
	}

	
}
