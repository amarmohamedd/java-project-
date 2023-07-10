// O(n*log(n))
public class Task2 {

    public static void main(String[] args) {
        // take the array to be sorted
        int[] arr = {12, 11, 13, 5, 6, 7, 2 ,1 ,9, 15 , 0};

        System.out.println("Array before sorting:");
        printArray(arr);

        heapSort(arr);

        System.out.println("Array after sorting:");
        printArray(arr);
    }

    /* the main function that
    1) adds each item
    2) then heapify it using the function "Heapify"
    3) then removes them one by one
    4) and heapify after each removal

     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // remove elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to the end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify the remaining heap
            heapify(arr, i, 0);
        }
    }

    // The heapify logic that is used to maintain that the smallest item is the root
    public static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // these two if conditions check for which child is the larger, left or right
        // If left child is larger than root
        if (left < n && arr[left] < arr[largest]) // to make min heap change the sign in the second condition
            largest = left;

        // If right child is larger than largest so far
        if (right < n && arr[right] < arr[largest]) // to make min heap change the sign in the second condition
            largest = right;

        // then compare the largest with the root and if the root is not the largest then swap them
        // If largest is not root
        if (largest != i) {
            swap (arr, i, largest);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
