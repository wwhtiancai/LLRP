package org.llrp.ltk.net;

import java.util.Hashtable;  
/** 
 * 
 * @author wuwh 
 * 
 */  
public class ConnectionPool  
{  
    private static final int CONNECTION_POOL_SIZE = 2;  //全局常量  
    private static final String API_SERVER_HOST = "10.86.20.54";  
    private static final int API_SERVER_PORT = 5084;  
    private static ConnectionPool self = null;  
    private Hashtable<Integer, LLRPConnector> connectorPool = null;    //连接池  
    public Hashtable<Integer, LLRPConnector> getConnectorPool() {
		return connectorPool;
	}
	public void setConnectorPool(Hashtable<Integer, LLRPConnector> connectorPool) {
		this.connectorPool = connectorPool;
	}
	private boolean [] socketStatusArray = null; //连接的状态(true-被占用,false-空闲)  
    /** 
     * 初始化连接池，最大TCP连接的数量为10 
     */  
    public static synchronized void init() {  
        self = new ConnectionPool();  
        self.connectorPool = new Hashtable<Integer, LLRPConnector>();  
        self.socketStatusArray = new boolean [CONNECTION_POOL_SIZE];  
        //初始化连接池  
        System.out.println("初始化连接池.");  
        buildConnectionPool();  
    }  
    /** 
     * 建立连接池 
     */  
    public synchronized static void buildConnectionPool() {  
        if(self==null)  
            init();  
        System.out.println("准备建立连接池.");  
        LLRPConnector newConnector = null;  
        try {  
            for(int i=0;i<CONNECTION_POOL_SIZE;i++){  
            	ObjectMessage endpoint = new ObjectMessage();
            	newConnector = new LLRPConnector(endpoint, API_SERVER_HOST,API_SERVER_PORT);
//                socket = new Socket(API_SERVER_HOST,API_SERVER_PORT);  
                self.connectorPool.put(new Integer(i), newConnector);  
                self.socketStatusArray[i] = false;  
                }  
        } catch (Exception e) {  
            System.out.println("连接池建立失败!");  
            throw new RuntimeException(e);  //抛出  
        }  
        System.out.println("连接池建立成功.");  
    }  
    /** 
     * 从连接池中获取一个空闲的connector 
     * @return 获取的TCP连接 
     */  
    public static LLRPConnector buildConnection(){  
        if(self==null)  
            init();  
        int i = 0;  
        for(i=0;i<CONNECTION_POOL_SIZE;i++){  
            if(!self.socketStatusArray[i]){  
                self.socketStatusArray[i] = true;  
                break;  
            }  
        }  
        if(i<=CONNECTION_POOL_SIZE) {  
            System.out.println("连接池中的第"+i+"个连接");  
            return self.connectorPool.get(new Integer(i));  
        }  
        else {  
            System.out.println("从连接池建立连接失败，没有空闲的连接");  
            throw new RuntimeException("No enough pooled connection");  
        }  
    }  
    /** 
     * 当获得的connector不可用时，重新获得一个空闲的socket。 
     * @param connector 不可用的connector 
     * @return 新得到的connector 
     */  
    public static LLRPConnector rebuildConnection(LLRPConnector connector) {  
        if(self==null)  
            init();  
        LLRPConnector newConnector = null;  
        try {  
            for(int i=0;i<CONNECTION_POOL_SIZE;i++) {  
                if(self.connectorPool.get(new Integer(i))==connector){  
                    System.out.println("重建连接池中的第"+i+"个连接");  
                    ObjectMessage endpoint = new ObjectMessage();
                    newConnector = new LLRPConnector(endpoint, API_SERVER_HOST,API_SERVER_PORT);
//                    newSocket = new Socket(API_SERVER_HOST,API_SERVER_PORT);  
                    self.connectorPool.put(new Integer(i), newConnector);  
                    self.socketStatusArray[i] = true;  
                }  
            }  
              
        } catch (Exception e) {  
            System.out.println("重建连接失败!");  
            throw new RuntimeException(e);  
        }  
        return newConnector;  
    }  
    /** 
     * 将用完的connector放回池中，调整为空闲状态。此时连接并没有断开。 
     * @param connector 使用完的connector 
     */  
    public static void releaseConnection(LLRPConnector connector){  
        if(self==null){  
            init();  
        }  
        for(int i=0;i<CONNECTION_POOL_SIZE;i++){  
            if(self.connectorPool.get(new Integer(i))==connector){  
                self.socketStatusArray[i] = false;  
                System.out.println("释放连接 "+i);  
                break;  
            }  
        }  
    }  
    /** 
     * 断开池中所有连接 
     */  
    public synchronized static void releaseAllConnection() {  
        if(self==null)  
            init();  
          
        //关闭所有连接  
        LLRPConnector connector = null;  
        for(int i=0;i<CONNECTION_POOL_SIZE;i++){  
        	connector = self.connectorPool.get(new Integer(i));  
            try {  
            	connector.disconnect();  
                self.socketStatusArray[i] = false;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println("全部连接已经关闭。");  
    }  
    /** 
     * 重新建立连接池。 
     */  
    public static void reset() {  
        self = null;  
        System.out.println("重建连接池...");  
        init();  
    }  
}  