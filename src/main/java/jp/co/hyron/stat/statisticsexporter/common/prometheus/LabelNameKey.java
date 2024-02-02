package jp.co.hyron.stat.statisticsexporter.common.prometheus;

/**
 * The name, matrix key pairs should be presented using a inner class
 */
public class LabelNameKey {
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
