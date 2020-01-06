package tn.esprit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.BandDAO;
import tn.esprit.DAO.VoteDAO;
import tn.esprit.DAO.VoteResponseDAO;
import tn.esprit.config.ResourceNotFoundException;
import tn.esprit.entity.Band;
import tn.esprit.entity.Vote;
import tn.esprit.entity.VoteResponse;
import tn.esprit.entity.VoteResponsePk;

import java.util.List;

@RestController
@RequestMapping("api/voteresponse")
@CrossOrigin(origins = "*", maxAge=0)
public class VoteResponseController {

    @Autowired
    VoteResponseDAO voteResponseDAO;
    @Autowired
    VoteDAO voteDAO;
    @Autowired
    BandDAO bandDAO;

    @GetMapping("/all")
    public List<VoteResponse> getAllVoteResponses(){
        return voteResponseDAO.findAll();
    }

    @GetMapping("/byvote/{id}")
    public List<VoteResponse> getVoteResponsesByUser(@PathVariable Integer id) throws ResourceNotFoundException {
        Vote vote = voteDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Vote with id:%s not found",id.toString())));
        return vote.getVoteResponses();
    }

    @GetMapping("/byband/{id}")
    public List<VoteResponse> getVoteResponsesByBand(@PathVariable String id) throws ResourceNotFoundException {
        Band band = bandDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Band with id:%s not found",id)));
        return band.getVoteResponses();
    }

    @PostMapping("/add")
    public VoteResponse addVoteResponse(@RequestBody VoteResponse voteResponse) throws ResourceNotFoundException {
        Vote vote = voteDAO.findById(voteResponse.getVote().getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Vote with id:%d not found",voteResponse.getVote().getId())));
        Band band = bandDAO.findById(voteResponse.getBand().getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Band with id:%s not found",voteResponse.getBand().getId())));
        VoteResponsePk id = new VoteResponsePk();
        id.setIdBand(band.getId());
        id.setIdVote(vote.getId());
        voteResponse.setId(id);
        voteResponse.setVote(vote);
        voteResponse.setBand(band);
        return voteResponseDAO.saveAndFlush(voteResponse);
    }

    @PutMapping("/update")
    public VoteResponse updateVoteResponse(@RequestBody VoteResponse voteResponse) throws ResourceNotFoundException {
        VoteResponse v = voteResponseDAO.findById(new VoteResponsePk(voteResponse.getVote().getId(),voteResponse.getBand().getId()))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("VoteResponse with id:%s|%d not found",voteResponse.getBand().getId(),voteResponse.getVote().getId())));
        v.setResponse(voteResponse.getResponse());
        return voteResponseDAO.saveAndFlush(v);
    }

    @DeleteMapping("/delete/{idb}/{idv}")
    public String deleteVoteResponse(@PathVariable String idb,@PathVariable Integer idv) throws ResourceNotFoundException {
        VoteResponse v = voteResponseDAO.findById(new VoteResponsePk(idv,idb))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("VoteResponse with id:%s|%d not found",idb,idv)));
        voteResponseDAO.delete(v);
        voteResponseDAO.flush();
        return "Done";
    }
}
