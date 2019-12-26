package org.llrp.ltk.mongo;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.Binary;
import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.util.MDBManager;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/*
 *wuweihong
 *2017-5-9
 */
public class MyThread extends Thread{
	private static final Logger log = Logger.getLogger(MyThread.class);
	 private int pageSize;
	 private int count = 0;
	 private int pageIndex;
	 private static MDBManager mdb = MDBManager.getInstance();
    public MyThread(int pageSize, int pageIndex){
    	 this.pageSize = pageSize;
         this.pageIndex = pageIndex;
    }
	  public void run() {  
	        try {  
	            //���ӵ�MongoDB���� �����Զ�����ӿ����滻��localhost��Ϊ����������IP��ַ  
	            //ServerAddress()���������ֱ�Ϊ ��������ַ �� �˿�  
	            ServerAddress serverAddress = new ServerAddress("10.2.44.241",27017);  
	            List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
	            addrs.add(serverAddress);  
	            //MongoCredential.createScramSha1Credential()���������ֱ�Ϊ �û��� ���ݿ����� ����  
	            MongoCredential credential = MongoCredential.createScramSha1Credential("efence", "efence", "efence".toCharArray());  
	            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	            credentials.add(credential);  
	              
	            //ͨ��������֤��ȡMongoDB����  
	            MongoClient mongoClient = new MongoClient(addrs,credentials);  
	              
	            //���ӵ����ݿ�  
	            MongoDatabase mongoDatabase = mongoClient.getDatabase("efence");  
	            System.out.println("Connect to database successfully");  
	            MongoCollection<Document> collection = mongoDatabase.getCollection("received_byte_data");
	            System.out.println("���� test ѡ��ɹ�");
	            
	            //���������ĵ�  
	            /** 
	            * 1. ��ȡ������FindIterable<Document> 
	            * 2. ��ȡ�α�MongoCursor<Document> 
	            * 3. ͨ���α�������������ĵ����� 
	            * */  
//	            BasicDBObject query = new BasicDBObject();
//	            query.put("_id", new ObjectId("584648e5b7031b1f30f0b312"));
	            Connection conn = mdb.getConnection();
	            Statement stmt = conn.createStatement();
	            int batchSize = 1000;
	            FindIterable<Document> findIterable = collection.find().skip((pageIndex - 1) * pageSize).limit(pageSize).sort(new BasicDBObject("_id", 1)).batchSize(500);  
	            MongoCursor<Document> mongoCursor = findIterable.iterator();  
	            while(mongoCursor.hasNext()){  
	            	Document dbObj= mongoCursor.next();
	            	Binary b  =  (Binary) dbObj.get("byteArray");
	            	log.error(dbObj.get("_id"));  
	            	if(b!=null){
	            		byte[] msg = b.getData();
	            		try{
	            			LLRPMessage message = LLRPMessageFactory.createLLRPMessage(msg);
	            			ArrayList<String> sqlList = MongoDB2Oracle.Msg2Oracle(message);
		                	for(int m = 0;m<sqlList.size();m++){
//		                		ps = conn.prepareStatement(sqlList.get(m));
//		                		ps.setString(1, "SEQ_RFID_LLRP_READ_UNION.NEXTVAL");
		                		stmt.addBatch(sqlList.get(m));
		                	}
		                	if(++count % batchSize == 0 ){
		                		System.out.println(count);
		                		stmt.executeBatch();
		                        conn.commit();
		                        count = 0;
		                    }
	            		}catch(Exception e){
	            			log.error("=====oracle �쳣");
	            			continue;
	            		}
	            	}
	               
	            }  
	            stmt.executeBatch();
	            conn.commit();
	            stmt.close();
	            conn.close();
	           log.error("=====over"+pageIndex);
	        } catch (Exception e) {  
	        	log.error( e.getClass().getName() + ": " + e.getMessage() );  
	        }  
	    }
	  
}
