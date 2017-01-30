	package facturacion.dao;

import general.bean.MensajeTransaccionBean;
import general.bean.ParametrosSesionBean;
import general.dao.BaseDAO;
import herramientas.Constantes;
import herramientas.Utileria;

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
import facturacion.bean.FacturaBean;

public class FacturaDAO extends BaseDAO{

	public FacturaDAO() {
		super();
	}
	
	ParametrosSesionBean parametrosSesionBean;

	
	
	public List listaConceptos(FacturaBean facturaBean, int tipoLista){
		String query = "call CONCEPTOSFACTLIS(?,?);";
		Object[] parametros = {	
			facturaBean.getNumFactura(),
			tipoLista
		};
		//loggerSAFI.info("call CUENTASPERSONALIS(" + Arrays.toString(parametros) +")");
		List matches=jdbcTemplate.query(query,parametros,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				FacturaBean facturaBean = new FacturaBean();
				facturaBean.setEmisor(String.valueOf(resultSet.getInt(1)));
				facturaBean.setCondicionPago(resultSet.getString(2));
				return facturaBean;
			}
		});
		return matches;
	}	
}