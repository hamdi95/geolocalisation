package tn.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.EventDAO;
import tn.esprit.DAO.SalleDAO;
import tn.esprit.config.ResourceNotFoundException;
import tn.esprit.entity.Event;
import tn.esprit.entity.Salle;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/event")
@CrossOrigin(origins = "*", maxAge=0)
public class EventController {

    @Autowired
    EventDAO eventDAO;
    @Autowired
    SalleDAO salleDAO;

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventDAO.findAll();
    }

    @GetMapping("/byid/{id}")
    public Event getEventById(@PathVariable Integer id) {
        Optional<Event> e = eventDAO.findById(id);
        if(!e.isPresent()) return null ;
        return e.get();
    }

    @GetMapping("/bysalle/{id}")
    public Set<Event> getEventsBySalle(@PathVariable Integer id) {
        Optional<Salle> s = salleDAO.findById(id);
        if(!s.isPresent()) return null;
        return s.get().getEvents();
    }

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event) {
        byte[] imageByte= event.getWrapper().getBytes();
        event.setImage(imageByte);
        return eventDAO.saveAndFlush(event);
    }

    @PutMapping("/update/{id}")
    public Event updateEvent(@PathVariable Integer id,@RequestBody Event event) {
        Optional<Event> e = eventDAO.findById(id);
        if(!e.isPresent()) return null;
        Event el = e.get();
        el.setDateDebut(event.getDateDebut());
        el.setDateFin(event.getDateFin());
        el.setDescription(event.getDescription());
        el.setImage(event.getImage());
        el.setTitre(event.getTitre());
        return eventDAO.saveAndFlush(el);
    }

    @PutMapping("/affectsalle/{ide}/{ids}")
    public String affectSalle(@PathVariable Integer ide,@PathVariable Integer ids) throws ResourceNotFoundException {
        Event e = eventDAO.findById(ide)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Event with id:%s not found",ide.toString())));
        Salle s = salleDAO.findById(ids)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Salle with id:%s not found",ids.toString())));
        e.getSalles().add(s);
        eventDAO.saveAndFlush(e);
        return "Done";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        Optional<Event> e = eventDAO.findById(id);
        if(!e.isPresent()) return "Event not exist";
        eventDAO.delete(e.get());
        return "Done";
    }

}
