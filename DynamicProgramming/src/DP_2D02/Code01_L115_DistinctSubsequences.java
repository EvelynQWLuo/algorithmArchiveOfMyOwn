package DP_2D02;

// 不同的子序列
// 给你两个字符串s和t ，统计并返回在s的子序列中t出现的个数
// 答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/distinct-subsequences/
public class Code01_L115_DistinctSubsequences {

	// 已经展示太多次从递归到动态规划了
	// 直接写动态规划吧
	public static int numDistinct1(String str, String target) {
		char[] s = str.toCharArray();
		char[] t = target.toCharArray();
		int n = s.length;
		int m = t.length;
		// dp[i][j] :
		// s[前缀长度为i]的所有子序列中，有多少个子序列等于t[前缀长度为j]
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j]; //不选，直接跳过，不选是i跳，不跳
				if (s[i - 1] == t[j - 1]) { //相等，就有选和不选两种可能，此处dp[i][j] 已经= dp[i - 1][j]
					dp[i][j] += dp[i - 1][j - 1];
					//实际就是dp[i][j] = dp[i - 1][j]+dp[i - 1][j - 1];
				}
			}
		}
		return dp[n][m];
	}

	// 空间压缩
	public static int numDistinct2(String str, String target) {
		char[] s = str.toCharArray();
		char[] t = target.toCharArray();
		int n = s.length;
		int m = t.length;
		int[] dp = new int[m + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = m; j >= 1; j--) {
				if (s[i - 1] == t[j - 1]) {
					dp[j] += dp[j - 1];
				}
			}
		}
		return dp[m];
	}

	// 本题说了要取模，所以增加取模的逻辑
	public static int numDistinct3(String str, String target) {
		int mod = 1000000007;
		char[] s = str.toCharArray();
		char[] t = target.toCharArray();
		int n = s.length;
		int m = t.length;
		int[] dp = new int[m + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = m; j >= 1; j--) {
				if (s[i - 1] == t[j - 1]) {
					dp[j] = (dp[j] + dp[j - 1]) % mod;
				}
			}
		}
		return dp[m];
	}
//递归尝试
	/**
	 * 纯暴力递归解法（从尾开始比较）
	 *
	 * 思路：
	 * - i: s的当前位置（从末尾开始，逐步递减）
	 * - j: t的当前位置（从末尾开始，逐步递减）
	 * - 如果s[i] == t[j]，有两种选择：
	 *   1. 使用s[i]来匹配t[j]，继续匹配前面部分：递归(i-1, j-1)
	 *   2. 不使用s[i]，继续在s中寻找：递归(i-1, j)
	 * - 如果s[i] != t[j]，只能跳过s[i]：递归(i-1, j)
	 * - 递归停止条件：
	 *   - j < 0：t全部匹配成功，返回1
	 *   - i < 0：s遍历完但t还没匹配完，返回0
	 */
	public int numDistinct(String s, String t) {
		return helper(s, t, s.length() - 1, t.length() - 1);
	}

	private int helper(String s, String t, int i, int j) {
		// 递归停止条件
		if (j < 0) {  // t已经全部匹配
			return 1;
		}
		if (i < 0) {  // s遍历完但t还没匹配完
			return 0;
		}

		// 递归逻辑
		if (s.charAt(i) == t.charAt(j)) {
			// 两种选择：用s[i]匹配 + 不用s[i]匹配
			return helper(s, t, i - 1, j - 1) + helper(s, t, i - 1, j);
		} else {
			// 只能跳过s[i]
			return helper(s, t, i - 1, j);
		}
	}
	class BruteforceSolution {
		public int numDistinct(String str, String target) {
			char[] s = str.toCharArray();
			char[] t = target.toCharArray();
			return dfs(s, t, s.length - 1, t.length - 1);
		}

		public int dfs(char[] s, char[] t, int i, int j) {
			if (j < 0) {
				return 1;
			}
			if (i < 0) {
				return 0;
			}

			if (s[i] == t[j]) {
				return dfs(s, t, i - 1, j) + dfs(s, t, i - 1, j - 1);
			} else {
				return dfs(s, t, i - 1, j);
			}
		}
	}

	/**
	 * 记忆化搜索优化版本（从尾开始比较）
	 *
	 * 从字符串的末尾开始比较，i从s.length()-1开始递减，j从t.length()-1开始递减
	 * memo[i][j] = -1 表示未计算
	 * memo[i][j] >= 0 表示已计算，直接返回该值
	 * 时间复杂度：O(m*n)，空间复杂度：O(m*n)
	 */

	class Solution {
		public int numDistinct(String str, String target) {
			char[] s = str.toCharArray();
			char[] t = target.toCharArray();
			int n = s.length;
			int m = t.length;
			int[][] dp = new int[n + 1][m + 1];

			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= m; j++) {
					dp[i][j] = -1;
				}
			}
			return dfs(s, t, n - 1, m - 1, dp);
		}

		public int dfs(char[] s, char[] t, int i, int j, int[][] dp) {
			if (j < 0) {
				return 1;
			}
			if (i < 0) {
				return 0;
			}
			if (dp[i][j] != -1) {
				return dp[i][j];
			}
			int ans = 0;

			if (s[i] == t[j]) {
				ans = dfs(s, t, i - 1, j, dp) + dfs(s, t, i - 1, j - 1, dp);
			} else {
				ans = dfs(s, t, i - 1, j, dp);
			}
			dp[i][j] = ans;
			return ans;
		}
	}
}


