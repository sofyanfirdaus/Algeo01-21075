package tubes.algeo;

import java.util.HashMap;
import java.util.Map;

import tubes.algeo.studikasus.PolynomInterpolation;

import static java.util.Map.entry;

/**
 * Hello world!
 *
 */
public class Main {
  public static void main( String[] args ) {
    // Matrix m = Matrix.from(new double[][] {
    //   {1, 2, 3, 0, 3, 5},
    //   {5, 2, 1, 1, 0, 2},
    //   {6, 8, 3, 2, 1, 7},
    //   {0, 0, 2, 8, 1, 10},
    //   {7, 4, 7, 0, 2, 16}
    // });
    // m.getEchelon().print();
    // m.getReductedEchelon().print();

    // Expr a = new Expr(-4, Expr.var("x", 2), Expr.var("y", -2));
    // Expr b = new Expr(2, Expr.var("x", 1), Expr.var("z", 2));

    // final HashMap<String, Expr> valueMap = new HashMap(Map.ofEntries(
    //   entry("y", new Expr(2, Expr.var("t", 2)))
    // ));
    // System.out.println(a + " = " + b);
    // System.out.println(LinearEquationSolver.solve(a, b, "x", valueMap));
    // System.out.println(Expr.multiply(a, 2));
    Matrix system = Matrix.from(new double[][] {
    { 5, 10, 6, 15, 4, 3, 24, 12, -9, 8, 72 },
    { 5, 0, 0, 5, 2, 4, 0, 3, 0, 4, 42 },
    { 9, 1, 0, 0, 0, 0, 3, 8, 7, 5, 25 },
    { 3, 5, 7, 1, 2, 5, 9, 7, 4, 1, 5 },
    { 2, 3, 4, 2, 3, 8, 7, 0, 4, 2, 3 },
    { 2, 0, 6, 8, 5, 2, 3, 5, 7, 0, 6 },
    { 5, 2, 2, 0, 21, 5, 8, 70000, 5, 2, 3 },
    { 3, 3, 3, 5, 8, 7, 1, 1, 5, 6, 3 },
    { 8, 0, 9, 8, 7, 0, 8, 5, 2, 1, 5 },
    { 1, 5, 8, 5, 6, 3, 5, 7, 8, 9, 10 }
    });
    HashMap<String, Expr> solution1 = LinearEquationSolver.solveSystemGauss(system);
    HashMap<String, Expr> solution2 = LinearEquationSolver.solveSystemGaussJordan(system);
    HashMap<String, Expr> solution3 = LinearEquationSolver.solveSystemCramer(system);
    HashMap<String, Expr> solution4 = LinearEquationSolver.solveSystemInverse(system);
    System.out.println(solution1);
    System.out.println(solution2);
    System.out.println(solution3);
    System.out.println(solution4);
    System.out.println(system);

    Expr testEval = new Expr(2, 
      Expr.var("x", 1, 2),
      Expr.var("y", 2)
    );
    System.out.println(testEval);
    HashMap<String, Double> varValues = new HashMap(Map.ofEntries(
      entry("x", 2d),
      entry("y", 5d)
    ));
    // x^2 + 2y + 2 = 2^2 + 2*5 + 2 = 16
    System.out.println(testEval.evaluate(varValues));
    System.out.println(Expr.add(testEval, new Expr(-3, Expr.var("x", 3))));

    PolynomInterpolation polin = new PolynomInterpolation(
      new double[] {1, 4, 12, 15},
      new double[] {1, 20, 45, 56}
    );
    System.out.println(polin.getFunction());
    System.out.println(polin.apply(20));
  }
}
