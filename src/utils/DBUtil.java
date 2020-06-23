package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Statement;

public class DBUtil {

	private static ComboPooledDataSource dataSource = null;

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		dataSource = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() {
		Connection conn = tl.get();
		try {
			if (conn == null) {
				conn = dataSource.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tl.set(conn);
		return conn;
	}

	/**
	 * 开始事务
	 * 
	 * @throws SQLException
	 */
	public static void startTransaction() {
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回滚事务
	 * 
	 * @throws SQLException
	 */
	public static void rollback() {
		Connection conn = getConnection();
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public static void commit() {
		Connection conn = getConnection();
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭Connection,并移除线程中的连接
	 * 
	 * @throws SQLException
	 */
	public static void closeConnection() {
		close(getConnection());
		tl.remove();
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stm) {
		try {
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps) {
		// TODO Auto-generated method stub
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
