package general.dao;

import herramientas.Utileria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import soporte.bean.UsuarioBean;

public class TransaccionDAO extends BaseDAO{

	public TransaccionDAO() {
		super();
	}
	
	// ------------------ Transacciones ------------------------------------------
	
	public void generaNumeroTransaccion(){
		Long numeroTransaccion;
		
		numeroTransaccion = (Long)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				long transaccion = 0;
				try {					
					//Query con el Store Procedure
					String query = "call TRANSACCIONESALT();";					
					Object[] parametros = {};
					
					List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
								long transaccion = resultSet.getLong(1);					
								return transaccion;
				
						}
					});					
					transaccion = matches.size() > 0 ? ((Long) matches.get(0)).longValue() : 0; 
				} catch (Exception e) {
					e.printStackTrace();
					transaction.setRollbackOnly();
				}
				return transaccion;
			}
		});
		parametrosAuditoriaBean.setNumeroTransaccion(numeroTransaccion.longValue());
		
	}

	public  long generaNumeroTransaccionOut(){
		long numeroTransaccion;
		
		numeroTransaccion = (Long)transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus transaction) {
				long transaccion = 0;
				try {					
					//Query con el Store Procedure
					String query = "call TRANSACCIONESALT();";					
					Object[] parametros = {};
					
					List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
						public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
								long transaccion = resultSet.getLong(1);					
								return transaccion;
				
						}
					});					
					transaccion = matches.size() > 0 ? ((Long) matches.get(0)).longValue() : 0; 
				} catch (Exception e) {
					e.printStackTrace();
					transaction.setRollbackOnly();
				}
				return transaccion;
			}
		});
		return numeroTransaccion;
		
		
	}
	//------------------ Geters y Seters ------------------------------------------------------
	
	
}
