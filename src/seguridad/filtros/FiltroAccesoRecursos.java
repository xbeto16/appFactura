package seguridad.filtros;

import herramientas.Utileria;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import seguridad.bean.RolesPorRecursoBean;

public class FiltroAccesoRecursos implements FilterInvocationSecurityMetadataSource {

	private RolesPorRecursoBean rolesPorRecursoBean; 
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {		
		return null;
	}

	
	public Collection<ConfigAttribute> getAttributes(Object arg0) throws IllegalArgumentException {		
		FilterInvocation filterInvocation = (FilterInvocation) arg0;
		String url = filterInvocation.getRequestUrl();		
		String rolesString = null;
		Utileria.escribelog("FiltroAccesoRecursos. Filtro Acceso, URL Solicitada: " + url);
		
		if(rolesPorRecursoBean.getRolesPorRecursoMapa().get(url)!=null){
			rolesString = (String)rolesPorRecursoBean.getRolesPorRecursoMapa().get(url);			
		}
		
		if(rolesString == null || rolesString.equalsIgnoreCase("")){			
			if (url.indexOf("/dwr/")>-1 || url.indexOf("/js/")>-1){				
				rolesString = rolesPorRecursoBean.getListaRoles();
			}else{
				rolesString = "ANONYMOUS," + rolesPorRecursoBean.getListaRoles(); 
			}
		}				
		Utileria.escribelog("FiltroAccesoRecursos. Roles del URL: " + url + " : " + rolesString);				
		return SecurityConfig.createListFromCommaDelimitedString(rolesString);
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	public void setRolesPorRecursoBean(RolesPorRecursoBean rolesPorRecursoBean) {
		this.rolesPorRecursoBean = rolesPorRecursoBean;
	}
	

}
