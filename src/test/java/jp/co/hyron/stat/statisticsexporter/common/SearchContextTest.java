package jp.co.hyron.stat.statisticsexporter.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class SearchContextTest {
    private String[] keys;
    private String[][] data;
    private Matrix matrix;

    @BeforeEach
    public void setup() {
        keys = new String[] { "key1", "key2", "key3", "key4" };
        /**
         * data を以下のように設定する。
         * 
         * R1C1 | R1C2 | R1C3 | R1C4
         * R1C1 | R1C2 | R2C3 | R2C4
         * R3C1 | R3C2 | R3C3 | R3C4
         * R3C1 | R3C2 | R4C3 | R4C4
         * R5C1 | R5C2 | R5C3 | R5C4
         * R6C1 | R6C2 | R6C3 | R6C4
         * R7C1 | R7C2 | R7C3 | R7C4
         */ 
        data = new String[][] { { "value-R1C1", "value-R1C2", "value-R1C3", "value-R1C4" },
        { "value-R1C1", "value-R1C2", "value-R2C3", "value-R2C4" },
        { "value-R3C1", "value-R3C2", "value-R3C3", "value-R3C4" },
        { "value-R3C1", "value-R3C2", "value-R4C3", "value-R4C4" },
        { "value-R5C1", "value-R5C2", "value-R5C3", "value-R5C4" },
        { "value-R6C1", "value-R6C2", "value-R6C3", "value-R6C4" },
        { "value-R7C1", "value-R7C2", "value-R7C3", "value-R7C4" } };
        
        matrix = Matrix.createInstance(keys);
        for (String[] row : data) {
            matrix.add(row);
        }

    }

    @Test
    public void testCreateSearchContext() {
        String[] searchKeys = {"key1", "key2"};

        SearchContext context = SearchContext.createSearchContext(searchKeys, matrix);

        assertArrayEquals(searchKeys, context.getSearchKeys());
        assertArrayEquals(new int[] { 0, 1 }, context.getSearchKeyColumns());

        // extractKeys, extractKeyColumnsはsearchKeys以外のキーが入っていることを確認
        assertArrayEquals(new String[]{"key3", "key4"}, context.getExtractKeys());
        assertArrayEquals(new int[] { 2, 3 }, context.getExtractKeyColumns());

        // keyToRowsMapはsearchKeys以外のキーが入っていないことを確認
        assertEquals(5, context.getKeyToRowsMap().size());

    }

    @Test
    public void testSearch() {
        String[] searchKeys = {"key1", "key2"};
        SearchContext context = SearchContext.createSearchContext(searchKeys, matrix);

        String[] search = { "val1", "val2" };
        int[] expected = { };

        assertArrayEquals(expected, context.search(search));

        search = new String[] { "value-R1C1", "value-R1C2" };
        expected = new int[] { 1, 2 };
        assertArrayEquals(expected, context.search(search));

        //　一行のみマッチした場合
        search = new String[] { "value-R7C1", "value-R7C2" };
        expected = new int[] { 7 };
        assertArrayEquals(expected, context.search(search));
    }

    @Test
    public void testSearchExtract() {
        String[] searchKeys = {"key1", "key2"};
        SearchContext context = SearchContext.createSearchContext(searchKeys, matrix);

        String[] search = { "val1", "val2" };
        String[][] expected = {  };

        assertArrayEquals(expected, context.searchExtract(search));

        search = new String[] { "value-R1C1", "value-R1C2" };  
        expected = new String[][] { { "value-R1C3", "value-R1C4" }, { "value-R2C3", "value-R2C4" } };
        assertArrayEquals(expected, context.searchExtract(search));

        search = new String[] { "value-R7C1", "value-R7C2" };
        expected = new String[][] { { "value-R7C3", "value-R7C4" } };
        assertArrayEquals(expected, context.searchExtract(search));
    }

}
