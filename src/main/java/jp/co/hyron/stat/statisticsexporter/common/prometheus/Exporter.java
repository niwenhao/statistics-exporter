package jp.co.hyron.stat.statisticsexporter.common.prometheus;

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
public class Exporter {
    /**
     * The name, value pairs should be presented using a inner class
     */
    class LabelNameValue {
        private String name;
        private String value;

        public LabelNameValue(String name, String value) {
          this.name = name;
          this.value = value;  
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    
    /**
     * The name, matrix key pairs should be presented using a inner class
     */
    class LabelNameKey {
        private String name;
        private String key;

        public LabelNameKey(String name, String key) {
            this.name = name;
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    /**
     * Add a Extractor reference to get Matrix data.
     * Add attributes to keep the LabelNameValue array as attribute to present the predefined metric labels to Prometheus.
     * Add attributes to keep the LabelNameKey array as attribute, take the label value form Matrix using the key to present the variable 
     *   metric labels to Prometheus.
     * Add attribute metricValueKey to get the metric value from the matrix.
     * 
     * Add a register method to register the metric to micrometer.
     */

}
