package tn.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.EventDAO;
import tn.esprit.DAO.UserDAO;
import tn.esprit.DAO.VoteDAO;
import tn.esprit.entity.Event;
import tn.esprit.entity.User;
import tn.esprit.entity.Vote;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/vote")
@CrossOrigin(origins = "*", maxAge=0)
public class VoteController {

    @Autowired
    VoteDAO voteDAO;
    @Autowired
    EventDAO eventDAO;
    @Autowired
    UserDAO userDAO;

    @GetMapping("/all")
    public List<Vote> getAllVotes() {
        return voteDAO.findAll();
    }

    @GetMapping("/byid/{id}")
    public Vote getVoteById(@PathVariable Integer id) {
        Optional<Vote> v = voteDAO.findById(id);
        if(!v.isPresent()) return null;
        return v.get();
    }

    @GetMapping("/byevent/{id}")
    public List<Vote> getVotesByEvent(@PathVariable Integer id) {
        Optional<Event> e = eventDAO.findById(id);
        if(!e.isPresent()) return null;
        return e.get().getVotes();
    }

    @GetMapping("/byuser/{id}")
    public List<Vote> getVotesByUser(@PathVariable Integer id) {
        Optional<User> u = userDAO.findById(id);
        if(!u.isPresent()) return null;
        return u.get().getVotes();
    }

    @PostMapping("/add")
    public Vote addVote(@RequestBody Vote vote) {
        if(vote.getEvent() != null) {
            Optional<Event> e = eventDAO.findById(vote.getEvent().getId());
            if(e.isPresent()) {
                vote.setEvent(e.get());
                vote.setUser(null);
            }
            else vote.setEvent(null);
        }
        if(vote.getUser() != null) {
            Optional<User> u = userDAO.findById(vote.getUser().getId());
            if(u.isPresent()) vote.setUser(u.get());
            else vote.setUser(null);
        }
        return voteDAO.saveAndFlush(vote);
    }

    @PutMapping("/update/{id}")
    public Vote updateVote(@PathVariable Integer id,@RequestBody Vote vote) {
        Optional<Vote> v = voteDAO.findById(id);
        if(!v.isPresent()) return null;
        Vote vl = v.get();
        vl.setDescription(vote.getDescription());
        vl.setDate(vote.getDate());
        vl.setMsg(vote.getMsg());
        vl.setOptions(vote.getOptions());
        if(vl.getUser() != null) vl.setUser(null);
        if(vote.getEvent() != null) {
            Optional<Event> e = eventDAO.findById(vote.getEvent().getId());
            if(e.isPresent()) vl.setEvent(e.get());
            else vl.setEvent(null);
        }else  vl.setEvent(null);
        return voteDAO.saveAndFlush(vl);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVote(@PathVariable Integer id) {
        Optional<Vote> e = voteDAO.findById(id);
        if(!e.isPresent()) return "Vote not exist";
        voteDAO.delete(e.get());
        return "Done";
    }
}
