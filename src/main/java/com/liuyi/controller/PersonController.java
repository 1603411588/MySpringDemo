package com.liuyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuyi.entity.PageQueryResult;
import com.liuyi.entity.Person;
import com.liuyi.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/pageQuery")
	@ResponseBody
	public PageQueryResult<Person> pageQuery(Integer pageNum, Integer pageSize) {
		return personService.pageQuery(pageNum, pageSize);
	}
}
