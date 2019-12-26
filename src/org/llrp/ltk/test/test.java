package org.llrp.ltk.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.generated.messages.TagSelectAccessReport;
import org.llrp.ltk.generated.parameters.CID;
import org.llrp.ltk.generated.parameters.CustomizedSelectSpecResult;
import org.llrp.ltk.generated.parameters.FPDH;
import org.llrp.ltk.generated.parameters.HPHMXH;
import org.llrp.ltk.generated.parameters.ReadDataInfo;
import org.llrp.ltk.generated.parameters.TagReportData;
import org.llrp.ltk.mongo.MongoDB2Oracle;
import org.llrp.ltk.net.CacheMap;
import org.llrp.ltk.net.DbConnection;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedByteArray_HEX;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedLong;

/*
 *wuweihong
 *2016-4-27
 */
public class test {
	public static void main(String[] args) throws Exception {
		//CF 3D 00 80 02
//		UnsignedByteArray_HEX cIDData = new UnsignedByteArray_HEX(5);
//		cIDData.set(0, new UnsignedByte(0x0e));
//		cIDData.set(1, new UnsignedByte(0x84));
//		cIDData.set(2, new UnsignedByte(0x09));
//		cIDData.set(3, new UnsignedByte(0x80));
//		cIDData.set(4, new UnsignedByte(0x02));
//		LLRPBitList Cidbit = ByteArraytoBitBack(cIDData);
//		LLRPBitList sfdm = Cidbit.subList(3, 7);
//		LLRPBitList kh = Cidbit.subList(10, 30);
//		new UnsignedInteger(kh).toInteger();
//		org.jdom.Document doc = new org.jdom.input.SAXBuilder().build(new FileReader(System.getProperty("user.dir") + "/test.xml"));
//		LLRPMessage addSelectSpecMsg = LLRPMessageFactory.createLLRPMessage(doc);
//		String cc = "00 00 00 00 00 00 00 00 01 01 F4 00 00 00 BD 6D FA 14 24 01 F4 00 B9 00 08 E8 82 00 00 28 57 02 30 01 F5 00 04 00 00 00 FA 01 F6 00 02 00 01 01 F7 00 02 00 64 01 F8 00 02 00 01 01 F9 00 01 00 01 FA 00 08 00 00 00 00 00 00 00 00 01 FB 00 08 00 00 00 00 00 00 00 00 01 FC 00 02 00 01 01 FD 00 04 00 00 00 00 02 05 00 66 00 02 04 00 61 88 01 00 07 00 05 02 00 00 40 08 88 02 00 04 00 02 13 01 88 03 00 03 00 01 04 88 04 00 04 00 02 35 01 88 05 00 04 00 02 91 00 88 06 00 03 00 01 3C 88 08 00 03 00 01 01 88 09 00 07 00 05 1A 1B 00 00 01 88 0A 00 04 00 02 2E 00 88 0B 00 03 00 01 0E 88 0D 00 03 00 01 00 88 0C 00 04 00 02 45 00";
//		Map cc1 = new HashMap();
//		cc1.put("0", "0");
//		cc1.put("1", "1");
//		System.out.println(cc1.toString());
		
//		String cc = "53 48 00 00 00 00 00 00 01 01 F4 00 00 00 AE 00 00 00 00 01 F4 00 AA 00 08 E8 81 00 0A 1D 8C 54 66 01 F5 00 04 00 00 00 78 01 F7 00 02 00 64 01 F8 00 01 04 01 F9 00 01 F1 01 FA 00 08 4E FF A2 2F 00 00 03 A5 01 FB 00 08 4E FF A2 2F 00 00 03 A5 01 FC 00 02 00 00 02 05 00 66 00 02 04 00 61 03 E9 00 07 00 05 01 00 00 40 08 03 EA 00 04 00 02 13 01 03 EB 00 03 00 01 04 03 EC 00 04 00 02 35 01 03 ED 00 04 00 02 91 00 03 EE 00 03 00 01 3C 03 F0 00 03 00 01 01 03 F1 00 07 00 05 1A 1B 00 00 00 03 F2 00 04 00 02 2E 00 03 F3 00 03 00 01 0E 03 F4 00 04 00 02 45 00 03 F5 00 03 00 01 00";
		String cc = "00 00 00 00 00 00 00 00 01 01 90 00 00 00 60 00 00 00 05 01 90 00 5C 00 00 00 FA 00 00 00 01 91 00 0D 00 01 92 00 08 00 00 00 00 00 00 00 C8 01 94 00 05 00 00 00 00 00 01 95 00 2E 00 01 01 01 96 00 14 03 00 00 00 C8 01 97 00 0B 00 00 05 00 0A 00 00 00 00 27 10 01 98 00 0F 00 FA 00 01 01 99 00 07 00 01 00 04 00 0A 02 01 9A 00 05 02 00 01 FF 80";
//		String cc = "4b 43 30 31 32 33 34 35 01 01 f4 00 00 00 b5 00 00 00 00 01 f4 00 b1 00 08 e8 81 00 0a 1c 22 56 d0 01 f5 00 04 00 00 00 01 01 f6 00 02 00 00 01 f7 00 02 00 01 01 f8 00 01 01 01 f9 00 01 00 01 fa 00 08 73 0c 27 00 00 00 00 00 01 fb 00 08 73 0c 27 00 00 00 00 00 01 fc 00 02 00 01 02 05 00 67 00 02 04 00 62 03 e9 00 07 00 05 01 00 00 40 08 03 ea 00 04 00 02 13 01 03 eb 00 03 00 01 04 03 ec 00 04 00 02 35 01 03 ed 00 04 00 02 91 00 03 ef 00 03 00 01 3c 03 f0 00 03 00 01 01 03 f1 00 07 00 05 1a 1b 00 00 00 03 f2 00 04 00 02 2e 00 03 f3 00 03 00 01 0e 03 f5 00 04 00 02 45 00 03 f4 00 04 00 02 00 45";
		String ccc = cc.replace(" ", "");
		byte[] aa = new byte[ccc.length()/2];
//		for(int i =0;i<cc.split(" ").length;i++){
//			aa[i]=(byte) Integer.parseInt(cc.split(" ")[i]);
//		}
		LinkedList<UnsignedByte> tempList = new LinkedList<UnsignedByte>();
		String bb = cc.replace(" ", "");
		int length = bb.length();
		UnsignedByte[] bytes;
		for (int i = 0; i < length; i = i + 2) {
			String temp = bb.substring(i, i+2);
			Integer ti = Integer.decode("0x" + temp);
			aa[i/2]= (byte) Integer.parseInt(ti+"");
			tempList.add(new UnsignedByte(ti));
		}
		UnsignedByte[] bs = new UnsignedByte[tempList.size()];
		bytes = tempList.toArray(bs);
		LLRPMessage message1 = LLRPMessageFactory.createLLRPMessage(aa);
		TagSelectAccessReport tagSelectAccessReport = (TagSelectAccessReport)message1;
		List<TagReportData> tagReportDataList = tagSelectAccessReport.getTagReportDataList();
		for(int i = 0 ;i<tagReportDataList.size();i++){
			TagReportData tag = tagReportDataList.get(i);
			
			CustomizedSelectSpecResult selectSpecResult = (CustomizedSelectSpecResult) tag.getSelectSpecResult();
			ReadDataInfo tagData = selectSpecResult.getReadDataInfo();
			
			CID cartId = tagData.getCID();
			UnsignedByteArray_HEX CIDData = cartId.getCIDData();
			LLRPBitList Cidbit = ByteArraytoBitBack(CIDData);
			LLRPBitList sfdm = Cidbit.subList(3, 7);
			LLRPBitList kh = Cidbit.subList(10, 30);
			String fdxh = SystemCache.sfxhMap.get(new UnsignedInteger(sfdm).toString());
		FPDH fpdh = tagData.getFPDH();
		UnsignedByteArray_HEX fpdhData = fpdh.getFPDHData();
		String sfdmStr = new SystemCache().sfdmMap.get(fpdhData.get(0).encodeBinary().toString().substring(2));
		String fpdhStr = new SystemCache().fpdhMap.get(fpdhData.get(1).encodeBinary().toString().substring(3));
		
		String csys = SystemCache.csysMap.get(tagData.getCSYS().getCSYSData().get(0).encodeBinary().toString().substring(4));
		}
		MongoDB2Oracle mongoDB2Oracle = new MongoDB2Oracle();
		mongoDB2Oracle.Msg2Oracle(message1);
		System.out.println(aa);
	} 

	public static boolean isNumeric(String str){ //函数判断是否是数字
		for (int i = str.length();--i>=0;){ 
		if (!Character.isDigit(str.charAt(i))){
		return false;
		}
		}
		return true;
		}
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
	static void memcache(){
		CacheMap.getDefault().put("aa", "bb");
		System.out.println();
		CacheMap.getDefault().get("aa");
	}
	
    public static void toAnyConversion(BigInteger Bigtemp, BigInteger base)
    {
        String ans = "";
        while(Bigtemp.compareTo(BigInteger.ZERO) != 0)
        {
            BigInteger temp = Bigtemp.mod(base);
            Bigtemp = Bigtemp.divide(base);
            char ch = changToNum(temp);
            ans = ch + ans;
        }
        //return ans;
       System.out.println(ans);
    }
    //数字转换为字符
    static char changToNum(BigInteger temp)
    {
        int n = temp.intValue();

        if(n >= 0 && n <= 25)
            return (char) (n  + 'A');

        else if(n >= 25 && n <= 35)
            return (char)(n +22);

        else
            return (char)(n + '0');
    }
	 public static LLRPBitList ByteArraytoBitBack(UnsignedByteArray_HEX Data)
	    {
		 LLRPBitList bit = new LLRPBitList();
			for(int j = Data.size()-1;j>-1;j--){
				bit.append(Data.get(j).encodeBinary());
			}
			return bit;
	    }
}
