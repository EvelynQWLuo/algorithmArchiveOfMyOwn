package DP_1D;

// 丑数 II
// 给你一个整数 n ，请你找出并返回第 n 个 丑数
// 丑数 就是只包含质因数 2、3 或 5 的正整数
// 测试链接 : https://leetcode.cn/problems/ugly-number-ii/
public class Code05_L264_UglyNumberII {

	// 时间复杂度O(n)，n代表第n个丑数
	/*
	思路：
	❌第一直觉：一个一个数验证
	✅直接用丑数规律一个一个写出来，这样就省去验证过程
	分别乘2，3，5它们当中最小的一个就是下一个丑数
	三个指针分别代表乘2，3，5，当任意一个指针在某位置被用过之后，就不用再停在当前位置，可以移动到下一位置了
	即在这种移动中，也产生了某种单调性
	 */
	public static int nthUglyNumber(int n) {
		// dp 0 1 2 ...  n
		//      1 2 ...  ?
		int[] dp = new int[n + 1];
		dp[1] = 1;
		//i2，i3，i5，指的是下标，乘的指针停在哪个对应的下标
		for (int i = 2, i2 = 1, i3 = 1, i5 = 1, a, b, c, cur; i <= n; i++) {
			a = dp[i2] * 2;
			b = dp[i3] * 3;
			c = dp[i5] * 5;
			cur = Math.min(Math.min(a, b), c);
			if (cur == a) {
				i2++;
			}
			if (cur == b) {
				i3++;
			}
			if (cur == c) {
				i5++;
			}
			dp[i] = cur;
		}
		return dp[n];
	}
	/*
	============记忆化搜索=======
	 */

}
