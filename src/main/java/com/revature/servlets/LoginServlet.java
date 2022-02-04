package com.revature.servlets;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the parameters of username and password from the request.
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = userService.login(username, password);
        if (u != null) {
            User user = UserDAO.getByUsername(username);
            session.setAttribute("user",user);
            String role = String.valueOf(user.getRole());
            if(role.equals("EMPLOYEE")) {
                resp.sendRedirect("employee.html");
            } else {
                resp.sendRedirect("manager.html");
            }

        } else {
            resp.setStatus(401);
        }
    }
}



