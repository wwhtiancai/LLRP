package org.llrp.ltk.mongo;



import java.util.ArrayList;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
* ������ MongoDBDao
* ���ߣ�lky
*/
public interface MongoDBDao {
    /**
     * 
     * ��������getDb
     * ���ߣ�lky
     * ��������ȡָ����mongodb���ݿ�
     * @param dbName
     * @return
     */
    public DB getDb(String dbName);
    /**
     * 
     * ��������getCollection
     * ���ߣ�lky
     * ��������ȡָ��mongodb���ݿ��collection����
     * @param dbName    ���ݿ���
     * @param collectionName    ���ݿ⼯����
     * @return
     */
    public DBCollection getCollection(String dbName, String collectionName);
    /**
     * 
     * ��������inSert
     * ���ߣ�lky
     * ��������ָ�������ݿ�����Ӹ�����keys����Ӧ��values
     * @param dbName
     * @param collectionName
     * @param keys
     * @param values
     * @return
     */
    public boolean inSert(String dbName, String collectionName, String keys, Object values);
    /**
     * 
     * ��������delete
     * ���ߣ�lky
     * ������ɾ�����ݿ�dbName�У�ָ��keys����Ӧvalues��ֵ
     * @param dbName
     * @param collectionName
     * @param keys
     * @param values
     * @return
     */
    public boolean delete(String dbName, String collectionName, String keys, Object values);
    /**
     * 
     * ��������find
     * ���ߣ�lky
     * �����������ݿ�dbName��ȡ����Ӧ��Ŀ������
     * @param dbName
     * @param collectionName
     * @param keys
     * @param values
     * @param num
     * @return
     */
    public ArrayList<DBObject> find(String dbName, String collectionName, int num);
    /**
     * 
     * ��������update
     * ���ߣ�lky
     * �������������ݿ�dbName����ָ����newValue����oldValue
     * @param dbName
     * @param collectionName
     * @param oldValue
     * @param newValue
     * @return
     */
    public boolean update(String dbName, String collectionName, DBObject oldValue, DBObject newValue);
    /**
     * 
     * ��������isExit
     * ���ߣ�lky
     * �������жϸ�����keys����Ӧ��values��ָ����dbName��collectionName�������Ƿ����
     * @param dbName
     * @param collectionName
     * @param keys
     * @param values
     * @return
     */
    public boolean isExit(String dbName, String collectionName, String key, Object value);
}