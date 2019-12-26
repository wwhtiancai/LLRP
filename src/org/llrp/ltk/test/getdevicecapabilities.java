package org.llrp.ltk.test;

import java.io.FileReader;

import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.generated.messages.AddSelectSpecAck;
import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.net.LLRPConnector;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.net.TableProgramMessage;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.UnsignedInteger;

/*
 *wuweihong
 *2016-8-19
 */
public class getdevicecapabilities {
	public static void main(String[] args) throws Exception {
		SystemCache cache = new SystemCache();
	    TableProgramMessage TableProgramMessage = new TableProgramMessage();
		LLRPConnection connection = new LLRPConnector(TableProgramMessage, cache.ipAddress,cache.port);
		((LLRPConnector) connection).connect();
		org.jdom.Document doc = new org.jdom.input.SAXBuilder().build(new
                FileReader(System.getProperty("user.dir") +"/AddSelectSpec.xml"));
//		 File xmlFile=new File(System.getProperty("user.dir") +"/ADD_ACCESSSPEC.xml");//����ָ����·������file����  
//		 Document document=sax.read(xmlFile);//��ȡdocument����,����ĵ��޽ڵ㣬����׳�Exception��ǰ����  
		LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(doc);
		AddSelectSpecAck repAdd = new AddSelectSpecAck();
		repAdd = (AddSelectSpecAck) connection.transact(addSelectSpecMsg);
		UnsignedInteger code1= repAdd.getStatus().getStatusCode();
		System.out.println(code1);
	}
}
