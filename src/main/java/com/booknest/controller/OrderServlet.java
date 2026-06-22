package com.booknest.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.booknest.daoimpl.CartDAOImpl;
import com.booknest.daoimpl.OrderDAOImpl;
import com.booknest.model.Cart;
import com.booknest.model.CartItem;
import com.booknest.model.Order;
import com.booknest.model.OrderItem;
import com.booknest.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAOImpl cartDAO;
    private OrderDAOImpl orderDAO;

    @Override
    public void init() throws ServletException {

        cartDAO = new CartDAOImpl();

        orderDAO = new OrderDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        Cart cart =
                cartDAO.getCartByUserId(
                        user.getUserId());

        if(cart == null){

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        List<CartItem> cartItems =
                cartDAO.getCartItemsByCartId(
                        cart.getCartId());

        if(cartItems == null || cartItems.isEmpty()){

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        BigDecimal totalAmount =
                cartDAO.getCartTotalAmount(
                        cart.getCartId());

        String deliveryName =
                request.getParameter("deliveryName");

        String deliveryPhone =
                request.getParameter("deliveryPhone");
        

        String addressLine1 =
                request.getParameter("addressLine1");

        String addressLine2 =
                request.getParameter("addressLine2");

        String city =
                request.getParameter("city");

        String state =
                request.getParameter("state");

        String postalCode =
                request.getParameter("postalCode");

        String country =
                request.getParameter("country");

        String paymentMethod =
                request.getParameter("paymentMethod");

        Order order =
                new Order();

        order.setUserId(
                user.getUserId());

        order.setDeliveryName(
                deliveryName);

        order.setDeliveryPhone(
                deliveryPhone);

        order.setDeliveryAddressLine1(
                addressLine1);

        order.setDeliveryAddressLine2(
                addressLine2);

        order.setDeliveryCity(
                city);

        order.setDeliveryState(
                state);

        order.setDeliveryPostalCode(
                postalCode);

        order.setDeliveryCountry(
                country);

        order.setPaymentMethod(
                paymentMethod);

        order.setTotalAmount(
                totalAmount);
        order.setOrderStatus("Placed");

        int orderId =
                orderDAO.createOrder(order);

        if(orderId > 0){

            for(CartItem cartItem : cartItems){

                OrderItem orderItem =
                        new OrderItem();

                orderItem.setOrderId(orderId);

                orderItem.setProductId(
                        cartItem.getProductId());

                orderItem.setQuantity(
                        cartItem.getQuantity());

                orderItem.setPrice(
                        cartItem.getPriceAtAddition());

                orderDAO.addOrderItem(orderItem);
            }

            cartDAO.clearCart(
                    cart.getCartId());

            request.setAttribute(
                    "orderId",
                    orderId);

            request.getRequestDispatcher(
                    "/WEB-INF/views/order-success.jsp")
                    .forward(request, response);

        }
        else{

            response.sendRedirect(
                    request.getContextPath() + "/checkout");
        }
    }
}