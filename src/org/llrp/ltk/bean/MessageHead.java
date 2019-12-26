package org.llrp.ltk.bean;

/**
 * Created by Joey on 2015/11/27.
 */
public class MessageHead {

    private String messageId;
    private String messageDeviceSN;
    
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageDeviceSN() {
		return messageDeviceSN;
	}
	public void setMessageDeviceSN(String messageDeviceSN) {
		this.messageDeviceSN = messageDeviceSN;
	}

}