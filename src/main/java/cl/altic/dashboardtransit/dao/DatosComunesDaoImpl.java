package cl.altic.dashboardtransit.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.altic.dashboardtransit.mapper.BibliografiaRowMapper;
import cl.altic.dashboardtransit.mapper.CuadroRowMapper;
import cl.altic.dashboardtransit.mapper.CuadroSVRowMapper;
import cl.altic.dashboardtransit.model.Cuadro;
import cl.altic.dashboardtransit.model.Fuente;

@Repository
public class DatosComunesDaoImpl implements DatosComunesDao {

    Logger logger = LoggerFactory.getLogger(DatosComunesDaoImpl.class);
	@Autowired
	@Qualifier("datos_comunesJdbc")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("eod_antofagasta_2010Jdbc")
	private JdbcTemplate eod_antofagasta_2010Jdbc;
	@Autowired
	@Qualifier("eod_gran_concepcion_2015Jdbc")
	private JdbcTemplate eod_gran_concepcion_2015Jdbc;
	@Autowired
	@Qualifier("eod_gran_valparaiso_2014Jdbc")
	private JdbcTemplate eod_gran_valparaiso_2014Jdbc;
	@Autowired
	@Qualifier("eod_iquique_alto_hospicio_2010Jdbc")
	private JdbcTemplate eod_iquique_alto_hospicio_2010Jdbc;
	@Autowired
	@Qualifier("eod_la_serena_coquimbo_2010Jdbc")
	private JdbcTemplate eod_la_serena_coquimbo_2010Jdbc;
	@Autowired
	@Qualifier("eod_puerto_montt_2014Jdbc")
	private JdbcTemplate eod_puerto_montt_2014Jdbc;
	@Autowired
	@Qualifier("eod_santiago_2003Jdbc")
	private JdbcTemplate eod_santiago_2003Jdbc;
	@Autowired
	@Qualifier("eod_santiago_2012Jdbc")
	private JdbcTemplate eod_santiago_2012Jdbc;
	@Autowired
	@Qualifier("eod_temuco_2013Jdbc")
	private JdbcTemplate eod_temuco_2013Jdbc;
	@Autowired
	@Qualifier("eod_valdivia_2013Jdbc")
	private JdbcTemplate eod_valdivia_2013Jdbc;
	@Autowired
	@Qualifier("gtfs_santiagoJdbc")
	private JdbcTemplate gtfs_santiagoJdbc;
	@Autowired
	@Qualifier("seguridad_vialJdbc")
	private JdbcTemplate seguridad_vialJdbc;
	@Autowired
	@Qualifier("transporte_privadoJdbc")
	private JdbcTemplate transporte_privadoJdbc;
	@Autowired
	@Qualifier("transporte_publico_santiagoJdbc")
	private JdbcTemplate transporte_publico_santiagoJdbc;
	@Autowired
	@Qualifier("bicicletaJdbc")
	private JdbcTemplate bicicletaJdbc;

	public List<Cuadro> getCuadrosByRegion(Cuadro cuadro) {
		List<Cuadro> lista = null;
		StringBuilder qry = new StringBuilder("SELECT vista, vista_id, region, region_id, base_de_datos_valor, ");
		qry.append("query_valor, texto_valor, texto_tooltip, ciudad_eod_gtfs ");
		qry.append("FROM public.datos_home ");
		qry.append("WHERE region_id = ? ");
		// qry.append("AND vista_id = ? ");
		qry.append(";");

		lista = jdbcTemplate.query(qry.toString(), new Object[] { cuadro.getRegion() }, new CuadroRowMapper());

		// itero para obtener el detalle a mostrar en cada cuadro
		for (Cuadro cuadro2 : lista) {
			String baseValor = cuadro2.getBaseValor();
			String queryValor = cuadro2.getQueryValor();
			String valor = null;
			logger.info("Cuadro: "+cuadro2.getNombre());
			logger.info("consultando en: "+baseValor);
			logger.info("query: "+queryValor);
			try {
				switch (baseValor) {
				case "eod_antofagasta_2010":
					valor = eod_antofagasta_2010Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_gran_concepcion_2015":
					valor = eod_gran_concepcion_2015Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_gran_valparaiso_2014":
					valor = eod_gran_valparaiso_2014Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_iquique_alto_hospicio_2010":
					valor = eod_iquique_alto_hospicio_2010Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_la_serena_coquimbo_2010":
					valor = eod_la_serena_coquimbo_2010Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_puerto_montt_2014":
					valor = eod_puerto_montt_2014Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_santiago_2003":
					valor = eod_santiago_2003Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_santiago_2012":
					valor = eod_santiago_2012Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_temuco_2013":
					valor = eod_temuco_2013Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_valdivia_2013":
					valor = eod_valdivia_2013Jdbc.queryForObject(queryValor, String.class);
					break;
				case "gtfs_santiago":
					valor = gtfs_santiagoJdbc.queryForObject(queryValor, String.class);
					break;
				case "seguridad_vial":
					valor = seguridad_vialJdbc.queryForObject(queryValor, String.class);
					break;
				case "transporte_privado":
					valor = transporte_privadoJdbc.queryForObject(queryValor, String.class);
					break;
				case "transporte_publico_santiago":
					valor = transporte_publico_santiagoJdbc.queryForObject(queryValor, String.class);
					break;
				case "bicicleta":
					valor = bicicletaJdbc.queryForObject(queryValor, String.class);
					break;
				default:
					valor = "";
					break;
				}
			}catch(Exception e){
				valor = "";
				logger.error("ERROR: "+e.getMessage());
			}
			logger.info("Valor obtenido: "+valor);
			cuadro2.setValor(valor);
		}

		return lista;
	}
	
	public List<String> getTextosAyuda(int idRegion, int idVista) {
		List<Cuadro> lista = null;
		List<String> textos = new ArrayList<String>();
		StringBuilder qry = new StringBuilder("SELECT vista_id, dato, dato_id, region_id, ");
		qry.append("base_de_datos_valor, query_valor, texto_valor, texto_tooltip ");
		qry.append("FROM public.datos_otras_vistas ");
		qry.append("WHERE region_id = ? ");
		qry.append("AND vista_id = ? ");
		qry.append(";");

		lista = jdbcTemplate.query(qry.toString(), new Object[] { idRegion, idVista }, new CuadroSVRowMapper());

		// itero para obtener el detalle a mostrar en cada cuadro
		for (Cuadro cuadro2 : lista) {
			String baseValor = cuadro2.getBaseValor();
			String queryValor = cuadro2.getQueryValor();
			String valor = null;
//			logger.info("Cuadro: " + cuadro2.getNombre());
			logger.info("consultando en: " + baseValor);
			logger.info("query: " + queryValor);
			try {
				switch (baseValor) {
				case "eod_antofagasta_2010":
					valor = eod_antofagasta_2010Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_gran_concepcion_2015":
					valor = eod_gran_concepcion_2015Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_gran_valparaiso_2014":
					valor = eod_gran_valparaiso_2014Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_iquique_alto_hospicio_2010":
					valor = eod_iquique_alto_hospicio_2010Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_la_serena_coquimbo_2010":
					valor = eod_la_serena_coquimbo_2010Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_puerto_montt_2014":
					valor = eod_puerto_montt_2014Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_santiago_2003":
					valor = eod_santiago_2003Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_santiago_2012":
					valor = eod_santiago_2012Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_temuco_2013":
					valor = eod_temuco_2013Jdbc.queryForObject(queryValor, String.class);
					break;
				case "eod_valdivia_2013":
					valor = eod_valdivia_2013Jdbc.queryForObject(queryValor, String.class);
					break;
				case "gtfs_santiago":
					valor = gtfs_santiagoJdbc.queryForObject(queryValor, String.class);
					break;
				case "seguridad_vial":
					valor = seguridad_vialJdbc.queryForObject(queryValor, String.class);
					break;
				case "transporte_privado":
					valor = transporte_privadoJdbc.queryForObject(queryValor, String.class);
					break;
				case "transporte_publico_santiago":
					valor = transporte_publico_santiagoJdbc.queryForObject(queryValor, String.class);
					break;
				case "bicicleta":
					valor = bicicletaJdbc.queryForObject(queryValor, String.class);
					break;
				default:
					valor = "";
					break;
				}
			} catch (Exception e) {
				valor = "";
				logger.error("ERROR: " + e.getMessage());
			}
			logger.info("Valor obtenido: " + valor);
			textos.add(valor);
		}
		return textos;
	}

	@Override
	public List<Fuente> getBibliografia() {
		List<Fuente> lista = null;
		StringBuilder qry = new StringBuilder("select\r\n");
		qry.append("	distinct(link_fuente),\r\n"); 
		qry.append("	nombre_fuente\r\n");
		qry.append("from\r\n");
		qry.append("	public.fuentes_informacion\r\n");
		qry.append("where\r\n");
		qry.append("	link_fuente like 'http://%'");
		qry.append("	or link_fuente like 'https://%'");
//		qry.append("query_valor, texto_valor, texto_tooltip, ciudad_eod_gtfs ");
//		qry.append("FROM public.datos_home ");
//		qry.append("WHERE region_id = ? ");
		// qry.append("AND vista_id = ? ");
		qry.append(";");

		lista = jdbcTemplate.query(qry.toString(), new BibliografiaRowMapper());

		return lista;
	}
	
}
