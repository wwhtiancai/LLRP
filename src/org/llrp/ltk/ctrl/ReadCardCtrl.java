package org.llrp.ltk.ctrl;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.generated.TableTestProgram;
import org.llrp.ltk.generated.enumerations.AirProtocolType;
import org.llrp.ltk.generated.enumerations.AntennaSpecStopTriggerType;
import org.llrp.ltk.generated.enumerations.CommLinkType;
import org.llrp.ltk.generated.enumerations.HbBankType;
import org.llrp.ltk.generated.enumerations.HbReadType;
import org.llrp.ltk.generated.enumerations.HbSpecMemoryBankIDType;
import org.llrp.ltk.generated.enumerations.IPAddressVersion;
import org.llrp.ltk.generated.enumerations.KeepaliveTriggerType;
import org.llrp.ltk.generated.enumerations.RfSpecSelectType;
import org.llrp.ltk.generated.enumerations.SelectReportTriggerType;
import org.llrp.ltk.generated.enumerations.SelectSpecStartTriggerType;
import org.llrp.ltk.generated.enumerations.SelectSpecState;
import org.llrp.ltk.generated.enumerations.SelectSpecStopTriggerType;
import org.llrp.ltk.generated.enumerations.TcpLinkCommMode;
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
import org.llrp.ltk.generated.parameters.ClientModeConfiguration;
import org.llrp.ltk.generated.parameters.CommLinkConfiguration;
import org.llrp.ltk.generated.parameters.HttpLinkConfiguration;
import org.llrp.ltk.generated.parameters.IPAddress;
import org.llrp.ltk.generated.parameters.KeepaliveSpec;
import org.llrp.ltk.generated.parameters.MemoryBank;
import org.llrp.ltk.generated.parameters.PeriodicTrigger;
import org.llrp.ltk.generated.parameters.ReportDestination;
import org.llrp.ltk.generated.parameters.RfSpec;
import org.llrp.ltk.generated.parameters.SelectReportSpec;
import org.llrp.ltk.generated.parameters.SelectSpec;
import org.llrp.ltk.generated.parameters.SelectSpecStartTrigger;
import org.llrp.ltk.generated.parameters.SelectSpecStopTrigger;
import org.llrp.ltk.generated.parameters.SerialLinkConfiguration;
import org.llrp.ltk.generated.parameters.ServerModeConfiguration;
import org.llrp.ltk.generated.parameters.TcpLinkConfiguration;
import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.UTF8String_UTF_8;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedByteArray;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedIntegerArray;
import org.llrp.ltk.types.UnsignedShort;

/*
 *wuweihong
 *2016-6-3
 */
public class ReadCardCtrl {
	private static final Logger log = Logger.getLogger(ReadCardCtrl.class);
	
	public static void readCardALL(LLRPConnection connection,HashMap<String, String> data) throws Exception{
		log.error("开始删除标签规则:"+data.get("SelectSpecID"));
//		DeleteSelectSpec del = new DeleteSelectSpec();
//		del.setSelectSpecID(new UnsignedInteger(0));
//		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(del.encodeBinary());
//		DeleteSelectSpecAck rep = new DeleteSelectSpecAck();
//		rep = (DeleteSelectSpecAck) connection.transact(message1);
//		UnsignedInteger code = rep.getStatus().getStatusCode();
//		if(code.intValue()==0){
//			log.error("删除标签规则:"+data.get("SelectSpecID")+"成功！");
//		}else{
//			log.error("删除标签规则:"+data.get("SelectSpecID")+"失败！");
//		}
			AddSelectSpec addSelectSpec = new AddSelectSpec();
			SelectSpec sl = new SelectSpec();
			sl.setSelectSpecID(new UnsignedInteger(Integer.valueOf(data.get("SelectSpecID"))));
			sl.setPriority(new UnsignedByte(Integer.valueOf(data.get("Priority"))));
			SelectSpecState selectSpecState = new SelectSpecState();
			selectSpecState.set(Integer.valueOf(data.get("CurrentState")));
			sl.setCurrentState(selectSpecState);
			sl.setPersistence(new Bit(Integer.valueOf(data.get("Persistence"))));
			
			SelectSpecStartTrigger selectSpecStartTrigger = new SelectSpecStartTrigger();
			SelectSpecStartTriggerType selectSpecStartTriggerType = new SelectSpecStartTriggerType();
			selectSpecStartTriggerType.set(Integer.valueOf(data.get("selectSpecStartTriggerType")));
			PeriodicTrigger periodicTrigger = new PeriodicTrigger();
			periodicTrigger.setOffset(new UnsignedInteger(Integer.valueOf(data.get("Offset"))));
			periodicTrigger.setPeriod(new UnsignedInteger(Integer.valueOf(data.get("Period"))));
			selectSpecStartTrigger.setSelectSpecStartTriggerType(selectSpecStartTriggerType);
			selectSpecStartTrigger.setPeriodicTrigger(periodicTrigger);
			SelectSpecStopTrigger selectSpecStopTrigger = new SelectSpecStopTrigger();
			selectSpecStopTrigger.setDurationValue(new UnsignedInteger(Integer.valueOf(data.get("DurationValue"))));
			SelectSpecStopTriggerType selectSpecStoptTriggerType = new SelectSpecStopTriggerType();
			selectSpecStoptTriggerType.set(Integer.valueOf(data.get("SelectSpecStopTriggerType")));
			selectSpecStopTrigger.setSelectSpecStopTriggerType(selectSpecStoptTriggerType);
			sl.setSelectSpecStartTrigger(selectSpecStartTrigger);
			sl.setSelectSpecStopTrigger(selectSpecStopTrigger);
			
			AntennaSpec antennaSpec = new AntennaSpec();
			AntennaSpecStopTrigger antennaSpecStopTrigger = new AntennaSpecStopTrigger();
			AntennaSpecStopTriggerType antennaSpecStopTriggerType = new AntennaSpecStopTriggerType();
			antennaSpecStopTriggerType.set(Integer.valueOf(data.get("AntennaSpecStopTriggerType")));
			antennaSpecStopTrigger.setDurationValue(new UnsignedInteger(Integer.valueOf(data.get("DurationValue"))));
			antennaSpecStopTrigger.setAntennaSpecStopTriggerType(antennaSpecStopTriggerType);
			antennaSpec.setAntennaSpecStopTrigger(antennaSpecStopTrigger);
			UnsignedByteArray cc = new UnsignedByteArray(Integer.valueOf(data.get("AntennaIDs").toString().split(",").length));
//			cc.add(new UnsignedByte(0));
			if(data.get("AntennaIDs").toString().split(",").length>1){
				for(int m = 0 ;m<data.get("AntennaIDs").toString().split(",").length;m++){
					cc.set(m,new UnsignedByte(Integer.valueOf(data.get("AntennaIDs").toString().split(",")[m])));
				}
			}else{
				cc.set(0,new UnsignedByte(Integer.valueOf(data.get("AntennaIDs"))));
			}
			antennaSpec.setAntennaIDs(cc);
			RfSpec rfSpec = new RfSpec();
			AirProtocolType protocolID = new AirProtocolType();
			protocolID.set(Integer.valueOf(data.get("ProtocolID")));
			rfSpec.setProtocolID(protocolID);
			
			RfSpecSelectType selectType = new RfSpecSelectType();
//			selectType.set("Inventory");Read
			selectType.set(Integer.valueOf(data.get("SelectType")));
			rfSpec.setSelectType(selectType);
			rfSpec.setRfSpecID(new UnsignedShort(Integer.valueOf(data.get("RfSpecID"))));
			
			//MemoryBank
			MemoryBank memoryBank = new MemoryBank();
			memoryBank.setCount(new UnsignedShort(Integer.valueOf(data.get("Count"))));
			memoryBank.setPointer(new UnsignedShort(Integer.valueOf(data.get("Pointer"))));
			HbSpecMemoryBankIDType hbSpecMemoryBankIDType = new HbSpecMemoryBankIDType();
			hbSpecMemoryBankIDType.set(Integer.valueOf(data.get("MemoryBankID")));
			memoryBank.setMemoryBankID(hbSpecMemoryBankIDType);
			HbBankType bankType = new HbBankType();
			HbReadType readType = new HbReadType();
			readType.set(Integer.valueOf(data.get("ReadType")));
			bankType.set(Integer.valueOf(data.get("BankType")));
			memoryBank.setBankType(bankType);
			memoryBank.setReadType(readType);
			rfSpec.setMemoryBank(memoryBank);
			
			List<RfSpec> rfSpecList = new ArrayList<RfSpec>() ; 
			rfSpecList.add(rfSpec);
			antennaSpec.setRfSpecList(rfSpecList);
			
			List<SpecParameter> specParameterList  = new ArrayList<SpecParameter>();
			specParameterList.add(antennaSpec);
			sl.setSpecParameterList(specParameterList);
			addSelectSpec.setSelectSpec(sl);
//			sl.setSelectReportSpec(selectReportSpec);
			LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(addSelectSpec.encodeBinary());
			AddSelectSpecAck repAdd = new AddSelectSpecAck();
			repAdd = (AddSelectSpecAck) connection.transact(addSelectSpecMsg);
			UnsignedInteger code1= repAdd.getStatus().getStatusCode();
			if(code1.intValue()==0){
				log.error("添加规则成功");
			}else{
				log.error("添加规则失败");
			}
			
			log.error("EnableSelectSpec:"+data.get("SelectSpecID"));
			//EnableSelectSpec
			EnableSelectSpec enableSelectSpec = new EnableSelectSpec();
			enableSelectSpec.setSelectSpecID(new UnsignedInteger(Integer.valueOf(data.get("SelectSpecID"))));
			EnableSelectSpecAck enableSelectSpecAck = new EnableSelectSpecAck();
			enableSelectSpecAck = (EnableSelectSpecAck) connection.transact(enableSelectSpec);
			UnsignedInteger enableSelectSpecAckCode = enableSelectSpecAck.getStatus().getStatusCode();
			if(enableSelectSpecAckCode.intValue()==0){
				log.error("EnableSelectSpec "+data.get("SelectSpecID")+"添加成功");
			}else{
				log.error("EnableSelectSpec "+data.get("SelectSpecID")+" 添加失败");
			}
			
			log.error("startSelectSpec:"+data.get("SelectSpecID"));
			//StartSelectSpec
			StartSelectSpec startSelectSpec = new StartSelectSpec();
			startSelectSpec.setSelectSpecID(new UnsignedInteger(Integer.valueOf(data.get("SelectSpecID"))));
			StartSelectSpecAck startSelectSpecAck = new StartSelectSpecAck();
			startSelectSpecAck = (StartSelectSpecAck)connection.transact(startSelectSpec);
			UnsignedInteger startSelectSpecAckCode = startSelectSpecAck.getStatus().getStatusCode();
			if(startSelectSpecAckCode.intValue()==0){
				log.error("StartSelectSpec "+data.get("SelectSpecID")+"添加成功");
			}else{
				log.error("StartSelectSpec"+data.get("SelectSpecID")+" 添加失败");
			}
			
	}
	public static void readCardstart(LLRPConnection connection,HashMap<String, String> data) throws Exception{
		log.error("开始删除标签规则:"+data.get("SelectSpecID"));
//		DeleteSelectSpec del = new DeleteSelectSpec();
//		del.setSelectSpecID(new UnsignedInteger(0));
//		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(del.encodeBinary());
//		DeleteSelectSpecAck rep = new DeleteSelectSpecAck();
//		rep = (DeleteSelectSpecAck) connection.transact(message1);
//		UnsignedInteger code = rep.getStatus().getStatusCode();
//		if(code.intValue()==0){
//			log.error("删除标签规则:"+data.get("SelectSpecID")+"成功！");
//		}else{
//			log.error("删除标签规则:"+data.get("SelectSpecID")+"失败！");
//		}
			AddSelectSpec addSelectSpec = new AddSelectSpec();
			SelectSpec sl = new SelectSpec();
			sl.setSelectSpecID(new UnsignedInteger(Integer.valueOf(data.get("SelectSpecID"))));
			sl.setPriority(new UnsignedByte(Integer.valueOf(data.get("Priority"))));
			SelectSpecState selectSpecState = new SelectSpecState();
			selectSpecState.set(Integer.valueOf(data.get("CurrentState")));
			sl.setCurrentState(selectSpecState);
			sl.setPersistence(new Bit(Integer.valueOf(data.get("Persistence"))));
			
			SelectSpecStartTrigger selectSpecStartTrigger = new SelectSpecStartTrigger();
			SelectSpecStartTriggerType selectSpecStartTriggerType = new SelectSpecStartTriggerType();
			selectSpecStartTriggerType.set(Integer.valueOf(data.get("selectSpecStartTriggerType")));
			PeriodicTrigger periodicTrigger = new PeriodicTrigger();
			periodicTrigger.setOffset(new UnsignedInteger(Integer.valueOf(data.get("Offset"))));
			periodicTrigger.setPeriod(new UnsignedInteger(Integer.valueOf(data.get("Period"))));
			selectSpecStartTrigger.setSelectSpecStartTriggerType(selectSpecStartTriggerType);
			selectSpecStartTrigger.setPeriodicTrigger(periodicTrigger);
			SelectSpecStopTrigger selectSpecStopTrigger = new SelectSpecStopTrigger();
			selectSpecStopTrigger.setDurationValue(new UnsignedInteger(Integer.valueOf(data.get("DurationValue"))));
			SelectSpecStopTriggerType selectSpecStoptTriggerType = new SelectSpecStopTriggerType();
			selectSpecStoptTriggerType.set(Integer.valueOf(data.get("SelectSpecStopTriggerType")));
			selectSpecStopTrigger.setSelectSpecStopTriggerType(selectSpecStoptTriggerType);
			sl.setSelectSpecStartTrigger(selectSpecStartTrigger);
			sl.setSelectSpecStopTrigger(selectSpecStopTrigger);
			
			AntennaSpec antennaSpec = new AntennaSpec();
			AntennaSpecStopTrigger antennaSpecStopTrigger = new AntennaSpecStopTrigger();
			AntennaSpecStopTriggerType antennaSpecStopTriggerType = new AntennaSpecStopTriggerType();
			antennaSpecStopTriggerType.set(Integer.valueOf(data.get("AntennaSpecStopTriggerType")));
			antennaSpecStopTrigger.setDurationValue(new UnsignedInteger(Integer.valueOf(data.get("DurationValue"))));
			antennaSpecStopTrigger.setAntennaSpecStopTriggerType(antennaSpecStopTriggerType);
			antennaSpec.setAntennaSpecStopTrigger(antennaSpecStopTrigger);
			UnsignedByteArray cc = new UnsignedByteArray(1);
//			cc.add(new UnsignedByte(0));
			cc.set(0,new UnsignedByte(Integer.valueOf(data.get("AntennaIDs"))));
			antennaSpec.setAntennaIDs(cc);
			RfSpec rfSpec = new RfSpec();
			AirProtocolType protocolID = new AirProtocolType();
			protocolID.set(Integer.valueOf(data.get("AntennaIDs")));
			rfSpec.setProtocolID(protocolID);
			
			RfSpecSelectType selectType = new RfSpecSelectType();
//			selectType.set("Inventory");Read
			selectType.set(Integer.valueOf(data.get("SelectType")));
			rfSpec.setSelectType(selectType);
			rfSpec.setRfSpecID(new UnsignedShort(Integer.valueOf(data.get("RfSpecID"))));
			
			//MemoryBank
			MemoryBank memoryBank = new MemoryBank();
			memoryBank.setCount(new UnsignedShort(Integer.valueOf(data.get("Count"))));
			memoryBank.setPointer(new UnsignedShort(Integer.valueOf(data.get("Pointer"))));
			HbSpecMemoryBankIDType hbSpecMemoryBankIDType = new HbSpecMemoryBankIDType();
			hbSpecMemoryBankIDType.set(Integer.valueOf(data.get("MemoryBankID")));
			memoryBank.setMemoryBankID(hbSpecMemoryBankIDType);
			HbBankType bankType = new HbBankType();
			HbReadType readType = new HbReadType();
			readType.set(Integer.valueOf(data.get("ReadType")));
			bankType.set(Integer.valueOf(data.get("BankType")));
			memoryBank.setBankType(bankType);
			memoryBank.setReadType(readType);
			rfSpec.setMemoryBank(memoryBank);
			
			List<RfSpec> rfSpecList = new ArrayList<RfSpec>() ; 
			rfSpecList.add(rfSpec);
			antennaSpec.setRfSpecList(rfSpecList);
			
			List<SpecParameter> specParameterList  = new ArrayList<SpecParameter>();
			specParameterList.add(antennaSpec);
			sl.setSpecParameterList(specParameterList);
			addSelectSpec.setSelectSpec(sl);
//			sl.setSelectReportSpec(selectReportSpec);
			LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(addSelectSpec.encodeBinary());
			AddSelectSpecAck repAdd = new AddSelectSpecAck();
			repAdd = (AddSelectSpecAck) connection.transact(addSelectSpecMsg);
			UnsignedInteger code1= repAdd.getStatus().getStatusCode();
			if(code1.intValue()==0){
				log.error("添加规则成功");
			}else{
				log.error("添加规则失败");
			}
	}
	
	public static void readCardstop(LLRPConnection connection,String input) throws Exception{
		log.error("开始删除所有标签规则:"+input);
		DeleteSelectSpec del = new DeleteSelectSpec();
		del.setSelectSpecID(new UnsignedInteger(Integer.valueOf(input)));
		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(del.encodeBinary());
		DeleteSelectSpecAck rep = new DeleteSelectSpecAck();
		rep = (DeleteSelectSpecAck) connection.transact(message1);
		UnsignedInteger code = rep.getStatus().getStatusCode();
		if(code.intValue()==0){
			log.error("删除标签规则"+input+"成功");
		}else{
			log.error("删除标签规则"+input+"失败");
		}
		}
	
	public static void enableSelectSpec(LLRPConnection connection,String input) throws Exception{
		log.error("EnableSelectSpec:"+input);
		//EnableSelectSpec
		EnableSelectSpec enableSelectSpec = new EnableSelectSpec();
		enableSelectSpec.setSelectSpecID(new UnsignedInteger(Integer.valueOf(input)));
		EnableSelectSpecAck enableSelectSpecAck = new EnableSelectSpecAck();
		enableSelectSpecAck = (EnableSelectSpecAck) connection.transact(enableSelectSpec);
		UnsignedInteger enableSelectSpecAckCode = enableSelectSpecAck.getStatus().getStatusCode();
		if(enableSelectSpecAckCode.intValue()==0){
			log.error("EnableSelectSpec "+input+"添加成功");
		}else{
			log.error("EnableSelectSpec "+input+" 添加失败");
		}
		}
	
	public static void startSelectSpec(LLRPConnection connection,String input) throws Exception{
		log.error("startSelectSpec:"+input);
		//StartSelectSpec
		StartSelectSpec startSelectSpec = new StartSelectSpec();
		startSelectSpec.setSelectSpecID(new UnsignedInteger(Integer.valueOf(input)));
		StartSelectSpecAck startSelectSpecAck = new StartSelectSpecAck();
		startSelectSpecAck = (StartSelectSpecAck)connection.transact(startSelectSpec);
		UnsignedInteger startSelectSpecAckCode = startSelectSpecAck.getStatus().getStatusCode();
		if(startSelectSpecAckCode.intValue()==0){
			log.error("StartSelectSpec "+input+"添加成功");
		}else{
			log.error("StartSelectSpec"+input+" 添加失败");
		}
		}
	
	public static void SelectReportSpec(LLRPConnection connection,HashMap<String, String> data) throws Exception{
		SelectReportSpec selectReportSpec = new SelectReportSpec();
		SelectReportTriggerType selectReportTrigger = new SelectReportTriggerType();
		selectReportTrigger.set(Integer.valueOf(data.get("selectReportTrigger")));
		selectReportSpec.setSelectReportTrigger(selectReportTrigger);
		selectReportSpec.setNValue(new UnsignedShort(Integer.valueOf(data.get("NValue"))));
		selectReportSpec.setEnableSelectSpecID(new Bit(Integer.valueOf(data.get("EnableSelectSpecID"))));
		selectReportSpec.setEnableSpecIndex(new Bit(Integer.valueOf(data.get("EnableSpecIndex"))));
		selectReportSpec.setEnableRfSpecID(new Bit(Integer.valueOf(data.get("EnableRfSpecID"))));
		selectReportSpec.setEnableAntennaID(new Bit(Integer.valueOf(data.get("EnableAntennaID"))));
		selectReportSpec.setEnablePeakRSSI(new Bit(Integer.valueOf(data.get("EnablePeakRSSI"))));
		selectReportSpec.setEnableFirstSeenTimestamp(new Bit(Integer.valueOf(data.get("EnableFirstSeenTimestamp"))));
		selectReportSpec.setEnableLastSeenTimestamp(new Bit(Integer.valueOf(data.get("EnableLastSeenTimestamp"))));
		selectReportSpec.setEnableTagSeenCount(new Bit(Integer.valueOf(data.get("EnableTagSeenCount"))));
		selectReportSpec.setEnableAccessSpecID(new Bit(Integer.valueOf(data.get("EnableAccessSpecID"))));
		ReportDestination  reportDestination = new ReportDestination();
		List<CommLinkConfiguration> commLinkConfigurationList = new LinkedList<CommLinkConfiguration>();
		CommLinkConfiguration commLinkConfiguration = new CommLinkConfiguration();
		CommLinkType linkType = new CommLinkType();
		linkType.set(Integer.valueOf(data.get("LinkType")));
		commLinkConfiguration.setLinkType(linkType);
		KeepaliveSpec keepaliveSpec = new KeepaliveSpec (); 
		KeepaliveTriggerType keepaliveTriggerType = new KeepaliveTriggerType();
		keepaliveTriggerType.set(Integer.valueOf(data.get("KeepaliveTriggerType")));
		keepaliveSpec.setKeepaliveTriggerType(keepaliveTriggerType);
		keepaliveSpec.setPeriodicTriggerValue(new UnsignedInteger(Integer.valueOf(data.get("PeriodicTriggerValue"))));
		commLinkConfiguration.setKeepaliveSpec(keepaliveSpec);
		TcpLinkConfiguration tcpLinkConfiguration = new TcpLinkConfiguration();
		TcpLinkCommMode commMode = new TcpLinkCommMode();
		commMode.set(Integer.valueOf(data.get("CommMode")));
		tcpLinkConfiguration.setCommMode(commMode);
		tcpLinkConfiguration.setIsSSL(new  Bit(Integer.valueOf(data.get("IsSSL"))));
		ClientModeConfiguration clientModeConfiguration = new ClientModeConfiguration();
		IPAddress iPAddress = new IPAddress();
		if(data.get("Address")!=null){
			String[] addressStrs = data.get("Address").toString().split(",");
			UnsignedIntegerArray address = new UnsignedIntegerArray(addressStrs.length);
			for(int i = 0;i<addressStrs.length;i++){
				address.set(i, new UnsignedInteger(addressStrs[i]));
			}
			iPAddress.setAddress(address);
		}
		
		IPAddressVersion version = new IPAddressVersion();
		version.set(Integer.valueOf(data.get("Version")));
		iPAddress.setVersion(version);
		clientModeConfiguration.setIPAddress(iPAddress);
		clientModeConfiguration.setPort(new UnsignedShort(Integer.valueOf(data.get("Port"))));
		tcpLinkConfiguration.setClientModeConfiguration(clientModeConfiguration);
		ServerModeConfiguration serverModeConfiguration = new ServerModeConfiguration();
		serverModeConfiguration.setPort(new UnsignedShort(Integer.valueOf(data.get("Port"))));
		tcpLinkConfiguration.setServerModeConfiguration(serverModeConfiguration);
		commLinkConfiguration.setTcpLinkConfiguration(tcpLinkConfiguration);
		SerialLinkConfiguration serialLinkConfiguration = new SerialLinkConfiguration();
		serialLinkConfiguration.setIfIndex(new UnsignedByte(Integer.valueOf(data.get("IfIndex"))));
		serialLinkConfiguration.setSrcAddr(new UnsignedByte(Integer.valueOf(data.get("SrcAddr"))));
		serialLinkConfiguration.setDstAddr(new UnsignedByte(Integer.valueOf(data.get("DstAddr"))));
		commLinkConfiguration.setSerialLinkConfiguration(serialLinkConfiguration);
		HttpLinkConfiguration httpLinkConfiguration = new HttpLinkConfiguration();
		httpLinkConfiguration.setServerUrl(new UTF8String_UTF_8(data.get("ServerUrl")));
		commLinkConfiguration.setHttpLinkConfiguration(httpLinkConfiguration);
		commLinkConfigurationList.add(commLinkConfiguration);
		reportDestination.setCommLinkConfigurationList(commLinkConfigurationList);
		selectReportSpec.setReportDestination(reportDestination);
		
		
		}
	public static void readCardtest(LLRPConnection connection,HashMap<String, String> data) throws Exception{
		DeleteSelectSpec del = new DeleteSelectSpec();
		del.setSelectSpecID(new UnsignedInteger(0));
		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(del.encodeBinary());
		DeleteSelectSpecAck rep = new DeleteSelectSpecAck();
		rep = (DeleteSelectSpecAck) connection.transact(message1);
		UnsignedInteger code = rep.getStatus().getStatusCode();
		if (code.intValue() == 0) {
			org.jdom.Document doc = new org.jdom.input.SAXBuilder().build(new FileReader(System.getProperty("user.dir") + "/AddSelectSpec.xml"));
			LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(doc);
			AddSelectSpecAck repAdd = new AddSelectSpecAck();
			repAdd = (AddSelectSpecAck) connection.transact(addSelectSpecMsg);
			UnsignedInteger code1 = repAdd.getStatus().getStatusCode();
			if(code1.intValue()==0){
				log.error("AddSelectSpec 添加成功");
			}else{
				log.error("AddSelectSpec  添加失败");
			}
			// EnableSelectSpec
			EnableSelectSpec enableSelectSpec = new EnableSelectSpec();
			enableSelectSpec.setSelectSpecID(new UnsignedInteger(250));
			EnableSelectSpecAck enableSelectSpecAck = new EnableSelectSpecAck();
			enableSelectSpecAck = (EnableSelectSpecAck) connection.transact(enableSelectSpec);
			if(enableSelectSpecAck.getStatus().getStatusCode().intValue()==0){
				log.error("EnableSelectSpec 添加成功");
			}else{
				log.error("EnableSelectSpec  添加失败");
			}		
			// //StartSelectSpec
			StartSelectSpec startSelectSpec = new StartSelectSpec();
			startSelectSpec.setSelectSpecID(new UnsignedInteger(250));
			StartSelectSpecAck startSelectSpecAck = new StartSelectSpecAck();
			startSelectSpecAck = (StartSelectSpecAck) connection.transact(LLRPMessageFactory.createLLRPMessage(startSelectSpec.encodeBinary()));
			if(startSelectSpecAck.getStatus().getStatusCode().intValue()==0){
				log.error("startSelectSpec 添加成功");
			}else{
				log.error("startSelectSpec  添加失败");
			}
		}else{
				log.error("添加规则删除失败");
		}
	}
}
