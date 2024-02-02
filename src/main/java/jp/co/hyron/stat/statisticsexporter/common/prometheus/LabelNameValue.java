package jp.co.hyron.stat.statisticsexporter.common.prometheus;

/**
 * The name, value pairs should be presented using a inner class
 */
public class LabelNameValue {
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
