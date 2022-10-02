package tubes.algeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

/**
 * Unit test for LinerEquationSolver.
 */
public class LinearEquationSolverTest {

  Matrix system = Matrix.from(new double[][] {
  { 0, 1, 0, -1, 1 },
  { 2, 5, -7, -5, -2 },
  { 2, -1, 1, 3, 4 },
  { 5, 2, -4, 2, 6 }
  });

  public final double EPSILON = .0001d;

  HashMap<String, Expr> solution = new HashMap(Map.ofEntries(
    entry("x1", new Expr(0)),
    entry("x2", new Expr(3)),
    entry("x3", new Expr(1)),
    entry("x4", new Expr(2))
  ));

  public boolean solutionEqual(HashMap<String,Expr> expected, HashMap<String, Expr> actual) {
    for (String k : expected.keySet()) {
      if (!actual.containsKey(k)) {
        return false;
      }
      if (Math.abs(actual.get(k).getConstant() - expected.get(k).getConstant()) > EPSILON) {
        return false;
      }
    }
    return true;
  }

  @Test
  public void testSystemGauss() {
    HashMap<String, Expr> solution1 = LinearEquationSolver.solveSystemGauss(system);
    assertTrue(solutionEqual(solution, solution1));
  }

  @Test
  public void testSystemGaussJordan() {
    HashMap<String, Expr> solution2 = LinearEquationSolver.solveSystemGaussJordan(system);
    assertTrue(solutionEqual(solution, solution2));
  }

  @Test
  public void testSystemGaussCramer() {
    HashMap<String, Expr> solution3 = LinearEquationSolver.solveSystemCramer(system);
    assertTrue(solutionEqual(solution, solution3));
  }

  @Test
  public void testSystemGaussInverse() {
    HashMap<String, Expr> solution4 = LinearEquationSolver.solveSystemInverse(system);
    assertTrue(solutionEqual(solution, solution4));
  }
}
