package com.liuyi.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class RedisServiceImpl implements RedisService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public List<?> findListFromCache(String key) {
		Long size = redisTemplate.opsForList().size(key);
		List<?> result = redisTemplate.opsForList().range(key, 0, size - 1);
		return result;
	}

	@Override
	public Map<?, ?> findMapFromCache(String key) {
		Map<?, ?> result = redisTemplate.opsForHash().entries(key);
		return result;
	}

	@Override
	public void updateCache(String key, List<?> cacheList) {
		redisTemplate.delete(key);
		redisTemplate.opsForList().rightPushAll(key, cacheList);
	}

	@Override
	public void updateCache(String key, List<?> cacheList, long timeout, TimeUnit unit) {
		updateCache(key, cacheList);
		redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public void updateCache(String key, Map<String, Object> cacheMap) {
		redisTemplate.delete(key);
		redisTemplate.opsForHash().putAll(key, cacheMap);
	}

	@Override
	public void updateCache(String key, Map<String, Object> cacheMap, long timeout, TimeUnit unit) {
		updateCache(key, cacheMap);
		redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public boolean hasKey(String key) {
		Boolean bo = redisTemplate.hasKey(key);
		if (bo == null) {
			return false;
		}
		return bo;
	}

	@Override
	public Object findValueFromCache(String key) {
		Object object = redisTemplate.opsForValue().get(key);
		return object;
	}

	@Override
	public void updateCache(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.delete(key);
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	@Override
	public void removeKey(String key) {
		redisTemplate.delete(key);
	}
}
