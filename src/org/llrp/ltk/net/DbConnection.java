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
    /**����һ��Connection �����������ݿ�*/  
    private static Connection conn=null;  
    /**�������ݿ��URL*/  
    private static String url=null;  
    /**ָ�����ݵ��û���������*/  
    private static String username=null;  
    private static String password=null;  
    public static String savetflag=null; //1 �����ݿ�  2 ��ӡ 
    /**����һ��int��¼���µļ�¼����*/  
    int count=0;  
    /**����һ������� ���ڷŻز�ѯ���*/  
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
     * �������ݵ����� 
     * @exception SQLException, ClassNotFoundException 
     */  
    @SuppressWarnings("finally")  
    public Connection connectionDB(){  
        try{  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            conn=DriverManager.getConnection(url,username,password);  
            log.debug("�������ݿ�ɹ�");  
        }catch(Exception e){  
            e.printStackTrace();  
            log.debug("�������ݿⷢ������");  
        }finally{  
            return conn;  
        }  
    }  
    /** 
     * ��ѯ���� 
     * @param sql��ѯsql��� 
     * @return resultSet 
     */  
    @SuppressWarnings("finally")  
    public ResultSet query(String sql){  
        try {  
            pstmt = conn.prepareStatement(sql);  
            /**��ѯ*/  
            resultSet = pstmt.executeQuery();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
            return resultSet;  
        }  
    }  
    /** 
     * �������� 
     * @param sql ����sql��� 
     * @return 
     */  
    public int update(String sql){  
        try {  
            pstmt = conn.prepareStatement(sql);  
            count=pstmt.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
            log.debug("ִ�и��³�����");  
        }  
          
        return count;  
          
    }  
    /**�ر�����*/  
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
                log.debug("�رս������������");  
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
                log.debug("�ر�pstmt�����쳣");  
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
                log.debug("�ر�conn�����쳣");  
            }  
        }  
        return isColes;  
    }  
    /** 
     * ���Բ�ѯ�ķ��� 
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
        /**���ò�ѯ����*/  
        db.testQuery();  
        /**���ø��·���*/  
        /**���ùر����ӷ���*/  
        db.coles();  
    }  
    
}
