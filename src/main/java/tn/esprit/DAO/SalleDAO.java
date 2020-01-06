package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.Salle;

@Repository
public interface SalleDAO extends JpaRepository<Salle,Integer> {
}
