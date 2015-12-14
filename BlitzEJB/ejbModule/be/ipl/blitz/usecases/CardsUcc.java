package be.ipl.blitz.usecases;

import java.util.List;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.Card;

@Remote
public interface CardsUcc {
	
	public void shuffleDeck();

	public List<Card> pickCard(int nb);

	public void discard(Card card);

	public Card stealCardFrom(String username);

}
