package Java;

import java.util.Scanner;

public class task18 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        input.nextLine();  // Consume newline
        System.out.print("Enter a string: ");
        String str18 = input.nextLine();
        int[] vowelConsonantCounts = countVowelsConsonants(str18);
        System.out.println("Vowels: " + vowelConsonantCounts[0] + ", Consonants: " + vowelConsonantCounts[1]);
    }

    private static int[] countVowelsConsonants(String str) {
        int vowels = 0;
        int consonants = 0;
        str = str.toLowerCase();
        for (char c : str.toCharArray()) {
            if (c >= 'a' && c <= 'z') { //check for alphabet letters only
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        return new int[]{vowels, consonants};
    }
}

