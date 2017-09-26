package com.liuyi.dao;

import java.util.List;

import com.liuyi.entity.Person;

public interface PersonMapper {

	public List<Person> findAll();

	public void add(Person p);
}
