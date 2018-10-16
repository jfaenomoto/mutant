package net.jfaenomoto.mutant.service;

class MatchingDNADirection {

    private String[] dna;

    public MatchingDNADirection(String[] dna) {
        this.dna = dna;
    }

    public boolean isMutant(int x, int y, Direction direction) {
        System.out.println(String.format("isMutant(%s, %s)", x, y));
        char base = 0;
        int sequenceSize = 0;

        while((x >= 0 && x < dna.length) && (y >= 0 && y < dna[x].length())) {
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
