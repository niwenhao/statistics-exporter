package jp.co.hyron.stat.statisticsexporter.common;

import java.util.Arrays;
import java.util.stream.Stream;

public class JoinMerger extends Merger {
    private String[] joinKeys;

    public String[] getJoinKeys() {
        return joinKeys;
    }

    public void setJoinKeys(String[] joinKeys) {
        this.joinKeys = joinKeys;
    }

    public Matrix extract() {
        Matrix[] matrixes = Arrays.stream(this.getExtractors())
                .map(extractor -> extractor.extract()).map(matrix -> matrix.createSorted(joinKeys)).toArray(Matrix []::new);

        String[][] matrixesKeys = new String[matrixes.length][];

        for (int i = 0; i < matrixes.length; i++) {
            matrixesKeys[i] = Arrays.stream(matrixes[i].getKeyNames()).filter(key ->!Arrays.asList(joinKeys).contains(key)).toArray(String[]::new);
        }

        String[] dataKeyNames = Arrays.stream(matrixesKeys).reduce(null, (a, b) -> {
            if (a == null) {
                return b;
            }
            if (b == null) {
                return a;
            }
            return Stream.of(a, b).flatMap(Arrays::stream).toArray(String[]::new);
        });

        var joinedMatrix = Matrix.createInstance(Stream.concat(Arrays.stream(joinKeys), Arrays.stream(dataKeyNames)).toArray(String[]::new));

        var baseMatrix = matrixes[0];

        for (int i = 1; i < baseMatrix.getLastRow(); i++) {
            // TODO: baseMatrixのi.collect(Collectors.toL)
        }


        // TODO: コンパイルエラーを押されるための一時的なコード、後で修正する
        return null;
    }


}
