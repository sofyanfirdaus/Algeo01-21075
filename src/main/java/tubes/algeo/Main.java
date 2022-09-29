package tubes.algeo;

/**
 * Hello world!
 *
 */
public class Main {
  public static void main( String[] args ) {
    Matrix m = Matrix.from(new double[][] {
      {1, 2, 3, 0, 3, 5},
      {5, 2, 1, 1, 0, 2},
      {6, 8, 3, 2, 1, 7},
      {0, 0, 2, 8, 1, 10},
      {7, 4, 7, 0, 2, 16}
    });
    m.getEchelon().print();
    m.getReductedEchelon().print();

    Expr a = new Expr(-4, Expr.var("x", 3), Expr.var("y", -2), Expr.var("z", -1));
    Expr b = new Expr(2, Expr.var("x", 1), Expr.var("z", 2), Expr.var("w", 4));
    System.out.println(Expr.add(a, b));
  }
}
