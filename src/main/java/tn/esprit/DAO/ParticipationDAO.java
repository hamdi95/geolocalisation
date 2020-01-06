package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.Participation;
import tn.esprit.entity.ParticipationPk;

@Repository
public interface ParticipationDAO extends JpaRepository<Participation, ParticipationPk> {
}
