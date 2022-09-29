package tubes.algeo;

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
      _lhs.getVariables().removeIf(x -> x.getName() == var);
      _rhs.getVariables().removeIf(x -> x.getName() == var);
      _rhs.subtract(_lhs);
      _rhs.multiply(1/varCoeff);
      return _rhs;
    }
    return null;
  }
}
