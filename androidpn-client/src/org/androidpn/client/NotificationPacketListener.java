/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.client;

import org.androidpn.client.model.NotificationHistory;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;

import android.content.Intent;
import android.util.Log;

/** 
 * This class notifies the receiver of incoming notifcation packets asynchronously.  
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class NotificationPacketListener implements PacketListener {

    private static final String LOGTAG = LogUtil
            .makeLogTag(NotificationPacketListener.class);

    private final XmppManager xmppManager;

    public NotificationPacketListener(XmppManager xmppManager) {
        this.xmppManager = xmppManager;
    }

    @Override
    public void processPacket(Packet packet) {
        Log.d(LOGTAG, "NotificationPacketListener.processPacket()...");
        Log.d(LOGTAG, "packet.toXML()=" + packet.toXML());

        if (packet instanceof NotificationIQ) {
            NotificationIQ notification = (NotificationIQ) packet;

            if (notification.getChildElementXML().contains(
                    "androidpn:iq:notification")) {
                String notificationId = notification.getId();
                String notificationApiKey = notification.getApiKey();
                String notificationTitle = notification.getTitle();
                String notificationMessage = notification.getMessage();
                //                String notificationTicker = notification.getTicker();
                String notificationUri = notification.getUri();
                String notificationTime=notification.getTime();
                String notificationImageUrl=notification.getImageUrl();
//                Log.e("notificationTime", notificationTime);
                setNotificationHistoryLitep(notificationId, notificationApiKey, notificationTitle, 
                		notificationMessage, notificationUri, notificationTime, notificationImageUrl);
                Intent intent = new Intent(Constants.ACTION_SHOW_NOTIFICATION);
                intent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                intent.putExtra(Constants.NOTIFICATION_API_KEY,
                        notificationApiKey);
                intent
                        .putExtra(Constants.NOTIFICATION_TITLE,
                                notificationTitle);
                intent.putExtra(Constants.NOTIFICATION_MESSAGE,
                        notificationMessage);
                intent.putExtra(Constants.NOTIFICATION_URI, notificationUri);
                intent.putExtra(Constants.NOTIFICATION_TIMA,notificationTime);
                intent.putExtra(Constants.NOTIFICATION_IMAGEURL, notificationImageUrl);
                
                //                intent.setData(Uri.parse((new StringBuilder(
                //                        "notif://notification.androidpn.org/")).append(
                //                        notificationApiKey).append("/").append(
                //                        System.currentTimeMillis()).toString()));

                xmppManager.getContext().sendBroadcast(intent);
                //消息回执 告知服务器已经收到推送 可以删除数据库中该条id
                Log.e("notificationId", "notificationId:"+notificationId);
                Log.e("notificationId", "notificationId:"+notificationTime);
                Log.e("notificationId", "notificationId:"+notificationImageUrl);
                
                DeliverConfirmIQ confirmIQ =new DeliverConfirmIQ();
                confirmIQ.setUuid(notificationId); 
                confirmIQ.setType(IQ.Type.SET);
                xmppManager.getConnection().sendPacket(confirmIQ);
                Log.e("消息回执", "notificationId:"+notificationId);
            }
        }

    }

   private void setNotificationHistoryLitep(String notificationId,String notificationApiKey
    		,String notificationTitle, String notificationMessage,String notificationUri
    		,String notificationTime,String notificationImageUrl){
	   
	  NotificationHistory history=new NotificationHistory();
	  history.setApiKey(notificationApiKey);
	  history.setImageUrl(notificationImageUrl);
	  history.setMessage(notificationMessage);
	  history.setTime(notificationTime);
	  history.setTitle(notificationTitle);
	  history.setUri(notificationUri);
	  
	  history.save();
	   
   }
}
