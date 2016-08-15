package org.androidpn.client;

import org.jivesoftware.smack.packet.IQ;

import android.util.Log;
/**
 *	推送消息回执 以确定更好的推送消息收到质量
 * @author sinosoft_wan
 *
 */
public class DeliverConfirmIQ extends IQ {
	
	private String uuid;
	
	@Override
	public String getChildElementXML() {
		 StringBuilder buf = new StringBuilder();
	        buf.append("<").append("deliverconfirm").append(" xmlns=\"").append(
	                "androidpn:iq:deliverconfirm").append("\">");
	        if (uuid != null) {
	        	Log.e("DeliverConfirmIQ", uuid);
	            buf.append("<uuid>").append(uuid).append("</uuid>");
	        }
	        buf.append("</").append("deliverconfirm").append("> ");
	        
	        return buf.toString();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
