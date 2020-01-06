package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.VoteResponse;
import tn.esprit.entity.VoteResponsePk;

@Repository
public interface VoteResponseDAO extends JpaRepository<VoteResponse, VoteResponsePk> {
}
