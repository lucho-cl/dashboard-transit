package cl.altic.dashboardtransit.controller;

import java.util.ArrayList;
import java.util.List;
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
		regiones.add(new Region(6, "Del Libertador General Bernardo O’Higgins"));
		regiones.add(new Region(7, "Maule"));
		regiones.add(new Region(8, "Bío Bío"));
		regiones.add(new Region(9, "Araucanía"));
		regiones.add(new Region(14, "Los Ríos"));
		regiones.add(new Region(10, "Los Lagos"));
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
	public  ResponseEntity<?> getCuadros(@Valid @RequestBody SearchCriteria search, Errors errors){

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
		List<Cuadro> cuadros = new ArrayList<Cuadro>();
		cuadro.setRegion(idRegion);
		cuadros = datosComunesService.getCuadrosByRegion(cuadro);
		
        if (cuadros.isEmpty()) {
            result.setMsg("no regions found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(cuadros);

        return ResponseEntity.ok(result);
		
	}
}
