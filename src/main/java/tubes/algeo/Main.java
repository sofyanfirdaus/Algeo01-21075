package tubes.algeo;

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
    Matrix system = Matrix.from(new double[][] {
    {1  , -1 , 0 ,  0 , 1  ,  3},
    {1  ,  1 , 0 , -3 , 0  ,  6},
    {2  , -1 , 0 ,  1 , -1 ,  5},
    {-1 ,  2 , 0 , -2 , -1 , -1}
    });
    System.out.println(LinearEquationSolver.solveSystemGauss(system));
  }
}
