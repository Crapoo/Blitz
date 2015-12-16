package be.ipl.blitz.daoImpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.Card;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class CardDaoImpl extends DaoImpl<Integer, Card> {

	public CardDaoImpl() {
		super(Card.class);
	}

	@Override
	public Card findById(Integer id) {
		return find(id);
	}

	public List<Card> getAll() {
		return list("SELECT c FROM Card c");
	}

}
