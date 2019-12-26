package org.llrp.ltk.generated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.llrp.ltk.ctrl.ReadCardCtrl;
import org.llrp.ltk.generated.enumerations.AirProtocolType;
import org.llrp.ltk.generated.enumerations.AntennaSpecStopTriggerType;
import org.llrp.ltk.generated.enumerations.RfSpecSelectType;
import org.llrp.ltk.generated.enumerations.SelectSpecStartTriggerType;
import org.llrp.ltk.generated.enumerations.SelectSpecState;
import org.llrp.ltk.generated.enumerations.SelectSpecStopTriggerType;
import org.llrp.ltk.generated.interfaces.SpecParameter;
import org.llrp.ltk.generated.messages.AddSelectSpec;
import org.llrp.ltk.generated.messages.AddSelectSpecAck;
import org.llrp.ltk.generated.messages.DeleteSelectSpec;
import org.llrp.ltk.generated.messages.DeleteSelectSpecAck;
import org.llrp.ltk.generated.messages.EnableSelectSpec;
import org.llrp.ltk.generated.messages.EnableSelectSpecAck;
import org.llrp.ltk.generated.messages.StartSelectSpec;
import org.llrp.ltk.generated.messages.StartSelectSpecAck;
import org.llrp.ltk.generated.parameters.AntennaSpec;
import org.llrp.ltk.generated.parameters.AntennaSpecStopTrigger;
import org.llrp.ltk.generated.parameters.MemoryBank;
import org.llrp.ltk.generated.parameters.PeriodicTrigger;
import org.llrp.ltk.generated.parameters.RfSpec;
import org.llrp.ltk.generated.parameters.SelectSpec;
import org.llrp.ltk.generated.parameters.SelectSpecStartTrigger;
import org.llrp.ltk.generated.parameters.SelectSpecStopTrigger;
import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.net.LLRPConnectionAttemptFailedException;
import org.llrp.ltk.net.LLRPConnector;
import org.llrp.ltk.net.ObjectMessage;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.net.TableProgramMessage;
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedByteArray;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;


/*
 *wuweihong
 *2016-4-25
 */
public class TableTestProgram {
	private static final Logger log = Logger.getLogger(TableTestProgram.class);
	public static void main(String[] args) throws Exception {
		SystemCache cache = new SystemCache();
	    log.error("输入“1” 开始盘点标签");
	    log.error("输入“2” 删除规则");
	    TableProgramMessage TableProgramMessage = new TableProgramMessage();
		LLRPConnection connection = new LLRPConnector(TableProgramMessage, cache.ipAddress,cache.port);
		((LLRPConnector) connection).connect();
		boolean flag = true;
		boolean tidflag = true;
		String a=null;
		while (flag) {
		Scanner sc = new Scanner(System.in);
		a = sc.next();
		if(a.trim().equals("1")){
//			System.out.println("输入天线规则参数");
//			String josn = sc.next();
//			HashMap<String, String> data = toHashMap("");
			HashMap<String, String> data = new HashMap<String, String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
			ReadCardCtrl.readCardtest(connection,data);
			tidflag = true;
			String num = "";
			while(tidflag){
				ConcurrentHashMap<String,String> tidMap1 = TableProgramMessage.tidMap;
				 Set<Entry<String, String>> entries = tidMap1.entrySet();
				 for (Entry<String, String> entry : entries) { 
					 if(!num.equals(entry.getValue().split("##")[2])){
						 System.out.println(sdf.format(new Date())+"【Tid:"+entry.getKey()+"】   【号牌号码:"+entry.getValue().split("##")[0]+"】"+"【count值:"+entry.getValue().split("##")[3]+" 】"); 
					 }
					num = entry.getValue().split("##")[2];
				 }
			}
		}
//		if(a.trim().equals("1")){
//			System.out.println("输入天线规则参数");
//			String josn = sc.next();
//			HashMap<String, String> data = toHashMap(josn);
//			ReadCardCtrl.readCardstart(connection,data);
//		}
		if(a.trim().equals("2")){
			System.out.println("输入删除的规则参数");
			String input = sc.next();
			ReadCardCtrl.readCardstop(connection, input);
		}
//		if(a.trim().equals("3")){
//			System.out.println("输入enable的规则参数");
//			String input = sc.next();
//			ReadCardCtrl.enableSelectSpec(connection, input);
//		}
//		if(a.trim().equals("4")){
//			System.out.println("输入start的规则参数");
//			String input = sc.next();
//			ReadCardCtrl.startSelectSpec(connection, input);
//		}
		}
	}
	 private static HashMap<String, String> toHashMap(Object object)  
	   {  
	       HashMap<String, String> data = new HashMap<String, String>();  
	       // 将json字符串转换成jsonObject  
	       try {
	    	   object = TableTestProgram.readJosn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       JSONObject jsonObject = JSONObject.fromObject(object);  
	       Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           String value = (String) jsonObject.get(key);  
	           data.put(key, value);  
	       }  
	       return data;  
	   }  
	 
		private static  String readJosn() throws Exception{
			HashMap<String, String> returnMap = new HashMap<String, String>();
//	        BufferedReader br =
//	            new BufferedReader(new InputStreamReader(new FileInputStream(new File("etc/llrpcode.txt")), "GBK"));
	        BufferedReader br =
	            new BufferedReader(new InputStreamReader(new FileInputStream(new File(System.getProperty("user.dir") +"/测试json.txt")), "GBK"));
	        String str;
	        str = br.readLine();
//	        while ((str = br.readLine()) != null)
//	        {
//	            String[] strs = str.split("\t");
//	            if (strs.length != 3)
//	            {
//	                System.out.println("格式错误：" + str);
//	            }
//	 
//	            try
//	            {
//	            	if(strs[0].trim().equalsIgnoreCase(dmz)){
//	            		returnMap.put(strs[1], strs[2]);
//	            	}
//	            }
//	            catch (NumberFormatException e)
//	            {
//	                e.printStackTrace();
//	            }
//		}
	        return str;
	}
}
