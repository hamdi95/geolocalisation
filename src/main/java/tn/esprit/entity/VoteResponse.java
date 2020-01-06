package tn.esprit.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: VoteResponse
 *
 */
@Entity
@Table(name="voteresponses")
public class VoteResponse implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public VoteResponse() {
		super();
	}
	
	@EmbeddedId
	private VoteResponsePk id;
	private String response;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idVote",referencedColumnName="id",insertable=false,updatable=false)
	private Vote vote;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="idBand",referencedColumnName="id",insertable=false,updatable=false)
	private Band band;

	public VoteResponsePk getId() {
		return id;
	}
	public void setId(VoteResponsePk id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Vote getVote() {
		return vote;
	}
	public void setVote(Vote vote) {
		this.vote = vote;
	}
	public Band getBand() {
		return band;
	}
	public void setBand(Band band) {
		this.band = band;
	}
	@Override
	public String toString() {
		return "VoteResponse [id=" + id + ", response=" + response + ", vote=" + vote + ", band=" + band + "]";
	}
	
	
   
}
