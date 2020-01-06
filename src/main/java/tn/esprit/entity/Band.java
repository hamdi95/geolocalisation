package tn.esprit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Band
 *
 */
@Entity
@Table(name="bands")
public class Band implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Band() {
		super();
		voteResponses = new ArrayList<>();
	}
	
	@Id
	private String id;
	private String socketId;
	private float positionX;
	private float positionY;
	@Enumerated(EnumType.STRING)
	private Status status = Status.Disconnected;
	@OneToMany(mappedBy="band")
	@JsonIgnore
	private List<VoteResponse> voteResponses;
	@OneToMany(mappedBy="band")
	@JsonIgnore
	private List<Link> links;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSocketId() {
		return socketId;
	}

	public void setSocketId(String socketId) {
		this.socketId = socketId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<VoteResponse> getVoteResponses() {
		return voteResponses;
	}

	public void setVoteResponses(List<VoteResponse> voteResponses) {
		this.voteResponses = voteResponses;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "Band{" +
				"id='" + id + '\'' +
				", positionX=" + positionX +
				", positionY=" + positionY +
				", status=" + status +
				'}';
	}
}
