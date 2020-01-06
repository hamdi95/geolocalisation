package tn.esprit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Node
 *
 */
@Entity
@Table(name="nodes")
public class Node implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Node() {
		super();
	}
   
	@Id
	@Column(name="nodeId")
	private String id;
	private String description;
	private String ip;
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="idSalle")
	private Salle salle;
	@OneToMany(mappedBy="node",cascade = CascadeType.DETACH)
	@JsonIgnore
	private List<Commande> commandes;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	
	@Override
	public String toString() {
		return "Node [id=" + id + ", description=" + description + ", ip=" + ip + ", salle=" + salle + ", commandes="
				+ commandes + "]";
	}
	
}
