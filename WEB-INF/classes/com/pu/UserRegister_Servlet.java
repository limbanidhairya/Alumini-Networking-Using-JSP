package com.pu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@WebServlet("/register")

public class UserRegister_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int generateUserId() {
        Set<Integer> generatedIds = new HashSet<>();
        Random random = new Random();
        int id;
        do {
            id = random.nextInt(900000) + 100000; // Generates a random 6-digit number
        } while (generatedIds.contains(id));
        generatedIds.add(id);
        return id;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int uq = 0;
        int uq2 = 0;
        int userId = generateUserId();
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dhairya", "root", "root");
            con.setAutoCommit(false); // Start transaction

            // First query to insert user details
            PreparedStatement pstatement = con.prepareStatement("INSERT INTO users ( user_id, username, name, password, email, contact) VALUES ( ?, ?, ?, ?, ?, ?)");
            pstatement.setInt(1, userId);
            pstatement.setString(2, request.getParameter("username"));
            pstatement.setString(3, request.getParameter("name"));
            pstatement.setString(4, request.getParameter("password"));
            pstatement.setString(5, request.getParameter("email"));
            pstatement.setString(6, request.getParameter("contact"));
            uq = pstatement.executeUpdate();
            pstatement.close();

            // Second query to insert userId into another table
           

            con.commit(); // Commit transaction
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            System.out.println(ex);
            String message = ex.getMessage();
            out.print("<br>\r\n"
                    + "<TABLE style=\"background-color: #E3E4FA;\" WIDTH=\"30%\" border=\"1\">\r\n");
            out.print("<tr><th><%");
            out.println(message + "; %></th></tr>\r\n");
            out.print("</table>");
        } catch (Exception exc) {
            System.out.println(exc);
            try {
                if (con != null) {
                    con.rollback(); // Rollback transaction if an exception occurs
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true); // Restore auto-commit mode
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (uq > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("UserReg-success.jsp");
            dispatcher.forward(request, response);
        } else {
            out.println("<h1>Failed to register user!</h1>\r\n"
            		+ "<a href=\"./index.html\"><input type=\"button\" value=\"Home\"></a>");
        }
    }
}

