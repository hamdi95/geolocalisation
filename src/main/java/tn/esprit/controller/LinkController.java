package tn.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.BandDAO;
import tn.esprit.DAO.LinkDAO;
import tn.esprit.DAO.UserDAO;
import tn.esprit.config.BandAlreadyLinkedException;
import tn.esprit.config.ResourceNotFoundException;
import tn.esprit.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/link")
@CrossOrigin(origins = "*", maxAge=0)
public class LinkController {

    @Autowired
    LinkDAO linkDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    BandDAO bandDAO;

    @GetMapping("/all")
    public List<Link> getAllLinks() {
        return linkDAO.findAll();
    }

    @GetMapping("/byuser/{id}")
    public List<Link> getLinksByUser(@PathVariable Integer id) {
        Optional<User> u = userDAO.findById(id);
        if(!u.isPresent()) return null;
        return linkDAO.getAllByDateFinIsNullAndUser(u.get());
    }

    @GetMapping("/active")
    public  List<Link> getActiveLinks() {
        return linkDAO.getAllByDateFinIsNull();
    }

    @GetMapping("/byband/{id}")
    public List<Link> getLinksByBand(@PathVariable String id) {
        Optional<Band> b = bandDAO.findById(id);
        if(!b.isPresent()) return null;
        return b.get().getLinks();
    }

    private boolean checkLink(Link link) {
        if(link.getBand() == null || link.getUser() == null) return false;
        if(link.getBand().getId() == null || link.getUser().getId() == null) return false;
        return true;
    }

    @PostMapping("/add")
    public Link addLink(@RequestBody Link link) throws BandAlreadyLinkedException {
        if(!checkLink(link)) return null;
        Optional<User> u = userDAO.findById(link.getUser().getId());
        if(!u.isPresent()) return null;
        Optional<Band> b = bandDAO.findById(link.getBand().getId());
        if(!b.isPresent()) return null;
        List<Link> links = linkDAO.getAllByDateFinIsNullAndBand(b.get());
        if(!links.isEmpty()) {
            throw new BandAlreadyLinkedException(links.get(0));
        }
        LinkPk pk = new LinkPk(b.get().getId(),u.get().getId(),new Date());
        link.setId(pk);
        link.setBand(b.get());
        link.setUser(u.get());
        return linkDAO.saveAndFlush(link);
    }

    @PutMapping("/update")
    public Link updateLink(@RequestBody Link link) {
        if(!checkLink(link) || link.getId().getDateDebut() == null) return null;
        Optional<Link> l = linkDAO.findById(new LinkPk(link.getBand().getId(),link.getUser().getId(),link.getId().getDateDebut()));
        if(!l.isPresent()) return null;
        Link link1 = l.get();
        link1.setNom(link.getNom());
        link1.setPrenom(link.getPrenom());
        link1.setAge(link.getAge());
        link1.setDateFin(link.getDateFin());
        return linkDAO.saveAndFlush(link1);
    }

    @DeleteMapping("/delete/{idb}/{idu}/{date}")
    public String deleteLink(@PathVariable String idb,@PathVariable Integer idu,@PathVariable String date) throws ResourceNotFoundException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datef = format.parse(date);
        Link link = linkDAO.findById(new LinkPk(idb,idu,datef))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Link not found for this id ::%s|%d|%s ",idb,idu,date.toString())));
        linkDAO.delete(link);
        linkDAO.flush();
        return "Done";
    }
}
