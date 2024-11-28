package com.example.coches.servicios;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.coches.entidad.Coche;
import com.example.coches.entidad.FuenteEnergia;


@Service
public class ServicioCoches {
	private List<Coche> coches;
	
	public ServicioCoches() {
		coches = new ArrayList<Coche>();
	}
	  /**
     * Obtiene una lista de todos los coches disponibles.
     *
     * @return una lista que contiene todos los coches
     */
    public List<Coche> obtenerTodos(){
        return coches;
    }
    
    /**
     * Guarda un coche en la lista.
     *
     * @param coche el objeto coche a guardar
     * @return verdadero si el coche se agregó con éxito, falso en caso contrario
     */
    public Boolean guardar(Coche coche) {
    	coche.setId(obtenerNuevoId());
        return coches.add(coche);
    }
    
    /**
     * Filtra la lista de coches por su fuente de energía.
     *
     * @param fuenteEnergia la fuente de energía por la cual filtrar los coches
     * @return una lista de coches que tienen la fuente de energía especificada
     */
    public List<Coche> filtrarFuenteEnergia(FuenteEnergia fuenteEnergia){
        return coches.stream().filter(c -> c.getFuenteEnergia().equals(fuenteEnergia)).collect(Collectors.toList());
    }
    
    /**
     * Obtiene una lista de coches ordenada por precio en orden ascendente.
     *
     * @return una lista ordenada de coches basada en su precio
     */
    public List<Coche> obtenerCochesOrdenadosPorPrecio() {
        List<Coche> cochesOrdenados = new ArrayList<>(coches);
        cochesOrdenados.sort(Comparator.comparingDouble(Coche::getPrecio));
        return cochesOrdenados;
    }
    
    /**
     * Obtiene el ID más alto de la lista de coches y lo incrementa.
     *
     * @return un nuevo ID incrementado.
     */
    private Long obtenerNuevoId() {
        return coches.stream()
                     .map(Coche::getId) // Extraer los IDs
                     .max(Comparator.naturalOrder()) // Encontrar el mayor
                     .orElse(0L) + 1; // Si no hay coches, empezar desde 1
    }
    
    /**
     * Obtiene todas las fuentes de energía disponibles para los coches.
     *
     * @return un arreglo de todas las posibles fuentes de energía
     */
    public FuenteEnergia[] obtenerFuentesEnergia() {
        return FuenteEnergia.values();
    }
    
    /**
     * Obtiene todas las marcas únicas de los coches disponibles.
     * 
     * @return Un conjunto de marcas únicas.
     */
    public Set<String> obtenerMarcasUnicas() {
    	
        return obtenerTodos().stream()
                     .map(Coche::getMarca) // Extrae solo las marcas
                     .distinct() // Asegura que no se repitan
                     .collect(Collectors.toSet()); // Convierte a un conjunto
    }

   

}
