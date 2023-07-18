import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Enter the elements of the array:");

        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        System.out.print("Enter the target value to search: ");
        int target = scanner.nextInt();

        System.out.println("Choose the search algorithm:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");
        int choice = scanner.nextInt();
        long startTime, endTime, executionTime;

        switch (choice) {
            case 1 -> {
                startTime = System.nanoTime();
                int linearResult = linearSearch(array, target);
                endTime = System.nanoTime();
                executionTime = endTime - startTime;
                if (linearResult != -1) {
                    System.out.println("Linear Search: Element found at index " + linearResult);
                } else {
                    System.out.println("Linear Search: Element not found in the array.");
                }
                System.out.println("Execution time: " + executionTime + " nanoseconds");
            }
            case 2 -> {
                Arrays.sort(array);
                startTime = System.nanoTime();
                int binaryResult = binarySearch(array, target);
                endTime = System.nanoTime();
                executionTime = endTime - startTime;
                if (binaryResult != -1) {
                    System.out.println("Binary Search: Element found at index " + binaryResult);
                } else {
                    System.out.println("Binary Search: Element not found in the array.");
                }
                System.out.println("Execution time: " + executionTime + " nanoseconds");
            }
            default -> System.out.println("Invalid choice. Please choose either 1 or 2.");
        }
    }

    private static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // Element not found
    }

    private static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; // Element not found
    }
}
