<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>

	<head>	
       	<script type="text/javascript" src="dwr/interface/parametrosSisServicio.js"></script>  
		<script type="text/javascript" src="js/soporte/parametrosSis.js"></script>   		     
	</head>
   
<body>
<div id="contenedorForma">
<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="parametrosSisBean">

<fieldset class="ui-widget ui-widget-content ui-corner-all">                
	<legend class="ui-widget ui-widget-header ui-corner-all">Parámetros del Sistema</legend>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td class="label"> 
		    	<label for="empredaID">Empresa: </label> 
			</td>
			<td>
				<form:input id="empresaID" name="empresaID" path="empresaID" size="6" tabindex="1"/> 				
			</td>
			 <td class="separador"></td> 	
			 <td class="label"> 
		    	<label for="lblFecha">Fecha: </label> 
			</td>
			<td>
				<form:input id="fechaSistema" name="fechaSistema" path="fechaSistema" size="20" tabindex="2" readOnly="true" disabled="true"/> 				
			</td>		
		</tr>
		<tr>
			<td class="label"> 
		    	<label for="diasCambioPass">Días de cambio de Password : </label> 
			</td>
			<td>
				<form:input id="diasCambioPass" name="diasCambioPass" path="diasCambioPass" size="11" tabindex="19"/>  
			</td>
			<td class="separador"></td> 
			<td class="label"> 
		    	<label for="lonMinCaracPass">Longitud Mínima de Caracteres: </label> 
		   	</td> 
		    <td> 
		     	<form:input id="lonMinCaracPass" name="lonMinCaracPass" path="lonMinCaracPass" size="6" tabindex="20"/>		     
		  	</td>
		</tr>			
	</table>
	</br>
	

		
	
</fieldset>

<table align="right">
		<tr>
			<td align="right">				
				<input type="submit" id="modificar" name="modificar" class="submit" value="Modificar" tabindex="34"/>
				<input type="hidden" id="tipoTransaccion" name="tipoTransaccion"/>						
			</td>
		</tr>
	</table>

</form:form>
</div>

<div id="cargando" style="display: none;"></div>
<div id="cajaLista" style="display: none;">
	<div id="elementoLista"></div>
</div>
</body>
<div id="mensaje" style="display: none;"></div>
</html>