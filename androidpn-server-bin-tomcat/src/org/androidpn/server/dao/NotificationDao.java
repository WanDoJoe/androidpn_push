package org.androidpn.server.dao;

import java.util.List;

import org.androidpn.server.model.Notification;

public interface NotificationDao {
	public void saveNotification(Notification notification);
	public List<Notification> findNotificationsByUserName(String userName);
	public void deleteNotifaction(Notification notification);
	
	public void deleteNotifactionByUUID(String uuid);

}
