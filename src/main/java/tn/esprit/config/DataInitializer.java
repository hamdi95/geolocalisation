package tn.esprit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tn.esprit.DAO.UserDAO;
import tn.esprit.entity.User;

import java.util.Arrays;

//@Component
public class DataInitializer implements CommandLineRunner {


    @Autowired
    UserDAO users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setEmail("test@esprit.tn");
        user.setPassword(this.passwordEncoder.encode("test"));
        user.setRoles(Arrays.asList( "ROLE_USER"));
        this.users.save(user);

    }
}
