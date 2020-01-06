package tn.esprit.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class VoteResponsePk implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private int idVote;
	private String idBand;

	public VoteResponsePk() {
	}

	public VoteResponsePk(int idVote, String idBand) {
		this.idVote = idVote;
		this.idBand = idBand;
	}

	public int getIdVote() {
		return idVote;
	}
	public void setIdVote(int idVote) {
		this.idVote = idVote;
	}
	public String getIdBand() {
		return idBand;
	}
	public void setIdBand(String idBand) {
		this.idBand = idBand;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBand == null) ? 0 : idBand.hashCode());
		result = prime * result + idVote;
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
		VoteResponsePk other = (VoteResponsePk) obj;
		if (idBand == null) {
			if (other.idBand != null)
				return false;
		} else if (!idBand.equals(other.idBand))
			return false;
		if (idVote != other.idVote)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "VoteResponsePk [idVote=" + idVote + ", idBand=" + idBand + "]";
	}
	
	
}
