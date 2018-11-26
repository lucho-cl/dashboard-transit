package cl.altic.dashboardtransit.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.altic.dashboardtransit.model.Cuadro;
import cl.altic.dashboardtransit.model.Region;
import cl.altic.dashboardtransit.service.DatosComunesService;

@Controller
public class DashboardController {

    Logger logger = LoggerFactory.getLogger(DashboardController.class);
	private static final int REGION_INICIAL = 13;
	@Autowired
	private DatosComunesService datosComunesService;
	@Value("${spring.application.name}")
	String appName;
	
	private List<Region> regiones = new ArrayList<Region>();
	
	private List<Cuadro> cuadros = new ArrayList<Cuadro>();
	private Cuadro cuadro = new Cuadro();

	@PostConstruct
    public void init() throws Exception {
		cuadro.setRegion(REGION_INICIAL);;
//		regiones.add(new Region(13, "Metropolitanta"));
//		regiones.add(new Region(15, "Arica y Parinacota"));
//		regiones.add(new Region(1, "Tarapacá"));
//		regiones.add(new Region(2, "Antofagasta"));
////		regiones.add(new Region(3, "Atacama"));
//		regiones.add(new Region(4, "Coquimbo"));
//		regiones.add(new Region(5, "Valparaíso"));
//		regiones.add(new Region(6, "Del Libertador General Bernardo O’Higgins"));
//		regiones.add(new Region(7, "Maule"));
//		regiones.add(new Region(8, "Bío Bío"));
//		regiones.add(new Region(9, "Araucanía"));
//		regiones.add(new Region(14, "Los Ríos"));
//		regiones.add(new Region(10, "Los Lagos"));
		
		cuadros.add(new Cuadro(1,"Encuesta Origen Destino", "Viajes diarios en la región", "132k", "tooltip", "fas fa-user-check"));
		cuadros.add(new Cuadro(2,"Demanda Transporte Publico", "Promedio de transbordos en Transantiago", "1,87", "tooltip", "fas fa-users"));
		cuadros.add(new Cuadro(3,"Oferta Transporte Publico", "Distintos servicios de metro y buses en Santiago", "17", "tooltip", "fas fa-bus"));
		cuadros.add(new Cuadro(4,"Performance Transporte Publico", "Velocidad promedio de buses", "54km/h", "tooltip", "fas fa-chart-bar"));
		cuadros.add(new Cuadro(5,"Transporte Privado", "Tasa de motorización", "59%", "tooltip", "fas fa-car-alt"));
		cuadros.add(new Cuadro(6,"Transporte No Motorizado", "Kilómetros de ciclovías", "10,4km", "tooltip", "fas fa-bicycle"));
		cuadros.add(new Cuadro(7,"Seguridad Vial", "Fallecidos en el año 2017", "654", "tooltip", "fas fa-car-crash"));
	}
	
	@GetMapping("/")
	public String home(@RequestParam(name="idRegion", required=false) String idRegion, Model model) {
		if (null==idRegion) {
			cuadro.setRegion(REGION_INICIAL);
		}else {
			cuadro.setRegion(Integer.parseInt(idRegion));
		}
//		cuadros = datosComunesService.getCuadrosByRegion(cuadro);
		model.addAttribute("appName", appName);
		model.addAttribute("regionSeleccionada", cuadro.getRegion());
//		model.addAttribute("regiones", regiones);
//		model.addAttribute("cuadros", cuadros);
		return "home";
	}
	
	@PostMapping("/reportes")
	public String homePage(@RequestParam(name="regionSelect", required=false) String regionSelect, Model model) {
		if (null==regionSelect) {
			cuadro.setRegion(REGION_INICIAL);
		}else {
			cuadro.setRegion(Integer.parseInt(regionSelect));
		}
		cuadros = datosComunesService.getCuadrosByRegion(cuadro);
		model.addAttribute("appName", appName);
		model.addAttribute("regionSeleccionada", cuadro.getRegion());
		model.addAttribute("regiones", regiones);
		model.addAttribute("cuadros", cuadros);
		return "home";
	}
//	@GetMapping("/home")
//	public String homePage(@RequestParam(name="idRegion", required=false) String idRegion, Model model) {
//		if (null==idRegion) {
//			cuadro.setRegion(REGION_INICIAL);
//		}else {
//			cuadro.setRegion(Integer.parseInt(idRegion));
//		}
//		cuadros = datosComunesService.getCuadrosByRegion(cuadro);
//		model.addAttribute("appName", appName);
//		model.addAttribute("regiones", regiones);
//		model.addAttribute("cuadros", cuadros);
//		return "home";
//	}
	
    @GetMapping("/detail")
    public String detail(@RequestParam(name="idCuadro", required=true) String idCuadro, @RequestParam(name="idRegion", required=true) String idRegion, Model model) {
    	logger.info("idCuadro: "+idCuadro);
    	logger.info("idRegion: "+idRegion);
		model.addAttribute("cuadros", cuadros);
    	Cuadro selected = cuadros.stream()
    			  .filter(cuadro -> idCuadro.equals(cuadro.getId().toString()))
    			  .findAny().get();
//        model.addAttribute("name", selected.getNombre());
//        model.addAttribute("class", selected.getClass());
        model.addAttribute("selected", selected);
		model.addAttribute("regionSeleccionada", idRegion);
//        valor que utilizo para seleccionar los graficos q se moestrarán
        model.addAttribute("graphs", "div.graph_"+idRegion+"_"+selected.getId());
        List<String> textosAyuda = datosComunesService.getTextosAyuda(Integer.valueOf(idRegion), Integer.valueOf(idCuadro));
        for (int i = 0; i < textosAyuda.size(); i++) {
        	model.addAttribute("help"+(i+1), textosAyuda.get(i));
		}
        return "detail";
    }
}
