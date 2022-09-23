package tubes.algeo;

public class Matrix {
    private Row[] data;

    public Matrix(int baris, int kolom) {
        this.data = new Row[baris];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Row(kolom);
        }
    }

    public void setElements(Row[] matrix) {
        data = matrix;
    }

    private void setElements(int i, int j, double value) {
        data[i].setElement(j, value);
    }

    public void setAll(double value) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < this.data.length; j++) {
                data[i].setElement(j, value);;
            }
        }
    }

    /**
     * Menghitung determinan matriks dengan mengubah
     * terlebih dahulu matriks menjadi matriks segitiga.
     * @return determinan matriks
     */
    public double getDeterminantGauss() {
        Row[] triangleMatrix = getMatrixData();
        for (int j = 0; j < getCol() - 1; j++) {
            for (int i = j+1; i < getRow(); i++) {
                double val = triangleMatrix[j].getElement(j);
                if (val == 0) {
                    if (i - 1 == j) continue;
                    else swap(triangleMatrix[i], triangleMatrix[j + 1]);
                } else {
                    triangleMatrix[i] = addMul(triangleMatrix[i], triangleMatrix[j], -triangleMatrix[i].getElement(j)/triangleMatrix[j].getElement(j));
                }
            }
        }
        double result = 1;
        for (int i = 0; i < triangleMatrix.length; i++) {
            result *= triangleMatrix[i].getElement(i);
        }
        return result;
    }

    private void swap(Row a, Row b) {
        Row tmp = new Row(a.getData());
        a.setData(b.getData());
        b.setData(tmp.getData());
    }

    private Row addMul(Row A, Row B, double t) {
        Row result = new Row(A.length());
        for (int i = 0; i < result.length(); i++) {
            result.setElement(i, A.getElement(i) + t*B.getElement(i));
        }
        return result;
    }

    public Row addRow(Row A, Row B) {
        Row result = new Row(A.length());
        for (int i = 0; i < result.length(); i++) {
            result.setElement(i, A.getElement(i) + B.getElement(i));
        }
        return result;
    }

    public Row multiply(double scalar, Row row) {
        Row result = new Row(row.length());
        for (int i = 0; i < result.length(); i++) {
            result.setElement(i, scalar * row.getElement(i));
        }
        return result;
    }

    public Matrix matMul(Matrix others) {
        Matrix result = new Matrix(getRow(), others.getRow());
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < others.getCol(); j++) {
                double sum = 0;
                for (int k = 0; k < getCol(); k++) {
                    sum += data[i].getElement(k) * others.getMatrixData()[k].getElement(i);
                }
                result.setElements(i, j, sum);
            }
        }
        return result;
    }

    private int getCol() {
        return data[0].length();
    }

    public Row[] getMatrixData() {
        return data;
    }

    public int getRow() {
       return data.length;
    }

    public int getBanyakKolom() {
        return data[0].length();
    }

    public void print() {
        System.out.println("[");
        for (Row ds : data) {
            System.out.print("[");
            int i;
            for (i = 0; i < ds.getData().length - 1; i++) {
                System.out.print(String.format("%8.2f ", ds.getElement(i)));
            }
            System.out.println(String.format("%8.2f]", ds.getElement(i)));
        }
        System.out.println("]");
    }

    @Override
    public String toString() {
        String format = "[";
        for (Row ds : data) {
            format += "[";
            int i;
            for (i = 0; i < ds.getData().length - 1; i++) {
                format += String.format("%8.2f ", ds.getElement(i));
            }
            format += String.format("%8.2f]", ds.getElement(i));
        }
        format += "]";
        return format;
    }
}


