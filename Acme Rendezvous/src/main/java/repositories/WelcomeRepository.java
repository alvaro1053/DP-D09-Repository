package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.WelcomePage;



@Repository
public interface WelcomeRepository extends JpaRepository<WelcomePage, Integer> {

}

