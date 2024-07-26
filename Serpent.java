import java.awt.*;

public class Serpent {
    private int x = 400;
    private int y = 500;
    public static final int WIDTH = 60;
    public static final int HEIGHT = 20;

    public void moveLeft() {
        if (x > 0) {
            x -= 10;
        }
    }

    public void moveRight() {
        if (x < 800 - WIDTH) {
            x += 10;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
