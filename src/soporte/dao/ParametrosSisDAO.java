package soporte.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;


import general.bean.MensajeTransaccionBean;
import general.dao.BaseDAO;
import herramientas.Constantes;
import herramientas.Utileria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


import soporte.bean.ParametrosSisBean;

public class ParametrosSisDAO extends BaseDAO {

	public ParametrosSisDAO(){
		super();
	}
	

				

/* Modificacion de parametros del sistema */
public MensajeTransaccionBean modificaParametrosSis(final ParametrosSisBean parametrosSisBean) {
	MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
	transaccionDAO.generaNumeroTransaccion();
	mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
		public Object doInTransaction(TransactionStatus transaction) {
			MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
			try {				
				// Query con el Store Procedure
//				mensajeBean = jdbcTemplate.execute(
//					new CallableStatementCreator() {
//						public CallableStatement createCallableStatement(Connection arg0) throws SQLException {
//					String query = "call PARAMETROSSISMOD( ?,?,?,?,?  ,?,?,?,?,?  ,?,?,? );";
//							CallableStatement sentenciaStore = arg0.prepareCall(query);
//							sentenciaStore.setInt	("Par_EmpresaID",Utileria.convierteEntero(parametrosSisBean.getEmpresaID()));
//
//							sentenciaStore.setInt("Par_DiasCambioPass",Utileria.convierteEntero(parametrosSisBean.getDiasCambioPass()));							
//							sentenciaStore.setString("Par_LonMinCaracPass",parametrosSisBean.getLonMinCaracPass());
//							sentenciaStore.setString("Par_FechaSistema",Utileria.convierteFecha(parametrosSisBean.getFechaSistema()));
//
//							sentenciaStore.setString("Par_Salida",Constantes.salidaSI);
//							sentenciaStore.registerOutParameter("Par_NumErr", Types.CHAR);
//							sentenciaStore.registerOutParameter("Par_ErrMen", Types.VARCHAR);
//							
//							sentenciaStore.setInt("Aud_Usuario",parametrosAuditoriaBean.getUsuario());
//							sentenciaStore.setDate("Aud_FechaActual",parametrosAuditoriaBean.getFecha());
//							sentenciaStore.setString("Aud_DireccionIP",parametrosAuditoriaBean.getDireccionIP());
//							sentenciaStore.setString("Aud_ProgramaID",parametrosAuditoriaBean.getNombrePrograma());
//							sentenciaStore.setInt("Aud_Sucursal",parametrosAuditoriaBean.getSucursal());
//							sentenciaStore.setLong("Aud_NumTransaccion",parametrosAuditoriaBean.getNumeroTransaccion());
//							
//							return sentenciaStore;
//						} //public sql exception
//					} // new CallableStatementCreator
//					,new CallableStatementCallback() {
//						public Object doInCallableStatement(CallableStatement callableStatement) throws SQLException,
//																										DataAccessException {
//							MensajeTransaccionBean mensajeTransaccion = new MensajeTransaccionBean();
//							if(callableStatement.execute()){																		
//								ResultSet resultadosStore = callableStatement.getResultSet();
//								resultadosStore.beforeFirst();
//								resultadosStore.next();
//								mensajeTransaccion.setNumero(Integer.valueOf(resultadosStore.getString("NumErr")).intValue());
//								mensajeTransaccion.setDescripcion(resultadosStore.getString("ErrMen"));
//								mensajeTransaccion.setNombreControl(resultadosStore.getString("control"));  
//								mensajeTransaccion.setConsecutivoString(resultadosStore.getString("consecutivo"));
//							}else{
//								mensajeTransaccion.setNumero(999);
//								mensajeTransaccion.setDescripcion("Fallo. El Procedimiento no Regreso Ningun Resultado.");
//							}																						
//							return mensajeTransaccion;
//						}// public
//					}// CallableStatementCallback
//					);
				if(mensajeBean ==  null){
					mensajeBean = new MensajeTransaccionBean();
					mensajeBean.setNumero(999);
					throw new Exception("Fallo. El Procedimiento no Regreso Ningun Resultado.");
				}else if(mensajeBean.getNumero()!=0){
					throw new Exception(mensajeBean.getDescripcion());
				}		
			} catch (Exception e) {				
				if (mensajeBean.getNumero() == 0) {
					mensajeBean.setNumero(999);
				}
				e.printStackTrace();
			mensajeBean.setDescripcion(e.getMessage());
			transaction.setRollbackOnly();
			}
			return mensajeBean;
		}
	});
	return mensaje;
}


/* Consuta por Llave Principal */
public ParametrosSisBean consultaPrincipal(ParametrosSisBean parametrosSis,int tipoConsulta) {
	ParametrosSisBean parametrosSisBean= new ParametrosSisBean();
	try{
		//Query con el Store Procedure
		String query = "call PARAMETROSSISCON(?,?);";

		Object[] parametros = {
								Constantes.STRING_VACIO,
								tipoConsulta,
								 };//numTransaccion

		List matches = jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum)
					throws SQLException {
				ParametrosSisBean parametrosSisBean = new ParametrosSisBean();
				parametrosSisBean.setEmpresaID(resultSet.getString("EmpresaID"));
				parametrosSisBean.setFechaSistema(resultSet.getString("FechaSistema"));
				parametrosSisBean.setSucursalMatrizID(resultSet.getString("SucursalMatrizID"));
				parametrosSisBean.setTelefonoLocal(resultSet.getString("TelefonoLocal"));
				parametrosSisBean.setTelefonoInterior(resultSet.getString("TelefonoInterior"));
				parametrosSisBean.setInstitucionID(resultSet.getString("InstitucionID"));
				parametrosSisBean.setEmpresaDefault(resultSet.getString("EmpresaDefault"));
				
				parametrosSisBean.setNombreRepresentante(resultSet.getString("NombreRepresentante"));
				parametrosSisBean.setRFCRepresentante(resultSet.getString("RFCRepresentante"));
				parametrosSisBean.setMonedaBaseID(resultSet.getString("MonedaBaseID"));
				parametrosSisBean.setMonedaExtrangeraID(resultSet.getString("MonedaExtrangeraID"));
				
				parametrosSisBean.setTasaISR(resultSet.getString("TasaISR"));
				parametrosSisBean.setTasaIDE(resultSet.getString("TasaIDE"));
				parametrosSisBean.setMontoExcIDE(resultSet.getString("MontoExcIDE"));
				
				parametrosSisBean.setEjercicioVigente(resultSet.getString("EjercicioVigente"));
				parametrosSisBean.setPeriodoVigente(resultSet.getString("PeriodoVigente"));
				parametrosSisBean.setDiasInversion(resultSet.getString("DiasInversion"));
				parametrosSisBean.setDiasCredito(resultSet.getString("DiasCredito"));
				parametrosSisBean.setDiasCambioPass(resultSet.getString("DiasCambioPass"));
				
				parametrosSisBean.setLonMinCaracPass(resultSet.getString("LonMinCaracPass"));
				parametrosSisBean.setClienteInstitucion(resultSet.getString("ClienteInstitucion"));
				parametrosSisBean.setCuentaInstituc(resultSet.getString("CuentaInstituc"));
				
				parametrosSisBean.setManejaCaptacion(resultSet.getString("ManejaCaptacion"));
				parametrosSisBean.setBancoCaptacion(resultSet.getString("BancoCaptacion"));
				parametrosSisBean.setRutaRPTEdoCta(resultSet.getString("RutaRPTEdoCta"));
				parametrosSisBean.setRutaPDFEdoCta(resultSet.getString("RutaPDFEdoCta"));
				
				parametrosSisBean.setTipoCuenta(resultSet.getString("TipoCuenta"));
				parametrosSisBean.setRutaArchivos(resultSet.getString("RutaArchivos"));
				parametrosSisBean.setRolTesoreria(resultSet.getString("RolTesoreria"));
				parametrosSisBean.setRolAdminTeso(resultSet.getString("RolAdminTeso"));
				
			
								
				return parametrosSisBean;

			}// trows ecexeption
		});//lista

		parametrosSisBean= matches.size() > 0 ? (ParametrosSisBean) matches.get(0) : null;
	}catch(Exception e){
		e.printStackTrace();
	}
	return parametrosSisBean;
}// consultaPrincipal

/* Lista de parametros EmpresaID y InstitucionID */
public List listaPrincipal(ParametrosSisBean parametrosSisBean, int tipoLista) {
	//Query con el Store Procedure
	String query = "call PARAMETROSSISLIS(?,?,?,?,?,?,?,?,?);";
	Object[] parametros = {	parametrosSisBean.getEmpresaID(),
							parametrosSisBean.getNombreInstitucion(),
							tipoLista,
							Constantes.ENTERO_CERO,//aud_usuario
							Constantes.FECHA_VACIA, //fechaActual
							Constantes.STRING_VACIO,// direccionIP
							Constantes.STRING_VACIO, //programaID
							Constantes.ENTERO_CERO,// sucursal
							Constantes.ENTERO_CERO };//numTransaccion
	
	List matches=jdbcTemplate.query(query,parametros,new RowMapper() {
		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			ParametrosSisBean parametrosSisbean = new ParametrosSisBean();			
			parametrosSisbean.setEmpresaID(resultSet.getString("EmpresaID"));
			parametrosSisbean.setNombreInstitucion(resultSet.getString("Nombre"));
			return parametrosSisbean;				
		}
	});
	return matches;
}


}//class
	
	
	
	

