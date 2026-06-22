package com.booknest.dao;

import java.util.List;

import com.booknest.model.User;

public interface UserDAO {

    boolean registerUser(User user);

    User loginUser(String email, String password);

    User getUserById(int userId);

    User getUserByEmail(String email);

    User getUserByPhone(String phone);

    boolean updateUser(User user);

    boolean updatePassword(int userId, String newPassword);

    boolean emailExists(String email);

    boolean phoneExists(String phone);

    List<User> getAllUsers();

	boolean deleteUser(int userId);

	boolean updateAddress(int userId, String addressLine1, String addressLine2, String city, String state,
			String postalCode, String country);

	boolean addUser(User user);
}