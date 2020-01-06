package tn.esprit.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Participation
 *
 */
@Entity

public class Participation implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Participation() {
		super();
	}
   
	@EmbeddedId
	private ParticipationPk id;
	private String commentaire;
	private String rating;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idEvent",referencedColumnName="id",insertable=false,updatable=false)
	private Event event;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User user;

	public ParticipationPk getId() {
		return id;
	}
	public void setId(ParticipationPk id) {
		this.id = id;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Participation [id=" + id + ", commentaire=" + commentaire + ", rating=" + rating + ", event=" + event
				+ ", user=" + user + "]";
	}
	
}
