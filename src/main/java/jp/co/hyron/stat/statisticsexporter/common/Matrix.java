package jp.co.hyron.stat.statisticsexporter.common;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * A class to contain data to transfer between components
 * It has a two dimensional array of Strings. The first row is the key names for
 * the data. the following rows are the data.
 * The index of row or column should start from 1.
 * It should has a lastRow to indicate the number of rows and auto extends the
 * array by 100(a const int) rows when lastRow will over Array size.
 * It should has a initialize method to setup the key names without data.
 * It should has a add method to add a row of data.
 * It should has a get method to remove a row of data.
 * It should has set/get method to set/get a cell of data using row and key or
 * using row and column.
 */
public class Matrix {

    /**
     * Joinを効率化するため、以下の機能を実現する。
     * Joinキーと列番号を管理する。
     * データ抽出のキーと列番号を管理する。
     * キーのハッシュ値と行番の対応を管理する。
     */
    public static class SearchContext {
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

        /**
         * 検索キーの配列とデータ(Matrix)を設定して、検索コンテキストを生成します。
         * 
         * @param searchKeys
         * @param matrix
         */
        public static SearchContext createSearchContext(String[] searchKeys, Matrix matrix) {
            SearchContext searchContext = new SearchContext();
            searchContext.setSearchKeys(searchKeys);

            searchContext.matrix = matrix;

            // ここでキーの列番号を計算する。
            searchContext.searchKeyColumns = Arrays.stream(searchKeys).map(searchKey -> matrix.findColumn(searchKey))
                    .toArray(Integer[]::new);

            searchContext.extractKeys = Arrays.stream(searchKeys)
                    .filter(searchKey -> !Arrays.asList(searchKeys).contains(search)).toArray(String[]::new);
            searchContext.extractKeyColumns = Arrays.stream(searchContext.extractKeys)
                    .map(extractKey -> matrix.findColumn(extractKey))
                    .toArray(Integer[]::new);

            // ここでキーと行番号の対応を管理するHashMapを生成する。
            searchContext.keyToRowsMap = new HashMap<>();
            for (int i = 1; i < matrix.getLastRow(); i++) {
                // searchKeyColumnsでキー値を取得する。すべてのキー値を":"で連結して、keyToRowsMapに追加する。
                var key = Arrays.stream(searchContext.searchKeyColumns)
                        .map(searchKeyColumn -> matrix.get(i, searchKeyColumn))
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
        }

        /**
         * 検索条件を指定して、行番号の配列を返します。
         * 
         * @param search    検索条件の配列、検索条件はキーと同じ順番の検索値の配列です。
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
         * @param search    検索条件の配列、検索条件はキーと同じ順番の検索値の配列
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

    protected String[][] data;
    protected int lastRow = 0;
    public static final int EXTEND_SIZE = 100;

    /**
     * Default constructor for Matrix
     */
    private Matrix() {

    }

    public Matrix createSorted(String[] keys) {
        Matrix matrix = new Matrix();
        matrix.data = Arrays.copyOf(this.data, this.data.length);
        matrix.lastRow = this.lastRow;

        Arrays.sort(matrix.data, 1, matrix.getLastRow(),
                new Comparator<String[]>() {
                    @Override
                    public int compare(String[] row1, String[] row2) {
                        for (String key : keys) {
                            int idx = matrix.findColumn(key);
                            if (idx == -1) {
                                throw new IllegalArgumentException("key is not found: key=" + key);
                            }
                            int result = row1[idx].compareTo(row2[idx]);
                            if (result != 0) {
                                return result;
                            }
                        }
                        return 0;
                    }
                });

        return matrix;
    }

    /**
     * SearchKeyValueの配列を使って、行を検索する、検索結果をSearchResultの配列に格納して返す
     */
    public SearchResult[] search(SearchKeyValue[] searchKeyValues) {
    }

    /**
     * Getter for lastRow
     *
     * @return lastRow
     */
    int getLastRow() {
        return lastRow;
    }

    /**
     * Create instance of Matrix with key names in first row
     *
     * @param keys key names
     * @return Matrix instance
     */
    public static Matrix createInstance(String[] keys) {
        Matrix matrix = new Matrix();
        matrix.data = new String[EXTEND_SIZE][keys.length];
        for (int i = 0; i < keys.length; i++) {
            matrix.data[0][i] = keys[i];
        }
        return matrix;
    }

    /**
     * Get the key names from the first row
     *
     * @return key names
     */
    public String[] getKeyNames() {
        return data[0];
    }

    /**
     * Getter for data array
     * 
     * @return data array
     */
    String[][] getData() {
        return data;
    }

    public void add() {
        this.add(new String[getKeyNames().length]);
    }

    /**
     * Add a row of data
     * 
     * @param values data values
     */
    public void add(String[] values) {
        lastRow++;

        if (values.length != data[0].length) {
            throw new IllegalArgumentException("values should have the same length as keys");
        }

        if (lastRow >= data.length) {
            extend();
        }
        data[lastRow] = new String[data[0].length];
        for (int i = 0; i < values.length; i++) {
            data[lastRow][i] = values[i];
        }
    }

    /**
     * Get a row of data
     * 
     * @param row row number starting from 1
     * @return row data
     */
    public String[] get(int row) {

        if (row <= lastRow) {
            return data[row];
        } else {
            throw new IndexOutOfBoundsException("Row " + row + " out of bounds");
        }

    }

    /**
     * Set value of a cell
     *
     * @param row   row number starting from 1
     * @param key   key name
     * @param value cell value
     */
    public void set(int row, String key, String value) {
        if (row > lastRow) {
            throw new IndexOutOfBoundsException("Row " + row + " out of bounds");
        }

        int col = findColumn(key);
        if (col == -1) {
            throw new IllegalArgumentException("Key " + key + " does not exist");
        }

        data[row][col] = value;
    }

    /**
     * Get value of a cell
     * 
     * @param row row number starting from 1
     * @param key key name
     * @return cell value
     * @throws IndexOutOfBoundsException if row is out of bounds
     * @throws IllegalArgumentException  if key does not exist
     */
    public String get(int row, String key) {
        if (row <= 0 || row > lastRow) {
            throw new IndexOutOfBoundsException("Row " + row + " out of bounds");
        }

        int col = findColumn(key);
        if (col == -1) {
            throw new IllegalArgumentException("Key " + key + " does not exist");
        }

        return data[row][col];
    }

    /**
     * Set value of a cell
     * 
     * @param row   row number starting from 1
     * @param col   column number starting from 1
     * @param value cell value
     */
    public void set(int row, int col, String value) {
        if (row <= 0 || row > lastRow) {
            throw new IndexOutOfBoundsException("Row " + row + " out of bounds");
        }

        if (col <= 0 || col > data[0].length) {
            throw new IndexOutOfBoundsException("Column " + col + " out of bounds");
        }

        data[row][col - 1] = value;
    }

    /**
     * Get value of a cell
     * 
     * @param row row number starting from 1
     * @param col column number starting from 1
     * @return cell value
     */
    public String get(int row, int col) {
        if (row <= 0 || row > lastRow) {
            throw new IndexOutOfBoundsException("Row " + row + " out of bounds");
        }

        if (col <= 0 || col > data[0].length) {
            throw new IndexOutOfBoundsException("Column " + col + " out of bounds");
        }

        return data[row][col - 1];
    }

    private int findColumn(String key) {
        for (int i = 0; i < data[0].length; i++) {
            if (data[0][i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private synchronized void extend() {
        String[][] newData = new String[data.length + EXTEND_SIZE][];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
