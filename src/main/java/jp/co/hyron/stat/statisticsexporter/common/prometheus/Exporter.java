package jp.co.hyron.stat.statisticsexporter.common.prometheus;

/**
 * Prometheus Exporter
 * 
 * This class should register a metric to micrometer.
 * It has a Extractor reference to get Matrix data.
 * It keeps the name, label name and value pairs in his attributes.
 * It also keeps the label name and key of the matrix pairs, take the label value form Matrix using the key.
 * It also has a metricKey to get the metric value from the matrix.
 */
public class Exporter {

}
