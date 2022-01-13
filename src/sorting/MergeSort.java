package sorting;
import java.util.*;

public class MergeSort {
    public void sort(int[] array, int low, int high){
        
        if (low<high){
            int mid = (low + high)/2;
            sort(array, low, mid);
            sort(array, mid+1, high);
            merge(array, low, mid, high);
        }
    }

    private void merge(int[] array, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = array[low + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];

        int i=0, j=0;
        int k = low;

        while (i<n1 && j<n2){
            if (L[i] <= R[j]) {
                array[k++] = L[i++];
            }
            else {
                array[k++] = R[j++];
            }
        }

        while (i < n1) {
            array[k++] = L[i++];
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            array[k++] = R[j++];
        }
    }

    public static void main(String[] args) {
        int arr[] = { 12, 11, 13, 5, 6, 7 };

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        Arrays.stream(arr).forEach(elem -> System.out.print(elem + " "));
    }
}
