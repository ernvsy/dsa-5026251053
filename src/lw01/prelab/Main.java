package lw01.prelab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("jobs.txt"));

        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int pages = scanner.nextInt();

            PrintJob job;
            if (type.equals("MONO")) {
                job = new MonoPrint(id, pages);
            } else if (type.equals("COLOUR")) {
                job = new ColourPrint(id, pages);
            } else {
                throw new IllegalArgumentException("Unknown print job type: " + type);
            }
            jobs.add(job);
        }

        scanner.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}