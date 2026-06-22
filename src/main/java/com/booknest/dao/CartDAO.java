package com.booknest.dao;

import java.math.BigDecimal;
import java.util.List;

import com.booknest.model.Cart;
import com.booknest.model.CartItem;

public interface CartDAO {

    Cart getCartByUserId(int userId);

    Cart createCartForUser(int userId);

    boolean addItemToCart(int cartId,
            int productId,
            int quantity);

    boolean updateCartItemQuantity(int cartId, int productId, int quantity);

    boolean removeCartItem(int cartId, int productId);

    boolean clearCart(int cartId);

    CartItem getCartItemByCartIdAndProductId(int cartId, int productId);

    List<CartItem> getCartItemsByCartId(int cartId);

    List<CartItem> getCartItemsByUserId(int userId);

    int getCartItemCount(int cartId);

    BigDecimal getCartTotalAmount(int cartId);

    boolean isProductAlreadyInCart(int cartId, int productId);
}