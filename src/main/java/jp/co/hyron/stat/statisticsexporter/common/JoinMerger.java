package jp.co.hyron.stat.statisticsexporter.common;

/**
 * A class extends the Merger class and implements the extract method to get the
 * merged Matrix.
 * It join the Matrix from extracts using joinKeys and return it through extract method
 */
public class JoinMerger extends Merger {
    /**
     * The keys to join the Matrix from extracts
     */
    String[] joinKeys;

    public void setJoinKeys(String[] joinKeys) {
        this.joinKeys = joinKeys;
    }

    public String[] getJoinKeys() {
        return joinKeys;
    }

    @Override
    public Matrix extract() {
        // Sort the extracts by joinKeys using Matrix.createSorted()

        // Scan the matrixes 

    }

}
