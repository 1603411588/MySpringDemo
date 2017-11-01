import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class NIOTest {
	
	@Test
	public void test2() throws Exception{
		RandomAccessFile fromFile = new RandomAccessFile("e:\\tt.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("e:\\tt_copy.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		fromChannel.transferTo(0, fromChannel.size(), toChannel);
		fromFile.close();
		toFile.close();
	}

	@Test
	public void test1() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("e:\\tt.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}
}
