package Java;

import java.util.Scanner;

public class task7 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer N: ");
        int num7 = input.nextInt();
        printReverseNumbers(num7);
    }

    private static void printReverseNumbers(int num) {
        for(int i = num; i >= 1; i--)
            System.out.print(i + " ");
    }
}
