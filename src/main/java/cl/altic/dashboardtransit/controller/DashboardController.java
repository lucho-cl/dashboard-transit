package cl.altic.dashboardtransit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.altic.dashboardtransit.model.Cuadro;
import cl.altic.dashboardtransit.service.DatosComunesService;

@Controller
public class DashboardController {

	@Autowired
	private DatosComunesService datosComunesService;
	@Value("${spring.application.name}")
	String appName;
	
	static Integer region = 13;
	
	private List<Cuadro> cuadrosList = new ArrayList<Cuadro>();
	private Cuadro cuadro = new Cuadro();

	@PostConstruct
    public void init() throws Exception {
		cuadro.setRegion(region);;
//		cuadrosList.add(new Cuadro(1,"Encuesta Origen Destino", "Viajes diarios en la región", "132k", "tooltip"));
//		cuadrosList.add(new Cuadro(2,"Demanda Transporte Publico", "Promedio de transbordos en Transantiago", "1,87", "tooltip"));
//		cuadrosList.add(new Cuadro(3,"Oferta Transporte Publico", "Distintos servicios de metro y buses en Santiago", "17", "tooltip"));
//		cuadrosList.add(new Cuadro(4,"Performance Transporte Publico", "Velocidad promedio de buses", "54km/h", "tooltip"));
//		cuadrosList.add(new Cuadro(5,"Transporte Privado", "Tasa de motorización", "59%", "tooltip"));
//		cuadrosList.add(new Cuadro(6,"Transporte No Motorizado", "Kilómetros de ciclovías", "10,4km", "tooltip"));
//		cuadrosList.add(new Cuadro(7,"Seguridad Vial", "Fallecidos en el año 2017", "654", "tooltip"));
	}
	
	@GetMapping("/")
	public String homePage(Model model) {
		cuadrosList = datosComunesService.getCuadrosByRegion(cuadro);
		model.addAttribute("appName", appName);
		model.addAttribute("cuadros", cuadrosList);
		return "home";
	}
	
    @GetMapping("/detail")
    public String detail(@RequestParam(name="idCuadro", required=true) String idCuadro, Model model) {
		model.addAttribute("cuadros", cuadrosList);
    	Cuadro selected = cuadrosList.stream()
    			  .filter(cuadro -> idCuadro.equals(cuadro.getId().toString()))
    			  .findAny().get();
        model.addAttribute("name", selected.getNombre());
        model.addAttribute("graphs", "div.graph"+region.toString()+selected.getId());
        return "detail";
    }
}
