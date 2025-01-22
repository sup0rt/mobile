package Java;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        printMultiplicationTable(5);
    }

    private static void printMultiplicationTable(int table) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(table + " x " + i + " = " + (table * i));
        }
    }
}
