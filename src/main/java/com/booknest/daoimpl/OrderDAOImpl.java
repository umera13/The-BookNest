package com.booknest.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booknest.dao.OrderDAO;
import com.booknest.model.Order;
import com.booknest.model.OrderItem;
import com.booknest.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;

    public OrderDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public int placeOrder(Order order) {

        int orderId = 0;

        String query = "INSERT INTO orders(user_id, delivery_name, delivery_phone, " +
                       "delivery_address_line1, delivery_address_line2, delivery_city, " +
                       "delivery_state, delivery_postal_code, delivery_country, " +
                       "total_amount, payment_method, order_status) " +
                       "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getDeliveryName());
            preparedStatement.setString(3, order.getDeliveryPhone());
            preparedStatement.setString(4, order.getDeliveryAddressLine1());
            preparedStatement.setString(5, order.getDeliveryAddressLine2());
            preparedStatement.setString(6, order.getDeliveryCity());
            preparedStatement.setString(7, order.getDeliveryState());
            preparedStatement.setString(8, order.getDeliveryPostalCode());
            preparedStatement.setString(9, order.getDeliveryCountry());
            preparedStatement.setBigDecimal(10, order.getTotalAmount());
            preparedStatement.setString(11, order.getPaymentMethod());
            preparedStatement.setString(12, order.getOrderStatus());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    orderId = resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String orderStatus) {

        boolean status = false;

        String query = "UPDATE orders SET order_status=? WHERE order_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, orderStatus);
            preparedStatement.setInt(2, orderId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean cancelOrder(int orderId) {

        boolean status = false;

        String query = "UPDATE orders SET order_status='Cancelled' WHERE order_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteOrder(int orderId) {

        boolean status = false;

        String query = "DELETE FROM orders WHERE order_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public Order getOrderById(int orderId) {

        Order order = null;

        String query = "SELECT * FROM orders WHERE order_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                order = new Order();

                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setDeliveryName(resultSet.getString("delivery_name"));
                order.setDeliveryPhone(resultSet.getString("delivery_phone"));
                order.setDeliveryAddressLine1(resultSet.getString("delivery_address_line1"));
                order.setDeliveryAddressLine2(resultSet.getString("delivery_address_line2"));
                order.setDeliveryCity(resultSet.getString("delivery_city"));
                order.setDeliveryState(resultSet.getString("delivery_state"));
                order.setDeliveryPostalCode(resultSet.getString("delivery_postal_code"));
                order.setDeliveryCountry(resultSet.getString("delivery_country"));
                order.setTotalAmount(resultSet.getBigDecimal("total_amount"));
                order.setPaymentMethod(resultSet.getString("payment_method"));
                order.setOrderStatus(resultSet.getString("order_status"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Order order = new Order();

                order.setOrderId(
                        resultSet.getInt("order_id"));

                order.setUserId(
                        resultSet.getInt("user_id"));

                order.setDeliveryName(
                        resultSet.getString("delivery_name"));

                order.setDeliveryPhone(
                        resultSet.getString("delivery_phone"));

                order.setDeliveryAddressLine1(
                        resultSet.getString(
                                "delivery_address_line1"));

                order.setDeliveryAddressLine2(
                        resultSet.getString(
                                "delivery_address_line2"));

                order.setDeliveryCity(
                        resultSet.getString(
                                "delivery_city"));

                order.setDeliveryState(
                        resultSet.getString(
                                "delivery_state"));

                order.setDeliveryPostalCode(
                        resultSet.getString(
                                "delivery_postal_code"));

                order.setDeliveryCountry(
                        resultSet.getString(
                                "delivery_country"));

                order.setTotalAmount(
                        resultSet.getBigDecimal(
                                "total_amount"));

                order.setPaymentMethod(
                        resultSet.getString(
                                "payment_method"));

                order.setOrderStatus(
                        resultSet.getString(
                                "order_status"));

                order.setOrderDate(
                        resultSet.getTimestamp(
                                "order_date"));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM orders ORDER BY order_date DESC";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Order order = new Order();

                order.setOrderId(
                        resultSet.getInt("order_id"));

                order.setUserId(
                        resultSet.getInt("user_id"));

                order.setDeliveryName(
                        resultSet.getString("delivery_name"));

                order.setDeliveryPhone(
                        resultSet.getString("delivery_phone"));

                order.setDeliveryAddressLine1(
                        resultSet.getString(
                                "delivery_address_line1"));

                order.setDeliveryAddressLine2(
                        resultSet.getString(
                                "delivery_address_line2"));

                order.setDeliveryCity(
                        resultSet.getString(
                                "delivery_city"));

                order.setDeliveryState(
                        resultSet.getString(
                                "delivery_state"));

                order.setDeliveryPostalCode(
                        resultSet.getString(
                                "delivery_postal_code"));

                order.setDeliveryCountry(
                        resultSet.getString(
                                "delivery_country"));

                order.setTotalAmount(
                        resultSet.getBigDecimal(
                                "total_amount"));

                order.setPaymentMethod(
                        resultSet.getString(
                                "payment_method"));

                order.setOrderStatus(
                        resultSet.getString(
                                "order_status"));

                order.setOrderDate(
                        resultSet.getTimestamp(
                                "order_date"));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public int getOrderCountByUserId(int userId) {

        int count = 0;

        String query = "SELECT COUNT(*) FROM orders WHERE user_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public double getOrderTotalAmount(int orderId) {

        double totalAmount = 0;

        String query = "SELECT total_amount FROM orders WHERE order_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalAmount = resultSet.getDouble("total_amount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalAmount;
    }

    @Override
    public int createOrder(Order order) {

        int orderId = 0;

        String query =
                "INSERT INTO orders "
                + "(user_id, delivery_name, delivery_phone, "
                + "delivery_address_line1, delivery_address_line2, "
                + "delivery_city, delivery_state, "
                + "delivery_postal_code, delivery_country, "
                + "total_amount, payment_method, order_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query,
                            PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(
                    1,
                    order.getUserId());

            preparedStatement.setString(
                    2,
                    order.getDeliveryName());

            preparedStatement.setString(
                    3,
                    order.getDeliveryPhone());

            preparedStatement.setString(
                    4,
                    order.getDeliveryAddressLine1());

            preparedStatement.setString(
                    5,
                    order.getDeliveryAddressLine2());

            preparedStatement.setString(
                    6,
                    order.getDeliveryCity());

            preparedStatement.setString(
                    7,
                    order.getDeliveryState());

            preparedStatement.setString(
                    8,
                    order.getDeliveryPostalCode());

            preparedStatement.setString(
                    9,
                    order.getDeliveryCountry());

            preparedStatement.setBigDecimal(
                    10,
                    order.getTotalAmount());

            preparedStatement.setString(
                    11,
                    order.getPaymentMethod());

            preparedStatement.setString(
                    12,
                    "Placed");

            int rows =
                    preparedStatement.executeUpdate();

            if(rows > 0){

                ResultSet generatedKeys =
                        preparedStatement.getGeneratedKeys();

                if(generatedKeys.next()){

                    orderId =
                            generatedKeys.getInt(1);
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {

        String query =
                "INSERT INTO order_items "
                + "(order_id, product_id, quantity, price) "
                + "VALUES (?, ?, ?, ?)";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(
                    1,
                    orderItem.getOrderId());

            preparedStatement.setInt(
                    2,
                    orderItem.getProductId());

            preparedStatement.setInt(
                    3,
                    orderItem.getQuantity());

            preparedStatement.setBigDecimal(
                    4,
                    orderItem.getPrice());

            preparedStatement.executeUpdate();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}