package jp.co.hyron.stat.statisticsexporter.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
/**
 * Joinを効率化するため、以下の機能を実現する。
 * Joinキーと列番号を管理する。
 * データ抽出のキーと列番号を管理する。
 * キーのハッシュ値と行番の対応を管理する。
 */
public class SearchContext {
    /**
     * 検索キーの配列
     */
    private String[] searchKeys;

    public String[] getSearchKeys() {
        return searchKeys;
    }

    /**
     * 検索キーの列番号の配列
     */
    private int[] searchKeyColumns;

    public int[] getSearchKeyColumns() {
        return searchKeyColumns;
    }

    /**
     * データ抽出のキーの配列
     */
    private String[] extractKeys;

    public String[] getExtractKeys() {
        return extractKeys;
    }

    /**
     * データ抽出のキーの列番号の配列
     */
    private int[] extractKeyColumns;

    public int[] getExtractKeyColumns() {
        return extractKeyColumns;
    }

    /**
     * キーと行番号の対応を管理するHashMap
     */
    private HashMap<String, int[]> keyToRowsMap;

    public HashMap<String, int[]> getKeyToRowsMap() {
        return keyToRowsMap;
    }

    private Matrix matrix;

    private int findColumn(String key) {
        var col = this.matrix.findColumn(key);
        if (col == -1) {
            throw new IllegalArgumentException("key is not found: key=" + key);
        }
        return col;
    }
    /**
     * 検索キーの配列とデータ(Matrix)を設定して、検索コンテキストを生成します。
     * 
     * @param searchKeys
     * @param matrix
     */
    public static SearchContext createSearchContext(String[] searchKeys, Matrix matrix) {
        SearchContext searchContext = new SearchContext();
        searchContext.searchKeys = searchKeys;

        searchContext.matrix = matrix;

        // ここでキーの列番号を計算する。
        searchContext.searchKeyColumns = Arrays.stream(searchKeys)
            .map(searchContext::findColumn).mapToInt(Integer::intValue).toArray();

        searchContext.extractKeys = Arrays.stream(matrix.getKeyNames())
                .filter(key -> !Arrays.asList(searchKeys).contains(key)).toArray(String[]::new);
        searchContext.extractKeyColumns = Arrays.stream(searchContext.extractKeys)
                .map(searchContext::findColumn).mapToInt(Integer::intValue).toArray();

        // ここでキーと行番号の対応を管理するHashMapを生成する。
        searchContext.keyToRowsMap = new HashMap<>();
        String[][] data = matrix.getData();
        for (int i = 1; i < matrix.getLastRow(); i++) {
            final int idx = i;
            // searchKeyColumnsでキー値を取得する。すべてのキー値を":"で連結して、keyToRowsMapに追加する。
            var key = Arrays.stream(searchContext.searchKeyColumns).boxed().map(col -> data[idx][col])
                    .collect(Collectors.joining(":"));
            if (searchContext.keyToRowsMap.containsKey(key)) {
                int[] rows = searchContext.keyToRowsMap.get(key);
                rows = Arrays.copyOf(rows, rows.length + 1);
                rows[rows.length - 1] = i;
                searchContext.keyToRowsMap.put(key, rows);
            } else {
                searchContext.keyToRowsMap.put(key, new int[] { i });
            }
        }

        return searchContext;
    }

    /**
     * 検索条件を指定して、行番号の配列を返します。
     * 
     * @param search 検索条件の配列、検索条件はキーと同じ順番の検索値の配列です。
     * @return 検索条件に一致する行番号の配列
     */
    public int[] search(String[] search) {
        var key = Arrays.stream(search).collect(Collectors.joining(":"));
        if (keyToRowsMap.containsKey(key)) {
            return keyToRowsMap.get(key);
        }
        return new int[] {};
    }

    /**
     * 検索条件を指定して、抽出データを返します。
     * 
     * @param search 検索条件の配列、検索条件はキーと同じ順番の検索値の配列
     * @return 検索条件に一致するデータの２次元配列
     */
    public String[][] searchExtract(String[] search) {
        var rows = search(search);
        String[][] extract = new String[rows.length][extractKeys.length];
        String[][] data = matrix.getData();
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < extractKeys.length; j++) {
                extract[i][j] = data[rows[i]][extractKeyColumns[j]];
            }
        }
        return extract;
    }
}

