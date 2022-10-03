package tubes.algeo;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

public class DataStudiKasus {
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

      // case ini cukup bermasalah karena keterbatasan komputer menangani floating point
      public static final Matrix system = Matrix.from(new double[][] {
      {1/1d, 1/2d, 1/3d, 1/4d, 1/5d,  1/6d,  1},
      {1/2d, 1/3d, 1/4d, 1/5d, 1/6d,  1/7d,  0},
      {1/3d, 1/4d, 1/5d, 1/6d, 1/7d,  1/8d,  0},
      {1/4d, 1/5d, 1/6d, 1/7d, 1/8d,  1/9d,  0},
      {1/5d, 1/6d, 1/7d, 1/8d, 1/9d,  1/10d, 0},
      {1/6d, 1/7d, 1/8d, 1/9d, 1/10d, 1/11d, 0}
      });
      public static final HashMap<String, Expr> solution = new HashMap<>(Map.ofEntries(
        entry("x1", new Expr(36)),
        entry("x2", new Expr(-630)),
        entry("x3", new Expr(3360)),
        entry("x4", new Expr(-7560)),
        entry("x5", new Expr(7560)),
        entry("x6", new Expr(-2772))
      ));
    }
  }

}
