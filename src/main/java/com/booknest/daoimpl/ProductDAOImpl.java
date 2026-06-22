package com.booknest.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booknest.dao.ProductDAO;
import com.booknest.model.Product;
import com.booknest.model.ProductDetails;
import com.booknest.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private Connection connection;

    public ProductDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean addProduct(Product product) {

        boolean status = false;

        String query = "INSERT INTO products(title, author, description, price, stock, image_url, rating, is_active, category_id) VALUES(?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getAuthor());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setBigDecimal(4, product.getPrice());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setString(6, product.getImageUrl());
            preparedStatement.setDouble(7, product.getRating());
            preparedStatement.setBoolean(8, product.isActive());
            preparedStatement.setInt(9, product.getCategoryId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updateProduct(Product product) {

        boolean status = false;

        String query = "UPDATE products SET title=?, author=?, description=?, price=?, stock=?, image_url=?, rating=?, is_active=?, category_id=? WHERE product_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getAuthor());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setBigDecimal(4, product.getPrice());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setString(6, product.getImageUrl());
            preparedStatement.setDouble(7, product.getRating());
            preparedStatement.setBoolean(8, product.isActive());
            preparedStatement.setInt(9, product.getCategoryId());
            preparedStatement.setInt(10, product.getProductId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteProduct(int productId) {

        boolean status = false;

        String query = "DELETE FROM products WHERE product_id=?";

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
    public Product getProductById(int productId) {

        Product product = null;

        String query =
                "SELECT p.*, pd.publisher, pd.language, " +
                "pd.pages, pd.publication_year " +
                "FROM products p " +
                "LEFT JOIN product_details pd " +
                "ON p.product_id = pd.product_id " +
                "WHERE p.product_id=?";

        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()){

                product = new Product();

                product.setProductId(
                        resultSet.getInt("product_id"));

                product.setTitle(
                        resultSet.getString("title"));

                product.setAuthor(
                        resultSet.getString("author"));

                product.setDescription(
                        resultSet.getString("description"));

                product.setPrice(
                        resultSet.getBigDecimal("price"));

                product.setStock(
                        resultSet.getInt("stock"));

                product.setRating(
                        resultSet.getDouble("rating"));

                product.setImageUrl(
                        resultSet.getString("image_url"));

                product.setPublisher(
                        resultSet.getString("publisher"));

                product.setLanguage(
                        resultSet.getString("language"));

                product.setPages(
                        resultSet.getInt("pages"));

                product.setPublishedYear(
                        resultSet.getInt("publication_year"));
            }

        }
        catch(Exception e){

            e.printStackTrace();
        }

        return product;
    }
    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product();

                product.setProductId(resultSet.getInt("product_id"));
                product.setTitle(resultSet.getString("title"));
                product.setAuthor(resultSet.getString("author"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setStock(resultSet.getInt("stock"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setRating(resultSet.getDouble("rating"));
                product.setActive(resultSet.getBoolean("is_active"));
                product.setCategoryId(resultSet.getInt("category_id"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getActiveProducts() {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products WHERE is_active=true";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product();

                product.setProductId(resultSet.getInt("product_id"));
                product.setTitle(resultSet.getString("title"));
                product.setAuthor(resultSet.getString("author"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setStock(resultSet.getInt("stock"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setRating(resultSet.getDouble("rating"));
                product.setActive(resultSet.getBoolean("is_active"));
                product.setCategoryId(resultSet.getInt("category_id"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products WHERE category_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product();

                product.setProductId(resultSet.getInt("product_id"));
                product.setTitle(resultSet.getString("title"));
                product.setAuthor(resultSet.getString("author"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setStock(resultSet.getInt("stock"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setRating(resultSet.getDouble("rating"));
                product.setActive(resultSet.getBoolean("is_active"));
                product.setCategoryId(resultSet.getInt("category_id"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> products =
                new ArrayList<>();

        String query =
                "SELECT * FROM products " +
                "WHERE LOWER(title) LIKE LOWER(?) " +
                "OR LOWER(author) LIKE LOWER(?)";

        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    "%" + keyword + "%");

            preparedStatement.setString(
                    2,
                    "%" + keyword + "%");

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()){

                Product product =
                        new Product();

                product.setProductId(
                        resultSet.getInt("product_id"));

                product.setTitle(
                        resultSet.getString("title"));

                product.setAuthor(
                        resultSet.getString("author"));

                product.setDescription(
                        resultSet.getString("description"));

                product.setPrice(
                        resultSet.getBigDecimal("price"));

                product.setStock(
                        resultSet.getInt("stock"));

                product.setRating(
                        resultSet.getDouble("rating"));

                product.setImageUrl(
                        resultSet.getString("image_url"));

                products.add(product);
            }

        }
        catch(Exception e){

            e.printStackTrace();
        }

        return products;
    }
    
    @Override
    public List<Product> getProductsByCategory(String category) {

        List<Product> products = new ArrayList<>();

        String query =
                "SELECT * FROM products WHERE category = ?";

        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, category);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()){

                Product product = new Product();

                product.setProductId(
                        resultSet.getInt("product_id"));

                product.setTitle(
                        resultSet.getString("title"));

                product.setAuthor(
                        resultSet.getString("author"));

                product.setPrice(
                        resultSet.getBigDecimal("price"));

                product.setCategory(
                        resultSet.getString("category"));

                product.setDescription(
                        resultSet.getString("description"));

                product.setStock(
                        resultSet.getInt("stock"));

                product.setRating(
                        resultSet.getDouble("rating"));

                product.setImageUrl(
                        resultSet.getString("image_url"));

                products.add(product);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return products;
    }

	@Override
	public List<Product> getFeaturedProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getTrendingProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getLatestProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateProductStock(int productId, int stock) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProductRating(int productId, double rating) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProductStatus(int productId, boolean isActive) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addProductDetails(ProductDetails productDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProductDetails(ProductDetails productDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductDetails getProductDetailsByProductId(int productId) {

	    ProductDetails details = null;

	    String query =
	            "SELECT * FROM product_details WHERE product_id=?";

	    try {

	        PreparedStatement preparedStatement =
	                connection.prepareStatement(query);

	        preparedStatement.setInt(1, productId);

	        ResultSet resultSet =
	                preparedStatement.executeQuery();

	        if(resultSet.next()){

	            details = new ProductDetails();

	            details.setPublisher(
	                    resultSet.getString("publisher"));

	            details.setLanguage(
	                    resultSet.getString("language"));

	            details.setPages(
	                    resultSet.getInt("pages"));

	            details.setIsbn(
	                    resultSet.getString("isbn"));
	            
	            System.out.println(
	            	    resultSet.getInt("publication_year"));

	            details.setPublicationYear(
	                    resultSet.getInt("publication_year"));
	        }

	    }
	    catch(Exception e){

	        e.printStackTrace();
	    }

	    return details;
	}

	@Override
	public boolean deleteProductDetails(int productId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}