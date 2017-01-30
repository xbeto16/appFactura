package seguridad.filtros;

import herramientas.Utileria;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;


public class FiltroTimeOut implements Filter, InitializingBean {

	private String expiredUrl;

	public void destroy() {	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {		
				
		if(req instanceof HttpServletRequest){
			HttpServletRequest hReq = (HttpServletRequest)req;
			HttpServletResponse hRes = (HttpServletResponse)res;
			HttpSession session = hReq.getSession(false);
			Utileria.escribelog("FiltroTimeOut --> HttpSession: "+session + " --> Contexto: "+hReq.getContextPath() + 
								" --> Pagina Solicitada: "+hReq.getRequestURL() + " --> URI Solicitada: "+hReq.getRequestURI());
						
			if(session == null && hReq.getRequestedSessionId() != null && !hReq.isRequestedSessionIdValid()
			   && esRecursoAsegurado(hReq.getRequestURI())){
				Utileria.escribelog("FiltroTimeOut. Session Id: "+hReq.getRequestedSessionId() + " Valid Session: "+hReq.isRequestedSessionIdValid() + 
									" Session expired..Redirecting to " + expiredUrl);
				String targetUrl = hReq.getContextPath() + expiredUrl;				
				hRes.sendRedirect(hRes.encodeRedirectURL(targetUrl));
				return;
			}
			chain.doFilter(req,res);
		}
	}
	
	private boolean esRecursoAsegurado(String urlSolicitada){
		boolean esAsegurado = true;
		StringTokenizer tokensBean = new StringTokenizer(urlSolicitada, "/");
		String stringToken;
		
		while(tokensBean.hasMoreTokens()){
			stringToken = tokensBean.nextToken();
						
			if(stringToken.equalsIgnoreCase("images") ||
			   stringToken.equalsIgnoreCase("css") ||
			   stringToken.equalsIgnoreCase("js")){
				
					Utileria.escribelog("Es una Imagen, CSS, JS: "+ urlSolicitada);
					esAsegurado = false;					
					return esAsegurado;					
			}else if (stringToken.equalsIgnoreCase("")){
				Utileria.escribelog("Es la Pagina Vacia de Login: "+ urlSolicitada);
				esAsegurado = false;
				return esAsegurado;				
			}else if(stringToken.equalsIgnoreCase("entradaAplicacion.htm") ||
					   stringToken.equalsIgnoreCase("cerrarSessionUsuarios.htm") ||
					   stringToken.equalsIgnoreCase("accesoDenegado.htm")||
					   stringToken.equalsIgnoreCase("sesionExpiradaConcurrente.htm") ||
					   stringToken.equalsIgnoreCase("invalidaSession.htm")){
				Utileria.escribelog("Son las Paginas de Redireccionamiento: "+ urlSolicitada);
				esAsegurado = false;
				return esAsegurado;
			}
		}
		return esAsegurado;
	}
	
		
	public void init(FilterConfig config) throws ServletException {	}

	public void afterPropertiesSet() throws Exception {
	}

	public void setExpiredUrl(String expiredUrl) {
		this.expiredUrl = expiredUrl;
	}


}
