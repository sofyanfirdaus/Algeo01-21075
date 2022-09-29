package tubes.algeo;

import java.security.InvalidParameterException;
import java.util.HashMap;

import tubes.algeo.Expr.Var;

public class LinearEquationSolver {

  public static Expr solve(Expr lhs, Expr rhs, String var, HashMap<String, Expr> valueMap) {
    Expr _lhs = new Expr(lhs.getConstant(), lhs.getVariables().toArray(new Expr.Var[lhs.getVariables().size()]));
    Expr _rhs = new Expr(rhs.getConstant(), rhs.getVariables().toArray(new Expr.Var[rhs.getVariables().size()]));
    for (int i = 0; i < _lhs.getVariables().size(); i++) {
      Var sub = _lhs.getVariables().get(i);
      String name = sub.getName();
      if (valueMap.containsKey(name)) {
        double coeff = sub.getCoefficient();
        _lhs.add(Expr.add(Expr.multiply(valueMap.get(name), coeff), new Expr(0, Expr.var(sub.getName(), -coeff))));
        i--;
      }
    }
    for (int i = 0; i < _rhs.getVariables().size(); i++) {
      Var sub = _rhs.getVariables().get(i);
      String name = sub.getName();
      if (valueMap.containsKey(name)) {
        double coeff = sub.getCoefficient();
        _rhs.add(Expr.add(Expr.multiply(valueMap.get(name), coeff), new Expr(0, Expr.var(sub.getName(), -coeff))));
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
    return null;
  }

  public static HashMap<String, Expr> solveSystemGauss(Matrix augmentedMatrix) {
    try {
      if (augmentedMatrix.getRow() + 1 != augmentedMatrix.getCol()) {
        throw new InvalidParameterException("matrix doesn't represent system of linear equations");
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    HashMap<String, Expr> solution = new HashMap<>();
    // clone matrix
    Matrix sys = new Matrix(augmentedMatrix.getMatrixData()).getEchelon();
    // check free variable
    int firstZero = -1;
    for (int i = 0; i < sys.getRow(); i++) {
      boolean zero = true;
      int j;
      for (j = i; j < sys.getCol() - 1 && zero; j++) {
        zero = sys.getElement(i, j) == 0;
      }
      if (zero) {
        if (sys.getElement(i, j) != 0) {
          // no solution
          return null;
        }
        firstZero = firstZero == -1 ? i : firstZero;
        solution.put("x" + (i+1), new Expr(0, Expr.var("p" + (i+1), 1)));
      }
    }
    // solve the rest
    int startIdx = firstZero == -1 ? sys.getRow() - 1 : firstZero - 1;
    for (int i = startIdx; i >= 0; i--) {
      Expr lhs = new Expr(0);
      Expr rhs = new Expr(sys.getElement(i, sys.getCol() - 1));
      for (int j = i; j < sys.getCol() - 1; j++) {
        lhs.add(new Expr(0, Expr.var("x" + (j+1), sys.getElement(i, j))));
      }
      Expr sol = solve(lhs, rhs, "x" + (i+1), solution);
      solution.put("x" + (i+1), sol);
    }
    return solution;
  }

  public static HashMap<String, Expr> solveSystemGaussJordan(Matrix augmentedMatrix) {
    try {
      if (augmentedMatrix.getRow() + 1 != augmentedMatrix.getCol()) {
        throw new InvalidParameterException("matrix doesn't represent system of linear equations");
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    HashMap<String, Expr> solution = new HashMap<>();
    // clone matrix
    Matrix sys = new Matrix(augmentedMatrix.getMatrixData()).getReductedEchelon();
    // check free variable
    int firstZero = -1;
    for (int i = 0; i < sys.getRow(); i++) {
      boolean zero = true;
      int j;
      for (j = i; j < sys.getCol() - 1 && zero; j++) {
        zero = sys.getElement(i, j) == 0;
      }
      if (zero) {
        if (sys.getElement(i, j) != 0) {
          // no solution
          return null;
        }
        firstZero = firstZero == -1 ? i : firstZero;
        solution.put("x" + (i+1), new Expr(0, Expr.var("p" + (i+1), 1)));
      }
    }
    // solve the rest
    int startIdx = firstZero == -1 ? sys.getRow() - 1 : firstZero - 1;
    for (int i = startIdx; i >= 0; i--) {
      Expr lhs = new Expr(0);
      Expr rhs = new Expr(sys.getElement(i, sys.getCol() - 1));
      for (int j = i; j < sys.getCol() - 1; j++) {
        lhs.add(new Expr(0, Expr.var("x" + (j+1), sys.getElement(i, j))));
      }
      Expr sol = solve(lhs, rhs, "x" + (i+1), solution);
      solution.put("x" + (i+1), sol);
    }
    return solution;
  }
}
