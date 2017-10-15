package com.liuyi.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.liuyi.entity.Person;
import com.liuyi.event.CommonMessageEvent;
import com.liuyi.service.HelloService;
import com.liuyi.service.PersonService;
import com.liuyi.service.RedisLockService;
import com.liuyi.service.RedisService;
import com.liuyi.util.ContextUtils;
import com.liuyi.util.JsonUtils;
import com.liuyi.ws.weather.ArrayOfString;
import com.liuyi.ws.weather.WeatherWS;
import com.liuyi.ws.weather.WeatherWSSoap;
import com.liuyi.ws.weather.GetRegionDatasetResponse.GetRegionDatasetResult;

@RestController
@RequestMapping("/hello")
public class HelloController {

	private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private PersonService personService;

	@Autowired
	private RedisLockService redisLockService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private HelloService helloService;

	private static final String key = "LY_LY_Test_Queue_Inventory";
	private static final String lock_key = "LY_LY_Test_Lock_Key";

	public void testTx() {

	}

	@RequestMapping("/testEvent")
	public void testApplicationEvent() {
		ContextUtils.publishEvent(new CommonMessageEvent(" Hello World!"));
		System.out.println("testApplicationEvent:" + System.currentTimeMillis());
	}

	@RequestMapping("/textWS")
	@ResponseBody
	public List<String> testWS() {
		WeatherWS weatherWS = new WeatherWS();
		WeatherWSSoap weatherWSSoap = weatherWS.getWeatherWSSoap();
		List<String> string = weatherWSSoap.getSupportCityString("31118").getString();
		System.out.println(string);
		List<String> list = weatherWSSoap.getWeather("1668", "").getString();
		return list;
	}

	@RequestMapping("/testContext")
	public void testContext(HttpServletRequest request) {
		WebApplicationContext rootContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		System.out.println(rootContext.getBean(HelloController.class));
		WebApplicationContext childContext = RequestContextUtils.findWebApplicationContext(request);
		System.out.println(childContext.getBean(HelloController.class));
		String[] beanDefinitionNames = rootContext.getBeanDefinitionNames();
		System.out.println(this);
		System.out.println(Arrays.asList(beanDefinitionNames));
		String[] beanDefinitionNames2 = childContext.getBeanDefinitionNames();
		System.out.println(Arrays.asList(beanDefinitionNames2));
	}

	@RequestMapping("/testAop")
	@ResponseBody
	public void testAop() {
		helloService.test1();
	}

	@RequestMapping(value = "/testJson", produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String test(@RequestBody JsonNode data) throws IOException {
		System.out.println(data);
		Iterator<JsonNode> iterator = data.get("data").iterator();
		Map<String, Set<Map<String, Object>>> resultMap = new HashMap<>();
		while (iterator.hasNext()) {
			List<Map<String, Object>> list = new ArrayList<>();
			JsonNode next = iterator.next();
			Iterator<JsonNode> cityIter = next.get("city").iterator();
			String title = next.get("title").toString();
			while (cityIter.hasNext()) {
				JsonNode next2 = cityIter.next();
				String city_child = next2.get("city_child").toString();
				String city_parent = next2.get("city_parent").toString();
				if (city_child.equalsIgnoreCase(city_parent)) {
					Map<String, Object> map = new HashMap<>();
					map.put("city_id", next2.get("city_id").asLong());
					map.put("city_name", next2.get("city_child").asText());
					map.put("city_name_ab", next2.get("city_name_ab").asText());
					map.put("province", next2.get("provcn").toString());
					list.add(map);
				}
			}
			Set<Map<String, Object>> set = new TreeSet<>(new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					return o1.get("city_id").toString().compareTo(o2.get("city_id").toString());
				}
			});
			set.addAll(list);
			resultMap.put(title, set);
		}
		// FileUtils.write(new File("D:\\aa.txt"),
		// JsonUtils.marshal(resultMap).replace("\\\"", ""));
		return JsonUtils.marshal(resultMap);
	}

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

	@RequestMapping("/testRedisLock")
	@ResponseBody
	public void testRedisLock() {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String threadName = Thread.currentThread().getName();
					redisLockService.lock(lock_key);
					try {
						List<?> list = redisService.findListFromCache(key);
						if (CollectionUtils.isEmpty(list)) {
							logger.warn(threadName + " ...已经抢光了...");
							return;
						}
						Object prize = list.get(0);
						list.remove(0);
						if (CollectionUtils.isEmpty(list)) {
							logger.warn(" ...已经抢光了...");
							return;
						}
						redisService.updateCache(key, list, 3600, TimeUnit.SECONDS);
						System.err.println(threadName + " ... 抢到...  " + prize);
					} finally {
						redisLockService.unlock(lock_key);
					}
				}
			}).start();
		}
	}
}