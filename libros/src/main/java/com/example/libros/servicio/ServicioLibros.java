package com.example.libros.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.libros.entidad.Categoria;
import com.example.libros.entidad.Libro;
/**
 * Clase que representa un servicio para gestionar una lista de libros.
 */
@Service
public class ServicioLibros {
	/**
	 * Lista que almacena objetos de tipo Libro.
	 */
	private List<Libro> libros;
	 /**
     * Constructor por defecto de la clase ServicioLibros.
     * Inicializa la lista de libros como un ArrayList vacío.
     */
	public ServicioLibros() {
		libros = new ArrayList<Libro>();
	}
	/**
     * Método que permite guardar un libro en la lista.
     * 
     * @param libro Objeto de tipo Libro que se desea guardar.
     * @return true si el libro se añade con éxito y false en caso contrario.
     */
	public boolean guardarLibro(Libro libro) {
		return libros.add(libro);
	}
	 /**
     * Método que devuelve todos los libros almacenados en la lista.
     * 
     * @return Lista de objetos de tipo Libro.
     */
	public  List<Libro> obtenerLibros(){
		return libros;
	}
	/**
     * Método que devuelve una lista de libros filtrados por categoría.
     * 
     * @param categoria String que representa la categoría de libros que se desea obtener.
     * @return Lista de objetos de tipo Libro filtrados por la categoría dada.
     */
	public List<Libro> obtenerLibros(String categoria) {
		return libros.stream().filter(d -> d.getCategoria().equals(Categoria.valueOf(categoria))).collect(Collectors.toList());
	}
	

}
