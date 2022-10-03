package tubes.algeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tubes.algeo.Expr.Var;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static tubes.algeo.DataStudiKasus.EPSILON;

/**
 * Unit test for LinerEquationSolver.
 */
public class LinearEquationSolverTest {

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
  public void testSPL_D1_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.D.system1);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution1, solution);
  }

  @Test
  public void testSPL_D1_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.D.system1);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution1, solution);
  }

  @Test
  public void testSPL_D1_Inverse() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemInverse(DataStudiKasus.SPL.D.system1);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution1, solution);
  }

  @Test
  public void testSPL_D1_Cramer() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemCramer(DataStudiKasus.SPL.D.system1);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution1, solution);
  }

  @Test
  public void testSPL_D2_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.D.system2);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution2, solution);
  }

  @Test
  public void testSPL_D2_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.D.system2);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution2, solution);
  }

  @Test
  public void testSPL_D2_Inverse() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemInverse(DataStudiKasus.SPL.D.system2);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution2, solution);
  }

  @Test
  public void testSPL_D2_Cramer() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemCramer(DataStudiKasus.SPL.D.system2);
    assertSolutionEqual(DataStudiKasus.SPL.D.solution2, solution);
  }

  @Test
  public void testSPL_E_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.E.system);
    assertSolutionEqual(DataStudiKasus.SPL.E.solution, solution);
  }

  @Test
  public void testSPL_E_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.E.system);
    assertSolutionEqual(DataStudiKasus.SPL.E.solution, solution);
  }

  @Test
  public void testSPL_F_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.F.system);
    assertSolutionEqual(DataStudiKasus.SPL.F.solution, solution);
  }

  @Test
  public void testSPL_F_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.F.system);
    assertSolutionEqual(DataStudiKasus.SPL.F.solution, solution);
  }

  @Test
  public void testSPL_G_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.G.system);
    assertSolutionEqual(DataStudiKasus.SPL.G.solution, solution);
  }

  @Test
  public void testSPL_G_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.G.system);
    assertSolutionEqual(DataStudiKasus.SPL.G.solution, solution);
  }

  @Test
  public void testSPL_G_Inverse() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemInverse(DataStudiKasus.SPL.G.system);
    assertSolutionEqual(DataStudiKasus.SPL.G.solution, solution);
  }

  @Test
  public void testSPL_G_Cramer() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemCramer(DataStudiKasus.SPL.G.system);
    assertSolutionEqual(DataStudiKasus.SPL.G.solution, solution);
  }

  @Test
  public void testSPL_H_Gauss() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(DataStudiKasus.SPL.H.system);
    assertSolutionEqual(DataStudiKasus.SPL.H.solution, solution);
  }

  @Test
  public void testSPL_H_GaussJordan() {
    HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(DataStudiKasus.SPL.H.system);
    assertSolutionEqual(DataStudiKasus.SPL.H.solution, solution);
  }
}
