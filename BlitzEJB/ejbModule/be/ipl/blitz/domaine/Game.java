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
				int nextPlayer = Util.modulo(game.currentUser + game.direction, game.users.size());
				if (game.skippedPlayers.contains(nextPlayer)) {
					game.skippedPlayers.remove(nextPlayer);
					nextPlayer = Util.modulo(nextPlayer + game.direction, game.users.size());
				}
				game.setCurrentUser(nextPlayer);
				return game.users.get(game.getCurrentUser());
			}

			@Override
			void removeDie(int num, PlayerGame pg, Game game) {
				pg.removeDie(num);
			}

			@Override
			void keepRandomCard(PlayerGame p, int num) {
				int nbCards = p.getCards().size();
				for (int i = 0; i < nbCards - num; i++) {
					p.removeCard(i);
				}
			}

			@Override
			void endGame(Game g) {
				g.setState(OVER);
				if (g.users.size() < 2) {
					g.setWinner(g.users.get(0).getUser().getName());
				} else {
					for (PlayerGame p : g.getUsers()) {
						if (p.getNbDice() == 0) {
							g.setWinner(p.getUser().getName());
							return;
						}
					}
				}
			}

			@Override
			void skipTurn(PlayerGame p, Game g) {
				for (int i = 0; i < g.getUsers().size(); i++) {
					if (p.equals(g.getUsers().get(i))) {
						g.skippedPlayers.add(i);
					}
				}
			}

			@Override
			void exchangeDice(String direction, Game game) {
				List<PlayerGame> players = game.getUsers();
				int tmp;
				int nbDicePreviousPlayer = players.get(0).getNbDice();
				if (direction.equals("r")) {
					for (int i=1; i < players.size(); i++) {
						PlayerGame p = players.get(i);
						tmp = p.getNbDice();
						p.setNbDice(nbDicePreviousPlayer);
						nbDicePreviousPlayer = tmp;
					}
				}else{
					for (int i=players.size()-1; i >=0 ; i--) {
						PlayerGame p = players.get(i);
						tmp = p.getNbDice();
						p.setNbDice(nbDicePreviousPlayer);
						nbDicePreviousPlayer = tmp;
					}
				}
			}
		},
		OVER {
			@Override
			void removePlayer(PlayerGame pg, Game g) {
			}

			@Override
			String getWinner(Game game) {
				return game.winner;
			}
		};

		void removePlayer(PlayerGame player, Game g) {
			if (player == null) {
				return;
			}
			if (g.users.contains(player)) {
				g.users.remove(player);
			}
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

		String getWinner(Game game) {
			return null;
		}

		PlayerGame nextPlayer(Game game) {
			return null;
		}

		void changeDirection(Game game) {
		}

		void keepRandomCard(PlayerGame playerGame, int num) {
		}

		void endGame(Game game) {
		}

		void skipTurn(PlayerGame playerGame, Game game) {
		}

		void exchangeDice(String direction, Game game) {
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
	private String winner;

	@Transient
	private int currentUser;

	@Transient
	private int direction = 1;

	@Transient
	private List<Integer> skippedPlayers = new ArrayList<>();

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWinner() {
		return state.getWinner(this);
	}

	public void setWinner(String winner) {
		Util.checkString(winner);
		this.winner = winner;
	}

	public void setState(State s) {
		Util.checkObject(s);
		this.state = s;
	}

	public State getState() {
		return this.state;
	}

	public int getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(int currentUser) {
		Util.checkPositiveOrZero(currentUser);
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
		Util.checkObject(pg);
		Util.checkPositiveOrZero(num);
		state.removeDie(num, pg, this);
	}

	public void removePlayer(PlayerGame player) {
		state.removePlayer(player, this);
	}

	public void cancel() {
		state = State.OVER;
	}

	public void keepRandomCard(PlayerGame playerGame, int num) {
		Util.checkObject(playerGame);
		Util.checkPositiveOrZero(num);
		state.keepRandomCard(playerGame, num);
	}

	public void endGame() {
		state.endGame(this);
	}

	public void skipTurn(PlayerGame playerGame) {
		state.skipTurn(playerGame, this);
	}

	public void exchangeDice(String direction) {
		state.exchangeDice(direction, this);
	}
}