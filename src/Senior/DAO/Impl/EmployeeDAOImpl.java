package Senior.DAO.Impl;

import Senior.DAO.BaseDAO;
import Senior.DAO.EmployeeDAO;
import Senior.Pojo.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: EmployeeDAOImpl
 * Package: Senior.DAO.Impl
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/5 下午8:12
 * @Version 1.0
 */
public class EmployeeDAOImpl extends BaseDAO implements EmployeeDAO {
	@Override
	public List<Employee> selectAll() throws Exception {
		String sql = "SELECT emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge FROM t_emp";
		return executeQuery(Employee.class,sql,null);
	}

	@Override
	public Employee selectByEmpId(Integer empId) {
		String sql = "SELECT emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge FROM t_emp where emp_id = ?";

		try {
			return executeQueryBean(Employee.class,sql,empId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(Employee employee) {
		String sql = "UPDATE t_emp SET emp_salary = ? WHERE emp_id = ?";
		try {
			return executeUpdate(sql,employee.getEmpSalary(),employee.getEmpId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insert(Employee employee) {
		String sql = "INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES (?,?,?)";

		try {
			return executeUpdate(sql,employee.getEmpName(),employee.getEmpSalary(),employee.getEmpAge());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int delete(Integer empId) {
		String sql = "delete from t_emp where emp_id = ?";
		try {
			return executeUpdate(sql,empId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
