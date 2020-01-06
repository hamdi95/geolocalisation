package tn.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.EventDAO;
import tn.esprit.DAO.ImageSalleDAO;
import tn.esprit.DAO.SalleDAO;
import tn.esprit.entity.Event;
import tn.esprit.entity.ImageSalle;
import tn.esprit.entity.Salle;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/salle")
@CrossOrigin(origins = "*", maxAge=0)
public class SalleController {

    @Autowired
    SalleDAO salleDAO;
    @Autowired
    ImageSalleDAO imageSalleDAO;
    @Autowired
    EventDAO eventDAO;

    @GetMapping("/all")
    public List<Salle> getAllSalles() {
        return salleDAO.findAll();
    }

    @GetMapping("/byid/{id}")
    public Salle getSalleById(@PathVariable Integer id) {
        Optional<Salle> s = salleDAO.findById(id);
        if(!s.isPresent()) return null ;
        return s.get();
    }

    @GetMapping("/images/bysalle/{id}")
    public List<ImageSalle> getImagesBySalle(@PathVariable Integer id) {
        Optional<Salle> s = salleDAO.findById(id);
        if(!s.isPresent()) return null;
        return s.get().getImages();
    }

    @GetMapping("/byevent/{id}")
    public Set<Salle> getSallesByEvent(@PathVariable Integer id) {
        Optional<Event> e = eventDAO.findById(id);
        if(!e.isPresent()) return null;
        return e.get().getSalles();
    }

    @PostMapping("/add")
    public Salle addSalle(@RequestBody Salle salle) {
        return salleDAO.saveAndFlush(salle);
    }

    @PostMapping("/images/add/bysalle/{id}")
    public ImageSalle addImageSalle(@PathVariable Integer id,@RequestBody ImageSalle imageSalle) {
        Optional<Salle> s = salleDAO.findById(id);
        if(!s.isPresent()) return null;
        imageSalle.setSalle(s.get());
        return imageSalleDAO.saveAndFlush(imageSalle);
    }

    @PutMapping("/update/{id}")
    public Salle updateSalle(@PathVariable Integer id,@RequestBody Salle salle) {
        Optional<Salle> s = salleDAO.findById(id);
        if(!s.isPresent()) return null;
        Salle sl = s.get();
        sl.setDescription(salle.getDescription());
        sl.setNom(salle.getNom());
        return salleDAO.saveAndFlush(sl);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSalle(@PathVariable Integer id) {
        Optional<Salle> s = salleDAO.findById(id);
        if(!s.isPresent()) return "Salle not found";
        salleDAO.delete(s.get());
        salleDAO.flush();
        return "Done";
    }

    @DeleteMapping("/images/delete/{id}")
    public String deleteImageSalle(@PathVariable Integer id) {
        Optional<ImageSalle> im = imageSalleDAO.findById(id);
        if(!im.isPresent()) return "Image not found";
        imageSalleDAO.delete(im.get());
        imageSalleDAO.flush();
        return "Done";
    }
}
