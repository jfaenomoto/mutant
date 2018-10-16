package net.jfaenomoto.mutant.service;

/**
 * Represents a direction where we can crawl to find a DNA sequence.
 *
 * @author jfaenomoto
 */
public enum Direction {

    HORIZONTAL(0, 1), VERTICAL(1, 0), DIAGONAL(1, 1), OPPOSING_DIAGONAL(1, -1);

    private int directionX;
    private int directionY;

    Direction(int directionX, int directionY) {
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

}
