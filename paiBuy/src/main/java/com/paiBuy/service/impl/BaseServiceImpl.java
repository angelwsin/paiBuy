package com.paiBuy.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paiBuy.dao.mapper.BaseDao;
import com.paiBuy.service.BaseService;

@Service
public class BaseServiceImpl<T>  implements BaseService<T>{
        
	@Autowired
	    private BaseDao<T>baseDao;
	
	public void save(T entry) {
		// TODO Auto-generated method stub
		  baseDao.save(entry,getClazz());
	}
	
	protected Class<T> clazz;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseServiceImpl() {
		Class type = getClass();
		if (type != BaseServiceImpl.class) {
			Class parent = type.getSuperclass();
			while (parent != BaseServiceImpl.class) {
				parent = (type = parent).getSuperclass();
			}
			Type[] types = ((ParameterizedType) type.getGenericSuperclass()).getActualTypeArguments();
			if (types.length > 0) {
				this.clazz = (Class<T>) types[0];
			}
		}
	}

	public Class<T> getClazz() {
		return this.clazz;
	}

	public void update(T entry,String ... where) {
		// TODO Auto-generated method stub
		baseDao.update(entry, getClazz(), where);
	}

	public void delete(T entry, String... where) {
		// TODO Auto-generated method stub
		baseDao.delete(entry, getClazz(), where);
	}

}
