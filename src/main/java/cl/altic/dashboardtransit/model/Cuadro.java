package cl.altic.dashboardtransit.model;

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
	public Cuadro() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRegion() {
		return region;
	}
	public void setRegion(Integer region) {
		this.region = region;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public String getBaseValor() {
		return baseValor;
	}
	public void setBaseValor(String baseValor) {
		this.baseValor = baseValor;
	}
	public String getQueryValor() {
		return queryValor;
	}
	public void setQueryValor(String queryValor) {
		this.queryValor = queryValor;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
}
