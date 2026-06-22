package com.booknest.dao;

import java.util.List;

import com.booknest.model.Order;
import com.booknest.model.OrderItem;

public interface OrderDAO {

    int placeOrder(Order order);

    boolean updateOrderStatus(int orderId, String orderStatus);

    boolean cancelOrder(int orderId);

    boolean deleteOrder(int orderId);

    Order getOrderById(int orderId);

    List<Order> getOrdersByUserId(int userId);

    List<Order> getAllOrders();

    int getOrderCountByUserId(int userId);

    double getOrderTotalAmount(int orderId);

	void addOrderItem(OrderItem orderItem);

	int createOrder(Order order);
}