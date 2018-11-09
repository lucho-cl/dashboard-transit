package cl.altic.dashboardtransit.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.altic.dashboardtransit.model.Cuadro;

public class CuadroRowMapper implements RowMapper<Cuadro> {

	@Override
	public Cuadro mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cuadro cuadro = new Cuadro();
		
		cuadro.setId(rs.getInt("vista_id"));
		cuadro.setRegion(rs.getInt("region_id"));
		cuadro.setNombre(rs.getString("vista"));
		cuadro.setTexto(rs.getString("texto_valor"));
		cuadro.setTooltip(rs.getString("texto_tooltip"));
		cuadro.setBaseValor(rs.getString("base_de_datos_valor"));
		cuadro.setQueryValor(rs.getString("query_valor"));
		cuadro.setValor("test123");
		
		return cuadro;
	}

}
