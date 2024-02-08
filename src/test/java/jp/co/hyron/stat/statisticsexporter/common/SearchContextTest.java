/**
 * Tests the SearchContext class which provides search functionality on a Matrix.
 * 
 * Sets up a sample Matrix with test data in @BeforeEach.
 * 
 * Creates a SearchContext for 2 keys from the Matrix.
 * Checks the searchKeyColumns are correct.
 * Tests searching the context with known values.
 * Tests searchExtract to get related data.
 * 
 * This shows basic usage of SearchContext and verifies it works as expected.
 */
package jp.co.hyron.stat.statisticsexporter.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchContextTest {
    Matrix matrix = Matrix.createInstance(new String[] { "key1", "key2", "key3", "key4", "key5" });

    @BeforeEach
    public void setup() {

        matrix.add(new String[] { "value1", "value2", "value3", "value4", "value5" });
        matrix.add(new String[] { "value1", "value2", "value6", "value7", "value8" });
        matrix.add(new String[] { "value9", "value10", "value11", "value12", "value13" });
        matrix.add(new String[] { "value9", "value10", "value14", "value15", "value16" });

        // 4行データを追加する。それぞれの行に違うデータが入っている。
        matrix.add(new String[] { "value20", "value21", "value22", "value23", "value24" });
        matrix.add(new String[] { "value25", "value26", "value27", "value28", "value29" });
        matrix.add(new String[] { "value30", "value31", "value32", "value33", "value34" });
        matrix.add(new String[] { "value35", "value36", "value37", "value38", "value39" });
    }

    @Test
    public void testSearchContext() {
        SearchContext context = new SearchContext();

        // key1とkey2のSearchContextを作る。
        SearchContext key1AndKey2 = context.createSearchContext(new String[] { "key1", "key2" }, matrix);
        assertEquals(2, key1AndKey2.getSearchKeys().length);

        // searchKeysColumnsの値を確認する。
        assertEquals(0, key1AndKey2.getSearchKeyColumns()[0]);
        assertEquals(1, key1AndKey2.getSearchKeyColumns()[1]);

        // value1, value2で検索した結果を確認する。
        int[] values = key1AndKey2.search(new String[] { "value1", "value2" });
        assertEquals(1, values[0]);
        assertEquals(2, values[1]);

        // value1, value2でsearchExtractした結果を確認する。
        String[][] values2 = key1AndKey2.searchExtract(new String[] { "value1", "value2" });
        assertArrayEquals(new String[][] { new String[] { "value3", "value4", "value5" },
                new String[] { "value6", "value7", "value8" } }, values2);

    }

}
