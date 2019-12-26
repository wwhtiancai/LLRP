package org.llrp.ltk.redis;

import org.apache.log4j.Logger;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.types.LLRPMessage;

import redis.clients.jedis.Jedis;

/*
 *wuweihong
 *2016-8-15
 */
public class Publisher {
	private static final Logger log = Logger.getLogger(Publisher.class);
	 public void start(LLRPMessage message) {
	    Jedis jedis = new Jedis("127.0.0.1",6379);
	    try {
	      log.info("message to redis start..");
	      jedis.publish(SystemCache.channelname, message.toXMLString());
	    } catch (Exception e) {
	    	log.info("message to redis error :" + e.getMessage() );
	    }
	  }
}
