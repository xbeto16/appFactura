 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>	
		<script type="text/javascript" src="dwr/interface/rolesServicio.js"></script>   
 	   <script type="text/javascript" src="js/soporte/rolesCatalogo.js"></script>  

</head>
<body>

<div id="contenedorForma">
<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="roles">
<fieldset class="ui-widget ui-widget-content ui-corner-all">		
<legend class="ui-widget ui-widget-header ui-corner-all">Roles</legend>	
<table border="0" cellpadding="0" cellspacing="0" width="950px">
<tr>
      <td class="label"> 
         <label for="rolID">Número: </label> 
     </td>
     <td>
			<form:input id="rolID" name="rolID" path="rolID" size="10" tabindex="1" iniforma="false" /> 
     </td> 
		<td class="separador"></td>
     <td class="label"> 
         <label for="nombre">Nombre: </label> 
     </td> 
     <td> 
        <form:input id="nombreRol" name="nombreRol" path="nombreRol" size="35" tabindex="2" /> 
     </td> 
 </tr> 
 <tr> 
     <td class="label"> 
          <label for="descripcion">Descripci&oacute;n: </label> 
     </td> 
     <td> 
       <form:input id="descripcion" name="descripcion" path="descripcion" tabindex="3" size="35"/>
     </td> 
  </tr>  
 </table>
  </fieldset>
		<table align="right">
					<tr>
						<td align="right">
							<input type="submit" id="agrega" name="agrega" class="submit" 
							 value="Agrega"/>
							<input type="submit" id="modifica" name="modifica" class="submit" 
							 value="Modifica"/>
							<input type="hidden" id="tipoTransaccion" name="tipoTransaccion"/>
						</td>
					</tr>
				</table>	 
</form:form> 

</div> 
<div id="cargando" style="display: none;">	
</div>
<div id="cajaLista" style="display: none;">
	<div id="elementoLista"/>
</div>
</body>
<div id="mensaje" style="display: none;position:absolute; z-index:999;"/ -->
</html>

  

