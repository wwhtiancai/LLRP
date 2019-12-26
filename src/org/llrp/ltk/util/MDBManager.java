package org.llrp.ltk.util;



import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.llrp.ltk.net.LLRPIoHandlerAdapterImpl;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MDBManager {
	private static Logger log = Logger.getLogger(MDBManager.class);	
	private static final MDBManager instance=new MDBManager();  
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();   
      
    /** 
     * 此处可以不配置，采用默认也行 
     */  
    static{  
    	Properties p = new Properties();  
    	try {
    		InputStream in = new FileInputStream(System.getProperty("user.dir") +"/DBConfig.properties");
    		p.load(in);
			cpds.setDriverClass(p.getProperty("driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}   
		
    	cpds.setUser(p.getProperty("user"));
    	cpds.setPassword(p.getProperty("password"));
    	cpds.setJdbcUrl(p.getProperty("url"));
    	//当连接池中的连接用完时，C3P0一次性创建新连接的数目3
    	cpds.setAcquireIncrement(Integer.valueOf(p.getProperty("AcquireIncrement")));
    	cpds.setMinPoolSize(Integer.valueOf(p.getProperty("MinPoolSize")));
    	cpds.setMaxPoolSize(Integer.valueOf(p.getProperty("MaxPoolSize")));
//        cpds.setDataSourceName("mydatasource");  
//        
//        cpds.setJdbcUrl(ConstantUtils.getValue("c3p0.jdbcUrl").toString());  
//        try {  
//            cpds.setDriverClass(ConstantUtils.getValue("c3p0.driverClass").toString());  
//        } catch (PropertyVetoException e) {  
//            e.printStackTrace();  
//        }  
//        cpds.setUser(ConstantUtils.getValue("c3p0.user").toString());  
//        cpds.setPassword(ConstantUtils.getValue("c3p0.password").toString());  
//        cpds.setMaxPoolSize(Integer.valueOf(ConstantUtils.getValue("c3p0.maxPoolSize").toString()));  
//        cpds.setMinPoolSize(Integer.valueOf(ConstantUtils.getValue("c3p0.minPoolSize").toString()));  
//        cpds.setAcquireIncrement(Integer.valueOf(ConstantUtils.getValue("c3p0.acquireIncrement").toString()));  
//        cpds.setInitialPoolSize(Integer.valueOf(ConstantUtils.getValue("c3p0.initialPoolSize").toString()));  
//        cpds.setMaxIdleTime(Integer.valueOf(ConstantUtils.getValue("c3p0.maxIdleTime").toString()));  
    }  
      
    private MDBManager(){
    	
    }  
      
    public static MDBManager getInstance(){  
        return instance;  
    }  
      
    public static Connection  getConnection(){  
        try {  
            return cpds.getConnection();  
        } catch (SQLException e) {  
        	log.error(e.getMessage());
        }  
        return null;  
    }  
	public static void main(String[] args) throws Exception {
		
		Runnable r = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				Connection con ;
				Object o ="";
				try {
					for(int i= 0 ;i<1 ;i++){
//						synchronized(o) {
							con = MDBManager.getConnection();
							System.out.println(con = MDBManager.getConnection());
							PreparedStatement ps = con.prepareStatement("select * from rfid_llrp_read");
							ResultSet rs = ps.executeQuery();
							System.out.println(
									"5完成查询   ");
							ps.close();
							rs.close();
							con.close();
//						}												
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage());
				}
				
			}
			
		};
		
		
		for(int i= 0 ;i<1000 ;i++){
			System.out.println("并发线程 "+ i);
			Thread t = new Thread(r);
			t.start();
		}
		
		
	}
}