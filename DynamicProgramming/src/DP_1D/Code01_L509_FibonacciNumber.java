package DP_1D;

import java.util.Arrays;

// 斐波那契数
// 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列
// 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
// 也就是：F(0) = 0，F(1) = 1
// F(n) = F(n - 1) + F(n - 2)，其中 n > 1
// 给定 n ，请计算 F(n)
// 测试链接 : https://leetcode.cn/problems/fibonacci-number/
// 注意：最优解来自矩阵快速幂，时间复杂度可以做到O(log n)
// 后续课程一定会讲述！本节课不涉及！
public class Code01_L509_FibonacciNumber {

	//纯暴力，每个节点都要展开，所以是2^n
	//从顶到底展开，即从f(n)往下展开
	//为什么O n很差？因为在展开过程中，有很多位置重复计算了
	public static int fib1(int n) {

		return f1(n);
	}

	public static int f1(int i) {
		if (i == 0) {
			return 0;
		}
		if (i == 1) {
			return 1;
		}
		return f1(i - 1) + f1(i - 2);
	}
/*
======================================优化1：记忆化搜索==================================

 */
	//还是从顶到底从n向下展开
	//时间O n，相当于每个节点只处理一次
	//用一张Array作缓存表，把每个节点的结果挂上去
	public static int fib2(int n) {
		int[] dp = new int[n + 1];  //存0到n
		Arrays.fill(dp, -1);
		return f2(n, dp);
	}

	public static int f2(int i, int[] dp) {
		//base case
		if (i == 0) {
			return 0;
		}
		if (i == 1) {
			return 1;
		}
		if (dp[i] != -1) {
			return dp[i];
		}
		int ans = f2(i - 1, dp) + f2(i - 2, dp);
		dp[i] = ans;
		return ans;
	}
/*
=======================自底到顶，dp=======================
 */
	//先算简单位置，后算复杂位置
	public static int fib3(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int[] dp = new int[n + 1];

		//dp[0]=0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
/*
=========================优化空间====================
 */
	public static int fib4(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int prePre = 0, pre = 1;
		int curr=0;
		for (int i = 2, cur; i <= n; i++) {
			curr = prePre + pre;
			prePre = pre;
			pre = curr;
		}
		return curr;

	}

}
