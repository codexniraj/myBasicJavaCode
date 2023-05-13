
import java.applet.*;  
import java.awt.*;  
import java.util.*;  
import java.text.*;  
public class DigitalClockDemo1 extends Applet implements Runnable 
{  
  Thread t = null;  
  int h=0, m=0, s=0;  
  String timeString = "";  
public void init() 
{  
  setBackground( Color.black);  
}  
public void start() 
{  
  t = new Thread( this );  
  t.start();  
}  
public void run() 
{  
  try 
    {  
      while (true) 
        {  
          Calendar cal = Calendar.getInstance();  
          h = cal.get( Calendar.HOUR_OF_DAY );  
          if ( h> 12 ) h -= 12;  
          m = cal.get( Calendar.MINUTE );  
          s = cal.get( Calendar.SECOND );  
          SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");  
          Date date = cal.getTime();  
          timeString = f.format( date );  
          repaint();  
          t.sleep( 1000 );  
       }  
    }  
  catch (Exception e) { }
}  
public void paint( Graphics g ) 
  {  
    g.setColor( Color.white );  
    g.drawString( timeString, 50, 50 );  
  }  
} 
  