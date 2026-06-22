package com.booknest.dao;

import java.util.List;

import com.booknest.model.Product;
import com.booknest.model.ProductDetails;

public interface ProductDAO {

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);

    Product getProductById(int productId);

    List<Product> getAllProducts();

    List<Product> getActiveProducts();

    List<Product> getProductsByCategory(int categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> getFeaturedProducts();

    List<Product> getTrendingProducts();

    List<Product> getLatestProducts();

    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);

    boolean updateProductStock(int productId, int stock);

    boolean updateProductRating(int productId, double rating);

    boolean updateProductStatus(int productId, boolean isActive);

    boolean addProductDetails(ProductDetails productDetails);

    boolean updateProductDetails(ProductDetails productDetails);

    ProductDetails getProductDetailsByProductId(int productId);

    boolean deleteProductDetails(int productId);
    
    List<Product> getProductsByCategory(String category);
}