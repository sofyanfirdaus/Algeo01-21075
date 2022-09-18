import tubes.algeo.*;

public class RowTest {
    public static void main(String[] args) {
        Row row = new Row(10);
        System.out.println(row);
        Row row2 = new Row(new double[] {1, 2, 3, 4, 4, 3, 2, 1, 0, 0});
        row.add(row2);
        System.out.println(row);
        row.multiply(1/3d);
        System.out.println(row);
    }
}
