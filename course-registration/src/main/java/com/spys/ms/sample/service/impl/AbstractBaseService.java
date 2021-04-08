package com.spys.ms.sample.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spys.ms.sample.repository.BaseDAO;
import com.spys.ms.sample.service.BaseService;

@Transactional
public abstract class AbstractBaseService<T extends BaseDAO<V, ID>, V, ID extends Serializable>
		implements BaseService<T, V, ID> {

	@Override
	@Transactional(readOnly = true)
	public V get(ID id) {
		return getDAO().get(id);
	}

	@Override
	@Transactional()
	public void delete(V entity) {
		getDAO().delete(entity);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public V add(V entity) {
		return getDAO().save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public V update(V entity) {
		return getDAO().update(entity);
	}

}
