/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.androidpn.server.console.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.androidpn.server.util.Config;
import org.androidpn.server.xmpp.push.NotificationManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/** 
 * A controller class to process the notification related requests.  
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class NotificationController extends MultiActionController {

    private NotificationManager notificationManager;

    public NotificationController() {
        notificationManager = new NotificationManager();
    }

    public ModelAndView list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        // mav.addObject("list", null);
        mav.setViewName("notification/form");
        return mav;
    }

    public ModelAndView send(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String broadcast = null ;//= ServletRequestUtils.getStringParameter(request,"broadcast", "Y");
        String username = null ;//= ServletRequestUtils.getStringParameter(request,"username");
        String alias = null;//=ServletRequestUtils.getStringParameter(request, "alias");
        String tag = null;//=ServletRequestUtils.getStringParameter(request, "tag");
        
        
        String title = null;// = ServletRequestUtils.getStringParameter(request, "title");
        String message = null ;//= ServletRequestUtils.getStringParameter(request,"message");
        String uri = null;// = ServletRequestUtils.getStringParameter(request, "uri");
        String imageUrl=null;
        String apiKey = Config.getString("apiKey", "");
        logger.debug("apiKey=" + apiKey);
//        logger.debug("alias:"+alias);
//        logger.debug("title:"+title);
//        logger.debug("message:"+message);
        
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload servletFileUpload=new ServletFileUpload(factory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        for(FileItem item:fileItems){
        	if("broadcast".equals(item.getFieldName())){
        		broadcast=item.getString("utf-8");
        	}else if("username".equals(item.getFieldName())){
        		username=item.getString("utf-8");
        	}else if("alias".equals(item.getFieldName())){
        		alias=item.getString("utf-8");
        	}else if("tag".equals(item.getFieldName())){
        		tag=item.getString("utf-8");
        	}else if("title".equals(item.getFieldName())){
        		title=item.getString("utf-8");
        	}else if("message".equals(item.getFieldName())){
        		message=item.getString("utf-8");
        	}else if("uri".equals(item.getFieldName())){
        		uri=item.getString("utf-8");
        	}else if("image".equals(item.getFieldName())){
        		imageUrl=uploadImage(request, item);
        	}
        }
        
        if (broadcast.equals("0")) {
          notificationManager.sendBroadcast(apiKey, title, message, uri,imageUrl);
      } else  if (broadcast.equals("1")){
          notificationManager.sendNotifcationToUser(apiKey, username, title,message, uri,imageUrl,true);
      }else  if (broadcast.equals("2")){
    	  notificationManager.sendNotificationByAlias(apiKey, alias, title,message, uri,imageUrl, true);
      }else if(broadcast.equals("3")){
    	  notificationManager.sendNoitificationByTag(apiKey, tag, title, message, uri,imageUrl, true);
      }


        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:notification.do");
        return mav;
    }
    private String uploadImage(HttpServletRequest request,FileItem fileItem )throws Exception{
    	String uploadPath=getServletContext().getRealPath("/upload/images");
    	File uploadDir=new File(uploadPath);
    	if(!uploadDir.exists()){
    		uploadDir.mkdirs();//创建文件夹确保有改文件夹
    	}
    	
    	if(fileItem!=null&&fileItem.getContentType().startsWith("image")){
    		String suffix=fileItem.getName().substring(fileItem.getName().indexOf("."));
    		String fileName=System.currentTimeMillis()+suffix;
    		InputStream is = fileItem.getInputStream();
    		FileOutputStream fos=new FileOutputStream(uploadPath+"/"+fileName);
    		byte[] b=new byte[1024];
    		int len=0;
    		while((len=is.read(b))>0){
    			fos.write(b,0,len);
    			fos.flush();
    		}
    		fos.close();
    		is.close();
    		String serverName=request.getServerName();
    		int serverPort=request.getServerPort();
    		String imgUrl="http://192.168.0.139:"+serverPort+"/upload/images/"+fileName;
    		System.out.println(imgUrl);
    		return imgUrl;
    	}
    	
		return "";
	}

    
}
