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
        Matrix m = new Matrix(3, 3);
        m.setElements(new Row[] {
            new Row(new double[] { 3,5,2,1,8 }),
            new Row(new double[] { 4,8,0,3,2 }),
            new Row(new double[] { 0,5,2,7,0 }),
            new Row(new double[] { 0,0,0,3,1 }),
            new Row(new double[] { 8,7,0,3,2 })
        });

        System.out.println(m.getDeterminantGauss());
    }
}
