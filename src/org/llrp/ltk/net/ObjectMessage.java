package org.llrp.ltk.net;

import org.apache.log4j.Logger;
import org.llrp.ltk.generated.messages.DeviceEventNotification;
import org.llrp.ltk.generated.messages.TagSelectAccessReport;
import org.llrp.ltk.generated.parameters.*;
import org.llrp.ltk.redis.Publisher;
import org.llrp.ltk.types.*;
import org.llrp.ltk.util.Constants;
import org.llrp.ltk.util.MDBManager;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 *wuweihong
 *2016-4-18
 */
public class ObjectMessage implements LLRPEndpoint{
	private static final Logger log = Logger.getLogger(ObjectMessage.class);
	private static int mm = 0;
	private static MDBManager mdb = MDBManager.getInstance();
	@Override
	public void errorOccured(String message) {
		System.out.print(message);
		log.error(message);
		
	}

	@Override
	public void messageReceived(LLRPMessage message) {
		// TODO Auto-generated method stub
		
		if (message instanceof DeviceEventNotification) {
			DeviceEventNotification deviceEventNotification = (DeviceEventNotification)message;
			String sbxh = message.getDevicesn().toString();
			String sbxhHex = binaryString2hexString(sbxh);
			System.out.println(sbxhHex);
		}
		if (message instanceof TagSelectAccessReport) {
			Publisher publisher = new Publisher();
			publisher.start(message);
			TagSelectAccessReport tagSelectAccessReport = (TagSelectAccessReport)message;
			List<TagReportData> tagReportDataList = tagSelectAccessReport.getTagReportDataList();
			String sbxh = message.getDevicesn().toString();
			String sbxhHex = binaryString2hexString(sbxh);
			for(int i = 0 ;i<tagReportDataList.size();i++){
				TagReportData tag = tagReportDataList.get(i);
				String count = tag.getTagSeenCount().getTagCount()+"";
				AntennaID tx = tag.getAntennaID();
				String txnum = tx.getAntennaID().toString();
				FirstSeenTimestampUTC firsttime = tag.getFirstSeenTimestampUTC();
				LastSeenTimestampUTC lstime = tag.getLastSeenTimestampUTC();
				Date firstdat = new Date(firsttime.getMicroseconds().toLong()/1000);
				Date lstdat = new Date(lstime.getMicroseconds().toLong()/1000);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
//				System.out.println(sdf1.format(firstdat));
				String firsttimeStr = sdf.format(firstdat);
				String lasttimeStr = sdf.format(lstdat);
				UnsignedByteArray_HEX Tid = tag.getTID();
				SelectSpecID selectSpecID = tag.getSelectSpecID();
				RfSpecID rfSpecID = tag.getRfSpecID();
				AntennaID antennaID = tag.getAntennaID();
				SpecIndex specIndex = tag.getSpecIndex();
				if(tag.getSelectSpecResult() instanceof GenaralSelectSpecResult){
					GenaralSelectSpecResult selectSpecResult = (GenaralSelectSpecResult)tag.getSelectSpecResult();
					UnsignedShortArray_HEX tagData = selectSpecResult.getTagData();
					String tagDataStr = tagData.toString();
					try {
						Connection conn = mdb.getConnection();
						String sql = "insert into RFID_LLRP_READ_user2 (XH, SBXH, TAGDATA, TID, SCDQSJ, ZHDQSJ,TXCS"+txnum+")"+
						"values (SEQ_rfid_llrp_read_zte.NEXTVAL, '"+sbxhHex+"','"+tagDataStr+"','"+Tid.toString()+"',to_date('"+sdf.format(new Date())+"','YYYY/MM/DD HH24:MI:SS')"+",to_date('"+sdf.format(new Date())+"','YYYY/MM/DD HH24:MI:SS') , '"
						+count+"')";
						PreparedStatement ps = conn.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();
						ps.close();
						rs.close();
						conn.close();
					}catch (Exception e) {
						// TODO: handle exception
						log.error(e.getMessage());
					}
					
				}else if(tag.getSelectSpecResult() instanceof CustomizedSelectSpecResult){
					CustomizedSelectSpecResult selectSpecResult = (CustomizedSelectSpecResult)tag.getSelectSpecResult();
					ReadDataInfo tagData = selectSpecResult.getReadDataInfo();
//					LLRPBitList bit = new LLRPBitList();
//					for(int j = 0;j<tagData.size();j++){
//						bit.append(tagData.get(j).encodeBinary());
//					}
					CID cartId = tagData.getCID();
					UnsignedByteArray_HEX CIDData = cartId.getCIDData();
					LLRPBitList Cidbit = ByteArraytoBitBack(CIDData);
					LLRPBitList sfdm = Cidbit.subList(3, 7);
					LLRPBitList kh = Cidbit.subList(10, 30);
					String fdxh = SystemCache.sfxhMap.get(new UnsignedInteger(sfdm).toString());
		            String khNum = String.format("%010d", new UnsignedInteger(kh).toInteger());
		            String khStr = fdxh +khNum;
//					System.out.println(new UnsignedInteger(kh).toInteger());
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
					HPZL hpzl = tagData.getHPZL();
					log.info("HPZL编码："+hpzl.getHPZLData().get(0).encodeBinary().toString());
					String hpzlStr = new SystemCache().hpzlMap.get(hpzl.getHPZLData().get(0).encodeBinary().toString().substring(4));
					LLRPBitList CcrqBit = new LLRPBitList();
					CcrqBit.append(tagData.getCCRQ().getCCRQData().get(1).encodeBinary());
					CcrqBit.append(tagData.getCCRQ().getCCRQData().get(0).encodeBinary());
					String ccrq ="";
					try {
						ccrq = ChangeData(new SignedShort(CcrqBit).toInteger(),"199001");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						log.error("出厂日期解析错误！");
					}
					String syxz = SystemCache.syxzMap.get(tagData.getSYXZ().getSYXZData().get(0).encodeBinary().toString().substring(4));
					LLRPBitList CllxBit = new LLRPBitList();
					CllxBit.append(tagData.getCLLX().getCLLXData().get(1).encodeBinary());
					CllxBit.append(tagData.getCLLX().getCLLXData().get(0).encodeBinary());
					String cllx = SystemCache.cllxMap.get(CllxBit.toString().substring(7));
					String gl = "";
					if(tagData.getGL()!=null){
						gl = tagData.getGL().getGLData().get(0).toString();
					}else{
						log.info("功率为空");
					}
					String pl = "";
					if(tagData.getPL()!=null){
						pl = tagData.getPL().getPLData().get(0).toString();
//						log.info("排量："+tagData.getPL().getPLData().get(0));
					}else{
						log.info("排量为空");
					}
					LLRPBitList JyyxqBit = new LLRPBitList();
					JyyxqBit.append(tagData.getJYYXQ().getJYYXQData().get(1).encodeBinary());
					JyyxqBit.append(tagData.getJYYXQ().getJYYXQData().get(0).encodeBinary());
					String jyyxqString = "";
					try {
						jyyxqString = ChangeData(new SignedShort(JyyxqBit).toInteger(),"201301");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						log.error("检验有效期解析错误！");
					}
					String qzbfqString = "";
					try {
						qzbfqString = ChangeData(new SignedShort(tagData.getQZBFQ().getQZBFQData().get(0).encodeBinary()).toInteger()*12,jyyxqString);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						log.error("强制报废期解析错误！");
					}
					String csys = SystemCache.csysMap.get(tagData.getCSYS().getCSYSData().get(0).encodeBinary().toString().substring(4));
					String zkzl = "";
					if(tagData.getZKZL()!=null){
						LLRPBitList ZkzlqBit = new LLRPBitList();
						ZkzlqBit.append(tagData.getZKZL().getZKZLData().get(1).encodeBinary());
						ZkzlqBit.append(tagData.getZKZL().getZKZLData().get(0).encodeBinary());
						zkzl = new SignedShort(ZkzlqBit)+"";
					}else{
						log.info("载客载质量为空");
					}
					try {
						DbConnection db = new DbConnection();
						if(db.savetflag.equals(Constants.savetotxt)){
							log.debug("Tid :" +Tid.toString());
							log.info("天线编号："+txnum);
//							log.info("FirstSeenTimestampUTC" + firsttime.toString());
//							log.info("LastSeenTimestampUTC" + lstime.toString());
							log.info("省份："+sfdmStr);
							log.info("hphmStr："+hphmStr);
							log.info("fzjgStr："+fzjgStr);
							log.info("hpzlStr："+hpzlStr);
							log.info("CIDData："+CIDData.toString());
							log.info("使用性质："+SystemCache.syxzMap.get(tagData.getSYXZ().getSYXZData().get(0).encodeBinary().toString().substring(4)));
//							tagData.getGL().getGLData().get(0);
							if(tagData.getGL()!=null){
								log.info("功率："+tagData.getGL().getGLData().get(0));
							}else{
								log.info("功率为空");
							}
							if(tagData.getPL()!=null){
								log.info("排量："+tagData.getPL().getPLData().get(0));
							}else{
								log.info("排量为空");
							}
//							LLRPBitList JyyxqBit = new LLRPBitList();
//							JyyxqBit.append(tagData.getJYYXQ().getJYYXQData().get(1).encodeBinary());
//							JyyxqBit.append(tagData.getJYYXQ().getJYYXQData().get(0).encodeBinary());
//							String jyyxqString = ChangeData(new SignedShort(JyyxqBit).toInteger(),"201301");
							log.info("检验有效期："+jyyxqString);
							log.info("强制报废期："+ChangeData(new SignedShort(tagData.getQZBFQ().getQZBFQData().get(0).encodeBinary()).toInteger()*12,jyyxqString));
//							LLRPBitList ZkzlqBit = new LLRPBitList();
//							ZkzlqBit.append(tagData.getZKZL().getZKZLData().get(1).encodeBinary());
//							ZkzlqBit.append(tagData.getZKZL().getZKZLData().get(0).encodeBinary());
//							log.info("核定载客载质量："+new SignedShort(ZkzlqBit));
							log.info("车身颜色:"+SystemCache.csysMap.get(tagData.getCSYS().getCSYSData().get(0).encodeBinary().toString().substring(4)));
						}else{
							Connection conn = mdb.getConnection();
							String sql = "insert into rfid_llrp_read_zte (XH, SBXH, TID, KH, SCDQSJ, ZHDQSJ, HPHM, HPZL, FZJG, CCRQ,SYXZ,CLLX,PL,GL,JYYXQ,QZBFQ,CSYS,ZKZL,TXCS"+txnum+")"+
							"values (SEQ_rfid_llrp_read_zte.NEXTVAL, '"+sbxhHex+"','"+Tid.toString()+"','"+khStr+"',to_date('"+sdf.format(new Date())+"','YYYY/MM/DD HH24:MI:SS')"+",to_date('"+sdf.format(new Date())+"','YYYY/MM/DD HH24:MI:SS') ,'"+hphmStr+"', '"+ hpzlStr
							+"', '"+fzjgStr +"',to_date('"+ccrq+"','YYYY/MM'),'"+syxz+"','"+cllx+"','"+pl+"','"+gl+"',to_date('"+jyyxqString+"','YYYY/MM'),to_date('"+qzbfqString+"','YYYY/MM'),'"+csys+"','"+zkzl+"','"+count+"')";
							PreparedStatement ps = conn.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							ps.close();
							rs.close();
							conn.close();
//							log.info(sql);
//							db.update(sql);	
//							db.coles();
						}
//						setTidtoCache(Tid);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage());
					}  
				}
//				CustomizedSelectSpecResult selectSpecResult = (CustomizedSelectSpecResult) tag.getSelectSpecResult();
				
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
