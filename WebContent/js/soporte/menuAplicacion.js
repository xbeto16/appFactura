$(document).ready(function() {
	var parametroBean = consultaParametrosSession();  
	var clav= parametroBean.claveUsuario;
	consultaSesion();
	
	$('#listaMenu').menuTree({
		animation: true,
		handler: 'slideToggle',
		anchor: 'a[href="javascript:"]',
		trace: false
	});
	
	$(window).bind('beforeunload', function(){
		 window.location='logout';  
		//return 'Vas a abandonar esta pagina. Si has hecho algun cambio sin grabar vas a perder todos los datos';		
	});
		 		
});

// Funcion para Cerrar la Session del Usuario	
function cerrarSession(){ 
	var parametroBean = consultaParametrosSession();
	var clave =parametroBean.claveUsuario;
	
	if(clave==''){
		alert('La sesion ya caduco'); 
		location.href ='sesionExpirada.htm?claveUsu='+cl+'';  
	}else{
		location.href ='logout';
	}
			
}   
