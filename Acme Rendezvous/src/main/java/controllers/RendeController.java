
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
		int filterCategoryId = 0;
		final String uri = "";
		rendes = this.rendeService.selectNotAdultRendes();
		categories = this.categoryService.findAll();
		result = new ModelAndView("rende/list");
		result.addObject("rendes", rendes);
		result.addObject("categories", categories);
		result.addObject("filterCategoryId", filterCategoryId);
		result.addObject("uri", uri);
		return result;
	}	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, params = {
			"filterCategoryId"
		})
		public ModelAndView filterCategory(@RequestParam final int filterCategoryId) {
			final ModelAndView result;
			Collection<Rende> res = new ArrayList<Rende>();
			Collection<Category> categories;
			Category filterCategory;
			final String uri = "";
			filterCategory	= this.categoryService.findOne(filterCategoryId);
			categories = categoryService.findAll();	
			
			if(filterCategoryId==0){
				res=rendeService.selectNotAdultRendes();
			}else if(filterCategory.equals(categoryService.findRootCategory())){
				res=rendeService.findRendezvousWithCategoriesUnderAge();
			}else{
				res=rendeService.findRendezvousByCategoryUnderAge(filterCategoryId);
			}
			
			result = new ModelAndView("rende/list");
			result.addObject("rendes", res);
			result.addObject("categories", categories);
			result.addObject("filterCategory", filterCategory);
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
