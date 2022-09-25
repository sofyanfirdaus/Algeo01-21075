import tubes.algeo.Row;
import tubes.algeo.Matrix;

public class RowTest {
    public static void main(String[] args) {
        // Row row = new Row(10);
        // System.out.println(row);
        // Row row2 = new Row(new double[] {1, 2, 3, 4, 4, 3, 2, 1, 0, 0});
        // row.add(row2);
        // System.out.println(row);
        // row.multiply(1/3d);
        // System.out.println(row);
        Matrix m1 = new Matrix(5, 3);
        m1.setElements(new Row[] {
            new Row(new double[] { 3,5,2 }),
            new Row(new double[] { 4,8,0 }),
            new Row(new double[] { 0,5,2 }),
            new Row(new double[] { 1,2,1 }),
            new Row(new double[] { 8,7,0 })
        });
        Matrix m2 = new Matrix(3, 5);
        m2.setElements(new Row[] {
            new Row(new double[] { 3,5,2,1,4 }),
            new Row(new double[] { 4,8,0,4,0 }),
            new Row(new double[] { 0,5,2,4,1 })
        });
        m1 = m1.matMul(m2);
        m1.print();

        System.out.println(m1.getDeterminantCofactor());

        Matrix m3 = new Matrix(3, 3);
        m3.setElements(new Row[] {
            new Row(new double[] { 1,6,2 }),
            new Row(new double[] { 5,9,1 }),
            new Row(new double[] { 9,5,8 })
        });
        System.out.println(m3.getDeterminantGauss());
        m3.getInverseMatrixGaussJordan().print();
        m3.getInverseMatrixAdj().print();

        Matrix m4 = new Matrix(m3.getMatrixData());
        m4.concatCols(m3);
        m4.print();

        Matrix m5 = new Matrix(m1.getMatrixData());
        m5.concatRow(m1);
        m5.print();

        Matrix.subMatrix(m5, 2, 1, 3, 4).print();
    }
}
