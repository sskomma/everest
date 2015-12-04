package com.leetcode.problem.four;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * @author Ram Komma
 */
public class MedianofTwoSortedArrays
{    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        if( (nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length ==0) ) return 0;
        else if( nums1 == null || nums1.length == 0 ) return findMedianOfSortedArray(nums2);
        else if( nums2 == null || nums2.length == 0 ) return findMedianOfSortedArray(nums1);
        
        int numberOfElements = nums1.length + nums2.length ;
        int n = 0, m = 0;
        int middle1 = 0, middle2 = 0;
        for(int i = 0; i <= Math.ceil(numberOfElements/2); i++)
        {
            middle1 = middle2;
            if(n == nums1.length ){
               middle2=nums2[m++];
            }
            else if (m == nums2.length){
               middle2=nums1[n++]; 
            }
            else if( nums1[n] < nums2[m] ){
                middle2 = nums1[n++];
            }
            else if( nums1[n] >= nums2[m] ){
                middle2=nums2[m++];
            }
            
        }
        return numberOfElements % 2 == 0 ? (middle1+middle2)*0.5 : middle2;
    }
    
    public double findMedianOfSortedArray(int[] array)
    {
        int length = array.length;
        if(length % 2 == 0)
            return ( array[length /2] + array[(length/2) - 1] )* 0.5;
        else
            return array[length /2];
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        int[] a1 = {};
        int[] a2 = {};
       MedianofTwoSortedArrays median = new MedianofTwoSortedArrays();
       System.out.println(median.findMedianSortedArrays(a1, a2));
       
    }

}
