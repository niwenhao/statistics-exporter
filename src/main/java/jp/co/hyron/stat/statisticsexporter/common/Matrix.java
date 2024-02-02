package jp.co.hyron.stat.statisticsexporter.common;

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
    protected String[][] data;
    protected int lastRow = 0;
    public static final int EXTEND_SIZE = 100;

    /**
     * Default constructor for Matrix
     */
    private Matrix() {

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
     * Getter for data array
     * 
     * @return data array
     */
    String[][] getData() {
        return data;
    }
    
    
    /**
     * Add a row of data
     * 
     * @param values data values
     */
    public void add(String[] values) {
        if (lastRow >= data.length) {
            extend();
        }
        for (int i = 0; i < values.length; i++) {
            data[lastRow][i] = values[i];
        }
        lastRow++;
    }

    /**
     * Get a row of data
     * 
     * @param row row number starting from 1
     * @return row data
     */
    public String[] get(int row) {
        return data[row - 1];
    }

    /**
     * Set value of a cell
     * 
     * @param row   row number starting from 1
     * @param key   key name
     * @param value cell value
     */
    public void set(int row, String key, String value) {
        int col = findColumn(key);
        data[row - 1][col] = value;
    }

    /**
     * Get value of a cell
     * 
     * @param row row number starting from 1
     * @param key key name
     * @return cell value
     */
    public String get(int row, String key) {
        int col = findColumn(key);
        return data[row - 1][col];
    }

    /**
     * Set value of a cell
     * 
     * @param row   row number starting from 1
     * @param col   column number starting from 1
     * @param value cell value
     */
    public void set(int row, int col, String value) {
        data[row - 1][col - 1] = value;
    }

    /**
     * Get value of a cell
     * 
     * @param row row number starting from 1
     * @param col column number starting from 1
     * @return cell value
     */
    public String get(int row, int col) {
        return data[row - 1][col - 1];
    }

    private int findColumn(String key) {
        for (int i = 0; i < data[0].length; i++) {
            if (data[0][i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private void extend() {
        String[][] newData = new String[data.length + EXTEND_SIZE][];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
