package lw01.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("rentals.txt"));
        int rentalCount = 0;
        int n = scanner.nextInt();
        Rental[] rentals = new Rental[n];
        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            Rental rental;
            if (type.equals("PROJECTOR")) {
                rental = new ProjectorRental(id, days, units);
            } else if (type.equals("LAPTOP")) {
                System.out.println(new LaptopRental(id, days, units).summary());
                continue;
            } else {
                throw new IllegalArgumentException("Unknown rental type: " + type);
            }
            rentals[rentalCount++] = rental;
        }
        scanner.close();
        for (Rental rental : rentals) {
            if (rental != null) {
                System.out.println(rental.summary());
            }
        }
    }
}
