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
import soporte.bean.UsuarioBean;

public class UsuarioDAO extends BaseDAO {

	public UsuarioDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// ------------------ Transacciones ------------------------------------------

	/* Alta Usuario */
	public MensajeTransaccionBean altaUsuario(final UsuarioBean usuario) {
		
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();		
				try {
					
					//Query con el Store Procedure
					String query = "call USUARIOSALT(" +
							"?,?,?,?,?, ?,?,?,?,?," +
							"?,?,?,?,?, ?);";
					Object[] parametros = {	
											usuario.getNombre(),
											usuario.getApPaterno(),
											usuario.getApMaterno(),
											usuario.getClave(),
											usuario.getContrasenia(),
											usuario.getCorreo(),
											usuario.getSucursalUsuario(),
											usuario.getRolID(),
											usuario.getIpSesion(),
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"UsuarioDAO.altaUsuario",
											parametrosAuditoriaBean.getSucursal(),
											parametrosAuditoriaBean.getNumeroTransaccion()};
					
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
	
	
	
	/* Modifica Usuario */
	public MensajeTransaccionBean modificaUsuario(final UsuarioBean usuario)  {
		
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query con el Store Procedure
					String query = "call USUARIOSMOD(" +
							"?,?,?,?,?, ?,?,?,?,?," +
							"?,?,?,?,?, ?,?);";
					Object[] parametros = {	usuario.getUsuarioID(),
											usuario.getNombre(),
											usuario.getApPaterno(),
											usuario.getApMaterno(),
											usuario.getClave(),
											usuario.getContrasenia(),
											usuario.getCorreo(),
											usuario.getSucursalUsuario(),
											usuario.getRolID(),
											usuario.getIpSesion(),
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"UsuarioDAO.modificaUsuario",
											parametrosAuditoriaBean.getSucursal(),
											parametrosAuditoriaBean.getNumeroTransaccion()};
							
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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

	/* Actualiza el Numero de Intentos Fallidos de Login */
	public MensajeTransaccionBean actualizaIntentosFallidos(final UsuarioBean usuario, final int tipoActualizacion) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();	
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
										
					String query = "call USUARIOSACT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";					
					Object[] parametros = {	Constantes.ENTERO_CERO,
											usuario.getClave(),
											Constantes.STRING_VACIO,//estatus
											Constantes.STRING_VACIO,//motivoBloqueo
											Constantes.FECHA_VACIA,//fechabloqueo
											Constantes.STRING_VACIO,//motivoCancelacion
											Constantes.FECHA_VACIA,//fechaCancelacion
											Constantes.STRING_VACIO,//contrasenia
											tipoActualizacion,
											Constantes.ENTERO_CERO,
											Constantes.ENTERO_CERO,
											Constantes.FECHA_VACIA,
											Constantes.STRING_VACIO,
											"UsuarioDAO.actualizaIntentosFallidos",
											Constantes.ENTERO_CERO,
											transaccionDAO.generaNumeroTransaccionOut()};
											
			
							
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
										MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
										mensaje.setNumero(Integer.valueOf(resultSet.getString(1)).intValue());
										mensaje.setDescripcion(resultSet.getString(2));
										mensaje.setNombreControl(resultSet.getString(3));
										return mensaje;
				
						}
					});				
					
					if(mensajeBean.getNumero()!=0){
						throw new Exception(mensajeBean.getDescripcion());
					}						
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
	
	/* Actualiza el estatus del usuario a bloqueado o desbloqueado*/
	public MensajeTransaccionBean actBloqDesbloqueoUsuario(final UsuarioBean usuario, final int tipoActualizacion) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query con el Store Procedure
					String query = "call USUARIOSACT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {	usuario.getUsuarioID(),
											Constantes.STRING_VACIO,//clave
											usuario.getEstatus(),
											usuario.getMotivoBloqueo(),
											Utileria.convierteFecha(usuario.getFechaBloqueo()),
											Constantes.STRING_VACIO,//motivoCancelacion
											Constantes.FECHA_VACIA,//fechaCancelacion
											Constantes.STRING_VACIO,//contrasenia
											tipoActualizacion,
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"UsuarioDAO.actBloqDesbloqueoUsuario",
											parametrosAuditoriaBean.getSucursal(),
											parametrosAuditoriaBean.getNumeroTransaccion()};
							
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
	
	/* Actualiza el estatus del usuario a Cancelado*/
	public MensajeTransaccionBean actCancelaUsuario(final UsuarioBean usuario, final int tipoActualizacion) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query con el Store Procedure
					String query = "call USUARIOSACT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {	usuario.getUsuarioID(),
											Constantes.STRING_VACIO,//clave
											usuario.getEstatus(),
											Constantes.STRING_VACIO,//motivoBloqueo
											Constantes.FECHA_VACIA,//fechaBloqueo
											usuario.getMotivoCancel(),
											Utileria.convierteFecha(usuario.getFechaCancel()),
											Constantes.STRING_VACIO,//contrasenia
											tipoActualizacion,
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"UsuarioDAO.actCancelaUsuario",
											parametrosAuditoriaBean.getSucursal(),
											parametrosAuditoriaBean.getNumeroTransaccion()};
							
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
	
	/* Actualizacion: resetea password de usuario*/
	public MensajeTransaccionBean resetPasswordUsuario(final UsuarioBean usuario, final int tipoActualizacion) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
									//Query con el Store Procedure
					String query = "call USUARIOSACT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {	usuario.getUsuarioID(),
											Constantes.STRING_VACIO,//clave
											Constantes.STRING_VACIO,//Estatus
											Constantes.STRING_VACIO,//motivoBloqueo
											Constantes.FECHA_VACIA,//fechaBloqueo
											Constantes.STRING_VACIO,//MotivoCancel
											Constantes.FECHA_VACIA,//FechaCancel
											usuario.getContrasenia(),
											tipoActualizacion,
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"UsuarioDAO.resetPasswordUsuario",
											parametrosAuditoriaBean.getSucursal(),
											parametrosAuditoriaBean.getNumeroTransaccion()};
							
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							
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
	
	/* Actualiza el estatus del la sesion del usuario a Activo*/
	public MensajeTransaccionBean actStatusSesionActivoUsuario(final UsuarioBean usuario, final int tipoActualizacion) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query con el Store Procedure
					String query = "call USUARIOSACT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {	Constantes.ENTERO_CERO,// numero de usuario
											usuario.getClave(),
											Constantes.STRING_VACIO,//estatus usuario
											Constantes.STRING_VACIO,//motivoBloqueo
											Constantes.FECHA_VACIA,//fechaBloqueo
											Constantes.STRING_VACIO,// motivo cancelacion
											Constantes.FECHA_VACIA,//FechaCancel
											Constantes.STRING_VACIO,//contraseña
											tipoActualizacion,
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"UsuarioDAO.actStatusSesionActivoUsuario",
											parametrosAuditoriaBean.getSucursal(),
											parametrosAuditoriaBean.getNumeroTransaccion()};
							
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
	
	
	/* Actualiza el estatus del la sesion del usuario a Inactivo (cerra sesion)*/
	public MensajeTransaccionBean actStatusSesionInactivoUsuario(final UsuarioBean usuario, final int tipoActualizacion) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query con el Store Procedure
					String query = "call USUARIOSACT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {	Constantes.ENTERO_CERO,// numero de usuario
											usuario.getClave(),
											Constantes.STRING_VACIO,//estatus usuario
											Constantes.STRING_VACIO,//motivoBloqueo
											Constantes.FECHA_VACIA,//fechaBloqueo
											Constantes.STRING_VACIO,// motivo cancelacion
											Constantes.FECHA_VACIA,//FechaCancel
											Constantes.STRING_VACIO,//contraseña
											tipoActualizacion,
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"UsuarioDAO.actStatusSesionInactivoUsuario",
											parametrosAuditoriaBean.getSucursal(),
											parametrosAuditoriaBean.getNumeroTransaccion()};
							
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
	
	
	
	/* Consuta Usuario por Llave Principal*/
	public UsuarioBean consultaPrincipal(UsuarioBean usuarioBean, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call USUARIOSCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	usuarioBean.getUsuarioID(),
								Constantes.STRING_VACIO,//clave
								Constantes.STRING_VACIO,// contrasenia 
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"UsuarioDAO.consultaPrincipal",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				UsuarioBean usuario = new UsuarioBean();
				
							usuario.setUsuarioID(String.valueOf(resultSet.getInt(1)));
							usuario.setNombre(resultSet.getString(2));
							usuario.setApPaterno(resultSet.getString(3));
							usuario.setApMaterno(resultSet.getString(4));
							usuario.setClave(resultSet.getString(5));
							usuario.setContrasenia("");
							usuario.setRolID(String.valueOf(resultSet.getInt(7)));
							usuario.setFechUltAcces(resultSet.getString(8));
							usuario.setFechUltPass(resultSet.getString(9));
							usuario.setEstatus(resultSet.getString(10));
							usuario.setNombreCompleto(resultSet.getString(11));
							return usuario;
			}
		});
				
		return matches.size() > 0 ? (UsuarioBean) matches.get(0) : null;
	}
	
	public UsuarioBean consultaForanea(UsuarioBean usuarioBean, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call USUARIOSCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	usuarioBean.getUsuarioID(),
								Constantes.STRING_VACIO,
								Constantes.STRING_VACIO,// contrasenia 
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"UsuarioDAO.consultaForanea",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				UsuarioBean usuario = new UsuarioBean();	
					
				usuario.setUsuarioID(String.valueOf(resultSet.getInt(1)));
				usuario.setNombreCompleto(resultSet.getString(2));					
						
				return usuario;
	
			}
		});
		return matches.size() > 0 ? (UsuarioBean) matches.get(0) : null;
				
	}

	/* Consulta de Usuario: Para Pantalla de Login */
	public UsuarioBean consultaPorClave(UsuarioBean usuarioBean, int tipoConsulta) {
		//Query con el Store Procedure
		UsuarioBean usuario = null;
		
		System.out.println("Llega al DAO......");
		
		String query = "call USUARIOSCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	Constantes.ENTERO_CERO,
								usuarioBean.getClave(),
								Constantes.STRING_VACIO,// contrasenia 
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"UsuarioDAO.consultaPorClave",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		System.out.println("jdbcTemplate: " + usuarioBean.getClave());
		
		try{
			List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
				public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					UsuarioBean usuario = new UsuarioBean();				
					usuario.setUsuarioID(herramientas.Utileria.completaCerosIzquierda(
											resultSet.getInt(1), UsuarioBean.LONGITUD_ID));				
					usuario.setNombreCompleto(resultSet.getString(2));
					usuario.setClave(resultSet.getString(3));
					usuario.setContrasenia(resultSet.getString(4));
					usuario.setNombreRol(resultSet.getString(5));
					usuario.setEstatus(resultSet.getString(6));								
					usuario.setLoginsFallidos(resultSet.getInt(7));
					usuario.setEstatusSesion(resultSet.getString(8));
					usuario.setSalt(resultSet.getString(9));
					return usuario;
				}
			});
			
			usuario = matches.size() > 0 ? (UsuarioBean) matches.get(0) : null; 
			
		}catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		System.out.println("Regresa del DAO");
		
		return usuario;
				
	}
	
	/* Consulta de Usuario: Para la pantalla de Bloqueo y Desbloqueo de usuarios */
	public UsuarioBean consultaBloDesbloqueo(UsuarioBean usuarioBean, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call USUARIOSCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	usuarioBean.getUsuarioID(),
								Constantes.STRING_VACIO,
								Constantes.STRING_VACIO,// contrasenia 
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"UsuarioDAO.consultaBloDesbloqueo",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
			
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				UsuarioBean usuario = new UsuarioBean();				
				usuario.setUsuarioID(herramientas.Utileria.completaCerosIzquierda(
										resultSet.getInt(1), UsuarioBean.LONGITUD_ID));				
				usuario.setNombreCompleto(resultSet.getString(2));
				usuario.setClave(resultSet.getString(3));
				usuario.setContrasenia("");
				usuario.setFechUltAcces(resultSet.getString(4));
				usuario.setFechUltPass(resultSet.getString(5));
				usuario.setEstatus(resultSet.getString(6));
				usuario.setMotivoBloqueo(resultSet.getString(7));
				usuario.setFechaBloqueo(resultSet.getString(8));
				return usuario;
			}
		});
		return matches.size() > 0 ? (UsuarioBean) matches.get(0) : null;
				
	}
	
	/* Consulta de Usuario: Para la pantalla de Cancelacion de usuarios */
	public UsuarioBean consultaCancelaUsuario(UsuarioBean usuarioBean, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call USUARIOSCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	usuarioBean.getUsuarioID(),
								Constantes.STRING_VACIO,
								Constantes.STRING_VACIO,// contrasenia 
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"UsuarioDAO.consultaCancelaUsuario",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
			
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				UsuarioBean usuario = new UsuarioBean();				
				usuario.setUsuarioID(herramientas.Utileria.completaCerosIzquierda(
										resultSet.getInt(1), UsuarioBean.LONGITUD_ID));				
				usuario.setNombreCompleto(resultSet.getString(2));
				usuario.setClave(resultSet.getString(3));
				usuario.setContrasenia("");
				usuario.setFechUltAcces(resultSet.getString(4));
				usuario.setFechUltPass(resultSet.getString(5));
				usuario.setEstatus(resultSet.getString(6));
				usuario.setMotivoCancel(resultSet.getString(7));
				usuario.setFechaCancel(resultSet.getString(8));
				return usuario;
			}
		});
		return matches.size() > 0 ? (UsuarioBean) matches.get(0) : null;
				
	}
	/* Consulta Contrasenia de Usuario: Para la pantalla de Cambio de contrasenia*/
	public UsuarioBean consultaContraseniaUsuario(UsuarioBean usuarioBean, int tipoConsulta) {
		
		
		//Query con el Store Procedure
		String query = "call USUARIOSCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	usuarioBean.getUsuarioID(),
								Constantes.STRING_VACIO,
								usuarioBean.getContrasenia(),// contrasenia 
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"UsuarioDAO.consultaContraseniaUsuario",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				UsuarioBean usuario = new UsuarioBean();				
				usuario.setContrasenia(resultSet.getString(1));
				return usuario;
			}
		});
		return matches.size() > 0 ? (UsuarioBean) matches.get(0) : null;
				
	}

	/* Consuta Usuario*/
	public UsuarioBean consultaWS(UsuarioBean usuarioBean, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call USUARIOSCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	usuarioBean.getUsuarioID(),
								Constantes.STRING_VACIO,//clave
								Constantes.STRING_VACIO,// contrasenia 
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"UsuarioDAO.consultaWS",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				UsuarioBean usuario = new UsuarioBean();
							usuario.setSucursalUsuario(resultSet.getString(1));
							usuario.setIpSesion(resultSet.getString(2));
							usuario.setEmpresaID(resultSet.getString(3));
							usuario.setFechaSistema(resultSet.getString(4));
							return usuario;
			}
		});
			
		return matches.size() > 0 ? (UsuarioBean) matches.get(0) : null;
	}
	
	
	/* Lista de Usuarios por Nombre */
	public List listaPrincipal(UsuarioBean usuarioBean, int tipoLista) {
		//Query con el Store Procedure
		String query = "call USUARIOSLIS(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	usuarioBean.getNombreCompleto(),
								tipoLista,
								parametrosAuditoriaBean.getEmpresaID(),
								parametrosAuditoriaBean.getUsuario(),
								parametrosAuditoriaBean.getFecha(),
								parametrosAuditoriaBean.getDireccionIP(),
								"UsuarioDAO.listaPrincipal",
								parametrosAuditoriaBean.getSucursal(),
								Constantes.ENTERO_CERO};
		
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				UsuarioBean usuario = new UsuarioBean();			
				usuario.setUsuarioID(String.valueOf(resultSet.getInt(1)));
				usuario.setNombreCompleto(resultSet.getString(2));
				return usuario;				
			}
		});
				
		return matches;
	}
	
	
}
