package Java;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num;

        System.out.print("Enter an integer: ");
        num = input.nextInt();
        System.out.println(isEven(num) ? num + " is even." : num + " is odd.");
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}