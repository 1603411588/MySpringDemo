package com.liuyi.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.liuyi.entity.Person;
import com.liuyi.service.PersonService;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/{name}")
	public String sayHello(@PathVariable String name) {
		return " Hello " + name;
	}

	@RequestMapping("/findAllPerson")
	public List<Person> findAllPerson() {
		List<Person> findAllPerson = personService.findAllPerson();
		return findAllPerson;
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(String desc, MultipartFile file) throws IOException {
		System.out.println(desc);
		if (file.isEmpty()) {
			return "File is empty";
		}
		String originalFilename = file.getOriginalFilename();
		String subfix = originalFilename.substring(originalFilename.lastIndexOf("."));
		String pathname = "D:\\Image\\" + UUID.randomUUID().toString() + subfix;
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(pathname));
		return "SUCCESS";
	}

}