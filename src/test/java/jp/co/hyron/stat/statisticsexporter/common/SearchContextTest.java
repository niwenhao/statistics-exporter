package jp.co.hyron.stat.statisticsexporter.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import jp.co.hyron.stat.statisticsexporter.common.Matrix;
import jp.co.hyron.stat.statisticsexporter.common.SearchContext;

import java.util.Arrays;
import java.util.stream.Stream;

public class SearchContextTest {

    @Test
    public void testSearch() {
        String[] searchKeys;
        Matrix matrix;

        matrix = createMatrix();

        // 検索とデータ抽出のテスト
        SearchContext context = SearchContext.createSearchContext(createSearchKeys(), matrix);

        assertEquals(2, context.search(new String[] { "r1c1", "r1c2" }).length);

        assertArrayEquals(
                new String[][] { 
                    new String[] { "r1c3", "r1c4", "r1c5" }, 
                    new String[] { "r1cc3", "r1cc4", "r1cc5" }
                }, context.searchExtract(new String[] { "r1c1", "r1c2" }));
        assertArrayEquals(
                new String[][] { new String[] { "r3c3", "r3c4", "r3c5" } }, 
                context.searchExtract(new String[] { "r3c1", "r3c2" }));
        assertArrayEquals(
                new String[][] { }, 
                context.searchExtract(new String[] { "r6c1", "r6c2" }));

    }

    private String[] createSearchKeys() {
        return new String[] { "key1", "key2" };
    }

    private String[] createDataKeys() {
        return new String[] { "key3", "key4", "key5" };
    }

    private Matrix createMatrix() {
        // createSearchKeys()とcreateDataKeys()で作成したキー配列を合併してMatrixを作成する。
        String[] keys = Stream.concat(Arrays.stream(createSearchKeys()), Arrays.stream(createDataKeys()))
                .toArray(String[]::new);
        Matrix matrix = Matrix.createInstance(keys);

        for (int i = 0; i < 3; i++) {
            matrix.add(new String[] { "r" + i + "c1", "r" + i + "c2", "r" + i + "c3", "r" + i + "c4", "r" + i + "c5" });
            matrix.add(
                    new String[] { "r" + i + "c1", "r" + i + "c2", "r" + i + "cc3", "r" + i + "cc4", "r" + i + "cc5" });
        }
        for (int i = 3; i < 5; i++) {
            matrix.add(new String[] { "r" + i + "c1", "r" + i + "c2", "r" + i + "c3", "r" + i + "c4", "r" + i + "c5" });
        }
        return matrix;
    }

    @Test
    public void testSearchExtract() {
        // 検索とデータ抽出のテスト
    }
}
