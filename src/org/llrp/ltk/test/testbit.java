package org.llrp.ltk.test;

import org.llrp.ltk.net.SystemCache;
import org.llrp.ltk.types.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 *wuweihong
 *2017-4-11
 */
public class testbit {
	public static void main(String[] args) throws Exception {
//		String strHEX = "020089C520D07E57B40E8E00AFE1097945980909B393" +
//				"54F36C001011B6195C98660E3A8AB832FA028AC19625EE2" +
//				"114D82C4D7056181FB842164DAC7571995E2329D88DD2B1" +
//				"5E96C00D070AEA3E3B4F3808455CAA20E69113494AC0545E" +
//				"C5DEA885C7218FF5D2112B4FEC67EB56928B430EEAE8820000449208C64A" +
//				"27DBBE7021F02C6B748B9459D9EE30330F483B5752FE3C117D5B5039E85F5533" +
//				"0F483B5752FE3C117D5B5039E85F555A0130040A6FECADE5645A7AB1520F5FADF2266E4E0B2045D300";
//		strHEX = strHEX +"81FEA5421CB939E44DE65E835972D300157C2BFE0075507A9FD1F13C168ECB384F43A3271920E0944FF138314A21E28282A840333467BD8A757EB989DA44A891";
//		UnsignedShortArray_HEX unsignedShortArray_HEX = new UnsignedShortArray_HEX();
//
//		 String str = "0123456789ABCDEF";
//		    char[] hexs = strHEX.toCharArray();
//		    byte[] bytes = new byte[strHEX.length() / 2];
//		    int n;
//		    int b = Integer.parseInt("C5", 16);
//		    for (int i = 0; i < strHEX.length()/2; i++)
//		    {
////		        n = str.indexOf(hexs[2 * i]) * 16;
////		        n += str.indexOf(hexs[2 * i + 1]);
//		        unsignedShortArray_HEX.add(new UnsignedShort(Integer.parseInt(strHEX.substring(i*2, i*2+2), 16)));
////		        bytes[i] = (byte) (n & 0xff);
//		    }
//
//		unsignedShortArray_HEX.add(new UnsignedShort(0x02));
//		for(int i =0;i<str.length();i++){
//			unsignedShortArray_HEX.add(new UnsignedShort(89));
//		}



        UnsignedShortArray_HEX unsignedShortArray_HEX = new UnsignedShortArray_HEX();
        unsignedShortArray_HEX.add(new UnsignedShort(5120));
        unsignedShortArray_HEX.add(new UnsignedShort(2229));
        unsignedShortArray_HEX.add(new UnsignedShort(35137));
        unsignedShortArray_HEX.add(new UnsignedShort(34884));
        unsignedShortArray_HEX.add(new UnsignedShort(14394));
        unsignedShortArray_HEX.add(new UnsignedShort(8249));
        unsignedShortArray_HEX.add(new UnsignedShort(40584));
        unsignedShortArray_HEX.add(new UnsignedShort(41391));
        unsignedShortArray_HEX.add(new UnsignedShort(49763));
        unsignedShortArray_HEX.add(new UnsignedShort(4166));
        System.out.print(unsignedShortArray_HEX.encodeBinary());
        LLRPBitList data = new LLRPBitList();
        for(int m =0;m<unsignedShortArray_HEX.size();m++){
            data.append(unsignedShortArray_HEX.get(m).encodeBinary());
        }
        new UnsignedShort(data.subList(7,30));
        //上半区
        LLRPBitList cardType = data.subList(0,1);
        LLRPBitList sf = data.subList(1,6);
        LLRPBitList num =data.subList(7,30);
        LLRPBitList sfdm = data.subList(37,6);
        LLRPBitList fpdh = data.subList(43,5);
        LLRPBitList syxz = data.subList(48,4);
        LLRPBitList ccrq = data.subList(52,9);
        LLRPBitList cllx = data.subList(61,9);
        LLRPBitList glorpl = data.subList(70,1);
        //下半区
        LLRPBitList hpzl = data.subList(80,4);
        LLRPBitList hphmXh = data.subList(84,32);
        LLRPBitList jyyxq = data.subList(116,9);
        LLRPBitList qzbfq = data.subList(125,5);
        LLRPBitList hdzkorzzl = data.subList(130,10);
        LLRPBitList csys = data.subList(140,4);
        LLRPBitList plgl = data.subList(144,8);

        String fdxh = SystemCache.sfxhMap.get(new UnsignedInteger(sf).toString());
        String khNum = String.format("%010d", new UnsignedInteger(num).toInteger());
        String khStr = fdxh +khNum;
        String fpdhStr = new SystemCache().fpdhMap.get(fpdh.toString());
        String sfdmStr = new SystemCache().sfdmMap.get(sfdm.toString());
        String ccrqStr  = ChangeData(new SignedShort(ccrq).toInteger(),"199001");
        String syxzStr = new SystemCache().syxzMap.get(syxz.toString());
        String cllxStr = new SystemCache().cllxMap.get(cllx.toString());
        String hpzlStr = new SystemCache().hpzlMap.get(hpzl.toString());
        int value = new UnsignedInteger(hphmXh).intValue();
        int hphmLength = 0;
        int result = value;
        String hphm ="";
        if(value < Math.pow(36, 6) + Math.pow(36, 5) + Math.pow(36, 4)){
            if (value < Math.pow(36, 4)) {
                hphmLength = 4;
            } else if (value < Math.pow(36, 5) + Math.pow(36, 4)) {
                hphmLength = 5;
                result = result - (new Double(Math.pow(36, 4))).intValue();
            } else {
                hphmLength = 6;
                result = result - (new Double(Math.pow(36, 4))).intValue() - (new Double(Math.pow(36, 5))).intValue();
            }
        }
        double remainder = 0;
        do {
            remainder = Integer.parseInt(String.valueOf(result % 36));
            result = Integer.parseInt(String.valueOf(result / 36));
            if (remainder < 26) {
                hphm = (char)(65 + remainder) + hphm;
            } else {
                hphm = (char)(48 + remainder - 26) + hphm;
            }
        } while (result > 0);
        int l = hphm.length();
        for ( int i = 0; i < hphmLength - l; i++) {
            hphm = "A" + hphm;
        }
        String hmhmStr = sfdmStr+fpdhStr +hphm;
        String jyyxqString = ChangeData(new SignedShort(jyyxq).toInteger(),"201301");
        String qzbfqString = ChangeData(new SignedShort(qzbfq).toInteger()*12,jyyxqString);
        int zlzz = new SignedShort(hdzkorzzl).intValue();
        String csysStr = new SystemCache().csysMap.get(csys.toString());

        int plOrGl = new SignedShort(glorpl).intValue();
        int plglNum = new SignedShort(plgl).intValue();
        if (plOrGl == 0) {
            String pl = plglNum / 10.0 + "升";
        } else {
            String gl = plglNum+ "千瓦";
        }
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
