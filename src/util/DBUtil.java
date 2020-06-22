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
    	String user = "chen";
    	String password = "chen295";
    	String url = "jdbc:mysql://47.112.206.61:3306/StudentInfoManagement?useSSL=false";
    	String driver = "com.mysql.jdbc.Driver";
    	Connection con = null;
    	
    	try {
    		Class.forName(driver);
    		con = (Connection) DriverManager.getConnection(url,user,password);
    	} catch (ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	} 
    	
    	return con;
    }
    
    /**
     * 关闭数据库连接
     * @param con Connection对象
     */
    public static void closeConnection(Connection con) {
		//判断con是否为空
    	if(con != null){
    		try {
				con.close();//关闭数据库连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
	}
}
