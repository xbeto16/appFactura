package soporte.servicio;

import general.bean.MensajeTransaccionBean;
import general.servicio.BaseServicio;

import java.util.ArrayList;
import java.util.List;

import soporte.bean.OpcionesRolBean;
import soporte.dao.OpcionesRolDAO;
import soporte.servicio.RolesServicio.Enum_Lis_Roles;

public class OpcionesRolServicio extends BaseServicio {



	//---------- Variables ------------------------------------------------------------------------
	OpcionesRolDAO opcionesRolDAO = null;

	//---------- Constructor ------------------------------------------------------------------------
	public OpcionesRolServicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static interface Enum_Tra_OpRol {
		int alta 			= 1;
		int bajaDefinitva = 1;
	}

	public static interface Enum_Con_OpRol {
		int principal		= 1;
		int accesosPorRol	= 4;
	}
	//---------- Transacciones ------------------------------------------------------------------------

	public MensajeTransaccionBean grabaListaRoles(int tipoTransaccion, OpcionesRolBean opRolBean,String cadena){

		OpcionesRolBean opcionesRolBean =null;
		OpcionesRolBean opcionesRolBeanExistentes =null;
		boolean existe,listaVacia;
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();;

		ArrayList listaOpcionesRol = (ArrayList) creaListaRoles(opRolBean);
 
		List listaOpRolAnterior = null;
		listaOpRolAnterior = opcionesRolDAO.listaOpcionesPorRol(opRolBean,Enum_Lis_Roles.opcionesRol);


		if(!listaOpRolAnterior.isEmpty()){	
			for(int i=0; i<listaOpRolAnterior.size(); i++){
				opcionesRolBean = (OpcionesRolBean)listaOpRolAnterior.get(i);
				mensaje = opcionesRolDAO.baja(opcionesRolBean,opRolBean.getRolID(),Enum_Tra_OpRol.bajaDefinitva);

			}
		}

		if(!listaOpcionesRol.isEmpty()){	
			for(int i=0; i<listaOpcionesRol.size(); i++){
				opcionesRolBean = (OpcionesRolBean)listaOpcionesRol.get(i);
				mensaje = opcionesRolDAO.alta(opcionesRolBean);

			}

		}
		

		return mensaje;		 
	}

	private List creaListaRoles(OpcionesRolBean opRol){		



		List<String> listaIDRoles = opRol.getMenuIDCheck();
		int tamanio;
		ArrayList listaRoles = new ArrayList();
		OpcionesRolBean opcionesRolBean;

		if (listaIDRoles == null){
			tamanio = 0;
		}else{			
			tamanio = listaIDRoles.size();

		}
		for(int i=0; i<tamanio; i++){
			opcionesRolBean= new OpcionesRolBean();
			if(!listaIDRoles.get(i).equals("0")){	
				opcionesRolBean.setOpcionMenuID(listaIDRoles.get(i));
				opcionesRolBean.setRolID(opRol.getRolID());
				listaRoles.add(opcionesRolBean);
			}
		}

		return listaRoles;
	}
	//Utilizada para el Esquema de Seguridad, es un listado de Recursos y los perfiles o roles
	//que tienen acceso al recurso
	public List consultaRolesPorOpcion(int tipoconsulta, OpcionesRolBean opcionesRolBean){
		List listaRolesPorRecurso = null;
		switch (tipoconsulta) {
			case Enum_Con_OpRol.accesosPorRol:		
				listaRolesPorRecurso=  opcionesRolDAO.listaRolesPorOpcion(opcionesRolBean,tipoconsulta);				
				break;
		}		
		return listaRolesPorRecurso;
	}
	
	
	//------------------ Geters y Seters ------------------------------------------------------		
	
	public void setOpcionesRolDAO(OpcionesRolDAO opcionesRolDAO) {
		this.opcionesRolDAO = opcionesRolDAO;
	}
		
}	//Nueva instalacion a kubo 1.1