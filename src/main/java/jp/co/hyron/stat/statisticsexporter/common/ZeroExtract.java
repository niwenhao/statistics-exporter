package jp.co.hyron.stat.statisticsexporter.common;

/**
 * ZeroExtract does nothing to extract matrix but returns a predefined matrix to the class using it.
 * It is normally used to set some parameters for the succeeding process.
 */
public class ZeroExtract implements Extractor {
    Matrix predefinedMatrix;

    /**
     * Getter for predefinedMatrix.
     * 
     * @return the predefinedMatrix
     */
    public Matrix getPredefinedMatrix() {
        return predefinedMatrix;
    }

    /**
     * Setter for predefinedMatrix.
     * 
     * @param predefinedMatrix the predefinedMatrix to set
     */
    public void setPredefinedMatrix(Matrix predefinedMatrix) {
        this.predefinedMatrix = predefinedMatrix;
    }

    
    /**
     * Implements the extract method to get the predefined matrix.
     */
    public Matrix extract() {
        return predefinedMatrix;
    }

}
