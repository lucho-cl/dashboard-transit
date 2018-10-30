package cl.altic.dashboardtransit.dao;

import java.util.List;

import cl.altic.dashboardtransit.model.Cuadro;

public interface DatosComunesDao {
	public List<Cuadro> getCuadrosByRegion(Cuadro cuadro);
}
