package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {
    /**
     * 获取数据库连接
     * @return Connection对象
     */
    public static Connection getConnection(){
    	String dbUserName = "chen";
    	String dbUserPasswd = "chen295";
    	String dbURL = "jdbc:mysql://47.112.206.61:3306/StudentInfoManagement";

    	Connection conn = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = (Connection) DriverManager.getConnection(dbURL,dbUserName,dbUserPasswd);
    	} catch (ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	} 
    	return conn;
    }
    
    /**
     * 关闭数据库连接
     * @param conn Connection对象
     */
    public static void closeConnection(Connection conn) {
		//判断conn是否为空
    	if(conn != null){
    		try {
				conn.close();//关闭数据库连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
	}
}
