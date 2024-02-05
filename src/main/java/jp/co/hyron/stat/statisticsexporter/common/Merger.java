package jp.co.hyron.stat.statisticsexporter.common;

/**
 * A class hold one or above Extractor to extract Matrix and merge those Matrix
 * data into one Matrix.
 * It implements Extractor interface, so has a extract method to get the merged
 * Matrix using by other classes.
 */
public abstract class Merger implements Extractor {
    Extractor[] extractors;

    /**
     * Getter for extractors.
     */
    public Extractor[] getExtractors() {
        return extractors;
    }

    /**
     * Setter for extractors.
     */
    public void setExtractors(Extractor[] extractors) {
        this.extractors = extractors;
    }

    /**
     * Implements the extract method to get the merged Matrix.
     */
    @Override
    public abstract Matrix extract();

}