package com.spring.PracticaFinal_2.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.spring.PracticaFinal_2.cliente.ClienteDao;
import com.spring.PracticaFinal_2.libro.LibroDao;


import jakarta.validation.Valid;

@RestController
public class CompraController {

	@Autowired
	CompraDao compraDao;

	@Autowired
	ClienteDao clienteDao;

	@Autowired
	LibroDao libroDao;

	@GetMapping("/compras")
	public ModelAndView compras() {
		ModelAndView respuesta = new ModelAndView();
		respuesta.addObject("compras", compraDao.findAll());
		respuesta.setViewName("compras");

		return respuesta;
	}

	@GetMapping("/compras/add")
	public ModelAndView compraAdd() {
		ModelAndView model = new ModelAndView();
		model.addObject("compra", new Compra());
		model.addObject("libros", libroDao.findAll());
		model.addObject("clientes", clienteDao.findAll());

		model.setViewName("FormCompra");

		return model;
	}

	@PostMapping("/compras/save")
	public ModelAndView saveCompra(@ModelAttribute @Valid Compra compra, BindingResult bindingResult) {

	    ModelAndView model = new ModelAndView();
	    if (bindingResult.hasErrors()) {

	        model.setViewName("formCompra");
	        model.addObject("compra", compra);

	        compra.setCliente(null);
	        compra.setLibro(null);

	        return model;
	    }
	    
	    
	    model.addObject("clientes", clienteDao.findAll());
	    model.addObject("libros", libroDao.findAll());

	    // Luego, puedes guardar la Compra
	    compraDao.save(compra);

	    model.setViewName("redirect:/compras");

	    return model;
	}
	
	@GetMapping("/compras/delete/{id}")
	public ModelAndView deleteCompra(@PathVariable("id") long id) {
	    ModelAndView model = new ModelAndView();

	    
	    if (compraDao.existsById(id)) {
	        compraDao.deleteById(id);
	        model.setViewName("redirect:/compras");
	    } else {
	        // Aquí puedes manejar el caso en el que la compra no exista
	        model.setViewName("error");
	    }

	    return model;
	}
}
	
	