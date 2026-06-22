package com.booknest.controller;

import java.io.IOException;
import java.util.List;

import com.booknest.daoimpl.CategoryDAOImpl;
import com.booknest.daoimpl.ProductDAOImpl;
import com.booknest.model.Category;
import com.booknest.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAOImpl productDAO;
    private CategoryDAOImpl categoryDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();

        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String keyword =
                request.getParameter("keyword");

        String categoryId =
                request.getParameter("categoryId");

        List<Product> products;

        if(keyword != null &&
           !keyword.trim().isEmpty()){

            products =
                    productDAO.searchProducts(keyword.trim());
        }

        else if(categoryId != null &&
                !categoryId.isEmpty()){

            int id =
                    Integer.parseInt(categoryId);

            products =
                    productDAO.getProductsByCategory(id);
        }

        else{

            products =
                    productDAO.getAllProducts();
        }

        List<Category> categories =
                categoryDAO.getAllCategories();

        request.setAttribute(
                "products",
                products);

        request.setAttribute(
                "categories",
                categories);

        request.getRequestDispatcher(
                "/WEB-INF/views/products.jsp")
                .forward(request, response);
    }
}