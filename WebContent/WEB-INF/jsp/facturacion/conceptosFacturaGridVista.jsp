<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="conceptosFactLista" value="${conceptosFactLista}"/>
<div id="conceptosFactGrid">
<fieldset class="ui-widget ui-widget-content ui-corner-all">
	<legend class="ui-widget ui-widget-header ui-corner-all">Conceptos</legend>
	<input type="button" id="agregaConcepto" value="Agregar" class="botonGral" tabindex="10"/>
	<table id="miTabla"  border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td class="label"> 
					<label for="lblCantidad">Cantidad</label> 
				</td>
			  	<td class="label"> 
					<label for="lblunidad">Unidad</label> 
				</td>
				<td class="label"> 
					<label for="lblDescripcion">Descripcion</label> 
		     	</td>
				<td class="label"> 
					<label for="lblPrecio">Precio Unitario</label> 
				</td>
				<td class="label"> 
					<label for="lblImporte">Importe</label> 
				</td>
	 		</tr>
	 		<c:forEach items="${conceptosFactLista}" var="conceptosFactLista" varStatus="status">
	 		<tr id="renglon${status.count}" name="renglon">
	 			<td> 
					<input id="consecutivoID${status.count}"  name="consecutivoID" value="${status.count}" type="text" style="display:none"/> 
					<input type="text" id="cantidad${status.count}" name="cantidad" size="5" value="${conceptosFactLista.cantidad}" /> 
				</td> 
				<td> 
					<input type="text" id="unidad${status.count}" name="unidad" size="10" value="${conceptosFactLista.unidad}" /> 
				</td> 
				<td> 
					<input type="text" id="descripcion${status.count}" name="descripcion" size="20" value="${conceptosFactLista.descripcion}"/> 
				</td> 					 
				<td> 
					<input type="text" id="precio${status.count}" name="precio" size="10" value="${conceptosFactLista.precio}"/> 
				</td>
				<td> 
					<input type="text" id="importe${status.count}" name="importe" size="10" value="${conceptosFactLista.importe}"/> 
				</td>
				<td>
					<input type="button" name="elimina"   id="${status.count}"  value="" class="btnElimina" onclick="eliminaConcepto(this.id)"/>
					<input type="button" name="agrega" id="agrega${status.count}"  value="" class="btnAgrega" onclick="agregaConcepto()"/>
				</td>
	 		</tr>
	 		</c:forEach>
	 	</table>
	<input type="hidden" value="0" name="numeroFila" id="numeroFila" />
</fieldset>	
</div>
<script type="text/javascript">

	$("#numeroFila").val($('input[name=consecutivoID]').length);	
	
	$("#agregaConcepto").click(function() {
		agregaConcepto();
 	});

	function agregaConcepto(){
		var numeroFila = document.getElementById("numeroFila").value;
		var nuevaFila = parseInt(numeroFila) + 1;
		var tds = '<tr id="renglon' + nuevaFila + '" name="renglon">';
		if(numeroFila == 0){
			tds += '<td><input id="consecutivoID'+nuevaFila+'" name="consecutivoID" size="5" value="1" autocomplete="off" style="display:none"/>';
			tds += '<input id="cantidad'+nuevaFila+'" name="cantidad" size="6" autocomplete="off" /></td>';
			tds += '<td><input id="unidad'+nuevaFila+'" name="unidad" size="6" autocomplete="off" /></td>';
			tds += '<td><input id="descripcion'+nuevaFila+'" name="descripcion" size="50" onBlur="ponerMayusculas(this)" autocomplete="off" /></td>';
			tds += '<td><input id="precio'+nuevaFila+'" name="precio" size="10" autocomplete="off" /></td>';
			tds += '<td><input id="importe'+nuevaFila+'" name="importe" size="10" />';
		} else{
			var valor = parseInt(document.getElementById("consecutivoID"+numeroFila+"").value) + 1;
			tds += '<td><input id="consecutivoID'+nuevaFila+'" name="consecutivoID" size="6" value="'+valor+'" autocomplete="off" style="display:none"/>';
			tds += '<input id="cantidad'+nuevaFila+'" name="cantidad" size="6" autocomplete="off" /></td>';
			tds += '<td><input id="unidad'+nuevaFila+'" name="unidad" size="6" autocomplete="off" /></td>';
			tds += '<td><input id="descripcion'+nuevaFila+'" name="descripcion" size="50" onBlur="ponerMayusculas(this)" autocomplete="off" /></td>';
			tds += '<td><input id="precio'+nuevaFila+'" name="precio" size="10" autocomplete="off" /></td>';
			tds += '<td><input id="importe'+nuevaFila+'" name="importe" size="10" />';
    	}
		tds += '<input type="button" name="elimina" id="'+nuevaFila +'" value="" class="btnElimina" onclick="eliminaConcepto(this.id)"/>';
		tds += '<input type="button" name="agrega" id="agrega'+nuevaFila +'" value="" class="btnAgrega" onclick="agregaConcepto()"/></td>';
		tds += '</tr>';
    	document.getElementById("numeroFila").value = nuevaFila;
    	$("#miTabla").append(tds);
		return false;
	}
	
	function eliminaConcepto(control){
		var contador = 0 ;
		var numeroID = control;
		
		var jqRenglon = eval("'#renglon" + numeroID + "'");
		var jqNumero = eval("'#consecutivoID" + numeroID + "'");
		var jqCantidad = eval("'#cantidad" + numeroID + "'");		
		var jqUnidad = eval("'#unidad" + numeroID + "'");
		var jqDescripcion = eval("'#descripcion" + numeroID + "'");
		var jqPrecio = eval("'#precio" + numeroID + "'");
		var jqImporte = eval("'#importe" + numeroID + "'");
		var jqElimina = eval("'#" + numeroID + "'");
		var jqAgrega = eval("'#agrega" + numeroID + "'");
		 
		// se elimina la fila seleccionada
		$(jqNumero).remove();
		$(jqCantidad).remove();
		$(jqUnidad).remove();
		$(jqDescripcion).remove();
		$(jqPrecio).remove();
		$(jqImporte).remove();
		$(jqElimina).remove();
		$(jqRenglon).remove();
		$(jqAgrega).remove();
		// se asigna el numero de 
		var elementos = document.getElementsByName("renglon");
		$('#numeroFila').val(elementos.length);

		//Reordenamiento de Controles
		contador = 1;
		var numero= 0;
		$('tr[name=renglon]').each(function() {
			numero= this.id.substr(7,this.id.length);
			var jqRenglonCiclo = eval("'renglon" + numero+ "'");
			var jqNumeroCiclo = eval("'consecutivoID" + numero + "'");
			var jqNoCantidadCiclo = eval("'cantidad" + numero + "'");
			var jqNoUnidadCiclo = eval("'unidad" + numero + "'");
			var jqNoDescripcionCiclo = eval("'descripcion" + numero + "'");
			var jqNoPrecioCiclo = eval("'precio" + numero + "'");
			var jqNoImporteCiclo = eval("'importe" + numero + "'");
			var jqEliminaCiclo = eval("'" + numero + "'");
			var jqAgregaCiclo=eval("'agrega" + numero + "'");
			
			document.getElementById(jqNumeroCiclo).setAttribute('value',  contador);
			document.getElementById(jqRenglonCiclo).setAttribute('id', "renglon" + contador);
			document.getElementById(jqNumeroCiclo).setAttribute('id', "consecutivoID" + contador);
			
			document.getElementById(jqNoCantidadCiclo).setAttribute('id', "cantidad" + contador);
			document.getElementById(jqNoUnidadCiclo).setAttribute('id', "unidad" + contador);
			document.getElementById(jqNoDescripcionCiclo).setAttribute('id', "descripcion" + contador);
			document.getElementById(jqNoPrecioCiclo).setAttribute('id', "precio" + contador);
			document.getElementById(jqNoImporteCiclo).setAttribute('id', "importe" + contador);
			
			document.getElementById(jqEliminaCiclo).setAttribute('id',  contador);
			document.getElementById(jqAgregaCiclo).setAttribute('id', "agrega"+ contador);

			contador = parseInt(contador + 1);
		});
	}
</script>
