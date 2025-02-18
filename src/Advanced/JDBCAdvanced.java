package Advanced;

import Advanced.Pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: JDBCAdvanced
 * Package: Advanced
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/4 下午8:12
 * @Version 1.0
 */
public class JDBCAdvanced {
	@Test
	public void testORM() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");

		PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM t_emp WHERE emp_id = ?;");

		preparedStatement.setInt(1,3);
		ResultSet resultSet = preparedStatement.executeQuery();

		Employee employee = null;
		if(resultSet.next()){
			employee = new Employee();
			employee.setEmpId(resultSet.getInt("emp_id"));
			employee.setEmpName(resultSet.getString("emp_name"));
			employee.setEmpSalary(resultSet.getDouble("emp_salary"));
			employee.setEmpAge(resultSet.getInt("emp_age"));
			System.out.println(employee);
		}

		resultSet.close();
		connection.close();
	}

	@Test
	public void testORMList() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM t_emp;");

		ResultSet resultSet = preparedStatement.executeQuery();

		Employee employee = null;
		ArrayList<Employee> employees = new ArrayList<>();

		while(resultSet.next()){
			employee = new Employee();
			employee.setEmpId(resultSet.getInt("emp_id"));
			employee.setEmpName(resultSet.getString("emp_name"));
			employee.setEmpSalary(resultSet.getDouble("emp_salary"));
			employee.setEmpAge(resultSet.getInt("emp_age"));
			employees.add(employee);
		}

		employees.forEach(System.out::println);
		resultSet.close();
		connection.close();
	}
	
	@Test
	public void testReturnPK() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");

		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES(?,?,?);",Statement.RETURN_GENERATED_KEYS);
		Employee employee = new Employee(null,"张三",567.22,32);
		preparedStatement.setString(1,employee.getEmpName());
		preparedStatement.setDouble(2,employee.getEmpSalary());
		preparedStatement.setInt(3,employee.getEmpAge());

		int i = preparedStatement.executeUpdate();
		ResultSet resultSet = null;
		if(i > 0){
			System.out.println("成功");
			resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()){
				employee.setEmpId(resultSet.getInt(1));
			}

			System.out.println(employee);
		}else {
			System.out.println("失败");
		}

		if(resultSet!=null){
			resultSet.close();
		}
		preparedStatement.close();
		connection.close();

	}
	
	@Test
	public void testMoreInsert() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");
		String sql = "INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES(?,?,?);";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			preparedStatement.setString(1,"marry"+i);
			preparedStatement.setDouble(2,123.45+i);
			preparedStatement.setInt(3,12+i);

			preparedStatement.executeUpdate();
		}
		long end = System.currentTimeMillis();

		System.out.println("消耗时间" + (end - start));
		preparedStatement.close();
		connection.close();
	}

	@Test
	public void testBatch() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?rewriteBatchedStatements=true", "root", "yk848601");
		String sql = "INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES(?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			preparedStatement.setString(1,"marry"+i);
			preparedStatement.setDouble(2,123.45+i);
			preparedStatement.setInt(3,12+i);

			preparedStatement.addBatch();
		}

		preparedStatement.executeBatch();
		long end = System.currentTimeMillis();

		System.out.println("消耗时间" + (end - start));
		preparedStatement.close();
		connection.close();
	}
}
