package tubes.algeo;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class User {

  public Matrix readFile() {
    Matrix m = null;
    Scanner ketik = new Scanner(System.in);
    int Row,Col,i,j;

    try {
      System.out.print("Tulis nama filenya : ");
      String nama = ketik.next();
      String checkpath = "../test/";
      File file = new File(checkpath+nama);
      System.out.println(file.getAbsolutePath());

      Scanner CountRow = new Scanner(file);
      Row = 0;
      while (CountRow.hasNextLine()){
        CountRow.nextLine();
        Row += 1;
      }
      CountRow.close();

      Scanner CekBaris = new Scanner(file);
      Col = 0;
      if (CekBaris.hasNextLine()){
        Scanner CountCol = new Scanner(CekBaris.nextLine());
        while (CountCol.hasNextDouble()){
          CountCol.nextDouble();
          Col += 1;
        }
        CountCol.close();
      }
      CekBaris.close();
      m = new Matrix(Row,Col);

      Scanner MatrixRow = new Scanner(file);
      i = 0;
      while (MatrixRow.hasNextLine()){
        Scanner MatrixCol = new Scanner(MatrixRow.nextLine());
        j = 0;
        while (MatrixCol.hasNextDouble()){
          m.setElement(i,j,MatrixCol.nextDouble());
          j += 1;
        }
        MatrixCol.close();
        i += 1;
      }
      MatrixRow.close();
      System.out.println("Matrix yang terbaca dari file adalah :");
      System.out.println(m);
    }
    catch (FileNotFoundException e) {
      System.err.println("File tidak ada atau nama yang dimasukkan salah.");
      e.printStackTrace();
      ketik.close();
      System.exit(0);
    }
    ketik.close();
    return m;
  }

  public Matrix scanMatrix() {
    int m,n;
    System.out.println("Masukkan Ordo Matriks m x n");
    System.out.print("Masukkan baris m : ");
    m = input.nextInt();
    System.out.print("Masukkan kolom n : ");
    n = input.nextInt();

    Matrix matrix = new Matrix(m,n);

    System.out.println("Tulis Elemen Matriks ");
    for(int i = 0; i < m; i++){
      for (int j = 0; j < n; j++){
        System.out.print("Masukkan Elemen Baris "+(i+1)+" dan Kolom "+(j+1)+" : ");
        matrix.setElement(i,j,input.nextDouble());
      }
    }
    System.out.println("Matriks yang Anda masukkan ialah :");
    System.out.println(matrix);

    return matrix;
  }

  public Matrix scanMatrixPersegi() {
    int n;
    System.out.println("Masukkan n dalam Ordo Matriks n x n : ");
    n = input.nextInt();

    Matrix matrix = new Matrix(n,n);

    System.out.println("Tulis Elemen Matriks ");
    for(int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        System.out.print("Masukkan Elemen Baris "+(i+1)+" dan Kolom "+(j+1)+" : ");
        matrix.setElement(i,j,input.nextDouble());
      }
    }
    System.out.println("Matriks yang Anda masukkan ialah :");
    System.out.println(matrix);

    return matrix;
  }

  static Scanner input = new Scanner(System.in);

  public void mainMenu() {
    System.out.println("Tugas Besar 1 IF2123 Aljabar Linear dan Geometri");
    System.out.println("Sistem Persamaan Linier, Determinan, dan Aplikasinya");
    System.out.println("");
    System.out.println("Dibuat oleh :");
    System.out.println("13521075 - Muhammad Rifko Favian");
    System.out.println("13521083 - Moch. Sofyan Firdaus");
    System.out.println("13521098 - Fazel Ginanda");
    System.out.println("");

    int pil = tanyaMenuUtama(input);

    while (pil != 1 && pil != 2 && pil != 3 && pil != 4 && pil != 5 && pil != 6 && pil != 7) {
      System.out.println("");
      System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
      pil = tanyaMenuUtama(input);
    }
    System.out.println("");
    if (pil == 1) {
      menuSPL();
    } else if (pil == 2) {
      menuDeterminan();
    } else if (pil == 3) {
      menuMatriksBalikan();
    } else if (pil == 4) {
      menuInterPolinom();
    } else if (pil == 5) {
      menuInterBicubic();
    } else if (pil == 6) {
      menuRegresi();
    } else if (pil == 7) {
      Keluar();
    }
  }

  public void menuSPL() {
    System.out.println("Anda memilih menu Sistem Persamaan Linier");
    System.out.println("");
    System.out.println("Sekarang Anda akan memasukkan Matriks");
    System.out.println("Pilih metode pembacaan Matriks Anda (1. File ; 2. Keyboard)");
    System.out.print("Masukkan pilihan Anda : ");
    int pil,pil2,out;
    pil = input.nextInt();
    System.out.println("");

    while (pil != 1 && pil != 2) {
      System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
      System.out.println("Pilih metode pembacaan Matriks Anda (1. File ; 2. Keyboard)");
      System.out.print("Masukkan pilihan Anda : ");
      pil = input.nextInt();
      System.out.println("");
    }

    Matrix Maug = null;
    if (pil == 1) {
      Maug = readFile();
    } else if (pil == 2) {
      Maug = scanMatrix();
    }
    
    System.out.println("Pilih metode pencarian solusi Sistem Persamaan Linier");
    System.out.println("1. Metode Eliminasi Gauss");
    System.out.println("2. Metode Eliminasi Gauss-Jordan");
    System.out.println("3. Metode Matriks Balikan");
    System.out.println("4. Kaidah Cramer");
    System.out.print("Masukkan pilihan Anda : ");
    pil2 = input.nextInt();
    System.out.println("");
    while (pil2 != 1 && pil2 != 2 && pil2 != 3 && pil2 != 4) {
      System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
      System.out.println("Pilih metode pencarian solusi Sistem Persamaan Linier");
      System.out.println("1. Metode Eliminasi Gauss");
      System.out.println("2. Metode Eliminasi Gauss-Jordan");
      System.out.println("3. Metode Matriks Balikan");
      System.out.println("4. Kaidah Cramer");
      System.out.print("Masukkan pilihan Anda : ");
      pil2 = input.nextInt();
      System.out.println("");
    }

    if (pil2 == 1) {
      HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGauss(Maug);
      System.out.println("Hasil Solusi SPL-nya ialah :");
      System.out.println(solution);
    }
    else if (pil2 == 2) {
      HashMap<String, Expr> solution = LinearEquationSolver.solveSystemGaussJordan(Maug);
      System.out.println("Hasil Solusi SPL-nya ialah :");
      System.out.println(solution);
    }
    else if (pil2 == 3) {
      HashMap<String, Expr> solution = LinearEquationSolver.solveSystemInverse(Maug);
      System.out.println("Hasil Solusi SPL-nya ialah :");
      System.out.println(solution);
    }
    else if (pil2 == 4) {
      HashMap<String, Expr> solution = LinearEquationSolver.solveSystemCramer(Maug);
      System.out.println("Hasil Solusi SPL-nya ialah :");
      System.out.println(solution);
    }

    System.out.print("Apakah Anda ingin kembali ke [1]Menu Utama atau [2]Keluar ? ");
    out = input.nextInt();
    while (out != 1 && out != 2) {
      System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
      System.out.print("Apakah Anda ingin kembali ke [1]Menu Utama atau [2]Keluar ? ");
      out = input.nextInt();
    }
    if (out == 1) {
      System.out.println("");
      mainMenu();
    }
    else if (out == 2) {
      Keluar();
    }
  }

  public void menuDeterminan() {
    System.out.println("Anda memilih menu Determinan");
    System.out.println("");
    System.out.println("Sekarang Anda akan memasukkan Matriks");
    System.out.println("Pilih metode pembacaan Matriks Anda (1. File ; 2. Keyboard)");
    System.out.print("Masukkan pilihan Anda : ");
    int pil,pil2,out;
    pil = input.nextInt();
    System.out.println("");

    while (pil != 1 && pil != 2) {
      System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
      System.out.println("Pilih metode pembacaan Matriks Anda (1. File ; 2. Keyboard)");
      System.out.print("Masukkan pilihan Anda : ");
      pil = input.nextInt();
      System.out.println("");
    }

    Matrix Maug = null;
    if (pil == 1) {
      Maug = readFile();
    } else if (pil == 2) {
      Maug = scanMatrixPersegi();
    }

    if (Maug.getRow() != Maug.getCol()) {
      System.out.println("Tidak punya determinan karena bukan matriks persegi");
			System.out.println("");
    }
    else {
      System.out.println("Pilih metode pencarian Determinan");
      System.out.println("1. Ekspansi Kofaktor");
      System.out.println("2. OBE");
			System.out.print("Masukkan pilihan Anda : ");
      pil2 = input.nextInt();
      System.out.println("");
      while (pil2 != 1 && pil2 != 2) {
        System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
        System.out.println("Pilih metode pencarian Determinan");
        System.out.println("1. Ekspansi Kofaktor");
        System.out.println("2. OBE");
        System.out.print("Masukkan pilihan Anda : ");
        pil2 = input.nextInt();
        System.out.println("");
      }

      if (pil2 == 1) {
        double answer = Maug.getDeterminantCofactor();
        System.out.println("Hasil Determinannya ialah "+answer);
      }
      else if (pil2 == 2) {
        double answer = Maug.getDeterminantGauss();
        System.out.println("Hasil Determinannya ialah "+answer);
      }
    }
    System.out.print("Apakah Anda ingin kembali ke [1]Menu Utama atau [2]Keluar ? ");
    out = input.nextInt();
    while (out != 1 && out != 2) {
      System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
      System.out.print("Apakah Anda ingin kembali ke [1]Menu Utama atau [2]Keluar ? ");
      out = input.nextInt();
    }
    if (out == 1) {
      System.out.println("");
      mainMenu();
    }
    else if (out == 2) {
      Keluar();
    }
    
  }

  public void menuMatriksBalikan() {
    System.out.println("Anda memilih menu Matriks Balikan");
    System.out.println("");
    System.out.println("Sekarang Anda akan memasukkan Matriks");
    System.out.println("Pilih metode pembacaan Matriks Anda (1. File ; 2. Keyboard)");
    System.out.print("Masukkan pilihan Anda : ");
    int pil,pil2,out;
    pil = input.nextInt();
    System.out.println("");

    while (pil != 1 && pil != 2) {
      System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
      System.out.println("Pilih metode pembacaan Matriks Anda (1. File ; 2. Keyboard)");
      System.out.print("Masukkan pilihan Anda : ");
      pil = input.nextInt();
      System.out.println("");
    }

    Matrix Maug = null;
    if (pil == 1) {
      Maug = readFile();
    } else if (pil == 2) {
      Maug = scanMatrixPersegi();
    }

    if (Maug.getRow() != Maug.getCol()) {
      System.out.println("Tidak punya determinan karena bukan matriks persegi,");
      System.out.println("Matriks balikan tidak dapat dicari");
			System.out.println("");
    }
    else if (Maug.getDeterminantCofactor() == 0.0 || Maug.getDeterminantGauss() == 0.0) {
      System.out.println("Tidak punya matriks balikan karena determinannya 0");
      System.out.println("");
    }
    else {
      System.out.println("Pilih metode pencarian Matriks Balikan");
      System.out.println("1. Adjoin");
      System.out.println("2. Gauss-Jordan");
			System.out.print("Masukkan pilihan Anda : ");
      pil2 = input.nextInt();
      System.out.println("");
      while (pil2 != 1 && pil2 != 2) {
        System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
        System.out.println("Pilih metode pencarian Matriks Balikan");
        System.out.println("1. Adjoin");
        System.out.println("2. Gauss-Jordan");
        System.out.print("Masukkan pilihan Anda : ");
        pil2 = input.nextInt();
        System.out.println("");
      }
      if (pil == 1) {
        Matrix answer = Maug.getInverseMatrixAdj();
        System.out.println("Hasil Inverse Matriks ialah :");
        System.out.println(answer);
      }
      else if (pil == 2) {
        Matrix answer = Maug.getInverseMatrixGaussJordan();
        System.out.println("Hasil Inverse Matriks ialah :");
        System.out.println(answer);
      }
    }
    System.out.print("Apakah Anda ingin kembali ke [1]Menu Utama atau [2]Keluar ? ");
    out = input.nextInt();
    while (out != 1 && out != 2) {
      System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
      System.out.print("Apakah Anda ingin kembali ke [1]Menu Utama atau [2]Keluar ? ");
      out = input.nextInt();
    }
    if (out == 1) {
      System.out.println("");
      mainMenu();
    }
    else if (out == 2) {
      Keluar();
    }
  }

  public void menuInterPolinom() {

  }

  public void menuInterBicubic() {

  }

  public void menuRegresi() {

  }

  public int tanyaMenuUtama(Scanner scanner) {
    System.out.println("MENU");
    System.out.println("1. Sistem Persamaan Linier");
    System.out.println("2. Determinan");
    System.out.println("3. Matriks Balikan");
    System.out.println("4. Interpolasi Polinom");
    System.out.println("5. Interpolasi Bicubic");
    System.out.println("6. Regresi Linear Berganda");
    System.out.println("7. Keluar");
    System.out.print("Masukkan pilihan menu yang diinginkan : ");

    return scanner.nextInt();
  }

  public void Keluar() {
    input.close();
    System.exit(0);
  }
}

