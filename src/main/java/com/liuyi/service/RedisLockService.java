package com.liuyi.service;

public interface RedisLockService {

	public void lock(String key);

	public void lock(String key, long timeout, long expire, long sleepTime);

	public void unlock(String key);
}
