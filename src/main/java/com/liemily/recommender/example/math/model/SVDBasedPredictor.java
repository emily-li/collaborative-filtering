package com.liemily.recommender.example.math.model;

public class SVDBasedPredictor {
    public double[][] predict(final double[][] data) {
        return new double[][]{};
    }

    SVDs getSvds(final double[][] matrix, int numSingularValues) {
        final int m = matrix[0].length;
        final int n = matrix.length;
        final double[][] u = new double[numSingularValues][m];
        final double[] s = new double[numSingularValues];
        final double[][] vt = new double[n][numSingularValues];

        SVDs svds = new SVDs(u, s, vt);
        return svds;
    }

    class SVDs {
        private double[][] u;
        private double[] s;
        private double[][] vt;

        private SVDs(double[][] u, double[] s, double[][] vt) {
            this.u = u;
            this.s = s;
            this.vt = vt;
        }

        public double[][] getU() {
            return u;
        }

        public double[] getS() {
            return s;
        }

        public double[][] getVt() {
            return vt;
        }
    }
}
