package org.llrp.ltk.generated;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.net.LLRPConnector;
import org.llrp.ltk.net.ObjectMessage;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.types.Bit;

/*
 *wuweihong
 *2018-4-26
 */
public class GetBusiMessage {
	private static final Logger log = Logger.getLogger(BusiManager.class);
	public static void main(String[] args) throws Exception {
		SystemCache cache = new SystemCache();
		ObjectMessage endpoint = new ObjectMessage();
		LLRPConnection connection = new LLRPConnector(endpoint, cache.ipAddress,cache.port);
//		String tx = cache.tx;
		((LLRPConnector) connection).connect();

			
	}
}
