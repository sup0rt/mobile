package Java;

import java.util.Scanner;

public class task8 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter two integers A and B (space-separated): ");
        int num8a = input.nextInt();
        int num8b = input.nextInt();
        System.out.println("Sum of even numbers from " + num8a + " to " + num8b + " is: " + sumOfEvenNumbersInRange(num8a, num8b));
    }

    private static int sumOfEvenNumbersInRange(int a, int b) {
        int sum = 0;
        for (int i = Math.min(a,b); i <= Math.max(a,b); i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
