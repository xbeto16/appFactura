package soporte.controlador;
import general.bean.ParametrosAuditoriaBean;
import general.bean.ParametrosSesionBean;
import general.servicio.ParametrosAplicacionServicio;
import general.servicio.ParametrosAplicacionServicio.Enum_Con_ParAplicacion;
import herramientas.SistemaLogging;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import soporte.bean.MenuAplicacionBean;
import soporte.bean.UsuarioBean;
import soporte.servicio.MenuServicio;
import soporte.servicio.MenuServicio.Enum_Con_Menu;

public class MenuControlador extends AbstractController{

	Logger log = Logger.getLogger( this.getClass() );
	
 	MenuServicio menuServicio = null;
 	ParametrosAplicacionServicio parametrosAplicacionServicio = null;
 	private ParametrosSesionBean parametrosSesionBean = null;
 	private ParametrosAuditoriaBean parametrosAuditoriaBean = null; 	
	public MenuControlador() {
		super();
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			 									 HttpServletResponse response)
																			throws Exception {
		
		String claveUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
				
		MenuAplicacionBean menuAplicacion = null;
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.setClave(claveUsuario);
	
	
			ParametrosSesionBean parametros =	parametrosAplicacionServicio.consultaParametrosSession(
					Enum_Con_ParAplicacion.loginValidaSesion, usuarioBean);
			String programa="MenuControlador";
			ParametrosAuditoriaBean parametrosAuditoriaBean1 = new ParametrosAuditoriaBean();
			parametrosAuditoriaBean1.setNumeroTransaccion(1234567890);
									
			log.info("Consulto parametros sesion");
			estableceParametros(parametros, request);
			//Sistema Loggin
			//String ClaseBean=ParametrosSesionBean.class.getName();
			String ClaseBean = parametros.getClass().getName();//nombre de la claseBean que se envia con
			SistemaLogging.escribeLog(programa,parametrosAuditoriaBean,parametros, this.getClass(),ClaseBean);
			//TODO Agregar el llenado de los Parametros de Auditoria
			menuAplicacion = menuServicio.consultaMenuPorPerfil(Enum_Con_Menu.porPerfil, usuarioBean);
			List<Object> listaResultado = (List<Object>)new ArrayList();
			listaResultado.add(parametros);   
			listaResultado.add(menuAplicacion);		
			
			return new ModelAndView("menuAplicacionView", "listaResultado", listaResultado);
			
			
		}
		
//	}
	
	private void estableceParametros(ParametrosSesionBean parametros, HttpServletRequest request){
		parametrosSesionBean.setFechaAplicacion(parametros.getFechaAplicacion());
		parametrosSesionBean.setNumeroSucursalMatriz(parametros.getNumeroSucursalMatriz());
		parametrosSesionBean.setNombreSucursalMatriz(parametros.getNombreSucursalMatriz());
		parametrosSesionBean.setTelefonoLocal(parametros.getTelefonoLocal());
		parametrosSesionBean.setTelefonoInterior(parametros.getTelefonoInterior());
		parametrosSesionBean.setNumeroInstitucion(parametros.getNumeroInstitucion());
		parametrosSesionBean.setNombreInstitucion(parametros.getNombreInstitucion());
		parametrosSesionBean.setRepresentanteLegal(parametros.getRepresentanteLegal());
		parametrosSesionBean.setRfcRepresentante(parametros.getRfcRepresentante());		
		parametrosSesionBean.setNumeroMonedaBase(parametros.getNumeroMonedaBase());
		parametrosSesionBean.setNombreMonedaBase(parametros.getNombreMonedaBase());
		parametrosSesionBean.setDesCortaMonedaBase(parametros.getDesCortaMonedaBase());
		parametrosSesionBean.setSimboloMonedaBase(parametros.getSimboloMonedaBase());
		
		parametrosSesionBean.setNumeroUsuario(parametros.getNumeroUsuario());
		parametrosSesionBean.setClaveUsuario(parametros.getClaveUsuario());
		parametrosSesionBean.setPerfilUsuario(parametros.getPerfilUsuario());		
		parametrosSesionBean.setNombreUsuario(parametros.getNombreUsuario());
		parametrosSesionBean.setCorreoUsuario(parametros.getCorreoUsuario());
		parametrosSesionBean.setSucursal(parametros.getSucursal());
		parametrosSesionBean.setFechaSucursal(parametros.getFechaSucursal());
		parametrosSesionBean.setNombreSucursal(parametros.getNombreSucursal());		
		parametrosSesionBean.setGerenteSucursal(parametros.getGerenteSucursal());		
		parametrosSesionBean.setNumeroCaja(parametros.getNumeroCaja());
		parametrosSesionBean.setLoginsFallidos(parametros.getLoginsFallidos());		
		parametrosSesionBean.setTasaISR(parametros.getTasaISR());
		parametrosSesionBean.setDiasBaseInversion(parametros.getDiasBaseInversion());
		parametrosSesionBean.setFechUltimAcces(parametros.getFechUltimAcces());
		parametrosSesionBean.setFechUltPass(parametros.getFechUltPass());
		parametrosSesionBean.setDiasCambioPass(parametros.getDiasCambioPass());
		parametrosSesionBean.setCambioPassword(parametros.getCambioPassword());
		parametrosSesionBean.setEstatusSesion(parametros.getEstatusSesion());
		parametrosSesionBean.setIPsesion(parametros.getIPsesion());
		parametrosSesionBean.setPromotorID(parametros.getPromotorID());
		parametrosSesionBean.setClienteInstitucion(parametros.getClienteInstitucion());
		parametrosSesionBean.setCuentaInstitucion(parametros.getCuentaInstitucion());
		parametrosSesionBean.setRutaArchivos(parametros.getRutaArchivos());
		
		parametrosAuditoriaBean.setSucursal(parametros.getSucursal());
		parametrosAuditoriaBean.setUsuario(parametros.getNumeroUsuario());
		parametrosAuditoriaBean.setDireccionIP(request.getRemoteHost());
		parametrosAuditoriaBean.setFecha(parametros.getFechaAplicacion());
		parametrosAuditoriaBean.setEmpresaID(parametros.getEmpresaID());
		
		
	}			
		

	
	// ------------------------- Getters y Setters ----------------------------------------------------------
	public void setMenuServicio(MenuServicio menuServicio) {
		this.menuServicio = menuServicio;
	}

	public void setParametrosSesionBean(ParametrosSesionBean parametrosSesionBean) {
		this.parametrosSesionBean = parametrosSesionBean;
	}

	public void setParametrosAplicacionServicio(
			ParametrosAplicacionServicio parametrosAplicacionServicio) {
		this.parametrosAplicacionServicio = parametrosAplicacionServicio;
	}

	public void setParametrosAuditoriaBean(
			ParametrosAuditoriaBean parametrosAuditoriaBean) {
		this.parametrosAuditoriaBean = parametrosAuditoriaBean;
	}
	
	
	
}

	
