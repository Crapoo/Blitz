package be.ipl.blitz.domaine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.transform.stream.StreamSource;

import be.ipl.blitz.daoImpl.CardDaoImpl;
import be.ipl.blitz.daoImpl.DiceDaoImpl;
import be.ipl.blitz.daoImpl.UserDaoImpl;

@javax.ejb.Startup
@Singleton
public class Startup {

	@EJB
	private DiceDaoImpl diceDao;
	@EJB
	private CardDaoImpl cardDao;
	@EJB
	private UserDaoImpl userDao;

	public Startup() {
	}

	@PostConstruct
	public void go() throws JAXBException, IOException {
		InputStream is = new FileInputStream("../standalone/deployments/BlitzEAR.ear/BlitzEJB.jar/blitz.xml");

		Blitz blitz = fromStream(Blitz.class, is);

		// enregistrement des dés
		for (int i = 0; i < blitz.getDice().getTotalNbDices(); i++) {
			Dice de = new Dice();
			diceDao.save(de);
		}

		try {
			userDao.save(new User("em", "em"));
			userDao.save(new User("mi", "mi"));
			userDao.save(new User("ol", "ol"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// enregistrement des Cards
		for (Card card : blitz.getCards().getCard()) {
			int nbCardsType = card.getNb();
			Card[] cards = new Card[nbCardsType];
			for (int i = 0; i < nbCardsType; i++) {
				/*
				 * FIXME dans l'exemple on peut directement cloner sans le try
				 * catch, tout ça :/
				 */
				try {
					cards[i] = (Card) card.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				cardDao.save(cards[i]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T fromStream(Class<T> clazz, InputStream input) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(clazz);
		Object result = ctx.createUnmarshaller().unmarshal(new StreamSource(input), clazz);
		if (result instanceof JAXBElement<?>) {
			result = JAXBIntrospector.getValue(result);
		}
		return (T) result;
	}
}
