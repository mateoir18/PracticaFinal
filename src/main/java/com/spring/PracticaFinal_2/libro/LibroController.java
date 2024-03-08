package com.spring.PracticaFinal_2.libro;

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
public class LibroController {

	@Autowired
	LibroDao libroDao;

	@GetMapping("/")
	public ModelAndView inicio() {

		ModelAndView model = new ModelAndView("index");

		return model;
	}

	@GetMapping("/libros")
	public ModelAndView libros() {
		ModelAndView model = new ModelAndView("libros");
		List<Libro> libros = (List<Libro>) libroDao.findAll();

		model.addObject("libros", libros);

		return model;
	}

	@GetMapping("/libros/{id}")
	public ModelAndView libro(@PathVariable long id) {
		ModelAndView model = new ModelAndView("libro");
		Libro libro = libroDao.findById(id).orElse(null);
		if (libro != null) {
			model.addObject("libro", libro);
		} else {
			model.setViewName("redirect:/libros");
		}
		return model;
	}

	@GetMapping("/libros/add")
	public ModelAndView addLibro() {

		ModelAndView model = new ModelAndView();
		model.setViewName("FormLibro");
		model.addObject("libro", new Libro());

		return model;
	}

	@GetMapping("/libros/edit/{id}")
	public ModelAndView editPlan(@PathVariable long id) {

		ModelAndView model = new ModelAndView();

		Optional<Libro> BuscoLibro = libroDao.findById(id);
		if (BuscoLibro.isPresent()) {

			model.addObject("libro", BuscoLibro.get());

			model.setViewName("FormLibro");
		} else
			model.setViewName("redirect:/libros");

		return model;
	}

	@GetMapping("/libros/del/{id}")
	public ModelAndView BorrarLibro(@PathVariable long id) {

		libroDao.deleteById(id);
		ModelAndView model = new ModelAndView("redirect:/libros");

		return model;
	}

	@PostMapping("/libros/save")
	public ModelAndView formTutoria(@ModelAttribute @Valid Libro libro, BindingResult bindingResult) {

		ModelAndView model = new ModelAndView();
		if (bindingResult.hasErrors()) {

			model.setViewName("FormLibro");
			model.addObject("libro", libro);

			return model;
		}

		libroDao.save(libro);

		model.setViewName("redirect:/libros");

		return model;
	}
}
