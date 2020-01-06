package tn.esprit.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entity.User;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
