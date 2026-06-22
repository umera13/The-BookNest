package com.booknest.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

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

        String action =
                request.getParameter("action");

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

        int userId =
                user.getUserId();

        if(action != null && action.equals("add")){

            addToCart(request, response, userId);

            return;
        }

        if(action != null && action.equals("remove")){

            removeFromCart(request, response, userId);

            return;
        }

        if(action != null && action.equals("increase")){

            increaseQuantity(request, response, userId);

            return;
        }

        if(action != null && action.equals("decrease")){

            decreaseQuantity(request, response, userId);

            return;
        }

        viewCart(request, response, userId);
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

        int userId =
                user.getUserId();

        String action =
                request.getParameter("action");

        if("update".equals(action)){

            updateCartItem(request, response, userId);

            return;
        }

        if("clear".equals(action)){

            clearCart(request, response, userId);

            return;
        }

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }

    private void addToCart(HttpServletRequest request,
                           HttpServletResponse response,
                           int userId)
            throws IOException {

        String productIdParam =
                request.getParameter("id");

        String quantityParam =
                request.getParameter("quantity");

        if(productIdParam == null ||
           productIdParam.isEmpty()){

            response.sendRedirect(
                    request.getContextPath() + "/products");

            return;
        }

        int productId =
                Integer.parseInt(productIdParam);

        int quantity = 1;

        if(quantityParam != null &&
           !quantityParam.isEmpty()){

            quantity =
                    Integer.parseInt(quantityParam);
        }

        Product product =
                productDAO.getProductById(productId);

        if(product == null){

            response.sendRedirect(
                    request.getContextPath() + "/products");

            return;
        }

        Cart cart =
                cartDAO.getCartByUserId(userId);

        if(cart == null){

            cart =
                    cartDAO.createCartForUser(userId);
        }

        boolean alreadyInCart =
                cartDAO.isProductAlreadyInCart(
                        cart.getCartId(),
                        productId);

        if(alreadyInCart){

            CartItem existingItem =
                    cartDAO.getCartItemByCartIdAndProductId(
                            cart.getCartId(),
                            productId);

            int newQuantity =
                    existingItem.getQuantity() + quantity;

            cartDAO.updateCartItemQuantity(
                    cart.getCartId(),
                    productId,
                    newQuantity);

        }
        else{

        	cartDAO.addItemToCart(
        	        cart.getCartId(),
        	        productId,
        	        quantity);
        }

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }

    private void removeFromCart(HttpServletRequest request,
                                HttpServletResponse response,
                                int userId)
            throws IOException {

        String productIdParam =
                request.getParameter("id");

        if(productIdParam == null ||
           productIdParam.isEmpty()){

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        int productId =
                Integer.parseInt(productIdParam);

        Cart cart =
                cartDAO.getCartByUserId(userId);

        if(cart != null){

            cartDAO.removeCartItem(
                    cart.getCartId(),
                    productId);
        }

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }

    private void increaseQuantity(HttpServletRequest request,
                                  HttpServletResponse response,
                                  int userId)
            throws IOException {

        String productIdParam =
                request.getParameter("id");

        if(productIdParam == null ||
           productIdParam.isEmpty()){

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        int productId =
                Integer.parseInt(productIdParam);

        Cart cart =
                cartDAO.getCartByUserId(userId);

        if(cart != null){

            CartItem item =
                    cartDAO.getCartItemByCartIdAndProductId(
                            cart.getCartId(),
                            productId);

            if(item != null){

                int newQuantity =
                        item.getQuantity() + 1;

                cartDAO.updateCartItemQuantity(
                        cart.getCartId(),
                        productId,
                        newQuantity);
            }
        }

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }

    private void decreaseQuantity(HttpServletRequest request,
                                  HttpServletResponse response,
                                  int userId)
            throws IOException {

        String productIdParam =
                request.getParameter("id");

        if(productIdParam == null ||
           productIdParam.isEmpty()){

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        int productId =
                Integer.parseInt(productIdParam);

        Cart cart =
                cartDAO.getCartByUserId(userId);

        if(cart != null){

            CartItem item =
                    cartDAO.getCartItemByCartIdAndProductId(
                            cart.getCartId(),
                            productId);

            if(item != null){

                int currentQuantity =
                        item.getQuantity();

                if(currentQuantity > 1){

                    cartDAO.updateCartItemQuantity(
                            cart.getCartId(),
                            productId,
                            currentQuantity - 1);

                }
                else{

                    cartDAO.removeCartItem(
                            cart.getCartId(),
                            productId);
                }
            }
        }

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }

    private void updateCartItem(HttpServletRequest request,
                                HttpServletResponse response,
                                int userId)
            throws IOException {

        String productIdParam =
                request.getParameter("id");

        String quantityParam =
                request.getParameter("quantity");

        if(productIdParam == null ||
           quantityParam == null ||
           productIdParam.isEmpty() ||
           quantityParam.isEmpty()){

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        int productId =
                Integer.parseInt(productIdParam);

        int quantity =
                Integer.parseInt(quantityParam);

        Cart cart =
                cartDAO.getCartByUserId(userId);

        if(cart != null){

            cartDAO.updateCartItemQuantity(
                    cart.getCartId(),
                    productId,
                    quantity);
        }

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }

    private void clearCart(HttpServletRequest request,
                           HttpServletResponse response,
                           int userId)
            throws IOException {

        Cart cart =
                cartDAO.getCartByUserId(userId);

        if(cart != null){

            cartDAO.clearCart(
                    cart.getCartId());
        }

        response.sendRedirect(
                request.getContextPath() + "/cart");
    }

    private void viewCart(HttpServletRequest request,
                          HttpServletResponse response,
                          int userId)
            throws ServletException, IOException {

        Cart cart =
                cartDAO.getCartByUserId(userId);

        List<Product> cartProducts =
                new ArrayList<>();

        BigDecimal totalAmount =
                BigDecimal.ZERO;

        int itemCount = 0;

        if(cart != null){

            List<CartItem> cartItems =
                    cartDAO.getCartItemsByCartId(
                            cart.getCartId());

            itemCount =
                    cartDAO.getCartItemCount(
                            cart.getCartId());

            totalAmount =
                    cartDAO.getCartTotalAmount(
                            cart.getCartId());

            for(CartItem item : cartItems){

                Product product =
                        productDAO.getProductById(
                                item.getProductId());

                if(product != null){

                    cartProducts.add(product);
                }
            }

            request.setAttribute(
                    "cartItems",
                    cartItems);
        }

        request.setAttribute(
                "cartProducts",
                cartProducts);

        request.setAttribute(
                "totalAmount",
                totalAmount);

        request.setAttribute(
                "itemCount",
                itemCount);

        request.getRequestDispatcher(
                "/WEB-INF/views/cart.jsp")
                .forward(request, response);
    }
}