package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void urutkanKartu(int[] kartu, int jumlahPass) {
        int n = kartu.length;

        for (int i = 0; i < jumlahPass && i < n - 1; i++) {
            int indeksTerkecil = i;

            for (int j = i + 1; j < n; j++) {
                if (kartu[j] < kartu[indeksTerkecil]) {
                    indeksTerkecil = j;
                }
            }

            int sementara = kartu[i];
            kartu[i] = kartu[indeksTerkecil];
            kartu[indeksTerkecil] = sementara;
        }
    }

    public static int cariKartu(int[] kartu, int dicari) {
        for (int i = 0; i < kartu.length; i++) {
            if (kartu[i] == dicari) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("cards.txt")
        );

        int jumlahKartu = scanner.nextInt();
        int[] kartu = new int[jumlahKartu];

        for (int i = 0; i < jumlahKartu; i++) {
            kartu[i] = scanner.nextInt();
        }

        int jumlahCari = scanner.nextInt();
        int[] permintaan = new int[jumlahCari];

        for (int i = 0; i < jumlahCari; i++) {
            permintaan[i] = scanner.nextInt();
        }

        scanner.close();

        urutkanKartu(kartu, 2);
        for (int i = 0; i < jumlahKartu; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(kartu[i]);
        }
        System.out.println();
        for (int i = 0; i < jumlahCari; i++) {
            int posisi = cariKartu(kartu, permintaan[i]);

            if (posisi != -1) {
                System.out.println(permintaan[i] +
                    ": FOUND at index " + posisi);
            } else {
                System.out.println(permintaan[i] + ": NOT FOUND");
            }
        }
    }
}