$(document).ready(function() {
		esTab = true;
		var parametroBean = consultaParametrosSession();
	//Definicion de Constantes y Enums  
	var catTipoTransaccionSucursal = {
  		'agrega':'1',
  		'modifica':'2',
  		
	};
	var catTipoConsultaSucursal = {
  		'principal':1,
  		'foranea':2 
	};	
	

	//------------ Metodos y Manejo de Eventos -----------------------------------------
	
	
	$(':text').focus(function() {	
	 	esTab = false;
	});	
	
		$.validator.setDefaults({
            submitHandler: function(event) { 
                    grabaFormaTransaccion(event, 'formaGenerica', 'contenedorForma', 'mensaje','true','sucursalID'); 
            }
    });			
		    
	$(':text').bind('keydown',function(e){
		if (e.which == 9 && !e.shiftKey){
			esTab= true;
		}
	});
	
	$('#agrega').click(function() {		
		$('#tipoTransaccion').val(catTipoTransaccionSucursal.agrega);
	});
	
	$('#modifica').click(function() {		
		$('#tipoTransaccion').val(catTipoTransaccionSucursal.modifica);
	});		
	
	$('#sucursalID').blur(function() {
  		validaSucursal(this);
	});

	$('#sucursalID').bind('keyup',function(e){
		//TODO Agregar Libreria de Constantes Tipo Enum
		lista('sucursalID', '2', '4', 'nombreSucurs', $('#sucursalID').val(), 'listaSucursales.htm');
	});
	
	$('#estadoID').bind('keyup',function(e){
		lista('estadoID', '2', '1', 'nombre', $('#estadoID').val(), 'listaEstados.htm');
	});
	
	
	$('#estadoID').blur(function() {
  		consultaEstado(this.id);
	});
	
	$('#municipioID').bind('keyup',function(e){
		var camposLista = new Array();
		var parametrosLista = new Array();
		
		camposLista[0] = "estadoID";
		camposLista[1] = "nombre";
		
		
		parametrosLista[0] = $('#estadoID').val();
		parametrosLista[1] = $('#municipioID').val();
		
		lista('municipioID', '2', '1', camposLista, parametrosLista,'listaMunicipios.htm');
	});
	
	$('#municipioID').blur(function() {
  		consultaMunicipio(this.id);
	});
	
	$('#nombreGerente').bind('keyup',function(e){
		lista('nombreGerente', '2', '1', 'nombreCompleto', $('#nombreGerente').val(), 'listaUsuarios.htm');
	});
	
	$('#nombreGerente').blur(function() {
  		consultaGerente(this.id);
	});	
	$('#subGerente').bind('keyup',function(e){
		lista('subGerente', '2', '1', 'nombre', $('#subGerente').val(), 'listaUsuarios.htm');
	});
	
	$('#subGerente').blur(function() {
  		consultaSubGerente(this.id);
	});
	
	$('#CP').blur(function() {
  		consultaDirecCompleta(this.id);
  	});
	
	//------------ Validaciones de la Forma -------------------------------------
	
	$('#formaGenerica').validate({
				
		rules: {
			sucursalID: {
				//required: false
			},
			nombreSucurs: {
				required: true,
				minlength: 3
			},
			tipoSucursal: {
				required: true,
			},
			IVA: {
				required: true,
			},
			tasaISR: {
				required: true,
			},
			nombreGerente: {
				required: true,
			},
			subGerente: {
				required: true,
			},
			estadoID: {
				required: true,
			},
			municipioID: {
				required: true,
			},
			calle: {
				required: true,
			},
			colonia: {
				required: true,
			},
			CP: {
				required: true,
			},
			difHorarMatriz: {
				required: true,
			},
		},
		messages: {
			sucursalID: {
			required: 'Especificar numero'
			},
		
			nombreSucurs: {
				required: 'Especificar Nombre'
			},
			
			tipoSucursal: {
				required: 'Especificar Tipo'
				
			},
			IVA: {
				required: 'Especificar IVA'
			},
			tasaISR: {
				required: 'Especificar ISR'
			},
			nombreGerente: {
				required: 'Especificar Gerente'
			},
			subGerente: {
				required: 'Especificar SubGerente'
			},
			estadoID: {
				required: 'Especificar Estado'
			},
			municipioID: {
				required: 'Especificar Municipio'
			},
			calle: {
				required: 'Especificar Calle'
			},
			colonia: {
				required: 'Especificar Colonia'
			},
			CP: {
				required: 'Especificar C.P.'
			},
			difHorarMatriz: {
				required: 'Especificar Diferencia Horaria'
			},
			
		}		
	});
	
	//------------ Validaciones de Controles -------------------------------------
	
	function validaSucursal(control) {
		var numSucursal = $('#sucursalID').val();
		setTimeout("$('#cajaLista').hide();", 200);
		if(numSucursal != '' && !isNaN(numSucursal)){
					habilitaBoton('agrega', 'submit');
					habilitaBoton('modifica', 'submit');
			//	alert(numSucursal);
				sucursalesServicio.consultaSucursal(catTipoConsultaSucursal.principal,numSucursal,function(sucursal) { 
						if(sucursal!=null){
						dwr.util.setValues(sucursal);	
						esTab=true; 		
						$('#nombreGerente').val(sucursal.nombreGerente);
						consultaGerente('nombreGerente');
						$('#subGerente').val(sucursal.subGerente);
						consultaSubGerente('subGerente');
						$('#estadoID').val(sucursal.estadoID);
						consultaEstado('estadoID');
						$('#municipioID').val(sucursal.municipioID);
						consultaMunicipio('municipioID');
							deshabilitaBoton('agrega', 'submit');
							habilitaBoton('modifica', 'submit');													
						}else{
								inicializaForma('formaGenerica','sucursalID');
								$('#descripcionCC').val('');
								$('#nombGerente').val('');
								$('#nomSubGerente').val('');
								$('#fechaSucursal').val(parametroBean.fechaSucursal);
								habilitaBoton('agrega', 'submit');
								deshabilitaBoton('modifica', 'submit');
								deshabilitaBoton('elimina', 'submit');			 														
							} 
					});
			}
		}
			
			function consultaEstado(idControl) {
			var jqEstado = eval("'#" + idControl + "'");
			var numEstado = $(jqEstado).val();	
			var tipConForanea = 2;	 
			setTimeout("$('#cajaLista').hide();", 200);		
			if(numEstado != '' && !isNaN(numEstado) && esTab){
				estadosServicio.consulta(tipConForanea,numEstado,function(estado) {
						if(estado!=null){							
							$('#nombreEstado').val(estado.nombre); 
																	
						}else{
							alert("No Existe el Estado");
				}    	 						
				});
			}
		}	
		
		function consultaMunicipio(idControl) {
		var jqMunicipio = eval("'#" + idControl + "'");
		var numMunicipio = $(jqMunicipio).val();	
		var numEstado =  $('#estadoID').val();				
		var tipConForanea = 2;	
		setTimeout("$('#cajaLista').hide();", 200);		
		if(numMunicipio != '' && !isNaN(numMunicipio) && esTab){
			municipiosServicio.consulta(tipConForanea,numEstado,numMunicipio,function(municipio) {
						if(municipio!=null){							
							$('#nombreMuni').val(municipio.nombre);
																	
						}else{
							alert("No Existe el Municipio");
								$('#municipioID').focus();
								$('#municipioID').select();
						}    	 						
				});
			}
		}			
			
		function consultaGerente(idControl) {
		var jqGerente = eval("'#" + idControl + "'");
		var numGerente = $(jqGerente).val();
		var usuarioBeanCon = {
  				'usuarioID':numGerente
				};				
		var tipConForanea = 2;	
		setTimeout("$('#cajaLista').hide();", 200);		
		if(numGerente != '' && !isNaN(numGerente) && esTab){
			usuarioServicio.consulta(tipConForanea,usuarioBeanCon,function(gerente) { 
						if(gerente!=null){						
							$('#nombGerente').val(gerente.nombreCompleto);
																	
						}else{
							alert("No Existe el Gerente");
								$('#nombreGerente').focus();
								$('#nombreGerente').select();
						}    	 						
				});
			}
		}		
		
		function consultaSubGerente(idControl) {
		var jqSubGerente = eval("'#" + idControl + "'");
		var numSubGerente = $(jqSubGerente).val();		
		var usuarioBeanCon = {
  				'usuarioID':numSubGerente
				};			
		var tipConForanea = 2;	
		setTimeout("$('#cajaLista').hide();", 200);		
		if(numSubGerente != '' && !isNaN(numSubGerente) && esTab){
			usuarioServicio.consulta(tipConForanea,usuarioBeanCon,function(subgerente) { 
						if(subgerente!=null){							
							$('#nomSubGerente').val(subgerente.nombreCompleto); 
																	
						}else{
							alert("No Existe el SubGerente");
								$('#subGerente').focus();
								$('#subGerente').select();
						}    	 						
				});
				}
			}
							
		function consultaDirecCompleta() {
			var ca 			=  $('#calle').val();
			var nu  			=  $('#numero').val();	
			var co 	 		=  $('#colonia').val();	
			var cp  			= 	$('#CP').val();	
			var nm  			=  $('#nombreMuni').val();
			var ne 			=  $('#nombreEstado').val();				
  		
			var dirCom		=	(ca +", No." + nu +", Col."+ co +", C.P. "+ cp +", "+ nm  + ", "+ ne +"." );
			$('#direcCompleta').val(dirCom);
		}		  	
}); 
