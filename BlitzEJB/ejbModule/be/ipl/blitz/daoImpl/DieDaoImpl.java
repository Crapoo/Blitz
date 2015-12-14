package be.ipl.blitz.daoImpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.Die;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class DieDaoImpl extends DaoImpl<Integer, Die> {

	public DieDaoImpl() {
		super(Die.class);
	}

	@Override
	public Die findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
