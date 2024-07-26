import java.awt.*;

public class Target {
    private final int x;
    private final int y;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 20;

    public Target(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
