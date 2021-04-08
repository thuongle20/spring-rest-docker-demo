package com.spys.ms.sample.repository;

import java.io.Serializable;

/**
 *
 */
public interface BaseDAO<T, ID extends Serializable>
{

    T get(ID cid);

    T delete(T entity);

    T save(T entity);

    T update(T entity);
}
