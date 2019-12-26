package org.llrp.ltk.generated;

import org.llrp.ltk.net.ConnectionPool;
import org.llrp.ltk.net.LLRPConnectionAttemptFailedException;
import org.llrp.ltk.net.LLRPConnector;
import org.llrp.ltk.net.ObjectMessage;

/*
 *wuweihong
 *2018-4-25
 */
public class BusiTread extends Thread{
	public static void main(String[] args) throws LLRPConnectionAttemptFailedException {
		ConnectionPool pool = new ConnectionPool();
		pool.init();
		pool.buildConnection().connect(10000);
//		new Thread(new Runnable() {  
//			 
//	          @Override  
//	          public void run() { 
//	        	  while(true) { 
//	        		  ObjectMessage endpoint = new ObjectMessage();
//		      			LLRPConnector connection = new LLRPConnector(endpoint, "10.86.20.54",5084);
//	      		System.out.print("123");
//	      		try {
//	      			Thread.sleep(3000);
//	      			if(connection.isconnect()){
//	      				break;
//	      			}
//	      			connection.connect(10000);
//				} catch (LLRPConnectionAttemptFailedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	          }
//	          } }).start();
	}
}
