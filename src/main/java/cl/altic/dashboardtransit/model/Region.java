package cl.altic.dashboardtransit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class Region {
	
	private int id;
	private String nombre;
	private boolean estado;
	public Region(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
}
