package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.ejb.EJB;
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

import be.ipl.blitz.usecases.CardsUcc;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.utils.Util;

@Entity
@Table(name = "GAMES", schema = "BLITZ")
public class Game implements Serializable {

	@EJB
	@Transient
	private CardsUcc cardUcc;
	@EJB
	@Transient
	private GameUcc gameUcc;

	public enum State {
		INITIAL {
			@Override
			PlayerGame addPlayer(User user, Game game) {
				Util.checkObject(user);
				Util.checkObject(game);

			/*	// pour pas ajouter deux fois le meme utilisateur
				if (game.users != null) {
					for (PlayerGame p : game.users) {
						if (p.getUser().equals(user)) {
							return null;
						}
					}
				}
*/
				PlayerGame p = new PlayerGame(user, game);
				game.users.add(p);
				return p;
			}

			@Override
			boolean startGame(Game game) {
				game.state = State.IN_PROGRESS;
				Random r = new Random();
				game.currentUser = r.nextInt(game.users.size());
				return dealCards(game);
			}

			private boolean dealCards(Game game) {
				for (PlayerGame playerGame : game.users) {
					List<Card> cards = game.drawCard(3);
					if (cards == null) {
						return false;
					}
					playerGame.setCards(cards);
				}
				return true;
			}
		},
		IN_PROGRESS {
			@Override
			User nextPlayer(Game game) {
				game.setCurrentUser((++game.currentUser) % game.users.size());
				return game.getCurrentUser();
			}

			@Override
			Set<Face> throwDice(Game game) {
				PlayerGame p = game.users.get(game.currentUser);
				Set<Face> faces = new HashSet<Face>();
				for (Die d : p.getDice()) {
					faces.add(d.throwDice());
				}
				return faces;
			}

			@Override
			boolean deleteDice(int num, String username, Game game) {
				// PlayerGame p =
				// game.users.get(game.users.indexOf(game.userDao.findByName(username)));
				// int tmp = 0;
				// while (p.removeDie() && tmp < num) {
				// tmp++;
				// }
				return false;
			}

			@Override
			List<Card> drawCard(int num, Game game) {
				return game.cardUcc.drawCard(num);
			}
		},
		OVER {
			@Override
			User getWinner(Game game) {
				return null;
			}
		};
		PlayerGame addPlayer(User u, Game g) {
			return null;
		}

		List<Card> drawCard(int num, Game game) {
			return null;
		}

		boolean startGame(Game game) {
			return false;
		}

		boolean startNextTurn(Game game) {
			return false;
		}

		Set<Face> throwDice(Game game) {
			return null;
		}

		boolean deleteDice(int num, String id, Game game) {
			return false;
		}

		User getWinner(Game game) {
			return null;
		}

		User nextPlayer(Game game) {
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

	// TODO : ajouter le sens du jeu

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

	public User getCurrentUser() {
		return null;
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

	public Set<Face> throwDice() {
		return state.throwDice(this);
	}

	public User nextPlayer() {
		return state.nextPlayer(this);
	}

	public boolean deleteDice(int num, String username) {
		return state.deleteDice(num, username, this);
	}

	public List<Card> drawCard(int num) {
		return state.drawCard(num, this);
	}

	public void cancel() {
		state = State.OVER;
	}
}