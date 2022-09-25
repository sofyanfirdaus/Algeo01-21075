package tubes.algeo;

public class Matrix {
    private Row[] data;

    public Matrix(int baris, int kolom) {
        this.data = new Row[baris];
        for (int i = 0; i < data.length; i++) {
            data[i] = new Row(kolom);
        }
    }

    public Matrix(Row[] data) {
        this.data = new Row[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = new Row(data[i].length());
            for (int j = 0; j < data[i].length(); j++) {
                this.data[i].setElement(j, data[i].getElement(j));
            }
        }
    }

    public void concatRow(Matrix other) {
        try {
            if (getCol() != other.getCol()) {
                throw new Exception("both matrix must have same numbers of column");
            }
            int prevRow = getRow();
            expandRows(other.getRow());
            for (int i = 0; i < other.getRow(); i++) {
                for (int j = 0; j < other.getCol(); j++) {
                    setElement(prevRow + i, j, other.getElement(i, j));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void concatCols(Matrix other) {
        try {
            if (getRow() != other.getRow()) {
                throw new Exception("both matrix must have same numbers of row");
            }
            int prevCol = getCol();
            expandCols(other.getCol());
            for (int i = 0; i < other.getRow(); i++) {
                for (int j = 0; j < other.getCol(); j++) {
                    setElement(i, prevCol + j, other.getElement(i, j));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void expandRows(int length) {
        int prevRow = getRow();
        Row[] newData = new Row[getRow() + length];
        System.arraycopy(data, 0, newData, 0, getRow());
        data = newData;
        for (int i = prevRow; i < getRow(); i++) {
            data[i] = new Row(getCol());
        }
    }

    public void expandCols(int length) {
        for (Row row : data) {
            row.expand(length);
        }
    }

    public void setElements(Row[] matrix) {
        data = matrix;
    }

    private void setElement(int i, int j, double value) {
        data[i].setElement(j, value);
    }

    public void setAll(double value) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < this.data.length; j++) {
                data[i].setElement(j, value);;
            }
        }
    }

    public double getElement(int row, int col) {
        return data[row].getElement(col);
    }

    /**
     * Menghitung determinan matriks dengan mengubah
     * terlebih dahulu matriks menjadi matriks segitiga.
     * @return determinan matriks
     */
    public double getDeterminantGauss() {
        double result = 1;
        Row[] triangularMatrix = new Matrix(data).data;
        for (int j = 0; j < getCol() - 1; j++) {
            for (int i = j+1; i < getRow(); i++) {
                double val = triangularMatrix[j].getElement(j);
                if (val == 0) {
                    if (i - 1 == j) continue;
                    else {
                        swap(triangularMatrix[i], triangularMatrix[j + 1]);
                        result *= -1;
                    }
                } else {
                    triangularMatrix[i] = addMul(triangularMatrix[i], triangularMatrix[j], -triangularMatrix[i].getElement(j)/triangularMatrix[j].getElement(j));
                }
            }
        }
        for (int i = 0; i < triangularMatrix.length; i++) {
            result *= triangularMatrix[i].getElement(i);
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

    public Matrix matMul(Matrix others) {
        Matrix result = new Matrix(getRow(), others.getCol());
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < others.getCol(); j++) {
                double sum = 0;
                for (int k = 0; k < getCol(); k++) {
                    sum += data[i].getElement(k) * others.getMatrixData()[k].getElement(j);
                }
                result.setElement(i, j, sum);
            }
        }
        return result;
    }

    public void multiply(double scalar) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length(); j++) {
                setElement(i, j, getElement(i, j) * scalar);
            }
        }
    }

    public static Matrix multiply(Matrix m, double scalar) {
        Matrix res = new Matrix(m.data);
        for (int i = 0; i < m.data.length; i++) {
            for (int j = 0; j < m.data[i].length(); j++) {
                res.setElement(i, j, res.getElement(i, j) * scalar);
            }
        }
        return res;
    }

    public double getCofactor(int row, int col) {
        Matrix mk = new Matrix(getRow() - 1, getCol() - 1);
        int idx = 0;
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getCol(); j++) {
                if (i != row && j != col) {
                    mk.setElement(idx/mk.getRow(), idx%mk.getRow(), getElement(i, j));
                    idx++;
                }
            }
        }
        return Math.pow(-1, row+col) * mk.getDeterminantCofactor();
    }

    public double getDeterminantCofactor() {
        if (getRow() > 2) {
            double det = 0;
            for (int col = 0; col < getCol(); col++) {
                det += getElement(0, col) * getCofactor(0, col);
            }
            return det;
        } else if (getRow() == 2) {
            return getElement(0, 0) * getElement(1, 1) - getElement(0, 1) * getElement(1, 0);
        } else {
            return getElement(0, 0);
        }
    }

    public Matrix getInverseMatrixGaussJordan() {
        int n = getRow();
        Row[] ident = identityMatrix(getRow()).data;
        Row[] mk = new Matrix(getMatrixData()).data;
        // Create lower triangular matrix
        for (int j = 0; j < n - 1; j++) {
            for (int i = j+1; i < n; i++) {
                double val = mk[j].getElement(j);
                if (val == 0) {
                    if (i - 1 == j) continue;
                    else {
                        swap(mk[i], mk[j + 1]);
                        swap(ident[i], ident[j + 1]);
                    }
                } else {
                    double t = -mk[i].getElement(j)/mk[j].getElement(j);
                    mk[i] = addMul(mk[i], mk[j], t);
                    ident[i] = addMul(ident[i], ident[j], t);
                }
            }
        }
        for(int i = 0; i < n; i++) {
            double val = 1/mk[i].getElement(i);
            mk[i].multiply(val);
            ident[i].multiply(val);
        }
        // Check inversibility
        for (int i = 0; i < mk.length; i++) {
           if (mk[i].getElement(i) == 0) return null;
        }
        // Create upper triangular matrix
        for (int j = n - 1; j > 0; j--) {
            for(int i = j - 1; i >= 0; i--) {
                double t = -mk[i].getElement(j)/mk[j].getElement(j);
                mk[i] = addMul(mk[i], mk[j], t);
                ident[i] = addMul(ident[i], ident[j], t);
            }
        }
        for(int i = 0; i < n; i++) {
            double val = 1/mk[i].getElement(i);
            mk[i].multiply(val);
            ident[i].multiply(val);
        }
        Matrix res = new Matrix(n, n);
        res.setElements(ident);
        return res;
    }

    public Matrix getInverseMatrixAdj() {
        double det = getDeterminantGauss();
        if (det == 0d) return null;
        return Matrix.multiply(adj(this).transpose(), 1/det);
    }

    public static Matrix adj(Matrix m) {
        int n = m.getRow(); // -> row = col
        Matrix res = new Matrix(m.getRow(), m.getCol());
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res.setElement(i, j, m.getCofactor(i, j));
            }
        }
        return res;
    }

    public Matrix transpose() {
        Matrix res = new Matrix(getRow(), getCol());
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getCol(); j++) {
                res.setElement(i, j, getElement(j, i));
            }
        }
        return res;
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

    public static Matrix identityMatrix(int n) {
        Matrix ident = new Matrix(n, n);
        for(int i = 0; i < n; i++) {
            ident.setElement(i, i, 1);
        }
        return ident;
    }
}


