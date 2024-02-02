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
 * It keeps the LabelNameValue array as attribute to present the predefined metric labels to Prometheus.
 * It also keeps the LabelNameKey array as attribute, take the label value form Matrix using the key to present the variable 
 *   metric labels to Prometheus.
 * It also has a metricValueKey to get the metric value from the matrix.
 * 
 * It also has a register method to register the metric to micrometer.
 */
public abstract class Exporter {
    private Extractor extractor;
    
    @Autowired
    private MeterRegistry meterRegistry;

    public Extractor getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    private String[] labelNameValues;

    public String[] getLabelNameValues() {
        return labelNameValues;
    }

    public void setLabelNameValues(String[] labelNameValues) {
        this.labelNameValues = labelNameValues;
    }

    private String[] labelNameKeys;

    public String[] getLabelNameKeys() {
        return labelNameKeys;
    }

    public void setLabelNameKeys(String[] labelNameKeys) {
        this.labelNameKeys = labelNameKeys;
    }

    private String metricValueKey;

    public String getMetricValueKey() {
        return metricValueKey;
    }

    public void setMetricValueKey(String metricValueKey) {
        this.metricValueKey = metricValueKey;
    }

    public abstract void updateMetric();

    public abstract void exportMetric();
}
