package com.questions.arrays;

/** Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * 
 * https://leetcode.com/problems/container-with-most-water/
 * @author Ram Komma
 */
public class ContainerWithMostWater {

	public static int maxArea(int[] height)
	{
		if(height == null || height.length <2)
			return 0;
		int area = Integer.MIN_VALUE;
		int lowPtr = 0;
		int highPtr = height.length -1;
		while(highPtr > lowPtr)
		{
			int lowerHeight = height[lowPtr];
			int highHeight = height[highPtr];
			int y = lowerHeight< highHeight ?lowerHeight:highHeight;
			int candidate = (highPtr - lowPtr) * y;
			area = area > candidate ? area:candidate;
			
			if(height[lowPtr] <= height[highPtr])
				while(highPtr > lowPtr && height[lowPtr] <= lowerHeight)
					lowPtr++;
			else
				while(highPtr > lowPtr && height[highPtr] <= highHeight)
					highPtr --;
		}
		return area;
	}
	
	public static void main(String[] args) {
		int[] heights = {4,2};
		System.out.println(maxArea(heights));
	}

}
