package tn.esprit.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Commande
 *
 */
@Entity
@Table(name="commandes")
public class Commande implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Commande() {
		super();
	}
   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String description;
	private String getValue;
	private String setValue;
	@Column(nullable=true,name="min")
	private int minValue;
	@Column(nullable=true,name="max")
	private int maxValue;
	@ManyToOne()
	@JoinColumn(name="nodeId")
	private Node node;

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
	public String getGetValue() {
		return getValue;
	}
	public void setGetValue(String getValue) {
		this.getValue = getValue;
	}
	public String getSetValue() {
		return setValue;
	}
	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	@Override
	public String toString() {
		return "Commande [id=" + id + ", nom=" + nom + ", description=" + description + ", getValue=" + getValue
				+ ", setValue=" + setValue + ", minValue=" + minValue + ", maxValue=" + maxValue + ", node=" + node
				+ "]";
	}
	
	
}
