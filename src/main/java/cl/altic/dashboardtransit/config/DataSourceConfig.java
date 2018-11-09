package cl.altic.dashboardtransit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

	@Bean(name = "datos_comunesDs")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.datos-comunes")
	public DataSource datos_comunesDs() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "datos_comunesJdbc")
	public JdbcTemplate datos_comunesJdbc(@Qualifier("datos_comunesDs") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_antofagasta_2010Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-antofagasta-2010")
	public DataSource eod_antofagasta_2010Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_antofagasta_2010Jdbc")
	public JdbcTemplate eod_antofagasta_2010Jdbc(@Qualifier("eod_antofagasta_2010Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_gran_concepcion_2015Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-gran-concepcion-2015")
	public DataSource eod_gran_concepcion_2015Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_gran_concepcion_2015Jdbc")
	public JdbcTemplate eod_gran_concepcion_2015Jdbc(@Qualifier("eod_gran_concepcion_2015Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_gran_valparaiso_2014Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-gran-valparaiso-2014")
	public DataSource eod_gran_valparaiso_2014Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_gran_valparaiso_2014Jdbc")
	public JdbcTemplate eod_gran_valparaiso_2014Jdbc(@Qualifier("eod_gran_valparaiso_2014Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_iquique_alto_hospicio_2010Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-iquique-alto-hospicio-2010")
	public DataSource eod_iquique_alto_hospicio_2010Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_iquique_alto_hospicio_2010Jdbc")
	public JdbcTemplate eod_iquique_alto_hospicio_2010Jdbc(
			@Qualifier("eod_iquique_alto_hospicio_2010Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_la_serena_coquimbo_2010Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-la-serena-coquimbo-2010")
	public DataSource eod_la_serena_coquimbo_2010Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_la_serena_coquimbo_2010Jdbc")
	public JdbcTemplate eod_la_serena_coquimbo_2010Jdbc(@Qualifier("eod_la_serena_coquimbo_2010Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_puerto_montt_2014Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-puerto-montt-2014")
	public DataSource eod_puerto_montt_2014Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_puerto_montt_2014Jdbc")
	public JdbcTemplate eod_puerto_montt_2014Jdbc(@Qualifier("eod_puerto_montt_2014Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_santiago_2003Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-santiago-2003")
	public DataSource eod_santiago_2003Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_santiago_2003Jdbc")
	public JdbcTemplate eod_santiago_2003Jdbc(@Qualifier("eod_santiago_2003Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_santiago_2012Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-santiago-2012")
	public DataSource eod_santiago_2012Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_santiago_2012Jdbc")
	public JdbcTemplate eod_santiago_2012Jdbc(@Qualifier("eod_santiago_2012Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_temuco_2013Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-temuco-2013")
	public DataSource eod_temuco_2013Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_temuco_2013Jdbc")
	public JdbcTemplate eod_temuco_2013Jdbc(@Qualifier("eod_temuco_2013Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "eod_valdivia_2013Ds")
	@ConfigurationProperties(prefix = "spring.datasource.eod-valdivia-2013")
	public DataSource eod_valdivia_2013Ds() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "eod_valdivia_2013Jdbc")
	public JdbcTemplate eod_valdivia_2013Jdbc(@Qualifier("eod_valdivia_2013Ds") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "gtfs_santiagoDs")
	@ConfigurationProperties(prefix = "spring.datasource.gtfs-santiago")
	public DataSource gtfs_santiagoDs() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "gtfs_santiagoJdbc")
	public JdbcTemplate gtfs_santiagoJdbc(@Qualifier("gtfs_santiagoDs") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "seguridad_vialDs")
	@ConfigurationProperties(prefix = "spring.datasource.seguridad-vial")
	public DataSource seguridad_vialDs() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "seguridad_vialJdbc")
	public JdbcTemplate seguridad_vialJdbc(@Qualifier("seguridad_vialDs") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "transporte_privadoDs")
	@ConfigurationProperties(prefix = "spring.datasource.transporte-privado")
	public DataSource transporte_privadoDs() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "transporte_privadoJdbc")
	public JdbcTemplate transporte_privadoJdbc(@Qualifier("transporte_privadoDs") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "transporte_publico_santiagoDs")
	@ConfigurationProperties(prefix = "spring.datasource.transporte-publico-santiago")
	public DataSource transporte_publico_santiagoDs() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "transporte_publico_santiagoJdbc")
	public JdbcTemplate transporte_publico_santiagoJdbc(@Qualifier("transporte_publico_santiagoDs") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}

	@Bean(name = "bicicletaDs")
	@ConfigurationProperties(prefix = "spring.datasource.bicicleta")
	public DataSource bicicletaDs() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "bicicletaJdbc")
	public JdbcTemplate bicicletaJdbc(@Qualifier("bicicletaDs") DataSource myDs) {
		return new JdbcTemplate(myDs);
	}
}
