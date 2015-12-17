package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
			PlayerGame addPlayer(User user, Game game) {
				Util.checkObject(user);
				Util.checkObject(game);

				if (game.users == null) {
					game.users = new ArrayList<>();
				}

				PlayerGame p = new PlayerGame(user, game);

				// Evite d'ajouter deux fois le même joueur
				if (game.users.contains(p)) {
					return null;
				}
				game.users.add(p);
				return p;
			}

			@Override
			boolean startGame(Game game) {
				Random r = new Random();
				int playerCount = game.users.size();
				if (playerCount == 0) {
					return false;
				}
				game.currentUser = r.nextInt(playerCount);
				game.state = State.IN_PROGRESS;
				return true;
			}

		},
		IN_PROGRESS {
			@Override
			void changeDirection(Game game) {
				if (game.direction == 1) {
					game.direction = -1;
				} else {
					game.direction = 1;
				}
			}

			@Override
			PlayerGame nextPlayer(Game game) {
				game.setCurrentUser((game.currentUser + game.direction) % game.users.size());
				return game.users.get(game.getCurrentUser());
			}

			@Override
			void removeDie(int num, PlayerGame pg, Game game) {
				pg.removeDie(num);
			}

		},
		OVER {
			@Override
			void removePlayer(PlayerGame pg, Game g) {

			}

			@Override
			User getWinner(Game game) {
				return null;
			}
		};
		void removePlayer(PlayerGame player, Game g) {
			g.users.remove(player);
		}

		PlayerGame addPlayer(User u, Game g) {
			return null;
		}

		boolean startGame(Game game) {
			return false;
		}

		boolean startNextTurn(Game game) {
			return false;
		}

		void removeDie(int num, PlayerGame pg, Game game) {
		}

		User getWinner(Game game) {
			return null;
		}

		PlayerGame nextPlayer(Game game) {
			return null;
		}

		void changeDirection(Game game) {

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

	@Transient
	private int direction = 1;

	// @Transient
	// private UserDaoImpl userDao;

	public List<PlayerGame> getUsers() {
		return users;
	}

	public void setUsers(List<PlayerGame> users) {
		this.users = users;
	}

	@Column
	@NotNull
	@Enumerated
	private State state;

	@OneToMany(mappedBy = "game")
	private List<PlayerGame> users;

	public Game() {
		this("No name given");
	}

	public Game(String name) {
		Util.checkString(name);
		this.name = name;
		this.startDate = new Date();
		this.state = State.INITIAL;
		users = new ArrayList<>();
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

	public int getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}

	public PlayerGame addPlayer(User user) {
		Util.checkObject(user);
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

	public void changeDirection() {
		state.changeDirection(this);
	}

	public PlayerGame nextPlayer() {
		return state.nextPlayer(this);
	}

	public void deleteDice(int num, PlayerGame pg) {
		state.removeDie(num, pg, this);
	}

	public void removePlayer(PlayerGame player) {
		state.removePlayer(player, this);
	}

	public void cancel() {
		state = State.OVER;
	}
}