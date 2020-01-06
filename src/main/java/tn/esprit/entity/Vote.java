package tn.esprit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vote
 *
 */
@Entity
@Table(name="votes")
public class Vote implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Vote() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String description;
	private String msg;
	private Date date;
	private String options;
	@OneToMany(mappedBy="vote",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<VoteResponse> voteResponses = new ArrayList<>();
	@ManyToOne()
	private Event event;
	@ManyToOne
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<VoteResponse> getVoteResponses() {
		return voteResponses;
	}
	public void setVoteResponses(List<VoteResponse> voteResponses) {
		this.voteResponses = voteResponses;
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
		return "Vote{" +
				"id=" + id +
				", description='" + description + '\'' +
				", msg='" + msg + '\'' +
				", date=" + date +
				", options='" + options + '\'' +
				", voteResponses=" + voteResponses +
				", event=" + event +
				", user=" + user +
				'}';
	}
}
