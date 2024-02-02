package jp.co.hyron.stat.statisticsexporter.common.prometheus;

/**
 * Prometheus Exporter
 * 
 * This class should register a metric to micrometer.
 * It has a Extractor reference to get Matrix data.
 * It keeps the name, label name and value pairs in his attributes to present the predefined metric labels to Prometheus.
 * It also keeps the label name and key pairs, take the label value form Matrix using the key to present the variable 
 *   metric labels to Prometheus.
 * It also has a metricValueKey to get the metric value from the matrix.
 * 
 * It also has a register method to register the metric to micrometer.
 */
public class Exporter {
    /**
     * The name, value pairs should be presented using a inner class
     */
    
    /**
     * The name, matrix key pairs should be presented using a inner class
     */

}
