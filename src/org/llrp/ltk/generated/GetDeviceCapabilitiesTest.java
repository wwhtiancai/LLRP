package org.llrp.ltk.generated;

import org.llrp.ltk.generated.enumerations.GetDeviceCapabilitiesRequestedDataType;
import org.llrp.ltk.generated.messages.GetDeviceCapabilities;
import org.llrp.ltk.generated.messages.GetDeviceCapabilitiesAck;
import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.net.LLRPConnector;
import org.llrp.ltk.net.ObjectMessage;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.UnsignedInteger;

/*
 *wuweihong
 *2016-3-10
 */
public class GetDeviceCapabilitiesTest {
	
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
		LLRPConnection connection = new LLRPConnector(endpoint, "192.168.1.251",5084);
		((LLRPConnector) connection).connect();
//		connection.send(message);
//		LLRPMessage response = connection.transact(message);
//		System.out.println(response.getName());
		GetDeviceCapabilities  getDeviceCapabilities  = new GetDeviceCapabilities ();
		GetDeviceCapabilitiesRequestedDataType requestedData = new GetDeviceCapabilitiesRequestedDataType();
		requestedData.set(1);
		getDeviceCapabilities.setRequestedData(requestedData);
		LLRPMessage message2 = LLRPMessageFactory.createLLRPMessage(getDeviceCapabilities.encodeBinary());
//		LLRPMessage response = connection.transact(message1);
		GetDeviceCapabilitiesAck  rep  = new GetDeviceCapabilitiesAck();
		rep = (GetDeviceCapabilitiesAck) connection.transact(message2);
        String sbxh = rep.getGenaralCapabilities().getDeviceSN().toString();
        String sbxhHex = binaryString2hexString(sbxh);
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
public static String binaryString2hexString(String bString)
{
    if (bString == null || bString.equals("") || bString.length() % 8 != 0)
        return null;
    StringBuffer tmp = new StringBuffer();
    int iTmp = 0;
    for (int i = 0; i < bString.length(); i += 4)
    {
        iTmp = 0;
        for (int j = 0; j < 4; j++)
        {
            iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
        }
        tmp.append(Integer.toHexString(iTmp));
    }
    return tmp.toString();
}
}
