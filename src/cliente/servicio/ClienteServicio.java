package cliente.servicio;
import java.util.List;

import cliente.bean.ClienteBean;
import cliente.dao.ClienteDAO;
import general.bean.MensajeTransaccionBean;
import general.servicio.BaseServicio;
import herramientas.SistemaLogging;

public class ClienteServicio extends BaseServicio{
	
	ClienteDAO clienteDAO = null;
	
	
	
	public static interface Enum_Tra_Cliente {
		int alta = 1;
		int modifica = 2;
		int elimina= 3;	
	}	
	public static interface Enum_Con_Cliente{
		int principal = 1;
		int foranea = 2;
		
	}
	public static interface Enum_Lis_Cliente{
		int lisCliente = 2;
	}
	
	public ClienteServicio(){
		super();
	}
	public MensajeTransaccionBean grabaTransaccion(int tipoTransaccion, ClienteBean clienteBean){
		MensajeTransaccionBean mensaje = null;
		switch (tipoTransaccion) {
			case Enum_Tra_Cliente.alta:		
				mensaje = altaCliente(clienteBean);				
				break;				
			case Enum_Tra_Cliente.modifica:
				mensaje = modificaCliente(clienteBean);				
				break;
			case Enum_Tra_Cliente.elimina:
				mensaje = eliminaCliente(clienteBean);				
				break;
		}
		return mensaje;
	}
	
	public MensajeTransaccionBean altaCliente(ClienteBean cliente){
		MensajeTransaccionBean mensaje = null;
		mensaje = clienteDAO.altaCliente(cliente);		
		return mensaje;
	}
	public MensajeTransaccionBean modificaCliente(ClienteBean cliente){
		MensajeTransaccionBean mensaje = null;
		mensaje = clienteDAO.modificaCliente(cliente);		
		return mensaje;
	}
	public MensajeTransaccionBean eliminaCliente(ClienteBean cliente){
		MensajeTransaccionBean mensaje = null;
		//mensaje = clienteDAO.eliminaCliente(cliente);		
		return mensaje;
	}

	
	public ClienteBean consulta(int tipoConsulta, ClienteBean clienteBean){
		ClienteBean cliente = null;
		switch (tipoConsulta) {
			case Enum_Con_Cliente.principal:
				cliente = clienteDAO.consultaCliente(Enum_Con_Cliente.principal, clienteBean);				
				break;
		}
		return cliente;
	}
	
	public Object[] listaCombo(int tipoLista){
		List listaReceptor = null;
		switch (tipoLista) {
			case Enum_Lis_Cliente.lisCliente:
				listaReceptor=  clienteDAO.listaCombo(tipoLista);
				break;
		}
		return listaReceptor.toArray();
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}


}