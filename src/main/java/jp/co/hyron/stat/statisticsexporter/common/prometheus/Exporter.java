package jp.co.hyron.stat.statisticsexporter.common.prometheus;

import io.micrometer.core.instrument.MeterRegistry;
import jp.co.hyron.stat.statisticsexporter.common.Matrix;
import jp.co.hyron.stat.statisticsexporter.common.Extractor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Prometheus Exporter
 * 
 * This class should register a metric to micrometer.
 * It has a Extractor reference to get Matrix data.
 * It keeps the LabelNameValue array as attribute to present the predefined
 * metric labels to Prometheus.
 * It also keeps the LabelNameKey array as attribute, take the label value form
 * Matrix using the key to present the variable
 * metric labels to Prometheus.
 * It also has a metricValueKey to get the metric value from the matrix.
 * 
 * It also has a register method to register the metric to micrometer.
 */
public abstract class Exporter {

    // Extractor reference, to get Matrix data
    private Extractor extractor;
    // Micrometer reference, to register the metric
    private MeterRegistry meterRegistry;
    // LabelNameValue array, to present the predefined metric labels to Prometheus
    private String[] labelNameValues;
    // LabelNameKey array, to present the variable metric labels to Prometheus
    private String[] labelNameKeys;
    // metricValueKey, to get the metric value from the matrix
    private String metricValueKey;

    @Autowired
    public Extractor getExtractor() {
        return extractor;
    }

    /**
     * Sets the extractor.
     *
     * @param extractor the extractor
     */
    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    /**
     * Gets the label name values.
     *
     * @return the label name values
     */
    public String[] getLabelNameValues() {
        return labelNameValues;
    }

    /**
     * Sets the label name values.
     *
     * @param labelNameValues the label name values
     */
    public void setLabelNameValues(String[] labelNameValues) {
        this.labelNameValues = labelNameValues;
    }

    /**
     * Gets the label name keys.
     * 
     * @return the label name keys
     */
    public String[] getLabelNameKeys() {
        return labelNameKeys;
    }

    /**
     * Sets the label name keys.
     *
     * @param labelNameKeys the label name keys
     */
    public void setLabelNameKeys(String[] labelNameKeys) {
        this.labelNameKeys = labelNameKeys;
    }

    /**
     * Gets the metric value key.
     *
     * @return the metric value key
     */
    public String getMetricValueKey() {
        return metricValueKey;
    }

    /**
     * Sets the metric value key.
     *
     * @param metricValueKey the metric value key
     */
    public void setMetricValueKey(String metricValueKey) {
        this.metricValueKey = metricValueKey;
    }

    /**
     * This method should be called by the schedular to extract matrix data and
     * update the metric.
     */
    public abstract void updateMetric();

    /**
     * This method should be called by the spring bean manage to register the metric
     * to micrometer.
     */
    public abstract void exportMetric();
}
