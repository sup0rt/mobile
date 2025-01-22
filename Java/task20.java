package Java;

import java.util.Scanner;

public class task20 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int num20 = input.nextInt();
        System.out.println(num20 + " is an Armstrong number: " + isArmstrongNumber(num20));
        input.close();
    }

    private static boolean isArmstrongNumber(int number)
    {
        int originalNumber = number;
        int numDigits = String.valueOf(number).length();
        int sum = 0;

        while(number > 0){
            int digit = number % 10;
            sum += (int)Math.pow(digit, numDigits);
            number /= 10;
        }

        return sum == originalNumber;
    }

}
