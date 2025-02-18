package Senior.DAO;

import Senior.Pojo.Employee;

import java.util.List;

/**
 * ClassName: EmployeeDAO
 * Package: Senior.DAO
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/5 下午8:03
 * @Version 1.0
 */
public interface EmployeeDAO {

	/**
	 * 查询表中所有数据方法
	 * @param
	 * @return根据获得行数据返回对象集合
	 */
	List<Employee> selectAll() throws Exception;

	/**
	 * 根据职工id查询数据
	 * @param empId 职工id
	 * @return返回单个对象
	 */
	Employee selectByEmpId(Integer empId);

	/**
	 * 根据传入对象更新数据库
	 * @param employee 更新对象
	 * @return受影响行数
	 */
	int update(Employee employee);

	/**
	 * 根据传入对象插入数据
	 * @param employee 插入对象
	 * @return受影响行数
	 */
	int insert(Employee employee);

	/**
	 * 根据主键列删除对应数据
	 * @param empId 主键列
	 * @return受影响行数
	 */
	int delete(Integer empId);
}
