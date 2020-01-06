package tn.esprit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Event implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Event() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String titre;
	private String description;
	private Date dateDebut;
	private Date dateFin;
	@Lob
	@JsonIgnore
	private byte[] image;
	@Transient
	private String wrapper;
	@ManyToMany
	@JoinTable(name = "event_salle",
	joinColumns={@JoinColumn(name="id")},
	inverseJoinColumns={@JoinColumn(name ="idSalle")})
	@JsonIgnore
	private Set<Salle> salles = new HashSet<>();
	@OneToMany(mappedBy="event")
	@JsonIgnore
	private List<Vote> votes;
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Participation> participations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getWrapper() {
		return wrapper;
	}

	public void setWrapper(String wrapper) {
		this.wrapper = wrapper;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Set<Salle> getSalles() {
		return salles;
	}

	public void setSalles(Set<Salle> salles) {
		this.salles = salles;
	}

	public List<Vote> getVotes() {
		return votes;
	}
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}
	public List<Participation> getParticipations() {
		return participations;
	}
	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", titre=" + titre + ", description=" + description + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", image=" + image + ", salles=" + salles + ", votes=" + votes
				+ ", participations=" + participations + "]";
	}

	@PostLoad
	public void wrapping(){
		if(this.image == null) return;
		this.wrapper = new String(image);
	}
	
	
   
}
