package sorting;
import java.util.*;

public class QuickSort {
    public void sort(int[] array, int low, int high){
        if(low<high) {
            Random random_num = new Random();
            int pivot = low + random_num.nextInt(high - low);

             pivot = getPartition(array, low, high, pivot);

            sort(array, low, pivot-1);
            sort(array, pivot+1, high);
        }
    }

    public int quickSelectSort(int[] array, int low, int high, int kthSmallest) {
    /*
    Returns the k-th smallest element of list within left..right.
    */
        if (low == high) return array[low];

        Random randomNum = new Random();
        int pivotIndex = low + randomNum.nextInt(high - low);

        pivotIndex = getPartition(array, low, high, pivotIndex);

        // the pivot is on (N - k)th smallest position
        if (pivotIndex == kthSmallest)
            return array[pivotIndex];
        // go left side
        else if (kthSmallest < pivotIndex) {
            return quickSelectSort(array, low, pivotIndex - 1, kthSmallest);
        }
        // go right side
        else {
            return quickSelectSort(array, pivotIndex + 1, high, kthSmallest);
        }
    }

    private int getPartition(int[] arr, int low, int high, int pivotIndex) {
        int pivot = arr[pivotIndex];
        // 1. move pivot to end
        swap(arr, pivotIndex, high);
        int storeIndex = low;

        // 2. move all smaller elements to the left
        for (int i = low; i <= high; i++) {
            if (arr[i] < pivot) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        // 3. move pivot to its final place
        swap(arr, storeIndex, high);

        return storeIndex;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public int findKthLargest(int[] nums, int k) {
        int N = nums.length;
        return quickSelectSort(nums, 0, N-1, N-k);
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] arr = new int[]{1, 5, 2, 10, 8,4, 6, 12};
        qs.sort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(elem -> System.out.print(elem + " "));
        System.out.println();
       // System.out.println(qs.findKthLargest(arr, 1));
    }
}
