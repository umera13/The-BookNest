package com.booknest.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booknest.dao.OrderItemDAO;
import com.booknest.model.OrderItem;
import com.booknest.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    private Connection connection;

    public OrderItemDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {

        boolean status = false;

        String query = "INSERT INTO order_items(order_id, product_id, quantity, price) VALUES(?,?,?,?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderItem.getOrderId());
            preparedStatement.setInt(2, orderItem.getProductId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setBigDecimal(4, orderItem.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean addOrderItems(List<OrderItem> orderItems) {

        boolean status = false;

        String query = "INSERT INTO order_items(order_id, product_id, quantity, price) VALUES(?,?,?,?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (OrderItem orderItem : orderItems) {

                preparedStatement.setInt(1, orderItem.getOrderId());
                preparedStatement.setInt(2, orderItem.getProductId());
                preparedStatement.setInt(3, orderItem.getQuantity());
                preparedStatement.setBigDecimal(4, orderItem.getPrice());

                preparedStatement.addBatch();
            }

            int[] rows = preparedStatement.executeBatch();

            status = rows.length > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {

        boolean status = false;

        String query = "UPDATE order_items SET quantity=?, price=? WHERE order_item_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderItem.getQuantity());
            preparedStatement.setBigDecimal(2, orderItem.getPrice());
            preparedStatement.setInt(3, orderItem.getOrderItemId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {

        boolean status = false;

        String query = "DELETE FROM order_items WHERE order_item_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderItemId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {

        OrderItem orderItem = null;

        String query = "SELECT * FROM order_items WHERE order_item_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderItemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                orderItem = new OrderItem();

                orderItem.setOrderItemId(resultSet.getInt("order_item_id"));
                orderItem.setOrderId(resultSet.getInt("order_id"));
                orderItem.setProductId(resultSet.getInt("product_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setPrice(resultSet.getBigDecimal("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> orderItems = new ArrayList<>();

        String query = "SELECT * FROM order_items WHERE order_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                OrderItem orderItem = new OrderItem();

                orderItem.setOrderItemId(resultSet.getInt("order_item_id"));
                orderItem.setOrderId(resultSet.getInt("order_id"));
                orderItem.setProductId(resultSet.getInt("product_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setPrice(resultSet.getBigDecimal("price"));

                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {

        List<OrderItem> orderItems = new ArrayList<>();

        String query = "SELECT * FROM order_items";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                OrderItem orderItem = new OrderItem();

                orderItem.setOrderItemId(resultSet.getInt("order_item_id"));
                orderItem.setOrderId(resultSet.getInt("order_id"));
                orderItem.setProductId(resultSet.getInt("product_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setPrice(resultSet.getBigDecimal("price"));

                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItems;
    }
}