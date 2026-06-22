package com.booknest.controller;

import java.io.IOException;

import com.booknest.daoimpl.UserDAOImpl;
import com.booknest.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAOImpl userDAO;

    @Override
    public void init() throws ServletException {

        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();

        user.setFullName(
                request.getParameter("fullName"));

        user.setEmail(
                request.getParameter("email"));

        user.setPhone(
                request.getParameter("phone"));

        user.setPassword(
                request.getParameter("password"));

        user.setAddressLine1(
                request.getParameter("addressLine1"));

        user.setAddressLine2(
                request.getParameter("addressLine2"));

        user.setCity(
                request.getParameter("city"));

        user.setState(
                request.getParameter("state"));

        user.setPostalCode(
                request.getParameter("postalCode"));

        user.setCountry(
                request.getParameter("country"));

        boolean registered =
                userDAO.addUser(user);

        if (registered) {

            request.setAttribute(
                    "successMessage",
                    "Registration Successful! Please Login.");

            request.getRequestDispatcher(
                    "/WEB-INF/views/login.jsp")
                    .forward(request, response);

        } else {

            request.setAttribute(
                    "errorMessage",
                    "Registration Failed!");

            request.getRequestDispatcher(
                    "/WEB-INF/views/register.jsp")
                    .forward(request, response);
        }
    }
}