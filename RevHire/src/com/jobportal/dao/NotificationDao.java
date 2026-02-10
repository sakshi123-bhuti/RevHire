package com.jobportal.dao;

import com.jobportal.dto.Notification;
import java.util.List;

public interface NotificationDao {

    // 1️⃣ Add notification
    boolean addNotification(Notification notification);

    // 2️⃣ Get notifications for a user
    List<Notification> getNotificationsByUserId(int userId);

    // 3️⃣ Mark notification as read
    boolean markAsRead(int notificationId);

    // 4️⃣ Delete notification
    boolean deleteNotification(int notificationId);
}