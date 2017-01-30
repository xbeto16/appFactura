package soporte.dao;

import general.dao.BaseDAO;
import herramientas.Constantes;
import herramientas.Utileria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import soporte.bean.GrupoMenuBean;
import soporte.bean.MenuAplicacionBean;
import soporte.bean.MenuBean;
import soporte.bean.MenuCompletoBean;
import soporte.bean.OpcionMenuBean;
import soporte.bean.UsuarioBean;

public class MenuDAO extends BaseDAO{

	
	public MenuDAO() {
		super();
	}
	
	// ------------------ Transacciones ------------------------------------------
	
	/* Consuta Menu por Llave Principal*/
	public MenuBean  consultaPrincipal(MenuBean menu, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call MENUSAPLICACIONCON(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	Utileria.convierteEntero(menu.getNumero()),
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"MenuDAO.consultaPrincipal",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					MenuBean menu = new MenuBean();
					menu.setNumero(String.valueOf(resultSet.getInt(1)));
					menu.setDescripcion(resultSet.getString(2));
					menu.setDesplegado(resultSet.getString(3));
					menu.setOrden(resultSet.getInt(4));
		
					return menu;
	
			}
		});
				
		return matches.size() > 0 ? (MenuBean) matches.get(0) : null;
	}
	
	
	/* Consuta Menu por Llave Foranea*/
	public MenuBean consultaForanea(MenuBean menu, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call MENUSAPLICACIONCON(?,?,?,?,?,?,?,?,?);";
		
		Object[] parametros = {	Utileria.convierteEntero(menu.getNumero()),
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"MenuDAO.consultaForanea",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				MenuBean menu = new MenuBean();
				menu.setNumero(String.valueOf(resultSet.getInt(1)));
				menu.setDesplegado(resultSet.getString(2));
				return menu;		
	
			}
		});
				
		return matches.size() > 0 ? (MenuBean) matches.get(0) : null;
	}
	
	
	/* Consuta Opciones del Menu por Rol o Perfil del Usuario */
	
	public MenuAplicacionBean consultaPorRol(UsuarioBean usuarioBean, int tipoConsulta) {
		//Query con el Store Procedure
		
		String query = "call OPCIONESMENUCON(?,?,?,?,?,?,?,?,?,?,?);";
		
		Object[] parametros = {	Constantes.ENTERO_CERO,
								usuarioBean.getClave(),
								Constantes.ENTERO_CERO,
								tipoConsulta,	
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"MenuDAO.consultaPorRol",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		List listaMenu=jdbcTemplate.query(query, parametros  ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					MenuCompletoBean menuCompletoBean = new MenuCompletoBean();
					
					menuCompletoBean.setMenuID(String.valueOf(resultSet.getInt(1)));
					menuCompletoBean.setDesplegadoMenu(resultSet.getString(2));
					menuCompletoBean.setGrupoID(String.valueOf(resultSet.getInt(3)));					
					menuCompletoBean.setDesplegadoGrupo(resultSet.getString(4));
					menuCompletoBean.setOpcionMenuID(String.valueOf(resultSet.getInt(5)));
					menuCompletoBean.setDesplegadoOpcion(resultSet.getString(6));
					menuCompletoBean.setRecurso(resultSet.getString(7));
				
					return menuCompletoBean;
	
			}
		});
		MenuAplicacionBean menuAplicacionBean = new MenuAplicacionBean();
		MenuCompletoBean menuCompleto;
		MenuCompletoBean menuCompletoAnterior = new MenuCompletoBean();
		
		ArrayList menuList = new ArrayList();
		ArrayList grupoList = new ArrayList();
		ArrayList opcionList = new ArrayList();
		HashMap mapaGrupos = new HashMap();
		HashMap mapaOpciones = new HashMap();
		
		MenuBean menu = new MenuBean();
		GrupoMenuBean grupo = new GrupoMenuBean();
		OpcionMenuBean opcion = new OpcionMenuBean();
			
		for(int i=0; i < listaMenu.size(); i++){
			menuCompleto = (MenuCompletoBean)listaMenu.get(i);
			if(i==0){
				menu.setNumero(menuCompleto.getMenuID());	
				menu.setDesplegado(menuCompleto.getDesplegadoMenu());
				menuList.add(menu);
				
				grupo.setNumero(menuCompleto.getGrupoID());
				grupo.setDesplegado(menuCompleto.getDesplegadoGrupo());
				grupoList.add(grupo);

				
				opcion.setNumero(menuCompleto.getOpcionMenuID());
				opcion.setDesplegado(menuCompleto.getDesplegadoOpcion());
				opcion.setRecurso(menuCompleto.getRecurso());				
				opcionList.add(opcion);

				
			}else if (i>0 && i!=listaMenu.size()){
				if (menuCompletoAnterior.getGrupoID().equalsIgnoreCase(
													menuCompleto.getGrupoID())){
					opcion = new OpcionMenuBean();
					opcion.setNumero(menuCompleto.getOpcionMenuID());
					opcion.setDesplegado(menuCompleto.getDesplegadoOpcion());
					opcion.setRecurso(menuCompleto.getRecurso());				
					opcionList.add(opcion);
				}else{
					
					if (menuCompletoAnterior.getMenuID().equalsIgnoreCase(
							menuCompleto.getMenuID())){
											
						
						opcionList = new ArrayList();
						opcion = new OpcionMenuBean();
						opcion.setNumero(menuCompleto.getOpcionMenuID());
						opcion.setDesplegado(menuCompleto.getDesplegadoOpcion());
						opcion.setRecurso(menuCompleto.getRecurso());				
						opcionList.add(opcion);
						
						grupo = new GrupoMenuBean();
						grupo.setNumero(menuCompleto.getGrupoID());
						grupo.setDesplegado(menuCompleto.getDesplegadoGrupo());
						grupoList.add(grupo);
					}else{						
						menu = new MenuBean();
						menu.setNumero(menuCompleto.getMenuID());
						menu.setDesplegado(menuCompleto.getDesplegadoMenu());
						menuList.add(menu);
						
						
						grupoList = new ArrayList();
						grupo = new GrupoMenuBean();
						grupo.setNumero(menuCompleto.getGrupoID());
						grupo.setDesplegado(menuCompleto.getDesplegadoGrupo());
						grupoList.add(grupo);
						
						opcionList = new ArrayList();
						opcion = new OpcionMenuBean();
						opcion.setNumero(menuCompleto.getOpcionMenuID());
						opcion.setDesplegado(menuCompleto.getDesplegadoOpcion());
						opcion.setRecurso(menuCompleto.getRecurso());				
						opcionList.add(opcion);
						
						}
				}
					
			 }

			menuCompletoAnterior = menuCompleto;
			mapaGrupos.put(menuCompletoAnterior.getMenuID(), grupoList);
			mapaOpciones.put(menuCompletoAnterior.getGrupoID(), opcionList);

		}		
		menuAplicacionBean.setMenus(menuList);
		menuAplicacionBean.setGruposMenu(mapaGrupos);
		menuAplicacionBean.setOpcionesMenu(mapaOpciones);		
		
		return menuAplicacionBean;
	}
	
	/* Lista de  menus */
	public List listaPrincipal(MenuBean menu, int tipoLista) {
		//Query con el Store Procedure
		String query = "call MENUSAPLICACIONLIS(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	
								menu.getDesplegado(),
								tipoLista,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"MenuDAO.listaPrincipal",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
						
		List matches=jdbcTemplate.query(query,parametros,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					MenuBean menu = new MenuBean();
					menu.setNumero(String.valueOf(resultSet.getInt(1)));
					menu.setDesplegado(resultSet.getString(2));
			    	
				return menu;			
			}
		});
				
		return matches;
	}	

	//Nueva instalacion a kubo 1.1
}