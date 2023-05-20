package DBconnect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CAT")
public class CAT extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CAT() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver created");

            String url = "jdbc:mysql://localhost:3306/sonoo?characterEncoding=latin1";
            String username = "root";
            String password = "Niraj@123";

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection created");

            // Execute a query
            String sql = "SELECT * FROM your_table";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                // Process the query results
                while (resultSet.next()) {
                    // Retrieve data from the result set
                    String column1 = resultSet.getString("column1");
                    String column2 = resultSet.getString("column2");
                    // Output the retrieved data
                    out.println("Column 1: " + column1 + "<br>");
                    out.println("Column 2: " + column2 + "<br>");
                    out.println("<br>");
                }
            } catch (SQLException e) {
                out.println("Failed to execute query: " + e.getMessage());
            }

            connection.close(); // Close the connection

        } catch (ClassNotFoundException e) {
            out.println("Failed to load JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            out.println("Failed to establish database connection: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
