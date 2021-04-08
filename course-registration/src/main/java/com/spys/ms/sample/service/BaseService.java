package com.spys.ms.sample.service;

import java.io.Serializable;

import com.spys.ms.sample.repository.BaseDAO;

/**
 *
 * @param <T>
 */
public interface BaseService<T extends BaseDAO<V, ID>, V, ID extends Serializable>
{

    public T getDAO();
    
    public V get(ID id);
    
    public void delete(V entity);
    
    public V update(V entity);
    
    public V add(V entity);
}
