package com.booknest.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.booknest.dao.CartDAO;
import com.booknest.model.Cart;
import com.booknest.model.CartItem;
import com.booknest.model.Product;
import com.booknest.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    @Override
    public Cart getCartByUserId(int userId) {

        Cart cart = null;

        String query =
                "SELECT * FROM cart WHERE user_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()){

                cart = new Cart();

                cart.setCartId(
                        resultSet.getInt("cart_id"));

                cart.setUserId(
                        resultSet.getInt("user_id"));

                cart.setCreatedAt(
                        resultSet.getTimestamp("created_at"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public Cart createCartForUser(int userId) {

        Cart cart = null;

        String query =
                "INSERT INTO cart(user_id) VALUES(?)";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            query,
                            PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);

            int rows =
                    preparedStatement.executeUpdate();

            if(rows > 0){

                ResultSet generatedKeys =
                        preparedStatement.getGeneratedKeys();

                if(generatedKeys.next()){

                    cart = new Cart();

                    cart.setCartId(
                            generatedKeys.getInt(1));

                    cart.setUserId(userId);
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public boolean addItemToCart(int cartId,
                                 int productId,
                                 int quantity) {

        boolean status = false;

        String query =
                "INSERT INTO cart_items "
                + "(cart_id, product_id, quantity, price_at_addition) "
                + "VALUES (?, ?, ?, ?)";

        try {

            Connection connection =
                    DBConnection.getConnection();

            ProductDAOImpl productDAO =
                    new ProductDAOImpl();

            Product product =
                    productDAO.getProductById(productId);

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            preparedStatement.setInt(3, quantity);

            preparedStatement.setBigDecimal(
                    4,
                    product.getPrice());

            int rows =
                    preparedStatement.executeUpdate();

            if(rows > 0){
                status = true;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updateCartItemQuantity(int cartId,
                                          int productId,
                                          int quantity) {

        boolean status = false;

        String query =
                "UPDATE cart_items "
                + "SET quantity=? "
                + "WHERE cart_id=? AND product_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, quantity);

            preparedStatement.setInt(2, cartId);

            preparedStatement.setInt(3, productId);

            int rows =
                    preparedStatement.executeUpdate();

            if(rows > 0){
                status = true;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean removeCartItem(int cartId,
                                  int productId) {

        boolean status = false;

        String query =
                "DELETE FROM cart_items "
                + "WHERE cart_id=? AND product_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            int rows =
                    preparedStatement.executeUpdate();

            if(rows > 0){
                status = true;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean clearCart(int cartId) {

        boolean status = false;

        String query =
                "DELETE FROM cart_items WHERE cart_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            int rows =
                    preparedStatement.executeUpdate();

            if(rows > 0){
                status = true;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public CartItem getCartItemByCartIdAndProductId(int cartId,
                                                    int productId) {

        CartItem item = null;

        String query =
                "SELECT * FROM cart_items "
                + "WHERE cart_id=? AND product_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()){

                item = new CartItem();

                item.setCartItemId(
                        resultSet.getInt("cart_item_id"));

                item.setCartId(
                        resultSet.getInt("cart_id"));

                item.setProductId(
                        resultSet.getInt("product_id"));

                item.setQuantity(
                        resultSet.getInt("quantity"));

                item.setPriceAtAddition(
                        resultSet.getBigDecimal("price_at_addition"));

                item.setAddedAt(
                        resultSet.getTimestamp("added_at"));
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {

        List<CartItem> cartItems =
                new ArrayList<>();

        String query =
                "SELECT * FROM cart_items WHERE cart_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()){

                CartItem item =
                        new CartItem();

                item.setCartItemId(
                        resultSet.getInt("cart_item_id"));

                item.setCartId(
                        resultSet.getInt("cart_id"));

                item.setProductId(
                        resultSet.getInt("product_id"));

                item.setQuantity(
                        resultSet.getInt("quantity"));

                item.setPriceAtAddition(
                        resultSet.getBigDecimal("price_at_addition"));

                item.setAddedAt(
                        resultSet.getTimestamp("added_at"));

                cartItems.add(item);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {

        List<CartItem> cartItems =
                new ArrayList<>();

        Cart cart =
                getCartByUserId(userId);

        if(cart != null){

            cartItems =
                    getCartItemsByCartId(
                            cart.getCartId());
        }

        return cartItems;
    }

    @Override
    public int getCartItemCount(int cartId) {

        int count = 0;

        String query =
                "SELECT SUM(quantity) AS total_items "
                + "FROM cart_items WHERE cart_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()){

                count =
                        resultSet.getInt("total_items");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public BigDecimal getCartTotalAmount(int cartId) {

        BigDecimal total =
                BigDecimal.ZERO;

        String query =
                "SELECT SUM(quantity * price_at_addition) "
                + "AS total_amount "
                + "FROM cart_items WHERE cart_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next() &&
               resultSet.getBigDecimal("total_amount") != null){

                total =
                        resultSet.getBigDecimal("total_amount");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public boolean isProductAlreadyInCart(int cartId,
                                          int productId) {

        boolean exists = false;

        String query =
                "SELECT * FROM cart_items "
                + "WHERE cart_id=? AND product_id=?";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            preparedStatement.setInt(2, productId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()){
                exists = true;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return exists;
    }
}