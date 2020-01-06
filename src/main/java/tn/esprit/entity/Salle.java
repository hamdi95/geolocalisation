package tn.esprit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Salle
 *
 */
@Entity

public class Salle implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Salle() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idSalle")
	private int id ;
	private String nom;
	private String description;
	@OneToMany(mappedBy="salle")
	@JsonIgnore
	private List<ImageSalle> images ;
	@ManyToMany()
	@JoinTable(name = "event_salle",
	joinColumns={@JoinColumn(name="idSalle")},
	inverseJoinColumns={@JoinColumn(name ="id")})
	@JsonIgnore
	private Set<Event> events = new HashSet<>();
	@OneToMany(mappedBy="salle")
	@JsonIgnore
	private List<Node> nodes;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ImageSalle> getImages() {
		return images;
	}
	public void setImages(List<ImageSalle> images) {
		this.images = images;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	@Override
	public String toString() {
		return "Salle [id=" + id + ", nom=" + nom + ", description=" + description + ", images=" + images + ", events="
				+ events + ", nodes=" + nodes + "]";
	}
	
	
   
}
