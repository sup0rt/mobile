package Java;

import java.util.Scanner;

public class task9 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        input.nextLine();  // Consume newline
        System.out.print("Enter a string: ");
        String str9 = input.nextLine();
        System.out.println("Reversed string: " + reverseString(str9));
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    private static int countDigits(int num) {
        return String.valueOf(Math.abs(num)).length();
    }
}
