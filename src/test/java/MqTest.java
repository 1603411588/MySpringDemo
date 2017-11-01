import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class MqTest {

	private static final String Queue_Test1 = "Queue_Test1";

	public String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。     
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	@Test
	public void test() {
		String str = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".toLowerCase();
		String str2 = new StringBuilder(str).reverse().toString();
		List<String> list1 = Arrays.asList(str.split(" "));
		List<String> list2 = Arrays.asList(str2.split(" "));

		String s = "F963U F91XX F59Y9 F6211 F6765 F4V86".toLowerCase();
		s = s.replace(" ", "");
		StringBuilder re = new StringBuilder();
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			String t = c + "";
			if (str.contains(t)) {
				re.append(list2.get(list1.indexOf(c + "")));
			} else {
				re.append(c);
			}
		}
		String replace = re.toString().replace("u", "\\u");
		System.out.println(decodeUnicode(replace));
	}

	@Test
	public void testConsumer2() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("test");
		factory.setPassword("test");
		factory.setVirtualHost("/test");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//DeclareOk queueDeclare = channel.queueDeclare(Queue_Test1, true, false, false, null);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume("queue.testhost.test1", true, consumer);
		Delivery delivery = consumer.nextDelivery();
		System.out.println(new String(delivery.getBody()));
		channel.close();
		connection.close();
	}

	@Test
	public void testConsumer1() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//DeclareOk queueDeclare = channel.queueDeclare(Queue_Test1, true, false, false, null);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(Queue_Test1, true, consumer);
		Delivery delivery = consumer.nextDelivery();
		System.out.println(new String(delivery.getBody()));
		channel.close();
		connection.close();
	}

	@Test
	public void testProduce1() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		//		factory.setUsername("admin");
		//		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		DeclareOk declare = channel.queueDeclare(Queue_Test1, true, false, false, null);
		System.out.println(declare.toString());
		channel.basicPublish("", Queue_Test1, null, "Hello World!".getBytes());
		channel.close();
		connection.close();
	}

}
