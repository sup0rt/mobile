package Java;

import java.util.Scanner;

public class task11 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer N for factorial: ");
        int num11 = input.nextInt();
        System.out.println("Factorial of " + num11 + " is: " + factorial(num11));
    }

    private static long factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for(int i = 2; i <= n; i++)
            result *= i;
        return result;
    }
}
