public class Matriks {
    private double[][] matriks;

    public Matriks(int baris, int kolom) {
        this.matriks = new double[baris][kolom];
    }

    public void print() {
        System.out.println("[");
        for (double[] ds : this.matriks) {
            System.out.print("[");
            int i;
            for (i = 0; i < ds.length - 1; i++) {
                System.out.print(String.format("%8.2f ", ds[i]));
            }
            System.out.println(String.format("%8.2f]", ds[i]));
        }
        System.out.println("]");
    }

    public void setAll(double nilai) {
        for (int i = 0; i < this.matriks.length; i++) {
            for (int j = 0; j < this.matriks.length; j++) {
                this.matriks[i][j] = nilai;
            }
        }
    }

    /**
     * Menghitung determinan matriks dengan mengubah
     * terlebih dahulu matriks menjadi matriks segitiga.
     * @return determinan matriks
     */
    public double getDeterminan() {
        double determinan = 1;
        double[][] matriksSegitiga = this.getMatriksSegitiga();
        for (int i = 0; i < this.matriks.length; i++) {
            determinan *= matriksSegitiga[i][i];
        }
        return determinan;
    }

    private double[][] getMatriksSegitiga() {
        int n = this.getRow();
        double[][] result = this.getDataMatriks();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                double t = -result[j][i]/result[i][i];
                result[j] = this.addMul(this.matriks[j], this.matriks[i], t);
            }
        }
        return result;
    }

    private double[] addMul(double[] A, double[] B, double t) {
        double[] result = new double[A.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = A[i] + t*B[i];
        }
        return result;
    }

    private double[] addRow(double[] row1, double[] row2) {
        double[] result = new double[row1.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = row1[i] + row2[i];
        }
        return result;
    }

    private double[] multiply(double scalar, double[] row) {
        double[] result = new double[row.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = scalar * row[i];
        }
        return result;
    }

    public double[][] getDataMatriks() {
        return matriks;
    }

    public void setElement(double[][] matrix) {
        this.matriks = matrix;
    }

    public int getRow() {
        return matriks.length;
    }

    public int getBanyakKolom() {
        return matriks[0].length;
    }

    public static void main(String[] args) {
        Matriks m = new Matriks(3, 3);
        m.setElement(new double[][] {{ 3,5,2,1,8 },{ 4,8,5,3,2 },{ 1,5,2,7,0 }, { 5,8,6,3,1 }, { 8,7,5,3,2 }});
        m.setElement(m.getMatriksSegitiga());
        m.print();
        System.out.println(m.getDeterminan());
    }
}
