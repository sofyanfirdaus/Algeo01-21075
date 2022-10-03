package tubes.algeo;

import java.util.Locale;

public class Matrix {
  private Row[] data;

  public Matrix(int row, int column) {
    this.data = new Row[row];
    for (int i = 0; i < data.length; i++) {
      data[i] = new Row(column);
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

  public void concatRows(Matrix other) {
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
      for (int i = 0; i < other.getRow(); i++) {
        data[i].concat(other.getMatrixData()[i]);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void replaceColumn(int idx, double... data) {
    try {
      if (data.length != getRow()) {
        throw new IllegalArgumentException("the length of the data must be the same as the length of the row");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (int i = 0; i < data.length; i++) {
      setElement(i, idx, data[i]);
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

  public void setElement(int i, int j, double value) {
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
    Matrix triangular = new Matrix(data);
    for (int j = 0; j < triangular.getCol() - 1; j++) {
      for (int i = j+1; i < triangular.getRow(); i++) {
        double val = triangular.getElement(j, j);
        if (val == 0) {
          boolean zero = true;
          for (int k = i; k < triangular.getRow() && zero; k++) {
            if (triangular.getElement(k, j) != 0d) {
              triangular.swapRow(k, j);
              result *= -1;
              zero = false;
              i--;
            }
          }
        } else {
          double t = -triangular.getElement(i, j)/triangular.getElement(j, j);
          triangular.data[i] = addMul(triangular.data[i], triangular.data[j], t);
        }
      }
    }
    for (int i = 0; i < triangular.getRow(); i++) {
      result *= triangular.getElement(i, i);
    }
    return result;
  }

  public Row getRowData(int idx) {
    return data[idx];
  }

  public void swapRow(int idxA, int idxB) {
    Row a = getRowData(idxA);
    Row b = getRowData(idxB);
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
    Matrix mc = new Matrix(getMatrixData());
    mc.concatCols(identityMatrix(n));
    mc = mc.getReductedEchelon();

    Matrix leftSide = Matrix.subMatrix(mc, 0, 0, n, n);

    if (leftSide.equals(Matrix.identityMatrix(n))) {
      return subMatrix(mc.getReductedEchelon(), 0, n, n, n);
    }

    return null;
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

  public boolean equals(Matrix other) {
    boolean equal = getRow() == other.getRow() && getCol() == other.getCol();
    for (int i = 0; i < getRow() && equal; i++) {
      for (int j = 0; j < getCol() && equal; j++) {
        equal = Math.abs(getElement(i, j) - other.getElement(i, j)) <= .0001d;;
      }
    }
    return equal;
  }

  public Matrix transpose() {
    Matrix res = new Matrix(getCol(), getRow());
    for (int i = 0; i < getCol(); i++) {
      for (int j = 0; j < getRow(); j++) {
        res.setElement(i, j, getElement(j, i));
      }
    }
    return res;
  }

  public int getCol() {
    return data[0].length();
  }

  public Row[] getMatrixData() {
    return data;
  }

  public Matrix getEchelon() {
    int n = getRow();
    int m = getCol();
    Matrix mk = new Matrix(data);
    int pivotRow = 0;

    // avoid small number division
    for (int i = 0; i < mk.getRow(); i++) {
      double min = mk.getElement(i, 0);
      for (int j = 0; j < mk.getCol(); j++) {
        if (min > mk.getElement(i, j) && mk.getElement(i, j) != 0) {
          min = mk.getElement(i, j);
        }
      }
      mk.data[i].multiply(1.0 / min);
    }

    for (int j = 0; j < m; j++) {
      // find non-zero column
      boolean zero = true;
      for (int i = pivotRow; i < n && zero; i++) {
        zero = mk.getElement(i, j) == 0;
      }
      if (zero) {
        continue;
      }
      for (int i = pivotRow; i < n - 1; i++) {
        double pivotVal = mk.getElement(pivotRow, j);
        if (Math.abs(pivotVal - 0) <= .0001d) {
          // find row to swap
          boolean ok = false;
          for (int k = pivotRow+1; k < n && !ok; k++) {
            if (Math.abs(mk.getElement(k, j) - 0) > .0001d) {
              mk.swapRow(pivotRow, k);
              ok = true;
              i = k-1;
            }
          }
        } else {
          double t = -mk.getElement(i+1, j)/mk.getElement(pivotRow, j);
          if (t != 0) {
            mk.data[i+1] = addMul(mk.data[i+1], mk.data[pivotRow], t);
          }
        }
      }
      pivotRow++;
    }
    for(int i = 0; i < Math.min(m - 1, n); i++) {
      if (mk.getElement(i, i) != 0) {
        mk.data[i].multiply(1/mk.getElement(i, i));
      } else {
        boolean ok = false;
        for (int j = i+1; j < mk.getCol() && !ok; j++) {
          if (mk.getElement(i, j) != 0) {
            mk.data[i].multiply(1/mk.getElement(i, j));
            ok = true;
          }
        }
      }
    }
    return mk;
  }

  public Matrix getReductedEchelon() {
    int n = getRow();
    int m = getCol();
    Matrix mk = new Matrix(getEchelon().data);

    for (int j = m - Math.abs(m - n); j > 0; j--) {
      // find non-zero (pivot)
      int pivotRow = j;
      boolean ok = false;
      for (int i = Math.min(n - 1, j); i >= 0 && !ok; i--) {
        if (mk.getElement(i, j) != 0) {
          pivotRow = i;
          ok = true;
        }
      }
      if (!ok) {
        continue;
      }
      for (int i = pivotRow - 1; i >= 0; i--) {
        double t = -mk.getElement(i, j) / mk.getElement(pivotRow, j);
        mk.data[i] = addMul(mk.data[i], mk.data[pivotRow], t);
      }
    }
    return mk;
  }

  public int getRow() {
    return data.length;
  }

  @Override
  public String toString() {
    int[] align = new int[getCol()];
    for (int j = 0; j < getCol(); j++) {
      int max = 0;
      for (int i = 0; i < getRow(); i++) {
        int chars = String.format("%.2f", data[i].getElement(j)).length();
        if (max < chars) {
          max = chars;
        }
      }
      align[j] = max;
    }
    String str = "[";
    for (int i = 0; i < getRow(); i++) {
      if (i > 0) str += "\n ";
      str += "[";
      for (int j = 0; j < getCol(); j++) {
        String format = "%" + (align[j]+2) + ".2f";
        if (j > 0) str += ", ";
        str += String.format(Locale.US, format, getElement(i, j)).replaceAll("0*$", "").replaceAll("\\.$", "");
      }
      str += "]";
    }
    str += "]";
    return str;
  }

  public static Matrix identityMatrix(int n) {
    Matrix ident = new Matrix(n, n);
    for(int i = 0; i < n; i++) {
      ident.setElement(i, i, 1);
    }
    return ident;
  }

  public static Matrix subMatrix(Matrix m, int i, int j, int rows, int cols) {
    Row[] sub = new Row[rows];
    for (int k = 0; k < rows; k++) {
      sub[k] = new Row(cols);
      for (int l = 0; l < cols; l++) {
        sub[k].setElement(l, m.getElement(i+k, j+l));
      }
    }
    return new Matrix(sub);
  }

  public static Matrix from(double[][] data) {
    Matrix m = new Matrix(data.length, data[0].length);
    for (int i = 0; i < m.getRow(); i++) {
      for (int j = 0; j < m.getCol(); j++) {
        m.setElement(i, j, data[i][j]);
      }
    }
    return m;
  }
}
