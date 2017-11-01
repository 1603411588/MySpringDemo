import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class JdbcTest {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() throws SQLException {
		Connection connection = getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(" select * from person");
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + " -- " + resultSet.getString(2));
		}
	}

	private Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8", "root", "ly123456");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
