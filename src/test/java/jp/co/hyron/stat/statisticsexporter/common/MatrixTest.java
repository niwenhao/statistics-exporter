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

    // TODO: add test method for Matrix.add()
    //       1. create a Matrix instance use createInstance()
    //       2. add a row of data using add()
    //       3. assert the data array has a new row
    //       4. assert the data array has the same size as the key names array
    //       5. assert the data array has the same key names as the key names array
    //       6. assert the data array has the same data as the key names array
    //       7. assert the lastRow is 1

    // TODO: add test method for Matrix.add()
    //       1. create a Matrix instance use createInstance()
    //       2. add many rows over EXTEND_SIZE of data using add()
    //       3. assert the data array has extended by EXTEND_SIZE rows
    //       4. assert second row of data array is the same as the first row of data
    //       5. assert last row is correct.

}
