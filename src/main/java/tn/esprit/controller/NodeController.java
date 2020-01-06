package tn.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.CommandeDAO;
import tn.esprit.DAO.NodeDAO;
import tn.esprit.DAO.SalleDAO;
import tn.esprit.entity.Commande;
import tn.esprit.entity.Node;
import tn.esprit.entity.Salle;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/node")
@CrossOrigin(origins = "*", maxAge=0)
public class NodeController {

    @Autowired
    NodeDAO nodeDAO;
    @Autowired
    SalleDAO salleDAO;
    @Autowired
    CommandeDAO commandeDAO;
    @GetMapping("/all")
    public List<Node> getAllNodes() {
        return nodeDAO.findAll();
    }

    @GetMapping("/byid/{id}")
    public Node getNodeById(@PathVariable String id) {
        Optional<Node> n = nodeDAO.findById(id);
        if(!n.isPresent()) return null;
        return n.get();
    }

    @GetMapping("/bysalle/{id}")
    public List<Node> getNodesBySalle(@PathVariable Integer id) {
        Optional<Salle> s = salleDAO.findById(id);
        if(!s.isPresent()) return null;
        return s.get().getNodes();
    }

    @GetMapping("/commandes/bynode/{id}")
    public List<Commande> getCommandesByNode(@PathVariable String id) {
        Optional<Node> n = nodeDAO.findById(id);
        if(!n.isPresent()) return null;
        return n.get().getCommandes();
    }

    @PostMapping("/add")
    public Node addNode(@RequestBody Node node) {
        return nodeDAO.saveAndFlush(node);
    }

    @PostMapping("/commandes/add/bynode/{id}")
    public Commande addCommande(@PathVariable String id,@RequestBody Commande commande) {
        Optional<Node> n = nodeDAO.findById(id);
        if(!n.isPresent()) return null;
        commande.setNode(n.get());
        return commandeDAO.saveAndFlush(commande);
    }

    @PutMapping("/update/{id}")
    public Node updateNode(@PathVariable String id,@RequestBody Node node) {
        Optional<Node> n = nodeDAO.findById(id);
        if(!n.isPresent()) return null;
        Node nl = n.get();
        nl.setIp(node.getIp());
        nl.setDescription(node.getDescription());
        nl.setSalle(node.getSalle());
        return nodeDAO.saveAndFlush(nl);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNode(@PathVariable String id) {
        Optional<Node> n = nodeDAO.findById(id);
        if(!n.isPresent()) return "Node not found";
        nodeDAO.delete(n.get());
        nodeDAO.flush();
        return "Done";
    }

    @DeleteMapping("/commandes/delete/{id}")
    public String deleteCommande(@PathVariable Integer id) {
        Optional<Commande> c = commandeDAO.findById(id);
        if(!c.isPresent()) return "Commande not found";
        commandeDAO.delete(c.get());
        commandeDAO.flush();
        return "Done";
    }
}
