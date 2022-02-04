package com.revature.servlets;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    UserService us = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("checkbox");

        User u = us.Register(firstName,lastName,username,password, role);
        if (u != null) {
//            resp.getWriter().write("Successfully logged in");
            resp.sendRedirect("index.html");
        } else {
            resp.setStatus(401);
        }

    }
}
