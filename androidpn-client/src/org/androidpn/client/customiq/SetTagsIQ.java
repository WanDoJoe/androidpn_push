package org.androidpn.client.customiq;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.packet.IQ;

import android.util.Log;

public class SetTagsIQ extends IQ {
	private String username;
	private List<String> tagList=new ArrayList<String>();
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public List<String> getTagList() {
		return tagList;
	}



	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}



	@Override
	public String getChildElementXML() {
		 StringBuilder buf = new StringBuilder();
	        buf.append("<").append("settags").append(" xmlns=\"").append(
	                "androidpn:iq:settags").append("\">");
	        
//	        Log.e("SetAliasIQ","alias:"+alias+"-username:"+username);
	        if (username != null) {
	            buf.append("<username>").append(username).append("</username>");
	        }
	        System.out.println("settags::::::::"+tagList.size());
	        if(tagList!=null&&!tagList.isEmpty()){
	        	buf.append("<tags>");
	        	boolean needSeperate=false;//是不是需要一个分隔符
	        	for (String tags:tagList) {
	        		if(needSeperate){
	        			buf.append(",");
	        		}
	        		buf.append(tags);
	        		needSeperate=true;
				}
	        	buf.append("</tags>");
	        }
	        buf.append("</").append("settags").append("> ");
	        System.out.println(buf.toString());
	        return buf.toString();
	}

}
