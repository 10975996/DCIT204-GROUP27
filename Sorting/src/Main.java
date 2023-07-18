import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the array size and elements from the user
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        // Prompt the user to choose the sorting algorithm
        System.out.println("Choose the sorting algorithm:");
        System.out.println("1. Merge Sort");
        System.out.println("2. Bubble Sort");
        System.out.println("3. Selection Sort");
        System.out.println("4. Insertion Sort");
        System.out.println("5. Radix Sort");
        System.out.println("6. Quick Sort");
        int choice = scanner.nextInt();

        // Perform the sorting and calculate the execution time
        long startTime, endTime, executionTime;
        int[] sortedArray;
        switch (choice) {
            case 1:
                startTime = System.nanoTime();
                sortedArray = mergeSort(array);
                endTime = System.nanoTime();
                executionTime = endTime - startTime;
                System.out.println("Merge Sort:");
                break;
            case 2:
                startTime = System.nanoTime();
                sortedArray = bubbleSort(array);
                endTime = System.nanoTime();
                executionTime = endTime - startTime;
                System.out.println("Bubble Sort:");
                break;
            case 3:
                startTime = System.nanoTime();
                sortedArray = selectionSort(array);
                endTime = System.nanoTime();
                executionTime = endTime - startTime;
                System.out.println("Selection Sort:");
                break;
            case 4:
                startTime = System.nanoTime();
                sortedArray = insertionSort(array);
                endTime = System.nanoTime();
                executionTime = endTime - startTime;
                System.out.println("Insertion Sort:");
                break;
            case 5:
                startTime = System.nanoTime();
                sortedArray = radixSort(array);
                endTime = System.nanoTime();
                executionTime = endTime - startTime;
                System.out.println("Radix Sort:");
                break;
            case 6:
                startTime = System.nanoTime();
                sortedArray = quickSort(array);
                endTime = System.nanoTime();
                executionTime = endTime - startTime;
                System.out.println("Quick Sort:");
                break;
            default:
                System.out.println("Invalid choice. Please choose a number between 1 and 6.");
                return;
        }

        // Print the sorted array and execution time
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));
        System.out.println("Execution time: " + executionTime + " nanoseconds");
    }

    private static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];

        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                merged[mergedIndex] = left[leftIndex];
                leftIndex++;
            } else {
                merged[mergedIndex] = right[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }

        while (leftIndex < left.length) {
            merged[mergedIndex] = left[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex < right.length) {
            merged[mergedIndex] = right[rightIndex];
            rightIndex++;
            mergedIndex++;
        }

        return merged;
    }

    private static int[] bubbleSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }

    private static int[] selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }

        return array;
    }

    private static int[] insertionSort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }

        return array;
    }

    private static int[] radixSort(int[] array) {
        int max = getMax(array);
        int exp = 1;

        while (max / exp > 0) {
            countSort(array, exp);
            exp *= 10;
        }

        return array;
    }

    private static int getMax(int[] array) {
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    private static void countSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }

    private static int[] quickSort(int[] array) {
        return quickSort(array, 0, array.length - 1);
    }

    private static int[] quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }

        return array;
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
