package com.booknest.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booknest.dao.CartItemDAO;
import com.booknest.model.CartItem;
import com.booknest.util.DBConnection;

public class CartItemDAOImpl implements CartItemDAO {

    private Connection connection;

    public CartItemDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {

        boolean status = false;

        String query = "INSERT INTO cart_items(cart_id, product_id, quantity, price_at_addition) VALUES(?,?,?,?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItem.getCartId());
            preparedStatement.setInt(2, cartItem.getProductId());
            preparedStatement.setInt(3, cartItem.getQuantity());
            preparedStatement.setBigDecimal(4, cartItem.getPriceAtAddition());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {

        boolean status = false;

        String query = "UPDATE cart_items SET quantity=?, price_at_addition=? WHERE cart_item_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItem.getQuantity());
            preparedStatement.setBigDecimal(2, cartItem.getPriceAtAddition());
            preparedStatement.setInt(3, cartItem.getCartItemId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteCartItem(int cartItemId) {

        boolean status = false;

        String query = "DELETE FROM cart_items WHERE cart_item_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItemId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public CartItem getCartItemById(int cartItemId) {

        CartItem cartItem = null;

        String query = "SELECT * FROM cart_items WHERE cart_item_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartItemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setProductId(resultSet.getInt("product_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setPriceAtAddition(resultSet.getBigDecimal("price_at_addition"));
                cartItem.setAddedAt(resultSet.getTimestamp("added_at"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItem;
    }

    @Override
    public CartItem getCartItemByCartIdAndProductId(int cartId, int productId) {

        CartItem cartItem = null;

        String query = "SELECT * FROM cart_items WHERE cart_id=? AND product_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setProductId(resultSet.getInt("product_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setPriceAtAddition(resultSet.getBigDecimal("price_at_addition"));
                cartItem.setAddedAt(resultSet.getTimestamp("added_at"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItem;
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {

        List<CartItem> cartItems = new ArrayList<>();

        String query = "SELECT * FROM cart_items WHERE cart_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                CartItem cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setProductId(resultSet.getInt("product_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setPriceAtAddition(resultSet.getBigDecimal("price_at_addition"));
                cartItem.setAddedAt(resultSet.getTimestamp("added_at"));

                cartItems.add(cartItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public List<CartItem> getAllCartItems() {

        List<CartItem> cartItems = new ArrayList<>();

        String query = "SELECT * FROM cart_items";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                CartItem cartItem = new CartItem();

                cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
                cartItem.setCartId(resultSet.getInt("cart_id"));
                cartItem.setProductId(resultSet.getInt("product_id"));
                cartItem.setQuantity(resultSet.getInt("quantity"));
                cartItem.setPriceAtAddition(resultSet.getBigDecimal("price_at_addition"));
                cartItem.setAddedAt(resultSet.getTimestamp("added_at"));

                cartItems.add(cartItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }
}