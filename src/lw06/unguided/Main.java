package lw06.unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));
        BinarySearchTree bst = new BinarySearchTree();
        String[] values = read.nextLine().trim().split("\\s+");
        int target = Integer.parseInt(read.nextLine());
        for (String value : values) {
            int data = Integer.parseInt(value);
            bst.insert(data);
        }
        System.out.print("Preorder: ");
        bst.preOrder();
        System.out.print("Inorder: ");
        bst.inOrder();
        System.out.print("Postorder: ");
        bst.postOrder();
        System.out.print("Search " + target + ": ");
        System.out.println(bst.search(target));
        System.out.print("Minimum: ");
        System.out.println(bst.findMin());
        System.out.print("Maximum: ");
        System.out.println(bst.findMax());
        read.close();
    }
}