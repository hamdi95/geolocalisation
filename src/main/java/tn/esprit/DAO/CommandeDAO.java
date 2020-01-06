package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.Commande;

@Repository
public interface CommandeDAO extends JpaRepository<Commande,Integer> {
}
