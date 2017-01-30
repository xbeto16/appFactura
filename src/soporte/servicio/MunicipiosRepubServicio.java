package soporte.servicio;

import general.servicio.BaseServicio;

import java.util.List;

import soporte.bean.MunicipiosRepubBean;
import soporte.dao.MunicipiosRepubDAO;



public class MunicipiosRepubServicio extends BaseServicio {

	//---------- Variables ------------------------------------------------------------------------
	MunicipiosRepubDAO municipiosRepubDAO = null;

	//---------- Tipod de Consulta ----------------------------------------------------------------
	public static interface Enum_Con_Municipios {
		int principal = 1;
		int foranea = 2;		
	}

	public static interface Enum_Lis_Municipios {
		int principal = 1;
	}

	public MunicipiosRepubServicio () {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MunicipiosRepubBean consulta(int tipoConsulta,String estadoID, String municipioID){
		MunicipiosRepubBean municipios = null;
		
		switch(tipoConsulta){
		case Enum_Con_Municipios.principal:
			municipios = municipiosRepubDAO.consultaPrincipal(Integer.parseInt(estadoID),Integer.parseInt(municipioID),Enum_Con_Municipios.principal);
		break;
		case Enum_Con_Municipios.foranea:
			municipios = municipiosRepubDAO.consultaForanea(Integer.parseInt(estadoID),Integer.parseInt(municipioID), Enum_Con_Municipios.foranea);
		break;
		}
		
		return municipios;
	}
	

	
	public List lista(int tipoLista,MunicipiosRepubBean municipios){		
		List listaMunicipios = null;
		switch (tipoLista) {
			case Enum_Lis_Municipios.principal:		
				listaMunicipios=  municipiosRepubDAO.listaMunicipios(municipios,tipoLista);				
				break;				
		}		
		return listaMunicipios;
	}
	
	//------------------ Geters y Seters ------------------------------------------------------	
	public void setMunicipiosRepubDAO(MunicipiosRepubDAO municipiosRepubDAO) 
	{              
		this.municipiosRepubDAO = municipiosRepubDAO;
	}	
	

}
