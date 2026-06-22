package com.booknest.util;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try {

            Connection connection = DBConnection.getConnection();

            if (connection != null) {
                System.out.println("Connection Successful");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}