package divide_conquer;

import java.util.Arrays;

public class DivideAndConquer {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] sorted = new int[m+n];
        int i=0, j=0, k=0;
        while (i<m && j<n){
            if(nums1[i]<nums2[j]){
                sorted[k++] = nums1[i++];
            }
            else {
                sorted[k++] = nums2[j++];
            }
        }
        while (i<m){
            sorted[k++] = nums1[i++];
        }
        while (j<n){
            sorted[k++] = nums2[j++];
        }
        int len = sorted.length;
        double median;
        if(len%2 == 0){
            double a = sorted[len/2];
            double b = sorted[(len/2 - 1)];

            median = ((a + b ) / 2.0);
        }
        else {
            median = sorted[len / 2];
        }
        return median;
    }

    public static void main(String[] args) {
        DivideAndConquer obj = new DivideAndConquer();
        double median1 = obj.findMedianSortedArrays(new int[]{1,  3}, new int[]{2});
        System.out.println(median1);
        double median2 = obj.findMedianSortedArrays(new int[]{1,  2}, new int[]{3, 4});
        System.out.println(median2);

    }
}
