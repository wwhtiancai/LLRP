package org.llrp.ltk.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom.JDOMException;
import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.generated.enumerations.AirProtocolType;
import org.llrp.ltk.generated.enumerations.AntennaSpecStopTriggerType;
import org.llrp.ltk.generated.enumerations.GetDeviceCapabilitiesRequestedDataType;
import org.llrp.ltk.generated.enumerations.GetDeviceConfigRequestedDataType;
import org.llrp.ltk.generated.enumerations.RfSpecSelectType;
import org.llrp.ltk.generated.enumerations.SelectSpecStartTriggerType;
import org.llrp.ltk.generated.enumerations.SelectSpecState;
import org.llrp.ltk.generated.enumerations.SelectSpecStopTriggerType;
import org.llrp.ltk.generated.enumerations.VersionType;
import org.llrp.ltk.generated.interfaces.SpecParameter;
import org.llrp.ltk.generated.messages.ActiveVersion;
import org.llrp.ltk.generated.messages.AddAccessSpecAck;
import org.llrp.ltk.generated.messages.AddSelectSpec;
import org.llrp.ltk.generated.messages.AddSelectSpecAck;
import org.llrp.ltk.generated.messages.AlarmDelete;
import org.llrp.ltk.generated.messages.DeleteSelectSpec;
import org.llrp.ltk.generated.messages.DeleteSelectSpecAck;
import org.llrp.ltk.generated.messages.EnableSelectSpec;
import org.llrp.ltk.generated.messages.EnableSelectSpecAck;
import org.llrp.ltk.generated.messages.GetDeviceCapabilities;
import org.llrp.ltk.generated.messages.GetDeviceCapabilitiesAck;
import org.llrp.ltk.generated.messages.GetDeviceConfig;
import org.llrp.ltk.generated.messages.GetDeviceConfigAck;
import org.llrp.ltk.generated.messages.SetDeviceConfig;
import org.llrp.ltk.generated.messages.SetDeviceConfigAck;
import org.llrp.ltk.generated.messages.StartSelectSpec;
import org.llrp.ltk.generated.messages.StartSelectSpecAck;
import org.llrp.ltk.generated.parameters.AntennaSpec;
import org.llrp.ltk.generated.parameters.AntennaSpecStopTrigger;
import org.llrp.ltk.generated.parameters.Identification;
import org.llrp.ltk.generated.parameters.MemoryBank;
import org.llrp.ltk.generated.parameters.PeriodicTrigger;
import org.llrp.ltk.generated.parameters.RfSpec;
import org.llrp.ltk.generated.parameters.SelectSpec;
import org.llrp.ltk.generated.parameters.SelectSpecStartTrigger;
import org.llrp.ltk.generated.parameters.SelectSpecStopTrigger;
import org.llrp.ltk.generated.parameters.Status;
import org.llrp.ltk.generated.parameters.TagReportData;
import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.net.LLRPConnector;
import org.llrp.ltk.net.LLRPEndpoint;
import org.llrp.ltk.net.ObjectMessage;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.BitList;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.UTF8String_UTF_8;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedByteArray;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;
import org.llrp.ltk.util.Util;

/*
 *wuweihong
 *2016-3-10
 */
public class TestConfig {
	
//	private static final Logger log = Logger.getLogger(GetDeviceCapabilitiesTest.class);
	public static void main(String[] args) throws Exception {
//		PropertyConfigurator.configure("etc/log4j.properties");
//		 log.debug("Debug info.");
//	       log.info("Info info");
//	       log.warn("Warn info");
//	       log.error("Error info");
//	       log.fatal("Fatal info");
//		org.jdom.Document doc = new org.jdom.input.SAXBuilder().build(new
//                FileReader("F:/wuxi/dx101/dx101_a_out.xml"));
//		File file = new File("F:/wuxi/dx101/sendmessage.txt");	
////		LLRPBitList bits = Util.loadBinaryFileContent(file);
//		LLRPMessage message = LLRPMessageFactory.createLLRPMessage(bits);
//		LLRPMessage message = LLRPMessageFactory.createLLRPMessage(bits);
//		byte[] output = message.encodeBinary();
		ObjectMessage endpoint = new ObjectMessage();
//		LLRPConnection connection = new LLRPConnector(endpoint, "192.168.0.20");
		LLRPConnection connection = new LLRPConnector(endpoint, "192.168.1.108",5084);
		((LLRPConnector) connection).connect();
//		connection.send(message);
//		LLRPMessage response = connection.transact(message);
//		System.out.println(response.getName());
		SetDeviceConfig setDeviceConfig = new SetDeviceConfig();
		setDeviceConfig.setResetToFactoryDefault(new Bit(0));
		Identification Identification = new Identification();
		Identification.setDeviceName(new UTF8String_UTF_8("1232444") );
		setDeviceConfig.setIdentification(Identification );
//		GetDeviceCapabilities  getDeviceCapabilities  = new GetDeviceCapabilities ();
//		GetDeviceCapabilitiesRequestedDataType requestedData = new GetDeviceCapabilitiesRequestedDataType();
//		getDeviceCapabilities.setRequestedData(requestedData);
		LLRPMessage message2 = LLRPMessageFactory.createLLRPMessage(setDeviceConfig.encodeBinary());
//		LLRPMessage response = connection.transact(message1);
		SetDeviceConfigAck  rep  = new SetDeviceConfigAck();
		rep = (SetDeviceConfigAck) connection.transact(message2);
		System.out.println(rep.toXMLString());
		UnsignedInteger code1 = rep.getStatus().getStatusCode();
			
		}
//		System.out.println(response.getName());
//		Status status = new Status(bits);
//		del.setStatus(status);
//		connection.setEndpoint(endpoint);
////		Socket socket = null;
//        socket = new Socket();
//        socket.bind(new InetSocketAddress(0));
//        // 超时时间设为5秒
//        socket.setSoTimeout(5000);
//        socket.connect(new InetSocketAddress("10.2.44.121", 5084),5000);
//        // 发送前设置序列号
//        socket.getOutputStream().write(010101010123);
//        int sin = socket.getInputStream().read();
//        System.out.println(sin);
        
        
//        Socket socket =new Socket("10.2.44.121",5084);  
//        //2.得到socket读写流  
//        OutputStream os=socket.getOutputStream();  
//        PrintWriter pw=new PrintWriter(os);  
//        //输入流  
//        InputStream is=socket.getInputStream();  
//        BufferedReader br=new BufferedReader(new InputStreamReader(is));  
//        //3.利用流按照一定的操作，对socket进行读写操作  
//        String info="用户名：Tom,用户密码：123456";  
//        pw.write(info);  
//        pw.flush();  
//        socket.shutdownOutput();  
//        //接收服务器的相应  
//        String reply=null;  
//        while(!((reply=br.readLine())==null)){  
//            System.out.println("接收服务器的信息："+reply);  
//        }  
//        //4.关闭资源  
//        br.close();  
//        is.close();  
//        pw.close();  
//        os.close();  
//        socket.close();  
	
}
