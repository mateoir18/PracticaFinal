package com.spring.PracticaFinal_2.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



import jakarta.validation.Valid;
@RestController
public class ClienteController {

	@Autowired
	ClienteDao clienteDao;

	@GetMapping("/clientes")
	public ModelAndView clientes() {

		ModelAndView model = new ModelAndView("clientes");
		List<Cliente> clientes = (List<Cliente>) clienteDao.findAll();
		model.addObject("clientes", clientes);

		return model;
	}

	@GetMapping("/clientes/{id}")
	public ModelAndView usuarios(@PathVariable long id) {
		ModelAndView model = new ModelAndView("Formcliente");
		return model;

	}

	@GetMapping("/clientes/add")
	public ModelAndView añadirUsuario() {
		ModelAndView model = new ModelAndView("FormCliente");
		model.addObject("cliente", new Cliente());
		return model;
	}

	@GetMapping("/clientes/edit/{id}")
	public ModelAndView editUsuario(@PathVariable long id) {

		ModelAndView model = new ModelAndView("FormCliente");

		Optional<Cliente> BuscoUsuario = clienteDao.findById(id);

		if (BuscoUsuario.isPresent()) {
			model.addObject("cliente", BuscoUsuario.get());
			model.setViewName("FormCliente");
		} else {
			model.setViewName("redirect:/clientes");
		}
		return model;
	}

	@GetMapping("/clientes/del/{id}")
	public ModelAndView BorrarCliente(@PathVariable long id) {

		clienteDao.deleteById(id);
		ModelAndView model = new ModelAndView("redirect:/clientes");

		return model;
	}

	@PostMapping("/clientes/save")
	public ModelAndView formUsuario(@ModelAttribute @Valid Cliente cliente, BindingResult bindingResult) {

		ModelAndView model = new ModelAndView();
		if (bindingResult.hasErrors()) {

			model.setViewName("formCliente");
			model.addObject("cliente", cliente);

			return model;
		}
		clienteDao.save(cliente);

		model.setViewName("redirect:/clientes");

		return model;
	}
}
