package com.liuyi.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface RedisService {

	/**
	 * 删除key
	 * 
	 * @param key
	 */
	void removeKey(String key);

	/**
	 * 缓存是否存在
	 * 
	 * @param key
	 * @return
	 */
	boolean hasKey(String key);

	/**
	 * 从缓存取List数据
	 * 
	 * @param key
	 * @param keyPrefix
	 * @return
	 */
	List<?> findListFromCache(String key);

	/**
	 * 从缓存取Map数据
	 * 
	 * @param key
	 * @param keyPrefix
	 * @return
	 */
	Map<?, ?> findMapFromCache(String key);

	/**
	 * 从缓存取Objcet对象数据
	 * 
	 * @param key
	 * @return
	 */
	Object findValueFromCache(String key);

	/**
	 * 更新List缓存
	 * 
	 * @param key
	 * @param keyPrefix
	 * @param cacheList
	 */
	void updateCache(String key, List<?> cacheList);

	/**
	 * 更新List缓存 并设置有效期
	 * 
	 * @param key
	 * @param keyPrefix
	 * @param cacheList
	 * @param timeOut
	 */
	void updateCache(String key, List<?> cacheList, long timeout, TimeUnit unit);

	/**
	 * 更新Map缓存
	 * 
	 * @param key
	 * @param keyPrefix
	 * @param cacheList
	 */
	void updateCache(String key, Map<String, Object> cacheMap);

	/**
	 * 更新Map缓存 并设置有效期
	 * 
	 * @param key
	 * @param keyPrefix
	 * @param cacheMap
	 * @param timeOut
	 */
	void updateCache(String key, Map<String, Object> cacheMap, long timeout, TimeUnit unit);

	/**
	 * 更新Object缓存，并设置有效期
	 * 
	 * @param key
	 * @param obj
	 * @param timeout
	 * @param unit
	 */
	void updateCache(String key, Object value, long timeout, TimeUnit unit);
}
