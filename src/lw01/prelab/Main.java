package lw01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("src/lw01/prelab/jobs.txt"))) {
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                PrintJob job;
                switch (type) {
                    case "MONO":
                        job = new MonoPrint(id, pages);
                        break;
                    case "COLOUR":
                        job = new ColourPrint(id, pages);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown job type: " + type);
                }
                jobs.add(job);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File jobs.txt tidak ditemukan.");
            return;
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}