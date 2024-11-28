package com.example.libros.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.libros.entidad.Categoria;
import com.example.libros.entidad.Libro;
import com.example.libros.servicio.ServicioLibros;

@Controller
public class ControladorLibro {
	
	@Autowired
	ServicioLibros serviciolibros;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("mensaje", " DWES :: RA3");
		return "index";
	}
	

	

}
