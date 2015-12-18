package be.ipl.blitz.usecasesImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import be.ipl.blitz.daoImpl.CardDaoImpl;
import be.ipl.blitz.daoImpl.GameDaoImpl;
import be.ipl.blitz.daoImpl.PlayerGameDaoImpl;
import be.ipl.blitz.daoImpl.UserDaoImpl;
import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.usecases.CardsUcc;
import be.ipl.blitz.usecases.GameUcc;
import be.ipl.blitz.usecases.UserUcc;

@Singleton
@Startup
public class CardsUccImpl implements CardsUcc {

	@EJB
	CardDaoImpl cardDao;
	@EJB
	UserDaoImpl userDao;
	@EJB
	GameDaoImpl gameDao;

	@EJB
	PlayerGameDaoImpl playerGameDao;

	@EJB
	GameUcc gameUcc;
	@EJB
	UserUcc userUcc;

	private List<Card> deck;

	public CardsUccImpl() {
	}

	@Override
	public void shuffleDeck() {
		deck = cardDao.getAll();
		Collections.shuffle(deck);
	}

	@Override
	public List<Card> drawCard(int nb) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < nb; i++) {
			cards.add(deck.remove(0));
		}
		return cards;
	}

	@Override
	public void discard(Card card) {
		deck.add(card);
	}
}
