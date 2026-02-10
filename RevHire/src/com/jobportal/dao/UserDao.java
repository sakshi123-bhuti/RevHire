package com.jobportal.dao;

import com.jobportal.dto.User;

public interface UserDao {

    // 1️⃣ Register user
    boolean registerUser(User user);

    // 2️⃣ Login user
    User login(String email, String password);

    // 3️⃣ Get user by email
    User getUserByEmail(String email);

    // 4️⃣ Update password (forgot password flow)
    boolean updatePassword(int userId, String newPassword);

    // 5️⃣ Verify security question & answer
    boolean verifySecurityAnswer(String email, String question, String answer);
}