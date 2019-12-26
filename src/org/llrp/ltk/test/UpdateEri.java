package org.llrp.ltk.test;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by stone on 2017/3/30.
 */
public class UpdateEri {

	public static void main(String[] args) throws Exception {
		String tid = "E8820000449208C6";
		String aqmkxh = "RA0200201413";

		UpdateEri updateEri = new UpdateEri("10.2.43.234", "8080", "admin", "passw0rd", aqmkxh);
		JSONObject result = updateEri.getUpdateInfo(tid);// 发起http请求

		if (result.get("resultId").equals("00")) {
			String lsh = (String) result.get("lsh");
			JSONObject updateResult = updateEri.updateResult(tid, lsh);// 发起http请求
		} else {
			System.out.println("获取更新信息返回Err!!");
		}
	}

	public String ip;
	public String port;
	public String yhdh;
	public String mm;
	public String aqmkxh;
	
	public UpdateEri(String ip, String port, String yhdh, String mm, String aqmkxh) {
		this.ip = ip;
		this.port = port;
		this.yhdh = yhdh;
		this.mm = mm;
		this.aqmkxh = aqmkxh;
	}

	/**
	 * http获取更新信息
	 * 
	 * @param tid
	 * @return
	 */
	public JSONObject getUpdateInfo(String tid) {
		String token = "";
		JSONObject result = new JSONObject();
		String resultStr = "";
		String loginUrl = "http://" + ip + ":" + port + "/rmweb/be/login.rfid?yhdh=" + yhdh + "&mm=" + mm + "&aqmkxh=" + aqmkxh;
		System.out.println("=====loginUrl:"+loginUrl);
		JSONObject jo = new JSONObject();
		JSONObject loginResult = MyHttpUtil.httpPost12(loginUrl, jo);
		if (loginResult.get("resultId").equals("00")) {
			token = (String) loginResult.get("token");
			String getInfoUrl = "http://" + ip + ":" + port + "/rmweb/be/customize-task.rfid?method=fetch-data-with-tid&token=" + token + "&tid="
					+ tid + "&aqmkxh=" + aqmkxh;
			JSONObject jo2 = new JSONObject();
//			System.out.println("====getInfoUrl："+getInfoUrl);
			result = MyHttpUtil.httpPost12(getInfoUrl, jo2);

		} else {
			System.out.println("登录返回Err!!");
		}

		return result;
	}

	public JSONObject updateResult(String tid, String lsh) {
		String token = "";
		JSONObject result = new JSONObject();
		String loginUrl = "http://" + ip + ":" + port + "/rmweb/be/login.rfid?yhdh=" + yhdh + "&mm=" + mm + "&aqmkxh=" + aqmkxh;
		JSONObject jo = new JSONObject();
		JSONObject loginResult = MyHttpUtil.httpPost12(loginUrl, jo);
		if (loginResult.get("resultId").equals("00")) {
			token = (String) loginResult.get("token");
			String updateResultUrl = "http://" + ip + ":" + port + "/rmweb/be/eri.rfid?method=customize-result&lsh=" + lsh + "&result=1&sbyy&tid="
					+ tid + "&token=" + token;

			result = MyHttpUtil.httpGet(updateResultUrl);
		} else {
			System.out.println("登录返回Err!!");
		}
		return result;
	}

	public String getCert(String aqmkxh) {
		String token = "";
		JSONObject result = new JSONObject();
		String cert = "";
		String loginUrl = "http://" + ip + ":" + port + "/rmweb/be/login.rfid?yhdh=" + yhdh + "&mm=" + mm + "&aqmkxh=" + aqmkxh;
		JSONObject jo = new JSONObject();
		JSONObject loginResult = MyHttpUtil.httpPost12(loginUrl, jo);
		if (loginResult.get("resultId").equals("00")) {
			token = (String) loginResult.get("token");
			String getInfoUrl = "http://" + ip + ":" + port + "/rmweb/be/security-model.rfid?method=query-cret-model&token=" + token + "&ssztbh="
					+ aqmkxh;
			result = MyHttpUtil.httpGet(getInfoUrl);
			if (result.get("resultId").equals("00")) {
				cert = result.getString("cert");
			} else {
				System.out.println("获取证书内容失败"+ result.getString("resultMsg"));
			}
		} else {
			System.out.println("登录返回Err!!");
		}

		return cert;
	}

}
