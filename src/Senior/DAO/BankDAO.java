package Senior.DAO;

/**
 * ClassName: BankDAO
 * Package: Senior.DAO.Impl
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/5 下午11:06
 * @Version 1.0
 */
public interface BankDAO {
	int addMoney(Integer id,Integer money);

	int subMoney(Integer id,Integer money);
}
