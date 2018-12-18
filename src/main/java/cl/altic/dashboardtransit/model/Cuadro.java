package cl.altic.dashboardtransit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class Cuadro {

	private Integer id;
	private Integer region;
	private String nombre;
	private String texto;
	private String valor;
	private String tooltip;
	private String baseValor;
	private String queryValor;
	private String icono;
	private boolean habilitado;
	private String prev;
	private String next;
	
	public Cuadro(Integer id, Integer region, String nombre, String texto, String icono, boolean habilitado) {
		super();
		this.id = id;
		this.region = region;
		this.nombre = nombre;
		this.texto = texto;
		this.icono = icono;
		this.habilitado = habilitado;
		this.valor = "";
		this.tooltip = "";
	}
	public Cuadro(int id, String nombre, String texto, String valor, String tooltip, String icono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.texto = texto;
		this.valor = valor;
		this.tooltip = tooltip;
		this.icono = icono;
	}
	public Cuadro(Integer id, Integer region, String nombre, String texto, String valor, String tooltip,
			String baseValor, String queryValor, String icono) {
		super();
		this.id = id;
		this.region = region;
		this.nombre = nombre;
		this.texto = texto;
		this.valor = valor;
		this.tooltip = tooltip;
		this.baseValor = baseValor;
		this.queryValor = queryValor;
		this.icono = icono;
	}
	
}
