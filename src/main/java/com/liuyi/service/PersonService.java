package com.liuyi.service;

import java.util.List;

import com.liuyi.entity.Person;
import com.liuyi.util.PageQueryResult;

public interface PersonService {

	public List<Person> findAllPerson();

	public void add(Person p);

	public PageQueryResult<Person> pageQuery(Integer pageNum, Integer pageSize);

}
