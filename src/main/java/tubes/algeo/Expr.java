package tubes.algeo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;

public class Expr {

  public static class Var {

    private String name;
    private double coeff;
    private int degree;

    public Var(String name, double coeff) {
      this(name, coeff, 1);
    }

    public Var(String name, double coeff, int degree) {
      this.name = name;
      this.coeff = coeff;
      this.degree = degree;
    }

    public String getName() {
      return name;
    }

    public double getCoefficient() {
      return coeff;
    }

    public int getDegree() {
      return degree;
    }

    public void setDegree(int degree) {
      this.degree = degree;
    }

    public double evaluate(double varValue) {
      return coeff * Math.pow(varValue, degree);
    }

    @Override
    public String toString() {
      String s = "";
      if (coeff == 0) {
        return s;
      }
      if (Math.abs(coeff - 1) > 0.001d) {
        s = String.format(Locale.US, "%.2f", coeff);
        s = s.replaceAll("0*$", "").replaceAll("\\.$", "");
      }
      s += name;
      if (degree > 1) {
        s += "^" + degree;
      }
      return s;
    }
  }
  public static Expr add(Expr a, Expr b) {
    Expr c = new Expr(a.constant, a.variables.toArray(new Var[a.variables.size()]));
    c.add(b);
    return c;
  }

  public static Expr subtract(Expr a, Expr b) {
    Expr c = new Expr(a.constant, a.variables.toArray(new Var[a.variables.size()]));
    c.subtract(b);
    return c;
  }

  public static Expr multiply(Expr expr, double n) {
    Expr c = new Expr(expr.constant, expr.variables.toArray(new Var[expr.variables.size()]));
    c.multiply(n);
    return c;
  }

  public static Var var(String name, double coeff) {
    return var(name, coeff, 1);
  }

  public static Var var(String name, double coeff, int degree) {
    return new Var(name, coeff, degree);
  }

  private ArrayList<Var> variables;

  private double constant;

  public Expr(double constant, Var... variables) {
    // avoid passing by reference
    this.variables = new ArrayList<Var>();
    for (Var var: variables) {
      if (var.getCoefficient() != 0) {
        this.variables.add(Expr.var(var.getName(), var.getCoefficient(), var.getDegree()));
      }
    }
    this.constant = constant;
  }

  public double getConstant() {
    return constant;
  }

  public ArrayList<Var> getVariables() {
    return variables;
  }

  public Var getVariable(String name) {
    for (Var var: variables) {
      if (var.name.equals(name)) {
        return var;
      }
    }
    return null;
  }

  public void add(Expr other) {
    for (int i = 0; i < other.variables.size(); i++) {
      boolean found = false;
      for (int j = 0; j < variables.size(); j++) {
        Var var = variables.get(j);
        Var var2 = other.variables.get(i);
        if (var.name == var2.name && var.degree == var2.degree) {
          var.coeff += var2.coeff;
          if (var.coeff == 0) {
            variables.remove(j);
          }
          found = true;
        }
      }
      if (!found) {
        variables.add(other.variables.get(i));
      }
    }
    constant += other.constant;
  }

  public void subtract(Expr other) {
    for (int i = 0; i < other.variables.size(); i++) {
      boolean found = false;
      for (int j = 0; j < variables.size(); j++) {
        Var var = variables.get(j);
        Var var2 = other.variables.get(i);
        if (var.name == var2.name && var.degree == var2.degree) {
          var.coeff -= var2.coeff;
          if (var.coeff == 0) {
            variables.remove(j);
          }
          found = true;
        }
      }
      if (!found) {
        variables.add(new Var(other.variables.get(i).name, -other.variables.get(i).coeff));
      }
    }
    constant -= other.constant;
  }

  public void multiply(double n) {
    variables.forEach((var) -> {
      var.coeff *= n;
    });
    constant *= n;
  }

  public double evaluate(HashMap<String, Double> varValues) {
    // check hash map completeness
    double result = constant;
    try {
      for (Var var : variables) {
        if (!varValues.containsKey(var.getName())) {
          throw new IllegalArgumentException("all variables must be mapped");
        }
        result += var.evaluate(varValues.get(var.getName()));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
    return result;
  }

  @Override
  public String toString() {
    // sort variabel
    Collections.sort(variables, Comparator.comparing(Var::getDegree).reversed().thenComparing(Var::getName));
    String s = "";
    for (int i = 0; i < variables.size(); i++) {
      if (i > 0) s += variables.get(i).toString() != "" ? "+" : "";
      s += variables.get(i);
    }
    String formConst = String.format(Locale.US, "%.2f", constant);
    formConst = formConst.replaceAll("0*$", "").replaceAll("\\.$", "");
    if (constant != 0) {
      s += "+" + formConst;
    }
    if (variables.size() == 0) {
      return formConst;
    }
    return s.replace("+-", "-").replace("+", " + ").replace("-", " - ").trim().replaceAll("^- ", "-");
  }
}
