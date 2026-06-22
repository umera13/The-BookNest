package com.booknest.controller;

import java.io.IOException;

import com.booknest.daoimpl.ProductDAOImpl;
import com.booknest.daoimpl.ProductDetailsDAOImpl;
import com.booknest.model.Product;
import com.booknest.model.ProductDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product")
public class ProductDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAOImpl productDAO;
    private ProductDetailsDAOImpl productDetailsDAO;

    @Override
    public void init() throws ServletException {

        productDAO = new ProductDAOImpl();

        productDetailsDAO = new ProductDetailsDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String productIdParam = request.getParameter("id");

        if(productIdParam == null || productIdParam.isEmpty()){

            response.sendRedirect(
                    request.getContextPath() + "/products");

            return;
        }

        int productId = Integer.parseInt(productIdParam);

        Product product =
                productDAO.getProductById(productId);

        ProductDetails productDetails =
                productDetailsDAO.getProductDetailsByProductId(productId);

        if(product == null){

            response.sendRedirect(
                    request.getContextPath() + "/products");

            return;
        }

        request.setAttribute("product", product);

        request.setAttribute("productDetails", productDetails);

        request.getRequestDispatcher(
                "/WEB-INF/views/product-details.jsp")
                .forward(request, response);
    }
}