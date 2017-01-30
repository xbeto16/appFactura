//Author: FChia
//Funcion para el Manejo de Transacciones hacia la BD
//event: es el vento que se disparo (submit)
//idForma: id de la forma que se enviara
//idDivContenedor: id del contenedor de la forma
//idDivMensaje: id del div donde se mostrara el mensaje de resultado
//inicializaforma: (true, false) indica si se inicializa la forma en mensaje de exito
//idCampoOrigen: Campo desde donde se inicializa (este campo no se inicializara)

function grabaFormaTransaccion(event, idForma, idDivContenedor, idDivMensaje,
										 inicializaforma, idCampoOrigen) {
	 consultaSesion();
	var jqForma = eval("'#" + idForma + "'");
	var jqContenedor = eval("'#" + idDivContenedor + "'");
	var jqMensaje = eval("'#" + idDivMensaje + "'");
	var url = $(jqForma).attr('action');
	var resultadoTransaccion = 0;	

	quitaFormatoControles(idForma);
	//No descomentar la siguiente linea
	//event.preventDefault();
	$(jqMensaje).html('<img src="images/barras.jpg" alt=""/>');   
	$(jqContenedor).block({
			message: $(jqMensaje),
		 	css: {border:		'none',
		 			background:	'none'}
	});
   // Envio de la forma

   $.post( url, serializaForma(idForma), function( data ) {
			if(data.length >0) {
				$(jqMensaje).html(data);
				var exitoTransaccion = $('#numeroMensaje').val();
				resultadoTransaccion = exitoTransaccion; 
				if (exitoTransaccion == 0 && inicializaforma == 'true' ){
					inicializaForma(idForma, idCampoOrigen);		
				}
				var campo = eval("'#" + idCampoOrigen + "'");
				if($('#consecutivo').val() != 0){
					$(campo).val($('#consecutivo').val());
				}		
			}
	});
	return resultadoTransaccion;
}

function grabaFormaArchivo(event, idForma, idDivContenedor,idDivMensaje,
										 inicializaforma, idCampoOrigen) {
	
	var jqForma = eval("'#" + idForma + "'");
	var jqContenedor = eval("'#" + idDivContenedor + "'");
	var jqMensaje = eval("'#" + idDivMensaje + "'");
	quitaFormatoControles(idForma);
	
	$(jqMensaje).html('<img src="images/barras.jpg" alt=""/>');   
	$.blockUI({
			message: $(jqMensaje),
		 	css: {border:		'none',
		 			background:	'none'}
	});
	
     $('.blockOverlay').attr('title','Clic para Desbloquear').click($.unblockUI); 
	
	
   // Envio de la forma	
   $(jqForma).ajaxForm(function(data) {
	   if(data.length >0) {
			$(jqMensaje).html(data);
			var exitoTransaccion = $('#numeroMensaje').val();
			resultadoTransaccion = exitoTransaccion; 
			if (exitoTransaccion == 0 && inicializaforma == 'true' ){
				inicializaForma(idForma, idCampoOrigen);		
			}
			var campo = eval("'#" + idCampoOrigen + "'");
			if($('#consecutivo').val() != 0){
				$(campo).val($('#consecutivo').val());
			}		
		}
    	//alert("Archivo Guardado");
	});
		
}
//Author: FChia
//Funcion para el Manejo de Transacciones hacia la BD
//event: es el vento que se disparo (submit)
//idForma: id de la forma que se enviara
//idDivContenedor: id del contenedor de la forma
//idDivMensaje: id del div donde se mostrara el mensaje de resultado
//inicializaforma: (true, false) indica si se inicializa la forma en mensaje de exito
//idCampoOrigen: Campo desde donde se inicializa (este campo no se inicializara)
//funcionPostEjecucionExitosa: Nombre de la Funcion a Ejecutar cuando la Respuesta de Ejecucion es Exitosa
//funcionPostEjecucionFallo: Nombre de la Funcion a Ejecutar cuando la Respuesta de Ejecucion es de Fallo o NO Exito
function grabaFormaTransaccionRetrollamada(event, idForma, idDivContenedor, idDivMensaje,
							   inicializaforma, idCampoOrigen, funcionPostEjecucionExitosa,
							   funcionPostEjecucionFallo) {
	 consultaSesion();
	var jqForma = eval("'#" + idForma + "'");
	var jqContenedor = eval("'#" + idDivContenedor + "'");
	var jqMensaje = eval("'#" + idDivMensaje + "'");
	var url = $(jqForma).attr('action');
	var resultadoTransaccion = 0;	

	quitaFormatoControles(idForma);
	//No descomentar la siguiente linea
	//event.preventDefault();
	$(jqMensaje).html('<img src="images/barras.jpg" alt=""/>');   
	$(jqContenedor).block({
			message: $(jqMensaje),
		 	css: {border:		'none',
		 			background:	'none'}
	});
 // Envio de la forma

 $.post( url, serializaForma(idForma), function( data ) {
			if(data.length >0) {
				$(jqMensaje).html(data);
				var exitoTransaccion = $('#numeroMensaje').val();
				resultadoTransaccion = exitoTransaccion; 
			/*	if (exitoTransaccion == 0 && inicializaforma == 'true' ){
					inicializaForma(idForma, idCampoOrigen);	
				}  validar */
				var campo = eval("'#" + idCampoOrigen + "'");
				if($('#consecutivo').val() != 0){
					$(campo).val($('#consecutivo').val());
				}
				//Ejecucion de las Funciones de CallBack(RetroLlamada)
				if (exitoTransaccion == 0 && funcionPostEjecucionExitosa != '' ){
					esTab=true;
					
					eval( funcionPostEjecucionExitosa + '();' );
				}
				//TODO la de fallo
			}
	});
	return resultadoTransaccion;
}


function posicionamiento(jqControl, elemento) {
	jqElemento = eval("'#" + elemento + "'");
	
	$(jqElemento).position({
	  	my: "left top",
	  	at: "left bottom",
	 	of: jqControl
	});

}


function lista(controlId, minCaracteres, tipoListaVal, campoLista, parametroLista, vista) {
	jqControl = eval("'#" + controlId + "'");
	var position = $(jqControl).position();
	var valorListar = $(jqControl).val();	
	 consultaSesion();
	var params = {};	
	if($(jqControl).val().length <= minCaracteres || !isNaN($(jqControl).val())) {
		$('#cajaLista').hide();		
	} else {
	
		//Si la Variable de CampoLista es un Arreglo
		if ( (typeof campoLista == 'object') && campoLista.length != null){
			for (i=0;i<campoLista.length;i++){				 		 	
			 	params[campoLista[i]] = parametroLista[i];
			}
		}else{
			params[campoLista] = parametroLista;			
		}		
		params['controlID'] = controlId; 						
		params['tipoLista'] = tipoListaVal;
		
		$('#cajaLista').css({
            position:'absolute',
            left: eval("'" + (position.left+11) + "px'"),
            top:  eval("'" + (position.top +40) + "px'")	
		});
		
		$.post(vista, params, function(data){				
				if(data.length >0) {
					$('#cajaLista').show();
					$('#elementoLista').html(data);
					$('#cajaLista').focus();
					posicionamiento(jqControl, 'cajaLista');
					posicionamiento(jqControl, 'elementoLista');
				}
		});
	}
}



function listaAlfanumerica(controlId, minCaracteres, tipoListaVal, campoLista, parametroLista, vista) {
	jqControl = eval("'#" + controlId + "'");
	var position = $(jqControl).position();
	var valorListar = $(jqControl).val();	
	
	var params = {};	
	if($(jqControl).val().length <= minCaracteres) {
		$('#cajaLista').hide();		
	} else {
	
		//Si la Variable de CampoLista es un Arreglo
		if ( (typeof campoLista == 'object') && campoLista.length != null){
			for (i=0;i<campoLista.length;i++){				 		 	
			 	params[campoLista[i]] = parametroLista[i];
			}
		}else{
			params[campoLista] = parametroLista;			
		}		
		params['controlID'] = controlId; 						
		params['tipoLista'] = tipoListaVal;
		
		$('#cajaLista').css({
            position:'absolute',
            left: eval("'" + (position.left+11) + "px'"),
            top:  eval("'" + (position.top +40) + "px'")	
		});
		
		$.post(vista, params, function(data){				
				if(data.length >0) {
					$('#cajaLista').show();
					$('#elementoLista').html(data);
					$('#cajaLista').focus();
					posicionamiento(jqControl, 'cajaLista');
					posicionamiento(jqControl, 'elementoLista');
				}
		});
	}
}



function cargaValorLista(control, valor) {	
consultaSesion();
	jqControl = eval("'#" + control + "'");
	$(jqControl).val(valor);
	$(jqControl).focus();
	setTimeout("$('#cajaLista').hide();", 200);
}

function deshabilitaControl(controlBtnId) {
	var jqControl = eval("'#" + controlBtnId + "'");
	$(jqControl).attr('disabled', 'disabled');
	$(jqControl).attr('deshabilitado', 'true');
} 

function habilitaControl(controlBtnId) {
	var jqControl = eval("'#" + controlBtnId + "'");
	$(jqControl).attr('disabled',false); 
	$(jqControl).attr('deshabilitado', 'false');
} 

function soloLecturaControl(controlBtnId) {
	var jqControl = eval("'#" + controlBtnId + "'");
	$(jqControl).attr('readOnly',true); 
} 



//controlBtnId es el id del boton a deshabilitar
//tipo: boton, submit

function deshabilitaBoton(controlBtnId, tipo) {
	var jqControl = eval("'#" + controlBtnId + "'");
	var claseId = 'submit';
	var claseSobreId = 'submit:hover ';
	
	if (tipo == 'boton'){
		claseId = "botonGral";
		claseSobreId = "botonGral:hover";
	}
				
	$(jqControl).attr('disabled', 'disabled');						
	$(jqControl).removeClass(claseId);
	$(jqControl).removeClass(claseSobreId);
	$(jqControl).addClass("botonDeshabilitado");

}


//controlBtnId es el id del boton a habilitar
//tipo: boton, submit

function habilitaBoton(controlBtnId, tipo) {
	var jqControl = eval("'#" + controlBtnId + "'");
	var claseDeshabilitaId = 'submit';	
	var claseId = 'submit';
	var claseSobreId = 'submit:hover ';
	
	
	if (tipo == 'boton'){
		claseId = "botonGral";
		claseSobreId = "botonGral:hover";
	}
				
	$(jqControl).removeAttr('disabled');								
	$(jqControl).removeClass(claseDeshabilitaId);
	$(jqControl).addClass("submit");
	$(jqControl).addClass("submit:hover");

}


//limforma es el id de la forma  a limpiar
	function limpiaForm(limforma) {
// recorremos todos los campos que tiene el formulario
	$(':input', limforma).each(function() {
	var type = this.type;
	if (type == 'text' || type == 'password' || type == "textarea")
			this.value = "";
// excepto de los checkboxes y radios, le quitamos el checked
// pero su valor no debe ser cambiado
	else if (type == 'checkbox' || type == 'radio')
	this.checked = false;
// los selects le ponemos el indice a -
	else if (type == 'select')
	this.selectedIndex = -1;
		});
		}


//Author: FChia
//Funcion de inicializa los valores de una forma
//idForma: id de la forma a inicializar
//idCampoOrigen: Campo desde donde se inicializa,
//este campo no se inicializara
function inicializaForma(idForma, idCampoOrigen) {
	var jqForma = eval("'#" + idForma + "'");
	$(jqForma).find(':input').each( 
  		function(){	    		
 			var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'");
			var formInicializa = 'true'; 				
			
			if($(jControl).attr('iniForma') != null){					
				formInicializa = $(jControl).attr('iniForma');
			}
			
			if($(jControl).attr('id') == idCampoOrigen){
				formInicializa = 'false';
			}
			
			if(formInicializa=='true'){
	        	if(child.is(':text, textarea')){
	            child.val('');
	      }
	     
	        	if(child.is(':password')){  
	            child.val('');
	      	}	
	        	if(child.is(':radio')){         
	            $(jControl).attr('checked', true);
	      	}
	      	if(child.is(':select option')){	
	         	//$('#estatus option:first').attr('selected','selected');  
	         	//$(jControl  + " option:first").attr('selected','selected');   
	         	//TODO Agregar Funcionalidad FChia
	      	}
	      	
	      	if(child.is(':checkbox')){
	      		child.attr('checked', false);
	      	}
			}
  		}
	);	
}

//funcion para convertir minusculas a mayusculas
function ponerMayusculas(nombre) 
{           
nombre.value=nombre.value.toUpperCase(); 
}   

//Author: Daniel Carrasco
/* Agrega la propiedad de readOnly a los de tipo Hidden */
window.onload = validar_oculto;
function validar_oculto(){
var x=document.getElementsByTagName('input');
   for(var a=0;a<x.length;a++){
        if(x[a].type=='hidden'){
            x[a].setAttribute('readOnly', true);
        }
    }
    return true;
}


/* Desabilita el boton derecho del mouse*/
document.onmousedown = anularBotonDerecho;
document.oncontextmenu = new Function("return false");
function anularBotonDerecho(e) {
	var msg = "Derechos Reservados © \n EfiSys";
    if (navigator.appName == 'Netscape' && e.which == 3) {
       alert(msg); 
       return false;
    }else if (navigator.appName == 'Microsoft Internet Explorer' && event.button==2) {
       alert(msg); 
    	return false;
    }
 return true;
}

/* Desabilita el boton F5*/
document.onkeydown =validar;
function validar(e) {
	var key;

	 if(navigator.appName == 'Microsoft Internet Explorer' ) { 
		key=window.event.keyCode;
		window.event.keyCode = 505;
		if(key==116 && window.event && window.event.keyCode == 505){
	       alert("F5 Tecla Invalida");
	       return false;
	    }
	 }else if(navigator.appName == 'Netscape') { 
		key=e.keyCode;
		if(key==116) {
	    	alert("F5 Tecla Invalida");
	        return false;
		}
	 }
}

/* Cancela la tecla enter en los formulario*/
document.onkeypress = pulsar;
function pulsar(e) {
	tecla=(document.all) ? e.keyCode : e.which; 
	if(tecla==13){
		return false;	
	}
	return true;
}

function actualizaFormatosMoneda(idForma) {	
	actualizaFormatoMoneda(idForma);
	actualizaFormatoTasa(idForma);
}

function actualizaFormatoMoneda(idForma) {	
	var jqForma = eval("'#" + idForma + "'");	
	$(jqForma).find('input[esMoneda="true"]').each( 
  		function(){	    	
  			var child = $(this);  			
			var jControl = eval("'#" + child.attr('id') + "'"); 
		 	$(jControl).formatCurrency({positiveFormat: '%n', roundToDecimalPlace: 2});
	});			
}

function actualizaFormatoTasa(idForma) {	
	var jqForma = eval("'#" + idForma + "'");	
	$(jqForma).find('input[esTasa="true"]').each( 
  		function(){	    	
  			var child = $(this);  			
			var jControl = eval("'#" + child.attr('id') + "'"); 
		 	$(jControl).formatCurrency({positiveFormat: '%n', roundToDecimalPlace: 4});
	});			
}


function agregaFormatoMoneda(idForma) {
	var jqForma = eval("'#" + idForma + "'");	
	$(jqForma).find('input[esMoneda="true"]').each( 
  		function(){	    	
  			var child = $(this);  			
			var jControl = eval("'#" + child.attr('id') + "'"); 
        $(jControl).bind('keyup',function(){
			  $(jControl).formatCurrency({
							colorize: true,
							positiveFormat: '%n', 
							negativeFormat: '%n',
							roundToDecimalPlace: -1
							});
			});
			$(jControl).blur(function() {
					$(jControl).formatCurrency({
							positiveFormat: '%n',
							negativeFormat: '%n', 
							roundToDecimalPlace: 2	
					});
			});
			$(jControl).formatCurrency({
				positiveFormat: '%n',  
				negativeFormat: '%n',
				roundToDecimalPlace: 2	
			});			
		}					
	);		
}
			
function agregaFormatoTasa(idForma) {
	var jqForma = eval("'#" + idForma + "'");
	$(jqForma).find('input[esTasa="true"]').each( 
  		function(){	    		
  			var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'"); 
        	$(jControl).bind('keyup',function(){
			  $(jControl).formatCurrency({
							colorize: true,
							positiveFormat: '%n', 
							negativeFormat: '%n',
							roundToDecimalPlace: -1
			  });
			});
			$(jControl).blur(function() {
					$(jControl).formatCurrency({
							positiveFormat: '%n',
							negativeFormat: '%n',
							roundToDecimalPlace: 4	
				});
			});
			$(jControl).formatCurrency({
				positiveFormat: '%n', 
				negativeFormat: '%n',
				roundToDecimalPlace: 4	
			});			
						
  		}
	);		
}

// Author: Fsanchez
// Funcion para validar el numero maximo de caracteres numericos introducidos en un input
function agregaFormatoNumMaximo(idForma) { 
	 var jqForma = eval("'#" + idForma + "'");	
	$(jqForma).find('input[numMax]').each( 
  		function(){	    	
  			var child = $(this);  			
			var jControl = eval("'#" + child.attr('id') + "'"); 
        $(jControl).bind('keyup',function(){ 
				var texto = $(jControl).val();
				var numCaract = $(this).attr("numMax");
			 	if(texto.length > numCaract && !isNaN($(jControl).val())){
			 		texto = texto.substring(0,numCaract);
					$(jControl).val(texto);	
				}
			});
		}					
	);		
}


function controlQuitaFormatoMoneda(control){
	var jControl = eval("'#" + control + "'");
	var valor=($(jControl).formatCurrency({
							positiveFormat: '%n', 
							roundToDecimalPlace: 2	
							})).asNumber();
	$(jControl).val(valor);
}

function quitaFormatoMoneda(idForma) {
	var jqForma = eval("'#" + idForma + "'");
	$(jqForma).find('input[esMoneda="true"]').each( 
  		function(){	    		
  			var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'");
			var valor=($(jControl).formatCurrency({
							positiveFormat: '%n', 
							roundToDecimalPlace: 2	
							})).asNumber();
				$(jControl).val(valor);
  		}
	);		
}

function controlQuitaFormatoTasa(control){
	var jControl = eval("'#" + control + "'");
	var valor=($(jControl).formatCurrency({
							positiveFormat: '%n', 
							roundToDecimalPlace: 4	
							})).asNumber();
	$(jControl).val(valor);
}

function quitaFormatoTasa(idForma) {
	var jqForma = eval("'#" + idForma + "'");
	$(jqForma).find('input[esMoneda="true"]').each( 
  		function(){	    		
  			var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'");
			var valor=($(jControl).formatCurrency({
							positiveFormat: '%n', 
							roundToDecimalPlace: 4	
							})).asNumber();
				$(jControl).val(valor);				
  		}
	);		
} 

function agregaCalendario(idForma) {
	var jqForma = eval("'#" + idForma + "'");
	$(jqForma).find('input[esCalendario="true"]').each( 
  		function(){	    		
  			var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'");
    		$(jControl).datepicker({
    			showOn: "button",
    			buttonImage: "images/calendar.png",
    			buttonImageOnly: true,
				changeMonth: true, 
				changeYear: true,
				dateFormat: 'yy-mm-dd',
				yearRange: '-100:+10'
			}); 
					
			$(jControl).datepicker($.datepicker.regional['es']);	
  		}
	);		 
}

	
function agregaFormatoControles(idForma){ 
	agregaFormatoMoneda(idForma);
	agregaFormatoTasa(idForma);
	agregaCalendario(idForma);
	agregaFormatoNumMaximo(idForma);
	
}
 
function quitaFormatoControles(idForma){
	quitaFormatoMoneda(idForma);
	quitaFormatoTasa(idForma);
}

function serializaDisabled(idForma){
	var jqForma = eval("'#" + idForma + "'");
	var obj={};  

	$(jqForma).find('input[deshabilitado="true"]').each(
		function(){ 
		 	var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'");
      		obj[$(jControl).attr('id')]=$(jControl).val(); 
	    }
	);
	$(jqForma).find('select[deshabilitado="true"]').each(
		function(){ 
		 	var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'");
      		obj[$(jControl).attr('id')]=$(jControl).val(); 
	    }
	);

	$(jqForma).find('input[disabled="true"]').each(
		function(){ 
		 	var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'");
      	obj[$(jControl).attr('id')]=$(jControl).val(); 
	    }
    ); 
	$(jqForma).find('input[disabled="disabled"]').each(
		function(){
		 	var child = $(this);
			var jControl = eval("'#" + child.attr('id') + "'");
      	obj[$(jControl).attr('id')]=$(jControl).val(); 
	    }
    );    
    $(jqForma).find('select[disabled="true"]').each( 
		function(){
		 	var child = $(this); 
			var jControl = eval("'#" + child.attr('id') + "'");
 		//$(jControl).attr('disabled', true).siblings().removeAttr('disabled'); 
      	obj[$(jControl).attr('id')]=$(jControl).val(); 
	    }    
    ); 
    $(jqForma).find('select[disabled="disabled"]').each( 
		function(){
		 	var child = $(this); 
			var jControl = eval("'#" + child.attr('id') + "'");
 		//$(jControl).attr('disabled', true).siblings().removeAttr('disabled'); 
      	obj[$(jControl).attr('id')]=$(jControl).val(); 
	    }    
    );  
	return $.param(obj);
}

function serializaForma(idForma){
	var jqForma = eval("'#" + idForma + "'");
	return $(jqForma).serialize()+'&'+ serializaDisabled(idForma);
	
} 

//Autor: Fsanchez
// funcion para intercalar colores del grid 
function alternaFilas(id){
	 if(document.getElementsByTagName){ 
		 var table = document.getElementById(id);   
		 var rows = table.getElementsByTagName("tr");
		 for(i = 0; i < rows.length; i++){        
		 if(i % 2 == 0){
		 rows[i].className = "parcolor"; 
		 }else{
		 rows[i].className = "imparcolor";
		 }      
	 }  
	 }
	}
	
	function timerSesion(){
	$.timer(60000, function(){
		consultaSesion();
		});
	}
// variable clave de usuario para cambiar estatus de sesion cuando ya expiró la sesión
 var parametroBean = consultaParametrosSession();
		var cl =parametroBean.claveUsuario;
		$('#nuClave').val(cl);
// funcion para consultar si la sesion sigue activa o ya expiró	
	function consultaSesion(){ 
      var parametroBean = consultaParametrosSession();
		var clave =parametroBean.claveUsuario;
		
		if(clave==''){
			alert('La sesion ya caduco'); 
			location.href ='sesionExpirada.htm?claveUsu='+cl+'';  
		}
	}   
	 


//Consulta los Parametros de Session
function consultaParametrosSession(){
	var parSessionBean = null;
	$.ajax(
		{	type: "GET", 
			url: "consultaSession.htm",  
			dataType: "xml",
        	async: false,
			success: function(xmlInf) {
				parSessionBean = {
  					'fechaAplicacion':$(xmlInf).find('fechaAplicacion').text(),
  					'numeroSucursalMatriz':$(xmlInf).find('numeroSucursalMatriz').text(),
  					'nombreSucursalMatriz':$(xmlInf).find('nombreSucursalMatriz').text(),  					
  					'telefonoLocal':$(xmlInf).find('telefonoLocal').text(),
  					'telefonoInterior':$(xmlInf).find('telefonoInterior').text(),
  					'numeroInstitucion':$(xmlInf).find('numeroInstitucion').text(),
  					'nombreInstitucion':$(xmlInf).find('nombreInstitucion').text(),
  					'representanteLegal':$(xmlInf).find('representanteLegal').text(),
  					'rfcRepresentante':$(xmlInf).find('rfcRepresentante').text(),  					
  					'numeroMonedaBase':$(xmlInf).find('numeroMonedaBase').text(),
  					'nombreMonedaBase':$(xmlInf).find('nombreMonedaBase').text(),
  					'desCortaMonedaBase':$(xmlInf).find('desCortaMonedaBase').text(),
  					'simboloMonedaBase':$(xmlInf).find('simboloMonedaBase').text(),
  					'numeroUsuario':$(xmlInf).find('numeroUsuario').text(),
  					'claveUsuario':$(xmlInf).find('claveUsuario').text(),  					
  					'perfilUsuario':$(xmlInf).find('perfilUsuario').text(),
  					'nombreUsuario':$(xmlInf).find('nombreUsuario').text(),
  					'correoUsuario':$(xmlInf).find('correoUsuario').text(),	
  					'sucursal':$(xmlInf).find('sucursal').text(),
  					'fechaSucursal':$(xmlInf).find('fechaSucursal').text(),
  					'nombreSucursal':$(xmlInf).find('nombreSucursal').text(),
  					'gerenteSucursal':$(xmlInf).find('gerenteSucursal').text(),
  					'numeroCaja':$(xmlInf).find('numeroCaja').text(),
					'loginsFallidos':$(xmlInf).find('loginsFallidos').text(),
					'tasaISR':$(xmlInf).find('tasaISR').text(),
					'diasBaseInversion':$(xmlInf).find('diasBaseInversion').text()	,	
					'fechUltimAcces':$(xmlInf).find('fechUltimAcces').text(),
					'fechUltPass':$(xmlInf).find('fechUltPass').text(),
					'diasCambioPass':$(xmlInf).find('diasCambioPass').text(),
					'cambioPassword':$(xmlInf).find('cambioPassword').text(),
					'estatusSesion':$(xmlInf).find('estatusSesion').text(),
					'IPsesion':$(xmlInf).find('IPsesion').text(),	
					'promotorID':$(xmlInf).find('promotorID').text(),
					'clienteInstitucion':$(xmlInf).find('clienteInstitucion').text(),
					'cuentaInstitucion':$(xmlInf).find('cuentaInstitucion').text(),
					'rutaArchivos':$(xmlInf).find('rutaArchivos').text(),		
					'cajaID':$(xmlInf).find('cajaID').text(),
					'tipoCaja':$(xmlInf).find('tipoCaja').text(),	
					'saldoEfecMN':$(xmlInf).find('saldoEfecMN').text(),	
					'saldoEfecME':$(xmlInf).find('saldoEfecME').text(),
					'limiteEfectivoMN':$(xmlInf).find('limiteEfectivoMN').text(),
					'tipoCajaDes':$(xmlInf).find('tipoCajaDes').text()
				};
			}
	});
	return parSessionBean;
}


function validaTelefono(tel){
			
	var telefono = eval("'#" + tel + "'");
	var RegExPatternX = new RegExp('^[0-9]{10}$'); 
	//'^([0-9]{2}-?){2}[0-9]{3}-?[0-9]-?([0-9]{2}-?){2}$'
	var errorMessage = 'Numero de telefono incorrecto';
			if($(telefono).val()!= ''){ 
				if ($(telefono).val().match(RegExPatternX)) { 
					} 
				else {
					alert(errorMessage); 
					$(telefono).focus();
					$(telefono).select(); 
					
				} 
		}		
}

function convertDate(stringdate)
{ 
    // Internet Explorer does not like dashes in dates when converting, 
    // so lets use a regular expression to get the year, month, and day 
    var DateRegex = /([^-]*)-([^-]*)-([^-]*)/;
    var DateRegexResult = stringdate.match(DateRegex);
    var DateResult;
    var StringDateResult = "";
 
    // try creating a new date in a format that both Firefox and Internet Explorer understand
    try
    {
        DateResult = new Date(DateRegexResult[2]+"/"+DateRegexResult[3]+"/"+DateRegexResult[1]);
    } 
    // if there is an error, catch it and try to set the date result using a simple conversion
    catch(err) 
    { 
        DateResult = new Date(stringdate); 
    } 

    // format the date properly for viewing
    StringDateResult = (DateResult.getMonth()+1)+"/"+(DateResult.getDate()+1)+"/"+(DateResult.getFullYear());

    return StringDateResult;
}	

