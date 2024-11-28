package com.example.coches.controlador;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.coches.entidad.Coche;
import com.example.coches.entidad.FuenteEnergia;
import com.example.coches.entidad.Idioma;
import com.example.coches.servicios.ServicioCoches;

import jakarta.validation.Valid;

@Controller
public class ControladorCoche {

	@Autowired
	private ServicioCoches servicioCoches;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("mensaje", " DWES :: RA3");
		model.addAttribute("idiomas", Idioma.values());
		return "index";
	}

	@GetMapping("/idioma")
	public String idioma(@RequestParam String idioma) {
		if (idioma.equals(Idioma.EN.toString())) {
			return "redirect:/?lang=en";
		} else {
			return "redirect:/?lang=es";
		}
	}

	/**
	 * Método para listar coches. Este método obtiene una lista de todos los coches
	 * y las fuentes de energía disponibles, además de los idiomas, y los agrega al
	 * modelo para ser accesibles en la vista.
	 *
	 * @param model el modelo en el que se agregan los atributos para ser accesibles
	 *              en la vista.
	 * @return el nombre de la vista que se debe renderizar, en este caso "coches".
	 */
	@GetMapping("/coches")
	public String listarCoches(Model model) {
		// Obtener la lista de todos los coches
		List<Coche> coches = servicioCoches.obtenerTodos();
		// Agregar la lista de coches al modelo
		model.addAttribute("coches", coches);

		// Agrega la fuentes de Energia
		model.addAttribute("fuenteEnergia", servicioCoches.obtenerFuentesEnergia());

		// Añadir los idiomas [ES,EN]
		model.addAttribute("idiomas", Idioma.values());

		// Retornar el nombre de la vista "coches" que se debe renderizar
		return "coches"; // <---- Inyecto los datos del modelo en la VISTA |</>| coches.html
	}

	/**
	 * 
	 * @param fuenteEnergia
	 * @param model
	 * @return
	 */
	@GetMapping("/filtrar")
	public String filtrarPorFuenteEnergia(@RequestParam String fuenteEnergia, Model model) {
		List<Coche> coches = servicioCoches.filtrarFuenteEnergia(FuenteEnergia.valueOf(fuenteEnergia));
		model.addAttribute("coches", coches);
		model.addAttribute("fuenteEnergia", servicioCoches.obtenerFuentesEnergia());
		model.addAttribute("idiomas", Idioma.values());
		return "coches";
	}
	/**
	 * 
	 * @param coche
	 * @param model
	 * @return
	 */
	@GetMapping("/formulario")
	public String formulario(@ModelAttribute Coche coche, Model model) {
	    model.addAttribute("fuentesenergia", FuenteEnergia.values());
	    model.addAttribute("marcas", servicioCoches.obtenerMarcasUnicas());
	    return "form_coche"; // Nombre de la vista Thymeleaf
	}
	/**
	 * 
	 * @param coche
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/nuevo_coche")
	public String agregarCoche(@Valid @ModelAttribute Coche coche, BindingResult result, Model model) {
	    System.out.println("-->"+coche);
		if (result.hasErrors()) {
			
			model.addAttribute("marcas", servicioCoches.obtenerMarcasUnicas());
	        model.addAttribute("fuentesenergia", FuenteEnergia.values());
	        return "form_coche"; // Retorna a la vista del formulario si hay errores
	    }
	    servicioCoches.guardar(coche); // Lógica para guardar el coche
	    System.out.println("<--"+coche);
	    return "redirect:/coches"; // Redirección a la lista de coches
	}
	
 

}
