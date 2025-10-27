package PrefixSum;

// 利用前缀和快速得到区域累加和
// 测试链接 : https://leetcode.cn/problems/range-sum-query-immutable/
public class Code01_303PrefixSumArray {
 /*
 为什么前缀和数组最开始要补0？
 省得最开始的边界讨论
  */
	class NumArray {

		public int[] sum;//准备一个前缀和数组

		public NumArray(int[] nums) {
			sum = new int[nums.length + 1]; //数组大小，比原数组多1
			for (int i = 1; i <= nums.length; i++) { //从原数组index=1处开始遍历
				sum[i] = sum[i - 1] + nums[i - 1];  //前缀和数组多每个位置，==本数组前一个位置和原数组前一个位置的和
			}
		}
//如果要迅速得到某范围的和，用一下公司
		public int sumRange(int left, int right) {

			return sum[right + 1] - sum[left];
		}
	}

}
