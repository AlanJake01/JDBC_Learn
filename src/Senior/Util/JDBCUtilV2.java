package Senior.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JDBCUtilV2
 * Package: Senior.Util
 * Description:
 *JDBC工具类（V2.0）：
 *     1、维护一个连接池对象、维护了一个线程绑定变量的ThreadLocal对象
 *     2、对外提供在ThreadLocal中获取连接的方法
 *     3、对外提供回收连接的方法，回收过程中，将要回收的连接从ThreadLocal中移除！
 * 注意：工具类仅对外提供共性的功能代码，所以方法均为静态方法！
 * 注意：使用ThreadLocal就是为了一个线程在多次数据库操作过程中，使用的是同一个连接！
 * @Author AlanJake
 * @Create 2025/2/5 下午7:29
 * @Version 1.0
 */
public class JDBCUtilV2 {
	//1、创建连接池引用供给全局对象
	private static DataSource dataSource;
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

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
			//从threadlocal获取连接对象
			Connection connection = threadLocal.get();
			if(connection == null){
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//4、设置释放连接池引用方法
	public static void Relase(){
		try {
			Connection connection = threadLocal.get();
			if(connection != null){
				threadLocal.remove();
				if(!connection.getAutoCommit()){
					connection.setAutoCommit(true);
				}
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
