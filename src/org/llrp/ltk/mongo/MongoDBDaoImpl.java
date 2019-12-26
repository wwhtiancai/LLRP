package org.llrp.ltk.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;

/*
 * mongodb���ݿ����ӳ�
 */
public class MongoDBDaoImpl implements MongoDBDao
{
    private MongoClient mongoClient = null;
    private static final MongoDBDaoImpl mongoDBDaoImpl = new MongoDBDaoImpl();// ����ʽ����ģʽ

    private MongoDBDaoImpl()
    {
        if (mongoClient == null)
        {
            MongoClientOptions.Builder buide = new MongoClientOptions.Builder();
            buide.connectionsPerHost(100);// ��Ŀ�����ݿ���Խ��������������
            buide.connectTimeout(1000 * 60 * 20);// �����ݿ⽨�����ӵĳ�ʱʱ��
            buide.maxWaitTime(100 * 60 * 5);// һ���̳߳ɹ���ȡ��һ���������ݿ�֮ǰ�����ȴ�ʱ��
            buide.threadsAllowedToBlockForConnectionMultiplier(100);
            buide.maxConnectionIdleTime(0);
            buide.maxConnectionLifeTime(0);
            buide.socketTimeout(0);
            buide.socketKeepAlive(true);
            MongoClientOptions myOptions = buide.build();
            mongoClient = new MongoClient(new ServerAddress("10.2.44.241", 27017), myOptions);
        }
    }

    public static MongoDBDaoImpl getMongoDBDaoImpl()
    {
        return mongoDBDaoImpl;
    }

    @Override
    public DB getDb(String dbName)
    {
        return mongoClient.getDB(dbName);
    }

    @Override
    public DBCollection getCollection(String dbName, String collectionName)
    {
        DB db = mongoClient.getDB(dbName);
        return db.getCollection(collectionName);
    }

    @Override
    public boolean inSert(String dbName, String collectionName, String keys, Object values)
    {
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        long num = dbCollection.count();
        BasicDBObject doc = new BasicDBObject();
        doc.put(keys, values);
        dbCollection.insert(doc);
        if (dbCollection.count() - num > 0)
        {
            System.out.println("������ݳɹ�������");
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String dbName, String collectionName, String keys, Object values)
    {
        WriteResult writeResult = null;
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        BasicDBObject doc = new BasicDBObject();
        doc.put(keys, values);
        writeResult = dbCollection.remove(doc);
        if (writeResult.getN() > 0)
        {
            System.out.println("ɾ�����ݳɹ�!!!!");
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<DBObject> find(String dbName, String collectionName, int num)
    {
        int count=num;
        ArrayList<DBObject> list = new ArrayList<DBObject>();
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        DBCursor dbCursor = dbCollection.find();
        if (num == -1)
        {
            while (dbCursor.hasNext())
            {
                list.add(dbCursor.next());
            }
        } else
        {
            while(dbCursor.hasNext())
            {
                if(count==0) break;
                list.add(dbCursor.next());
                count--;
            }
        }
        return list;
    }

    @Override
    public boolean update(String dbName, String collectionName, DBObject oldValue, DBObject newValue)
    {
        WriteResult writeResult = null;
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        writeResult = dbCollection.update(oldValue, newValue);
        if (writeResult.getN() > 0)
        {
            System.out.println("���ݸ��³ɹ�");
            return true;
        }
        return false;
    }

    @Override
    public boolean isExit(String dbName, String collectionName, String key, Object value)
    {
        DB db = mongoClient.getDB(dbName);
        DBCollection dbCollection = db.getCollection(collectionName);
        BasicDBObject doc = new BasicDBObject();
        doc.put(key, value);
        if (dbCollection.count(doc) > 0)
        {
            return true;
        }
        return false;
    }
    public static void main(String args[])
    {
    	
        MongoDBDaoImpl mongoDBDaoImpl=MongoDBDaoImpl.getMongoDBDaoImpl();
        ArrayList<DBObject> list=new ArrayList<DBObject>();
        list=mongoDBDaoImpl.find("efence", "received_byte_data",-1);
        System.out.println(list.size());
    }
}