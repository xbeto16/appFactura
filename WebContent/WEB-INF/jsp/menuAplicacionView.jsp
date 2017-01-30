<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>PROYECTO</title>
	<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="css/template.css" media="screen,print"  />
	<link rel="stylesheet" type="text/css" href="css/menuTree.css" media="screen,print"  />
	<link rel="stylesheet" type="text/css" href="css/redmond/jquery-ui-1.8.13.custom.css"  />

 	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
 	<script type="text/javascript" src="js/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.13.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type='text/javascript' src='js/jquery.formatCurrency-1.4.0.min.js'></script>
 	<script type="text/javascript" src="js/jquery.maskedinput-1.3.min.js" ></script>
	<script type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	 
	<link rel="stylesheet" type="text/css" href="css/forma.css" media="all"  >
			
	<script type='text/javascript' src='js/jquery.hoverIntent.minified.js'></script>
	<script type="text/javascript" src="js/jquery.plugin.menuTree.js"></script>
	<script type="text/javascript" src="js/jquery.plugin.tracer.js"></script>
				
 	<script type="text/javascript" src="dwr/engine.js"></script>
 	<script type="text/javascript" src="dwr/util.js"></script>
 	<script type="text/javascript" src="dwr/interface/usuarioServicio.js"></script>
 	<script type="text/javascript" src="js/forma.js"></script>
 	<script type="text/javascript" src="js/general.js"></script>
 		
	<script type="text/javascript" src="js/soporte/menuAplicacion.js"></script>


</head>

<c:set var="parametrosBean"  value="${	listaResultado[0]}"/>
<c:set var="listaMenu" value="${listaResultado[1]}"/>


<body  style="background: #FFF; margin: 0pt;">
	<div id="PrincipalHeather" style= "margin-left: auto; margin-right: auto; height:75px; border: 1; align="center">
		<table border="0" width="100%"  cellspacing="0" height="75px" >
			<tr>
				<td width="180px" height="75px">
					<!--img src="images/LogoPrincipal.png" alt=""/ -->
				</td>
				<td class="separador"></td> 
				<td valign="middle">
					<div id="Credenciales">
						<table border="0" height="75px" >
	      				<tr align="left" valign="bottom">
							<td class="label" nowrap="nowrap" >
								<label>Usuario: </label>
							</td>
							<td class="etiqueta"  nowrap="nowrap">
								<input type="hidden" id="nuClave" name="nuClave"/>
								<label>${parametrosBean.nombreUsuario}</label>
							</td>
							<td class="separador"></td> 
							<td class="separador"></td> 
							<td class="separador"></td> 
						</tr>  
						<!--tr valign="middle">
							<td class="label"  nowrap="nowrap">
								<label>Sucursal: </label>
							</td>
							<td class="etiqueta"  nowrap="nowrap">
								<label>${parametrosBean.nombreSucursal}</label>
							</td> 
							<td class="separador"></td> 
							<td class="separador"></td> 
							<td class="separador"></td> 
						</tr-->
						</table>
					</div>
				</td>
							
				<td align="right" valign="middle" >
					<div id="Credenciales2">
						<table border="0" height="75px" >
							<tr valign="top">
								<td class="label" nowrap="nowrap" valign="bottom">
									<!--label>Fecha Sistema: </label-->
								</td>
								<td class="etiqueta" nowrap="nowrap" valign="bottom">
									<label>${parametrosBean.fechaSucursal}</label>
								</td>	
								<td class="label" align="right" valign="middle" nowrap="nowrap" rowspan="2" >
									<a id="ligaSalida" href="javascript:" onclick="cerrarSession();">
									<!--img src="/CRMSim/images/salida_peq.gif"/-->Salir</a>
									</a>
								</td>				
													
							</tr>
							<tr>
								<td class="separador"></td>
								<td class="separador"></td>								 
							</tr>
						</table>
				   </div>
				</td>
				<td width="180px" height="75px" align="right">
					
				</td>
			</tr>
		</table>
	</div>
	<div id="divPrincipalAplicacion" class="divPrincipalAplicacion">
		<div id="divPrincipalMenu" class="divPrincipalMenu">
			<ul id="listaMenu" >
				<c:forEach items="${listaMenu.menus}" var="menu" >
					<li><a href="javascript:">&nbsp;${menu.desplegado}</a>
					<c:choose>
						<c:when test="${empty listaMenu.gruposMenu[fn:trim(menu.numero)]}">
							</li>
						</c:when>
						<c:otherwise>
      					<ul>
	      			</c:otherwise>
	  				</c:choose>

					<c:forEach items="${listaMenu.gruposMenu[fn:trim(menu.numero)]}" var="grupo" >
						<li ><a href="javascript:">&nbsp;${grupo.desplegado}</a>
						<c:choose>
							<c:when test="${empty listaMenu.opcionesMenu[fn:trim(grupo.numero)]}">
								</li>
							</c:when>
							<c:otherwise>
	      					<ul>
		      			</c:otherwise>
		  				</c:choose>
		  	
						<c:forEach items="${listaMenu.opcionesMenu[fn:trim(grupo.numero)]}" var="opcion" >
							<li ><a id ="opcionM" href="javascript: " onclick="$('#Contenedor').load('${opcion.recurso}');consultaSesion();">
								<img src="images/opcion.gif"/>
								${opcion.desplegado}
								</a>
							</li>
						</c:forEach>
			
						<c:choose>
							<c:when test="${empty listaMenu.opcionesMenu[fn:trim(grupo.numero)]}">
							</c:when>
							<c:otherwise>
	      					</ul>
								</li>
		      			</c:otherwise>
		  				</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${empty listaMenu.gruposMenu[fn:trim(menu.numero)]}">
						</c:when>
						<c:otherwise>
      					</ul>
							</li>
	      			</c:otherwise>
	  				</c:choose>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div id="Contenedor">		
   </div>


<c:if test="${parametrosBean.cambioPassword == 'S'}">
	<script type="text/javascript"> 
	
	  $.blockUI({ 
	          message: $('#Contenedor').load('cambioContraseniaUsuarios.htm?user='+${parametrosBean.numeroUsuario}) , 
	          css: { top: '10%', left: '5%', right: '' } 
							        }); 
	</script> 
</c:if> 
	
 </body>
</html>