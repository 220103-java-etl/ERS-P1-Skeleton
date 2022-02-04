package com.revature.App;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;

import java.util.Scanner;

public class AppDriver {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static UserDAO userDAO = new UserDAO();


    public static void main(String[] args) {
        User getUser = userDAO.getById(4);
        System.out.println("Fetching user via id #:");
        System.out.println(getUser);
        User u  = UserService.login("coolguy", "iscool");
        System.out.println(u);
/*


*/







    }
}
