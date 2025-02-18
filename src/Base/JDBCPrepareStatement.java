package Base;

import java.sql.*;
import java.util.Scanner;

/**
 * ClassName: JDBCPrepareStatement
 * Package: Base
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/4 下午6:25
 * @Version 1.0
 */
public class JDBCPrepareStatement {
	public static void main(String[] args) throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu","root","yk848601");

//		Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM t_emp where emp_name = ?;");

		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();

		preparedStatement.setString(1,name);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			int empId = resultSet.getInt("emp_id");
			String empName = resultSet.getString("emp_name");
			double empSalary = resultSet.getDouble("emp_salary");
			int empAge = resultSet.getInt("emp_age");
			System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();
	}
}
