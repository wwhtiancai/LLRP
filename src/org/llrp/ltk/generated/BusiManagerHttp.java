package org.llrp.ltk.generated;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.generated.messages.DeviceEventNotification;
import org.llrp.ltk.generated.messages.TagSelectAccessReport;
import org.llrp.ltk.generated.parameters.AntennaID;
import org.llrp.ltk.generated.parameters.CID;
import org.llrp.ltk.generated.parameters.CustomizedSelectSpecResult;
import org.llrp.ltk.generated.parameters.FPDH;
import org.llrp.ltk.generated.parameters.FirstSeenTimestampUTC;
import org.llrp.ltk.generated.parameters.HPHMXH;
import org.llrp.ltk.generated.parameters.HPZL;
import org.llrp.ltk.generated.parameters.LastSeenTimestampUTC;
import org.llrp.ltk.generated.parameters.ReadDataInfo;
import org.llrp.ltk.generated.parameters.RfSpecID;
import org.llrp.ltk.generated.parameters.SelectSpecID;
import org.llrp.ltk.generated.parameters.SpecIndex;
import org.llrp.ltk.generated.parameters.TagReportData;
import org.llrp.ltk.net.DbConnection;
import org.llrp.ltk.net.ObjectMessage;
import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.types.LLRPBitList;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.SignedShort;
import org.llrp.ltk.types.UnsignedByte;
import org.llrp.ltk.types.UnsignedByteArray_HEX;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.util.Constants;
import org.llrp.ltk.util.MDBManager;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BusiManagerHttp {
	private static final Logger log = Logger.getLogger(BusiManagerHttp.class);
	private static MDBManager mdb = MDBManager.getInstance();
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket ss=new ServerSocket(8080);

            while(true){
                Socket socket=ss.accept();
                BufferedReader bd=new BufferedReader(new InputStreamReader(socket.getInputStream(),"ISO-8859-1"));
                
                /**
                 * 接受HTTP请求
                 */
                String requestHeader;
                int contentLength=0;
                while((requestHeader=bd.readLine())!=null&&!requestHeader.isEmpty()){
                    System.out.println(requestHeader);
                    /**
                     * 获得GET参数
                     */
                    if(requestHeader.startsWith("GET")){
                        int begin = requestHeader.indexOf("/?")+2;
                        int end = requestHeader.indexOf("HTTP/");
                        String condition=requestHeader.substring(begin, end);
                        System.out.println("GET参数是："+condition);
                    }
//                    if(requestHeader.startsWith("POST")){
//                        int begin = requestHeader.indexOf("/?")+2;
//                        int end = requestHeader.indexOf("HTTP/");
//                        String condition=requestHeader.substring(begin, end);
//                        System.out.println("POST参数是："+condition);
//                    }
//                    /**
//                     * 获得POST参数
//                     * 1.获取请求内容长度
//                     */
                    if(requestHeader.startsWith("Content-Length")){
                        int begin=requestHeader.indexOf("Content-Length:")+"Content-Length:".length();
                        String postParamterLength=requestHeader.substring(begin).trim();
                        contentLength=Integer.parseInt(postParamterLength);
                        System.out.println("POST参数长度是："+Integer.parseInt(postParamterLength));
                    }
                }
                StringBuffer sb=new StringBuffer();
//                byte[] sb = new byte[contentLength];
                if(contentLength>0){
                    for (int i = 0; i < contentLength; i++) {
//                    	sb[i] = (byte)bd.read();
//                    	System.out.println("str"+i+"=="+bd.read());
                        sb.append((char)bd.read());
                    }
//                    String msg = "00000000000000000101F4000000BD6DFA142401F400B90008E88200002857023001F50004000000FA01F60002000101F70002006401F80002000101F900010001FA0008000000000000000001FB0008000000000000000001FC0002000101FD00040000000002050066000204006103E900070005020000400803EA00040002130103EB000300010403EC00040002350103ED00040002910003EE000300013C03F0000300010103F1000700051A1B00000103F2000400022E0003F3000300010E03F4000300010003F5000400024500";
                    BASE64Decoder decoder = new BASE64Decoder(); 
                	byte[] b = decoder.decodeBuffer(sb.toString()); 
                    System.out.println("POST参数是："+sb.toString());
                    LLRPMessage message = LLRPMessageFactory.createLLRPMessage(b);
                }
                //解析msg
//                byte[] aa = new byte[sb.toString().length()/2];
//                int length = sb.toString().length();
//                for (int i = 0; i < length; i = i + 2) {
//        			String temp = sb.substring(i, i+2);
//        			Integer ti = Integer.decode("0x" + temp);
//        			aa[i/2]= (byte) Integer.parseInt(ti+"");
//        		}
//                bytesToHexString(sb);
//                new BASE64Encoder().encode(sb);
//                LLRPMessage message = LLRPMessageFactory.createLLRPMessage(b);
//                messageReceived(message);
                //发送回执
                PrintWriter pw=new PrintWriter(socket.getOutputStream());
                
                pw.println("HTTP/1.1 200 OK");
                pw.println("Content-type:text/html");
                pw.println();
                pw.println("<h1>访问成功！</h1>");
                
                pw.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String bytesToHexString(byte[] src){   
        StringBuilder stringBuilder = new StringBuilder("");   
        if (src == null || src.length <= 0) {   
            return null;   
        }   
        for (int i = 0; i < src.length; i++) {   
            int v = src[i] & 0xFF;   
            String hv = Integer.toHexString(v);   
            if (hv.length() < 2) {   
                stringBuilder.append(0);   
            }   
            stringBuilder.append(hv);   
        }   
        return stringBuilder.toString();   
    }  
    
    public static String getFromBASE64(String s) { 
    	if (s == null) return null; 
    	BASE64Decoder decoder = new BASE64Decoder(); 
    	try { 
    	byte[] b = decoder.decodeBuffer(s); 
    	return new String(b); 
    	} catch (Exception e) { 
    	return null; 
    	} 
    }
    public static void messageReceived(LLRPMessage message) {
		// TODO Auto-generated method stub
		if (message instanceof TagSelectAccessReport) {
//			Publisher publisher = new Publisher();
//			publisher.start(message);
			TagSelectAccessReport tagSelectAccessReport = (TagSelectAccessReport)message;
			List<TagReportData> tagReportDataList = tagSelectAccessReport.getTagReportDataList();
			for(int i = 0 ;i<tagReportDataList.size();i++){
				TagReportData tag = tagReportDataList.get(i);
			
				AntennaID tx = tag.getAntennaID();
				String txnum = tx.getAntennaID().toString();
				FirstSeenTimestampUTC firsttime = tag.getFirstSeenTimestampUTC();
				LastSeenTimestampUTC lstime = tag.getLastSeenTimestampUTC();
				Date firstdat = new Date(firsttime.getMicroseconds().toLong()/1000);
				Date lstdat = new Date(lstime.getMicroseconds().toLong()/1000);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String firsttimeStr = sdf.format(firstdat);
				String lasttimeStr = sdf.format(lstdat);
				UnsignedByteArray_HEX Tid = tag.getTID();
				SelectSpecID selectSpecID = tag.getSelectSpecID();
				RfSpecID rfSpecID = tag.getRfSpecID();
				AntennaID antennaID = tag.getAntennaID();
				SpecIndex specIndex = tag.getSpecIndex();
				CustomizedSelectSpecResult selectSpecResult = (CustomizedSelectSpecResult) tag.getSelectSpecResult();
				ReadDataInfo tagData = selectSpecResult.getReadDataInfo();
//				LLRPBitList bit = new LLRPBitList();
//				for(int j = 0;j<tagData.size();j++){
//					bit.append(tagData.get(j).encodeBinary());
//				}
				CID cartId = tagData.getCID();
				UnsignedByteArray_HEX CIDData = cartId.getCIDData();
				LLRPBitList Cidbit = ByteArraytoBitBack(CIDData);
				LLRPBitList sfdm = Cidbit.subList(3, 7);
				LLRPBitList kh = Cidbit.subList(10, 30);
//				System.out.println(new SignedShort(kh).toInteger());
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
				try {
					DbConnection db = new DbConnection();
					if(db.savetflag.equals(Constants.savetotxt)){
						log.debug("Tid :" +Tid.toString());
						log.info("天线编号："+txnum);
//						log.info("FirstSeenTimestampUTC" + firsttime.toString());
//						log.info("LastSeenTimestampUTC" + lstime.toString());
						log.info("省份："+sfdmStr);
						log.info("hphmStr："+hphmStr);
						log.info("fzjgStr："+fzjgStr);
						log.info("hpzlStr："+hpzlStr);
						log.info("CIDData："+CIDData.toString());
						log.info("使用性质："+SystemCache.syxzMap.get(tagData.getSYXZ().getSYXZData().get(0).encodeBinary().toString().substring(4)));
						LLRPBitList CcrqBit = new LLRPBitList();
						CcrqBit.append(tagData.getCCRQ().getCCRQData().get(1).encodeBinary());
						CcrqBit.append(tagData.getCCRQ().getCCRQData().get(0).encodeBinary());
						log.info("出厂日期："+ChangeData(new SignedShort(CcrqBit).toInteger(),"199001"));
						LLRPBitList CllxBit = new LLRPBitList();
						CllxBit.append(tagData.getCLLX().getCLLXData().get(1).encodeBinary());
						CllxBit.append(tagData.getCLLX().getCLLXData().get(0).encodeBinary());
						log.info("车辆类型："+SystemCache.cllxMap.get(CllxBit.toString().substring(7)));
//						tagData.getGL().getGLData().get(0);
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
						LLRPBitList JyyxqBit = new LLRPBitList();
						JyyxqBit.append(tagData.getJYYXQ().getJYYXQData().get(1).encodeBinary());
						JyyxqBit.append(tagData.getJYYXQ().getJYYXQData().get(0).encodeBinary());
						String jyyxqString = ChangeData(new SignedShort(JyyxqBit).toInteger(),"201301");
						log.info("检验有效期："+jyyxqString);
						log.info("强制报废期："+ChangeData(new SignedShort(tagData.getQZBFQ().getQZBFQData().get(0).encodeBinary()).toInteger()*12,jyyxqString));
//						LLRPBitList ZkzlqBit = new LLRPBitList();
//						ZkzlqBit.append(tagData.getZKZL().getZKZLData().get(1).encodeBinary());
//						ZkzlqBit.append(tagData.getZKZL().getZKZLData().get(0).encodeBinary());
//						log.info("核定载客载质量："+new SignedShort(ZkzlqBit));
						log.info("车身颜色:"+SystemCache.csysMap.get(tagData.getCSYS().getCSYSData().get(0).encodeBinary().toString().substring(4)));
					}else{
						Connection conn = mdb.getConnection();
						String sql = "insert into RFID_LLRP_READ (XH, FIRSTTIME,LASTTIME, HPHM, HPZL, FZJG,TX,TID,kh,qbxx,ip)"+
						"values (SEQ_RFID_LLRP_READ.NEXTVAL, "+"to_date('"+sdf.format(new Date())+"','YYYY/MM/DD HH24:MI:SS')"+",to_date('"+sdf.format(new Date())+"','YYYY/MM/DD HH24:MI:SS') ,'"+hphmStr+"', '"+ hpzlStr
						+"', '"+fzjgStr +"','"+txnum+"','"+Tid.toString()+"','"+new UnsignedInteger(kh).toInteger()+"','"+tag.toString()+"','"+SystemCache.ipAddress+"')";
						PreparedStatement ps = conn.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();
						ps.close();
						rs.close();
						conn.close();
//						log.info(sql);
//						db.update(sql);
//						db.coles();
					}
//					setTidtoCache(Tid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage());
				}  
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
	    
		 public static LLRPBitList ByteArraytoBitBack(UnsignedByteArray_HEX Data)
		    {
			 LLRPBitList bit = new LLRPBitList();
				for(int j = Data.size()-1;j>-1;j--){
					bit.append(Data.get(j).encodeBinary());
				}
				return bit;
		    }
}