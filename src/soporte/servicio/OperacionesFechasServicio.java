package soporte.servicio;

import general.servicio.BaseServicio;
import soporte.bean.OperacionesFechasBean;
import soporte.dao.OperacionesFechasDAO;

public class OperacionesFechasServicio extends BaseServicio {

	//---------- Variables ------------------------------------------------------------------------
	OperacionesFechasDAO operacionesFechasDAO = null;			   
	
	
	//---------- Tipo de Consulta ----------------------------------------------------------------
	public static interface Enum_Operaciones_Fechas {
		int sumaDias = 1;
		int restaFechas = 2;
	}
		
	public OperacionesFechasServicio() {
		super();
	}

	//---------- Transacciones (Calculos) ---------------------------------------------------------
	
	public OperacionesFechasBean realizaOperacion(OperacionesFechasBean operacionesFechasBean,
												 int tipoCalculo){
		OperacionesFechasBean operacionesFechas = null;
		
		switch (tipoCalculo) {
			case Enum_Operaciones_Fechas.sumaDias:	
				operacionesFechas = operacionesFechasDAO.sumaDias(operacionesFechasBean, tipoCalculo);
				break;				
			case Enum_Operaciones_Fechas.restaFechas:
				operacionesFechas = operacionesFechasDAO.restaFechas(operacionesFechasBean, tipoCalculo);				
				break;
		}
		return operacionesFechas;
	}
	
	//---------- Setters ---------------------------------------------------------------------
	public void setOperacionesFechasDAO(OperacionesFechasDAO operacionesFechasDAO) {
		this.operacionesFechasDAO = operacionesFechasDAO;
	}
	

	
}
