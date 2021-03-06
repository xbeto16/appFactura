<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>
        <script type="text/javascript" src="dwr/interface/usuarioServicio.js"></script>  
        <script type="text/javascript" src="js/soporte/cancelaUsuario.js"></script>                    
       
		
	</head>
<body>
<div id="contenedorForma">
<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="cancelaUsuario">
<fieldset class="ui-widget ui-widget-content ui-corner-all">		
<legend class="ui-widget ui-widget-header ui-corner-all">Cancelaci&oacute;n de Usuarios</legend>
	
<table cellpadding="0" cellspacing="0" border="0" width="950px">
	<tr>
		<td class="label">
			<label for="usuarioID">Número: </label>
		</td>
		<td >
			<form:input id="usuarioID" name="usuarioID" path="usuarioID" size="7" tabindex="1" />
		</td>
		<td class="separador"></td>
		<td class="label">
			<label for="nombre">Nombre:</label>
		</td>
		<td>
			<form:input id="nombreCompleto" name="nombreCompleto" path="nombreCompleto" size="40" tabindex="2" 
			onBlur=" ponerMayusculas(this)" readOnly="true"/>
		</td>		
	</tr>

	<tr>
		<td class="label">
			<label for="clave">Clave: </label>
		</td>
		<td >
			<form:input id="clave" name="clave" path="clave" tabindex="5" readOnly="true"/>		
		</td>
		<td class="separador"></td>
		<td class="label">
			<label for="contrasenia">Contrase&ntilde;a:</label>
		</td>
		<td>
			<form:password id="contrasenia" name="contrasenia" path="contrasenia" size="20" tabindex="6" readOnly="true"/>
		</td>		
	</tr>
	

	<tr>
		<td class="label">
			<label for="fechUltAcces">Fecha de Último Acceso:</label>
		</td>
		<td>
			<form:input id="fechUltAcces" name="fechUltAcces" path="fechUltAcces" size="20" tabindex="13" 
			disabled="true" readOnly="true"/>
		</td>	
		<td class="separador"></td>	
		<td class="label">
			<label for="fechUltPass">Fecha del Último Password: </label>
		</td>
		<td >
			<form:input id="fechUltPass" name="fechUltPass" path="fechUltPass" size="20" tabindex="14" 
			disabled="true" readOnly="true"/>  
		</td>
	</tr>
	<tr>
		<td class="label">
			<label for="estatus">Estatus:</label>
		</td>
		<td>	
			<form:select id="estatus" name="estatus" path="estatus" tabindex="6" disabled="true">
				<form:option value="A">Activo</form:option> 
			   <form:option value="B">Bloqueado</form:option>
				<form:option value="C">Cancelado</form:option>
			</form:select>
		</td>	
		<td class="separador"></td>	
		<td class="label">
			<label for="fechaBloq">Fecha de Cancelaci&oacute;n: </label>
		</td>
		<td >
			<form:input id="fechaCancel" name="fechaCancel" path="fechaCancel" size="20" tabindex="14" 
			readOnly="true"/>  
		</td>			
	</tr>

	<tr> 
		<td class="label">
			<label for="motivo">Motivo de Cancelaci&oacute;n: </label>
		</td>
		<td>
			<form:textarea id="motivoCancel" name="motivoCancel" path="motivoCancel" cols="35" rows="3" 
			tabindex="14" />   
			
		</td>
		
	</tr>
</table>
	 <table align="right">
				<tr>
					<td align="right">
						<input type="submit" id="cancelar" name="cancelar" class="submit" value="Actualizar"/>
						<input type="hidden" id="tipoTransaccion" name="tipoTransaccion"/>
					</td>
				</tr>
			</table>		
	
	

</fieldset>
</form:form>

  
</div>
<div id="cargando" style="display: none;">	
</div>
<div id="cajaLista" style="display: none;">
	<div id="elementoLista"/>
</div>
</body>
<div id="mensaje" style="display: none;"/>
</html>