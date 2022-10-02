package tubes.algeo.studikasus;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

import tubes.algeo.Expr;
import tubes.algeo.LinearEquationSolver;
import tubes.algeo.Matrix;

public class BicubicInterpolation {

  private final Matrix X = Matrix.from(new double[][] {
  {1 , -1 , 1 , -1 , -1 ,  1 , -1 ,  1 , 1 , -1 , 1  , -1 , -1 ,  1 , -1 ,  1},
  {1 ,  0 , 0 ,  0 , -1 ,  0 , 0  ,  0 , 1 ,  0 , 0  ,  0 , -1 ,  0 , 0  ,  0},
  {1 ,  1 , 1 ,  1 , -1 , -1 , -1 , -1 , 1 ,  1 , 1  ,  1 , -1 , -1 , -1 , -1},
  {1 ,  2 , 4 ,  8 , -1 , -2 , -4 , -8 , 1 ,  2 , 4  ,  8 , -1 , -2 , -4 , -8},
  {1 , -1 , 1 , -1 , 0  ,  0 , 0  ,  0 , 0 ,  0 , 0  ,  0 , 0  ,  0 , 0  ,  0},
  {1 ,  0 , 0 ,  0 , 0  ,  0 , 0  ,  0 , 0 ,  0 , 0  ,  0 , 0  ,  0 , 0  ,  0},
  {1 ,  1 , 1 ,  1 , 0  ,  0 , 0  ,  0 , 0 ,  0 , 0  ,  0 , 0  ,  0 , 0  ,  0},
  {1 ,  2 , 4 ,  8 , 0  ,  0 , 0  ,  0 , 0 ,  0 , 0  ,  0 , 0  ,  0 , 0  ,  0},
  {1 , -1 , 1 , -1 , 1  , -1 , 1  , -1 , 1 , -1 , 1  , -1 , 1  , -1 , 1  , -1},
  {1 ,  0 , 0 ,  0 , 1  ,  0 , 0  ,  0 , 1 ,  0 , 0  ,  0 , 1  ,  0 , 0  ,  0},
  {1 ,  1 , 1 ,  1 , 1  ,  1 , 1  ,  1 , 1 ,  1 , 1  ,  1 , 1  ,  1 , 1  ,  1},
  {1 ,  2 , 4 ,  8 , 1  ,  2 , 4  ,  8 , 1 ,  2 , 4  ,  8 , 1  ,  2 , 4  ,  8},
  {1 , -1 , 1 , -1 , 2  , -2 , 2  , -2 , 4 , -4 , 4  , -4 , 8  , -8 , 8  , -8},
  {1 ,  0 , 0 ,  0 , 2  ,  0 , 0  ,  0 , 4 ,  0 , 0  ,  0 , 8  ,  0 , 0  ,  0},
  {1 ,  1 , 1 ,  1 , 2  ,  2 , 2  ,  2 , 4 ,  4 , 4  ,  4 , 8  ,  8 , 8  ,  8},
  {1 ,  2 , 4 ,  8 , 2  ,  4 , 8  , 16 , 4 ,  8 , 16 , 32 , 8  , 16 , 32 , 64}
  });

  private Expr function;

  public BicubicInterpolation(Matrix data) {
    try {
      int n = 4;
      if (data.getRow() != n && data.getCol() != n) {
        throw new IllegalArgumentException("the data matrix must be 4x4");
      }
      Matrix fxy = new Matrix(16, 1);
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          fxy.setElement(i*4+j, 0, data.getElement(i, j));
        }
      }
      X.concatCols(fxy);
      HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(X);
      Expr.Prod[] products = new Expr.Prod[16];
      for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
          double coeff = solution.get("x" + (i*4+j+1)).getConstant();
          products[i*4+j] = Expr.prod(Expr.var("x", coeff, i), Expr.var("y", 1, j));
        }
      }
      function = new Expr(0, products);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public Expr getFunction() {
    return function;
  }

  public double apply(double x, double y) {
    HashMap<String, Double> values = new HashMap<>(Map.ofEntries(
      entry("x", x),
      entry("y", y)
    ));
    return function.evaluate(values);
  }
}
