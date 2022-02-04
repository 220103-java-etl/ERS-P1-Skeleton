package com.revature.repositories;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO  implements GenericDAO<User>{
    static ConnectionFactory cu = ConnectionFactory.getInstance();
    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */




    public User add(User user) {

        switch (user.getRole()) {
            case EMPLOYEE:
                String employee = "insert into users values (Default, ?, ?, ?, ?, ?) returning *";
                try (Connection conn = cu.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement(employee);
                    ps.setString(1,user.getFirstname());
                    ps.setString(2,user.getLastname());
                    ps.setString(3, user.getUsername());
                    ps.setString(4, user.getPassword());
                    ps.setString(5, String.valueOf(Role.EMPLOYEE));

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        user.setId(rs.getInt("id"));
                        return user;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;

            case FINANCE_MANAGER:
                String manager = "insert into users values (Default, ?, ?, ?, ?, ?) returning *";
                try (Connection conn = cu.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement(manager);
                    ps.setString(1,user.getFirstname());
                    ps.setString(2,user.getLastname());
                    ps.setString(3, user.getUsername());
                    ps.setString(4, user.getPassword());
                    ps.setString(5, String.valueOf(Role.FINANCE_MANAGER));

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        user.setId(rs.getInt("id"));
                        return user;
                   }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;

        }
        return null;
    }

    //@Override
    public User getById(Integer id) {
        String sql = "select * from users where id = ?";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("pw"),
                        Role.valueOf(rs.getString("role").toUpperCase())
                );
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer id) {

    }

    /**
    public Optional<User> getByUsername(String username) {
        return Optional.empty();
     }
     */

    public static User getByUsername(String username) {
        String sql = "select * from users where username = ?";
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("pw"),
                        Role.valueOf(rs.getString("role").toUpperCase())
                );
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
        return userToBeRegistered;
    }
}
