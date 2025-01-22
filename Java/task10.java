package Java;

import java.util.Scanner;

public class task10 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int num10 = input.nextInt();
        System.out.println("Number of digits in " + num10 + " is: " + countDigits(num10));
    }

    private static int countDigits(int num) {
        return String.valueOf(Math.abs(num)).length();
    }
}
