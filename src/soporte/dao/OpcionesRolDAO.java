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

 public class OpcionesRolDAO extends BaseDAO{
	
	
	public OpcionesRolDAO() {
		super();
	}
	
	public MensajeTransaccionBean alta(final OpcionesRolBean opRolBean) {	
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		
		try{
			String query = "call OPCIONESROLALT(?,?, ?,?,?,?,?,?,?);";
			Object[] parametros = {
					Utileria.convierteEntero(opRolBean.getRolID()),
					opRolBean.getOpcionMenuID(),
					
					parametrosAuditoriaBean.getEmpresaID(),
					parametrosAuditoriaBean.getUsuario(),
					parametrosAuditoriaBean.getFecha(),
					parametrosAuditoriaBean.getDireccionIP(),
					"OpcionesRolDAO.alta",
					parametrosAuditoriaBean.getSucursal(),
					parametrosAuditoriaBean.getNumeroTransaccion()
					};
	
			List matches = jdbcTemplate.query(query, parametros, new RowMapper() {
				public Object mapRow(ResultSet resultSet, int rowNum)
						throws SQLException {
					MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
					mensaje.setNumero(Integer.valueOf(resultSet.getString(1)).intValue());
					mensaje.setDescripcion(resultSet.getString(2));
					mensaje.setNombreControl(resultSet.getString(3));
					mensaje.setConsecutivoString(resultSet.getString(4));
					return mensaje;
				}
			});
			mensaje = matches.size() > 0 ? (MensajeTransaccionBean) matches.get(0) : null;
			return mensaje;			
		} catch (Exception e) {
			if(mensaje.getNumero()==0){
				mensaje.setNumero(999);
			}
			mensaje.setDescripcion(e.getMessage());
		}
		return mensaje;
			
	}
	
	public MensajeTransaccionBean baja (final OpcionesRolBean opRolBean,String rolID, final int tipoBaja) {	
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		
		try{
			String query = "call OPCIONESROLBAJ(?,?,?,?,?,?,?,?,?,?);";
			Object[] parametros = {
					Utileria.convierteEntero(rolID),
					opRolBean.getOpcionMenuID(),
					tipoBaja,
					parametrosAuditoriaBean.getEmpresaID(),
					parametrosAuditoriaBean.getUsuario(),
					parametrosAuditoriaBean.getFecha(),
					parametrosAuditoriaBean.getDireccionIP(),
					"OpcionesRolDAO.baja",
					parametrosAuditoriaBean.getSucursal(),
					parametrosAuditoriaBean.getNumeroTransaccion()
					};
	
			List matches = jdbcTemplate.query(query, parametros, new RowMapper() {
				public Object mapRow(ResultSet resultSet, int rowNum)
						throws SQLException {
					MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
					mensaje.setNumero(Integer.valueOf(resultSet.getString(1)).intValue());
					mensaje.setDescripcion(resultSet.getString(2));
					mensaje.setNombreControl(resultSet.getString(3));
					mensaje.setConsecutivoString(resultSet.getString(4));
					return mensaje;
				}
			});
			mensaje = matches.size() > 0 ? (MensajeTransaccionBean) matches.get(0) : null;
			return mensaje;			
		} catch (Exception e) {
			//Nueva instalacion a kubo 1.1
			if(mensaje.getNumero()==0){
				mensaje.setNumero(999);
			}
			mensaje.setDescripcion(e.getMessage());
		}
		return mensaje;
			
	}
	
	public MensajeTransaccionBean grabaListaRoles(final OpcionesRolBean opRolBean, final List listaOpcionesRol ) {		
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					OpcionesRolBean opRolBean;
					
				//	mensajeBean = alta(opRolBean);
					
					if(mensajeBean.getNumero()!=0){
						throw new Exception(mensajeBean.getDescripcion());
					}					
					String consecutivo= mensajeBean.getConsecutivoString();
					for(int i=0; i<listaOpcionesRol.size(); i++){
						opRolBean = (OpcionesRolBean)listaOpcionesRol.get(i);
						mensajeBean = alta(opRolBean);
						if(mensajeBean.getNumero()!=0){
							throw new Exception(mensajeBean.getDescripcion());	
						}											
					}
					mensajeBean = new MensajeTransaccionBean();
					mensajeBean.setNumero(0);
					mensajeBean.setDescripcion("Opcion Agregada.");
					mensajeBean.setNombreControl("rolID");
					mensajeBean.setConsecutivoString(consecutivo);
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
				
				opciones.setOpcionMenuID(resultSet.getString(1));
				opciones.setDesplegado(resultSet.getString(2));
				opciones.setRecurso(resultSet.getString(3));
					
					return opciones;
	
			}
		});
		return matches;
					
	}
	
	//Lista  de opciones(pantallas) por rol
	public List listaRolesPorOpcion(OpcionesRolBean opcionesRolBean, int tipoConsulta) {
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
				
				opciones.setRecurso(resultSet.getString(1));
				opciones.setRolID(resultSet.getString(2));
				return opciones;
	
			}
		});
		return matches;
	}

			
}