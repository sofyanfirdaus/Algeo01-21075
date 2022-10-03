package tubes.algeo;

import java.util.HashMap;

import tubes.algeo.Expr.Var;

/**Solver untuk sistem persamaan <strong>linear<strong> **/
public class LinearEquationSolver {

  public static Expr solve(Expr lhs, Expr rhs, String var, HashMap<String, Expr> valueMap) {
    Expr _lhs = new Expr(lhs.getConstant(), lhs.getVariables().toArray(new Expr.Var[lhs.getVariables().size()]));
    Expr _rhs = new Expr(rhs.getConstant(), rhs.getVariables().toArray(new Expr.Var[rhs.getVariables().size()]));


    for (int i = 0; i < lhs.getVariables().size(); i++) {
      Var sub = lhs.getVariables().get(i);
      String name = sub.getName();
      if (valueMap.containsKey(name)) {
        double coeff = sub.getCoefficient();
        _lhs.add(Expr.add(Expr.multiply(valueMap.get(name), coeff), new Expr(0, Expr.var(sub.getName(), -coeff))));
      } else if (!name.equals(var)) {
        String newName = name.replace("x", "p");
        valueMap.put(name, new Expr(0, Expr.var(newName)));
        i--;
      }
    }

    for (int i = 0; i < rhs.getVariables().size(); i++) {
      Var sub = rhs.getVariables().get(i);
      String name = sub.getName();
      if (valueMap.containsKey(name)) {
        double coeff = sub.getCoefficient();
        _rhs.add(Expr.add(Expr.multiply(valueMap.get(name), coeff), new Expr(0, Expr.var(sub.getName(), -coeff))));
      } else if (name != var) {
        String newName = name.replace("x", "p");
        valueMap.put(name, new Expr(0, Expr.var(newName)));
        i--;
      }
    }

    double varA = _lhs.getVariable(var) != null ? _lhs.getVariable(var).getCoefficient() : 0;
    double varB = _rhs.getVariable(var) != null ? _rhs.getVariable(var).getCoefficient() : 0;
    double varCoeff = varA - varB;

    if (varCoeff != 0) {
      _lhs.getVariables().removeIf(x -> x.getName().equals(var));
      _rhs.getVariables().removeIf(x -> x.getName().equals(var));
      _rhs.subtract(_lhs);
      _rhs.multiply(1/varCoeff);
      return _rhs;
    }

    return new Expr(0, Expr.var(var.replace("x", "p")));
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
        zeroes = Math.abs(sys.getElement(i, j) - 0) <= .0001d;
      }
      if (zeroes && Math.abs(sys.getElement(i, j) - 0) > .0001d) {
        // no solution
        return null;
      }
    }
    for (int n = numExpect; n > 0; n--) {
      // check wether the variable is a free variable
      boolean free = true;
      for (int i = sys.getRow() - 1; i >= 0 && free; i--) {
        free = Math.abs(sys.getElement(i, n-1) - 0) <= .0001d;
      }
      if (free) {
        solution.put("x" + n, new Expr(0, Expr.var("p" + n, 1)));
      }
    }
    for (int i = sys.getRow() - 1; i >= 0; i--) {
      zeroes = true;
      Expr lhs = new Expr(0);
      Expr rhs = new Expr(0);
      int j;
      String solvable = "";
      for (j = 0; j < numExpect; j++) {
        if (Math.abs(sys.getElement(i, j) - 0) > .0001d) {
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
        zeroes = Math.abs(sys.getElement(i, j) - 0) <= .0001d;
      }
      if (zeroes && Math.abs(sys.getElement(i, j) - 0) > .0001d) {
        // no solution
        return null;
      }
    }
    for (int n = numExpect; n > 0; n--) {
      // check wether the variable is a free variable
      boolean free = true;
      for (int i = sys.getRow() - 1; i >= 0 && free; i--) {
        free = Math.abs(sys.getElement(i, n-1) - 0) <= .0001d;
      }
      if (free) {
        solution.put("x" + n, new Expr(0, Expr.var("p" + n, 1)));
      }
    }
    for (int i = sys.getRow() - 1; i >= 0; i--) {
      zeroes = true;
      Expr lhs = new Expr(0);
      Expr rhs = new Expr(0);
      int j;
      String solvable = "";
      for (j = 0; j < numExpect; j++) {
        if (Math.abs(sys.getElement(i, j) - 0) > .0001d) {
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
