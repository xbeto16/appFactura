package soporte.servicio;

import java.util.List;
import soporte.bean.ParametrosSisBean;

import general.bean.MensajeTransaccionBean;
import general.servicio.BaseServicio;
import herramientas.Utileria;
import soporte.dao.ParametrosSisDAO;

public class ParametrosSisServicio extends BaseServicio {
	//---------- Variables ---------------------------------
		ParametrosSisDAO parametrosSisDAO = null;
		
		public ParametrosSisServicio() {
			super();
		}
		
		public static interface Enum_Tra_ParametrosSis {
			int alta = 1;
			int modificacion = 2;
		}
		// tipo de consulta
		public static interface Enum_Con_ParametrosSis { 
			int principal = 1;
		}
		public static interface Enum_Lis_ParametrosSis {
			int principal = 1;
		}

		
		public MensajeTransaccionBean grabaTransaccion(int tipoTransaccion, ParametrosSisBean parametrosSisBean){
			MensajeTransaccionBean mensaje = null;
			switch (tipoTransaccion) {			
				case Enum_Tra_ParametrosSis.modificacion:
					mensaje = modificaParametrosSis(parametrosSisBean);				
					break;
			}
			return mensaje;
		}
		
		
		
		public MensajeTransaccionBean modificaParametrosSis(ParametrosSisBean parametrosSisBean){
			MensajeTransaccionBean mensaje = null;
			mensaje = parametrosSisDAO.modificaParametrosSis(parametrosSisBean);		
			return mensaje;
		}
		
		
		public ParametrosSisBean consulta(int tipoConsulta,ParametrosSisBean parametrosSisB){
			System.out.println("Consulta en Parametros del Sistema Servicio "+ tipoConsulta);
			ParametrosSisBean parametrosSisBean = null;
			switch (tipoConsulta) {
				case Enum_Con_ParametrosSis.principal:		
					parametrosSisBean = parametrosSisDAO.consultaPrincipal(parametrosSisB, Enum_Con_ParametrosSis.principal);				
					break;				
			}
				
			return parametrosSisBean;
		}
		
		
		
		public List lista(int tipoLista, ParametrosSisBean parametrosSisBean){		
			List listaParametrosSis = null;
			switch (tipoLista) {
				case Enum_Lis_ParametrosSis.principal:		
					listaParametrosSis = parametrosSisDAO.listaPrincipal(parametrosSisBean, tipoLista);				
					break;				
			}		
			return listaParametrosSis;
		}	
		
		
		//------------------ Getters y Setters ------------------------------------------------------	
		public void setParametrosSisDAO(ParametrosSisDAO parametrosSisDAO) {
			this.parametrosSisDAO = parametrosSisDAO;
		}

		public ParametrosSisDAO getParametrosSisDAO() {
			return parametrosSisDAO;
		}
				
}
