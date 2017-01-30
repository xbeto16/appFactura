function centraDiv(divCentrar, contenedor) {
	jqConDivCentrar = eval("'#" + divCentrar + "'");
	jqConContenedor = eval("'#" + contenedor + "'");

	$(jqConDivCentrar).css(
			{
				position : 'absolute',
				left : ($(jqConContenedor).outerWidth() - $(jqConDivCentrar)
						.outerWidth()) / 2,
				top : ($(jqConContenedor).outerHeight() - $(jqConDivCentrar)
						.outerHeight()) / 2
			});
};

function cerosIzq(control, longitud) {
	expr = /\s/;
	control.value = control.value.replace(expr, "0");
	if (control.value == "")
		control.value = 0;
	if (isNaN(control.value) == true)
		control.value = 0;
	else
		control.value = parseInt(parseFloat(control.value));
	while (control.value.length < longitud) {
		control.value = "0" + control.value;
	}
}

/** Calculo entre dos fechas apertura y la fecha que escoja el cliente* */

function diferenciaDias(fechaInicio, fechaFin) {

	// Obtiene los datos del formulario
	CadenaFecha1 = fechaInicio;
	CadenaFecha2 = fechaFin;

	// Obtiene dia, mes y año
	var fecha1 = new fecha(CadenaFecha1);
	var fecha2 = new fecha(CadenaFecha2);

	// Obtiene objetos Date
	var miFecha1 = new Date(fecha1.anio, fecha1.mes - 1, fecha1.dia);
	var miFecha2 = new Date(fecha2.anio, fecha2.mes - 1, fecha2.dia);

	// Resta fechas y redondea
	var diferencia = miFecha2.getTime() - miFecha1.getTime();
	var dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));

	// var segundos = Math.floor(diferencia / 1000);

	return dias;

}

function fecha(cadena) {

	var separador = "-";

	if (cadena.indexOf(separador) != -1) {

		var pos1 = 0;
		var pos2 = cadena.indexOf(separador, pos1 + 3);
		var pos3 = cadena.indexOf(separador, pos2 + 2);

		this.anio = cadena.substring(pos1, pos2);
		this.mes = cadena.substring(pos2 + 1, pos3);
		this.dia = cadena.substring(pos3 + 1, cadena.length);

	} else {
		this.dia = 0;
		this.mes = 0;
		this.anio = 0;
	}
}

function convertirFecha( cadena ) {
	
	var date = new Date();
	var separador = "-";
	
	var pos1 = 0;
	var pos2 = cadena.indexOf(separador, pos1 + 3);
	var pos3 = cadena.indexOf(separador, pos2 + 2);
	
	var anio = cadena.substring(pos1, pos2);
	var mes = cadena.substring(pos2 + 1, pos3);
	var dia = cadena.substring(pos3 + 1, cadena.length);
	
	date.setYear(anio);
	date.setMonth(mes-1);
	date.setDate(dia);
	
		
	return date.getTime();
}

	//Formatea un Fecha y la convierte en una Cadena o String	
	//El formato Default es "año-mes-dia"
	function formateaFechaString(fecha){
		return formatDate(fecha, "yyyy-MM-dd");
	}

function ultimoDiaDelMes(Mes, Anio) {
     var ultimoDia = 30;
     if (Mes == 1 || Mes == 3 || Mes == 5 || Mes == 7
         || Mes == 8 || Mes == 10 || Mes == 12){
          ultimoDia = 31;
     }
     if (Mes == 2) {                  
         if (Anio % 4 == 0 && (Anio % 400 == 0 || Anio % 100 != 0))
              ultimoDia = 29;
         else
             ultimoDia = 28;
     }
     return ultimoDia;
}
