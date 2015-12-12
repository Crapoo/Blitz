package be.ipl.blitz.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<K, E> extends Serializable {
	E findById(K id);

	E save(E entity);

	E update(E entity);

	E reload(K id);

	void delete(K id);

	List<E> list();
}
