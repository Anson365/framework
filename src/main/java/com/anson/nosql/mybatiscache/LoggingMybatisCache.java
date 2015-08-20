package com.anson.nosql.mybatiscache;

import org.apache.ibatis.cache.decorators.LoggingCache;

public class LoggingMybatisCache extends LoggingCache {

	public LoggingMybatisCache(String id) {
		
		super(new MybatisCache(id));
	
	}

}
