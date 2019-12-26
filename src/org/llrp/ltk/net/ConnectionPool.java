package org.llrp.ltk.net;

import java.util.Hashtable;  
/** 
 * 
 * @author wuwh 
 * 
 */  
public class ConnectionPool  
{  
    private static final int CONNECTION_POOL_SIZE = 2;  //ȫ�ֳ���  
    private static final String API_SERVER_HOST = "10.86.20.54";  
    private static final int API_SERVER_PORT = 5084;  
    private static ConnectionPool self = null;  
    private Hashtable<Integer, LLRPConnector> connectorPool = null;    //���ӳ�  
    public Hashtable<Integer, LLRPConnector> getConnectorPool() {
		return connectorPool;
	}
	public void setConnectorPool(Hashtable<Integer, LLRPConnector> connectorPool) {
		this.connectorPool = connectorPool;
	}
	private boolean [] socketStatusArray = null; //���ӵ�״̬(true-��ռ��,false-����)  
    /** 
     * ��ʼ�����ӳأ����TCP���ӵ�����Ϊ10 
     */  
    public static synchronized void init() {  
        self = new ConnectionPool();  
        self.connectorPool = new Hashtable<Integer, LLRPConnector>();  
        self.socketStatusArray = new boolean [CONNECTION_POOL_SIZE];  
        //��ʼ�����ӳ�  
        System.out.println("��ʼ�����ӳ�.");  
        buildConnectionPool();  
    }  
    /** 
     * �������ӳ� 
     */  
    public synchronized static void buildConnectionPool() {  
        if(self==null)  
            init();  
        System.out.println("׼���������ӳ�.");  
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
            System.out.println("���ӳؽ���ʧ��!");  
            throw new RuntimeException(e);  //�׳�  
        }  
        System.out.println("���ӳؽ����ɹ�.");  
    }  
    /** 
     * �����ӳ��л�ȡһ�����е�connector 
     * @return ��ȡ��TCP���� 
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
            System.out.println("���ӳ��еĵ�"+i+"������");  
            return self.connectorPool.get(new Integer(i));  
        }  
        else {  
            System.out.println("�����ӳؽ�������ʧ�ܣ�û�п��е�����");  
            throw new RuntimeException("No enough pooled connection");  
        }  
    }  
    /** 
     * ����õ�connector������ʱ�����»��һ�����е�socket�� 
     * @param connector �����õ�connector 
     * @return �µõ���connector 
     */  
    public static LLRPConnector rebuildConnection(LLRPConnector connector) {  
        if(self==null)  
            init();  
        LLRPConnector newConnector = null;  
        try {  
            for(int i=0;i<CONNECTION_POOL_SIZE;i++) {  
                if(self.connectorPool.get(new Integer(i))==connector){  
                    System.out.println("�ؽ����ӳ��еĵ�"+i+"������");  
                    ObjectMessage endpoint = new ObjectMessage();
                    newConnector = new LLRPConnector(endpoint, API_SERVER_HOST,API_SERVER_PORT);
//                    newSocket = new Socket(API_SERVER_HOST,API_SERVER_PORT);  
                    self.connectorPool.put(new Integer(i), newConnector);  
                    self.socketStatusArray[i] = true;  
                }  
            }  
              
        } catch (Exception e) {  
            System.out.println("�ؽ�����ʧ��!");  
            throw new RuntimeException(e);  
        }  
        return newConnector;  
    }  
    /** 
     * �������connector�Żس��У�����Ϊ����״̬����ʱ���Ӳ�û�жϿ��� 
     * @param connector ʹ�����connector 
     */  
    public static void releaseConnection(LLRPConnector connector){  
        if(self==null){  
            init();  
        }  
        for(int i=0;i<CONNECTION_POOL_SIZE;i++){  
            if(self.connectorPool.get(new Integer(i))==connector){  
                self.socketStatusArray[i] = false;  
                System.out.println("�ͷ����� "+i);  
                break;  
            }  
        }  
    }  
    /** 
     * �Ͽ������������� 
     */  
    public synchronized static void releaseAllConnection() {  
        if(self==null)  
            init();  
          
        //�ر���������  
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
        System.out.println("ȫ�������Ѿ��رա�");  
    }  
    /** 
     * ���½������ӳء� 
     */  
    public static void reset() {  
        self = null;  
        System.out.println("�ؽ����ӳ�...");  
        init();  
    }  
}  