package Java;

import java.util.Scanner;

public class task17 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter two integers A and B (space-separated): ");
        int num17a = input.nextInt();
        int num17b = input.nextInt();
        System.out.print("Prime numbers between " + num17a + " and " + num17b + " are: ");
        printPrimeNumbersInRange(num17a, num17b);
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false; // 0 and 1 are not prime
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // If it's divisible by any number from 2 to the square root, it's not prime.
            }
        }
        return true;
    }

    private static void printPrimeNumbersInRange(int a, int b) {
        for (int i = Math.min(a,b); i <= Math.max(a,b); i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
