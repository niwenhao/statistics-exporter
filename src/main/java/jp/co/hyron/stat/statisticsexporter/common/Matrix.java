package jp.co.hyron.stat.statisticsexporter.common;
/**
 * A class to contain data to transfer between components
 * It has a two dimensional array of Strings. The first row is the key names for the data. the following rows are the data.
 * The index of row or column should start from 1.
 * It should has a lastRow to indicate the number of rows and auto extends the array by 100(a const int) rows when lastRow will over Array size.
 * It should has a initialize method to setup the key names without data.
 * It should has a add method to add a row of data.
 * It should has a get method to remove a row of data.
 * It should has set/get method to set/get a cell of data using row and key or using row and column.
 */
public class Matrix {
    
}
