
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rende;

@Repository
public interface RendeRepository extends JpaRepository<Rende, Integer> {

	@Query("select u.rendes from User u where u.id = ?1")
	public Collection<Rende> selectByUserId(int id);

	@Query("select r from Rende r where r.adultOnly = false")
	public Collection<Rende> selectNotAdultRendes();

	@Query("select l from Rende r join r.linked l where r.id MEMBER OF l.linked AND r.id =?1")
	public Collection<Rende> selectLinkedById(int id);

	@Query("select distinct a from Category c join c.services r join r.request re join re.rende a where c.id=?1")	
    public Collection<Rende> findRendezvousByCategory(Integer categoryId);	
	
	@Query("select distinct a from Category c join c.services r join r.request re join re.rende a where c.id=?1 AND a.adultOnly = false")	
    public Collection<Rende> findRendezvousByCategoryUnderAge(Integer categoryId);	
	
	@Query("select distinct a from Category c join c.services r join r.request re join re.rende a")	
    public Collection<Rende> findRendezvousWithCategories();	
	
	@Query("select distinct a from Category c join c.services r join r.request re join re.rende a where a.adultOnly = false")	
    public Collection<Rende> findRendezvousWithCategoriesUnderAge();
}
