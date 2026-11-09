package prelab.lw04;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var stream = Main.class.getResourceAsStream("branches.txt");

        if (stream == null) {
            System.out.println("File not found!");
            return;
        }
        Scanner input = new Scanner(stream);
        int n = input.nextInt();
        int[] branches = new int[n];

        for (int i = 0; i < n; i++) {
            branches[i] = input.nextInt();
        }
        int m = input.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = input.nextInt();
        }

        input.close();
        for (int i = 1; i <= 3 && i < n; i++) {

            int key = branches[i];
            int j = i - 1;

            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }

            branches[j + 1] = key;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(branches[i]);

            if (i < n - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
        for (int i = 0; i < m; i++) {

            int target = targets[i];
            int index = -1;

            for (int j = 0; j < n; j++) {

                if (branches[j] == target) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                System.out.println(target + ": FOUND at index " + index);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }
}
