package org.llrp.ltk.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.PropertyConfigurator;
import org.llrp.ltk.util.Constants;

/*
 *wuweihong
 *2016-4-29
 */
public class SystemCache {

	public static HashMap<String,String> fpdhMap = new HashMap<String, String>();
	public static HashMap<String,String> sfdmMap = new HashMap<String, String>();
	public static HashMap<String,String> hpzlMap = new HashMap<String, String>();
	public static HashMap<String,String> hphmMap = new HashMap<String, String>();
	public static HashMap<String,String> syxzMap = new HashMap<String, String>();
	public static HashMap<String,String> cllxMap = new HashMap<String, String>();
	public static HashMap<String,String> csysMap = new HashMap<String, String>();
	public static HashMap<String,String> sfxhMap = new HashMap<String, String>();
	/**连接LLRP的Ip*/  
    public static String ipAddress=null;  
    public static String tx=null;  
    public static int port=5084;  
    public static String channelname =null;  
    public static String socketip = null;  
    public static int socketport=8885;  
	 static  {
		PropertyConfigurator.configure(System.getProperty("user.dir") +"/log4j.properties");
//		 PropertyConfigurator.configure("etc/log4j.properties");
		if (fpdhMap.isEmpty()) {
			try {
				initfpdhMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (hphmMap.isEmpty()) {
			try {
				inithphmMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (sfdmMap.isEmpty()) {
			try {
				initsfdmMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (hpzlMap.isEmpty()) {
			try {
				inithpzlMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (syxzMap.isEmpty()) {
			try {
				initsyxzMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (cllxMap.isEmpty()) {
			try {
				initcllxMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (csysMap.isEmpty()) {
			try {
				initcsysMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (sfxhMap.isEmpty()) {
			try {
				initsfxhMap();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ipAddress == null){
			try {
				InputStream in = new FileInputStream(System.getProperty("user.dir") +"/Ipaddress.properties");
//				InputStream in = new FileInputStream("etc/Ipaddress.properties");
				Properties p = new Properties();  
	        	p.load(in); 
	        	ipAddress =p.getProperty("ipaddress");
	        	port =Integer.valueOf(p.getProperty("port"));
	        	socketip =p.getProperty("socketip");
	        	socketport =Integer.valueOf(p.getProperty("socketport"));
	        	channelname = p.getProperty("channelname");
	        	tx = p.getProperty("tx");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        
        	
    	}
		
	}
		/**
		 * 初始发牌代号
		 */
		private static void initfpdhMap() throws Exception{
			fpdhMap = initllrpCpdeMap("FPDH");
//				DbConnection db;
//				db = new DbConnection();
//				if(db.savetflag.equals(Constants.savetotxt)){
//					fpdhMap = initllrpCpdeMap("FPDH");
//				}else{
//					ResultSet resultSet = db.query("select * from frm_llrp_code where dmlb = 'FPDH'");
//					 while(resultSet.next()){  
//							 fpdhMap.put(resultSet.getString(2), resultSet.getString(3));
//					 }
//				}
		}
		/**
		 * 初始号牌号码
		 */
		private static void inithphmMap() throws Exception{
			hphmMap = initllrpCpdeMap("HPHM");
//				DbConnection db;
//				db = new DbConnection();
//				if(db.savetflag.equals(Constants.savetotxt)){
//					hphmMap = initllrpCpdeMap("HPHM");
//				}else{
//					ResultSet resultSet = db.query("select * from frm_llrp_code where dmlb = 'HPHM'");
//					 while(resultSet.next()){  
//						 hphmMap.put(resultSet.getString(2), resultSet.getString(3));
//					 }
//				}
		}
		
		/**
		 * 初始省份代码
		 */
		private static void initsfdmMap() throws Exception{
			sfdmMap = initllrpCpdeMap("SFDH");
//			DbConnection db;
//				db = new DbConnection();
//				if(db.savetflag.equals(Constants.savetotxt)){
//					sfdmMap = initllrpCpdeMap("SFDH");
//				}else{
//					ResultSet resultSet = db.query("select * from frm_llrp_code where dmlb = 'SFDH'");
//					while(resultSet.next()){  
//						 int columnCount = resultSet.getMetaData().getColumnCount();
//						 for(int i =0;i<columnCount;i++){
//							 sfdmMap.put(resultSet.getString(2), resultSet.getString(3));
//						 }
//					 }
//				}
				
		}
		
		/**
		 * 初始省份代码
		 */
		private static void inithpzlMap() throws Exception{
			hpzlMap = initllrpCpdeMap("HPZL");
//			DbConnection db;
//				db = new DbConnection();
//				if(db.savetflag.equals(Constants.savetotxt)){
//					hpzlMap = initllrpCpdeMap("HPZL");
//				}else{
//					ResultSet resultSet = db.query("select * from frm_llrp_code where dmlb = 'HPZL'");
//					while(resultSet.next()){  
//						 int columnCount = resultSet.getMetaData().getColumnCount();
//						 for(int i =0;i<columnCount;i++){
//							 hpzlMap.put(resultSet.getString(2), resultSet.getString(3));
//						 }
//					 }
//				}
		}
		/**
		 * 初始省份序号
		 */
		private static void initsfxhMap() throws Exception{
			sfxhMap = initllrpCpdeMap("SFDM");
//			DbConnection db;
//				db = new DbConnection();
//				if(db.savetflag.equals(Constants.savetotxt)){
//					hpzlMap = initllrpCpdeMap("HPZL");
//				}else{
//					ResultSet resultSet = db.query("select * from frm_llrp_code where dmlb = 'HPZL'");
//					while(resultSet.next()){  
//						 int columnCount = resultSet.getMetaData().getColumnCount();
//						 for(int i =0;i<columnCount;i++){
//							 hpzlMap.put(resultSet.getString(2), resultSet.getString(3));
//						 }
//					 }
//				}
		}
		/**
		 * 初始使用性质
		 */
		private static void initsyxzMap() throws Exception{
			syxzMap = initllrpCpdeMap("SYXZ");
//			DbConnection db;
//				db = new DbConnection();
//				if(db.savetflag.equals(Constants.savetotxt)){
//					syxzMap = initllrpCpdeMap("SYXZ");
//				}else{
//					ResultSet resultSet = db.query("select * from frm_llrp_code where dmlb = 'SYXZ'");
//					while(resultSet.next()){  
//						 int columnCount = resultSet.getMetaData().getColumnCount();
//						 for(int i =0;i<columnCount;i++){
//							 syxzMap.put(resultSet.getString(2), resultSet.getString(3));
//						 }
//					 }
//				}
		}
		
		/**
		 * 初始车辆类型
		 */
		private static void initcllxMap() throws Exception{
			cllxMap = initllrpCpdeMap("CLLX");
//			DbConnection db;
//				db = new DbConnection();
//				if(db.savetflag.equals(Constants.savetotxt)){
//					cllxMap = initllrpCpdeMap("CLLX");
//				}else{
//					ResultSet resultSet = db.query("select * from frm_llrp_code where dmlb = 'CLLX'");
//					while(resultSet.next()){  
//						 int columnCount = resultSet.getMetaData().getColumnCount();
//						 for(int i =0;i<columnCount;i++){
//							 cllxMap.put(resultSet.getString(2), resultSet.getString(3));
//						 }
//					 }
//				}
		}
		
		/**
		 * 初始车辆类型
		 */
		private static void initcsysMap() throws Exception{
			csysMap = initllrpCpdeMap("CSYS");
//			DbConnection db;
//				db = new DbConnection();
//				if(db.savetflag.equals(Constants.savetotxt)){
//					csysMap = initllrpCpdeMap("CSYS");
//				}else{
//					ResultSet resultSet = db.query("select * from frm_llrp_code where dmlb = 'CSYS'");
//					while(resultSet.next()){  
//						 int columnCount = resultSet.getMetaData().getColumnCount();
//						 for(int i =0;i<columnCount;i++){
//							 csysMap.put(resultSet.getString(2), resultSet.getString(3));
//						 }
//					 }
//				}
		}
		/**
		 * 初始化llrpcode
		 */
		private static  HashMap<String, String> initllrpCpdeMap(String dmz) throws Exception{
			HashMap<String, String> returnMap = new HashMap<String, String>();
//	        BufferedReader br =
//	            new BufferedReader(new InputStreamReader(new FileInputStream(new File("etc/llrpcode.txt")), "GBK"));
	        BufferedReader br =
	            new BufferedReader(new InputStreamReader(new FileInputStream(new File(System.getProperty("user.dir") +"/llrpcode.txt")), "GBK"));
	        String str;
	        while ((str = br.readLine()) != null)
	        {
	            String[] strs = str.split("\t");
	            if (strs.length != 3)
	            {
	                System.out.println("格式错误：" + str);
	            }
	 
	            try
	            {
	            	if(strs[0].trim().equalsIgnoreCase(dmz)){
	            		returnMap.put(strs[1], strs[2]);
	            	}
	            }
	            catch (NumberFormatException e)
	            {
	                e.printStackTrace();
	            }
		}
	        return returnMap;
	}

}
