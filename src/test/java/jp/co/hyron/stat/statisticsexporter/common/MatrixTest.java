package jp.co.hyron.stat.statisticsexporter.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    @Test
    void testCreateInstance() {
        String[] keys = { "key1", "key2" };

        Matrix matrix = Matrix.createInstance(keys);

        assertNotNull(matrix);
        assertArrayEquals(keys, matrix.getData()[0]);
    }

    @Test
    void testAddRow() {
        String[] keys = { "key1", "key2" };

        Matrix matrix = Matrix.createInstance(keys);

        matrix.add(keys);

        assertEquals(2, matrix.getData().length);
        assertEquals(keys.length, matrix.getData()[0].length);
        assertArrayEquals(keys, matrix.getData()[0]);
        assertEquals(1, matrix.getLastRow());
    }

    @Test
    void testAddRowExtend() {
        String[] keys = { "key1", "key2" };

        Matrix matrix = Matrix.createInstance(keys);

        for (int i = 0; i < Matrix.EXTEND_SIZE + 1; i++) {
            matrix.add(keys);
        }

        assertEquals(Matrix.EXTEND_SIZE + 1, matrix.getData().length);
        assertArrayEquals(keys, matrix.getData()[1]);
        assertEquals(Matrix.EXTEND_SIZE, matrix.getLastRow());
        assertArrayEquals(keys, matrix.getData()[Matrix.EXTEND_SIZE]);
    }

}
