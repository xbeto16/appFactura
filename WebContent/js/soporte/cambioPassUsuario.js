$(document).ready(function() { 
	esTab = true;
	//validaCap=false;
	//Definicion de Constantes y Enums  
	var catTipoActualizaUsuario = {
  		'cambioPassword':4      
	};
	var catTipoConsultaUsuario = {  
		'contrasenia':6,  
  		'cancela':5
	};
	var cancelado ='C';
	var activo = 'A'; 
	var bloqueado = 'B';
	var parametroBean = consultaParametrosSession();   
	//------------ Metodos y Manejo de Eventos -----------------------------------------

   deshabilitaBoton('cancelar', 'submit');
	
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

	$('#actualizar').click(function() {		
		$('#tipoTransaccion').val(catTipoActualizaUsuario.cambioPassword);  
	});
	
	$('#Continuar').click(function() { 
	if( parseInt($('#numeroMensaje').val()) == 0){	
		
	$('#contenedorForma').html(''); 

		}
	});
		
	$('#usuarioID').bind('keyup',function(e){ 
		//TODO Agregar Libreria de Constantes Tipo Enum
		lista('usuarioID', '2', '1', 'nombreCompleto', $('#usuarioID').val(), 'listaUsuarios.htm');
	});
		
	$('#usuarioID').blur(function() {
  		consultaUsuario(this.id);  
	});
	$('#contrasenia').blur(function() {
  		validaContrasenia(this.id); 
	});
	$('#Confirmacontra').blur(function() {
  		validaNuevaContra(this.id);  
	});
	
			
	//------------ Validaciones de la Forma -------------------------------------
	
	$('#formaGenerica').validate({ 
				
		rules: {
			
			usuarioID: {
				required: true
				
			},
			contrasenia: {
				required: true 
				
			},
					
		},
		messages: {
			
		 
			usuarioID: {
				required: 'Especificar Numero'
			
			},
			contrasenia: {
				required: 'Especificar ' 
			
			},
			
		}		
	});
	
	//------------ Validaciones de Controles -------------------------------------
	function consultaUsuario(idControl) {
		var jqUsuario = eval("'#" + idControl + "'"); 
		var numUsuario = $(jqUsuario).val();	
		var usuarioBeanCon = {      
  				'usuarioID':numUsuario 
				};
		setTimeout("$('#cajaLista').hide();", 200);		
		if(numUsuario != '' && !isNaN(numUsuario) && esTab){
			usuarioServicio.consulta(catTipoConsultaUsuario.cancela,usuarioBeanCon,function(usuario) {  
						if(usuario!=null){
						//dwr.util.setValues(usuario);
						$('#nombreCompleto'). val(usuario.nombreCompleto);
						$('#clave'). val(usuario.clave); 
						$('#fechUltAcces'). val(usuario.fechUltAcces);
						$('#fechUltPass'). val(usuario.fechUltPass); 	 	  	  
							var status = usuario.estatus; 
						if(status == cancelado){  
						alert('El usuario ya esta Cancelado');	  
						deshabilitaBoton('actualizar', 'submit'); 	 
						}	 			
						}else{  
							alert("No Existe el Usuario");
						}    						
				});
			}
		}
		 
		
		function validaContrasenia(idControl) {
		var jqContra = eval("'#" + idControl + "'");
		var contrasen = $(jqContra).val();	
		//alert('contraseña'+contrasen);
		var usuarioBeanCon = {      
  						'usuarioID':$('#usuarioID').val(),
  						'contrasenia':contrasen
				};
		setTimeout("$('#cajaLista').hide();", 200);		 
		if(contrasen != '' && esTab){
			usuarioServicio.consulta(catTipoConsultaUsuario.contrasenia,usuarioBeanCon,function(usuario) {  
						if(usuario!=null){
						var incorr= 'La Contrasenia Actual es incorrecta:';
							if(usuario.contrasenia==incorr){
							alert(usuario.contrasenia);
							$('#contrasenia').focus(); 
							$('#contrasenia').select();
							}
						} 		 						
				});
			}
		}
		
		function validaNuevaContra(idControl) { 
		var jqconfContra = eval("'#" + idControl + "'");
		var confContra = $(jqconfContra).val();	
		var nContra= $('#nuevaContra').val();  
			if(confContra!=nContra){    
			alert('Las contrasenias no coinciden');
			$('#nuevaContra').focus();
			$('#nuevaContra').select();
			}
		}
			
							
			
		
});