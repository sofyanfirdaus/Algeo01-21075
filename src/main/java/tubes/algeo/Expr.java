package tubes.algeo;

import java.util.ArrayList;

public class Expr {

  private ArrayList<Var>  variables;
  private double constant;

  public Expr(double constant, Var... variables) {
    // avoid passing by reference
    this.variables = new ArrayList<Var>();
    for (Var var: variables) {
      this.variables.add(var);
    }
    this.constant = constant;
  }

  public void add(Expr other) {
    for (int i = 0; i < other.variables.size(); i++) {
      boolean found = false;
      for (int j = 0; j < variables.size(); j++) {
        if (variables.get(j).name == other.variables.get(i).name) {
          variables.get(j).coeff += other.variables.get(i).coeff;
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
        if (variables.get(j).name == other.variables.get(i).name) {
          variables.get(j).coeff -= other.variables.get(i).coeff;
          found = true;
        }
      }
      if (!found) {
        variables.add(other.variables.get(i));
      }
    }
    constant -= other.constant;
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

  public static Var var(String name, double coeff) {
    return new Var(name, coeff);
  }

  @Override
  public String toString() {
    String s = "";
    for (int i = 0; i < variables.size(); i++) {
      if (i > 0) s += variables.get(i).toString() != "" ? "+" : "";
      s += variables.get(i).toString();
    }
    if (constant != 0) {
      String formConst = String.format("%.2f", constant);
      formConst = formConst.replaceAll("0*$", "").replaceAll("\\.$", "").replaceAll("\\,$", "");
      s += "+" + formConst;
    }
    return s.replace("+-", "-").replace("+", " + ").replace("-", " - ");
  }

  public static class Var {

    private String name;
    private double coeff;

    public Var(String name, double coeff) {
      this.name = name;
      this.coeff = coeff;
    }

    @Override
    public String toString() {
      String s = "";
      if (coeff == 0) {
        return s;
      }
      if (Math.abs(coeff - 1) > 0.001d) {
        s = String.format("%.2f", coeff);
        s = s.replaceAll("0*$", "").replaceAll("\\.$", "").replaceAll("\\,$", "");
      }
      s += name;
      return s;
    }
  }
}
