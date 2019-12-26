package org.llrp.ltk.generated;

import org.apache.log4j.Logger;
import org.llrp.ltk.generated.enumerations.*;
import org.llrp.ltk.generated.interfaces.OpSpec;
import org.llrp.ltk.generated.interfaces.SpecParameter;
import org.llrp.ltk.generated.messages.*;
import org.llrp.ltk.generated.parameters.*;
import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.net.LLRPConnector;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.net.TableProgramMessage;
import org.llrp.ltk.types.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 *wuweihong
 *2016-3-10
 */
public class LLRPWriteTest {

	private static final Logger log = Logger.getLogger(LLRPWriteTest.class);

	public static void main(String[] args) throws Exception {
		
		SystemCache cache = new SystemCache();
		TableProgramMessage TableProgramMessage = new TableProgramMessage();
//		LLRPConnection connection = new LLRPConnector(TableProgramMessage, cache.ipAddress, cache.port);
		LLRPConnection connection = new LLRPConnector(TableProgramMessage, "192.168.0.227", 5084);
		((LLRPConnector) connection).connect();
//		
//		StopSelectSpec startSelectSpec = new StopSelectSpec();
//		startSelectSpec.setSelectSpecID(new UnsignedInteger(120));
//		StopSelectSpecAck startSelectSpecAck = new StopSelectSpecAck();
//		startSelectSpecAck = (StopSelectSpecAck)connection.transact(LLRPMessageFactory.createLLRPMessage(startSelectSpec.encodeBinary()));
//		startSelectSpecAck.getStatus().getStatusCode();
		
		
		//安全模块设置
		GetDeviceConfig getDeviceConfig = new GetDeviceConfig();
		getDeviceConfig.setAntennaID(new UnsignedByte(0));
		getDeviceConfig.setRequestedData(new GetDeviceConfigRequestedDataType(0));
		GetDeviceConfigAck getDeviceConfigAck = new GetDeviceConfigAck();
		getDeviceConfigAck = (GetDeviceConfigAck) connection.transact(getDeviceConfig);
        UnsignedByteArray modelNo = getDeviceConfigAck.getSecurityModuleConfiguration().getGenaralConfigData().getSecurityModuleSN().getSerialNumber();

		SetDeviceConfig setDeviceConfig = new SetDeviceConfig();
		setDeviceConfig.setResetToFactoryDefault(new Bit(0));
		SecurityModuleConfiguration securityModuleConfiguration = new SecurityModuleConfiguration();
		PrivateConfigData privateConfigData = new PrivateConfigData();
		UnsignedByteArray configData = new UnsignedByteArray();
		String heard = "400001";
		String values = "30820202308201A6A003020102020900C99B7D0278C3B535300C06082A811CCF5501837505003056310B300906035504060C02434E310B300906035504080C024A53310B300906035504070C025758310D300B060355040A0C04544D5249310D300B060355040B0C04544D5249310F300D06035504030C06524649444341301E170D3135313232333039353734385A170D3335313232333039353734385A3054310B300906035504060C02434E310B300906035504080C024A53310B300906035504070C025758310C300A060355040A0C03414243310C300A060355040B0C03414243310F300D06035504030C063343414142433059301306072A8648CE3D020106082A811CCF5501822D03420004D661AB9EDEF7BE4B3BB931F832231DB0C68ABDE47E0220D26220CE516C32CABCBDE3DCE747FB5FB379EB2A42EEBE6BA6EF1CCA0B2BDB6ACEDB97BFB92CD07F34A35D305B301F0603551D230418301680144C32B197D9331BC4A605C1C6E58B625BF0977658300C0603551D13040530030101FF300B0603551D0F040403020106301D0603551D0E041604144C32B197D9331BC4A605C1C6E58B625BF0977658300C06082A811CCF5501837505000348003045022100EFA8B2ED0573330FD43397E9744A1FFFEA840AB5719C2FFDAF5662FFF558528E022072614918C13A1DA5689DF963D6F5C358F648E19F7B9B5BCE081F9B1648DC2248";
		String configStr = heard+values;
		for (int i = 0; i < configStr.length()/2; i++)    
	    {      
			configData.add(new UnsignedByte(Integer.parseInt(configStr.substring(i*2, i*2+2), 16)));
	    } 
		privateConfigData.setConfigData(configData);
		
		securityModuleConfiguration.setPrivateConfigData(privateConfigData);
		setDeviceConfig.setSecurityModuleConfiguration(securityModuleConfiguration);
		SetDeviceConfigAck setDeviceConfigAck = new SetDeviceConfigAck();
		setDeviceConfigAck = (SetDeviceConfigAck) connection.transact(setDeviceConfig);
		
		
		
		DeleteSelectSpec del = new DeleteSelectSpec();
		del.setSelectSpecID(new UnsignedInteger(0));
		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(del.encodeBinary());
		DeleteSelectSpecAck rep = new DeleteSelectSpecAck();
		rep = (DeleteSelectSpecAck) connection.transact(message1);
		UnsignedInteger code = rep.getStatus().getStatusCode();
		
		DeleteAccessSpec delAc = new DeleteAccessSpec();
		delAc.setAccessSpecID(new UnsignedInteger(0));
		DeleteAccessSpecAck dlACK = (DeleteAccessSpecAck)connection.transact(delAc);
		System.out.println("DeleteAccessSpecAck===" +dlACK.getStatus().getStatusCode());
		if(code.intValue()==0){
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
			selectSpecStartTriggerType.set("Immediate");
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
//			selectType.set("Inventory");Read
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
//			readType.set("As_Lenth");
			readType.set("Read_As_Area_Type");
			bankType.set("Full");
			memoryBank.setBankType(bankType);
			memoryBank.setReadType(readType);
			rfSpec.setMemoryBank(memoryBank);
			
			List<RfSpec> rfSpecList = new ArrayList<RfSpec>() ; 
			rfSpecList.add(rfSpec);
			antennaSpec.setRfSpecList(rfSpecList);
			
			List<SpecParameter> specParameterList  = new ArrayList<SpecParameter>();
			specParameterList.add(antennaSpec);
			sl.setSpecParameterList(specParameterList);
			
			SelectReportSpec selectReportSpec = new SelectReportSpec();
			SelectReportTriggerType selectReportTrigger = new SelectReportTriggerType();
			selectReportTrigger.set("Upon_N_Tags_Or_End_Of_SelectSpec");
			selectReportSpec.setSelectReportTrigger(selectReportTrigger);
			selectReportSpec.setNValue(new UnsignedShort(1));
			selectReportSpec.setEnableSelectSpecID(new Bit(1));
			selectReportSpec.setEnableSpecIndex(new Bit(1));
			selectReportSpec.setEnableRfSpecID(new Bit(1));
			selectReportSpec.setEnableAntennaID(new Bit(1));
			selectReportSpec.setEnablePeakRSSI(new Bit(1));
			selectReportSpec.setEnableFirstSeenTimestamp(new Bit(1));
			selectReportSpec.setEnableLastSeenTimestamp(new Bit(1));
			selectReportSpec.setEnableTagSeenCount(new Bit(1));
			selectReportSpec.setEnableAccessSpecID(new Bit(1));
			sl.setSelectReportSpec(selectReportSpec);
			
			
			
			
			addSelectSpec.setSelectSpec(sl);
			LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(addSelectSpec.encodeBinary());
			AddSelectSpecAck repAdd = new AddSelectSpecAck();
			repAdd = (AddSelectSpecAck) connection.transact(addSelectSpecMsg);
			UnsignedInteger code1= repAdd.getStatus().getStatusCode();
			//EnableSelectSpec
//			EnableSelectSpec enableSelectSpec = new EnableSelectSpec();
//			enableSelectSpec.setSelectSpecID(new UnsignedInteger(120));
//			EnableSelectSpecAck enableSelectSpecAck = new EnableSelectSpecAck();
//			enableSelectSpecAck = (EnableSelectSpecAck) connection.transact(enableSelectSpec);
		
		
		AddAccessSpec addAccessSpec = new AddAccessSpec();
		AccessSpec accessSpec = new AccessSpec();
		accessSpec.setAccessSpecID(new UnsignedInteger(120));
		accessSpec.setSelectSpecID(new UnsignedInteger(120));
		accessSpec.setAntennaID(new UnsignedByte(0));
		accessSpec.setProtocolID(new AirProtocolType(0));
		accessSpec.setCurrentState(new AccessSpecState(0));
		accessSpec.setPersistence(new Bit(0));
		AccessSpecStopTrigger accessSpecStopTrigger = new AccessSpecStopTrigger();
		accessSpecStopTrigger.setAccessSpecStopTriggerType(new AccessSpecStopTriggerType(0));
		accessSpecStopTrigger.setOperationCountValue(new UnsignedShort(3));
		accessSpec.setAccessSpecStopTrigger(accessSpecStopTrigger);
		AccessCommand accessCommand = new AccessCommand ();
		HbMatchSpec hbMatchSpec = new HbMatchSpec();
		List<HbTargetTag> hbTargetTagList = new LinkedList<HbTargetTag>();
		
		HbTargetTag matchSpec = new HbTargetTag();
		matchSpec.setMemoryType(new HbTargetTagMemoryType(0));
		matchSpec.setMatchType(new HbTargetTagMatchType(0));
		matchSpec.setPointer(new UnsignedShort(0)); //偏移量
		BitArray bitsTagMask = new BitArray();
		for(int i =0;i<64;i++){
			bitsTagMask.add(new Bit(1));
		}
		BitArray bitsTagData = new BitArray();
		//[232, 129, 0, 10, 24, 228, 197, 99] [232, 129, 0, 1, 137, 96, 79, 213]  E881000A18E4C563  E881000189604FD5
		String TidStr = "E8820000621806C8"; 
		UnsignedByteArray_HEX Tid =  new UnsignedByteArray_HEX();
		for (int i = 0; i < TidStr.length()/2; i++)    
	    {      
			Tid.add(new UnsignedByte(Integer.parseInt(TidStr.substring(i*2, i*2+2), 16)));
	    } 
//		Tid.add(new UnsignedByte(232));
//		Tid.add(new UnsignedByte(129));
//		Tid.add(new UnsignedByte(0));
//		Tid.add(new UnsignedByte(1));
//		Tid.add(new UnsignedByte(137));
//		Tid.add(new UnsignedByte(96));
//		Tid.add(new UnsignedByte(79));
//		Tid.add(new UnsignedByte(213));
		
		LLRPBitList bit = new LLRPBitList();
		for(int j =0;j<Tid.size();j++){
			bit.append(Tid.get(j).encodeBinary());
		}
//		BitArray bitsTagDatats = new BitArray();
//		bitsTagDatats.decodeBinary(bit);
		int num = Tid.encodeBinary().length();
		for(int i =0;i<bit.length();i++){
			bitsTagData.add(new Bit(bit.get(i)));
		}
		
//		 StringBuffer sb = new StringBuffer(bitsTagMask.size());
//		  String sTemp;
//		  for (int i = 0; i < bitsTagMask.size(); i++) {
//		   sTemp = Integer.toHexString(0xFF & bitsTagMask.encodeBinary());
//		   if (sTemp.length() < 2)
//		    sb.append(0);
//		   sb.append(sTemp.toUpperCase());
//		  }
		
		
		matchSpec.setTagData(bitsTagData);//FFFFF
		matchSpec.setTagMask(bitsTagMask);
		hbTargetTagList.add(matchSpec);
		hbMatchSpec.setHbTargetTagList(hbTargetTagList);
		accessCommand.setMatchSpec(hbMatchSpec);
		HbPrivateWriteSpec hbPrivateWriteSpec = new HbPrivateWriteSpec();
		hbPrivateWriteSpec.setOpSpecID(new UnsignedShort(0));
		hbPrivateWriteSpec.setWriteType(new UnsignedByte(1));
		String strHEX = "04003A0F103B5A03E958484A638EA7066E6855DD251DEC0FCA474572295BBECEE40D34E068A4EC19C39A08F0E9FEC9F1AAA6260742AE45EBA37BEEBB0F61E63B6D4CE6CE4DBDD5E91A8BFBD73A81FB57D1A4031D8FD2D60ECF7F52D5DC89339819863B5F17BBC072D5DAC28EB34F9856275EE8820000621806C83BF710236F90995CCB8E1AEEA879EE87EDA50F18CC239AF583C6C74A74C4A83D6328F12091DDB9BF1098C89BF7204530BD1D101553CC85BB3324A13AB257575B4B53FDC6AD64587A16E087F7D94FBC3EE087403107739DF27302CF329511CFD0AAF9CB56088ADFAC88867EFC0BC63D597C35EFC883173E966EF7CD82842D83F45119EFDF6A78C4569C4F10387A1B76C5631EF78872C358C973DE5664409791B983D2FE97D53934B1D802CAA6E28CBC8E1474EEFC4B229C493BAC5E16B6C54F0E57BE0F747DE7A25A7A89DAA08CF3C5834523A3F65D4564AADF4B29DE11F06AE95A02100002111503E330040544BD3748800465AC0755";
		strHEX = strHEX +"5D84A5EB7BC5ECB73B216A2A4B8781A136B3B0BD5ED818C5A63C98ECA90A7326397CD0D6C7D64DF218C495C6DD50D3E5AB18212235035CEDB6CC00220DE078FD";
//		String strHEX = "00E881000189604FD5013104021234567800";
		UnsignedShortArray_HEX unsignedShortArray_HEX = new UnsignedShortArray_HEX();
		 for (int i = 0; i < strHEX.length()/4; i++)    
		    {      
		        unsignedShortArray_HEX.add(new UnsignedShort(Integer.parseInt(strHEX.substring(i*4, i*4+4), 16)));
		    } 
		hbPrivateWriteSpec.setData(unsignedShortArray_HEX);
		HbReadSpec hbReadSpec = new HbReadSpec();
//		MemoryBank memoryBank = new MemoryBank();
//		memoryBank.setCount(new UnsignedShort(9));
//		memoryBank.setPointer(new UnsignedShort(4));
//		HbSpecMemoryBankIDType hbSpecMemoryBankIDType = new HbSpecMemoryBankIDType();
//		hbSpecMemoryBankIDType.set(0);
//		memoryBank.setMemoryBankID(hbSpecMemoryBankIDType);
//		HbBankType bankType = new HbBankType();
//		HbReadType readType = new HbReadType();
////		readType.set("As_Lenth");
//		readType.set("AS_Type");
//		bankType.set("All");
//		memoryBank.setBankType(bankType);
//		memoryBank.setReadType(readType);
		hbReadSpec.setMemoryBank(memoryBank);
		hbReadSpec.setOpSpecID(new UnsignedShort(0));
		List<OpSpec> opSpecList = new LinkedList<OpSpec>();
		opSpecList.add(hbPrivateWriteSpec);
		opSpecList.add(hbReadSpec);
		accessCommand.setOpSpecList(opSpecList);
		accessSpec.setAccessCommand(accessCommand);
		addAccessSpec.setAccessSpec(accessSpec);
		AddAccessSpecAck repAddAccess = new AddAccessSpecAck();
		repAddAccess = (AddAccessSpecAck) connection.transact(addAccessSpec);
		UnsignedInteger codeAck1= repAddAccess.getStatus().getStatusCode();
		System.out.println(codeAck1);
		
		EnableAccessSpec enableAccessSpec = new EnableAccessSpec();
		enableAccessSpec.setAccessSpecID(new UnsignedInteger(120));
		EnableAccessSpecAck enableAccessSpecAck = new EnableAccessSpecAck();
		enableAccessSpecAck = (EnableAccessSpecAck) connection.transact(enableAccessSpec);
		UnsignedInteger codeenableAccessSpecAck= enableAccessSpecAck.getStatus().getStatusCode();
		System.out.println(codeenableAccessSpecAck);
		
		
		
		EnableSelectSpec enableSelectSpec = new EnableSelectSpec();
		enableSelectSpec.setSelectSpecID(new UnsignedInteger(120));
		EnableSelectSpecAck enableSelectSpecAck = new EnableSelectSpecAck();
		enableSelectSpecAck = (EnableSelectSpecAck) connection.transact(enableSelectSpec);;
		System.out.println(enableSelectSpecAck.getStatus().getStatusCode());
		
		StartSelectSpec startSelectSpec = new StartSelectSpec();
		startSelectSpec.setSelectSpecID(new UnsignedInteger(120));
		StartSelectSpecAck startSelectSpecAck = new StartSelectSpecAck();
		startSelectSpecAck = (StartSelectSpecAck)connection.transact(LLRPMessageFactory.createLLRPMessage(startSelectSpec.encodeBinary()));
		System.out.println(startSelectSpecAck.getStatus().getStatusCode());
		}
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
