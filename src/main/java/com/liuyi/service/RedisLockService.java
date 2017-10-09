package com.liuyi.service;

public interface RedisLockService {

	public boolean lock(String key);

	public boolean lock(String key, long timeout, int expire);

	public void unlock(String key);
}
