package cl.altic.dashboardtransit.model;

import lombok.Data;

@Data
public class ContactoCommand {
	private String nombre;
	private String email;
	private String asunto;
	private String mensaje;
}
