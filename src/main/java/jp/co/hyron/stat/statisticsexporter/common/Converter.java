/**
 * コンバーター抽象クラス。
 * Extractorインターフェースを実装します。
 */
package jp.co.hyron.stat.statisticsexporter.common;

public abstract class Converter implements Extractor {

    /**
     * Extractorオブジェクト
     */
    private Extractor extractor;

    /**
     * コンストラクター
     * 
     * @param extractor Extractorオブジェクト
     */
    public Converter(Extractor extractor) {
        this.extractor = extractor;
    }

    /**
     * Matrixオブジェクトを抽出します。
     * 
     * @return 抽出したMatrixオブジェクト
     */
    @Override
    public abstract Matrix extract();

}
