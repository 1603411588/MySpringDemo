package com.liuyi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuyi.entity.Person;
import com.liuyi.service.PersonService;

@RestController
public class HelloController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/hello/{name}")
	public String sayHello(@PathVariable String name) {
		return " Hello " + name;
	}

	@RequestMapping("/hello/findAllPerson")
	public List<Person> findAllPerson() {
		List<Person> findAllPerson = personService.findAllPerson();
		return findAllPerson;
	}
}
