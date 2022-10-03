package tubes.algeo;

import java.util.HashMap;

import tubes.algeo.Expr.Var;

/**Solver untuk sistem persamaan <strong>linear<strong> **/
public class LinearEquationSolver {

  public static Expr solve(Expr lhs, Expr rhs, String target, HashMap<String, Expr> valueMap) {
    Expr temp = new Expr(rhs.getConstant()-lhs.getConstant());
    for (int i = 0; i < lhs.getVariables().size(); i++) {
      Var v = lhs.getVariables().get(i);
      if (!v.getName().equals(target)) {
        temp.subtract(new Expr(0, v));
      }
    }

    for (int i = 0; i < rhs.getVariables().size(); i++) {
      Var v = rhs.getVariables().get(i);
      if (!v.getName().equals(target)) {
        temp.add(new Expr(0, v));
      }
    }

    double coeff1 = lhs.getVariable(target) != null ? lhs.getVariable(target).getCoefficient() : 0;
    double coeff2 = rhs.getVariable(target) != null ? rhs.getVariable(target).getCoefficient() : 0;

    double delta = coeff1 - coeff2;

    Expr solution = new Expr(temp.getConstant());

    // substitute
    for (int i = 0; i < temp.getVariables().size(); i++) {
      Var v = temp.getVariables().get(i);
      Var copy = Expr.var(v.getName(), v.getCoefficient(), v.getDegree());
      if (valueMap.containsKey(copy.getName())) {
        solution.add(Expr.multiply(valueMap.get(copy.getName()), copy.getCoefficient()));
      } else {
        solution.add(new Expr(0, copy));
        valueMap.put(v.getName(), new Expr(0, Expr.var(v.getName())));
      }
    }

    if (delta != 0) {
      solution.multiply(1.0/delta);
      return solution;
    } else {
      return new Expr(0, Expr.var(target));
    }
  }

  public static HashMap<String, Expr> solveSystemGauss(Matrix augmentedMatrix) {
    HashMap<String, Expr> solution = new HashMap<>();
    // clone matrix
    Matrix sys = new Matrix(augmentedMatrix.getMatrixData()).getEchelon();
    // the number of the expected variables
    int numExpect = sys.getCol() - 1;
    // check wether the system has solution
    boolean zeroes = true;
    for (int i = sys.getRow() - 1; i >= 0 && zeroes; i--) {
      int j;
      for (j = 0; j < numExpect && zeroes; j++) {
        zeroes = Math.abs(sys.getElement(i, j)) <= .0001d;
      }
      if (zeroes && Math.abs(sys.getElement(i, j)) > .0001d) {
        // no solution
        return null;
      }
    }
    for (int n = numExpect; n > 0; n--) {
      // check wether the variable is a free variable
      boolean free = true;
      for (int i = sys.getRow() - 1; i >= 0 && free; i--) {
        free = Math.abs(sys.getElement(i, n-1)) <= .0001d;
      }
      if (free) {
        solution.put("x" + n, new Expr(0, Expr.var("x" + n, 1)));
      }
    }
    for (int i = sys.getRow() - 1; i >= 0; i--) {
      zeroes = true;
      Expr lhs = new Expr(0);
      Expr rhs = new Expr(0);
      int j;
      String solvable = "";
      for (j = 0; j < numExpect; j++) {
        if (Math.abs(sys.getElement(i, j)) > .0001d) {
          lhs.add(new Expr(0, Expr.var("x" + (j+1), sys.getElement(i, j))));
          zeroes = false;
          solvable = solvable == "" ? "x" + (j+1) : solvable;
        }
      }
      if (zeroes) continue;
      rhs.add(new Expr(sys.getElement(i, j)));
      solution.put(solvable, solve(lhs, rhs, solvable, solution));
    }
    return solution.size() > 0 ? solution : null;
  }

  public static HashMap<String, Expr> solveSystemGaussJordan(Matrix augmentedMatrix) {
    HashMap<String, Expr> solution = new HashMap<>();

    // clone matrix
    Matrix sys = new Matrix(augmentedMatrix.getMatrixData()).getReductedEchelon();

    // the number of the expected variables
    int numExpect = sys.getCol() - 1;
    // check wether the system has solution
    boolean zeroes = true;
    for (int i = sys.getRow() - 1; i >= 0 && zeroes; i--) {
      int j;
      for (j = 0; j < numExpect && zeroes; j++) {
        zeroes = Math.abs(sys.getElement(i, j)) <= .0001d;
      }
      if (zeroes && Math.abs(sys.getElement(i, j)) > .0001d) {
        // no solution
        return null;
      }
    }
    for (int n = numExpect; n > 0; n--) {
      // check wether the variable is a free variable
      boolean free = true;
      for (int i = sys.getRow() - 1; i >= 0 && free; i--) {
        free = Math.abs(sys.getElement(i, n-1)) <= .0001d;
      }
      if (free) {
        solution.put("x" + n, new Expr(0, Expr.var("x" + n, 1)));
      }
    }
    for (int i = sys.getRow() - 1; i >= 0; i--) {
      zeroes = true;
      Expr lhs = new Expr(0);
      Expr rhs = new Expr(0);
      int j;
      String solvable = "";
      for (j = 0; j < numExpect; j++) {
        if (Math.abs(sys.getElement(i, j)) > .0001d) {
          lhs.add(new Expr(0, Expr.var("x" + (j+1), sys.getElement(i, j))));
          zeroes = false;
          solvable = solvable == "" ? "x" + (j+1) : solvable;
        }
      }
      if (zeroes) continue;
      rhs.add(new Expr(sys.getElement(i, j)));
      solution.put(solvable, solve(lhs, rhs, solvable, solution));
    }
    return solution.size() > 0 ? solution : null;
  }

  public static HashMap<String, Expr> solveSystemCramer(Matrix augmentedMatrix) {
    Matrix lhs = Matrix.subMatrix(augmentedMatrix, 0, 0, augmentedMatrix.getRow(), augmentedMatrix.getCol() - 1);
    double[] rhs = new double[augmentedMatrix.getRow()];
    for (int i = 0; i < rhs.length; i++) {
      rhs[i] = augmentedMatrix.getElement(i, augmentedMatrix.getCol() - 1);
    }

    double det = lhs.getDeterminantGauss();

    try {
      if (det == 0) {
        throw new IllegalArgumentException("the system of equation cannot be solved using Cramer's rule (determinant = 0)");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    HashMap<String, Expr> solution = new HashMap<>();

    // cramer step
    for (int i = 0; i < lhs.getCol(); i++) {
      Matrix m = new Matrix(lhs.getMatrixData());
      m.replaceColumn(i, rhs);
      double deti = m.getDeterminantGauss();
      Expr sol = new Expr(deti/det);
      solution.put("x" + (i+1), sol);
    }

    return solution;
  }

  public static HashMap<String, Expr> solveSystemInverse(Matrix augmentedMatrix) {
    Matrix lhs = Matrix.subMatrix(augmentedMatrix, 0, 0, augmentedMatrix.getRow(), augmentedMatrix.getCol() - 1);
    Matrix rhs = new Matrix(lhs.getRow(), 1);

    for (int i = 0; i < rhs.getRow(); i++) {
      rhs.setElement(i, 0, augmentedMatrix.getElement(i, augmentedMatrix.getCol() - 1));
    }

    Matrix inverse = lhs.getInverseMatrixGaussJordan();

    if (inverse != null) {
      Matrix msol = inverse.matMul(rhs);

      HashMap<String, Expr> solution = new HashMap<>();

      for (int i = 0; i < msol.getRow(); i++) {
        Expr sol = new Expr(msol.getElement(i, 0));
        solution.put("x" + (i+1), sol);
      }

      return solution;
    }

    return null;
  }
}
