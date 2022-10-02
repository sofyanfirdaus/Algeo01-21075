package tubes.algeo.studikasus;

import java.security.InvalidParameterException;
import java.util.HashMap;

import tubes.algeo.Expr;
import tubes.algeo.Matrix;

public class MultipleLinearRegression {

  private Expr function;

  public MultipleLinearRegression(Matrix data, double[] y) {
    try {
      if (data.getRow() != y.length) {
        throw new InvalidParameterException("the number of y is not equal to the data");
      }
      Matrix X = new Matrix(data.getRow(), data.getCol() + 1);
      for (int i = 0; i < X.getRow(); i++) {
        X.setElement(i, 0, 1);
        for (int j = 1; j < X.getCol(); j++) {
          X.setElement(i, j, data.getElement(i, j-1));
        }
      }
      Matrix Xt = X.transpose();
      Matrix XtXinv = Xt.matMul(X).getInverseMatrixGaussJordan();
      Matrix Y = new Matrix(X.getRow(), 1);
      for (int i = 0; i < Y.getRow(); i++) {
        Y.setElement(i, 0, y[i]);
      }
      Matrix XtY = Xt.matMul(Y);
      Matrix beta = XtXinv.matMul(XtY);
      
      // construct the function
      function = new Expr(beta.getElement(0, 0));
      for (int i = 1; i < beta.getRow(); i++) {
        function.add(new Expr(0, Expr.var("x" + i, beta.getElement(i, 0))));
      }
    } catch (InvalidParameterException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public Expr getFunction() {
    return function;
  }

  public double apply(double... x) {
    double result = 0;
    try {
      if (x.length != function.getVariables().size()) {
        throw new InvalidParameterException("the number of x must be " + function.getVariables().size());
      }
      HashMap<String, Double> varValues = new HashMap<>();
      for (int i = 0; i < x.length; i++) {
        varValues.put("x" + (i + 1), x[i]);
      }
      result = function.evaluate(varValues);
    } catch (InvalidParameterException e) {
      e.printStackTrace();
      System.exit(1);
    }
    return result;
  }
}
