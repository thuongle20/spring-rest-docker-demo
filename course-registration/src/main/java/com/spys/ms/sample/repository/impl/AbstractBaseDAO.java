package com.spys.ms.sample.repository.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.spys.ms.sample.repository.BaseDAO;

public abstract class AbstractBaseDAO<T, ID extends Serializable> implements BaseDAO<T, ID> {

	@Autowired(required = true)
	protected SessionFactory sessionFactory;

	/**
	 * @see org.hibernate.Session#get(Class,Serializable)
	 * @param id primary key to criteria for.
	 *           <p>
	 * @return get reflected object.
	 */
	public T get(ID id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	/**
	 * Query for list of matched object by given properties.
	 *
	 * @param property name of property
	 * @param value    match for value
	 *                 <p>
	 * @return A list of record that their property match value.
	 */
	protected List<T> findByProperty(String property, Object value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	/**
	 * Query for list of matched object by given string data. For this is front
	 * matching method. <BR>
	 * Pattern: (value%)
	 *
	 * @param property name of property
	 * @param value    match for value
	 *                 <p>
	 * @return A list of record that their property match value.
	 */
	protected List<T> startsWith(String property, String value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.like(property, value + "%")).list();
	}

	/**
	 * Check if given property contains given value.<BR>
	 * Pattern: (%value%)
	 *
	 * @param property name of property
	 * @param value    match for value
	 *                 <p>
	 * @return A list of record that their property match value.
	 */
	protected List<T> contains(String property, String value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.like(property, "%" + value + "%")).list();
	}

	/**
	 * Query for list of matched object by given string data. For this is backend
	 * matching method.<BR>
	 * Pattern: (%value)
	 *
	 * @param property name of property
	 * @param value    match for value
	 *                 <p>
	 * @return A list of record that their property match value.
	 */
	protected List<T> endsWith(String property, String value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.like(property, "%" + value)).list();
	}

	/**
	 * Query for unique object of matched by given criteria.
	 *
	 * @param property name of property
	 * @param value    match for value
	 *                 <p>
	 * @return An record that its property match value.
	 */
	protected T findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(Restrictions.eq(property, value)).uniqueResult();
	}

	/**
	 * This method count quantity of returned row filtered by property.
	 *
	 * @param property The given property name
	 * @param value    The given property value
	 *                 <p>
	 * @return matched record number.
	 */
	protected int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value)).setProjection(Projections.rowCount())
				.uniqueResult())).intValue();
	}

	/**
	 * Similar to query, but predicate with criteria.
	 *
	 * @param criterion Given criteria to find.
	 *                  <p>
	 * @return A list of record that match the given criterion
	 */
	protected List findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	/**
	 * Create a criteria to filter.
	 *
	 * @param criterions
	 *                   <p>
	 * @return AN criteria object.
	 */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * This method could used for reflection in later funtionality.
	 *
	 * @return return current entity class.
	 */
	abstract protected Class<T> getEntityClass();

	/**
	 * Default Save behavior. <BR>
	 * The returned bean will probably has ID. <BR>
	 * The way ID generates depends on Database and Bean setting.
	 *
	 * @param bean Bean to be persisted.
	 *
	 * @return Bean with ID probably filled.
	 */
	public T save(T bean) {
		getSession().save(bean);
		return bean;
	}

	/**
	 * Delete object by ID.
	 *
	 * @param id Should be the {@code ID} annotated field.
	 *
	 * @return The bean that is deleted.
	 */
	public T deleteByPK(ID id) {
		T entity = this.get(id);
		return this.delete(entity);
	}

	/**
	 * Delete object.
	 *
	 * @param bean The bean to be deleted.
	 *
	 * @return The bean that is deleted.
	 */
	public T delete(T bean) {
		Assert.notNull(bean);
		getSession().delete(bean);
		return bean;
	}

	/**
	 * Generate HQL with properties set.
	 *
	 * @param hql    The template HQL
	 * @param values the properties values to be set into the template HQL
	 *               <p>
	 * @return a list of matched record with original object type.
	 */
	protected List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * Find unique or the first record return from database.
	 *
	 * @param hql    The template HQL
	 * @param values the properties values to be set into the template HQL
	 *               <p>
	 * @return Object matched record with original type.
	 */
	protected Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).setMaxResults(1).uniqueResult();
	}

	/**
	 * create query object.
	 *
	 * @param queryString query string of HQL
	 * @param values      properties for criteria
	 *                    <p>
	 * @return A query object.
	 */
	protected Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

//	/**
//	 * Inject a session factory from spring application context.
//	 *
//	 * @param sessionFactory The bean to be set.
//	 */
//	@Resource
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	/**
	 * Use {@code getCurrentSession()} from hibernate to get current hibernate
	 * session, thus there must be some opened session in container.
	 *
	 * @return Get current hibernate session.
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
