package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.barBean;
import utils.DBUtil;
public class barDAO {
	public ArrayList<barBean> select_all()
    {
         Connection conn = null;
         Statement stmt=null;
         ResultSet rst = null;
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from bar";
			rst = stmt.executeQuery(sql);   
            ArrayList<barBean> array = new ArrayList<barBean>();
            while(rst.next())
            {
                barBean bar = new barBean();
                bar.setName(rst.getString("name"));
                bar.setNum(rst.getInt("num"));
                array.add(bar);
            }
            stmt.close();
            rst.close();
            return array; 
            
        }catch(SQLException e){
            System.out.println("Error occured at barDAO->select_all()");
            return new ArrayList<barBean>();
        }
    }
}
