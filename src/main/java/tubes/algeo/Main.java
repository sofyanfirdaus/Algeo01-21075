package tubes.algeo;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

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

    Expr a = new Expr(-4, Expr.var("x", 2), Expr.var("y", -2));
    Expr b = new Expr(2, Expr.var("x", 1), Expr.var("z", 2));

    final HashMap<String, Expr> valueMap = new HashMap(Map.ofEntries(
      entry("y", new Expr(2, Expr.var("t", 2)))
    ));
    // System.out.println(a + " = " + b);
    System.out.println(LinearEquationSolver.solve(a, b, "x", valueMap));
    // System.out.println(Expr.multiply(a, 2));
  }
}
