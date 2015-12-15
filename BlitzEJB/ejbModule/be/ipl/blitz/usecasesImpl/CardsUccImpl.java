package be.ipl.blitz.usecasesImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.blitz.daoImpl.CardDaoImpl;
import be.ipl.blitz.daoImpl.GameDaoImpl;
import be.ipl.blitz.daoImpl.UserDaoImpl;
import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.usecases.CardsUcc;

@Singleton
@Startup
public class CardsUccImpl implements CardsUcc {

	@EJB
	CardDaoImpl cardDao;
	@EJB
	UserDaoImpl userDao;
	@EJB
	GameDaoImpl gameDao;

	private List<Card> deck;

	public CardsUccImpl() {
	}

	@Override
	public void shuffleDeck() {
		deck = cardDao.getAll();
		Collections.shuffle(deck);
	}

	@Override
	public List<Card> pickCard(int nb) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < nb; i++) {
			cards.add(deck.get(0));
		}
		return cards;
	}

	@Override
	public void discard(Card card) {
		deck.add(card);
	}

	@Override
	public Card stealCardFrom(String username) {
		return null;
	}

}
