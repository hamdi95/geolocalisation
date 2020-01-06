package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.ImageSalle;

@Repository
public interface ImageSalleDAO extends JpaRepository<ImageSalle,Integer> {
}
