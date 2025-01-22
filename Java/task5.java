package Java;

import java.util.Arrays;
import java.util.Scanner;

public class task5 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer N for Fibonacci series: ");
        int num5 = input.nextInt();
        System.out.println("First " + num5 + " Fibonacci numbers: " + Arrays.toString(fibonacciNumbers(num5)));

    }

    private static int[] fibonacciNumbers(int n) {
        if(n <= 0) {
            return new int[0];
        }
        int[] fib = new int[n];
        if(n >= 1)
            fib[0] = 0;
        if (n >= 2)
            fib[1] = 1;

        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }
}
