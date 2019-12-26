package org.llrp.ltk.generated;

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
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.jdom.JDOMException;
import org.llrp.ltk.ctrl.ReadCardCtrl;
import org.llrp.ltk.generated.enumerations.AirProtocolType;
import org.llrp.ltk.generated.enumerations.AntennaSpecStopTriggerType;
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
import org.llrp.ltk.generated.messages.GetDeviceConfig;
import org.llrp.ltk.generated.messages.GetDeviceConfigAck;
import org.llrp.ltk.generated.messages.StartSelectSpec;
import org.llrp.ltk.generated.messages.StartSelectSpecAck;
import org.llrp.ltk.generated.parameters.AntennaSpec;
import org.llrp.ltk.generated.parameters.AntennaSpecStopTrigger;
import org.llrp.ltk.generated.parameters.MemoryBank;
import org.llrp.ltk.generated.parameters.PeriodicTrigger;
import org.llrp.ltk.generated.parameters.RfSpec;
import org.llrp.ltk.generated.parameters.SecurityModuleConfiguration;
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
import org.llrp.ltk.net.TableProgramMessage;
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.BitList;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedByteArray;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;
import org.llrp.ltk.util.Util;

/*
 *wuweihong
 *2016-3-10
 */
public class test {

	private static final Logger log = Logger.getLogger(test.class);

	public static void main(String[] args) throws Exception {
		
		SystemCache cache = new SystemCache();
		log.error("输入“1” 开始盘点标签");
		log.error("输入“2” 删除规则");
		TableProgramMessage TableProgramMessage = new TableProgramMessage();
//		LLRPConnection connection = new LLRPConnector(TableProgramMessage, cache.ipAddress, cache.port);
		LLRPConnection connection = new LLRPConnector(TableProgramMessage, "10.86.20.50", cache.port);
		((LLRPConnector) connection).connect();
		StartSelectSpec startSelectSpec = new StartSelectSpec();
		startSelectSpec.setSelectSpecID(new UnsignedInteger(120));
		StartSelectSpecAck startSelectSpecAck = new StartSelectSpecAck();
		startSelectSpecAck = (StartSelectSpecAck) connection.transact(LLRPMessageFactory.createLLRPMessage(startSelectSpec.encodeBinary()));
		startSelectSpecAck.getStatus().getStatusCode();
		
		GetDeviceConfig getDeviceConfig = new GetDeviceConfig();
        GetDeviceConfigRequestedDataType getDeviceConfigRequestedDataType = new GetDeviceConfigRequestedDataType();
        getDeviceConfigRequestedDataType.set(GetDeviceConfigRequestedDataType.SecurityModuleConfiguration);
        getDeviceConfig.setRequestedData(getDeviceConfigRequestedDataType);
        getDeviceConfig.setAntennaID(new UnsignedByte(0));

        LLRPMessage message1 = null;
        try {
            message1 = LLRPMessageFactory.createLLRPMessage(getDeviceConfig.encodeBinary());
            GetDeviceConfigAck rep = new GetDeviceConfigAck();
            rep = (GetDeviceConfigAck) connection.transact(message1);
            if(0==rep.getStatus().getStatusCode().intValue())
            {
                SecurityModuleConfiguration securityModuleConfiguration = rep.getSecurityModuleConfiguration();
                if(securityModuleConfiguration!=null)
                {
                    //获取安全模块序号
                	UnsignedByteArray cc = securityModuleConfiguration.getGenaralConfigData().getSecurityModuleSN().getSerialNumber();
                	StringBuffer securityStr = new StringBuffer(); 
                	LLRPBitList bit4 = new LLRPBitList();
                	LLRPBitList bit5 = new LLRPBitList();
                	for(int i = 0;i<cc.size();i++){
                		switch(i){
                		case 0:
                			securityStr.append((char)cc.get(i).intValue());
                			break;
                		case 1:
                			securityStr.append((char)cc.get(i).intValue());
                			break;
                		case 2:
                			String Str3 = String.format("%02d",cc.get(i).intValue());
                			securityStr.append(Str3);
                			break;
                		case 3:
                			bit4.append(cc.get(i).encodeBinary());
                			break;
                		case 4:
                			bit4.append(cc.get(i).encodeBinary());
                			break;
                		case 5:
                			bit5.append(cc.get(i).encodeBinary());
                			break;
                		case 6:
                			bit5.append(cc.get(i).encodeBinary());
                			break;
                		case 7:
                			bit5.append(cc.get(i).encodeBinary());
                			break;
                		}
                	}
                	securityStr.append(String.format("%03d",new UnsignedInteger(bit4).toInteger())).
                	append(String.format("%05d",new UnsignedInteger(bit5).toInteger()));
                	System.out.println(securityStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

		
		
		
		
		
		
		
		
		
		
		
		
		
		/*DeleteSelectSpec del = new DeleteSelectSpec();
		del.setSelectSpecID(new UnsignedInteger(0));
		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(del.encodeBinary());
		DeleteSelectSpecAck rep = new DeleteSelectSpecAck();
		rep = (DeleteSelectSpecAck) connection.transact(message1);
		UnsignedInteger code = rep.getStatus().getStatusCode();
		if (code.intValue() == 0) {
			org.jdom.Document doc = new org.jdom.input.SAXBuilder().build(new FileReader(System.getProperty("user.dir") + "/test_ec.xml"));
			// File xmlFile=new File(System.getProperty("user.dir")
			// +"/ADD_ACCESSSPEC.xml");//根据指定的路径创建file对象
			// Document
			// document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
			LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(doc);
			AddSelectSpecAck repAdd = new AddSelectSpecAck();
			repAdd = (AddSelectSpecAck) connection.transact(addSelectSpecMsg);
			UnsignedInteger code1 = repAdd.getStatus().getStatusCode();
			// EnableSelectSpec
			EnableSelectSpec enableSelectSpec = new EnableSelectSpec();
			enableSelectSpec.setSelectSpecID(new UnsignedInteger(250));
			EnableSelectSpecAck enableSelectSpecAck = new EnableSelectSpecAck();
			enableSelectSpecAck = (EnableSelectSpecAck) connection.transact(enableSelectSpec);
			//		
			// //StartSelectSpec
			StartSelectSpec startSelectSpec = new StartSelectSpec();
			startSelectSpec.setSelectSpecID(new UnsignedInteger(250));
			StartSelectSpecAck startSelectSpecAck = new StartSelectSpecAck();
			startSelectSpecAck = (StartSelectSpecAck) connection.transact(LLRPMessageFactory.createLLRPMessage(startSelectSpec.encodeBinary()));
			startSelectSpecAck.getStatus().getStatusCode();
		}*/
	}
    static char changToNum(BigInteger temp)
    {
        int n = temp.intValue();

        if(n >= 0 && n <= 25)
            return (char) (n  + 'A');

        else if(n >= 25 && n <= 35)
            return (char)(n + 22);

        else
            return (char)(n + '0');
    }
}
