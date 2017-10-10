package com.liuyi.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisLockServiceImpl implements RedisLockService {

	private static final Logger logger = LoggerFactory.getLogger(RedisLockServiceImpl.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void lock(String key) {
		lock(key, 10000, 60000,1000);
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
	public void lock(final String key, final long timeout, final long expire,final long sleepTime) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				long timeout2 = TimeUnit.MILLISECONDS.toNanos(timeout);
				long expire2 = TimeUnit.MILLISECONDS.toSeconds(expire);
				long nanoTime = System.nanoTime();
				RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
				byte[] keyBytes = stringRedisSerializer.serialize(key);
				byte[] valueBytes = stringRedisSerializer.serialize("1");

				try {
					//在timeout的时间范围内不断轮询锁
					while (System.nanoTime() - nanoTime < timeout2) {
						//锁不存在的话，设置锁并设置锁过期时间，即加锁
						if (connection.setNX(keyBytes, valueBytes)) {
							connection.expire(keyBytes, expire2);//设置锁过期时间是为了在没有释放锁的情况下锁过期后消失，不会造成永久阻塞
							return null;
						}
						logger.debug(Thread.currentThread().getName() + " ...wait for acquire lock...");
						//短暂休眠，避免可能的活锁
						Thread.sleep(sleepTime);
					}
				} catch (Exception e) {
					throw new RuntimeException("redis locking error", e);
				}
				throw new RuntimeException("System Busy");
			}
		});
	}

	public void unlock(String key) {
		redisTemplate.delete(key);
	}
}
