
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rende;
import domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select r.request from User u join u.rendes r where u.id = ?1")
	public Collection<Request> selectByUserId(int id);
	
	@Query("select s.request from Manager m join m.services s where m.id = ?1")
	public Collection<Request> selectByManagerId(int id);

}
