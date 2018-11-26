package cl.altic.dashboardtransit.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.altic.dashboardtransit.model.AjaxResponseBody;
import cl.altic.dashboardtransit.model.Cuadro;
import cl.altic.dashboardtransit.model.Region;
import cl.altic.dashboardtransit.model.SearchCriteria;
import cl.altic.dashboardtransit.service.DatosComunesService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardRestAPIs {

    Logger logger = LoggerFactory.getLogger(DashboardRestAPIs.class);
	private static final int REGION_INICIAL = 13;

	private List<Region> regiones;
	private List<Cuadro> cuadros;
	private List<Cuadro> cuadrosFromDB = new ArrayList<Cuadro>();

	@Autowired
	private DatosComunesService datosComunesService;

	@PostConstruct
    public void init() throws Exception {
//		cuadro.setRegion(REGION_INICIAL);;
		regiones = new ArrayList<Region>();
		regiones.add(new Region(13, "Metropolitanta"));
		regiones.add(new Region(15, "Arica y Parinacota"));
		regiones.add(new Region(1, "Tarapacá"));
		regiones.add(new Region(2, "Antofagasta"));
//		regiones.add(new Region(3, "Atacama"));
		regiones.add(new Region(4, "Coquimbo"));
		regiones.add(new Region(5, "Valparaíso"));
		regiones.add(new Region(6, "O’Higgins"));
		regiones.add(new Region(7, "Maule"));
		regiones.add(new Region(8, "Bío Bío"));
		regiones.add(new Region(9, "Araucanía"));
		regiones.add(new Region(14, "Los Ríos"));
		regiones.add(new Region(10, "Los Lagos"));
		
		getCuadros(REGION_INICIAL);
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

	@GetMapping(value ="/regiones")
	public List<Region> getRegiones(){
    	logger.info("Obteniendo regiones");
		return regiones;
	}
	
//	@GetMapping(value = "/cuadros")
//	public  List<Cuadro> getCuadros(@RequestParam(name="regionSelect", required=false) String regionSelect){
//		Cuadro cuadro = new Cuadro();
//		List<Cuadro> cuadros = new ArrayList<Cuadro>();
//		cuadro.setRegion(Integer.parseInt(regionSelect));
//
//		cuadros = datosComunesService.getCuadrosByRegion(cuadro);
//		
//		return cuadros;
//		
//	}
	
	@PostMapping(value = "/cuadros")
	public  ResponseEntity<?> getCuadros(@Valid @RequestBody SearchCriteria search, Errors errors) throws IOException{

		Integer idRegion;
		if (null==search.getRegionSelect()) {
			idRegion =REGION_INICIAL;
		}else {
			idRegion = Integer.parseInt(search.getRegionSelect());
		}
    	logger.info("Obteniendo reportes de la region: "+idRegion);
		

        AjaxResponseBody result = new AjaxResponseBody();
		
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

		Cuadro cuadro = new Cuadro();
		
		getCuadros(idRegion);
		cuadro.setRegion(idRegion);
		cuadrosFromDB = datosComunesService.getCuadrosByRegion(cuadro);
		
        if (cuadrosFromDB.isEmpty()) {
            result.setMsg("no se obtuvieron reportes de la base de datos");
        } else {
            result.setMsg("success");
        }
//        result.setResult(cuadrosFromDB);
//        cuadros.stream().forEach(c -> getInfoCuadro(c, cuadrosFromDB.stream().filter(cdb -> c.getId()==cdb.getId()).findAny().get()));
        cuadros.stream().forEach(c -> getInfoCuadro(c, cuadrosFromDB));
        result.setResult(cuadros);

        return ResponseEntity.ok(result);
		
	}
	

//	private Cuadro getInfoCuadro(Cuadro c, Cuadro cuadro) {
//		c.setNombre(cuadro.getNombre());
//		c.setTexto(cuadro.getTexto());
//		c.setTooltip(cuadro.getTooltip());
//		c.setValor(cuadro.getValor());
//		return c;
//	}

	private Object getInfoCuadro(Cuadro c, List<Cuadro> listaCuadros) {
		try {
			Cuadro cuadro = listaCuadros.stream().filter(cdb -> c.getId()==cdb.getId()).findAny().get();
			c.setNombre(cuadro.getNombre());
			c.setTexto(cuadro.getTexto());
			c.setTooltip(cuadro.getTooltip());
			c.setValor(cuadro.getValor());
			
		}catch (NoSuchElementException e) {
			logger.debug("no hay info en la DB para el cuadro "+c.getId());
		}
		return null;
	}

	//	método para evaluar si determinado reporte tiene gráficos
	private boolean existenGraficos(int idRegion, int idReporte) throws IOException {

		Path path = Paths.get("src/main/resources/templates/graphs.html");

		// BufferedReader reader = Files.newBufferedReader(path);
		// String line = reader.readLine();
		// assertEquals(expected_value, line);
		try (Scanner scanner = new Scanner(path, StandardCharsets.UTF_8.name())) {
			while (scanner.hasNextLine()) {
				if(scanner.nextLine().contains("graph_"+idRegion+"_"+idReporte)) {
					return true;
				}
				// process each line in some way
				// log(scanner.nextLine());
			}
		}
		return false;
	}
}
