package be.ipl.blitz.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.Card;
import be.ipl.blitz.utils.Util;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class CardDaoImpl extends DaoImpl<Integer, Card> {

	public CardDaoImpl() {
		super(Card.class);
	}

	@Override
	public Card findById(Integer id) {
		Util.checkPositiveOrZero(id);
		String queryString = "SELECT c FROM Card c WHERE c.id = ?1";
		return search(queryString, id);
	}

	public List<Card> getAll() {
		return list("SELECT c FROM Card c");
	}

}
