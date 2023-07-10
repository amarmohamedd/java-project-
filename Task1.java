// O(n*log(n))
public class Task1 {

    public static void main(String[] args) {
        // take the array that will be arranged
        int[] arr = {5, 3, 8, 2, 7, 1, 6};
        // the number K which the array will be pivoted around
        int k = 5;

        System.out.println("Before:");
        printArray(arr);

        rearrangeArray(arr, k);

        System.out.println("After:");
        printArray(arr);
    }


    // The main function which is the entry point from the main function
    public static void rearrangeArray(int[] arr, int k) {
        rearrangeHelper(arr, k, 0, arr.length - 1);
    }

    // The actual function with all the logic inside
    private static void rearrangeHelper(int[] arr, int k, int left, int right) {
        // base case where the array is all ordered and the left and right pointers overlapped
        if (left >= right) {
            return;
        }

        int pivot = arr[left];
        int i = left + 1;
        int j = right;

        // loop over the array using the i & j counters and check for the values
        while (i <= j) {
            // if value from the left is less than k then keep going
            if (arr[i] <= k) {
                i++;
            // if value from the right is bigger than k then keep going
            } else if (arr[j] > k) {
                j--;
            // else we need to swap the left and right pointers
            } else {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        swap(arr, left, j);
        // recursively call the function on the left and right half of the array
        rearrangeHelper(arr, k, left, j - 1);
        rearrangeHelper(arr, k, j + 1, right);
    }

    // helper function to swap two elements in an array using their index
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // helper function to print the array
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
