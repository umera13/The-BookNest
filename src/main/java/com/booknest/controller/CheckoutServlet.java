package com.booknest.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.booknest.daoimpl.CartDAOImpl;
import com.booknest.daoimpl.ProductDAOImpl;
import com.booknest.model.Cart;
import com.booknest.model.CartItem;
import com.booknest.model.Product;
import com.booknest.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartDAOImpl cartDAO;
    private ProductDAOImpl productDAO;

    @Override
    public void init() throws ServletException {

        cartDAO = new CartDAOImpl();

        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
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

        request.setAttribute(
                "cartItems",
                cartItems);

        request.setAttribute(
                "totalAmount",
                totalAmount);

        request.setAttribute(
                "user",
                user);

        request.getRequestDispatcher(
                "/WEB-INF/views/checkout.jsp")
                .forward(request, response);
    }
}