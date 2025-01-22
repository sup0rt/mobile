package Java;

import java.util.Scanner;

public class task2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter three integers separated by spaces: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        int num3 = input.nextInt();
        System.out.println("The minimum is: " + findMin(num1, num2, num3));
    }

    private static int findMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
