package soporte.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import general.bean.MensajeTransaccionBean;
import soporte.bean.EmisorBean;
import soporte.servicio.EmisorServicio;


public class AltaEmisorControlador  extends SimpleFormController{
	
	EmisorServicio emisorServicio = null;
	public AltaEmisorControlador() {
		setCommandClass(EmisorBean.class);
		setCommandName("emisorBean");
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors) throws Exception {

			emisorServicio.getEmisorDAO().getParametrosAuditoriaBean().setNombrePrograma(request.getRequestURI().toString());
			EmisorBean emisorBean = (EmisorBean) command;
			int tipoTransaccion = Integer.parseInt(request.getParameter("tipoTransaccion"));
			MensajeTransaccionBean mensaje = null;
			mensaje = emisorServicio.grabaTransaccion(tipoTransaccion,emisorBean);
			return new ModelAndView(getSuccessView(), "mensaje", mensaje);
	}

	public EmisorServicio getEmisorServicio() {
		return emisorServicio;
	}

	public void setEmisorServicio(EmisorServicio emisorServicio) {
		this.emisorServicio = emisorServicio;
	}
	
	
}
