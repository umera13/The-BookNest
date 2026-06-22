package com.booknest.util;

import java.util.List;

import com.booknest.daoimpl.CategoryDAOImpl;
import com.booknest.daoimpl.ProductDAOImpl;
import com.booknest.daoimpl.UserDAOImpl;
import com.booknest.model.Category;
import com.booknest.model.Product;
import com.booknest.model.User;

public class SelectTest {

    public static void main(String[] args) {

        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        ProductDAOImpl productDAO = new ProductDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();

        System.out.println("===== CATEGORIES =====");
        List<Category> categories = categoryDAO.getAllCategories();
        for (Category category : categories) {
            System.out.println(category);
        }

        System.out.println("\n===== PRODUCTS =====");
        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("\n===== USERS =====");
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}