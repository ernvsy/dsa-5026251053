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

        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("transactions.txt")
        );
        while (scanner.hasNext()) {
            String[] transaction = new String[3];

            transaction[0] = scanner.next(); 
            transaction[1] = scanner.next(); 
            transaction[2] = scanner.next(); 

            transactions.add(transaction);
        }

        scanner.close();

        transactionQueue.addAll(transactions);

        while (!transactionQueue.isEmpty()) {

            String[] data = transactionQueue.poll();

            String customerName = data[0];
            String transactionType = data[1];
            int amount = Integer.parseInt(data[2]);

            String[] customer = null;

            for (String[] c : customers) {
                if (c[0].equals(customerName)) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                String[] newCustomer = new String[2];

                newCustomer[0] = customerName;
                newCustomer[1] = "0";

                customers.add(newCustomer);
                customer = newCustomer;
            }

            int balance = Integer.parseInt(customer[1]);

            if (transactionType.equals("DEPOSIT")) {

                balance += amount;
                customer[1] = String.valueOf(balance);

            } else if (transactionType.equals("WITHDRAW")) {

                if (amount <= balance) {

                    balance -= amount;
                    customer[1] = String.valueOf(balance);

                } else {
                    failed.push(data);
                }
            }
        }

        System.out.println("\n===== Final Balances: =====");

        for (String[] customer : customers) {
            System.out.println(customer[0] + ": " + customer[1]);
        }
        System.out.println("\n===== Failed Transactions: =====");

        while (!failed.isEmpty()) {

            String[] failedTransaction = failed.pop();

            System.out.println(
                    failedTransaction[0] + " "
                    + failedTransaction[1] + " "
                    + failedTransaction[2]
            );
        }
    }
}