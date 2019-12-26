package org.llrp.ltk.test;

import com.alibaba.fastjson.JSONObject;
import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.generated.enumerations.*;
import org.llrp.ltk.generated.interfaces.AccessSpecResult;
import org.llrp.ltk.generated.interfaces.OpSpec;
import org.llrp.ltk.generated.interfaces.SpecParameter;
import org.llrp.ltk.generated.messages.*;
import org.llrp.ltk.generated.parameters.*;
import org.llrp.ltk.net.LLRPConnection;
import org.llrp.ltk.net.LLRPEndpoint;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.types.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jinjw on 2016/10/10.
 */
public class TestMessage implements LLRPEndpoint {
	// private static final Logger log =
	// Logger.getLogger(TableProgramMessage.class);
	// public static ConcurrentHashMap<String,CacheObject> tidMap = new
	// ConcurrentHashMap<String, CacheObject>();
	public static CacheMap<String, CacheObject> tidMap;
	public static String aqmkxh;
	public LLRPConnection connection;
	public UpdateEri updateEri;

	public static String getAqmkxh() {
		return aqmkxh;
	}

	public static void setAqmkxh(String aqmkxh) {
		TestMessage.aqmkxh = aqmkxh;
	}

	public LLRPConnection getConnection() {
		return connection;
	}

	public void setConnection(LLRPConnection connection) {
		this.connection = connection;
	}

	public TestMessage() {
		// tidMap.getDefault();
		tidMap = new CacheMap<String, CacheObject>(30000);
	}

	public void setUpdateEri() {
		this.updateEri = new UpdateEri("10.2.43.234", "8080", "admin", "passw0rd", aqmkxh);
	}

	public UpdateEri getUpdateEri() {
		return this.updateEri;
	}

	@Override
	public void messageReceived(LLRPMessage message) {
		if (message instanceof TagSelectAccessReport) {
			TagSelectAccessReport tagSelectAccessReport = (TagSelectAccessReport) message;
			List<TagReportData> tagReportDataList = tagSelectAccessReport.getTagReportDataList();
			for (int i = 0; i < tagReportDataList.size(); i++) {
				TagReportData tag = tagReportDataList.get(i);
				AntennaID tx = tag.getAntennaID();
				// String txnum ="1";
				String txnum = tx.getAntennaID().toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				UnsignedByteArray_HEX Tid = tag.getTID();
				String tid = Tid.toString().toUpperCase();
				// read
				CustomizedSelectSpecResult selectSpecResult = (CustomizedSelectSpecResult) tag.getSelectSpecResult();
                //普通读写数据
//                GenaralSelectSpecResult genaralSelectSpecResult = (GenaralSelectSpecResult) tag.getSelectSpecResult();
//                UnsignedShortArray_HEX tagData = genaralSelectSpecResult.getTagData();
//                tagData.get(0);

				// 读取后不含有该tid时，去获取数据，然后写入
				if (!tidMap.containsKey(tid)) {
					CacheObject cacheObject = new CacheObject();
					cacheObject.setBeginTime(System.currentTimeMillis());
					// cacheObject.setNr();
					tidMap.put(tid, cacheObject);

					System.out.println("=========getUpdateInfo");
					JSONObject result = updateEri.getUpdateInfo(tid);// 发起http请求
					System.out.println("=========getUpdateInfo.result"+result);

					if (result.get("resultId").equals("00")) {
						cacheObject.setNr(result);
						tidMap.put(tid, cacheObject);
						// 写入信息
						writeEri((JSONObject) result.get("info"));

					} else {
						System.out.println("获取更新信息返回Err!!");
					}

				} else {
				}

				// write specs result
				List<AccessSpecResult> accessSpecResults = tag.getAccessSpecResultList();
				for (int j = 0; j < accessSpecResults.size(); j++) {
					HbPrivateWriteSpecResult hbPrivateWriteSpecResult = accessSpecResults.get(j) instanceof HbPrivateWriteSpecResult ? ((HbPrivateWriteSpecResult) accessSpecResults
							.get(j))
							: null;
					if (hbPrivateWriteSpecResult != null) {
						UnsignedByte resultid = hbPrivateWriteSpecResult.getResult(); // 0
																						// :ok
						if (resultid.toInteger() == 0) {
							System.out.println("写返回正常！");
						}
					}

					HbCustomizedReadSpecResult hbCustomizedReadSpecResult = accessSpecResults.get(j) instanceof HbCustomizedReadSpecResult ? ((HbCustomizedReadSpecResult) accessSpecResults
							.get(j))
							: null;
					if (hbCustomizedReadSpecResult != null) {
						UnsignedByte resultId = hbCustomizedReadSpecResult.getResult();
						if (resultId.toInteger() == 0) {
							System.out.println("写数据后读取返回正常");

							ReadDataInfo realData = hbCustomizedReadSpecResult.getReadDataInfo();
							if (!tidMap.containsKey(tid)) {
								System.out.println("写完数据后未找到map");
							} else {
								// 核对写完后读取的信息
								CacheObject cardCache = tidMap.get(tid);
								JSONObject cardInfo = cardCache.getNr();
								System.out.println("cardInfo:"+cardInfo);
								JSONObject info = (JSONObject) cardInfo.get("info");
								JSONObject vehicleInfo = (JSONObject) info.get("vehicle");
								String writeHphm = vehicleInfo.getString("fzjg").substring(0, 1)+vehicleInfo.getString("hphm");

								CID cartId = realData.getCID();
								if (realData.getHPHMXH() != null) {
									HPHMXH hphm = realData.getHPHMXH();
									UnsignedByteArray_HEX HPHMXHData = hphm.getHPHMXHData();
									String hphmStr = "";
									String fzjgStr = "";
									for (int k = 0; k < HPHMXHData.size(); k++) {
										hphmStr += bit2Hphm(HPHMXHData.get(k).encodeBinary());
									}

									FPDH fpdh = realData.getFPDH();
									UnsignedByteArray_HEX fpdhData = fpdh.getFPDHData();
									new SystemCache();
									String sfdmStr = SystemCache.sfdmMap.get(fpdhData.get(0).encodeBinary().toString().substring(2));
									new SystemCache();
									String fpdhStr = SystemCache.fpdhMap.get(fpdhData.get(1).encodeBinary().toString().substring(3));
									hphmStr = sfdmStr + fpdhStr + hphmStr;
									fzjgStr = sfdmStr + fpdhStr;
									if (hphmStr.equals(writeHphm)) {
										System.out.println("数据对比正确，hphm:" + hphmStr);

										// 对比正确，发起上传结果请�?
										String lsh = (String) cardInfo.get("lsh");
										JSONObject result = updateEri.updateResult(tid, lsh);// 发起http请求
										if (result.get("resultId").equals("00")) {
											tidMap.remove(tid);
										} else {
											System.out.println("上传更新结果返回Err!!");
										}
									}

								} else {
									System.out.println("写完后未读到信息");
								}

							}

						}
					}
				}

				/*
				 * ReadDataInfo tagData = selectSpecResult.getReadDataInfo();
				 * CID cartId = tagData.getCID(); if(tagData.getHPHMXH()!=null){
				 * HPHMXH hphm = tagData.getHPHMXH(); UnsignedByteArray_HEX
				 * HPHMXHData = hphm.getHPHMXHData(); String hphmStr = "";
				 * String fzjgStr = ""; for(int j =0;j<HPHMXHData.size();j++){
				 * hphmStr +=bit2Hphm(HPHMXHData.get(j).encodeBinary()); }
				 * 
				 * FPDH fpdh = tagData.getFPDH(); UnsignedByteArray_HEX fpdhData
				 * = fpdh.getFPDHData(); String sfdmStr = new
				 * SystemCache().sfdmMap
				 * .get(fpdhData.get(0).encodeBinary().toString().substring(2));
				 * String fpdhStr = new
				 * SystemCache().fpdhMap.get(fpdhData.get(1)
				 * .encodeBinary().toString().substring(3)); hphmStr = sfdmStr
				 * +fpdhStr+hphmStr; fzjgStr = sfdmStr +fpdhStr;
				 * 
				 * 
				 * System.out.println("==============hphmStr:"+hphmStr);
				 * System.out.print("----fzjg:"+fzjgStr);
				 * System.out.println("==============txnum:"+txnum);
				 * System.out.print("----tid:"+Tid.toString());
				 * if(!tidMap.containsKey(Tid.toString())){
				 * tidMap.put(Tid.toString(), hphmStr+"##"+txnum+"##"+1); }else{
				 * int count =
				 * Integer.valueOf(tidMap.get(Tid.toString()).split("##"
				 * )[2].toString())+1; tidMap.put(Tid.toString(),
				 * hphmStr+"##"+txnum+"##"+count); } }else{
				 * if(!tidMap.containsKey(Tid.toString())){
				 * tidMap.put(Tid.toString(), "null##"+txnum+"##"+1); }else{ int
				 * count =
				 * Integer.valueOf(tidMap.get(Tid.toString()).split("##")
				 * [2].toString())+1; tidMap.put(Tid.toString(),
				 * "null##"+txnum+"##"+count); } }
				 * System.out.println("==========tidMap.size():"+tidMap.size());
				 */
			}

		}
	}

	@Override
	public void errorOccured(String message) {

	}

	public static String bit2Hphm(LLRPBitList bit) {
		new SystemCache();
		return SystemCache.hphmMap.get(bit.toString().substring(2));
	}

	public void writeEri(JSONObject info) {

		System.out.println("=============writeEri");

		try {
			DeleteSelectSpec del = new DeleteSelectSpec();
			del.setSelectSpecID(new UnsignedInteger(120));
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
				memoryBank.setCount(new UnsignedShort(9));
				memoryBank.setPointer(new UnsignedShort(4));
				HbSpecMemoryBankIDType hbSpecMemoryBankIDType = new HbSpecMemoryBankIDType();
				hbSpecMemoryBankIDType.set(0);
				memoryBank.setMemoryBankID(hbSpecMemoryBankIDType);
				HbBankType bankType = new HbBankType();
				HbReadType readType = new HbReadType();
				// readType.set("As_Lenth");
				readType.set("AS_Type");
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
				UnsignedInteger code1 = repAdd.getStatus().getStatusCode();

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
				AccessCommand accessCommand = new AccessCommand();
				HbMatchSpec hbMatchSpec = new HbMatchSpec();
				List<HbTargetTag> hbTargetTagList = new LinkedList<HbTargetTag>();

				HbTargetTag matchSpec = new HbTargetTag();
				matchSpec.setMemoryType(new HbTargetTagMemoryType(0));
				matchSpec.setMatchType(new HbTargetTagMatchType(0));
				matchSpec.setPointer(new UnsignedShort(0)); // 偏移�?
				BitArray bitsTagMask = new BitArray();
				for (int i = 0; i < 64; i++) {
					bitsTagMask.add(new Bit(1));
				}
				BitArray bitsTagData = new BitArray();
				// [232, 129, 0, 10, 24, 228, 197, 99] [232, 129, 0, 1, 137, 96,
				// 79, 213] E881000A18E4C563 E881000189604FD5
				String TidStr = info.getString("tid"); // "E88100011CDAED2A";
				UnsignedByteArray_HEX Tid = new UnsignedByteArray_HEX();
				for (int i = 0; i < TidStr.length() / 2; i++) {
					Tid.add(new UnsignedByte(Integer.parseInt(TidStr.substring(i * 2, i * 2 + 2), 16)));
				}

				LLRPBitList bit = new LLRPBitList();
				for (int j = 0; j < Tid.size(); j++) {
					bit.append(Tid.get(j).encodeBinary());
				}
				int num = Tid.encodeBinary().length();
				for (int i = 0; i < bit.length(); i++) {
					bitsTagData.add(new Bit(bit.get(i)));
				}

				matchSpec.setTagData(bitsTagData);// FFFFF
				matchSpec.setTagMask(bitsTagMask);
				hbTargetTagList.add(matchSpec);
				hbMatchSpec.setHbTargetTagList(hbTargetTagList);
				accessCommand.setMatchSpec(hbMatchSpec);
				HbPrivateWriteSpec hbPrivateWriteSpec = new HbPrivateWriteSpec();
				hbPrivateWriteSpec.setOpSpecID(new UnsignedShort(0));
				hbPrivateWriteSpec.setWriteType(new UnsignedByte(1));
				// String strHEX =
				// "02002E170690A1377DD9D511B56A0635C72C6A9464B0B9527583326EAB8271D1B359875D667D64B91172FF3BEC359AD680C6C02C252CBDA6C5BBE2CDC799B321EBB7CE289FE74434C1AB46BD70A732E348327EC4AA28C6ACC4F3DCBD24A47045DA260E5CF21218A6417B0DD4AEAC43C04413E88100011CDAED2AB8B80573F21BE85B275AA967BE72C3F23BD71F6D913F02EACE9F59736A6965913BD71F6D913F02EACE9F59736A6965915A0130040A7F716D954970A683C1E70CD489936DF5A058DE2600";
				// strHEX = strHEX
				// +"9AFC3C014D9DB3324F2581D4F3E311CDA5EA05F65E4C93A9E01A44F75D63942406A9E2A95E74EFC4BA5CA3E841DDDA929CAAE0B6ACC33876DB610A475ABE7DAC";
				String strHEX = info.getString("frame") + info.getString("sign");
				UnsignedShortArray_HEX unsignedShortArray_HEX = new UnsignedShortArray_HEX();
				for (int i = 0; i < strHEX.length() / 4; i++) {
					unsignedShortArray_HEX.add(new UnsignedShort(Integer.parseInt(strHEX.substring(i * 4, i * 4 + 4), 16)));
				}
				hbPrivateWriteSpec.setData(unsignedShortArray_HEX);
				HbReadSpec hbReadSpec = new HbReadSpec();
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
				UnsignedInteger codeAck1 = repAddAccess.getStatus().getStatusCode();
				System.out.println(codeAck1);

				EnableAccessSpec enableAccessSpec = new EnableAccessSpec();
				enableAccessSpec.setAccessSpecID(new UnsignedInteger(120));
				EnableAccessSpecAck enableAccessSpecAck = new EnableAccessSpecAck();
				enableAccessSpecAck = (EnableAccessSpecAck) connection.transact(enableAccessSpec);
				UnsignedInteger codeenableAccessSpecAck = enableAccessSpecAck.getStatus().getStatusCode();
				System.out.println(codeenableAccessSpecAck);

				EnableSelectSpec enableSelectSpec = new EnableSelectSpec();
				enableSelectSpec.setSelectSpecID(new UnsignedInteger(120));
				EnableSelectSpecAck enableSelectSpecAck = new EnableSelectSpecAck();
				enableSelectSpecAck = (EnableSelectSpecAck) connection.transact(enableSelectSpec);
				;
				System.out.println(enableSelectSpecAck.getStatus().getStatusCode());

				StartSelectSpec startSelectSpec = new StartSelectSpec();
				startSelectSpec.setSelectSpecID(new UnsignedInteger(120));
				StartSelectSpecAck startSelectSpecAck = new StartSelectSpecAck();
				startSelectSpecAck = (StartSelectSpecAck) connection.transact(LLRPMessageFactory.createLLRPMessage(startSelectSpec.encodeBinary()));
				System.out.println(startSelectSpecAck.getStatus().getStatusCode());
			}
		} catch (Exception e) {

			System.out.println("=============writeEri Err!");
		}

	}

}
