package tubes.algeo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tubes.algeo.studikasus.PolynomInterpolation;
import static tubes.algeo.DataStudiKasus.EPSILON;

public class PolynomialInterpolationTest {

    private final double[] X_A = DataStudiKasus.InterpolasiPolinomial.A.X;
    private final double[] Y_A = DataStudiKasus.InterpolasiPolinomial.A.Y;

    private final PolynomInterpolation pol_A = new PolynomInterpolation(X_A, Y_A);

  @Test
  public void polInter_A1() {
    assertEquals(DataStudiKasus.InterpolasiPolinomial.A.uji(0.2), pol_A.apply(0.2), EPSILON);
  }

  @Test
  public void polInter_A2() {
    assertEquals(DataStudiKasus.InterpolasiPolinomial.A.uji(0.55), pol_A.apply(0.55), EPSILON);
  }

  @Test
  public void polInter_A3() {
    assertEquals(DataStudiKasus.InterpolasiPolinomial.A.uji(0.85), pol_A.apply(0.85), EPSILON);
  }

  @Test
  public void polInter_A4() {
    assertEquals(DataStudiKasus.InterpolasiPolinomial.A.uji(1.28), pol_A.apply(1.28), EPSILON);
  }

    private final double[] X_B = DataStudiKasus.InterpolasiPolinomial.B.X;
    private final double[] Y_B = DataStudiKasus.InterpolasiPolinomial.B.Y;

    private final PolynomInterpolation pol_B = new PolynomInterpolation(X_B, Y_B);

  @Test
  public void polInter_B1() {
    assertEquals(DataStudiKasus.InterpolasiPolinomial.B.uji(7 + 16.0/31.0), pol_B.apply(7 + 16/31), EPSILON);
  }

  @Test
  public void polInter_B2() {
    assertEquals(DataStudiKasus.InterpolasiPolinomial.B.uji(8 + 10.0/31.0), pol_B.apply(8 + 10.0/31.0), EPSILON);
  }

  @Test
  public void polInter_B3() {
    assertEquals(DataStudiKasus.InterpolasiPolinomial.B.uji(9 + 5.0/30.0), pol_B.apply(9 + 5.0/30.0), EPSILON);
  }
}
