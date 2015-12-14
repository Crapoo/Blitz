package be.ipl.blitz.usecases;

import javax.ejb.Remote;

import be.ipl.blitz.domaine.Card;

@Remote
public interface CardsUcc {

	public Card pickCard();

	public void discard(Card card);
}
