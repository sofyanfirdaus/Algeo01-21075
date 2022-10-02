package tubes.algeo;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class User {

    public void readFile(Matrix m) {
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
        System.out.println("File tidak ada atau nama yang dimasukkan salah.");
        e.printStackTrace();
        ketik.close();
        System.exit(0);
        }
        ketik.close();
    }

    public void writeMatrix(Matrix matrix) {
        int m,n;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan Ordo Matriks m x n");
        System.out.print("Masukkan baris m : ");
        m = scanner.nextInt();
        System.out.print("Masukkan kolom n : ");
        n = scanner.nextInt();

        matrix = new Matrix(m,n); //Bingung implementasinya gmn

        System.out.println("Tulis Elemen Matriks ");
        for(int i = 0; i < m; i++){
			for (int j = 0; j < n; j++){
				matrix.setElement(i,j,scanner.nextDouble());
			}
		}
        System.out.println("Matriks yang Anda masukkan ialah :");
        System.out.println(matrix);
        scanner.close();
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
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi Linear Berganda");
        System.out.println("7. Keluar");
        System.out.print("Masukkan pilihan menu yang diinginkan : ");

        int pil;
        pil = input.nextInt();

        while (pil != 1 && pil != 2 && pil != 3 && pil != 4 && pil != 5 && pil != 6 && pil != 7) {
            System.out.println("");
            System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
            System.out.println("MENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic");
            System.out.println("6. Regresi Linear Berganda");
            System.out.println("7. Keluar");
            System.out.print("Masukkan pilihan menu yang diinginkan : ");
            pil = input.nextInt();
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
            System.exit(0);
        }
    }

    public void menuSPL() {
        System.out.println("Anda memilih menu Sistem Persamaan Linier");
        System.out.println("");
        System.out.println("Sekarang Anda akan memasukkan Matriks");
        System.out.println("Pilih metode pembacaan Matriks Anda (1. File ; 2. Keyboard)");
        System.out.print("Masukkan pilihan Anda : ");
        int pil;
        pil = input.nextInt();
        System.out.println("");

        while (pil != 1 && pil != 2) {
            System.out.println("Masukan Anda Salah! Silakan Ulangi Masukan Anda");
            System.out.println("Pilih metode pembacaan Matriks Anda (1. File ; 2. Keyboard)");
            System.out.print("Masukkan pilihan Anda : ");
            pil = input.nextInt();
            System.out.println("");
        }

        // Ini inisiasi Matriks nya gmn ya
        Matrix Maug = new Matrix(0,0);
        if (pil == 1) {
            readFile(Maug);
        } else if (pil == 2) {
            writeMatrix(Maug);
        }
        System.out.println(Maug);


    }

    public void menuDeterminan() {

    }

    public void menuMatriksBalikan() {

    }

    public void menuInterPolinom() {

    }

    public void menuInterBicubic() {

    }
    
    public void menuRegresi() {

    }
}

