package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> enrollments = new LinkedHashMap<>();
        Set<String> courseorder = new LinkedHashSet<>();
        List<String> checkresults = new ArrayList<>();
        int rejected = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        while (sc.hasNext()) {
            String command = sc.next();
            String kodekelas = sc.next();
            courseorder.add(kodekelas);

            if (command.equals("CHECK")) {
                if (enrollments.containsKey(kodekelas)) {
                    checkresults.add(kodekelas + ": " + enrollments.get(kodekelas)+ " students");
                } else {
                    checkresults.add(kodekelas + ": Not found");
                }
            } else if (command.equals("REGISTER") || command.equals("WITHDRAW")) {
                int jumlah = sc.nextInt();
                if (jumlah <= 0) {
                    rejected++;
                } else if (command.equals("REGISTER")) {
                    enrollments.put(kodekelas, enrollments.getOrDefault(kodekelas, 0) + jumlah);
                } else if (enrollments.containsKey(kodekelas) && enrollments.get(kodekelas) >= jumlah) {
                    enrollments.put(kodekelas, enrollments.get(kodekelas) - jumlah);
                } else {
                    rejected++;
                }
            }
        }
        sc.close();

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkresults) {
            System.out.println(result);
        }
        System.out.println();

        System.out.println("===== Final Enrollment =====");
        for (String kodekelas : courseorder) {
            if (enrollments.containsKey(kodekelas)) {
                System.out.println(kodekelas + ": " + enrollments.get(kodekelas) + " students");
            }
        }
        System.out.println();

        System.out.println("Rejected operations: " + rejected);
    }
}
