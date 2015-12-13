package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import be.ipl.blitz.utils.Util;

@Entity
@Table(name = "GAMES", schema = "BLITZ")
public class Game implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull
	private Date startDate;
	@Column
	private User winner;
	
	@ManyToMany
	@JoinTable(name="PLAYERS_GAME", schema="BLITZ", joinColumns={@JoinColumn(name="game_id")},
	inverseJoinColumns={@JoinColumn(name="player_id")})
	
	private List<User> players;

	// TODO : ajouter le sens du jeu (et le joueur courant?)
	
	public Game() {
	}

	public Game(Date startDate, User winner, List<User> players) {
		super();
		Util.checkObject(startDate);
		Util.checkObject(winner);
		Util.checkObject(players);
		this.startDate = startDate;
		this.winner = winner;
		this.players = players;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		Util.checkPositiveOrZero(id);
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		Util.checkObject(startDate);
		this.startDate = startDate;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		Util.checkObject(winner);
		this.winner = winner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
