package Java;

import java.util.Scanner;

public class task16 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size16 = input.nextInt();
        int[] arr16 = new int[size16];
        System.out.print("Enter the elements of the array (space-separated): ");
        for (int i = 0; i < size16; i++) {
            arr16[i] = input.nextInt();
        }
        int[] counts = countPositiveNegative(arr16);
        System.out.println("Positive numbers: " + counts[0] + ", Negative numbers: " + counts[1]);
    }

    private static int[] countPositiveNegative(int[] arr) {
        int positiveCount = 0;
        int negativeCount = 0;
        for (int num : arr) {
            if (num > 0) {
                positiveCount++;
            } else if (num < 0) {
                negativeCount++;
            }
        }
        return new int[]{positiveCount, negativeCount};
    }
}
