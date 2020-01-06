package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class LinkPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idBand;
	private int idUser;
	private Date dateDebut;

	public LinkPk() {
	}

	public LinkPk(String idBand, int idUser, Date dateDebut) {
		this.idBand = idBand;
		this.idUser = idUser;
		this.dateDebut = dateDebut;
	}

	public String getIdBand() {
		return idBand;
	}
	public void setIdBand(String idBand) {
		this.idBand = idBand;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((idBand == null) ? 0 : idBand.hashCode());
		result = prime * result + idUser;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkPk other = (LinkPk) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (idBand == null) {
			if (other.idBand != null)
				return false;
		} else if (!idBand.equals(other.idBand))
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LinkPk [idBand=" + idBand + ", idUser=" + idUser + ", dateDebut=" + dateDebut + "]";
	}
	
	

}
