import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class SerpentAquaPistolGame extends JPanel implements ActionListener {
    private final Serpent serpent;
    private final ArrayList<Bullet> bullets;
    private final ArrayList<Target> targets;
    private final Timer timer;

    public SerpentAquaPistolGame() {
        serpent = new Serpent();
        bullets = new ArrayList<>();
        targets = new ArrayList<>();
        timer = new Timer(30, this);
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> serpent.moveLeft();
                    case KeyEvent.VK_RIGHT -> serpent.moveRight();
                    case KeyEvent.VK_SPACE -> shoot();
                }
            }
        });
        initializeTargets();
        timer.start();
    }

    private void initializeTargets() {
        for (int i = 0; i < 5; i++) {
            targets.add(new Target(i * 100 + 50, 50));
        }
    }

    private void shoot() {
        bullets.add(new Bullet(serpent.getX() + Serpent.WIDTH / 2, serpent.getY()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        serpent.draw(g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        for (Target target : targets) {
            target.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBullets();
        checkCollisions();
        repaint();
    }

    private void moveBullets() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.move();
            if (bullet.getY() < 0) {
                bulletIterator.remove();
            }
        }
    }

    private void checkCollisions() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Iterator<Target> targetIterator = targets.iterator();
            while (targetIterator.hasNext()) {
                Target target = targetIterator.next();
                if (bullet.getBounds().intersects(target.getBounds())) {
                    bulletIterator.remove();
                    targetIterator.remove();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Serpent Aqua Pistol Game");
        SerpentAquaPistolGame game = new SerpentAquaPistolGame();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
