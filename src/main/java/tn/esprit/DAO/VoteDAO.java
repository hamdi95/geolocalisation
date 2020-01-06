package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.Vote;

@Repository
public interface VoteDAO extends JpaRepository<Vote,Integer> {
}
