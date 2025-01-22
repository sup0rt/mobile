package Java;

import java.util.Scanner;

public class task13 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        input.nextLine();  // Consume newline
        System.out.print("Enter a string: ");
        String str13 = input.nextLine();
        System.out.println(str13 + " is a palindrome: " + isPalindrome(str13));
    }

    private static boolean isPalindrome(String str) {
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversedStr = new StringBuilder(cleanStr).reverse().toString();
        return cleanStr.equals(reversedStr);
    }
}
