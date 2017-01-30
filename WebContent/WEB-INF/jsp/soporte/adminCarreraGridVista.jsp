<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html"%> 

<%@page pageEncoding="UTF-8"%>

<html>

<head>



</head>

<body>

</br>



<c:set var="listaResultado"  value="${listaResultado}"/>



<form id="gridResumenAct" name="gridResumenAct">

	<fieldset class="ui-widget ui-widget-content ui-corner-all">                

		<legend>Estadísticas</legend>	

	

			<table class="altColorFilasTabla" id="alternacolor" border="4" cellpadding="3" cellspacing="0" width="100%">

				<tbody>	

					<tr id="encabezadoGrid" >

			     		<td width="20%"> 

					   <b><center>Numero</center>	</b>

						</td>

						<td width="80%"> 

					    <b><center>Nombre</center>	</b>

						</td>

					<td>

					</td>

					</tr>

					<c:forEach items="${listaResultado}" var="resumenAct" varStatus="status">

						<tr id="renglon${status.count}" name="renglon">

						  	<td> 

						  		<input type="hidden" id="corredorID${status.count}" name="corredorID" value="${resumenAct.numeroCorredor}" />

						  		<dd> 

								${resumenAct.numeroCorredor}

								</dd>

						  	</td>

						  	<td> 

								<center>${resumenAct.nombre}</center>	

						  	</td>

					  	<td>

					  		<input type="button" id="btnActualizar${status.count}" name="btnActualizar" onclick="grabarCrono(this)" value="Actualizar" />

					  	</td>

						</tr>

					</c:forEach>

				</tbody>

			</table>

		</fieldset>

</form>

</body>

</html>