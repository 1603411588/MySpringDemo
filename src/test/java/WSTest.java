import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.mail.Header;
import javax.xml.parsers.SAXParser;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.xml.sax.XMLReader;

import com.liuyi.util.JsonUtils;
import com.liuyi.ws.hello.HelloServer;
import com.liuyi.ws.hello.HelloServerService;
import com.liuyi.ws.hello.Person;
import com.liuyi.ws.translator.ArrayOfString;
import com.liuyi.ws.translator.EnglishChinese;
import com.liuyi.ws.translator.EnglishChineseSoap;

public class WSTest {
	
	@Test
	public void helloTest3()  throws Exception {
		HelloServerService service = new HelloServerService(new URL("http://127.0.0.1:9999/ws/hello"));
		HelloServer serverPort = service.getHelloServerPort();
		
		
		Person p1 = new Person();
		p1.setId(1L);
		p1.setAge(23);
		p1.setName("张三");
		p1.setSex("男");
		
		Person p2 = new Person();
		p2.setId(2L);
		p2.setAge(24);
		p2.setName("李四");
		p2.setSex("女");
		serverPort.addPerson(p1);
		serverPort.addPerson(p2);
		
		List<Person> list = serverPort.getAllPerson();
		System.out.println(list);
	}

	@Test
	public void helloTest2() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL");
		post.setHeader(HttpHeaders.CONTENT_TYPE,"text/xml;charset=UTF-8");
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
 + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> "
 + " <soap:Body> "
 + "    <getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">"
 + "      <mobileCode>13552591524</mobileCode>"
  + "      <userID></userID>"
  + "    </getMobileCodeInfo>"
 + "   </soap:Body>"
 + " </soap:Envelope>";
		post.setEntity(new StringEntity(data , "UTF-8"));
		CloseableHttpResponse httpResponse = httpClient.execute(post);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			SAXReader reader = new SAXReader();
			Document document = reader.read(new StringReader(response));
			Element rootElement = document.getRootElement();
			List elements = rootElement.elements();
			for (Object object : elements) {
				
				System.out.println(object);
				
			}
			List selectNodes3 = rootElement.selectNodes("//getMobileCodeInfoResponse");
			Node selectSingleNode = rootElement.selectSingleNode("//getMobileCodeInfoResult");
			List selectNodes2 = rootElement.selectNodes("//getMobileCodeInfoResult");
			List selectNodes = document.selectNodes("//getMobileCodeInfoResult");
			System.out.println(selectNodes2);
			System.out.println(selectNodes);
		} else {
			System.out.println(" error......");
		}
	}

	@Test
	public void helloTest() throws MalformedURLException {
		HelloServerService helloServerService = new HelloServerService(new URL("http://127.0.0.1:9999/ws/hello"));
		HelloServer helloServerPort = helloServerService.getHelloServerPort();
		String sayHello = helloServerPort.sayHello("刘毅");
		System.out.println(sayHello);
	}

	@Test
	public void traslatorTest() throws IOException {
		EnglishChinese englishChinese = new EnglishChinese();
		EnglishChineseSoap englishChineseSoap = englishChinese.getEnglishChineseSoap();
		ArrayOfString translatorString = englishChineseSoap.translatorString("hello");
		ArrayOfString translatorSentenceString = englishChineseSoap.translatorSentenceString("hello");
		System.out.println(JsonUtils.marshal(translatorSentenceString));
		System.out.println(JsonUtils.marshal(translatorString));
		byte[] mp3 = englishChineseSoap.getMp3("1059.mp3");
		FileUtils.writeByteArrayToFile(new File("d:\\hello.mp3"), mp3);
	}
}
