package daoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import dao.BaseDao;
import utils.DBUtil;

public class BaseDaoImpl implements BaseDao {

	@Override
	public List<Object> getList(Class type, String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getList(Class type, String sql, Object[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getList(Class type, String sql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getList(Connection conn, Class type, String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getList(Connection conn, Class type, String sql, Object[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getList(Connection conn, Class type, String sql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(Class type, String sql, Object[] param) {
		Connection con = DBUtil.getConnection();
		QueryRunner qr = new QueryRunner();
		Object obj = new LinkedList<>();
		try {
			obj = qr.query(con, sql, new BeanHandler(type), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Object getObject(Connection conn, Class type, String sql, Object[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String sql, Object[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String sql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String sql, Object[] param) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String sql, List<Object> param) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTransaction(Connection conn, String sql, Object[] param) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBatch(String sql, Object[][] param) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(String sql, Object[] param) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertTransaction(Connection conn, String sql, Object[] param) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int insertReturnKeys(String sql, Object[] param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReturnKeysTransaction(Connection conn, String sql, Object[] param) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertBatch(String sql, Object[][] param) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertBatchTransaction(Connection conn, String sql, Object[][] param) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String sql, Object[] param) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTransaction(Connection conn, String sql, Object[] param) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTransaction(Connection conn, String sql, List<Object> param) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBatchTransaction(Connection conn, String sql, Object[][] param) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getColumn(String sql, Object[] param) {
		// TODO Auto-generated method stub
		return null;
	}

}
