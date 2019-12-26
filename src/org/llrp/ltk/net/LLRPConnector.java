/*
 * Copyright 2007 ETH Zurich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

package org.llrp.ltk.net;

import org.apache.log4j.Logger;
import org.apache.mina.common.CloseFuture;
import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.SocketConnector;

import java.net.InetSocketAddress;

/**
 * LLRPConnector implements a self-initiated LLRP connection. 
 * 
 * Here is a simple code example:
 * <p>
 * <code> LLRPConnector c = new LLRPConnector(endpoint,ip); </code> <p>
 * <code> c.connect(); </code> <p>
 * <code> // send message asynchronously - response needs to handled via  </code> <p>
 * <code> // the message received method of the endpoint </code> <p>
 * <code> c.send(llrpmessage); </code> <p>
 * <code>  </code> <p>
 * <code> // send message synchronously </code> <p>
 * <code> LLRPMessage m = c.transact(llrpmessage); </code>
 * <p>
 * 
 * The connect method checks the status of the ConnectionAttemptStatus field in 
 * in the READER_EVENT_NOTIFICATION message. If the status field is not 'Success", 
 * an LLRPConnectionAttemptFailedException is thrown. 
 */

public class LLRPConnector extends LLRPConnection{
	private Logger log = Logger.getLogger(LLRPConnector.class);
	private String host;
	private int port = 5084;
	private org.apache.mina.transport.socket.nio.SocketConnector connector;
	private InetSocketAddress remoteAddress;
	

	public LLRPConnector() {
		super();
	}

	/** 
	 * LLRPConnector using parameters provided and LLRPIoAdapterHandlerImpl as default IoHandler
	 *
	 */
	
	public LLRPConnector(LLRPEndpoint endpoint, String host, int port) {
		super.endpoint = endpoint;
		this.host = host;
		this.port = port;

	}

	/** 
	 * LLRPConnector using parameters provided and LLRPIoAdapterHandlerImpl as default IoHandler
	 * and default port 5084
	 *
	 */
	
	public LLRPConnector(LLRPEndpoint endpoint, String host) {
		super.endpoint = endpoint;
		this.host = host;
	}
	
	/** 
	 * LLRPConnector using parameters provided and default port 5084
	 *
	 */
	
	public LLRPConnector(LLRPEndpoint endpoint, String host, LLRPIoHandlerAdapter handler) {
		super.endpoint = endpoint;
		super.handler = handler;
		this.host = host;
	} 
	
	/** 
	 * LLRPConnector using parameters provided
	 */
	
	public LLRPConnector(LLRPEndpoint endpoint, String host, int port, LLRPIoHandlerAdapter handler) {
		super.endpoint = endpoint;
		super.handler = handler;
		this.host = host;
		this.port = port;
	} 
	
	
	/**
	 * connects to a LLRP device at the host address and port specified. the connect method waits by default 
	 * 10 s for a response. If the READER_NOTIFICATION does not arrive or the ConnectionAttemptEventStatus 
	 * is not set to 'Success', a LLRPConnectionAttemptFailedException is thrown.
	 * 
	 * @throws LLRPConnectionAttemptFailedException
	 * 
	 */
	
	public void connect() throws LLRPConnectionAttemptFailedException{
		connect(CONNECT_TIMEOUT);
	}
	
	/**
	 * connects to a LLRP device at the host address and port specified. the connect method waits
	 * for the timeperiod specified (in ms) for a response. If the READER_NOTIFICATION does not arrive 
	 * or the ConnectionAttemptEventStatus 
	 * is not set to 'Success', a LLRPConnectionAttemptFailedException is thrown.
	 * 
	 * @param timeout time in ms
	 * @throws LLRPConnectionAttemptFailedException
	 */
	
	public void connect(long timeout) throws LLRPConnectionAttemptFailedException{
		
		
		
		
		new Thread(new Runnable() {  
			 
	          @Override  
	          public void run() { 
	        	  while(true) { 
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
	        		  try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        			connector = new SocketConnector();
	        			connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new LLRPProtocolCodecFactory(LLRPProtocolCodecFactory.BINARY_ENCODING)));
                      // MINA 2.0 method
	        			//connector.setHandler(handler);
                      remoteAddress = new InetSocketAddress(host, port);
	        			if(session!=null){
	        				if(session.isConnected()){
	        					break;
	        				}
	        			}
	        			ConnectFuture future = connector.connect(remoteAddress,handler);
	        			future.join();// Wait until the connection attempt is finished.
	        			if(future.isConnected()){
	        				session = future.getSession();
	        			}else{
	        				String msg = "failed to connect";
	        				try {
								throw new LLRPConnectionAttemptFailedException(msg);
							} catch (LLRPConnectionAttemptFailedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        			}
                      connector.addListener(new IoListener() {
	        	            @Override  
	        	            public void sessionDestroyed(IoSession arg0) {  
	        	                for (;;) {  
	        	                    try {  
	        	                        Thread.sleep(3000);  
	        	                        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new LLRPProtocolCodecFactory(LLRPProtocolCodecFactory.BINARY_ENCODING)));
	        	                        remoteAddress = new InetSocketAddress(host, port);
	        	                        ConnectFuture future = connector.connect(remoteAddress,handler);  
	        	                        future.join();
//	        	                        future.awaitUninterruptibly();// 等待连接创建成功  
	        	                        session = future.getSession();// 获取会话  
	        	                        if (session.isConnected()) {  
	        	                        	log.info("断线重连[" + remoteAddress + ":" + port + "]成功");  
	        	                            break;  
	        	                        }  
	        	                    } catch (Exception ex) {  
	        	                    	log.info("重连服务器登录失败,3秒再连接一次:" + ex.getMessage());  
	        	                    }  
	        	                }  
	        	            }
                            @Override
                            public void sessionCreated(IoSession arg0) {
                                log.info("-IoSession实例:" + session.toString());
                                // 设置IoSession闲置时间，参数单位是秒
                                session.setIdleTime(IdleStatus.BOTH_IDLE, 10);
//                                session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
                            }
                        });
	        			
	        			// MINA 2.0
	        			//future.awaitUninterruptibly();
	        			
	        			//check if llrp reader reply with a status report to indicate connection success.
	        			//the client shall not send any information to the reader until this status report message is received
	        			try {
							checkLLRPConnectionAttemptStatus(CONNECT_TIMEOUT);
						} catch (LLRPConnectionAttemptFailedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
	        		  
	          }
	          } }).start();
		
		
		
		
		
//		connector = new SocketConnector();
//        connector.addListener(new IoListener() {  
//            @Override  
//            public void sessionDestroyed(IoSession arg0) {  
//                for (;;) {  
//                    try {  
//                        Thread.sleep(3000);  
//                        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new LLRPProtocolCodecFactory(LLRPProtocolCodecFactory.BINARY_ENCODING)));
//                        remoteAddress = new InetSocketAddress(host, port);
//                        ConnectFuture future = connector.connect(remoteAddress,handler);  
////                        future.awaitUninterruptibly();// 等待连接创建成功  
//                        session = future.getSession();// 获取会话  
//                        if (session.isConnected()) {  
//                        	log.info("断线重连[" + remoteAddress + ":" + port + "]成功");  
//                            break;  
//                        }  
//                    } catch (Exception ex) {  
//                    	log.info("重连服务器登录失败,3秒再连接一次:" + ex.getMessage());  
//                    }  
//                }  
//            }  
//        });
//		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new LLRPProtocolCodecFactory(LLRPProtocolCodecFactory.BINARY_ENCODING)));
//		// MINA 2.0 method 
//		//connector.setHandler(handler);
//		remoteAddress = new InetSocketAddress(host, port);
//		ConnectFuture future = connector.connect(remoteAddress,handler);
//		future.join();// Wait until the connection attempt is finished.
//		if(future.isConnected()){
//			session = future.getSession();
//		}else{
//			String msg = "failed to connect";
//			throw new LLRPConnectionAttemptFailedException(msg);
//		}
//
//		
//		// MINA 2.0
//		//future.awaitUninterruptibly();
//		
//		//check if llrp reader reply with a status report to indicate connection success.
//		//the client shall not send any information to the reader until this status report message is received
//		checkLLRPConnectionAttemptStatus(timeout);
		
	}

	
	/**
	 * disconnect existing connection to LLRP device.
	 */
	
	public void disconnect(){
		//IoSession session = future.getSession();
		if (session != null && session.isConnected()){
			CloseFuture future = session.close();
			// MINA 2.0
			// future.awaitUninterruptibly();
		}
	}
	
	public void closed(){
		//IoSession session = future.getSession();
        if(session!=null) {
            session.close();
        }
			// MINA 2.0
			// future.awaitUninterruptibly();
	}
	
	public boolean isconnect() {
		if(session!=null) {
            return true;
        }else{
        	return false;
        }
	}
	/**
	 * reconnect to LLRP device using host, port and handler specified.
	 * 
	 * @return true if connection could be established 
	 * and ConnectionAttemptEvent Status was set to 'Success', set to false otherwise 
	 * 
	 */
	
	public boolean reconnect() {
		ConnectFuture future = connector.connect(remoteAddress,handler);
		future.join();		// Wait until the connection attempt is finished.
		// MINA 2.0
		// future = connector.connect();
		// future.awaitUninterruptibly();
		
		if(future.isConnected()){
			session = future.getSession();
			log.info("new session created:" + session);
		} else {
			return false;
		}
		
		//check if llrp reader reply with a status report to indicate connection success.
		//the client shall not send any information to the reader until this status report message is received
		
		try {
			checkLLRPConnectionAttemptStatus(CONNECT_TIMEOUT);
		} catch (LLRPConnectionAttemptFailedException e) {
			return false;
		}
	
		return true;
	}
	

	/**
	 * get host address of reader device.
	 * 
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * set host address of reader device.
	 * 
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * get port on which to connect to reader device.
	 * 
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * set port on which to connect to reader device.
	 * 
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	

}
