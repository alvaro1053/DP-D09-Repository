
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;
import domain.Manager;
import domain.Rende;
import domain.Service;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.userAccount.id = ?1")
	Admin findByUserAccountId(int userAccountId);

	@Query("select avg(u.rendes.size) from User u")
	Double averageOfRendesPerUser();
	
	@Query("select sqrt(sum(u.rendes.size*u.rendes.size)/count(u.rendes.size)-(avg(u.rendes.size)*avg(u.rendes.size))) from User u")
	Double desviationOfRendesPerUser();

	@Query("select 1.0*count(u) from User u where u.rendes.size > 0 /(select count(u) from User u where u.rendes.size = 0)")
	Double ratioOfUsersWithRendesCreated();

	@Query("select avg(r.attendants.size) from Rende r")
	Double averageOfRSVPPerRende();
	
	@Query("select sqrt(sum(r.attendants.size*r.attendants.size)/count(r.attendants.size)-(avg(r.attendants.size)*avg(r.attendants.size))) from Rende r")
	Double desviationOfRSVPPerRende();

	@Query("select avg(u.rSVPS.size) from User u")
	Double averageOfRSVPPerUser();
	
	@Query("select sqrt(sum(u.rSVPS.size*u.rSVPS.size)/count(u.rSVPS.size)-(avg(u.rSVPS.size)*avg(u.rSVPS.size))) from User u")
	Double desviationOfRSVPPerUser();
	
	@Query("select r.name from Rende r order by r.attendants.size desc")
	Collection<Rende> top10RendesByAttendants();

	@Query("select avg(r.announcements.size) from Rende r")
	Double averageOfAnnouncementPerRende();
	
	@Query("select sqrt(sum(r.announcements.size*r.announcements.size)/count(r.announcements.size)-(avg(r.announcements.size)*avg(r.announcements.size))) from Rende r")
	Double desviationOfAnnouncementPerRende();

	@Query("select r.name from Rende r where ( r.announcements.size > 0.75 * (select avg(r.announcements.size) from Rende r))")
	Collection<Rende> rendesWithMoreAnnouncementsThanTheAverage();

	@Query("select r.name from Rende r where ( r.linked.size > 1.1 * (select avg(r.linked.size) from Rende r))")
	Collection<Rende> rendesWithMoreLinkedThanTheAverage();

	@Query("select avg(r.questions.size) from Rende r")
	Double averageOfQuestionsPerRende();
	
	@Query("select sqrt(sum(r.questions.size*r.questions.size)/count(r.questions.size)-(avg(r.questions.size)*avg(r.questions.size))) from Rende r")
	Double desviationOfQuestionsPerRende();

	@Query("select avg(q.replyQuestions.size) from Rende r join r.questions q")
	Double averageOfRepliesPerQuestion();
	
	@Query("select sqrt(sum(q.replyQuestions.size*q.replyQuestions.size)/count(q.replyQuestions.size)-(avg(q.replyQuestions.size)*avg(q.replyQuestions.size))) from Rende r join r.questions q")
	Double desviationOfRepliesPerQuestion();
	
	@Query("select avg(c.repliesComments.size) from Comment c")
	Double averageOfRepliesPerComment();
	
	@Query("select sqrt(sum(c.repliesComments.size*c.repliesComments.size)/count(c.repliesComments.size)-(avg(c.repliesComments.size)*avg(c.repliesComments.size))) from Comment c")
	Double desviationOfRepliesPerComment();

	//Acme-Rendezvous 2.0
	
	@Query("select s from Service s where s.request.size = (select max(s2.request.size) from Service s2)")
	Collection<Service> topSellingServices();
	
	@Query("select m from Manager m where ( m.services.size > 1 * (select avg(m2.services.size) from Manager m2))")
	Collection<Manager> ManagersWithMoreServicesThanTheAverage();
	
	@Query("select avg(re.service.category) from Rende r join r.request re") //Creo que no funciona
	Double AverageCategoriesPerRendezvous();
	
	@Query("select avg(r.request.size) from Rende r")
	Double AverageServicesRequestedPerRende();
	
	@Query("select max(r.request.size) from Rende r")
	Double MaxServicesRequestedPerRende();
	
	@Query("select min(r.request.size) from Rende r")
	Double MinServicesRequestedPerRende();
	
	@Query("select sqrt(sum(r.request.size* r.request.size)/count(r.request.size)-(avg(r.request.size)*avg(r.request.size))) from Rende r")
	Double StandardDesviationServicesRequestedPerRende();
	
	@Query("select s from Service s order by s.request.size desc")
	Collection<Rende> top5SellingServices(); //Limitar en servicio a 5
}
