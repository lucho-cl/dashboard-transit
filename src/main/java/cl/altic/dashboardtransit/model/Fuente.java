package cl.altic.dashboardtransit.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fuente {

	private Integer id;
	private String nombre;
	private String link;
}
