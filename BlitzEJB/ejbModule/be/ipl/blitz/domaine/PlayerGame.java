package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.ipl.blitz.usecasesImpl.CardsUccImpl;

@SuppressWarnings("serial")
@Entity
@Table(name = "PLAYERS_GAMES", schema = "BLITZ")
@IdClass(PlayerGamePK.class)
public class PlayerGame implements Serializable {

	@Id
	private int userId;
	@Id
	private int gameId;

	@Transient
	@EJB
	private CardsUccImpl cardUcc;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "PLAYERID", referencedColumnName = "ID")
	// @JoinColumn(name = "userId", updatable = false, insertable = false)
	private User user;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "GAMEID", referencedColumnName = "ID")
	// @JoinColumn(name = "gameId", updatable = false, insertable = false)
	private Game game;

	@Transient
	private int nbDice = 0;

	@ManyToMany
	@JoinTable(schema = "BLITZ")
	private List<Die> dice;
	@ManyToMany
	@JoinTable(schema = "BLITZ")
	private List<Card> cards;

	public PlayerGame(User u, Game g) {
		dice = new ArrayList<Die>();
		// instanciation d'un dé pour recuperer le nombre de dé par personne
		Die d = new Die();
		for (int i = 0; i < d.getNbByPlayer(); i++) {
			dice.add(new Die());
		}
	}

	public PlayerGame() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game g) {
		this.game = g;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public List<Die> getDice() {
		return dice;
	}

	public boolean removeDie() {
		if (dice.isEmpty()) {
			return false;
		}
		this.dice.remove(0);
		return true;
	}

	public void setDice(List<Die> dice) {
		this.dice = dice;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public Card removeCard(int index) {
		return cards.remove(index);
	}
}
