package Senior;

import Senior.DAO.BankDAO;
import Senior.DAO.EmployeeDAO;
import Senior.DAO.Impl.BankDAOImpl;
import Senior.DAO.Impl.EmployeeDAOImpl;
import Senior.Pojo.Employee;
import Senior.Util.JDBCUtil;
import Senior.Util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: JDBCUtilTest
 * Package: Senior
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/5 下午7:40
 * @Version 1.0
 */
public class JDBCUtilTest {
	@Test
	public void test(){
		/*Connection connection1 = JDBCUtil.getConnection();
		Connection connection2 = JDBCUtil.getConnection();
		Connection connection3 = JDBCUtil.getConnection();

		System.out.println(connection1);
		System.out.println(connection2);
		System.out.println(connection3);*/

		Connection connection1 = JDBCUtilV2.getConnection();
		Connection connection2 = JDBCUtilV2.getConnection();
		Connection connection3 = JDBCUtilV2.getConnection();
		System.out.println(connection1);
		System.out.println(connection2);
		System.out.println(connection3);
	}
	
	@Test
	public void testEmployeeDAO(){
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();

		/*List<Employee> employees = null;
		try {
			employees = employeeDAO.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		for (Employee employee : employees) {
			System.out.println("employee = " + employee);
		}*/

		/*Employee employee = employeeDAO.selectByEmpId(1);
		System.out.println(employee);*/

		/*Employee employee = new Employee(null,"Tom",678.12,22);
		int insert = employeeDAO.insert(employee);
		System.out.println(insert);*/

		Employee employee = employeeDAO.selectByEmpId(20009);
//		System.out.println(employee);
		/*employee.setEmpSalary(461.78);
		int update = employeeDAO.update(employee);
		System.out.println(update);*/

		int delete = employeeDAO.delete(employee.getEmpId());
		System.out.println("delete = " + delete);
	}
	
	@Test
	public void testBankDAO(){
		BankDAO bankDAO = new BankDAOImpl();
		Connection connection = JDBCUtilV2.getConnection();

		try {
			connection.setAutoCommit(false);

			bankDAO.subMoney(1,100);

//			int s = 10/0;

			bankDAO.addMoney(2,100);
			connection.commit();
			System.out.println("事务提交");
		} catch (Exception e) {
			try {
				connection.rollback();
				System.out.println("事务回滚");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			JDBCUtilV2.Relase();
		}
	}
}
