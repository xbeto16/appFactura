package soporte.servicio;

import java.util.List;

import general.bean.MensajeTransaccionBean;
import general.servicio.BaseServicio;
import soporte.bean.EmisorBean;
import soporte.dao.EmisorDAO;

public class EmisorServicio extends BaseServicio{
	
	EmisorDAO emisorDAO = null;
	
	public EmisorServicio(){
		super();
	}
	
	public static interface Enum_Tra_Emisor{
		int alta = 1;
		int modifica = 2;
		int elimina= 3;	
	}	
	public static interface Enum_Con_Emisor{
		int principal = 1;
		int foranea = 2;
	}
	public static interface Enum_Lis_Emisor{
		int lisEmisor = 2;
	}
	public MensajeTransaccionBean grabaTransaccion(int tipoTransaccion, EmisorBean emisorBean){
		MensajeTransaccionBean mensaje = null;
		switch (tipoTransaccion) {
			case Enum_Tra_Emisor.alta:		
				mensaje = altaEmisor(emisorBean);				
				break;				
			case Enum_Tra_Emisor.modifica:
				mensaje = modificaEmisor(emisorBean);				
				break;
		}
		return mensaje;
	}
		
	public MensajeTransaccionBean altaEmisor(EmisorBean emisor){
		MensajeTransaccionBean mensaje = null;
		mensaje = emisorDAO.altaEmisor(emisor);		
		return mensaje;
	}
	public MensajeTransaccionBean modificaEmisor(EmisorBean emisor){
		MensajeTransaccionBean mensaje = null;
		mensaje = emisorDAO.modificaEmisor(emisor);		
		return mensaje;
	}
	
	public EmisorBean consulta(int tipoConsulta, EmisorBean emisorBean){
		EmisorBean emisor= null;
		switch (tipoConsulta) {
			case Enum_Con_Emisor.principal:
				emisor = emisorDAO.consultaEmisor(Enum_Con_Emisor.principal, emisorBean);				
				break;
		}
		return emisor;
	}
	
	public Object[] listaCombo(int tipoLista){
		List listaEmisor = null;
		switch (tipoLista) {
			case Enum_Lis_Emisor.lisEmisor:
				listaEmisor=  emisorDAO.listaCombo(tipoLista);
				break;
		}
		return listaEmisor.toArray();
	}

	public EmisorDAO getEmisorDAO() {
		return emisorDAO;
	}

	public void setEmisorDAO(EmisorDAO emisorDAO) {
		this.emisorDAO = emisorDAO;
	}

}
