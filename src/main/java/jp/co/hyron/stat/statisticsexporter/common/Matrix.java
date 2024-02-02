package jp.co.hyron.stat.statisticsexporter.common;
/**
 * A class to contain data to transfer between components
 * It has a two dimensional array of Strings. The first row is the key names for the data. the following rows are the data.
 * It should has a initialize method to setup the key names without data.
 * It should has a add method to add a row of data.
 * It should has a get method to remove a row of data.
 * It should has set/get method to set/get a cell of data using row and key or using row and column.
 * When the row will be appended, it should check the index range, if the index will overflow, it should expand the array by 100 rows
 */
public class Matrix {
    
}
