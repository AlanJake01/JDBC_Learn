package Senior.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JDBCUtil
 * Package: Senior.Util
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/5 下午12:16
 * @Version 1.0
 */
public class JDBCUtil {
	//1、创建连接池引用供给全局对象
	private static DataSource dataSource;

	//2、类加载时就创建连接池对象并赋值给引用
	static {
		Properties properties = new Properties();
		InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");

		try {
			properties.load(is);
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//3、提供获取Connection对象方法
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//4、设置释放连接池引用方法
	public static void Relase(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
