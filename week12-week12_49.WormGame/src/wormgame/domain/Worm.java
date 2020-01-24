package wormgame.domain;

import wormgame.Direction;
import java.util.ArrayList;

import java.util.List;

public class Worm {

    private Direction direction;
    private List<Piece> pieces;
    private boolean grow;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        direction = originalDirection;
        pieces = new ArrayList<Piece>();
        pieces.add(new Piece(originalX, originalY));
        this.grow = false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public int getLength() {
        return pieces.size();
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    // returns head piece
    public Piece getHead() {
        return pieces.get(getLength() -1);
    }

    //adds new piece to end of pieces array (head). Removes piece at index 0 of pieces array (tail) if length > 2
    public void move() {
        int newX = pieces.get(pieces.size() - 1).getX();
        int newY = pieces.get(pieces.size() - 1).getY();

        if (direction == Direction.UP) {
            newY--;
        } else if (direction == Direction.DOWN) {
            newY++;
        } else if (direction == Direction.LEFT) {
            newX--;
        } else if (direction == Direction.RIGHT) {
            newX++;
        }

        if (getLength() > 2 && !grow) {
            pieces.remove(0);
        }

        if (grow = true) {
            grow = false;
        }

        pieces.add(new Piece(newX, newY));
    }

    public void grow() {
        grow = true;
    }

    // checks if head has same x,y coordinates as parameter side piece
    public boolean runsInto(Piece piece) {
        for (int i = 0; i < getLength(); i++) {
            if (pieces.get(i).getX() == piece.getX() && pieces.get(i).getY() == piece.getY()) {
                return true;
            }
        }

        return false;
    }

    // checks if head has same x,y coordinates as another of its pieces
    public boolean runsIntoItself() {
        ArrayList<Piece> temp = new ArrayList<Piece>(pieces);
        temp.remove(getHead());
        return temp.contains(getHead());
    }
}
