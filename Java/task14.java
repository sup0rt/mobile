package Java;

import java.util.Scanner;

public class task14 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size14 = input.nextInt();
        int[] arr14 = new int[size14];
        System.out.print("Enter the elements of the array (space-separated): ");
        for (int i = 0; i < size14; i++) {
            arr14[i] = input.nextInt();
        }
        System.out.println("Maximum in array is: " + findMaxInArray(arr14));
    }

    private static int findMaxInArray(int[] arr) {
        if(arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array can not be null or empty");
        }
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max)
                max = arr[i];
        }
        return max;
    }
}
