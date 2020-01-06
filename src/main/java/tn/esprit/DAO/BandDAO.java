package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.Band;
import tn.esprit.entity.Status;

import java.util.List;

@Repository
public interface BandDAO extends JpaRepository<Band,String> {
    List<Band> getAllByStatus(Status status);
    @Query("select b from Band b where (select count(l) from Link l where l.band = b and dateFin = null) = 0")
    List<Band> getUnlinkedBands();
}
