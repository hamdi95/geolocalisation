package tn.esprit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AlbumSalle
 *
 */
@Entity

public class ImageSalle implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ImageSalle() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String image;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idSalle")
	private Salle salle;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	@Override
	public String toString() {
		return "ImageSalle [id=" + id + ", image=" + image + ", salle=" + salle + "]";
	}
	
	
}
