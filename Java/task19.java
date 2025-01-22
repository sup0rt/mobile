package Java;

import java.util.Scanner;

public class task19 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str19 = input.nextLine();
        System.out.println("Reversed words: " + reverseWordsInString(str19));
    }

    private static String reverseWordsInString(String str) {
        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }
        return result.toString().trim();
    }
}
