package be.ipl.blitz.daoImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import be.ipl.blitz.dao.Dao;

@SuppressWarnings("serial")
public abstract class DaoImpl<K, E> implements Dao<K, E> {
	private Class<E> entityClass;

	@PersistenceContext(unitName = "blitz")
	private EntityManager entityManager;

	public DaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public E find(K id) {
		return (E) entityManager.find(entityClass, id);
	}

	public E save(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	public E update(E entity) {
		return entityManager.merge(entity);
	}

	public E reload(K id) {
		E entity = find(id);
		entityManager.refresh(entity);
		return entity;
	}

	public void delete(K id) {
		E entity = find(id);
		entityManager.remove(entity);
	}

	public List<E> list() {
		return list("select x from " + entityClass.getName() + " x");
	}

	protected List<E> list(String queryString, Object... params) {
		List<E> entities = null;
		TypedQuery<E> query = entityManager.createQuery(queryString, entityClass);
		int i = 0, j = 1;
		while (i < params.length) {
			if (params[i] instanceof Date) {
				query.setParameter(j, (Date) params[i], (TemporalType) params[i + 1]);
				i += 2;
			} else if (params[i] instanceof Calendar) {
				query.setParameter(j, (Calendar) params[i], (TemporalType) params[i + 1]);
				i += 2;
			} else {
				query.setParameter(j, params[i]);
				i++;
			}
			j++;
		}
		entities = query.getResultList();
		return entities;
	}

	public E search(String queryString, Object... params) {
		try {
			TypedQuery<E> query = entityManager.createQuery(queryString, entityClass);
			int i = 0, j = 1;
			while (i < params.length) {
					query.setParameter(j, params[i]);
					i++;
				j++;
			}
			return (E) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null; // throw new InternalError();
		}
	}
}
