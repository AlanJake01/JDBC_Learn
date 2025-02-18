package Base;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * ClassName: JDBCQuicker
 * Package: Base
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/4 下午5:38
 * @Version 1.0
 */
public class JDBCQuicker {
	public static void main(String[] args) throws Exception{
		//1、注册驱动
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		DriverManager.registerDriver(new Driver());

		//2、获取数据库操作对象
		String url = "jdbc:mysql://localhost:3306/atguigu";
		String username = "root";
		String password = "yk848601";
		Connection connection = DriverManager.getConnection(url, username, password);

		//3、获取执行SQL语句的对象
		Statement statement = connection.createStatement();

		//4、编写sql语句并执行，接收返回的结果集
		String sql = "SELECT* FROM t_emp;";
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()){
			int empId = resultSet.getInt("emp_id");
			String empName = resultSet.getString("emp_name");
			double empSalary = resultSet.getDouble("emp_salary");
			int empAge = resultSet.getInt("emp_age");
			System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
		}

		//5、关闭资源
		resultSet.close();
		statement.close();
		connection.close();
	}
}
