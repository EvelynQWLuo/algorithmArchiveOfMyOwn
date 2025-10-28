package TwoPointers;

import java.util.ArrayDeque;
import java.util.Deque;

// 接雨水
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water/
public class Code03_L42_TrappingRainWater {

	// 辅助数组的解法（不是最优解）
	// 时间复杂度O(n)，额外空间复杂度O(n)
/*
在某位置i，要找出i左侧max和右侧max，取小-i的高度，就是i位置可以接的水量
同时，这个置需要和0比较取大，过滤掉负值
 */
	public static int trap1(int[] nums) {
		int n = nums.length;
		int[] lmax = new int[n];
		int[] rmax = new int[n];
		lmax[0] = nums[0];
		// 0~i范围上的最大值，记录在lmax[i]
		for (int i = 1; i < n; i++) {
			lmax[i] = Math.max(lmax[i - 1], nums[i]);
		}
		rmax[n - 1] = nums[n - 1];
		// i~n-1范围上的最大值，记录在rmax[i]
		for (int i = n - 2; i >= 0; i--) {
			rmax[i] = Math.max(rmax[i + 1], nums[i]);
		}
		int ans = 0;
		//   x              x
		//   0 1 2 3...n-2 n-1
		for (int i = 1; i < n - 1; i++) {
			ans += Math.max(0, Math.min(lmax[i - 1], rmax[i + 1]) - nums[i]);
		}
		return ans;
	}

	// 双指针的解法（最优解）
	// 时间复杂度O(n)，额外空间复杂度O(1)
/*
用两个指针，替代两个数组
lmax和rmax分别从最左和最右开始，谁小，它旁边是i的水量就可以结算了
 */
	public static int trap2(int[] nums) {
		int l = 1, r = nums.length - 2, lmax = nums[0], rmax = nums[nums.length - 1];
		int ans = 0;
		while (l <= r) {
			if (lmax <= rmax) {
				ans += Math.max(0, lmax - nums[l]);
				lmax = Math.max(lmax, nums[l++]);
			} else {
				ans += Math.max(0, rmax - nums[r]);
				rmax = Math.max(rmax, nums[r--]);
			}
		}
		return ans;
	}


	//单调栈模版写法
	class Solution {
		public int trap(int[] height) {
			Deque<Integer> stack = new ArrayDeque<>();
			int sum = 0;

			for(int i = 0; i < height.length; i++){
				// 当前高度大于栈顶时，可能形成凹槽
				while(!stack.isEmpty() && height[i] > height[stack.peek()]){
					int mid = stack.pop();
					// 需要左边界才能形成凹槽
					if(!stack.isEmpty()){
						// 凹槽的高度 = min(左右边界) - 底部
						int h = Math.min(height[i], height[stack.peek()]) - height[mid];
						// 凹槽的宽度
						int w = i - stack.peek() - 1;
						sum += h * w;
					}
				}
				// 当前索引入栈（无论是否相等）
				stack.push(i);
			}
			return sum;
		}
	}

}
