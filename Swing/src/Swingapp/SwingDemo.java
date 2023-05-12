package Swingapp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class SwingDemo extends JFrame {
 
private static final long serialVersionUID = 1L;
	
private static final String DB_URL = "jdbc:mysql://localhost:3306/swing_demo";
  private static final String DB_USER = "root";
  private static final String DB_PASSWORD = "Niraj@123";
  
  private JPanel contentPane;
  private JTextField usernameField;
  private JPasswordField passwordField;
  
  public SwingDemo() {
    super("Login Form");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Create the form components
    JLabel usernameLabel = new JLabel("Username:");
    usernameField = new JTextField(20);
    
    JLabel passwordLabel = new JLabel("Password:");
    passwordField = new JPasswordField(20);
    
    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(new LoginListener());
    
    // Add the form components to the content pane
    contentPane = new JPanel();
    contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new GridLayout(3, 1));
    contentPane.add(usernameLabel);
    contentPane.add(usernameField);
    contentPane.add(passwordLabel);
    contentPane.add(passwordField);
    contentPane.add(loginButton);
    
    setContentPane(contentPane);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  private class LoginListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());
      
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      try {
          conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
          stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
          stmt.setString(1, username);
          stmt.setString(2, password);
          rs = stmt.executeQuery();
          
          if (rs.next()) {
              JOptionPane.showMessageDialog(SwingDemo.this, "Login successful!");
          } else {
              JOptionPane.showMessageDialog(SwingDemo.this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
          }
      } catch (SQLException ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(SwingDemo.this, "An error occurred while trying to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
      } finally {
          if (rs != null) {
              try {
                  rs.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
          }
          if (stmt != null) {
              try {
                  stmt.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
          }
          if (conn != null) {
              try {
                  conn.close();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
          }
      }
    }
  }
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new SwingDemo();
      }
    });
  }
}
