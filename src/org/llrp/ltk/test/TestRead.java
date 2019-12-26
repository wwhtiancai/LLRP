package org.llrp.ltk.test;

import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.generated.enumerations.*;
import org.llrp.ltk.generated.interfaces.SpecParameter;
import org.llrp.ltk.generated.messages.*;
import org.llrp.ltk.generated.parameters.*;
import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.net.LLRPConnector;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by stone on 2017/3/27.
 */
public class TestRead {

	public static void main(String[] args) throws Exception {
		SystemCache cache = new SystemCache();
		SystemCache.ipAddress = "10.86.20.50";
		SystemCache.port = 5084;

		TestMessage tableProgramMessage = new TestMessage();
		LLRPConnection connection = new LLRPConnector(tableProgramMessage, SystemCache.ipAddress, SystemCache.port);
		((LLRPConnector) connection).connect();

		String aqmkxh = getAqmkxh(connection);
		tableProgramMessage.setAqmkxh(aqmkxh);
		tableProgramMessage.setUpdateEri();
		UpdateEri updateEri = tableProgramMessage.getUpdateEri();
		//String cert = updateEri.getCert(aqmkxh);

		setZsl(connection);// 配置证书�?

		tableProgramMessage.setConnection(connection);

		DeleteSelectSpec del = new DeleteSelectSpec();
		del.setSelectSpecID(new UnsignedInteger(0));
		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(del.encodeBinary());
		DeleteSelectSpecAck rep = new DeleteSelectSpecAck();
		rep = (DeleteSelectSpecAck) connection.transact(message1);
		UnsignedInteger code = rep.getStatus().getStatusCode();
		System.out.println("DeleteSelectSpecAck:"+code);
		DeleteAccessSpec delAc = new DeleteAccessSpec();
		delAc.setAccessSpecID(new UnsignedInteger(0));
		DeleteAccessSpecAck dlACK = (DeleteAccessSpecAck) connection.transact(delAc);
		System.out.println("DeleteAccessSpecAck===" + dlACK.getStatus().getStatusCode());
		if (code.intValue() == 0) {
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
			selectSpecStartTriggerType.set("Periodic");
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
			antennaSpecStopTriggerType.set(0);
			antennaSpecStopTrigger.setDurationValue(new UnsignedInteger(0));
			antennaSpecStopTrigger.setAntennaSpecStopTriggerType(antennaSpecStopTriggerType);
			antennaSpec.setAntennaSpecStopTrigger(antennaSpecStopTrigger);
			UnsignedByteArray cc = new UnsignedByteArray(1);
			cc.set(0, new UnsignedByte(0));
			antennaSpec.setAntennaIDs(cc);
			RfSpec rfSpec = new RfSpec();
			AirProtocolType protocolID = new AirProtocolType();
			protocolID.set(0);
			rfSpec.setProtocolID(protocolID);

			RfSpecSelectType selectType = new RfSpecSelectType();
			// selectType.set("Inventory");Read
			selectType.set("Read");
			rfSpec.setSelectType(selectType);
			rfSpec.setRfSpecID(new UnsignedShort(120));

			// MemoryBank
			MemoryBank memoryBank = new MemoryBank();
			memoryBank.setCount(new UnsignedShort(10));
			memoryBank.setPointer(new UnsignedShort(4));
			memoryBank.setMemoryBankID(new HbSpecMemoryBankIDType(0));
			HbBankType bankType = new HbBankType();
			HbReadType readType = new HbReadType();
			readType.set("As_Lenth");
			bankType.set("All");
			memoryBank.setBankType(bankType);
			memoryBank.setReadType(readType);
			rfSpec.setMemoryBank(memoryBank);

			List<RfSpec> rfSpecList = new ArrayList<RfSpec>();
			rfSpecList.add(rfSpec);
			antennaSpec.setRfSpecList(rfSpecList);

			List<SpecParameter> specParameterList = new ArrayList<SpecParameter>();
			specParameterList.add(antennaSpec);
			sl.setSpecParameterList(specParameterList);
			addSelectSpec.setSelectSpec(sl);
			LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(addSelectSpec.encodeBinary());
			AddSelectSpecAck repAdd = new AddSelectSpecAck();
			repAdd = (AddSelectSpecAck) connection.transact(addSelectSpecMsg);
			UnsignedInteger code1 = repAdd.getStatus().getStatusCode();

			// EnableSelectSpec
			EnableSelectSpec enableSelectSpec = new EnableSelectSpec();
			enableSelectSpec.setSelectSpecID(new UnsignedInteger(120));
			EnableSelectSpecAck enableSelectSpecAck = new EnableSelectSpecAck();
			enableSelectSpecAck = (EnableSelectSpecAck) connection.transact(enableSelectSpec);
			//
			//StartSelectSpec
			StartSelectSpec startSelectSpec = new StartSelectSpec();
			startSelectSpec.setSelectSpecID(new UnsignedInteger(120));
			StartSelectSpecAck startSelectSpecAck = new StartSelectSpecAck();
			startSelectSpecAck = (StartSelectSpecAck) connection.transact(LLRPMessageFactory.createLLRPMessage(startSelectSpec.encodeBinary()));
			startSelectSpecAck.getStatus().getStatusCode();

		}
	}

	/**
	 * 获取读写器安全模块序�?
	 * 
	 * @return
	 */
	public static String getAqmkxh(LLRPConnection connection) {
		String aqmkxh = "";
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
			if (0 == rep.getStatus().getStatusCode().intValue()) {
				SecurityModuleConfiguration securityModuleConfiguration = rep.getSecurityModuleConfiguration();
				if (securityModuleConfiguration != null) {
					// 获取安全模块序号
					UnsignedByteArray cc = securityModuleConfiguration.getGenaralConfigData().getSecurityModuleSN().getSerialNumber();
					StringBuffer securityStr = new StringBuffer();
//					String securityStr = "";
					LLRPBitList bit4 = new LLRPBitList();
					LLRPBitList bit5 = new LLRPBitList();
					for (int i = 0; i < cc.size(); i++) {
						switch (i) {
						case 0:
//							securityStr = securityStr+(char) cc.get(i).intValue();
							securityStr.append((char) cc.get(i).intValue());
							break;
						case 1:
//							securityStr = securityStr+(char) cc.get(i).intValue();
							securityStr.append((char) cc.get(i).intValue());
							break;
						case 2:
							String Str3 = String.format("%02d", cc.get(i).intValue());
//							securityStr = securityStr+Str3;
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
//					securityStr = securityStr+String.format("%03d", new UnsignedInteger(bit4).toInteger()) +
//					String.format("%05d", new UnsignedInteger(bit5).toInteger());
					securityStr.append(String.format("%03d", new UnsignedInteger(bit4).toInteger())).append(
							String.format("%05d", new UnsignedInteger(bit5).toInteger()));
					aqmkxh = securityStr.toString();
					System.out.println("aqmkxh:" + aqmkxh);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// String cert =
		// "30820213308201B6A00302010202080F2EC63DF79FBBB0300C06082A811CCF5501837505003056310B300906035504060C02434E310B300906035504080C024A53310B300906035504070C025758310D300B060355040A0C04544D5249310D300B060355040B0C04544D5249310F300D06035504030C06524649444341301E170D3136303931393136303230305A170D3336303931393136303230305A3065310B300906035504060C02434E310B300906035504080C024A53310B300906035504070C025758310D300B060355040A0C04544D5249310D300B060355040B0C04544D5249311E301C06035504030C1531525241303230303230313431325246494453414D3059301306072A8648CE3D020106082A811CCF5501822D03420004BAE24FC4FA5793E0885E53BA6D0FF28926D4125B4C3CB9FA229DCD0A351DFE9D68B300ADCA58E77C45FED70B3FA93AB17EE390B5F051E380F7F12091FAF84540A35D305B301F0603551D230418301680142B6A0FC25AB98EF6B7CEA448EB4309DFF4705BAC300C0603551D1304053003010100300B0603551D0F040403020186301D0603551D0E0416041468840A35791CCB0392C886E91C6A328053E3F295300C06082A811CCF5501837505000349003046022100925C58FC42D606B6935007AA2915170DF3BC5CC3426AD0996A8C6836879A48C7022100F40C17E6258F3E52460C9FE643865FAAB05C4AB6C15F1FF3754E1C02D1AFED1A";
		// String aqmkxh = "RA0200201412";
		return aqmkxh;
	}

	/**
	 * 配置证书�?
	 * 
	 * @param connection
	 */
	public static void setZsl(LLRPConnection connection) {
		try {
			SetDeviceConfig setDeviceConfig = new SetDeviceConfig();
			setDeviceConfig.setResetToFactoryDefault(new Bit(0));
			SecurityModuleConfiguration securityModuleConfiguration = new SecurityModuleConfiguration();
			PrivateConfigData privateConfigData = new PrivateConfigData();
			UnsignedByteArray configData = new UnsignedByteArray();
			String heard = "400001";
			String values = "30820202308201A6A003020102020900C99B7D0278C3B535300C06082A811CCF5501837505003056310B300906035504060C02434E310B300906035504080C024A53310B300906035504070C025758310D300B060355040A0C04544D5249310D300B060355040B0C04544D5249310F300D06035504030C06524649444341301E170D3135313232333039353734385A170D3335313232333039353734385A3054310B300906035504060C02434E310B300906035504080C024A53310B300906035504070C025758310C300A060355040A0C03414243310C300A060355040B0C03414243310F300D06035504030C063343414142433059301306072A8648CE3D020106082A811CCF5501822D03420004D661AB9EDEF7BE4B3BB931F832231DB0C68ABDE47E0220D26220CE516C32CABCBDE3DCE747FB5FB379EB2A42EEBE6BA6EF1CCA0B2BDB6ACEDB97BFB92CD07F34A35D305B301F0603551D230418301680144C32B197D9331BC4A605C1C6E58B625BF0977658300C0603551D13040530030101FF300B0603551D0F040403020106301D0603551D0E041604144C32B197D9331BC4A605C1C6E58B625BF0977658300C06082A811CCF5501837505000348003045022100EFA8B2ED0573330FD43397E9744A1FFFEA840AB5719C2FFDAF5662FFF558528E022072614918C13A1DA5689DF963D6F5C358F648E19F7B9B5BCE081F9B1648DC2248";
			String configStr = heard + values;
			for (int i = 0; i < configStr.length() / 2; i++) {
				configData.add(new UnsignedByte(Integer.parseInt(configStr.substring(i * 2, i * 2 + 2), 16)));
			}
			privateConfigData.setConfigData(configData);

			securityModuleConfiguration.setPrivateConfigData(privateConfigData);
			setDeviceConfig.setSecurityModuleConfiguration(securityModuleConfiguration);
			SetDeviceConfigAck setDeviceConfigAck = new SetDeviceConfigAck();
			setDeviceConfigAck = (SetDeviceConfigAck) connection.transact(setDeviceConfig);
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

}
