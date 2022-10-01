package tubes.algeo;

import static org.junit.Assert.assertEquals;

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

    
        HashMap<String, Double> solution = new HashMap(Map.ofEntries(
            entry("x1", 0),
            entry("x2", 3),
            entry("x3", 1),
            entry("x4", 2)
          ));

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((system == null) ? 0 : system.hashCode());
        result = prime * result + ((solution == null) ? 0 : solution.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LinearEquationSolverTest other = (LinearEquationSolverTest) obj;
        if (system == null) {
            if (other.system != null)
                return false;
        } else if (!system.equals(other.system))
            return false;
        if (solution == null) {
            if (other.solution != null)
                return false;
        } else if (!solution.equals(other.solution))
            return false;
        return true;
    }

    @Test
    public void testSystemGauss() {
        HashMap<String, Expr> solution1 = LinearEquationSolver.solveSystemGauss(system);
        assertEquals(solution, solution1);
    }

    @Test
    public void testSystemGaussJordan() {
        HashMap<String, Expr> solution2 = LinearEquationSolver.solveSystemGaussJordan(system);
        assertEquals(solution, solution2);
    }

    @Test
    public void testSystemGaussCramer() {
        HashMap<String, Expr> solution3 = LinearEquationSolver.solveSystemCramer(system);
        assertEquals(solution, solution3);
    }

    @Test
    public void testSystemGaussInverse() {
        HashMap<String, Expr> solution4 = LinearEquationSolver.solveSystemInverse(system);
        assertEquals(solution, solution4);
    }
}
