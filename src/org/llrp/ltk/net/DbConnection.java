package org.llrp.ltk.net;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.llrp.ltk.util.Constants;

/*
 *wuweihong
 *2016-4-29
 */
public class DbConnection {
	private static final Logger log = Logger.getLogger(DbConnection.class);
    /**定义一个Connection 用来连接数据库*/  
    private static Connection conn=null;  
    /**连接数据库的URL*/  
    private static String url=null;  
    /**指定数据的用户名和密码*/  
    private static String username=null;  
    private static String password=null;  
    public static String savetflag=null; //1 存数据库  2 打印 
    /**定义一个int记录更新的记录数量*/  
    int count=0;  
    /**定义一个结果集 用于放回查询结果*/  
    private ResultSet resultSet=null;  
    private PreparedStatement pstmt=null; 
	public DbConnection() throws IOException{
    	if(url == null ||username ==null||password == null){
    		InputStream in = new FileInputStream(System.getProperty("user.dir") +"/DBConfig.properties");     
//    		InputStream in = new FileInputStream("etc/DBConfig.properties");   
        	Properties p = new Properties();  
        	p.load(in); 
        	url =p.getProperty("url");
        	username = p.getProperty("user");
        	password = p.getProperty("password");
        	savetflag = p.getProperty("savetflag");
    	}
    	if(conn==null&&savetflag.equals(Constants.savetodb)){
    		 conn = connectionDB();  
    	}
    }  
    /** 
     * 建立数据的连接 
     * @exception SQLException, ClassNotFoundException 
     */  
    @SuppressWarnings("finally")  
    public Connection connectionDB(){  
        try{  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            conn=DriverManager.getConnection(url,username,password);  
            log.debug("连接数据库成功");  
        }catch(Exception e){  
            e.printStackTrace();  
            log.debug("建立数据库发生错误！");  
        }finally{  
            return conn;  
        }  
    }  
    /** 
     * 查询方法 
     * @param sql查询sql语句 
     * @return resultSet 
     */  
    @SuppressWarnings("finally")  
    public ResultSet query(String sql){  
        try {  
            pstmt = conn.prepareStatement(sql);  
            /**查询*/  
            resultSet = pstmt.executeQuery();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
            return resultSet;  
        }  
    }  
    /** 
     * 更新数据 
     * @param sql 更新sql语句 
     * @return 
     */  
    public int update(String sql){  
        try {  
            pstmt = conn.prepareStatement(sql);  
            count=pstmt.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
            log.debug("执行更新出错了");  
        }  
          
        return count;  
          
    }  
    /**关闭连接*/  
    public boolean coles(){  
        boolean isColes = false;  
        if(resultSet!=null){  
            try {  
                resultSet.close();  
                resultSet=null;  
                isColes=true;  
            } catch (SQLException e) {  
                isColes=false;  
                e.printStackTrace();  
                log.debug("关闭结果集发生错误");  
            }  
        }  
        if(pstmt!=null){  
            try {  
                pstmt.close();  
                pstmt=null;  
                isColes=true;  
            } catch (SQLException e) {  
                isColes=false;  
                e.printStackTrace();  
                log.debug("关闭pstmt发生异常");  
            }  
        }  
        if(conn!=null){  
            try{  
                conn.close();  
                conn=null;  
                isColes=true;  
            }catch (Exception e) {  
                isColes=false;  
                e.printStackTrace();  
                log.debug("关闭conn发生异常");  
            }  
        }  
        return isColes;  
    }  
    /** 
     * 测试查询的方法 
     * @throws SQLException 
     */  
    public void testQuery() throws SQLException{  
        resultSet =query("select * from RFID_LLRP_READ");  
        if(resultSet.next()){  
            System.out.println(resultSet.getString(1));  
            System.out.println(resultSet.getString(3));  
            System.out.println(resultSet.getString(2));  
        }  
    }  
    /** 
     *  
     * @param args 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws IOException 
     */  
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {  
    	DbConnection db = new DbConnection();  
        /**调用查询方法*/  
        db.testQuery();  
        /**调用更新方法*/  
        /**调用关闭连接方法*/  
        db.coles();  
    }  
    
}
