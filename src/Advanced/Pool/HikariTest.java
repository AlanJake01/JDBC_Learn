package Advanced.Pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * ClassName: HikariTest
 * Package: Advanced.Pool
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/5 上午10:24
 * @Version 1.0
 */
public class HikariTest {
	@Test
	public void testHardCodeHikari() throws Exception{
		HikariDataSource hikariDataSource = new HikariDataSource();

		hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariDataSource.setJdbcUrl("jdbc:mysql:///atguigu");
		hikariDataSource.setUsername("root");
		hikariDataSource.setPassword("yk848601");

		hikariDataSource.setMinimumIdle(10);
		hikariDataSource.setMaximumPoolSize(20);

		Connection connection = hikariDataSource.getConnection();
		System.out.println(connection);

		connection.close();
	}

	@Test
	public void testSoftCodeHikari() throws Exception{
		Properties properties = new Properties();

		InputStream is = HikariTest.class.getClassLoader().getResourceAsStream("hikari.properties");
		properties.load(is);
		Set<String> set = properties.stringPropertyNames();
		for (String key : set) {
			System.out.println(key+" "+properties.getProperty(key));
		}

		HikariConfig hikariConfig = new HikariConfig(properties);
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

		Connection connection = hikariDataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}
}
