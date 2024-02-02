/**
 * Extractor interface
 */
package jp.co.hyron.stat.statisticsexporter.common;

public interface Extractor {
    /**
     * Extracts data into a Matrix
     * 
     * @return the Matrix containing the extracted data
     */
    public Matrix extract();
}
