<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="dwr/interface/corredorServicio.js"></script>  
        <script type="text/javascript" src="js/soporte/adminCarrera.js"></script>                    
</head>
<body>
<div id="contenedorForma">
<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="carreraBean">
<fieldset class="ui-widget ui-widget-content ui-corner-all">
<legend class="ui-widget ui-widget-header ui-corner-all">Corredores</legend>
	
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td>
			<label> Cronometro:</label>
		</td>
		<td>
		<input type="text" id="cronometro" name="cronometro" value="00:00:00:00" style="font-size: 42px"/>
		</td>
	<td>
		<input type="button" id="empezar" name="empezar" value="Empezar" onclick = "empezarDetener(this);"/>
	</td>
	</tr>
	<tr>
		<td colspan="7">
			<div id="gridCorredores" style="display: none;"/>	
		</td>	
	</tr>
	
</table>
</fieldset>
</form:form>
</div>
<div id="cargando" style="display: none;">	
</div>
<div id="cajaLista" style="display: none;">
	<div id="elementoLista"></div>
</div>
</body>
<div id="mensaje" style="display: none;"></div>
</html>