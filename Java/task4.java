package Java;

import java.util.Scanner;

public class task4 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer N: ");
        int num4 = input.nextInt();
        System.out.println("Sum from 1 to " + num4 + " is: " + sumOfNumbers(num4));

    }

    private static int sumOfNumbers(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
