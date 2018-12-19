package cl.altic.dashboardtransit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.altic.dashboardtransit.model.ContactoCommand;
import cl.altic.dashboardtransit.model.Cuadro;
import cl.altic.dashboardtransit.model.Fuente;
import cl.altic.dashboardtransit.model.Region;
import cl.altic.dashboardtransit.service.DatosComunesService;
import cl.altic.dashboardtransit.service.SendMailService;

@Controller
public class DashboardController {

    Logger logger = LoggerFactory.getLogger(DashboardController.class);
	private static final int REGION_INICIAL = 13;
	@Autowired
	private DatosComunesService datosComunesService;
	@Autowired
	private SendMailService sendMailService;
	@Value("${spring.application.name}")
	String appName;
	
	private List<Region> regiones = new ArrayList<Region>();
	
	private List<Cuadro> cuadros = new ArrayList<Cuadro>();
//	private List<Cuadro> cuadrosFromDB = new ArrayList<Cuadro>();
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
		
//		cuadros.add(new Cuadro(1,"Encuesta Origen Destino", "Viajes diarios en la región", "132k", "tooltip", "fas fa-user-check"));
//		cuadros.add(new Cuadro(2,"Demanda Transporte Publico", "Promedio de transbordos en Transantiago", "1,87", "tooltip", "fas fa-users"));
//		cuadros.add(new Cuadro(3,"Oferta Transporte Publico", "Distintos servicios de metro y buses en Santiago", "17", "tooltip", "fas fa-bus"));
//		cuadros.add(new Cuadro(4,"Performance Transporte Publico", "Velocidad promedio de buses", "54km/h", "tooltip", "fas fa-chart-bar"));
//		cuadros.add(new Cuadro(5,"Transporte Privado", "Tasa de motorización", "59%", "tooltip", "fas fa-car-alt"));
//		cuadros.add(new Cuadro(6,"Transporte No Motorizado", "Kilómetros de ciclovías", "10,4km", "tooltip", "fas fa-bicycle"));
//		cuadros.add(new Cuadro(7,"Seguridad Vial", "Fallecidos en el año 2017", "654", "tooltip", "fas fa-car-crash"));
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
	
	/**
	 * Método que obtiene la información que se mostrará en el detalle del reporte seleccionado
	 * @param idCuadro
	 * @param idRegion
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
    @GetMapping("/detail")
    public String detail(@RequestParam(name="idCuadro", required=true) String idCuadro, @RequestParam(name="idRegion", required=true) String idRegion, Model model) throws NumberFormatException, IOException {
    	logger.info("idCuadro: "+idCuadro);
    	logger.info("idRegion: "+idRegion);
    	getCuadros(Integer.valueOf(idRegion));
/*
		cuadrosFromDB = datosComunesService.getCuadrosByRegion(cuadro);
        if (cuadrosFromDB.isEmpty()) {
            logger.debug("No se obtuvieron reportes de la base de datos");
        } else {
            logger.debug("Si se obtuvieron reportes de la base de datos");
        }
        cuadros.stream().forEach(c -> getInfoCuadro(c, cuadrosFromDB));
        */
		model.addAttribute("cuadros", cuadros);
    	Cuadro selected = cuadros.stream()
    			  .filter(cuadro -> idCuadro.equals(cuadro.getId().toString()))
    			  .findAny().get();
    	getPreviousNext(selected);
        model.addAttribute("selected", selected);
		model.addAttribute("regionSeleccionada", idRegion);
//        valor que utilizo para seleccionar los fragmentos de los gráficos q se moestrarán
        model.addAttribute("graphs", "div.graph_"+idRegion+"_"+selected.getId());
//        obtengo los textos de ayuda (variable) para el reporte seleccionado 
        List<String> textosAyuda = datosComunesService.getTextosAyuda(Integer.valueOf(idRegion), Integer.valueOf(idCuadro));
        for (int i = 0; i < textosAyuda.size(); i++) {
        	model.addAttribute("help"+(i+1), textosAyuda.get(i));
		}
        return "detail";
    }

	private void getPreviousNext(Cuadro cuadro) {
		int index = cuadros.indexOf(cuadro);
		Optional<Cuadro> prev;
		Optional<Cuadro> next;
//		if(index == -1) {
			//no existe, creo q esto no podría ocurrir, pero de todas formas lo dejaré
//		}else if(index==0){//entonces existe
			//estoy en el primer elemento
			prev = cuadros.subList(0, index).stream()
	    			  .filter(c -> c.isHabilitado())
	    			  .findFirst();
			next = cuadros.subList(index+1, cuadros.size()).stream()
					.filter(c -> c.isHabilitado())
					.findFirst();
//		}else if(index==cuadros.size()-1) {
			//estoy en el último elemento
//		}
			
		if(prev.isPresent()) {
		     cuadro.setPrev(prev.get().getId().toString());
		 } else {
			 //al home
			 cuadro.setPrev("home");
		 }
		if(next.isPresent()) {
			cuadro.setNext(next.get().getId().toString());
		} else {
			//al home
			cuadro.setNext("home");
		}
	}

	private void getCuadros(int region) throws IOException {
		cuadros = new ArrayList<Cuadro>();
		cuadros.add(new Cuadro(1, region, "Encuesta Origen Destino", "Viajes diarios en la región", "fas fa-user-check", existenGraficos(region,1)));
		cuadros.add(new Cuadro(2, region, "Demanda Transporte Publico", "Promedio de transbordos en Transantiago", "fas fa-users", existenGraficos(region,2)));
		cuadros.add(new Cuadro(3, region, "Oferta Transporte Publico", "Distintos servicios de metro y buses en Santiago", "fas fa-bus", existenGraficos(region,3)));
		cuadros.add(new Cuadro(4, region, "Performance Transporte Publico", "Velocidad promedio de buses", "fas fa-chart-bar", existenGraficos(region,4)));
		cuadros.add(new Cuadro(5, region, "Transporte Privado", "Tasa de motorización", "fas fa-car-alt", existenGraficos(region,5)));
		cuadros.add(new Cuadro(6, region, "Transporte No Motorizado", "Kilómetros de ciclovías", "fas fa-bicycle", existenGraficos(region,6)));
		cuadros.add(new Cuadro(7, region, "Seguridad Vial", "Fallecidos en el año 2017", "fas fa-car-crash", existenGraficos(region,7)));
	}

	/**
	 * 	método para evaluar si determinado reporte tiene gráficos y por lo tanto si se muestra habilitado o no
	 * @param idRegion
	 * @param idReporte
	 * @return
	 * @throws IOException
	 */
	private boolean existenGraficos(int idRegion, int idReporte) throws IOException {
        InputStream is = DashboardRestAPIs.class.getResourceAsStream("/templates/graphs.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
        	if(line.contains("graph_"+idRegion+"_"+idReporte)) {
				return true;
			}
        }
		return false;
	}
	
	/**
	 * método q hace el match de los reportes en duro con la información (si hay) obtenida de la base de datos para ellos
	 * @param c
	 * @param listaCuadros
	 * @return
	 */
//	private Object getInfoCuadro(Cuadro c, List<Cuadro> listaCuadros) {
//		try {
//			Cuadro cuadro = listaCuadros.stream().filter(cdb -> c.getId()==cdb.getId()).findAny().get();
//			c.setNombre(cuadro.getNombre());
//			c.setTexto(cuadro.getTexto());
//			c.setTooltip(cuadro.getTooltip());
//			c.setValor(cuadro.getValor());
//			
//		}catch (NoSuchElementException e) {
//			logger.debug("no hay info en la DB para el cuadro "+c.getId());
//		}
//		return null;
//	}
	

	@GetMapping("/bibliografia")
	public String bibliografia(Model model) {
//		List<Fuente> bibliografia = new ArrayList<Fuente>();
//		bibliografia.add(Fuente.builder().id(1).nombre("Google").link("http://www.google.cl").build());
		List<Fuente> bibliografia = datosComunesService.getBibliografia();
		model.addAttribute("bibliografia", bibliografia);
//		model.addAttribute("regionSeleccionada", cuadro.getRegion());
//		model.addAttribute("regiones", regiones);
//		model.addAttribute("cuadros", cuadros);
		return "bibliografia";
	}
	

	@PostMapping("/contacto")
	public String contactoSend(
			@ModelAttribute("command") ContactoCommand command,
			// WARN: BindingResult *must* immediately follow the Command.
			// https://stackoverflow.com/a/29883178/1626026
			BindingResult bindingResult,   
			Model model
//			,RedirectAttributes ra
			) {
		
		logger.debug("form submission.");
		
		if ( bindingResult.hasErrors() ) {
			return "contacto";
		}
		logger.info("ENVIANDO CORREO A: " +command.getNombre());

		try {
			sendMailService.sendMail(command);
			model.addAttribute("resultado", "Mensaje enviado, pronto nos pondremos en contacto");
		} catch (MessagingException e) {
			model.addAttribute("resultado", "No se pudo enviar mensaje, intente nuevamente");
//			e.printStackTrace();
		}
//		ra.addFlashAttribute("command", command);
		
		return "contacto-result";
	}
	@GetMapping("/contacto")
	public String contacto(Model model) {
        model.addAttribute("command", new ContactoCommand());
		return "contacto";
	}
}
