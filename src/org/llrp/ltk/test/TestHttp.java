package org.llrp.ltk.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.generated.LLRPMessageFactory;
import org.llrp.ltk.types.LLRPMessage;

/*
 *wuweihong
 *2016-8-30
 */
public class TestHttp {
	public static void main(String[] args) throws InvalidLLRPMessageException {
		
		String cc = String.format("%09d", 64);
		System.out.println(cc);
		String firsttimeStr = "9367/04/10 04:39:59";
		int a  = firsttimeStr.indexOf("/");
		boolean flag= isValidDate(firsttimeStr);
		String msgStr= "000000000000000001012C0000000000000064";
		byte[] msg =hexStringToByte(msgStr);
		LLRPMessage message = LLRPMessageFactory.createLLRPMessage(msg);
		System.out.println("====");
//		msg[0] = msgStr.getBytes("0");
	}
	 /**
	   * 把16进制字符串转换成字节数组
	   * @param hexString
	   * @return byte[]
	   */
	  public static byte[] hexStringToByte(String hex) {
	   int len = (hex.length() / 2);
	   byte[] result = new byte[len];
	   char[] achar = hex.toCharArray();
	   for (int i = 0; i < len; i++) {
	    int pos = i * 2;
	    result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
	   }
	   return result;
	  }
	  private static int toByte(char c) {
		    byte b = (byte) "0123456789ABCDEF".indexOf(c);
		    return b;
		 }
	  
	  public static boolean isValidDate(String str) {
	      boolean convertSuccess=true;
	       SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	       try {
	          format.setLenient(false);
	          format.parse(str);
	       } catch (ParseException e) {
	           convertSuccess=false;
	       } 
	       return convertSuccess;
	}
}
