package tubes.algeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tubes.algeo.Expr.Var;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ActionMap;

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

  public final double EPSILON = .1d;

  HashMap<String, Expr> solution = new HashMap(Map.ofEntries(
    entry("x1", new Expr(0)),
    entry("x2", new Expr(3)),
    entry("x3", new Expr(1)),
    entry("x4", new Expr(2))
  ));

  public void assertSolutionEqual(HashMap<String,Expr> expected, HashMap<String, Expr> actual) {
    if (expected == null) {
      assertEquals(expected, actual);
    } else {
      assertNotNull(actual);
      for (String k : expected.keySet()) {
        Expr exp = expected.get(k);
        Expr act = actual.get(k);
        assertTrue(actual.containsKey(k));
        if (exp.getVariables().size() > 0) {
          assertEquals(exp.getVariables().size(), act.getVariables().size());
          Collections.sort(exp.getVariables(), Comparator.comparing(Var::getName));
          Collections.sort(act.getVariables(), Comparator.comparing(Var::getName));
          for (int i = 0; i < exp.getVariables().size(); i++) {
            Var vExp = exp.getVariables().get(i);
            Var vAct = exp.getVariables().get(i);
            assertEquals(vExp.getName(), vAct.getName());
            assertEquals(vExp.getDegree(), vAct.getDegree());
            assertEquals(vExp.getCoefficient(), vAct.getCoefficient(), EPSILON);
          }
        }
        assertEquals(exp.getConstant(), act.getConstant(), EPSILON);
      }
    }
  }

  @Test
  public void testSPL_A_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.A.system);
    assertSolutionEqual(DataStudiKasus.SPL.A.solution, solution);
  }

  @Test
  public void testSPL_A_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.A.system);
    assertSolutionEqual(DataStudiKasus.SPL.A.solution, solution);
  }

  @Test
  public void testSPL_B_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.B.system);
    assertSolutionEqual(DataStudiKasus.SPL.B.solution, solution);
  }

  @Test
  public void testSPL_B_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.B.system);
    assertSolutionEqual(DataStudiKasus.SPL.B.solution, solution);
  }

  @Test
  public void testSPL_C_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.C.system);
    assertSolutionEqual(DataStudiKasus.SPL.C.solution, solution);
  }

  @Test
  public void testSPL_C_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.C.system);
    assertSolutionEqual(DataStudiKasus.SPL.C.solution, solution);
  }

  @Test
  public void testSPL_D_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.D.system);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution, solution);
  }

  @Test
  public void testSPL_D_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.D.system);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution, solution);
  }

  @Test
  public void testSPL_D_Inverse() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemInverse(DataStudiKasus.SPL.D.system);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution, solution);
  }

  @Test
  public void testSPL_D_Cramer() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemCramer(DataStudiKasus.SPL.D.system);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution, solution);
  }

  @Test
  public void testSystemGauss() {
    HashMap<String, Expr> solution1 = LinearEquationSolver.solveSystemGauss(system);
    assertSolutionEqual(solution, solution1);
  }

  @Test
  public void testSystemGaussJordan() {
    HashMap<String, Expr> solution2 = LinearEquationSolver.solveSystemGaussJordan(system);
    assertSolutionEqual(solution, solution2);
  }

  @Test
  public void testSystemGaussCramer() {
    HashMap<String, Expr> solution3 = LinearEquationSolver.solveSystemCramer(system);
    assertSolutionEqual(solution, solution3);
  }

  @Test
  public void testSystemGaussInverse() {
    HashMap<String, Expr> solution4 = LinearEquationSolver.solveSystemInverse(system);
    assertSolutionEqual(solution, solution4);
  }
}
