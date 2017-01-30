package soporte.dao;

import general.bean.MensajeTransaccionBean;
import general.dao.BaseDAO;
import herramientas.Constantes;
import herramientas.Utileria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import soporte.bean.OpcionesRolBean;
import soporte.bean.RolesBean;


public class RolesDAO extends BaseDAO{

	
	public RolesDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
// ------------------ Transacciones ------------------------------------------
	/* Alta de Rol */

	public MensajeTransaccionBean alta(final RolesBean roles) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					String query = "call ROLESALT(?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {
							roles.getNombreRol(),
							roles.getDescripcion(),
							parametrosAuditoriaBean.getEmpresaID(),
							parametrosAuditoriaBean.getUsuario(),
							parametrosAuditoriaBean.getFecha(),
							parametrosAuditoriaBean.getDireccionIP(),
							"RolesDAO.alta",
							parametrosAuditoriaBean.getSucursal(),
							parametrosAuditoriaBean.getNumeroTransaccion()};
							
			
					List matches = jdbcTemplate.query(query, parametros, new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum)
								throws SQLException {
							MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
							mensaje.setNumero(Integer.valueOf(resultSet.getString(1)).intValue());
							mensaje.setDescripcion(resultSet.getString(2));
							mensaje.setNombreControl(resultSet.getString(3));
							return mensaje;
						}
					});
			
					return matches.size() > 0 ? (MensajeTransaccionBean) matches.get(0) : null;
				}catch (Exception e) {
					if(mensajeBean.getNumero()==0){
						mensajeBean.setNumero(999);
					}
					mensajeBean.setDescripcion(e.getMessage());
					transaction.setRollbackOnly();
					}
					return mensajeBean;
				}
			});
			return mensaje;
		}
	
	

	
	
	/* Modificacion de Rol */
	public MensajeTransaccionBean modifica(final RolesBean roles){
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		
		transaccionDAO.generaNumeroTransaccion();
		
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					
					String query = "call ROLESMOD(?,?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {
							Utileria.convierteEntero(roles.getRolID()),
							roles.getNombreRol(),
							roles.getDescripcion(),
							parametrosAuditoriaBean.getEmpresaID(),
							parametrosAuditoriaBean.getUsuario(),
							parametrosAuditoriaBean.getFecha(),
							parametrosAuditoriaBean.getDireccionIP(),
							"RolesDAO.modifica",
							parametrosAuditoriaBean.getSucursal(),
							parametrosAuditoriaBean.getNumeroTransaccion()};

					List matches = jdbcTemplate.query(query, parametros, new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum)
								throws SQLException {
							MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
							mensaje.setNumero(Integer.valueOf(resultSet.getString(1)).intValue());
							mensaje.setDescripcion(resultSet.getString(2));
							mensaje.setNombreControl(resultSet.getString(3));
							return mensaje;
						}
					});
					return matches.size() > 0 ? (MensajeTransaccionBean) matches.get(0) : null;
					
				} catch (Exception e) {
					if(mensajeBean.getNumero()==0){
						mensajeBean.setNumero(999);
					}
					mensajeBean.setDescripcion(e.getMessage());
					transaction.setRollbackOnly();
				}
				return mensajeBean;
			}
		});
		return mensaje;
	}
	/* Alta de opciones de menu del Rol */

	public MensajeTransaccionBean altaOpcionRol(final RolesBean roles) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					String query = "call OPCIONESROLALT(?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {
							roles.getNombreRol(),
							roles.getDescripcion(),
							parametrosAuditoriaBean.getEmpresaID(),
							parametrosAuditoriaBean.getUsuario(),
							parametrosAuditoriaBean.getFecha(),
							parametrosAuditoriaBean.getDireccionIP(),
							"RolesDAO.alta",
							parametrosAuditoriaBean.getSucursal(),
							parametrosAuditoriaBean.getNumeroTransaccion()};
							
			
					List matches = jdbcTemplate.query(query, parametros, new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum)
								throws SQLException {
							MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
							mensaje.setNumero(Integer.valueOf(resultSet.getString(1)).intValue());
							mensaje.setDescripcion(resultSet.getString(2));
							mensaje.setNombreControl(resultSet.getString(3));
							return mensaje;
						}
					});
			
					return matches.size() > 0 ? (MensajeTransaccionBean) matches.get(0) : null;
				}catch (Exception e) {
					if(mensajeBean.getNumero()==0){
						mensajeBean.setNumero(999);
					}
					mensajeBean.setDescripcion(e.getMessage());
					transaction.setRollbackOnly();
					}
					return mensajeBean;
				}
			});
			return mensaje;
		}
	
	
	//consulta principal  de Roles
		public RolesBean consultaRoles(RolesBean rol, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call ROLESCON(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	rol.getRolID(),
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"RolesDAO.consultaRoles",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
			
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				RolesBean rol = new RolesBean();			
				rol.setRolID(Utileria.completaCerosIzquierda(resultSet.getInt(1), 3));
				rol.setNombreRol(resultSet.getString(2));
				rol.setDescripcion(resultSet.getString(3));
					
					return rol;
	
			}
		});
		return matches.size() > 0 ? (RolesBean) matches.get(0) : null;
				
	}
		//consulta foranea  de Roles
		public RolesBean consultaRolesForanea(RolesBean rol, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call ROLESCON(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	rol.getRolID(),
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"RolesDAO.consultaRoles",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
			
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				RolesBean rol = new RolesBean();			
				rol.setRolID(Utileria.completaCerosIzquierda(resultSet.getInt(1), 3));
				rol.setNombreRol(resultSet.getString(2));
					return rol;
	
			}
		});
		return matches.size() > 0 ? (RolesBean) matches.get(0) : null;
				
	}
		
	
		//Lista  de opciones(pantallas) por rol
		public List listaOpcionesPorRol(OpcionesRolBean opcionesRolBean, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call OPCIONESROLCON(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	opcionesRolBean.getRolID(),
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"RolesDAO.listaOpcionesPorRol",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
			
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				OpcionesRolBean opciones = new OpcionesRolBean();			
				opciones.setOpcionMenuID(Utileria.completaCerosIzquierda(resultSet.getInt(1), 3));
				opciones.setDesplegado(resultSet.getString(2));
				opciones.setRecurso(resultSet.getString(3));
					
					return opciones;
	
			}
		});
		return matches;
				
	}
		
	
	//Lista de Roles
	public List listaRoles(RolesBean rol, int tipoLista) {
		//Query con el Store Procedure
		String query = "call ROLESLIS(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	rol.getNombreRol(),
								tipoLista,
								parametrosAuditoriaBean.getEmpresaID(),
								parametrosAuditoriaBean.getUsuario(),
								parametrosAuditoriaBean.getFecha(),
								parametrosAuditoriaBean.getDireccionIP(),
								"RolesDAO.listaRoles",
								parametrosAuditoriaBean.getSucursal(),
								Constantes.ENTERO_CERO};
		
		
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				RolesBean roles = new RolesBean();		
				roles.setRolID(Utileria.completaCerosIzquierda(resultSet.getInt(1), 3));
				roles.setNombreRol(resultSet.getString(2));			
				return roles;				
			}
		});
				
		return matches;
	}


	//Lista de Todos los Roles o Perfiles de la Aplicacion
	public List listaTodosRoles(RolesBean rol, int tipoLista) {
		//Query con el Store Procedure
		String query = "call ROLESLIS(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	rol.getNombreRol(),
								tipoLista,
								
								Constantes.ENTERO_CERO,	 //Se mandan Vacio pq a este punto no hay Session
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								"localhost",
								"RolesDAO.listaRoles",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				RolesBean roles = new RolesBean();		
				roles.setRolID(Utileria.completaCerosIzquierda(resultSet.getInt(1), 3));
				roles.setNombreRol(resultSet.getString(2));			
				return roles;				
			}
		});
				
		return matches;
	}

	
}
