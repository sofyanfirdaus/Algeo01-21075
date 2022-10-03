package tubes.algeo;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

public class DataStudiKasus {
  
  public static final double EPSILON = 0.1;

  public static class SPL {

    public static class A {
      public static final Matrix system = Matrix.from(new double[][] {
      {1 ,  1 , -1 , -1 ,  1},
      {2 ,  5 , -7 , -5 , -2},
      {2 , -1 ,  1 ,  3 ,  4},
      {5 ,  2 , -4 ,  2 ,  6}
      });
      public static final HashMap<String, Expr> solution = null;
    }

    public static class B {
      public static final Matrix system = Matrix.from(new double[][] {
      {1  , -1 , 0 ,  0 , 1  ,  3},
      {1  ,  1 , 0 , -3 , 0  ,  6},
      {2  , -1 , 0 ,  1 , -1 ,  5},
      {-1 ,  2 , 0 , -2 , -1 , -1}
      });
      public static final HashMap<String, Expr> solution = new HashMap<>(Map.ofEntries(
        entry("x1", new Expr(3, Expr.var("x5", 1))),
        entry("x2", new Expr(0, Expr.var("x5", 2))),
        entry("x3", new Expr(0, Expr.var("x3", 1))),
        entry("x4", new Expr(-1, Expr.var("x5", 1))),
        entry("x5", new Expr(0, Expr.var("x5", 1)))
      ));
    }

    public static class C {
      public static final Matrix system = Matrix.from(new double[][] {
      {0 , 1 , 0 , 0 , 1 , 0 , 2},
      {0 , 0 , 0 , 1 , 1 , 0 , -1},
      {0 , 1 , 0 , 0 , 0 , 1 , 1}
      });
      public static final HashMap<String, Expr> solution = new HashMap<>(Map.ofEntries(
        entry("x1", new Expr(0, Expr.var("x1", 1))),
        entry("x2", new Expr(1, Expr.var("x6", -1))),
        entry("x3", new Expr(0, Expr.var("x3", 1))),
        entry("x4", new Expr(-2, Expr.var("x6", -1))),
        entry("x5", new Expr(1, Expr.var("x6", 1))),
        entry("x6", new Expr(0, Expr.var("x6", 1)))
      ));
    }

    public static class D {

      public static final Matrix system1 = Matrix.from(new double[][] {
      {1/1d, 1/2d, 1/3d, 1/4d, 1/5d,  1/6d,  1},
      {1/2d, 1/3d, 1/4d, 1/5d, 1/6d,  1/7d,  0},
      {1/3d, 1/4d, 1/5d, 1/6d, 1/7d,  1/8d,  0},
      {1/4d, 1/5d, 1/6d, 1/7d, 1/8d,  1/9d,  0},
      {1/5d, 1/6d, 1/7d, 1/8d, 1/9d,  1/10d, 0},
      {1/6d, 1/7d, 1/8d, 1/9d, 1/10d, 1/11d, 0}
      });
      public static final HashMap<String, Expr> solution1 = new HashMap<>(Map.ofEntries(
        entry("x1", new Expr(36)),
        entry("x2", new Expr(-630)),
        entry("x3", new Expr(3360)),
        entry("x4", new Expr(-7560)),
        entry("x5", new Expr(7560)),
        entry("x6", new Expr(-2772))
      ));
      public static final Matrix system2 = Matrix.from(new double[][] {
      {1/1d,   1/2d, 1/3d,   1/4d, 1/5d,   1/6d, 1/7d,   1/8d, 1/9d,  1/10d, 1},
      {1/2d,   1/3d, 1/4d,   1/5d, 1/6d,   1/7d, 1/8d,   1/9d, 1/10d, 1/11d, 0},
      {1/3d,   1/4d, 1/5d,   1/6d, 1/7d,   1/8d, 1/9d,  1/10d, 1/11d, 1/12d, 0},
      {1/4d,   1/5d, 1/6d,   1/7d, 1/8d,   1/9d, 1/10d, 1/11d, 1/12d, 1/13d, 0},
      {1/5d,   1/6d, 1/7d,   1/8d, 1/9d,  1/10d, 1/11d, 1/12d, 1/13d, 1/14d, 0},
      {1/6d,   1/7d, 1/8d,   1/9d, 1/10d, 1/11d, 1/12d, 1/13d, 1/14d, 1/15d, 0},
      {1/7d,   1/8d, 1/9d,  1/10d, 1/11d, 1/12d, 1/13d, 1/14d, 1/15d, 1/16d, 0},
      {1/8d,   1/9d, 1/10d, 1/11d, 1/12d, 1/13d, 1/14d, 1/15d, 1/16d, 1/17d, 0},
      {1/9d,  1/10d, 1/11d, 1/12d, 1/13d, 1/14d, 1/15d, 1/16d, 1/17d, 1/18d, 0},
      {1/10d, 1/11d, 1/12d, 1/13d, 1/14d, 1/15d, 1/16d, 1/17d, 1/18d, 1/19d, 0}
      });
      public static final HashMap<String, Expr> solution2 = new HashMap<>(Map.ofEntries(
        entry("x1", new Expr(100)),
        entry("x2", new Expr(-4950)),
        entry("x3", new Expr(79200)),
        entry("x4", new Expr(-600600)),
        entry("x5", new Expr(2522520)),
        entry("x6", new Expr(-6306300)),
        entry("x7", new Expr(9609600)),
        entry("x8", new Expr(-8751600)),
        entry("x9", new Expr(4375800)),
        entry("x10", new Expr(-923780))
      ));
    }

    public static class E {
      public static final Matrix system = Matrix.from(new double[][] {
      {1,  -1, 2,  -1, -1},
      {2,   1, -2, -2, -2},
      {-1,  2, -4,  1, 1},
      {3,   0, 0,  -3, -3}
      });
      public static final HashMap<String, Expr> solution = new HashMap<>(Map.ofEntries(
        entry("x1", new Expr(-1, Expr.var("x4", 1))),
        entry("x2", new Expr(0, Expr.var("x3", 2))),
        entry("x3", new Expr(0, Expr.var("x3", 1))),
        entry("x4", new Expr(0, Expr.var("x4", 1)))
      ));
    }

    public static class F {
      public static final Matrix system = Matrix.from(new double[][] {
      {2,   0, 8,   0, 8},
      {0,   1, 0,   4, 6},
      {-4,  0, 6,   0, 6},
      {0,  -2, 0,   3, -1},
      {2,   0, -4,  0, -4},
      {0,   1, 0,  -2, 0}
      });
      public static final HashMap<String, Expr> solution = new HashMap<>(Map.ofEntries(
        entry("x1", new Expr(0)),
        entry("x2", new Expr(2)),
        entry("x3", new Expr(1)),
        entry("x4", new Expr(1))
      ));
    }

    public static class G {
      public static final Matrix system = Matrix.from(new double[][] {
      {8, 1, 3,   2, 0},
      {2, 9, -1, -2, 1},
      {1, 3, 2,  -1, 2},
      {1, 0, 0,   4, 3}
      });
      public static final HashMap<String, Expr> solution = new HashMap<>(Map.ofEntries(
        entry("x1", new Expr(-0.65)),
        entry("x2", new Expr(.56)),
        entry("x3", new Expr(.94)),
        entry("x4", new Expr(.91))
      ));
    }

    public static class H {
      public static final Matrix system = Matrix.from(new double[][] {
      {0,        0, 0,        1, 1,         1, 0,        0, 0,         15},
      {1,        1, 1,        0, 0,         0, 0,        0, 0,          8},
      {0,        0, .04289,   0, .04289, 0.75, .04289, .75, .61396, 14.79},
      {0,      .25, .91421, .25, .91421,  .25, .91421, .25, 0,      14.31},
      {.61396, .75, .04289, .75, .04289,    0, .04289,   0, 0,       3.81},
      {0,        0, 1,        0, 0,         1, 0,        0, 1,         18},
      {0,        1, 0,        0, 1,         0, 0,        1, 0,         12},
      {1,        0, 0,        1, 0,         0, 1,        0, 0,          6},
      {.04289, .75, .61396,   0, .04289,  .75, 0,        0, .04289, 10.51},
      {.91421, .25, 0,      .25, .91421,  .25, 0,      .25, .91421, 16.13},
      {.04289,   0, 0,      .75, 0,         0, .61396, .75, .04289,  7.04}
      });
      public static final HashMap<String, Expr> solution = null;
    }
  }

  public static class InterpolasiPolinomial {

    public static class A {

      public static final double[] X = new double[] {
        0.4, 0.7, 0.11, 0.17, 0.2, 0.23
      };

      public static final double[] Y = new double[] {
        0.043, 0.005, 0.072, 0.1, 0.13, 0.147
      };

      public static double uji(double x) {
        // Sumber: www.wolframalpha.com
        return -648.378 * Math.pow(x, 5) + 1117.46 * Math.pow(x, 4) - 696.133 * Math.pow(x, 3) + 193.715 * Math.pow(x, 2) - 23.6825 * x + 1.10651;
      }
    }

    public static class B {

      public static final double[] X = new double[] {
        6.567, 7, 7.258, 7.451, 7.548, 7.839, 8.161, 8.484, 8.709, 9
      };

      public static final double[] Y = new double[] {
        12.624, 21.807, 38.391, 54.517, 51.952, 28.228, 35.764, 20.813, 12.408, 10.534
      };

      public static double uji(double x) {
        // Sumber: www.wolframalpha.com
        return -141.075 * Math.pow(x, 9) + 9378.59 * Math.pow(x, 8) - 275654 * Math.pow(x, 7) + 4.69907e6 * Math.pow(x, 6) - 5.117e7 * Math.pow(x, 5) + 3.68848e8 * Math.pow(x, 4) - 1.75835e9 * Math.pow(x, 3) + 5.33932e9 * Math.pow(x, 2) - 9.35691e9 * x + 7.1956e9;
      }
    }
  }
}
