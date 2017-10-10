package com.liuyi.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.liuyi.entity.CitiesPre;
import com.liuyi.entity.City;
import com.liuyi.entity.Person;
import com.liuyi.service.PersonService;
import com.liuyi.service.RedisLockService;
import com.liuyi.service.RedisService;
import com.liuyi.util.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

	private static final String key = "LY_LY_Test_Queue_Inventory";
	private static final String lock_key = "LY_LY_Test_Lock_Key";
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request) throws IOException{
		String string = IOUtils.toString(request.getInputStream(), "UTF-8");
		Map<String, List<Map<String, Object>>> resultMap = new HashMap<>();
		Map<String,List<Map<String, Object>>> unmarshal = JsonUtils.unmarshal(string, Map.class);
		List<Map<String, Object>> list = unmarshal.get("data");
		for (Map<String, Object> map : list) {
			List<Map<String, Object>> resultList = new ArrayList<>();
			String string2 = map.get("city").toString();
			String title = map.get("title").toString();
			List<Object> list2 = JsonUtils.unmarshal(string2, List.class);
			/*for (Map<String, Object> map2 : list2) {
				String city_child = map2.get("city_child").toString();
				String city_parent = map2.get("city_parent").toString();
				Map<String, Object> map3 = new HashMap<>();
				if (city_child.equals(city_parent)) {
//					 "city_id": 101271901,
					map3.put("city_id", map2.get("city_id").toString());
//			            "city_name": "阿坝",
					map3.put("city_name", map2.get("city_child").toString());
//			            "city_name_ab": "ab",
					map3.put("city_name_ab", map2.get("city_name_ab").toString());
//			            "province": "四川"
					map3.put("province", map2.get("provcn").toString());
				}*/
			}
			//resultMap.put(title, resultList);
		/*JSONObject fromObject = JSONObject.fromObject(string);
		JSONArray jsonArray = fromObject.getJSONArray("data");
		for (Object object : jsonArray) {
			JSONObject fromObject2 = JSONObject.fromObject(object);
			String string2 = fromObject2.getString("title");
			JSONArray jsonArray2 = fromObject2.getJSONArray("city");
			for (Object object2 : jsonArray2) {
				JSONObject fromObject3 = JSONObject.fromObject(object2);
				String city_child = fromObject3.getString("city_child");
				String city_parent = fromObject3.getString("city_parent");
			}
		}*/
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