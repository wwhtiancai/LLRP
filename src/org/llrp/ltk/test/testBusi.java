package org.llrp.ltk.test;

import java.util.ArrayList;
import java.util.List;

import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.generated.enumerations.AirProtocolType;
import org.llrp.ltk.generated.enumerations.AntennaSpecStopTriggerType;
import org.llrp.ltk.generated.enumerations.CommLinkType;
import org.llrp.ltk.generated.enumerations.HbBankType;
import org.llrp.ltk.generated.enumerations.HbReadType;
import org.llrp.ltk.generated.enumerations.HbSpecMemoryBankIDType;
import org.llrp.ltk.generated.enumerations.KeepaliveTriggerType;
import org.llrp.ltk.generated.enumerations.RfSpecSelectType;
import org.llrp.ltk.generated.enumerations.SelectSpecStartTriggerType;
import org.llrp.ltk.generated.enumerations.SelectSpecState;
import org.llrp.ltk.generated.enumerations.SelectSpecStopTriggerType;
import org.llrp.ltk.generated.interfaces.SpecParameter;
import org.llrp.ltk.generated.messages.AddSelectSpec;
import org.llrp.ltk.generated.messages.AddSelectSpecAck;
import org.llrp.ltk.generated.messages.DeleteAccessSpec;
import org.llrp.ltk.generated.messages.DeleteAccessSpecAck;
import org.llrp.ltk.generated.messages.DeleteSelectSpecAck;
import org.llrp.ltk.generated.messages.EnableSelectSpec;
import org.llrp.ltk.generated.messages.EnableSelectSpecAck;
import org.llrp.ltk.generated.messages.StartSelectSpec;
import org.llrp.ltk.generated.messages.StartSelectSpecAck;
import org.llrp.ltk.generated.parameters.AntennaSpec;
import org.llrp.ltk.generated.parameters.AntennaSpecStopTrigger;
import org.llrp.ltk.generated.parameters.CommLinkConfiguration;
import org.llrp.ltk.generated.parameters.KeepaliveSpec;
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
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedByteArray;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;

/*
 *wuweihong
 *2017-7-14
 */
public class testBusi {
	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
		SystemCache cache = new SystemCache();
		ObjectMessage endpoint = new ObjectMessage();
		LLRPConnection connection = new LLRPConnector(endpoint, cache.ipAddress,cache.port);
		((LLRPConnector) connection).connect();
//		DeleteAccessSpec del = new DeleteAccessSpec();
//		del.setAccessSpecID(new  UnsignedInteger(0));
//		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(del.encodeBinary());
//		DeleteAccessSpecAck rep = new DeleteAccessSpecAck();
//		rep = (DeleteAccessSpecAck) connection.transact(message1);
//		UnsignedInteger code = rep.getStatus().getStatusCode();
		
		
		
		DeleteAccessSpec deleteAccessSpec = new DeleteAccessSpec();
		deleteAccessSpec.setAccessSpecID(new UnsignedInteger(0));
		LLRPMessage mesg = LLRPMessageFactory.createLLRPMessage(deleteAccessSpec.encodeBinary());
		DeleteAccessSpecAck rep = new DeleteAccessSpecAck();
		rep = (DeleteAccessSpecAck) connection.transact(mesg);
		
		AddSelectSpec addSelectSpec = new AddSelectSpec();
		SelectSpec sl = new SelectSpec();
		sl.setSelectSpecID(new UnsignedInteger(120));
		sl.setPriority(new UnsignedByte(0));
		SelectSpecState selectSpecState = new SelectSpecState();
		selectSpecState.set("Disabled");
		sl.setCurrentState(selectSpecState);
		sl.setPersistence(new Bit(0));
		
		SelectSpecStartTrigger selectSpecStartTrigger = new SelectSpecStartTrigger();
		SelectSpecStartTriggerType selectSpecStartTriggerType = new SelectSpecStartTriggerType();
		selectSpecStartTriggerType.set("Null");
		PeriodicTrigger periodicTrigger = new PeriodicTrigger();
		periodicTrigger.setOffset(new UnsignedInteger(0));
		periodicTrigger.setPeriod(new UnsignedInteger(0));
		selectSpecStartTrigger.setSelectSpecStartTriggerType(selectSpecStartTriggerType);
		selectSpecStartTrigger.setPeriodicTrigger(periodicTrigger);
		SelectSpecStopTrigger selectSpecStopTrigger = new SelectSpecStopTrigger();
		selectSpecStopTrigger.setDurationValue(new UnsignedInteger(0));
		SelectSpecStopTriggerType selectSpecStoptTriggerType = new SelectSpecStopTriggerType();
		selectSpecStoptTriggerType.set("Null");
		selectSpecStopTrigger.setSelectSpecStopTriggerType(selectSpecStoptTriggerType);
		sl.setSelectSpecStartTrigger(selectSpecStartTrigger);
		sl.setSelectSpecStopTrigger(selectSpecStopTrigger);
		
		AntennaSpec antennaSpec = new AntennaSpec();
		AntennaSpecStopTrigger antennaSpecStopTrigger = new AntennaSpecStopTrigger();
		AntennaSpecStopTriggerType antennaSpecStopTriggerType = new AntennaSpecStopTriggerType();
		antennaSpecStopTriggerType.set("Null");
		antennaSpecStopTrigger.setDurationValue(new UnsignedInteger(0));
		antennaSpecStopTrigger.setAntennaSpecStopTriggerType(antennaSpecStopTriggerType);
		antennaSpec.setAntennaSpecStopTrigger(antennaSpecStopTrigger);
		UnsignedByteArray cc = new UnsignedByteArray(1);
		cc.set(0,new UnsignedByte(0));
		antennaSpec.setAntennaIDs(cc);
		RfSpec rfSpec = new RfSpec();
		AirProtocolType protocolID = new AirProtocolType();
		protocolID.set(0);
		rfSpec.setProtocolID(protocolID);
		
		RfSpecSelectType selectType = new RfSpecSelectType();
//		selectType.set("Inventory");Read
		selectType.set("Read");
		rfSpec.setSelectType(selectType);
		rfSpec.setRfSpecID(new UnsignedShort(120));
		
		//MemoryBank
		MemoryBank memoryBank = new MemoryBank();
		memoryBank.setCount(new UnsignedShort(9));
		memoryBank.setPointer(new UnsignedShort(4));
		HbSpecMemoryBankIDType hbSpecMemoryBankIDType = new HbSpecMemoryBankIDType();
		hbSpecMemoryBankIDType.set(0);
		memoryBank.setMemoryBankID(hbSpecMemoryBankIDType);
		HbBankType bankType = new HbBankType();
		HbReadType readType = new HbReadType();
//		readType.set("As_Lenth");
		readType.set("AS_Type");
		bankType.set("All");
		memoryBank.setBankType(bankType);
		memoryBank.setReadType(readType);
		rfSpec.setMemoryBank(memoryBank);
		
		List<RfSpec> rfSpecList = new ArrayList<RfSpec>() ; 
		rfSpecList.add(rfSpec);
		antennaSpec.setRfSpecList(rfSpecList);
		
		List<SpecParameter> specParameterList  = new ArrayList<SpecParameter>();
		specParameterList.add(antennaSpec);
		sl.setSpecParameterList(specParameterList);
		
//		SelectReportSpec selectReportSpec = new SelectReportSpec();
//		SelectReportTriggerType selectReportTrigger = new SelectReportTriggerType();
//		selectReportTrigger.set("Upon_N_Tags_Or_End_Of_SelectSpec");
//		selectReportSpec.setSelectReportTrigger(selectReportTrigger);
//		selectReportSpec.setNValue(new UnsignedShort(1));
//		selectReportSpec.setEnableSelectSpecID(new Bit(1));
//		selectReportSpec.setEnableSpecIndex(new Bit(1));
//		selectReportSpec.setEnableRfSpecID(new Bit(1));
//		selectReportSpec.setEnableAntennaID(new Bit(1));
//		selectReportSpec.setEnablePeakRSSI(new Bit(1));
//		selectReportSpec.setEnableFirstSeenTimestamp(new Bit(1));
//		selectReportSpec.setEnableLastSeenTimestamp(new Bit(1));
//		selectReportSpec.setEnableTagSeenCount(new Bit(1));
//		selectReportSpec.setEnableAccessSpecID(new Bit(1));
//		sl.setSelectReportSpec(selectReportSpec);
		
		
		
		
		addSelectSpec.setSelectSpec(sl);
		try {
			LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(addSelectSpec.encodeBinary());
		} catch (InvalidLLRPMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
