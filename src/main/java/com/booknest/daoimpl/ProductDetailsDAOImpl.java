package com.booknest.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booknest.dao.ProductDetailsDAO;
import com.booknest.model.ProductDetails;
import com.booknest.util.DBConnection;

public class ProductDetailsDAOImpl implements ProductDetailsDAO {

    private Connection connection;

    public ProductDetailsDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addProductDetails(ProductDetails productDetails) {

        boolean status = false;

        String query = "INSERT INTO product_details(product_id, publisher, language, pages, isbn) VALUES(?,?,?,?,?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productDetails.getProductId());
            preparedStatement.setString(2, productDetails.getPublisher());
            preparedStatement.setString(3, productDetails.getLanguage());
            preparedStatement.setInt(4, productDetails.getPages());
            preparedStatement.setString(5, productDetails.getIsbn());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updateProductDetails(ProductDetails productDetails) {

        boolean status = false;

        String query = "UPDATE product_details SET publisher=?, language=?, pages=?, isbn=? WHERE product_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, productDetails.getPublisher());
            preparedStatement.setString(2, productDetails.getLanguage());
            preparedStatement.setInt(3, productDetails.getPages());
            preparedStatement.setString(4, productDetails.getIsbn());
            preparedStatement.setInt(5, productDetails.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteProductDetails(int productId) {

        boolean status = false;

        String query = "DELETE FROM product_details WHERE product_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public ProductDetails getProductDetailsById(int detailId) {

        ProductDetails productDetails = null;

        String query = "SELECT * FROM product_details WHERE detail_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, detailId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                productDetails = new ProductDetails();

                productDetails.setDetailId(resultSet.getInt("detail_id"));
                productDetails.setProductId(resultSet.getInt("product_id"));
                productDetails.setPublisher(resultSet.getString("publisher"));
                productDetails.setLanguage(resultSet.getString("language"));
                productDetails.setPages(resultSet.getInt("pages"));
                productDetails.setIsbn(resultSet.getString("isbn"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDetails;
    }

    @Override
    public ProductDetails getProductDetailsByProductId(int productId) {

        ProductDetails productDetails = null;

        String query = "SELECT * FROM product_details WHERE product_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                productDetails = new ProductDetails();

                productDetails.setDetailId(resultSet.getInt("detail_id"));
                productDetails.setProductId(resultSet.getInt("product_id"));
                productDetails.setPublisher(resultSet.getString("publisher"));
                productDetails.setLanguage(resultSet.getString("language"));
                productDetails.setPages(resultSet.getInt("pages"));
                productDetails.setIsbn(resultSet.getString("isbn"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDetails;
    }

    @Override
    public boolean updateStock(int productId, int stock) {

        boolean status = false;

        String query = "UPDATE products SET stock=? WHERE product_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, stock);
            preparedStatement.setInt(2, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean isStockAvailable(int productId, int quantity) {

        boolean status = false;

        String query = "SELECT stock FROM products WHERE product_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int stock = resultSet.getInt("stock");

                status = stock >= quantity;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<ProductDetails> getAllProductDetails() {

        List<ProductDetails> productDetailsList = new ArrayList<>();

        String query = "SELECT * FROM product_details";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ProductDetails productDetails = new ProductDetails();

                productDetails.setDetailId(resultSet.getInt("detail_id"));
                productDetails.setProductId(resultSet.getInt("product_id"));
                productDetails.setPublisher(resultSet.getString("publisher"));
                productDetails.setLanguage(resultSet.getString("language"));
                productDetails.setPages(resultSet.getInt("pages"));
                productDetails.setIsbn(resultSet.getString("isbn"));

                productDetailsList.add(productDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productDetailsList;
    }
}