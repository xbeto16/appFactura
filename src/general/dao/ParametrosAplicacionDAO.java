package general.dao;

import general.bean.ParametrosSesionBean;
import herramientas.Utileria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import soporte.bean.UsuarioBean;

public class ParametrosAplicacionDAO {

	private JdbcTemplate jdbcTemplate;

	public ParametrosAplicacionDAO() {
		super();
	}
		
	/* Consulta Parametros de Session en el Login o Entrada */
	public ParametrosSesionBean consultaParaSession(UsuarioBean usuarioBean, int tipoConsulta){
		String query = "call PARAMETROSSISCON(?,?);";
		Object[] parametros = { usuarioBean.getClave(),
								tipoConsulta	};

		List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				ParametrosSesionBean sesionBean = new ParametrosSesionBean();
				sesionBean.setFechaAplicacion(resultSet.getDate(1));
				sesionBean.setNumeroUsuario(resultSet.getInt(2));
				sesionBean.setClaveUsuario(resultSet.getString(3));
				sesionBean.setPerfilUsuario(resultSet.getInt(4));
				sesionBean.setNombreUsuario(resultSet.getString(5));
				sesionBean.setCorreoUsuario(resultSet.getString(6));
				sesionBean.setFechUltimAcces(resultSet.getDate(7));
				sesionBean.setEstatusSesion(resultSet.getString(8));
				return sesionBean;
			}
		});
		return matches.size() > 0 ? (ParametrosSesionBean) matches.get(0) : null;
	}
	
	/* Consulta Parametros del cliente y cuenta de la institucion*/
	/* se usa en la creacion del credito*/
	public ParametrosSesionBean consultaCteCtaWS(UsuarioBean usuarioBean, int tipoConsulta){
		String query = "call PARAMETROSSISCON(?,?);";
		Object[] parametros = { usuarioBean.getClave(),
								tipoConsulta	};
		
		List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				ParametrosSesionBean sesionBean = new ParametrosSesionBean();
				sesionBean.setClienteInstitucion(resultSet.getInt(1));
				sesionBean.setCuentaInstitucion(resultSet.getInt(2));
				sesionBean.setFechaAplicacion(resultSet.getDate(3));
				return sesionBean;
			}
		});
		return matches.size() > 0 ? (ParametrosSesionBean) matches.get(0) : null;
	}
	
	
	/** Seters y Getters **/	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
}
