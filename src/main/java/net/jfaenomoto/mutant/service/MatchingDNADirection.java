package net.jfaenomoto.mutant.service;

/**
 * Loads a DNA table and do checks to see if it's mutant.
 *
 * @author jfaenomoto
 */
class MatchingDNADirection {

    private String[] dna;

    public MatchingDNADirection(String[] dna) {
        this.dna = dna;
    }

    /**
     * Checks if crawling for a given starting position (x, y) and going on a {@link Direction} we can see if one is a
     * mutant or not.
     *
     * @param x         x axis coordinate
     * @param y         y axis coordinate
     * @param direction {@link Direction} where we should head to keep on checking
     * @return true if mutant, false otherwise
     */
    public boolean isMutant(int x, int y, Direction direction) {
        char base = 0;
        int sequenceSize = 0;

        while ((x >= 0 && x < dna.length) && (y >= 0 && y < dna[x].length())) {
            if (dna[x].charAt(y) == base) {
                sequenceSize++;
            } else {
                base = dna[x].charAt(y);
                sequenceSize = 1;
            }

            if (sequenceSize == 4) {
                return true;
            }

            x += direction.getDirectionX();
            y += direction.getDirectionY();
        }

        return false;
    }

}
