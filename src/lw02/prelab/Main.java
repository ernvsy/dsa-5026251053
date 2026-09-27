package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> transactionQueue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        try (Scanner input = new Scanner(
                Main.class.getResourceAsStream("transactions.txt"))) {

            while (input.hasNext()) {
                String name = input.next();
                String type = input.next();
                String amount = input.next();

                String[] data = {name, type, amount};
                transactions.add(data);

                boolean found = false;

                for (String[] customer : customers) {
                    if (customer[0].equals(name)) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    customers.add(new String[]{name, "0"});
                }
            }
        }

        for (String[] data : transactions) {
            transactionQueue.offer(data);
        }


        while (!transactionQueue.isEmpty()) {

            String[] data = transactionQueue.poll();
            String customerName = data[0];
            String transactionType = data[1];
            int amount = Integer.parseInt(data[2]);

            for (String[] customer : customers) {

                if (customer[0].equals(customerName)) {

                    int balance = Integer.parseInt(customer[1]);

                    if (transactionType.equalsIgnoreCase("DEPOSIT")) {

                        balance += amount;
                        customer[1] = String.valueOf(balance);

                    } else if (transactionType.equalsIgnoreCase("WITHDRAW")) {

                        if (amount <= balance) {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        } else {
                            failed.push(data);
                        }
                    }

                    break;
                }
            }
        }
        
        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");

        while (!failed.empty()) {
            String[] data = failed.pop();

            System.out.println(
                data[0] + " " + data[1] + " " + data[2]
            );
        }
    }
}