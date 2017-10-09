package com.liuyi.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisLockServiceImpl implements RedisLockService {

	private static final Logger logger = LoggerFactory.getLogger(RedisLockServiceImpl.class);

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public boolean lock(String key) {
		long timeout = TimeUnit.SECONDS.toNanos(60); // 60s
		return lock(key, timeout, 60);
	}

	/**
	 * 加锁
	 * 使用方式为：
	 * lock();
	 * try{
	 *    executeMethod();
	 * }finally{
	 *   unlock();
	 * }
	 * @param timeout timeout的时间范围内轮询锁
	 * @param expire 设置锁超时时间
	 * @return 成功 or 失败
	 */
	@SuppressWarnings("unchecked")
	public boolean lock(String key, long timeout, int expire) {
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		long nanoTime = System.nanoTime();
		RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
		RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
		byte[] keyBytes = keySerializer.serialize(key);
		byte[] valueBytes = valueSerializer.serialize(key);
		try {
			//在timeout的时间范围内不断轮询锁
			while (System.nanoTime() - nanoTime < timeout) {
				//锁不存在的话，设置锁并设置锁过期时间，即加锁
				if (connection.setNX(keyBytes, valueBytes)) {
					connection.expire(keyBytes, expire);//设置锁过期时间是为了在没有释放
					//锁的情况下锁过期后消失，不会造成永久阻塞
					return true;
				}
				logger.debug(Thread.currentThread().getName() + " ...wait for acquire lock...");
				//短暂休眠，避免可能的活锁
				Thread.sleep(30);
			}
		} catch (Exception e) {
			throw new RuntimeException("redis locking error", e);
		}
		return false;
	}

	public void unlock(String key) {
		redisTemplate.delete(key);
	}
}
