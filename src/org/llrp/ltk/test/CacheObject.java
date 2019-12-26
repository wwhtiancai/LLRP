package org.llrp.ltk.test;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by stone on 2017/4/6.
 */
public class CacheObject {

	private long beginTime;// 缓存�?��时间
	private boolean isForever = false;// 是否持久
	private int durableTime;// 持续时间
	private JSONObject nr;

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public boolean isForever() {
		return isForever;
	}

	public void setForever(boolean forever) {
		isForever = forever;
	}

	public int getDurableTime() {
		return durableTime;
	}

	public void setDurableTime(int durableTime) {
		this.durableTime = durableTime;
	}

	public JSONObject getNr() {
		return nr;
	}

	public void setNr(JSONObject nr) {
		this.nr = nr;
	}
}
