package tubes.algeo;

import tubes.algeo.studikasus.PolynomInterpolation;

/**
 * Hello world!
 *
 */
public class Main {
  public static void main( String[] args ) {
    User test = new User();
    // test.mainMenu();
    // Matrix m = test.scanMatrix();
    // System.out.println(m);
    // test.mainMenu();
    final double[] X = new double[] {
      6.567, 7, 7.258, 7.451, 7.548, 7.839, 8.161, 8.484, 8.709, 9
    };

    final double[] Y = new double[] {
      12.624, 21.807, 38.391, 54.517, 51.952, 28.228, 35.764, 20.813, 12.408, 10.534
    };
    PolynomInterpolation pol = new PolynomInterpolation(X, Y);
    System.out.println(pol.getFunction());
  }
}
