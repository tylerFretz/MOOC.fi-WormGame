package wormgame.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import wormgame.game.WormGame;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WormGame wormGame;
    private int sideLength;
    private DrawingBoard drawingBoard;

    public UserInterface(WormGame wormGame, int sideLength) {
        this.wormGame = wormGame;
        this.sideLength = sideLength;
    }

    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        int width = (wormGame.getWidth() + 1) * sideLength + 10;
        int height = (wormGame.getHeight() + 2) * sideLength + 10;

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        drawingBoard = new DrawingBoard(wormGame, sideLength);
        container.add(drawingBoard);

        frame.addKeyListener(new KeyboardListener(wormGame.getWorm()));
    }

    public Updatable getUpdatable() {
        return drawingBoard;
    }


    public JFrame getFrame() {
        return frame;
    }
}
