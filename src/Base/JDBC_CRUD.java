package Base;

import org.junit.Test;

import java.sql.*;

/**
 * ClassName: JDBC_CRUD
 * Package: Base
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/4 下午7:19
 * @Version 1.0
 */
public class JDBC_CRUD {
	@Test
	public void testQuerySingleRowAndCol() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM t_emp;");

		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			int i = resultSet.getInt(1);
			System.out.println(i);
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();
	}

	@Test
	public void testQuerySingleRow() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu","root","yk848601");

		PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM t_emp WHERE emp_id = ?;");

		preparedStatement.setInt(1,4);
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

	@Test
	public void testQuery() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");

		PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM t_emp WHERE emp_age > ?;");

		preparedStatement.setInt(1,25);
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

	@Test
	public void testInsert() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");

		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES(?,?,?);");

		preparedStatement.setString(1,"AlanJake");
		preparedStatement.setDouble(2,7777.56);
		preparedStatement.setInt(3,21);

		int i = preparedStatement.executeUpdate();

		System.out.println(i);
		if(i > 0){
			System.out.println("成功插入数据");
		}else {
			System.out.println("插入数据失败");
		}

		preparedStatement.close();
		connection.close();
	}

	@Test
	public void testUpdate() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");

		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_emp SET emp_salary = ? WHERE emp_id = ?;");

		preparedStatement.setDouble(1,999.99);
		preparedStatement.setInt(2,6);

		int i = preparedStatement.executeUpdate();
		if(i > 0){
			System.out.println("更新成功");
		}else {
			System.out.println("更新成功");
		}

		preparedStatement.close();
		connection.close();
	}

	@Test
	public void testDelete() throws Exception{
		Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "yk848601");

		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_emp WHERE emp_id = ?;");

		preparedStatement.setInt(1,6);
		int i = preparedStatement.executeUpdate();

		if(i > 0){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}

		preparedStatement.close();
		connection.close();
	}
}
