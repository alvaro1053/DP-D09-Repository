
package controllers;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.RendeService;
import domain.Category;
import domain.Rende;


@Controller
@RequestMapping("/rende")
public class RendeController extends AbstractController {

	// Services

	@Autowired
	private RendeService	rendeService;
	
	@Autowired
	private CategoryService	categoryService;


	// Constructors

	public RendeController() {
		super();
	}

	// Listing
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Rende> rendes;
		Collection<Category>categories;
		final String uri = "";
		rendes = this.rendeService.selectNotAdultRendes();
		categories = this.categoryService.findAll();
		result = new ModelAndView("rende/list");
		result.addObject("rendes", rendes);
		result.addObject("categories", categories);
		result.addObject("uri", uri);
		return result;
	}	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, params = {
			"filterCategory"
		})
		public ModelAndView filterCategory(@RequestParam final Category filterCategory) {
			final ModelAndView result;
			Collection<Rende> res = new ArrayList<Rende>();
			Collection<Category> categories;
			final String uri = "";
			categories = categoryService.findAll();	
			
			if(filterCategory.equals(categoryService.findRootCategory())){
				res=rendeService.findRendezvousWithCategoriesUnderAge();
			}else{
				res=rendeService.findRendezvousByCategoryUnderAge(filterCategory.getId());
			}
			
			result = new ModelAndView("rende/list");
			result.addObject("rendes", res);
			result.addObject("categories", categories);
			result.addObject("uri", uri);

			return result;
		}
	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int rendeId) {
		ModelAndView result;
		Rende rende = new Rende();
		final String uri = "";
		Boolean permisos = true;
		rende = this.rendeService.findOne(rendeId);
		if (rende.getAdultOnly() == true)
			permisos = false;

		result = new ModelAndView("rende/display");
		result.addObject("rende", rende);
		result.addObject("permisos", permisos);
		result.addObject("uri", uri);
		return result;
	}

}
