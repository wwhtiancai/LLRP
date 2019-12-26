package org.llrp.ltk.mongo;
/*
 *wuweihong
 *2017-5-9
 */
public class MongoThread {
	public static void main(String[] args) {
		for(int i =1;i<52;i++){
			new Thread(new MyThread(200000,i)).start();
		}
    }
}
