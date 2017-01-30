package soporte.servicio;
import general.bean.MensajeTransaccionBean;
import general.servicio.BaseServicio;
import herramientas.Utileria;

import java.util.List;

import soporte.bean.SucursalesBean;
import soporte.dao.SucursalesDAO;




public class SucursalesServicio extends BaseServicio {

		//---------- Variables ------------------------------------------------------------------------
		SucursalesDAO sucursalesDAO = null;

		//---------- Tipod de Consulta ----------------------------------------------------------------
		public static interface Enum_Con_Sucursal{
			int principal = 1;
			int foranea = 2;
			int porCuentasAho = 3;
					
		}

		public static interface Enum_Lis_Sucursal {
			int principal = 1;
			int combo = 2;
			int foranea=4;
		}
		public static interface Enum_Tra_Sucursal {
			int alta = 1;
			int modificacion = 2;

		}

		
		public SucursalesServicio () {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public MensajeTransaccionBean grabaTransaccion(int tipoTransaccion, SucursalesBean sucursal){
			MensajeTransaccionBean mensaje = null;
			switch (tipoTransaccion) {
				case Enum_Tra_Sucursal.alta:		
					mensaje = altaSucursal(sucursal);				
					break;				
				case Enum_Tra_Sucursal.modificacion:
					mensaje = modificaSucursal(sucursal);
					break;
				
			}
			return mensaje;
		}
		
		public MensajeTransaccionBean altaSucursal(SucursalesBean sucursal){
			MensajeTransaccionBean mensaje = null;
			mensaje = sucursalesDAO.altaSucursal(sucursal);		
			return mensaje;
		}

		public MensajeTransaccionBean modificaSucursal(SucursalesBean sucursal){
			MensajeTransaccionBean mensaje = null;
			mensaje = sucursalesDAO.modificaSucursal(sucursal);		
			return mensaje;
		}	
		
		
		
		public SucursalesBean consultaSucursal(int tipoConsulta, String numSucursal){
			SucursalesBean sucursales = null;
			switch (tipoConsulta) {
				case Enum_Con_Sucursal.principal:		
					sucursales = sucursalesDAO.consultaPrincipal(Integer.parseInt(numSucursal), tipoConsulta);				
					break;				
				case Enum_Con_Sucursal.foranea:
					sucursales = sucursalesDAO.consultaForanea(Integer.parseInt(numSucursal), tipoConsulta);
					break;
				
			}
			if(sucursales!=null){
				sucursales.setSucursalID(Utileria.completaCerosIzquierda(sucursales.getSucursalID(),SucursalesBean.LONGITUD_ID));
			}
			
			return sucursales;
		}
		
		public SucursalesBean consulta(SucursalesBean sucursal, int tipoConsulta){
			SucursalesBean sucursales = null;
			switch (tipoConsulta) {
				case Enum_Con_Sucursal.porCuentasAho:
					sucursales = sucursalesDAO.consulaNombreSuc(sucursal, tipoConsulta);
				     break;	
			}
			return sucursales;
		}
		
		
		public List lista(int tipoLista, SucursalesBean sucursales){		
			List listaSucursales = null;
			switch (tipoLista) {
				case Enum_Lis_Sucursal.principal:		
					listaSucursales=  sucursalesDAO.listaSucursales(sucursales,tipoLista);				
					break;			
				case Enum_Lis_Sucursal.foranea:		
					listaSucursales=  sucursalesDAO.listaSucursales(sucursales,tipoLista);				
					break;	
			}		
			return listaSucursales;
		}

		public Object[] listaCombo(int tipoLista, SucursalesBean sucursales){
			List listaSucursales = null;
			switch (tipoLista) {
				case Enum_Lis_Sucursal.combo:		
					listaSucursales=  sucursalesDAO.listaCombo(sucursales,tipoLista);				
					break;				
			}		
			return listaSucursales.toArray();
		}

		//------------------ Geters y Seters ------------------------------------------------------	
		public void setSucursalesDAO(SucursalesDAO sucursalesDAO) {
			this.sucursalesDAO = sucursalesDAO;
		}	
		

	}
