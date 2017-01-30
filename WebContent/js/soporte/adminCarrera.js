$(document).ready(function(){
		esTab = true;
		
		//Definicion de constantes y Enums
		var catTipoLisCarrera= {
			'corredores' : 1	
		};
		
		
		var catTipoConResumen = { 
			'principal'	: 1,
			'foranea'	: 2
		};	
			
		var catTipoTranResumen = { 
			'agrega'		: 1,
			'modifica'	: 2
		};		
		
		var catTipoLisResumen = { 
		  	'principal'	: 1,
		  	'resumen'	: 2
		};	

		consultaGridCorredores();
		//-----------------------Metodos y manejo de eventos-----------------------
		
		deshabilitaBoton('agregar', 'submit');
		deshabilitaBoton('modificar', 'submit');

		$(':text').focus(function() {	
	 	esTab = false;
	});
	
	agregaFormatoControles('formaGenerica');
	
	$(':text').bind('keydown',function(e){
		if (e.which == 9 && !e.shiftKey){
			esTab= true;
		}
	});			
    

	
	
	$('#campaniaID').blur(function(){
		consultaCampania(this.id);		
	});
		
	
	$('#campaniaID').bind('keyup',function(e) { 
		lista('campaniaID', '1', '1', 'nombre', $('#campaniaID').val(),'campaniasLista.htm');
	});
	
//-------------Validaciones de controles---------------------
	

	
	function consultaGridCorredores(){	
		var params = {};
		params['tipoLista'] = catTipoLisCarrera.corredores;
		
		$.post("controlCorredorGridVista.htm", params, function(data){
				if(data.length >0) {		
						$('#gridCorredores').html(data);
						$('#gridCorredores').show();		
						// alternaFilas('alternacolor');			
				}else{				
						$('#gridCorredores').html("");
						$('#gridCorredores').show();
				}
		});
	}


	});



var inicio=0;
var timeout=0;
function empezarDetener(elemento){

	if(timeout==0)
	{
		// empezar el cronometro

		//elemento.value="Detener";
		deshabilitaBoton('empezar', 'submit');

		// Obtenemos el valor actual
		inicio=vuelta=new Date().getTime();

		// iniciamos el proceso
		funcionando();
	}else{
		// detemer el cronometro

		elemento.value="Empezar";
		clearTimeout(timeout);
		timeout=0;
	}
}

function funcionando()
{
	// obteneos la fecha actual
	var actual = new Date().getTime();

	// obtenemos la diferencia entre la fecha actual y la de inicio
	var diff=new Date(actual-inicio);

	// mostramos la diferencia entre la fecha actual y la inicial
	var result=LeadingZero(diff.getUTCHours())+":"+LeadingZero(diff.getUTCMinutes())+":"+LeadingZero(diff.getUTCSeconds())+ ":"+LeadingZero(diff.getUTCMilliseconds());
	//document.getElementById('crono').innerHTML = result;
	$('#cronometro').val(result);
		
	// Indicamos que se ejecute esta función nuevamente dentro de 1 segundo
		timeout=setTimeout("funcionando()",10);
	}

/* Funcion que pone un 0 delante de un valor si es necesario */
function LeadingZero(Time) {
	return (Time < 10) ? "0" + Time : + Time;
}


function grabarCrono(elemento){		
	var id = elemento.id.substring(13);
	var params = {};
		params['numeroCorredor'] =  $('#corredorID'+id).val();		
	params['tiempoVuelta'] =  $('#cronometro').val();
	
	$.post("guardaCronometro.htm", params, function(data){
				if(data.length >0) {		
								
				}else{				
					
				}
		});
}