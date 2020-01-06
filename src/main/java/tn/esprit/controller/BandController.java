package tn.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.BandDAO;
import tn.esprit.config.ResourceNotFoundException;
import tn.esprit.entity.Band;
import tn.esprit.entity.Status;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/band")
@CrossOrigin(origins = "*", maxAge=0)
public class BandController {

    @Autowired
    BandDAO bandDAO;

    @GetMapping("/all")
    public List<Band> getAllBands() {
        return bandDAO.findAll();
    }

    @GetMapping("/all/connected")
    public List<Band> getConnectedBands() {
        return bandDAO.getAllByStatus(Status.Connected);
    }

    @GetMapping("/all/disconnected")
    public List<Band> getDisconnectedBands() {
        return bandDAO.getAllByStatus(Status.Disconnected);
    }

    @GetMapping("/all/unlinked")
    public List<Band> getUnlinkedBands() {
        return bandDAO.getUnlinkedBands();
    }

    @GetMapping("/byid/{id}")
    public Band getBandById(@PathVariable String id) throws ResourceNotFoundException {
        return bandDAO.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Band not found"));
    }

    @PostMapping("/add")
    public Band addBand(@RequestBody Band band) {
        return bandDAO.saveAndFlush(band);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBand(@PathVariable String id) {
        Optional<Band> b = bandDAO.findById(id);
        if(!b.isPresent()) return "Band not exist";
        bandDAO.delete(b.get());
        bandDAO.flush();
        return "done";
    }
}
