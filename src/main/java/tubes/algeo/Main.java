package tubes.algeo;

import java.util.HashMap;
import java.util.Map;

import tubes.algeo.studikasus.BicubicInterpolation;
import tubes.algeo.studikasus.MultipleLinearRegression;
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
    {1   , 1/2d , 1/3d , 1/4d , 1/5d  ,  1/6d , 1},
    {1/2d , 1/3d , 1/4d , 1/5d , 1/6d  ,  1/7d , 0},
    {1/3d , 1/4d , 1/5d , 1/6d , 1/7d  ,  1/8d , 0},
    {1/4d , 1/5d , 1/6d , 1/7d , 1/8d  ,  1/9d , 0},
    {1/5d , 1/6d , 1/7d , 1/8d , 1/9d  , 1/10d , 0},
    {1/6d , 1/7d , 1/8d , 1/9d , 1/10d , 1/11d , 0}
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
    HashMap<String, Double> varValues = new HashMap<String, Double>(Map.ofEntries(
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

    Matrix bicubic = Matrix.from(new double[][] {
    {1  ,  2 , 3  ,  4},
    {5  ,  6 , 7  ,  8},
    {9  , 10 , 11 , 12},
    {13 , 14 , 15 , 16}
    });
    BicubicInterpolation bicin = new BicubicInterpolation(bicubic);
    System.out.println(bicin.getFunction());
    System.out.println(bicin.apply(0.5, 0.5));


    Matrix system2 = Matrix.from(new double[][] {
    {0      ,   0 , 0      ,   0 , 0      ,    0 , 1      ,   1 , 1      ,    13},
    {0      ,   0 , 0      ,   1 , 1      ,    1 , 0      ,   0 , 0      ,    15},
    {1      ,   1 , 1      ,   0 , 0      ,    0 , 0      ,   0 , 0      ,     8},
    {0      ,   0 , .04289 ,   0 , .04289 , 0.75 , .04289 , .75 , .61396 , 14.79},
    {0      , .25 , .91421 , .25 , .91421 ,  .25 , .91421 , .25 , 0      , 14.31},
    {.61396 , .75 , .04289 , .75 , .04289 ,    0 , .04289 ,   0 , 0      ,  3.81},
    {0      ,   0 , 1      ,   0 , 0      ,    1 , 0      ,   0 , 1      ,    18},
    {0      ,   1 , 0      ,   0 , 1      ,    0 , 0      ,   1 , 0      ,    12},
    {1      ,   0 , 0      ,   1 , 0      ,    0 , 1      ,   0 , 0      ,     6},
    {.04289 , .75 , .61396 ,   0 , .04289 ,  .75 , 0      ,   0 , .04289 , 10.51},
    {.91421 , .25 , 0      , .25 , .91421 ,  .25 , 0      , .25 , .91421 , 16.13},
    {.04289 ,   0 , 0      , .75 , 0      ,    0 , .61396 , .75 , .04289 ,  7.04}
    });
    System.out.println(LinearEquationSolver.solveSystemGaussJordan(system2));

    Matrix X = Matrix.from(new double[][] {
    {674  , 2175 , 4672 ,  147},
    {217  , 2404 , 3090 , 7464},
    {606  , 4551 , 1982 , 2000},
    {32   , 8265 , 7657 ,  307},
    {671  , 1648 , 4861 , 1786},
    {392  , 2841 , 378  , 4288},
    {4291 , 3749 , 221  , 1123},
    {438  , 1237 , 4817 , 3777}
    });
    double[] y = new double[] {606,4551,1982,2000,312,483,8847,2471};
    MultipleLinearRegression mlr = new MultipleLinearRegression(X, y);
    System.out.println(mlr.getFunction());
    System.out.println(mlr.apply(674, 2175, 4672, 147));

  }
}
