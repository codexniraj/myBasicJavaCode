import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SavletBrawser")
public class SavletBrawser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/product";
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Product Details</title></head><body>");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create a prepared statement to retrieve the product details
            String sql = "SELECT p_code, p_name, p_manuf, p_address, p_weight, p_price FROM pdt_tbl";
            stmt = conn.prepareStatement(sql);

            // Execute the query
            rs = stmt.executeQuery();

            // Display the product details in an HTML table
            out.println("<table>");
            out.println("<tr><th>Product Code</th><th>Product Name</th><th>Manufacturer</th><th>Address</th><th>Weight</th><th>Price</th></tr>");
            while (rs.next()) {
                String pCode = rs.getString("p_code");
                String pName = rs.getString("p_name");
                String pManuf = rs.getString("p_manuf");
                String pAddress = rs.getString("p_address");
                double pWeight = rs.getDouble("p_weight");
                double pPrice = rs.getDouble("p_price");

                out.println("<tr><td>" + pCode + "</td><td>" + pName + "</td><td>" + pManuf + "</td><td>" + pAddress
                        + "</td><td>" + pWeight + "</td><td>" + pPrice + "</td></tr>");
            }
            out.println("</table>");

        } catch (ClassNotFoundException | SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            // Close the database resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
