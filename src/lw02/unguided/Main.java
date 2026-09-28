package lw02.unguided;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[]args){
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> eachbook = new LinkedList<>();
        LinkedList<String[]> eachcustomer = new LinkedList<>();

        Queue<String[]> requestonebyone = new LinkedList<>();
        Stack<String[]> failedrequests = new Stack<>();

        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("borrowing.txt")
        );

        while (scanner.hasNext()) {
            String[] request = new String[3];

            request[0] = scanner.next();
            request[1] = scanner.next();
            requests.add(request);
        }
        scanner.close();
        requestonebyone.addAll(requests);

        int maxborrow = 2;
        
        while (!requestonebyone.isEmpty()) {
            String[] data = requestonebyone.poll();

            String customerName = data[0];
            String bookTitle = data[1];

            String[] customer = null;
            String[] book = null;

            for (String[] c : eachcustomer) {
                if (c[0].equals(customerName)) {
                    customer = c;
                    break;
                }
            }

            for (String[] c : eachcustomer){
                if (c[1].equals(bookTitle)){
                    book = c;
                    break;
                }
            }

            for (String[] b : eachbook) {
                if (b[0].equals(bookTitle)) {
                    book = b;
                    break;
                }
            }
            

            if (customer == null) {
                String[] newCustomer = new String[3];
                newCustomer[0] = customerName;
                newCustomer[1] = "0";
                newCustomer[2] = bookTitle;
                eachcustomer.add(newCustomer);
                customer = newCustomer;
            }

            if (book == null) {
                String[] newBook = new String[2];
                newBook[0] = bookTitle;
                newBook[1] = bookTitle.equalsIgnoreCase("fisika") ? "1" : "2";
                eachbook.add(newBook);
                book = newBook;
            }

            int currentBorrowed = Integer.parseInt(customer[1]);
            int currentStock = Integer.parseInt(book[1]);

            if (currentBorrowed < maxborrow && currentStock > 0) {
                currentBorrowed++;
                currentStock--;
                customer[1] = Integer.toString(currentBorrowed);
                book[1] = Integer.toString(currentStock);
                customer[2] = bookTitle;

            } else {
                failedrequests.push(data);

            }
        }
        System.out.println("\n===== Final Borrowing Status: =====");
        for (String[] customer : eachcustomer) {
            System.out.println(customer[0] + " " + customer[2]);
            System.out.println(customer[0] + " " + customer[1]);
            
        }
        System.out.println("\n===== Final Book Stock: =====");
        for (String[] book : eachbook) {
            System.out.println(book[0] + ": " + book[1]);
        }
        System.out.println("\n===== Failed Borrowing Requests: =====");
        while (!failedrequests.isEmpty()) {
            String[] failedRequest = failedrequests.pop();
            System.out.println(
                    failedRequest[0] + " "
                    + failedRequest[1]
            );
        }         

    }
    
}