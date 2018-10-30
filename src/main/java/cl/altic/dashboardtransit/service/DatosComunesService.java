package cl.altic.dashboardtransit.service;

import java.util.List;

import cl.altic.dashboardtransit.model.Cuadro;

public interface DatosComunesService {
	public List<Cuadro> getCuadrosByRegion(Cuadro cuadro);
}
