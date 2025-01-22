package Java;

import java.util.Scanner;

public class task15 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size15 = input.nextInt();
        int[] arr15 = new int[size15];
        System.out.print("Enter the elements of the array (space-separated): ");
        for (int i = 0; i < size15; i++) {
            arr15[i] = input.nextInt();
        }
        System.out.println("Sum of array elements is: " + sumOfArrayElements(arr15));
    }

    private static int sumOfArrayElements(int[] arr) {
        if(arr == null || arr.length == 0)
        {
            return 0; //returning zero instead of trowing an exception because according to problem statement no error handling required
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
}
