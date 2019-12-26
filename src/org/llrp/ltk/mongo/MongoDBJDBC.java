package org.llrp.ltk.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.util.MDBManager;

public class MongoDBJDBC {  
	private static final Logger log = Logger.getLogger(MongoDBJDBC.class);
	 private static MDBManager mdb = MDBManager.getInstance();
    public static void main(String[] args){  
        try {  
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址  
            //ServerAddress()两个参数分别为 服务器地址 和 端口  
            ServerAddress serverAddress = new ServerAddress("10.2.44.241",27017);  
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
            addrs.add(serverAddress);  
              
            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
            MongoCredential credential = MongoCredential.createScramSha1Credential("efence", "efence", "efence".toCharArray());  
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
            credentials.add(credential);  
              
            //通过连接认证获取MongoDB连接  
            MongoClient mongoClient = new MongoClient(addrs,credentials);  
              
            //连接到数据库  
            MongoDatabase mongoDatabase = mongoClient.getDatabase("efence");  
            System.out.println("Connect to database successfully");  
            MongoCollection<Document> collection = mongoDatabase.getCollection("received_byte_data");
            System.out.println("集合 test 选择成功");
            
            //检索所有文档  
            /** 
            * 1. 获取迭代器FindIterable<Document> 
            * 2. 获取游标MongoCursor<Document> 
            * 3. 通过游标遍历检索出的文档集合 
            * */  
            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId("5863341878d7832d94e37414"));
            Connection conn = mdb.getConnection();
            Statement stmt = conn.createStatement();
//            PreparedStatement ps ;
            int batchSize = 10000;
            int count = 0;
//            FindIterable<Document> findIterable = collection.find().batchSize(2).sort(new BasicDBObject("_id", 1)); 
            FindIterable<Document> findIterable = collection.find().skip(0).limit(50005).batchSize(10000);  
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
//	                		ps = conn.prepareStatement(sqlList.get(m));
//	                		ps.setString(1, "SEQ_RFID_LLRP_READ_UNION.NEXTVAL");
	                		stmt.addBatch(sqlList.get(m));
	                	}
	                	if(++count % batchSize == 0 ){
	                		System.out.println(count);
	                		stmt.executeBatch();
	                        conn.commit();
	                        count = 0;
	                    }
            		}catch(Exception e){
            			log.error("=====oracle 异常");
            			continue;
            		}
            	}
               
            }  
            stmt.executeBatch();
            conn.commit();
            stmt.close();
            conn.close();
        } catch (Exception e) {  
        	log.error( e.getClass().getName() + ": " + e.getMessage() );  
        }  
    }  
} 