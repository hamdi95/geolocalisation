package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Link
 *
 */
@Entity

public class Link implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Link() {
		super();
	}
   
	@EmbeddedId
	private LinkPk id;
	private Date dateFin;
	private String nom;
	private String prenom;
	private int age;
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="idBand",referencedColumnName="id",insertable=false,updatable=false)
	private Band band;
	public LinkPk getId() {
		return id;
	}
	public void setId(LinkPk id) {
		this.id = id;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Band getBand() {
		return band;
	}
	public void setBand(Band band) {
		this.band = band;
	}
	@Override
	public String toString() {
		return "Link [id=" + id + ", dateFin=" + dateFin + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age
				+ ", user=" + user + ", band=" + band + "]";
	}
	
	
}
