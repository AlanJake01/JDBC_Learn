package Senior.DAO.Impl;

import Senior.DAO.BankDAO;
import Senior.DAO.BaseDAO;

import java.sql.SQLException;

/**
 * ClassName: BankDAOImpl
 * Package: Senior.DAO.Impl
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/5 下午11:07
 * @Version 1.0
 */
public class BankDAOImpl extends BaseDAO implements BankDAO {
	@Override
	public int addMoney(Integer id, Integer money) {
		String sql = "update t_bank set money = money + ? where id = ?";
		try {
			return executeUpdate(sql,money,id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int subMoney(Integer id, Integer money) {
		String sql = "update t_bank set money = money - ? where id = ?";
		try {
			return executeUpdate(sql,money,id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
