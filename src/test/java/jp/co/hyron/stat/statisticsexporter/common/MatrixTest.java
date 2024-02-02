package jp.co.hyron.stat.statisticsexporter.common;

import org.junit.jupiter.api.Test;

import jp.co.hyron.stat.statisticsexporter.common.Matrix;

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
        String[] values = { "value1", "value2" };

        Matrix matrix = Matrix.createInstance(keys);

        matrix.add(values);

        assertEquals(Matrix.EXTEND_SIZE, matrix.getData().length);
        assertEquals(values.length, matrix.getData()[0].length);
        assertArrayEquals(keys, matrix.getData()[0]);
        assertArrayEquals(values, matrix.getData()[1]);
        assertEquals(1, matrix.getLastRow());
    }

    @Test
    void testAddRowsOverExtendSize() {
        String[] keys = { "key1", "key2" };

        Matrix matrix = Matrix.createInstance(keys);

        for (int i = 0; i < Matrix.EXTEND_SIZE + 1; i++) {
            matrix.add(keys);
        }

        assertEquals(Matrix.EXTEND_SIZE * 2, matrix.getData().length);
        assertArrayEquals(keys, matrix.getData()[Matrix.EXTEND_SIZE]);
        assertArrayEquals(keys, matrix.getData()[matrix.getLastRow()]);
    }

    @Test
    void testGetRow() {
        String[] keys = { "key1", "key2" };
        String[] values1 = { "value1", "value2" };
        String[] values2 = { "value3", "value4" };

        Matrix matrix = Matrix.createInstance(keys);
        matrix.add(values1);
        matrix.add(values2);

        String[] row1 = matrix.get(1);
        String[] row2 = matrix.get(2);

        try {
            matrix.get(3);
            fail("Should have thrown IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }

        assertArrayEquals(values1, row1);
        assertArrayEquals(values2, row2);
    }

    @Test
    void testSet() {
        String[] keys = { "key1", "key2" };
        Matrix matrix = Matrix.createInstance(keys);

        matrix.add(keys); // Added line to add row before setting

        int row = 1;
        String key = "key1";
        String value = "newValue";

        matrix.set(row, key, value);

        String[] updatedRow = matrix.get(row);
        assertEquals(value, updatedRow[0]);
    }

    @Test
    void testSetKeyMismatch() {
        String[] keys = { "key1", "key2" };
        Matrix matrix = Matrix.createInstance(keys);

        int row = 1;
        matrix.add(keys); // Added line to add row before setting

        String key = "key3";
        String value = "newValue";

        try {
            matrix.set(row, key, value);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    void testSetRowOverflow() {
        String[] keys = { "key1", "key2" };
        Matrix matrix = Matrix.createInstance(keys);

        matrix.add(keys); // Added line to add row before setting

        int row = matrix.getLastRow() + 1;
        String key = "key1";
        String value = "newValue";

        try {
            matrix.set(row, key, value);
            fail("Should have thrown IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    void testGetWithInvalidKey() {
        String[] keys = { "key1", "key2" };
        Matrix matrix = Matrix.createInstance(keys);

        matrix.add(keys);

        int row = 1;
        String invalidKey = "key3";

        try {
            matrix.get(row, invalidKey);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

                @Test
    void testGetWithValidKey() {
        String[] keys = { "key1", "key2" };
        Matrix matrix = Matrix.createInstance(keys);

        String[] values = { "value1", "value2" };
        matrix.add(values);

        int row = 1;
        String validKey = "key1";

        String value = matrix.get(row, validKey);

        assertEquals(values[0], value);
    }

    @Test
    void testGetWithInvalidRow() {
        String[] keys = { "key1", "key2" };
        Matrix matrix = Matrix.createInstance(keys);

        matrix.add(keys);

        int invalidRow = matrix.getLastRow() + 1;
        String validKey = "key1";

        try {
            matrix.get(invalidRow, validKey);
            fail("Should have thrown IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    void testSetIntInt() {
        String[] keys = { "key1", "key2" };
        Matrix matrix = Matrix.createInstance(keys);

        String value1 = "value1";
        int row1 = 1;
        matrix.add(keys);
        int col1 = 1;
        matrix.set(row1, col1, value1);
        assertEquals(value1, matrix.get(row1, col1));

        String value2 = "value2";
        int row2 = 1;
        matrix.add(keys);
        int col2 = 2;
        matrix.set(row2, col2, value2);
        assertEquals(value2, matrix.get(row2, col2));

        int invalidRow = matrix.getLastRow() + 1;
        int validCol = 1;
        try {
            matrix.set(invalidRow, validCol, "value");
            fail("Should have thrown IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }

        int validRow = 1;
        int invalidCol = 0;
        try {
            matrix.set(validRow, invalidCol, "value");
            fail("Should have thrown IllegalArgumentException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
    }

    @Test
    void testGetIntInt() {

        String[] keys = { "key1", "key2" };
        Matrix matrix = Matrix.createInstance(keys);

        matrix.add(keys);
        matrix.set(1, 1, "value1");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.get(0, 1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.get(2, 1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.get(1, 0);
        });

        assertEquals("value1", matrix.get(1, 1));

    }

}
