package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;
    private Random random;

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;
        this.worm = new Worm(width / 2, height / 2, Direction.DOWN);
        this.random = new Random();
        this.apple = new Apple(random.nextInt(width), random.nextInt(height));

        while (worm.runsInto(apple)) {
            this.apple = new Apple(random.nextInt(width), random.nextInt(height));
        }

        addActionListener(this);
        setInitialDelay(2000);
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Worm getWorm() {
        return this.worm;
    }

    public Apple getApple() {
        return this.apple;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues()) {
            return;
        }

        worm.move();

        if (worm.runsInto(apple)) {
            while (worm.runsInto(apple)) {
                getNewApple();
            }
            worm.grow();
        }
        else if (worm.runsIntoItself()) {
            this.continues = false;
        }
        else if (worm.getHead().getX() == this.width || worm.getHead().getX() < 0) {
            this.continues = false;
        }
        else if (worm.getHead().getY() == this.height || worm.getHead().getY() < 0) {
            this.continues = false;
        }

        updatable.update();
        setDelay(1000 / worm.getLength());
    }

    // creates a new apple with random coordinates that is not on the worm
    private void getNewApple() {
        do {
            this.apple = new Apple(random.nextInt(width), random.nextInt(height));
        }
        while (worm.getPieces().contains(apple));

    }

}
