package cl.altic.dashboardtransit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.altic.dashboardtransit.model.Fuente;

public class BibliografiaRowMapper implements RowMapper<Fuente> {

	@Override
	public Fuente mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Fuente.builder()
//				.id(rs.getInt("vista_id"))
				.nombre(rs.getString("nombre_fuente"))
				.link(rs.getString("link_fuente")).build();
	}

}
