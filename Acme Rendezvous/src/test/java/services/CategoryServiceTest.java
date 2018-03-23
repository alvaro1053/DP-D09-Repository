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
	
	@Autowired
	private RendeService rendeService;
	
	@Test
	public void driverListRendezvousByCategory(){
		Object testingData[][]= {
				{null,this.getEntityId("category2"),null},
				{null, 50, null}
		};
		
		for (int i = 0; i < testingData.length;i++){
			templateListRendezvousByCategory((String) testingData[i][0], (int) testingData[i][1] ,(Class<?>) testingData[i][2]); 
		
		}
	}

	protected void templateListRendezvousByCategory(String username, int categoryId,Class<?> expected){
		Class<?> caught;
		caught = null;
		try{
			authenticate(username);
			this.rendeService.findRendezvousByCategory(categoryId);
			unauthenticate();
		} catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}
	
	
	@Test
	public void driverListCategoriesAsAdmin(){
		Object testingData[][]= {
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
		Object testingData[][]= {
				{"admin",null},
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
	public void driverSaveCategoryAsAdmin(){
		Object testingData[][]= {
				{"admin", super.getEntityId("category2"),null},
				{null, super.getEntityId("category2"),IllegalArgumentException.class}
		};
		
		for (int i = 0; i < testingData.length;i++){
			templateSaveCategoryAsAdmin((String) testingData[i][0], (int) testingData[i][1],(Class<?>) testingData[i][2]); 
		
		}
	}

	protected void templateSaveCategoryAsAdmin(String username, int categoryId ,Class<?> expected) {
		Class<?> caught;
		caught = null;
		
		try{
			super.authenticate(username);
			Category category =  this.categoryService.findOne(categoryId);
			this.categoryService.save(category);
			super.unauthenticate();
		}catch(Throwable oops){
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}

	
	//Reorganizar categorias tambien
	@Test
	public void driverUpdateCategoryAsAdmin(){
		Object testingData[][]= {
				{"admin", super.getEntityId("category2"),false,null},
				{null, super.getEntityId("category2"),false,IllegalArgumentException.class},
				{"admin", super.getEntityId("category4"), true, null},
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

	
	//Reorganizar categorias tambien
		@Test
		public void driverDeleteCategoryAsAdmin(){
			Object testingData[][]= {
					{"admin", super.getEntityId("category2"),null},
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
