package cliente.dao;
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
import cliente.bean.ClienteBean;

public class ClienteDAO extends BaseDAO{
	
	public ClienteDAO(){
		super();
	}
	
	ParametrosSesionBean parametrosSesionBean;
	
	/* Alta del Cliente */
	public MensajeTransaccionBean altaCliente(final ClienteBean cliente) {
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
									String query = "call CLIENTESALT(" +
										"?,?,?,?,?, ?,?,?,?,?," +
										"?,?,?,?,?,?);";
									CallableStatement sentenciaStore = arg0.prepareCall(query);

									sentenciaStore.setString("Par_TipoPersona",cliente.getTipoPersona());
									sentenciaStore.setString("Par_RazonSocial",cliente.getRazonSocial());
									sentenciaStore.setString("Par_RFC",cliente.getRFC());
									sentenciaStore.setString("Par_Calle",cliente.getCalle());
									sentenciaStore.setString("Par_NumInt",cliente.getNumInt());

									sentenciaStore.setString("Par_NumExt",cliente.getNumExt());
									sentenciaStore.setString("Par_Colonia",cliente.getColonia());
									sentenciaStore.setString("Par_Localidad",cliente.getLocalidad());
									sentenciaStore.setString("Par_Municipio",cliente.getMunicipio());
									sentenciaStore.setString("Par_Estado",cliente.getEstado());
									
									sentenciaStore.setString("Par_CodigoPostal",cliente.getCodigoPostal());
									sentenciaStore.setString("Par_Telefono",cliente.getTelefono());
									sentenciaStore.setString("Par_Celular",cliente.getCelular());
									sentenciaStore.setString("Par_Correo",cliente.getCorreo());
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
										mensajeTransaccion.setDescripcion(Constantes.MSG_ERROR + " .FacturaDAO.altaCliente");
										mensajeTransaccion.setNombreControl(Constantes.STRING_VACIO);
										mensajeTransaccion.setConsecutivoString(Constantes.STRING_VACIO);
									}
									return mensajeTransaccion;
								}
							});
						if(mensajeBean ==  null){
							mensajeBean = new MensajeTransaccionBean();
							mensajeBean.setNumero(999);
							throw new Exception(Constantes.MSG_ERROR + " .ClientesDAO.altaCliente");
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
	public MensajeTransaccionBean modificaCliente(final ClienteBean cliente) {
		MensajeTransaccionBean mensaje = new MensajeTransaccionBean();
		transaccionDAO.generaNumeroTransaccion();
		mensaje = (MensajeTransaccionBean)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				MensajeTransaccionBean mensajeBean = new MensajeTransaccionBean();
				try {
					//Query cons el Store Procedure
					String query = "call CLIENTESMOD(" +
							"?,?,?,?,?, ?,?,?,?,?," +
							"?,?,?,?,?);";
					Object[] parametros = {								
								Utileria.convierteEntero(cliente.getClienteID()),
								cliente.getTipoPersona(),
								cliente.getRazonSocial(),
								cliente.getRFC(),
								cliente.getCalle(),
								
								cliente.getNumInt(),
								cliente.getNumExt(),
								cliente.getColonia(),
								cliente.getLocalidad(),
								cliente.getMunicipio(),
								
								cliente.getEstado(),
								cliente.getCodigoPostal(),
								cliente.getTelefono(),
								cliente.getCelular(),
								cliente.getCorreo(),
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
	public ClienteBean consultaCliente(int tipoConsulta, ClienteBean cliente) {
	ClienteBean clienteBean = null;
	try{
		//Query con el Store Procedure
		String query = "call CLIENTESCON(?,?);";			
		Object[] parametros = {	cliente.getClienteID(),
								tipoConsulta,
							};
		
		List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							ClienteBean cliente = new ClienteBean();
							cliente.setClienteID(resultSet.getString(1));
							cliente.setTipoPersona(resultSet.getString(2));
							cliente.setRazonSocial(resultSet.getString(3));
							cliente.setRFC(resultSet.getString(4));
							cliente.setCalle(resultSet.getString(5));
							cliente.setNumInt(resultSet.getString(6));
							cliente.setNumExt(resultSet.getString(7));
							cliente.setColonia(resultSet.getString(8));
							cliente.setLocalidad(resultSet.getString(9));
							cliente.setMunicipio(resultSet.getString(10));
							cliente.setEstado(resultSet.getString(11));
							cliente.setCodigoPostal(resultSet.getString(12));
							cliente.setTelefono(resultSet.getString(13));
							cliente.setCelular(resultSet.getString(14));
							cliente.setCorreo(resultSet.getString(15));
						return cliente;
					}
		});
				
		clienteBean= matches.size() > 0 ? (ClienteBean) matches.get(0) : null;
	}catch(Exception e){
		e.printStackTrace();
	}
	return clienteBean;		
	}
	
	public List listaCombo(int tipoLista) {
		//Query con el Store Procedure
		String query = "call CLIENTESLIS(?,?);";
		Object[] parametros = {	Constantes.ENTERO_CERO,
								tipoLista
							};
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				ClienteBean cliente = new ClienteBean();
				cliente.setClienteID(String.valueOf(resultSet.getInt(1)));
				cliente.setRazonSocial(resultSet.getString(2));
				return cliente;
			}
		});
		return matches;
	}
	
}
