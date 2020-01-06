package tn.esprit.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.*;
import tn.esprit.config.ResourceNotFoundException;
import tn.esprit.entity.*;

import java.util.List;

@RestController
@RequestMapping("mobile")
@CrossOrigin(origins = "*", maxAge=0)
public class MobileController {

    @Autowired
    VoteDAO voteDAO;
    @Autowired
    EventDAO eventDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    LinkDAO linkDAO;
    @Autowired
    ParticipationDAO participationDAO;


    @GetMapping("/myvotes")
    public List<Vote> getVotesByUser(@AuthenticationPrincipal UserDetails userDetails) {
        User u = userDAO.findByEmail(userDetails.getUsername()).get();
        return u.getVotes();
    }

    @GetMapping("/mylinks")
    public List<Link> getLinksByUser(@AuthenticationPrincipal UserDetails userDetails) {
        User u = userDAO.findByEmail(userDetails.getUsername()).get();
        return linkDAO.getAllByDateFinIsNullAndUser(u);
    }

    @GetMapping("/myparticipations")
    public List<Participation> getParticipationsByUser(@AuthenticationPrincipal UserDetails userDetails) {
        User u = userDAO.findByEmail(userDetails.getUsername()).get();
        return u.getParticipations();
    }

    @GetMapping("/voteresponse/byvote/{id}")
    public List<VoteResponse> getVoteResponsesByUser(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Integer id) throws Exception {
        User u = (User) userDetails;
        Vote vote = voteDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Vote with id:%s not found",id.toString())));
        if(vote.getUser().getId() != u.getId()) throw new Exception("Forbidden");
        return vote.getVoteResponses();
    }

    @GetMapping("/allevents")
    public List<Event> getAllEvents() {
        return eventDAO.findAll();
    }

    @PostMapping("/addvote")
    public Vote addVote(@AuthenticationPrincipal UserDetails userDetails,@RequestBody Vote vote) {
        User u = (User) userDetails;
        vote.setUser(u);
        vote.setEvent(null);
        return voteDAO.saveAndFlush(vote);
    }

    private boolean checkVote(Vote v,UserDetails userDetails) {
        User u = (User) userDetails;
        System.out.println(u.getId());
        if(v.getUser().getId() != u.getId() ) return false;
        return true;
    }

    @PutMapping("/updatevote/{id}")
    public Vote updateVote(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Integer id,@RequestBody Vote vote) throws Exception {
        Vote v = voteDAO.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Vote not found"));
        if(!checkVote(v,userDetails)) throw new Exception("Forbidden");
        v.setDescription(vote.getDescription());
        v.setDate(vote.getDate());
        v.setMsg(vote.getMsg());
        v.setOptions(vote.getOptions());
        return voteDAO.saveAndFlush(v);
    }

    @PutMapping("/updateparticipation")
    public Participation updateParticipation(@AuthenticationPrincipal UserDetails userDetails,@RequestBody Participation participation)  throws Exception{
        User u = (User) userDetails;
        if(participation.getUser().getId() != u.getId() || participation.getEvent() == null) throw new Exception("Forbidden");
        Participation p = participationDAO.findById(new ParticipationPk(participation.getUser().getId(),participation.getEvent().getId()))
                .orElseThrow(()->new ResourceNotFoundException("Participation not found"));
        p.setCommentaire(participation.getCommentaire());
        p.setRating(participation.getRating());
        return participationDAO.saveAndFlush(p);
    }

    @DeleteMapping("/deletevote/{id}")
    public String deleteVote(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Integer id) throws Exception{
        Vote v = voteDAO.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Vote not found"));
        if(!checkVote(v,userDetails)) throw new Exception("Forbidden");
        voteDAO.delete(v);
        return "Done";
    }
}
