
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

	@Query("select u.services from Manager u where u.id = ?1")
	public Collection<Service> selectByManagerId(int id);
	
	@Query("select s from Service s where s.category.id = ?1")
	Collection<Service> findByCategory(int categoryId);

}