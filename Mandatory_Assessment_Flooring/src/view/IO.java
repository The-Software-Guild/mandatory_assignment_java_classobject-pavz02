package view;

import model.Order;
import java.util.Scanner;

public class IO {
    Scanner scanner;
    IO() {
        this.scanner = new Scanner(System.in);
    }

    public int nextInt() {
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public String nextString() {
        return scanner.nextLine().trim();
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void printOrder(Order order) {
        System.out.println(order);
    }
}
