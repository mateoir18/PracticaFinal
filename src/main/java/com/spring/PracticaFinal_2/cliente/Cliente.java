package com.spring.PracticaFinal_2.cliente;

import java.util.List;
import java.util.Set;

import com.spring.PracticaFinal_2.compra.Compra;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "El nombre del cliente no puede estar en blanco")
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
	private String nombre;

	@NotBlank(message = "Los apellidos del cliente no pueden estar en blanco")
	@Size(min = 2, max = 100, message = "Los apellidos deben tener entre 2 y 100 caracteres")
	private String apellidos;

	

	@NotBlank(message = "El email del cliente no puede estar en blanco")
	@Email(message = "El formato del email no es válido")
	private String email;

	@OneToMany(mappedBy = "cliente", targetEntity = Compra.class, cascade = CascadeType.ALL)
	private List<Compra> compra;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}

	

	

	
}
