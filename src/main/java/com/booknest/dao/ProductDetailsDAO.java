package com.booknest.dao;

import java.util.List;

import com.booknest.model.ProductDetails;

public interface ProductDetailsDAO {

    boolean addProductDetails(ProductDetails productDetails);

    boolean updateProductDetails(ProductDetails productDetails);

    boolean deleteProductDetails(int productId);

    ProductDetails getProductDetailsById(int detailId);

    ProductDetails getProductDetailsByProductId(int productId);

    boolean updateStock(int productId, int stock);

    boolean isStockAvailable(int productId, int quantity);

    List<ProductDetails> getAllProductDetails();
}