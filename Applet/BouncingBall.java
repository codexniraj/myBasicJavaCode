import java.applet.*;
import java.awt.*;

public class BouncingBall extends Applet implements Runnable {
    int x, y, dx, dy, radius;
    Thread t = null;
    boolean running = false;
    boolean stopFlag;

    public void init() {
        setBackground(Color.white);
        radius = 20;
        x = getWidth() / 2;
        y = getHeight() / 2;
        dx = 5;
        dy = 5;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            running = true;
            stopFlag = false;
            t.start();
        }
    }

    public void stop() {
        running = false;
    }

    public void run() {
        while (running) {
            x += dx;
            y += dy;

            if (x + radius > getWidth() || x - radius < 0) {
                dx = -dx;
            }
            if (y + radius > getHeight() || y - radius < 0) {
                dy = -dy;
            }

            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                while (stopFlag) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public void update(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        paint(g);
    }

    public synchronized void stopBall() {
        stopFlag = true;
    }

    public synchronized void startBall() {
        stopFlag = false;
        notify();
    }
}
