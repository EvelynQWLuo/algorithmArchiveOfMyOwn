package DP_1D;

// 最长有效括号
// 给你一个只包含 '(' 和 ')' 的字符串
// 找出最长有效（格式正确且连续）括号子串的长度。
// 测试链接 : https://leetcode.cn/problems/longest-valid-parentheses/
public class Code06_L32_LongestValidParentheses {
/*
思路：对整个串，子串必须以i位置的字符结尾的情况下，往左整体有效的最大长度
理解往前为什么只需要跳一步，不需要跳更多？
 */
	// 时间复杂度O(n)，n是str字符串的长度
	public static int longestValidParentheses(String str) {
		char[] s = str.toCharArray();
		// dp[0...n-1]
		// dp[i] : 子串必须以i位置的字符结尾的情况下，往左整体有效的最大长度
		//1、s[i] ==‘（’，dp[i] ==0, 即维持初始化的状态即可
		//2、s[i] ==‘）’，根据dp[i-1]的值，从i-1往前跳dp[i-1]长度到p位置
		       //p的三种可能性
		       //1、p越界，dp[i]=0
		       //2、p位置为‘）’，dp[i]=0
		       //3、既不越界且为‘（’，才有必要继续讨论
		           //在这种情况下，最少是在 dp[i - 1]基础上+2
		           //能不能有更多，要看dp[p-1]的情况

		int[] dp = new int[s.length]; //dp[0]默认就是0，省掉了
		int ans = 0;
		//dp[0]=0;
		for (int i = 1, p; i < s.length; i++) {
			if (s[i] == ')') {
				                          //p = i - dp[i - 1] - 1;
				p = i-1 -dp[i-1];//我觉得这个比较好，直接从i-1位置往前跳dp[i - 1]长度
				if (p >= 0 && s[p] == '(') {
					dp[i] = dp[i - 1] + 2 + (p - 1 >= 0 ? dp[p - 1] : 0);
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

}
