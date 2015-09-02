package com.paiBuy.service;

public interface BaseService<T> {
	public void save(T entry);
	public void update(T entry,String ... where);
	public void delete(T entr,String ... where);
}
