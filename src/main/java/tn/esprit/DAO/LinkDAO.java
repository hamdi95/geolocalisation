package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.Band;
import tn.esprit.entity.Link;
import tn.esprit.entity.LinkPk;
import tn.esprit.entity.User;

import java.util.Date;
import java.util.List;

@Repository
public interface LinkDAO extends JpaRepository<Link, LinkPk> {
    List<Link> getAllByDateFinIsNullAndBand(Band band);
    List<Link> getAllByDateFinIsNullAndUser(User user);
    List<Link> getAllByDateFinIsNull();
}
