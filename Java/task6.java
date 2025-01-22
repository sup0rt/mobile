package Java;

import java.util.Scanner;

public class task6 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int num6 = input.nextInt();
        System.out.println(num6 + " is prime: " + isPrime(num6));
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
}
