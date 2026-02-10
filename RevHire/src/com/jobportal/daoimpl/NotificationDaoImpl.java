package com.jobportal.daoimpl;



import com.jobportal.dao.NotificationDao;
import com.jobportal.db.DBConnection;
import com.jobportal.dto.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDaoImpl implements NotificationDao {

    private Connection con;

    public NotificationDaoImpl() {
        con = DBConnection.getConnection();
    }

    // 1️⃣ Add notification
    @Override
    public boolean addNotification(Notification notification) {

        String sql = """
            INSERT INTO notifications (user_id, message, is_read, created_at)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, notification.getUserId());
            ps.setString(2, notification.getMessage());
            ps.setBoolean(3, notification.isRead());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(notification.getCreatedAt()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2️⃣ Get notifications by user ID
    @Override
    public List<Notification> getNotificationsByUserId(int userId) {

        List<Notification> list = new ArrayList<>();
        String sql = """
            SELECT * FROM notifications
            WHERE user_id = ?
            ORDER BY created_at DESC
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification n = new Notification();
                n.setId(rs.getInt("id"));
                n.setUserId(rs.getInt("user_id"));
                n.setMessage(rs.getString("message"));
                n.setRead(rs.getBoolean("is_read"));
                n.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                list.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3️⃣ Mark notification as read
    @Override
    public boolean markAsRead(int notificationId) {

        String sql = "UPDATE notifications SET is_read = 1 WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, 1);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Delete notification
    @Override
    public boolean deleteNotification(int notificationId) {

        String sql = "DELETE FROM notifications WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, notificationId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
