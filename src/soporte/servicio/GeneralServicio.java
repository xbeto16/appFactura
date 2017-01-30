package soporte.servicio;

import general.bean.MensajeTransaccionBean;
import general.servicio.BaseServicio;
import soporte.dao.GeneralDAO;

public class GeneralServicio extends BaseServicio{

	//---------- Variables ------------------------------------------------------------------------
	GeneralDAO generalDAO = null;
	
	public GeneralServicio() {
		super();
		// TODO Auto-generated constructor stub
		
	}	
	
	//---------- Procedimientos ------------------------------------------------------------------------

	
	//------------------ Geters y Seters ------------------------------------------------------
	
	public GeneralDAO getGeneralDAO() {
		return generalDAO;
	}	
	
	public void setGeneralDAO(GeneralDAO generalDAO) {
		this.generalDAO = generalDAO;
	}			   
	
	
	
	
}
