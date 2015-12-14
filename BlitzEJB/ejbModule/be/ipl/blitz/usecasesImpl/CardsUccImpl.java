package be.ipl.blitz.usecasesImpl;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;

import be.ipl.blitz.daoImpl.CardDaoImpl;
import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.usecases.CardsUcc;

public class CardsUccImpl implements CardsUcc {

	@EJB
	CardDaoImpl cardDao;
	
	private List<Card> deck;
	
	public CardsUccImpl() {
		deck = cardDao.getAll();
		Collections.shuffle(deck);
	}
	
	@Override
	public Card pickCard() {
		return deck.get(0);
	}

	@Override
	public void discard(Card card) {
		deck.add(card);
	}

}
