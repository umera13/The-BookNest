package com.booknest.dao;

import java.util.List;

import com.booknest.model.CartItem;

public interface CartItemDAO {

    boolean addCartItem(CartItem cartItem);

    boolean updateCartItem(CartItem cartItem);

    boolean deleteCartItem(int cartItemId);

    CartItem getCartItemById(int cartItemId);

    CartItem getCartItemByCartIdAndProductId(int cartId, int productId);

    List<CartItem> getCartItemsByCartId(int cartId);

    List<CartItem> getAllCartItems();
}