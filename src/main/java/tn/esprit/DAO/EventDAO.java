package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.Event;

@Repository
public interface EventDAO extends JpaRepository<Event,Integer> {
}
