package Advanced.Pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.crypto.spec.PSource;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * ClassName: DruidTest
 * Package: Advanced.Pool
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/4 下午9:42
 * @Version 1.0
 */
public class DruidTest {
	@Test
	public void testHardCodeDruid() throws Exception{
		DruidDataSource druidDataSource = new DruidDataSource();

		druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		druidDataSource.setUrl("jdbc:mysql:///atguigu");
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("yk848601");

		druidDataSource.setInitialSize(10);
		druidDataSource.setMaxActive(20);

		DruidPooledConnection connection = druidDataSource.getConnection();
		System.out.println(connection);

		connection.close();
	}
	
	@Test
	public void testSoftCodeDruid() throws Exception{
		Properties properties = new Properties();

		InputStream is = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");

		properties.load(is);
		Set<Map.Entry<Object, Object>> entries = properties.entrySet();
		for (Map.Entry<Object, Object> entry : entries) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}

		DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

		Connection connection = dataSource.getConnection();
		System.out.println(connection);

		//数据库开发

		connection.close();
		is.close();
	}
}
