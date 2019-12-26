package org.llrp.ltk.net;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.crypto.Data;

import org.apache.log4j.Logger;
import org.llrp.ltk.generated.interfaces.AccessSpecResult;
import org.llrp.ltk.generated.messages.TagSelectAccessReport;
import org.llrp.ltk.generated.parameters.AntennaID;
import org.llrp.ltk.generated.parameters.CID;
import org.llrp.ltk.generated.parameters.CustomizedSelectSpecResult;
import org.llrp.ltk.generated.parameters.FPDH;
import org.llrp.ltk.generated.parameters.FirstSeenTimestampUTC;
import org.llrp.ltk.generated.parameters.GenaralSelectSpecResult;
import org.llrp.ltk.generated.parameters.HPHMXH;
import org.llrp.ltk.generated.parameters.HPZL;
import org.llrp.ltk.generated.parameters.HbCustomizedReadSpecResult;
import org.llrp.ltk.generated.parameters.HbPrivateWriteSpecResult;
import org.llrp.ltk.generated.parameters.HbWriteSpecResult;
import org.llrp.ltk.generated.parameters.LastSeenTimestampUTC;
import org.llrp.ltk.generated.parameters.ReadDataInfo;
import org.llrp.ltk.generated.parameters.RfSpecID;
import org.llrp.ltk.generated.parameters.SelectSpecID;
import org.llrp.ltk.generated.parameters.SpecIndex;
import org.llrp.ltk.generated.parameters.TagReportData;
import org.llrp.ltk.generated.parameters.UTCTimestamp;
import org.llrp.ltk.test.SocketClient;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.UnsignedByteArray_HEX;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedLong;
import org.llrp.ltk.types.UnsignedLong_DATETIME;
import org.llrp.ltk.types.UnsignedShortArray_HEX;
import org.llrp.ltk.util.Constants;

/*
 *wuweihong
 *2016-4-18
 */
public class TableProgramMessage implements LLRPEndpoint{
	private static final Logger log = Logger.getLogger(TableProgramMessage.class);
	public static ConcurrentHashMap<String,String> tidMap = new ConcurrentHashMap<String, String>();
	public static int mm = 0;
	@Override
	public void errorOccured(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageReceived(LLRPMessage message) {
		// TODO Auto-generated method stub
		if (message instanceof TagSelectAccessReport) {
			TagSelectAccessReport tagSelectAccessReport = (TagSelectAccessReport)message;
			List<TagReportData> tagReportDataList = tagSelectAccessReport.getTagReportDataList();
			for(int i = 0 ;i<tagReportDataList.size();i++){
				TagReportData tag = tagReportDataList.get(i);
				
				if(!tag.getAccessSpecResultList().isEmpty()){
					List<AccessSpecResult> list= tag.getAccessSpecResultList();
					for(int m = 0;m<list.size();m++){
						if(list.get(m) instanceof HbCustomizedReadSpecResult){
							HbCustomizedReadSpecResult hbCustomizedReadSpecResult = (HbCustomizedReadSpecResult)list.get(m);
							hbCustomizedReadSpecResult.getResult();
						}
						if(list.get(m) instanceof HbPrivateWriteSpecResult){
							HbPrivateWriteSpecResult hbPrivateWriteSpecResult = (HbPrivateWriteSpecResult)list.get(m);
							UnsignedByteArray_HEX resultDescription = hbPrivateWriteSpecResult.getResultDescription();
							System.out.println(resultDescription.toString());
						}
						if(list.get(m) instanceof HbWriteSpecResult){
							HbWriteSpecResult hbWriteSpecResult = (HbWriteSpecResult)list.get(m);
						}
					}
				}
				
				AntennaID tx = tag.getAntennaID();
//				String txnum ="1";
				String txnum = tx.getAntennaID().toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				UnsignedByteArray_HEX Tid = tag.getTID();
				CustomizedSelectSpecResult selectSpecResult = (CustomizedSelectSpecResult) tag.getSelectSpecResult();
				ReadDataInfo tagData = selectSpecResult.getReadDataInfo();
				CID cartId = tagData.getCID();
				String countStr = tag.getTagSeenCount().getTagCount()+"";
				if(tagData.getHPHMXH()!=null){
					HPHMXH hphm = tagData.getHPHMXH();
					UnsignedByteArray_HEX HPHMXHData = hphm.getHPHMXHData();
					String hphmStr = "";
					String fzjgStr = "";
					for(int j =0;j<HPHMXHData.size();j++){
						hphmStr +=bit2Hphm(HPHMXHData.get(j).encodeBinary());
					}
					FPDH fpdh = tagData.getFPDH();
					UnsignedByteArray_HEX fpdhData = fpdh.getFPDHData();
					String sfdmStr = new SystemCache().sfdmMap.get(fpdhData.get(0).encodeBinary().toString().substring(2));
					String fpdhStr = new SystemCache().fpdhMap.get(fpdhData.get(1).encodeBinary().toString().substring(3));
					hphmStr = sfdmStr +fpdhStr+hphmStr;
					fzjgStr = sfdmStr +fpdhStr;
					if(!tidMap.containsKey(Tid.toString())){
						tidMap.put(Tid.toString(), hphmStr+"##"+txnum+"##"+1+"##"+countStr);
					}else{
						int count = Integer.valueOf(tidMap.get(Tid.toString()).split("##")[2].toString())+1;
						tidMap.put(Tid.toString(), hphmStr+"##"+txnum+"##"+count+"##"+countStr);
					}
				}else{
					if(!tidMap.containsKey(Tid.toString())){
						tidMap.put(Tid.toString(), "null##"+txnum+"##"+1+"##"+countStr);
					}else{
						int count = Integer.valueOf(tidMap.get(Tid.toString()).split("##")[2].toString())+1;
						tidMap.put(Tid.toString(), "null##"+txnum+"##"+count+"##"+countStr);
					}
				}

			}
			
		}
	}
	
	 public static LLRPBitList ByteArraytoBitBack(UnsignedByteArray_HEX Data)
	    {
		 LLRPBitList bit = new LLRPBitList();
			for(int j = Data.size()-1;j>-1;j--){
				bit.append(Data.get(j).encodeBinary());
			}
			return bit;
	    }
	 /**
	  * 调用Socket拍照
	  * @param tid
	  */
	 public static void setTidtoCache(UnsignedByteArray_HEX tid)
	    {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 if(!CacheMap.getDefault().containsKey(tid.toString())){
			 try{
//				 SocketClient.getSocket(); 
				 CacheMap.getDefault().put(tid.toString(),sdf.format(new Date()));
			 }catch (Exception e) {
				log.info("调用socket："+e.getMessage());
			}
			 
		 }
	    }
	 
	 public static LLRPBitList ByteArraytoBit(UnsignedByteArray_HEX Data)
	    {
		 LLRPBitList bit = new LLRPBitList();
			for(int j =0;j<Data.size();j++){
				bit.append(Data.get(j).encodeBinary());
			}
			return bit;
	    }
	 
	 public static String bit2Hphm(LLRPBitList bit)
	    {
		 return new SystemCache().hphmMap.get(bit.toString().substring(2));
	    }
	 public static String toAnyConversion(BigInteger Bigtemp, BigInteger base)
	    {
	        String ans = "";
	        while(Bigtemp.compareTo(BigInteger.ZERO) != 0)
	        {
	            BigInteger temp = Bigtemp.mod(base);
	            Bigtemp = Bigtemp.divide(base);
	            char ch = changToNum(temp);
	            ans = ch + ans;
	        }
	        if(ans.length() < 4){
	        	ans = "A" + ans;	
	        }
	        return ans;
	    }
	    //数字转换为字符
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
	    /**
	     * 距离validatetime的月数，0xFF表示车辆寿命已超出511月（约为42.58年）
	     * @param month
	     * @return
	     * @throws Exception
	     */
	    public static String ChangeData(int month,String validatetime) throws Exception
	    {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			 Date now = sdf.parse(validatetime);
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(now);
			 calendar.add(Calendar.MONTH, month);
			 return sdf.format(calendar.getTime());
	    }


}
