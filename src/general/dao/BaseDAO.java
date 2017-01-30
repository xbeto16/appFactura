package general.dao;

import javax.sql.DataSource;

import general.bean.ParametrosAuditoriaBean;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import soporte.bean.UsuarioBean;

public class BaseDAO {

	protected JdbcTemplate jdbcTemplate;	
	protected ParametrosAuditoriaBean parametrosAuditoriaBean = null;
	protected UsuarioBean usuarioBean = null;
	protected TransaccionDAO transaccionDAO = null;
	protected TransactionTemplate transactionTemplate;
	protected final Logger loggerSAFI = Logger.getLogger("SAFI");

	//------------------ Geters y Seters ------------------------------------------------------
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);		
	}

	public void setParametrosAuditoriaBean(
			ParametrosAuditoriaBean parametrosAuditoriaBean) {
		this.parametrosAuditoriaBean = parametrosAuditoriaBean;
	}

	public void setTransaccionDAO(TransaccionDAO transaccionDAO) {
		this.transaccionDAO = transaccionDAO;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ParametrosAuditoriaBean getParametrosAuditoriaBean() {
		return parametrosAuditoriaBean;
	}
	
	
}