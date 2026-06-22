package com.booknest.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CartItem {

    private int cartItemId;
    private int cartId;
    private int productId;
    private int quantity;
    private BigDecimal priceAtAddition;
    private Timestamp addedAt;

    public CartItem() {
    }

    public CartItem(int cartItemId, int cartId, int productId,
                    int quantity, BigDecimal priceAtAddition,
                    Timestamp addedAt) {

        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtAddition = priceAtAddition;
        this.addedAt = addedAt;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtAddition() {
        return priceAtAddition;
    }

    public void setPriceAtAddition(BigDecimal priceAtAddition) {
        this.priceAtAddition = priceAtAddition;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", priceAtAddition=" + priceAtAddition +
                ", addedAt=" + addedAt +
                '}';
    }
}