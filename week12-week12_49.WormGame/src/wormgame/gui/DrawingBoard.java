package wormgame.gui;

import wormgame.game.WormGame;
import wormgame.domain.Piece;
import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel implements Updatable {

    private int pieceLength;
    private WormGame wormGame;

    public DrawingBoard(WormGame wormGame, int pieceLength) {
        this.pieceLength = pieceLength;
        this.wormGame = wormGame;
    }

    //
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Piece piece : wormGame.getWorm().getPieces()) {
            g.setColor(Color.BLACK);
            g.fill3DRect(pieceLength * piece.getX(), pieceLength * piece.getY(), pieceLength, pieceLength, true);
        }

        g.setColor(Color.RED);
        g.fillOval(pieceLength * wormGame.getApple().getX(), pieceLength * wormGame.getApple().getY(), pieceLength, pieceLength);
    }

    @Override
    public void update() {
        super.repaint();
    }
}
