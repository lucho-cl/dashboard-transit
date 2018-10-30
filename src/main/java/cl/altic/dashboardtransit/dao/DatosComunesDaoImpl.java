package cl.altic.dashboardtransit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.altic.dashboardtransit.mapper.CuadroRowMapper;
import cl.altic.dashboardtransit.model.Cuadro;

@Repository
public class DatosComunesDaoImpl implements DatosComunesDao {

	@Autowired
	// @Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcTemplate;

	public List<Cuadro> getCuadrosByRegion(Cuadro cuadro) {
		List<Cuadro> lista = null;
		StringBuilder qry = new StringBuilder("SELECT vista, vista_id, region, region_id, base_de_datos_valor, ");
		qry.append("query_valor, texto_valor, texto_tooltip, ciudad_eod_gtfs ");
		qry.append("FROM public.datos_home ");
		qry.append("WHERE region_id = ? ");
//		qry.append("AND vista_id = ? ");
		qry.append(";");

		lista = jdbcTemplate.query(qry.toString(), new Object[] {cuadro.getRegion()}, new CuadroRowMapper());

		return lista;
	}
}
