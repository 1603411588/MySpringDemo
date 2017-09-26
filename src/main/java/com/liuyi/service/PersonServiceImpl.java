package com.liuyi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liuyi.dao.PersonMapper;
import com.liuyi.entity.PageQueryResult;
import com.liuyi.entity.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper personMapper;

	@Override
	public List<Person> findAllPerson() {
		List<Person> findAll = personMapper.findAll();
		return findAll;
	}

	@Override
	public void add(Person p) {
		personMapper.add(p);
	}

	@Override
	public PageQueryResult<Person> pageQuery(Integer pageNum, Integer pageSize) {
		Page<Person> page = PageHelper.startPage(pageNum, pageSize," name asc");
		List<Person> rows = personMapper.findAll();
		long total = page.getTotal();
		PageQueryResult<Person> result = new PageQueryResult<>();
		result.setRows(rows);
		result.setTotal(total);
		return result;
	}

}
