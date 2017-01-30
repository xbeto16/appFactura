package herramientas;

public class Constantes {
	
	public static String salidaNO = "N";
	public static String salidaSI = "S";
	
	public static String SALTO_LINEA = "\n";
	
	public static String RUTA_IMAGENES = 	System.getProperty("file.separator") + "opt" +
	  										System.getProperty("file.separator") + "tomcat6" +
	  										System.getProperty("file.separator") + "webapps" +
					  						System.getProperty("file.separator") + "CRMSim" +
					  						System.getProperty("file.separator") + "images";
	
	public static String MSG_ERROR = "Estimado Usuario(a), Ha ocurrido una falla en el sistema, " +
										"estamos trabajando para resolverla. Disculpe las molestias que " +
										"esto le ocasiona.";
	
	public static String LEYENDA_ESTADO_CUENTA = "Pagare";
	
	
	public static String 	STRING_VACIO 	= "";
	public static String 	STRING_CERO 	= "0";
	public static int 		ENTERO_CERO 	= 0;
	public static String 	FECHA_VACIA		="1900-01-01";
	
	public static String PARENTESIS_ABRE = "(";
	public static String PARENTESIS_CIERRA = ")";
	public static String ASTERISCO = "****";
	
	/* Constantes para Mensajes de Correo */
	
	public static String htmlErrorReporte = ""+
	"<TABLE WIDTH='600' style='border:solid 1px #242424' CELLSPACING='1' CELLPADDING='1' ALIGN='left' VALIGN='TOP'>" +
	"<TR><TD>" + 
	"<TABLE BORDER='0' WIDTH='100%' CELLSPACING='1'  ALIGN='CENTER' VALIGN='CENTER' CELLPADDING='1'>" +
		"<TR>" +
			"<TD ALIGN='CENTER' BGCOLOR='0182C4' STYLE='font-family: arial,helvetica,tahoma,verdana; font-weight: bold;font-size: 13px;color: CCCCCC ;text-decoration: none;'>" +
				"Error al Procesar el Reporte" +
			"</TD>" +
		"</TR>" +
		"<TR>" +
			"<TD ALIGN='LEFT' BGCOLOR='FFFFFF' STYLE='font-family: arial,helvetica,tahoma,verdana; font-weight: normal;font-size: 11px;color: 333333 ;text-decoration: none;'>" +
				"Ha ocurrido Un Error al generar el Reporte. Le sugerimos contactar a la Mesa de Ayuda o Help Desk " +
			"</TD>" +
		"</TR>" +
		"<TR>" +
			"<TD>&nbsp;</TD>" +
		"</TR>" +
	"</TABLE>" +
	"</TD>" +
	"</TR>" +
	"</TABLE>";

}
