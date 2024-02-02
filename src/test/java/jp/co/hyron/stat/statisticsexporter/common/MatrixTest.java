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

}
