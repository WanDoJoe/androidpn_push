package org.androidpn.server.service.impl;

import java.util.List;

import org.androidpn.server.dao.NotificationDao;
import org.androidpn.server.model.Notification;
import org.androidpn.server.service.NotificationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NotificationServiceImpl implements NotificationService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private NotificationDao notificationDao;


	public void saveNotification(Notification notification) {
		notificationDao.saveNotification(notification);
	}

	public List<Notification> findNotificationsByUserName(String userName) {
		return notificationDao.findNotificationsByUserName(userName);
	}

	public void deleteNotifaction(Notification notification) {
		notificationDao.deleteNotifaction(notification);
	}
	
	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	public void deleteNotificationByUUID(String uuid) {
		notificationDao.deleteNotifactionByUUID(uuid);
	}

}
