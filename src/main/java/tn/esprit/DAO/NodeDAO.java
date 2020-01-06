package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.Node;

@Repository
public interface NodeDAO extends JpaRepository<Node,String> {
}
