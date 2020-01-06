package tn.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.EventDAO;
import tn.esprit.DAO.ParticipationDAO;
import tn.esprit.DAO.UserDAO;
import tn.esprit.entity.Event;
import tn.esprit.entity.Participation;
import tn.esprit.entity.ParticipationPk;
import tn.esprit.entity.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/participation")
@CrossOrigin(origins = "*", maxAge=0)
public class ParticipationController {

    @Autowired
    EventDAO eventDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    ParticipationDAO participationDAO;

    @GetMapping("/all")
    public List<Participation> getAllParticipations() {
        return participationDAO.findAll();
    }

    @GetMapping("/byuser/{id}")
    public List<Participation> getParticipationsByUser(@PathVariable Integer id) {
        Optional<User> u = userDAO.findById(id);
        if(!u.isPresent()) return null;
        return u.get().getParticipations();
    }

    @GetMapping("/byevent/{id}")
    public List<Participation> getParticipationsByEvent(@PathVariable Integer id) {
        Optional<Event> e = eventDAO.findById(id);
        if(!e.isPresent()) return null;
        return e.get().getParticipations();
    }

    private static boolean checkParticipation(Participation participation) {
        if(participation.getEvent() == null || participation.getUser() == null) return false;
        if(participation.getEvent().getId() == null || participation.getUser().getId() == null) return false;
        return true;
    }

    @PostMapping("/add")
    public Participation addParticipation(@RequestBody Participation participation) {
        if(!checkParticipation(participation)) return null;
        Optional<User> u = userDAO.findById(participation.getUser().getId());
        if(!u.isPresent()) return null;
        Optional<Event> e = eventDAO.findById(participation.getEvent().getId());
        if(!e.isPresent()) return null;
        ParticipationPk pk = new ParticipationPk();
        pk.setIdEvent(e.get().getId());
        pk.setIdUser(u.get().getId());
        participation.setId(pk);
        participation.setUser(u.get());
        participation.setEvent(e.get());
        return participationDAO.saveAndFlush(participation);
    }

    @PutMapping("/update")
    public Participation updateParticipation(@RequestBody Participation participation) {
        if(!checkParticipation(participation)) return null;
        Optional<Participation> p = participationDAO.findById(new ParticipationPk(participation.getUser().getId(),participation.getEvent().getId()));
        if(!p.isPresent()) return  null;
        Participation pl = p.get();
        pl.setCommentaire(participation.getCommentaire());
        pl.setRating(participation.getRating());
        return participationDAO.saveAndFlush(pl);
    }

    @DeleteMapping("/delete/{idu}/{ide}")
    public String deleteParticipation(@PathVariable Integer idu,@PathVariable Integer ide) {
        Optional<Participation> p = participationDAO.findById(new ParticipationPk(idu,ide));
        if(!p.isPresent()) return "Participation not exist";
        participationDAO.delete(p.get());
        participationDAO.flush();
        return "Done";
    }
}
