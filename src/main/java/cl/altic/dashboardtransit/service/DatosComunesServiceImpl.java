package cl.altic.dashboardtransit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.altic.dashboardtransit.dao.DatosComunesDao;
import cl.altic.dashboardtransit.model.Cuadro;

@Service
public class DatosComunesServiceImpl implements DatosComunesService {
	
	@Autowired
	private DatosComunesDao datosComunesDao;

	public List<Cuadro> getCuadrosByRegion(Cuadro cuadro){
		List<Cuadro> lista = datosComunesDao.getCuadrosByRegion(cuadro);
		return lista ;
	}

	@Override
	public List<String> getTextosAyuda(int idRegion, int idVista) {
		return datosComunesDao.getTextosAyuda(idRegion, idVista);
	}
}
