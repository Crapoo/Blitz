package be.ipl.blitz.daoImpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.blitz.domaine.Blitz;

@Stateless
@LocalBean
public class BlitzDaoImpl extends DaoImpl<Integer, Blitz> {

	public BlitzDaoImpl() {
		super(Blitz.class);
	}

	@Override
	public Blitz findById(Integer id) {
		return super.find(id);
	}
	
	public Blitz get() {
		return find(0);
	}
}
