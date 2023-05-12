package SwingAssignment;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Assignment7 extends JFrame {

  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Assignment7 frame = new Assignment7();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public Assignment7() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 450);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
  }

  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D)g;
    
    // Draw a circle
    Ellipse2D.Double circle = new Ellipse2D.Double(100, 100, 200, 200);
    g2d.draw(circle);
    
    // Draw a waveform
    GeneralPath waveform = new GeneralPath();
    waveform.moveTo(200, 300);
    for (int i = 1; i < 9; i++) {
      if (i % 2 == 0) {
        waveform.lineTo(200 + 20*i, 320);
      } else {
        waveform.lineTo(200 + 20*i, 340);
      }
    }
    g2d.draw(waveform);
    
    // Draw enable/disable buttons
    RoundRectangle2D.Double enableButton = new RoundRectangle2D.Double(100, 350, 100, 50, 20, 20);
    RoundRectangle2D.Double disableButton = new RoundRectangle2D.Double(300, 350, 100, 50, 20, 20);
    g2d.draw(enableButton);
    g2d.draw(disableButton);
    g2d.drawString("Enable", 125, 380);
    g2d.drawString("Disable", 325, 380);
  }
}
