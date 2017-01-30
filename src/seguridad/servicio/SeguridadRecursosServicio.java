package seguridad.servicio;

import herramientas.Constantes;
import herramientas.Utileria;

import java.util.List;

import seguridad.bean.RolesPorRecursoBean;
import soporte.bean.OpcionesRolBean;
import soporte.bean.RolesBean;
import soporte.servicio.OpcionesRolServicio;
import soporte.servicio.RolesServicio;

public class SeguridadRecursosServicio {
	
	private RolesPorRecursoBean rolesPorRecursoBean;
	private RolesServicio rolesServicio;
	private OpcionesRolServicio opcionesRolServicio;
	
	public void consultaRolesPorRecurso(){
		String listaRolesStr = new String();
		String listaRolesyAnonimoStr = new String();
		List listaRecursoAsegurado;
							 		
		listaRolesStr = consultaRoles();		
		listaRolesyAnonimoStr = "ANONYMOUS," + listaRolesStr;
		Utileria.escribelog("SeguridadRecursosServicio.Lista Roles: " +  listaRolesyAnonimoStr);
		
		//Establecemos los Roles en el Esquema de Seguridad
		rolesPorRecursoBean.setListaRoles(listaRolesStr);
		
		//Recursos De Acceso General
		//A los que no se Da Acceso en la Plataforma, cuando se asignan las opciones al Perfil
		//Es decir no son opciones o recursos del Negocio
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/cerrarSessionUsuarios.htm", listaRolesyAnonimoStr); //Al dar click a Salir del Sistema
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/invalidaSession.htm", listaRolesyAnonimoStr); //TimeOut del Servidor
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/sesionExpirada.htm", listaRolesyAnonimoStr); //Session Expirada por Time Out Cliente
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/sesionExpiradaConcurrente.htm", listaRolesyAnonimoStr); //Session Expirada por Doble Login
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/accesoDenegado.htm", listaRolesyAnonimoStr); //Acceso No Autorizado
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/menuAplicacion.htm", listaRolesStr);  //Es el menu de la Aplicacion
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/consultaSession.htm", listaRolesStr);  //Es para Consulta de la Session por AJAX
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/olvidoUsuario.htm", listaRolesyAnonimoStr); //Recurso en Olvido de Clave
		
		//Utilizados en la Pantalla de Login, cuando el usuario todavia no ha ingresado a la Aplicacion
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/dwr/engine.js", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/dwr/util.js", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/dwr/call/plaincall/__System.generateId.dwr", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/dwr/interface/usuarioServicio.js", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/dwr/call/plaincall/usuarioServicio.consulta.dwr", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/dwr/call/plaincall/__System.pageLoaded.dwr", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/js/jquery-1.5.1.min.js", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/js/jquery.inlineFieldLabel.js", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/js/slide.js", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/js/forma.js", listaRolesyAnonimoStr);
		rolesPorRecursoBean.getRolesPorRecursoMapa().put("/js/soporte/entrada.js", listaRolesyAnonimoStr);
												
		//Consulta de los Recursos Asegurados por la Aplicacion
		OpcionesRolBean opcionesRolBean = new OpcionesRolBean();
		opcionesRolBean.setRolID(Constantes.STRING_CERO);
		
		listaRecursoAsegurado = opcionesRolServicio.consultaRolesPorOpcion(
										OpcionesRolServicio.Enum_Con_OpRol.accesosPorRol, opcionesRolBean);
		
		for(int i=0; i<listaRecursoAsegurado.size(); i++){
			opcionesRolBean = (OpcionesRolBean)listaRecursoAsegurado.get(i);
			rolesPorRecursoBean.getRolesPorRecursoMapa().put(opcionesRolBean.getRecurso(), opcionesRolBean.getRolID());
			Utileria.escribelog("Recurso: " +  opcionesRolBean.getRecurso() + " Roles: " + opcionesRolBean.getRolID());
		}
		
		Utileria.escribelog("SeguridadRecursosServicio. Consulto Roles por Recurso");
	}
	
	// ---------------- METODOS DE APOYO ----------------------------
	
	//Consulta los Roles en la BD y devuelve un String separado por comas con los Roles o Perfiles de Usuario Disponibles
	private String consultaRoles(){
		String listaRolesStr = Constantes.STRING_VACIO;
		List listaRoles;
		RolesBean rolBean = new RolesBean();
		rolBean.setNombreRol(Constantes.STRING_VACIO);
		listaRoles = rolesServicio.lista(RolesServicio.Enum_Lis_Roles.todosRoles, rolBean);		

		if (listaRoles!= null){
			for(int i=0; i<listaRoles.size(); i++){
				rolBean = (RolesBean)listaRoles.get(i);
				
				if(i == listaRoles.size() -1){
					listaRolesStr = listaRolesStr + rolBean.getNombreRol();
				}else{
					listaRolesStr = listaRolesStr + rolBean.getNombreRol() + ",";
				}
			}
		}
		
		return listaRolesStr;
		
	}
		
	
	// Getters y Setters
	public void setRolesPorRecursoBean(RolesPorRecursoBean rolesPorRecursoBean) {
		this.rolesPorRecursoBean = rolesPorRecursoBean;
	}

	public void setRolesServicio(RolesServicio rolesServicio) {
		this.rolesServicio = rolesServicio;
	}

	public void setOpcionesRolServicio(OpcionesRolServicio opcionesRolServicio) {
		this.opcionesRolServicio = opcionesRolServicio;
	}
	
	
}
