package jp.co.hyron.stat.statisticsexporter.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void testConstructor() {
        double[][] data = { { 1, 2 }, { 3, 4 } };
        Matrix matrix = new Matrix(data);
        assertArrayEquals(data, matrix.getData());
    }

    @Test
    void testGetRowDimension() {
        double[][] data = { { 1, 2 }, { 3, 4 } };
        Matrix matrix = new Matrix(data);
        assertEquals(2, matrix.getRowDimension());
    }

    @Test
    void testGetColumnDimension() {
        double[][] data = { { 1, 2 }, { 3, 4 } };
        Matrix matrix = new Matrix(data);
        assertEquals(2, matrix.getColumnDimension());
    }

    @Test
    void testSetAndGet() {
        double[][] data = { { 1, 2 }, { 3, 4 } };
        Matrix matrix = new Matrix(data);
        matrix.set(0, 1, 5.0);
        assertEquals(5.0, matrix.get(0, 1), 0.001);
    }

    @Test
    void testAdd() {
        double[][] data1 = { { 1, 2 }, { 3, 4 } };
        double[][] data2 = { { 2, 3 }, { 4, 5 } };
        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);
        Matrix result = matrix1.add(matrix2);
        assertArrayEquals(new double[][] { { 3, 5 }, { 7, 9 } }, result.getData());
    }

    @Test
    void testSubtract() {
        double[][] data1 = { { 1, 2 }, { 3, 4 } };
        double[][] data2 = { { 2, 3 }, { 4, 5 } };
        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);
        Matrix result = matrix1.subtract(matrix2);
        assertArrayEquals(new double[][] { { -1, -1 }, { -1, -1 } }, result.getData());
    }

    @Test
    void testMultiply() {
        double[][] data1 = { { 1, 2 }, { 3, 4 } };
        double[][] data2 = { { 2, 3 }, { 4, 5 } };
        Matrix matrix1 = new Matrix(data1);
        Matrix matrix2 = new Matrix(data2);
        Matrix result = matrix1.multiply(matrix2);
        assertArrayEquals(new double[][] { { 8, 11 }, { 20, 27 } }, result.getData());
    }

    @Test
    void testMultiplyWithScalar() {
        double[][] data = { { 1, 2 }, { 3, 4 } };
        Matrix matrix = new Matrix(data);
        Matrix result = matrix.multiply(2);
        assertArrayEquals(new double[][] { { 2, 4 }, { 6, 8 } }, result.getData());
    }

    @Test
    void testTranspose() {
        double[][] data = { { 1, 2 }, { 3, 4 } };
        Matrix matrix = new Matrix(data);
        Matrix result = matrix.transpose();
        assertArrayEquals(new double[][] { { 1, 3 }, { 2, 4 } }, result.getData());
    }

}
