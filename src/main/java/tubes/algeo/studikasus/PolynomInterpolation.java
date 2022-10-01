package tubes.algeo.studikasus;

import java.util.HashMap;
import java.util.Map;

import tubes.algeo.Expr;
import tubes.algeo.LinearEquationSolver;
import tubes.algeo.Matrix;

public class PolynomInterpolation {

  private Matrix system;
  private Expr function;

  public PolynomInterpolation(double[] x, double[] y) {
    try {
      if (x.length != y.length) {
        throw new IllegalArgumentException("the length of x must be the same as the length of y");
      }
      system = new Matrix(x.length, x.length + 1);

      // lhs
      for (int i = 0; i < system.getRow(); i++) {
        for (int j = 0; j < system.getCol() - 1; j++) {
          if (j == 0) {
            system.setElement(i, j, 1);
          } else {
            system.setElement(i, j, Math.pow(x[i], j));
          }
        }
      }
      // rhs
      for (int i = 0; i < y.length; i++) {
        system.setElement(i, system.getCol() - 1, y[i]);
      }

      HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(system);

      // build function
      function = new Expr(0);
      function.add(new Expr(solution.get("x1").getConstant()));
      for (int i = 1; i < system.getRow(); i++) {
        function.add(new Expr(0, Expr.var("x", solution.get("x" + (i + 1)).getConstant(), i)));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public Expr getFunction() {
    return function;
  }

  public double apply(double x) {
    return function.evaluate(new HashMap<>(Map.of("x", x)));
  }
}



