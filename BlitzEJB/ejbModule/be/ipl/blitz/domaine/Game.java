package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import be.ipl.blitz.utils.Util;

@Entity
@Table(name = "GAMES", schema = "BLITZ")
public class Game implements Serializable {

	public enum State {
		INITIAL {
			@Override
			boolean addPlayer(User user, Game game) {
				if (game.getPlayer(user) != null)
					return false;
				game.users.add(user);
				return true;
			}

			@Override
			boolean startGame(Game game) {
				game.state = State.IN_PROGRESS;
				Random r = new Random();
				game.currentUser = r.nextInt(game.players.size());
				for (User u : game.users) {
					game.players.add(new PlayerGame(u, game));
				}
				return true;
			}
		},
		IN_PROGRESS {
			@Override
			boolean startNextTurn(Game game) {
				return true;
			}

			@Override
			boolean throwDice(Game game) {
				PlayerGame p = game.players.get(game.currentUser);
				List<Face> facesThrown = new ArrayList<>();

				return true;
			}

			@Override
			boolean ecarterDe(int num, Game game) {
				return false;
			}
		},

		OVER {
			@Override
			User getWinner(Game game) {
				return null;
			}
		};
		boolean addPlayer(User u, Game g) {
			return false;
		}

		boolean startGame(Game game) {
			return false;
		}

		boolean startNextTurn(Game game) {
			return false;
		}

		boolean throwDice(Game game) {
			return false;
		}

		boolean ecarterDe(int numero, Game game) {
			return false;
		}

		User getWinner(Game game) {
			return null;
		}
	}

	@Column
	@NotNull
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull
	private Date startDate;
	@Column
	private User winner;

	@Transient
	private int currentUser;

	@ManyToMany(mappedBy = "games")
	private List<User> users;

	@Transient
	private List<PlayerGame> players;

	@Transient
	private State state;

	// TODO : ajouter le sens du jeu (et le joueur courant?)

	public Game() {
		this("No name given");
	}

	public Game(String name) {
		Util.checkString(name);
		this.name = name;
		this.startDate = new Date();
		this.state = State.INITIAL;
	}

	public User getPlayer(User u) {
		return users.get(users.indexOf(u));
	}

	public List<User> getPlayers() {
		return this.users;
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

	public void setState(State s) {
		this.state = s;
	}

	public State getState() {
		return this.state;
	}

	public User getCurrentUser() {
		return users.get(currentUser);
	}

	public void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}

	public boolean addPlayer(User user) {
		return state.addPlayer(user, this);
	}

	public boolean startGame() {
		return state.startGame(this);
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

	public boolean throwDice() {
		return state.throwDice(this);
	}

	@Override
	public String toString() {
		return "Game [name=" + name + ", id=" + id + ", startDate=" + startDate + ", winner=" + winner
				+ ", currentUser=" + currentUser + ", users=" + users + ", players=" + players + ", state=" + state
				+ "]";
	}

}