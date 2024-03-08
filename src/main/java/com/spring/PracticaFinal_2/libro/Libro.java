package com.spring.PracticaFinal_2.libro;

import java.util.Set;

import com.spring.PracticaFinal_2.compra.Compra;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	

	@NotBlank(message = "El título del libro no puede estar en blanco")
	@Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
	private String titulo;
	
	
	
	
	@NotBlank(message = "El título del libro no puede estar en blanco")
	@Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
	private String autor;
	
	
	

	@DecimalMin(value = "0.0", message = "El precio debe ser mayor o igual a cero")
	private float precio;
	
	
	

	@OneToMany(mappedBy = "libro", targetEntity = Compra.class, cascade = CascadeType.ALL)
	private Set<Compra> compra;




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getTitulo() {
		return titulo;
	}




	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}




	public String getAutor() {
		return autor;
	}




	public void setAutor(String autor) {
		this.autor = autor;
	}




	public float getPrecio() {
		return precio;
	}




	public void setPrecio(float precio) {
		this.precio = precio;
	}




	public Set<Compra> getCompra() {
		return compra;
	}




	public void setCompra(Set<Compra> compra) {
		this.compra = compra;
	}
	
	
	
	

	

}
