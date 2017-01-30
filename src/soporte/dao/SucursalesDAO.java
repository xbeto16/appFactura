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

import soporte.bean.SucursalesBean;

public class SucursalesDAO extends BaseDAO{

	
	public SucursalesDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
// ------------------ Transacciones ------------------------------------------

	/* Alta de Sucursales */
	public MensajeTransaccionBean altaSucursal(final SucursalesBean sucursal) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query con el Store Procedure
					String query = "call SUCURSALESALT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {	Utileria.convierteEntero(sucursal.getSucursalID()),
											sucursal.getNombreSucurs(),
											sucursal.getTipoSucursal(),
											sucursal.getIVA(),
											sucursal.getTasaISR(),
											sucursal.getNombreGerente(),
											sucursal.getSubGerente(),
											sucursal.getEstadoID(),
											sucursal.getMunicipioID(),
											sucursal.getCalle(),
											sucursal.getNumero(),
											sucursal.getColonia(),
											sucursal.getCP(),
											sucursal.getTelefono(),
											sucursal.getDifHorarMatriz(),
											Utileria.convierteFecha(sucursal.getFechaSucursal()),
											sucursal.getDirecCompleta(),
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"SucursalesDAO.altaSucursal",
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

	/* Modificacion de la sucursal */
	public MensajeTransaccionBean modificaSucursal(final SucursalesBean sucursal) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query cons el Store Procedure
					String query = "call SUCURSALESMOD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					Object[] parametros = {	Utileria.convierteEntero(sucursal.getSucursalID()),
											sucursal.getNombreSucurs(),
											sucursal.getTipoSucursal(),
											sucursal.getIVA(),
											sucursal.getTasaISR(),
											sucursal.getNombreGerente(),
											sucursal.getSubGerente(),
											sucursal.getEstadoID(),
											sucursal.getMunicipioID(),
											sucursal.getCalle(),
											sucursal.getNumero(),
											sucursal.getColonia(),
											sucursal.getCP(),
											sucursal.getTelefono(),
											sucursal.getDifHorarMatriz(),
											sucursal.getDirecCompleta(),
											parametrosAuditoriaBean.getEmpresaID(),
											parametrosAuditoriaBean.getUsuario(),
											parametrosAuditoriaBean.getFecha(),
											parametrosAuditoriaBean.getDireccionIP(),
											"SucursalesDAO.modificaSucursal",
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
	
	
	//consulta de sucursales 
	public SucursalesBean consultaPrincipal(int sucursalID, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call SUCURSALESCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	sucursalID,
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"SucursalesDAO.consultaPrincipal",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
			
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				SucursalesBean sucursales = new SucursalesBean();
				sucursales.setSucursalID(Utileria.completaCerosIzquierda(
						resultSet.getInt(1),SucursalesBean.LONGITUD_ID));
				sucursales.setNombreSucurs(resultSet.getString(2));					
				sucursales.setTipoSucursal(resultSet.getString(3));
				sucursales.setIVA(String.valueOf(resultSet.getFloat(4)));
				sucursales.setTasaISR(String.valueOf(resultSet.getFloat(5)));
				sucursales.setNombreGerente(String.valueOf(resultSet.getInt(6)));
				sucursales.setSubGerente(String.valueOf(resultSet.getInt(7)));
				sucursales.setEstadoID(String.valueOf(resultSet.getInt(8)));
				sucursales.setMunicipioID(String.valueOf(resultSet.getInt(9)));
				sucursales.setCalle(resultSet.getString(10));
				sucursales.setNumero(resultSet.getString(11));
				sucursales.setColonia(resultSet.getString(12));
				sucursales.setCP(resultSet.getString(13));
				sucursales.setTelefono(resultSet.getString(14));
				sucursales.setDifHorarMatriz(String.valueOf(resultSet.getInt(15)));
				sucursales.setDirecCompleta(resultSet.getString(16));
				sucursales.setFechaSucursal(resultSet.getString(17));
					
				return sucursales;
	
			}
		});
		return matches.size() > 0 ? (SucursalesBean) matches.get(0) : null;
				
	}
		
	public SucursalesBean consultaForanea(int sucursalID, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call SUCURSALESCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	sucursalID,
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"SucursalesDAO.consultaForanea",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		SucursalesBean sucursales = new SucursalesBean();		
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				SucursalesBean sucursales = new SucursalesBean();
				sucursales.setSucursalID(Utileria.completaCerosIzquierda(
						resultSet.getInt(1),SucursalesBean.LONGITUD_ID));
				sucursales.setNombreSucurs(resultSet.getString(2));					
						
					return sucursales;
	
			}
		});
		return matches.size() > 0 ? (SucursalesBean) matches.get(0) : null;
				
	}
	
	public SucursalesBean consulaNombreSuc(SucursalesBean sucursal, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call SUCURSALESCON(?,?,?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	Constantes.ENTERO_CERO,
								tipoConsulta,
								Constantes.ENTERO_CERO,
								sucursal.getNombreSucurs(),
								sucursal.getSucursalID(),
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"SucursalesDAO.consultaNombreSuc",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		SucursalesBean sucursales = new SucursalesBean();		
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				SucursalesBean sucursales = new SucursalesBean();
				sucursales.setNombreSucurs(resultSet.getString(1));					
					return sucursales;
			}
		});
		return matches.size() > 0 ? (SucursalesBean) matches.get(0) : null;
	}
	
	//Lista de sucursales	
	public List listaSucursales(SucursalesBean sucursales, int tipoLista) {
		//Query con el Store Procedure
		String query = "call SUCURSALESLIS(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	sucursales.getNombreSucurs(),
								tipoLista,
								parametrosAuditoriaBean.getEmpresaID(),
								parametrosAuditoriaBean.getUsuario(),
								parametrosAuditoriaBean.getFecha(),
								parametrosAuditoriaBean.getDireccionIP(),
								"SucursalesDAO.listaSucursales",
								parametrosAuditoriaBean.getSucursal(),
								Constantes.ENTERO_CERO};
										
		
		
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				SucursalesBean sucursales = new SucursalesBean();			
				sucursales.setSucursalID(String.valueOf(resultSet.getInt(1)));
				sucursales.setNombreSucurs(resultSet.getString(2));
				return sucursales;				
			}
		});
				
		return matches;
	}

	//Lista de sucursales para Combo Box	
	public List listaCombo(SucursalesBean sucursales, int tipoLista) {
		//Query con el Store Procedure
		String query = "call SUCURSALESLIS(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	Constantes.STRING_VACIO,
								tipoLista,
								
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"SucursalesDAO.listaCombo",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
		
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				SucursalesBean sucursales = new SucursalesBean();			
				sucursales.setSucursalID(String.valueOf(resultSet.getInt(1)));
				sucursales.setNombreSucurs(resultSet.getString(2));
				return sucursales;				
			}
		});
				
		return matches;
	}
	

}
