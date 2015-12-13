package be.ipl.blitz.daoImpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.Dice;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class DiceDaoImpl extends DaoImpl<Integer, Dice> {

	public DiceDaoImpl() {
		super(Dice.class);
	}

	@Override
	public Dice findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
