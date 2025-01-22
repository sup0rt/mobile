package Java;

import java.util.Scanner;

public class task12 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int num12 = input.nextInt();
        System.out.println("Sum of digits in " + num12 + " is: " + sumOfDigits(num12));
    }

    private static int sumOfDigits(int number) {
        int sum = 0;
        number = Math.abs(number);
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
