package cl.altic.dashboardtransit.model;

//import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

//	TODO: revisar si poner aquí un default 13 o mantener esta validacion mandando el dato en duro desde la página
//    @NotBlank(message = "Select a region!")
	private
	String regionSelect;

	public String getRegionSelect() {
		return regionSelect;
	}

	public void setRegionSelect(String regionSelect) {
		this.regionSelect = regionSelect;
	}
}
