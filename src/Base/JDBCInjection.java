package Base;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ClassName: JDBCInjection
 * Package: Base
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/4 下午6:09
 * @Version 1.0
 */
public class JDBCInjection {
	public static void main(String[] args) throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu","root","yk848601");

		Statement statement = connection.createStatement();

		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();

		ResultSet resultSet = statement.executeQuery("SELECT* FROM t_emp where emp_name = '"+name+"';");
		while(resultSet.next()){
			int empId = resultSet.getInt("emp_id");
			String empName = resultSet.getString("emp_name");
			double empSalary = resultSet.getDouble("emp_salary");
			int empAge = resultSet.getInt("emp_age");
			System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
		}

		resultSet.close();
		statement.close();
		connection.close();
	}
}
