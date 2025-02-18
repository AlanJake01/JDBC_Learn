package Senior.DAO;

import Senior.Util.JDBCUtil;
import Senior.Util.JDBCUtilV2;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseDAO
 * Package: Senior.DAO
 * Description:
 *
 * @Author AlanJake
 * @Create 2025/2/5 下午8:22
 * @Version 1.0
 */
public class BaseDAO {
	public int executeUpdate(String sql,Object...params) throws SQLException {
		Connection connection = JDBCUtilV2.getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		if(params != null&&params.length > 0){
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i+1,params[i]);
			}
		}
		int row = preparedStatement.executeUpdate();

		preparedStatement.close();
		if(connection.getAutoCommit()){
			JDBCUtilV2.Relase();
		}
		return row;
	}

	public <T> List<T> executeQuery(Class<T> clazz,String sql,Object...args) throws Exception {
		Connection connection = JDBCUtilV2.getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		if(args != null && args.length > 0){
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i+1,args[i]);
			}
		}
		ResultSet resultSet = preparedStatement.executeQuery();
		//获取结果集中的元数据对象
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		List<T> list = new ArrayList<>();

		while(resultSet.next()){
			T t = clazz.newInstance();
			for (int i = 1; i <= columnCount; i++) {
				//获取第i列的数据
				Object value = resultSet.getObject(i);

				String fieldName = metaData.getColumnLabel(i);
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(t,value);
			}
			list.add(t);
		}

		preparedStatement.close();
		connection.close();
		if(connection.getAutoCommit()){
			JDBCUtilV2.Relase();
		}

		return list;
	}

	public <T> T executeQueryBean(Class<T> clazz,String sql,Object...args) throws Exception {
		List<T> list = this.executeQuery(clazz, sql, args);
		if(list == null || list.size() <= 0){
			return null;
		}
		return list.get(0);
	}
}
