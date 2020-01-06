package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ParticipationPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idUser;
	private int idEvent;

	public ParticipationPk() {
	}

	public ParticipationPk(int idUser, int idEvent) {
		this.idUser = idUser;
		this.idEvent = idEvent;
	}

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEvent;
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
		ParticipationPk other = (ParticipationPk) obj;
		if (idEvent != other.idEvent)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ParticipationPk [idUser=" + idUser + ", idEvent=" + idEvent + "]";
	}
	
	

}
