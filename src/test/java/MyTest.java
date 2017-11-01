import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class MyTest {
	
	@Test
	public void test2(){
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		System.out.println(queue);
		queue.offer(6);
		System.out.println(queue);
		System.out.println("///////////////////////////////////////////////");

		System.out.println(queue.peek());
		System.out.println(queue.peek());
		
		System.out.println(queue.poll());
	}

	@Test
	public void test1() {
		int randomRedPacket = randomRedPacket(100, 1, 50, 10);
		System.out.println(randomRedPacket);
	}

	private List<Integer> random(int totalMax, int perMin, int perMax, int num) {
		List<Integer> list = new ArrayList<>();
		Random r = new Random();
		int a = r.nextInt(perMax - perMin) + perMin;
		return list;
	}

	@Test
	public void testDom4j() throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\openapi-bss-dubbo-consumer.xml")));
		System.out.println(document);
		Element rootElement = document.getRootElement();
		List selectNodes = document.selectNodes("//bean");
		for (Object object : selectNodes) {
			System.out.println(object);
		}
	}

	/** 
	 * 随机分配一个红包 
	 * @param money 
	 * @param minS :最小金额 
	 * @param maxS ：最大金额(每个红包的默认Times倍最大值) 
	 * @param count 
	 * @return 
	 */
	private int randomRedPacket(int money, int minS, int maxS, int count) {
		//若是只有一个，直接返回红包  
		if (count == 1) {
			return money;
		}
		//若是最小金额红包 == 最大金额红包， 直接返回最小金额红包  
		if (minS == maxS) {
			return minS;
		}
		//校验 最大值 max 要是比money 金额高的话？ 去 money 金额  
		int max = maxS > money ? money : maxS;
		//随机一个红包 = 随机一个数* (金额-最小)+最小  
		int one = ((int) Math.rint(Math.random() * (max - minS) + minS));
		//剩下的金额  
		int moneyOther = money - one;
		//校验这种随机方案是否可行，不合法的话，就要重新分配方案  
		if (isRight(moneyOther, count - 1)) {
			return one;
		} else {
			//重新分配  
			double avg = moneyOther / (count - 1);
			//本次红包过大，导致下次的红包过小；如果红包过大，下次就随机一个小值到本次红包金额的一个红包  
			if (avg < MINMONEY) {
				//递归调用，修改红包最大金额    
				return randomRedPacket(money, minS, one, count);
			} else if (avg > MAXMONEY) {
				//递归调用，修改红包最小金额    
				return randomRedPacket(money, one, maxS, count);
			}
		}
		return one;
	}
	
    private static final int MINMONEY =1;  
    private static final int MAXMONEY =200*100;  
	
	/**  
     * 红包 合法性校验  
     * @param money  
     * @param count  
     * @return  
     */  
    private boolean isRight(int money, int count) {  
        double avg =money/count;  
        //小于最小金额  
        if(avg<MINMONEY){  
            return false;  
        //大于最大金额      
        }else if(avg>MAXMONEY){  
            return false;  
        }  
        return true;  
    }  

}
