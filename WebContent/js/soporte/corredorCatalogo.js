$(document).ready(function() {
	esTab = true;
	//Definicion de Constantes y Enums  
	var catTipoTransaccionUsuario = {
  		'agrega':'1',
  		'modifica':'2',
	};

	//------------ Metodos y Manejo de Eventos -----------------------------------------

   deshabilitaBoton('modifica', 'submit');
   deshabilitaBoton('agrega', 'submit');
	
	$(':text').focus(function() {	
	 	esTab = false;
	});
	
	$.validator.setDefaults({
		submitHandler: function(event) { 
			grabaFormaTransaccion(event, 'formaGenerica', 'contenedorForma', 'mensaje','true','usuarioID'); 
		}
    });	
		    
	$(':text').bind('keydown',function(e){
		if (e.which == 9 && !e.shiftKey){
			esTab= true;
		}
	});
	
	$('#agrega').click(function() {		
		$('#tipoTransaccion').val(catTipoTransaccionUsuario.agrega);
	});
	
	$('#modifica').click(function() {		
		$('#tipoTransaccion').val(catTipoTransaccionUsuario.modifica);
	});		
	
	$('#numeroCorredor').bind('keyup',function(e){
		//TODO Agregar Libreria de Constantes Tipo Enum
		lista('usuarioID', '2', '1', 'nombreCompleto', $('#usuarioID').val(), 'listaUsuarios.htm');
	});
		
	$('#numeroCorredor').blur(function() {
  		validaCorredor(this);
	});	
	$('#sucursalUsuario').bind('keyup',function(e){
		//TODO Agregar Libreria de Constantes Tipo Enum
		lista('sucursalUsuario', '1', '1', 'nombreSucurs', 
				$('#sucursalUsuario').val(), 'listaSucursales.htm');
	});
	$('#sucursalUsuario').blur(function() {
  		consultaSucursal(this.id);
	});
	
	$('#rolID').bind('keyup',function(e){
		//TODO Agregar Libreria de Constantes Tipo Enum
		lista('rolID', '1', '1', 'nombreRol', 
				$('#rolID').val(), 'listaRoles.htm');
	});	


	$('#rolID').blur(function() {
  		consultaRoles(this.id);
	});	
	
	//------------ Validaciones de la Forma -------------------------------------
	
	$('#formaGenerica').validate({
				
		rules: {
			nombre: {
				required: true			
			},
			clave: {
				required: true
				
			},
			rolID: {
				required: true
			},	
			sucursalUsuario: {
				required: true
			},			
			correo: { 
				required:  false,
				email: 	true
			}
		},
		messages: {
			nombre: {
				required: 'Especificar Nombre'
			
			},
			clave: {
				required: 'Especificar Clave'
				
			},
			rolID: {
				required: 'Especificar un Rol'
				
			},
			sucursalUsuario: {
				required: 'Especificar Sucursal'
				
			},
			correo: {
				required: 	'Especifique un Correo',
				email: 		'Especifique un Correo Válido'
			}
		}		
	});
	
	//------------ Validaciones de Controles -------------------------------------
	function validaCorredor(control) {
	var numCorredor = $('#numeroCorredor').val();
	setTimeout("$('#cajaLista').hide();", 200);
	if(numCorredor != '' && esTab){
	var corredorBeanCon = {
 				'numeroCorredor': numCorredor 
			};
		corredorServicio.consulta(1,corredorBeanCon,function(corredor) {
			
		if(corredor != null){
			dwr.util.setValues(corredor);
			habilitaBoton('modifica', 'submit');
			deshabilitaBoton('agrega', 'submit');
			}else{
				$('#nombre').val('');
				$('#apPaterno').val('');
				$('#apMaterno').val('');
				$('#calle').val('');
				$('#numCasa').val('');
				$('#colonia').val('');
				$('#municipio').val('');
				$('#ciudad').val('');
				$('#telefono').val('');
				$('#correo').val('');
				$('#fechaNac').val('');
				$('#clubPertenece').val('');
				
				habilitaBoton('agrega', 'submit');
				deshabilitaBoton('modifica', 'submit');
			}
		});
		}
	}
	
	function consultaSucursal(idControl) {
		var jqSucursal = eval("'#" + idControl + "'");
		var numSucursal = $(jqSucursal).val();	
		var conSucursal=2;
		setTimeout("$('#cajaLista').hide();", 200);		 
		if(numSucursal != '' && !isNaN(numSucursal) && esTab){
			sucursalesServicio.consultaSucursal(conSucursal,numSucursal,function(sucursal) {
				if(sucursal!=null){							
					$('#nomSucursal').val(sucursal.nombreSucurs);
				}else{
					alert("No Existe la Sucursal"); 
				}    						
			});
		}
	}	
		
	function consultaRoles(idControl) { 
		var jqRol = eval("'#" + idControl + "'");
		var numRol = $(jqRol).val();	 
		var conRol=2;
		var rolesBeanCon = {
  				'rolID':numRol
		};	
		setTimeout("$('#cajaLista').hide();", 200);		 
		if(numRol != '' && !isNaN(numRol) && esTab){ 
			rolesServicio.consultaRoles(conRol,rolesBeanCon,function(roles) { 
			if(roles!=null){							
					$('#nombreRol').val(roles.nombreRol);
				}else{
					alert("No Existe el Rol"); 
				}    						
			});
		}
	}	
});