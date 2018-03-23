package services;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Category;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit.xml"})
@Transactional
public class CategoryServiceTest extends AbstractTest {
	
	
	@Autowired
	private CategoryService categoryService;
	
	
	
	@Test
	public void driverListCategoriesAsAdmin(){
/*
 11. An actor who is authenticated as an administrator must be able to:
1. Manage the categories of services, which includes LISTING, creating, updating, delet-ing, and re-organising them in the category hierarchies.		
 */
		
		Object testingData[][]= {
				//==========================================================================//
				//TEST POSITIVO
				//
				//Comprueba que pueda listar correctamente las categorías un admin
				{"admin",null}
		};
		
		for (int i = 0; i < testingData.length;i++){
			templateListCategoriesAsAdmin((String) testingData[i][0],(Class<?>) testingData[i][1]); 
		
		}
	}

	protected void templateListCategoriesAsAdmin(String username, Class<?> expected) {
		Class<?> caught;
		caught = null;
		
		try{
			super.authenticate(username);
			this.categoryService.findAll();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	
	@Test
	public void driverCreateCategoriesAsAdmin(){

 /*
 11. An actor who is authenticated as an administrator must be able to:
1. Manage the categories of services, which includes listing, CREATING, updating, delet-ing, and re-organising them in the category hierarchies.		
 */		
 
		
		Object testingData[][]= {
				//==========================================================================//
				//TEST POSITIVO
				//
				//Comprueba que una admin pueda crear correctamente una categoría
				{"admin",null},
				//==========================================================================//
				//TEST NEGATIVO 
				//
				//Comprueba que un actor no identificado NO pueda crear una categoría
				{null, IllegalArgumentException.class}
		};
		
		for (int i = 0; i < testingData.length;i++){
			templateCreateCategoriesAsAdmin((String) testingData[i][0],(Class<?>) testingData[i][1]); 
		
		}
	}

	protected void templateCreateCategoriesAsAdmin(String username, Class<?> expected) {
		Class<?> caught;
		caught = null;
		
		try{
			super.authenticate(username);
			this.categoryService.create();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	
	
	
	@Test
	public void driverUpdateCategoryAsAdmin(){
/*
 11. An actor who is authenticated as an administrator must be able to:
1. Manage the categories of services, which includes listing, creating, UPDATING, delet-ing, AND REORGANIZING them in the category hierarchies.		
*/		
		
		Object testingData[][]= {
				//==========================================================================//
				//TESTS POSITIVOS
				//
				//Comprueba que una admin pueda updatear correctamente una categoría
				{"admin", super.getEntityId("category2"),false,null},
				//Comprueba que una admin pueda reorganizar la jerarquía de una categoría
				{"admin", super.getEntityId("category4"), true, null},
				//==========================================================================//
				//TESTS NEGATIVOS
				//
				//Comprueba que un actor no identificado NO pueda updatear una categoría
				{null, super.getEntityId("category2"),false,IllegalArgumentException.class},
				//Comprueba que un actor no identificado NO pueda reorganizar la jerarquía deuna categoría
				{null, super.getEntityId("category4"), true, IllegalArgumentException.class}
		};
		
		for (int i = 0; i < testingData.length;i++){
			templateUpdateCategoryAsAdmin((String) testingData[i][0],(int) testingData[i][1],(boolean) testingData[i][2],(Class<?>) testingData[i][3]); 
		
		}
	}

	protected void templateUpdateCategoryAsAdmin(String username, int categoryId, boolean testingReorganazing ,Class<?> expected) {
		Class<?> caught;
		caught = null;
		
		try{
			super.authenticate(username);
			Category category =  this.categoryService.findOne(categoryId);
			if(!testingReorganazing){
				category.setName("Nombre prueba");
			}else{
				Category root = this.categoryService.findRootCategory();
				Collection<Category> newParentCategory = new ArrayList<Category>();
				newParentCategory.add(root);
				category.setParentCategories(newParentCategory);
			}
			this.categoryService.save(category);
			super.unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}

	
		@Test
		public void driverDeleteCategoryAsAdmin(){
/*
 11. An actor who is authenticated as an administrator must be able to:
1. Manage the categories of services, which includes listing, creating, updating, DELETING, and re-organising them in the category hierarchies.		
 */			
			
			Object testingData[][]= {
					//==========================================================================//
					//TEST POSITIVO
					//
					//Comprueba que una admin pueda eliminar correctamente una categoría
					{"admin", super.getEntityId("category2"),null},
					//==========================================================================//
					//TEST NEGATIVO
					//
					//Comprueba que un actor no identificado NO pueda eliminar una categoría
					{null, super.getEntityId("category2"),IllegalArgumentException.class}
			};
			
			for (int i = 0; i < testingData.length;i++){
				templateDeleteCategoryAsAdmin((String) testingData[i][0],(int) testingData[i][1],(Class<?>) testingData[i][2]); 
			
			}
		}

		protected void templateDeleteCategoryAsAdmin(String username, int categoryId,Class<?> expected) {
			Class<?> caught;
			caught = null;
			
			try{
				super.authenticate(username);
				Category categoryToBeDeleted =  this.categoryService.findOne(categoryId);
				
				this.categoryService.delete(categoryToBeDeleted);
				super.unauthenticate();
			}catch(Throwable oops){
				caught = oops.getClass();
			}
			checkExceptions(expected, caught);
			
		}




}
