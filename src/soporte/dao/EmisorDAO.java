package soporte.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import general.bean.MensajeTransaccionBean;
import general.bean.ParametrosSesionBean;
import general.dao.BaseDAO;
import herramientas.Constantes;
import herramientas.Utileria;
import soporte.bean.EmisorBean;

public class EmisorDAO extends BaseDAO{
	
	public EmisorDAO(){
		super();
	}
	ParametrosSesionBean parametrosSesionBean;
	
	
	/* Alta del Cliente */
	public MensajeTransaccionBean altaEmisor(final EmisorBean emisor) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean) transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();						
				try {
					// Query con el Store Procedure
					mensajeBean = (MensajeTransaccionBean) jdbcTemplate.execute(
						new CallableStatementCreator() {
							public CallableStatement createCallableStatement(Connection arg0) throws SQLException {
									String query = "call EMISORALT(" +
										"?,?,?,?,?, ?,?,?,?,?," +
										"?,?,?,?,?, ?,?);";
									CallableStatement sentenciaStore = arg0.prepareCall(query);

									sentenciaStore.setString("Par_TipoPersona",emisor.getTipoPersona());
									sentenciaStore.setString("Par_RazonSocial",emisor.getRazonSocial());
									sentenciaStore.setString("Par_RFC",emisor.getRFC());
									sentenciaStore.setString("Par_Calle",emisor.getCalle());
									sentenciaStore.setString("Par_NumInt",emisor.getNumInt());

									sentenciaStore.setString("Par_NumExt",emisor.getNumExt());
									sentenciaStore.setString("Par_Colonia",emisor.getColonia());
									sentenciaStore.setString("Par_Localidad",emisor.getLocalidad());
									sentenciaStore.setString("Par_Municipio",emisor.getMunicipio());
									sentenciaStore.setString("Par_Estado",emisor.getEstado());
									
									sentenciaStore.setString("Par_CodigoPostal",emisor.getCodigoPostal());
									sentenciaStore.setString("Par_Telefono",emisor.getTelefono());
									sentenciaStore.setString("Par_Celular",emisor.getCelular());
									sentenciaStore.setString("Par_Correo",emisor.getCorreo());
									sentenciaStore.setString("Par_Estatus",emisor.getEstatus());
									//Parametros de OutPut
									sentenciaStore.registerOutParameter("Par_NumErr", Types.INTEGER);
									sentenciaStore.registerOutParameter("Par_ErrMen", Types.VARCHAR);
									loggerSAFI.info(sentenciaStore.toString());
									return sentenciaStore;
								}
							},new CallableStatementCallback() {
								public Object doInCallableStatement(CallableStatement callableStatement) throws SQLException,DataAccessException {
									MensajeTransaccionBean mensajeTransaccion = new MensajeTransaccionBean();
									if(callableStatement.execute()){
										ResultSet resultadosStore = callableStatement.getResultSet();
										resultadosStore.beforeFirst();
										resultadosStore.next();
										mensajeTransaccion.setNumero(Integer.valueOf(resultadosStore.getString(1)).intValue());
										mensajeTransaccion.setDescripcion(resultadosStore.getString(2));
										mensajeTransaccion.setNombreControl(resultadosStore.getString(3));
										mensajeTransaccion.setConsecutivoString(resultadosStore.getString(4));
									}else{
										mensajeTransaccion.setNumero(999);
										mensajeTransaccion.setDescripcion(Constantes.MSG_ERROR + " .EmisorDAO.altaEmisor");
										mensajeTransaccion.setNombreControl(Constantes.STRING_VACIO);
										mensajeTransaccion.setConsecutivoString(Constantes.STRING_VACIO);
									}
									return mensajeTransaccion;
								}
							});
						if(mensajeBean ==  null){
							mensajeBean = new MensajeTransaccionBean();
							mensajeBean.setNumero(999);
							throw new Exception(Constantes.MSG_ERROR + " .EmisorDAO.altaEmisor");
						}else if(mensajeBean.getNumero()!=0){
							throw new Exception(mensajeBean.getDescripcion());
						}		
					} catch (Exception e) {
						
						if (mensajeBean.getNumero() == 0) {
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
			
	/* Modificacion del Cliente */
	public MensajeTransaccionBean modificaEmisor(final EmisorBean emisor) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query cons el Store Procedure
					String query = "call EMISORMOD(" +
							"?,?,?,?,?, ?,?,?,?,?," +
							"?,?,?,?,?, ?);";
					Object[] parametros = {								
								Utileria.convierteEntero(emisor.getEmisorID()),
								emisor.getTipoPersona(),
								emisor.getRazonSocial(),
								emisor.getRFC(),
								emisor.getCalle(),
								
								emisor.getNumInt(),
								emisor.getNumExt(),
								emisor.getColonia(),
								emisor.getLocalidad(),
								emisor.getMunicipio(),
								
								emisor.getEstado(),
								emisor.getCodigoPostal(),
								emisor.getTelefono(),
								emisor.getCelular(),
								emisor.getCorreo(),
								
								emisor.getEstatus()
							};
					
					List matches=jdbcTemplate.query(query,parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							MensajeTransaccionBean mensaje = new MensajeTransaccionBean();			
							mensaje.setNumero(Integer.valueOf(resultSet.getString(1)).intValue());
							mensaje.setDescripcion(resultSet.getString(2));
							mensaje.setNombreControl(resultSet.getString(3));
							mensaje.setConsecutivoString(resultSet.getString(4));
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
	
	/* Consuta Cliente por Llave Principal*/
	public EmisorBean consultaEmisor(int tipoConsulta, EmisorBean emisor) {
		EmisorBean emisorBean = null;
		try{
			//Query con el Store Procedure
			String query = "call EMISORCON(?,?);";			
			Object[] parametros = {	
					emisor.getEmisorID(),
					tipoConsulta,
			};
			
			List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
				public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					EmisorBean emisor = new EmisorBean();
					emisor.setEmisorID(resultSet.getString(1));
					emisor.setTipoPersona(resultSet.getString(2));
					emisor.setRazonSocial(resultSet.getString(3));
					emisor.setRFC(resultSet.getString(4));
					emisor.setCalle(resultSet.getString(5));
					emisor.setNumInt(resultSet.getString(6));
					emisor.setNumExt(resultSet.getString(7));
					emisor.setColonia(resultSet.getString(8));
					emisor.setLocalidad(resultSet.getString(9));
					emisor.setMunicipio(resultSet.getString(10));
					emisor.setEstado(resultSet.getString(11));
					emisor.setCodigoPostal(resultSet.getString(12));
					emisor.setTelefono(resultSet.getString(13));
					emisor.setCelular(resultSet.getString(14));
					emisor.setCorreo(resultSet.getString(15));
					emisor.setEstatus(resultSet.getString(16));
					return emisor;
				}
		});
				
		emisorBean= matches.size() > 0 ? (EmisorBean) matches.get(0) : null;
	}catch(Exception e){
		e.printStackTrace();
	}
		return emisorBean;		
	}
	
	
	public List listaCombo(int tipoLista) {
		//Query con el Store Procedure
		String query = "call EMISORLIS(?,?);";
		Object[] parametros = {	Constantes.ENTERO_CERO,
								tipoLista
							};		
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				EmisorBean emisor= new EmisorBean();
				emisor.setEmisorID(String.valueOf(resultSet.getInt(1)));
				emisor.setRazonSocial(resultSet.getString(2));
				return emisor;
			}
		});
		return matches;
	}
	
}
