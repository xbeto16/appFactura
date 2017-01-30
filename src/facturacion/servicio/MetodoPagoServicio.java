package facturacion.servicio;

import java.util.List;

import facturacion.dao.MetodoPagoDAO;
import general.servicio.BaseServicio;

public class MetodoPagoServicio extends BaseServicio{
	
	MetodoPagoDAO metodoPagoDAO = null;
	
	public static interface Enum_Lis_Metodo{
		int lisMetodoPago = 2;
	}
	public MetodoPagoServicio(){
		super();
	}

	public Object[] listaCombo(int tipoLista){
		System.out.println("Entra a Servicio -> " + tipoLista);
		List listaPago = null;
		switch (tipoLista) {
			case Enum_Lis_Metodo.lisMetodoPago:
				listaPago= metodoPagoDAO.listaCombo(tipoLista);
				break;
		}
		return listaPago.toArray();
	}

	public void setMetodoPagoDAO(MetodoPagoDAO metodoPagoDAO) {
		this.metodoPagoDAO = metodoPagoDAO;
	}

	
}
