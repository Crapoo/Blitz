package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.ipl.blitz.usecasesImpl.CardsUccImpl;

@SuppressWarnings("serial")
@Entity
@Table(name = "PLAYERS_GAMES", schema = "BLITZ")
@IdClass(PlayerGamePK.class)
public class PlayerGame implements Serializable {
	@Id
	private int player;
	@Id
	private int game;
	
	@Transient
	@EJB
	private CardsUccImpl cardUcc;

	@ManyToMany
	@JoinTable(schema = "BLITZ")
	private List<Die> dice;
	@ManyToMany
	@JoinTable(schema = "BLITZ")
	private List<Card> cards;

	public PlayerGame(User u, Game g) {
		this.game=g.getId();
		this.player=u.getId();
		dice = new ArrayList<Die>();
		Die d=new Die();
		for(int i=0;i<d.getNbByPlayer();i++){
			dice.add(new Die());//todo: le de doit retenir le joueur
		}
		cards = cardUcc.pickCard(3);
	}
	public PlayerGame(){
		
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getGame() {
		return game;
	}

	public void setGame(int game) {
		this.game = game;
	}

	public List<Die> getDice() {
		return dice;
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
}
