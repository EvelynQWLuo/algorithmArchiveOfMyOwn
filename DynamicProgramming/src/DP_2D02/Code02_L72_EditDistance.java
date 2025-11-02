package DP_2D02;

// 编辑距离
// 给你两个单词 word1 和 word2
// 请返回将 word1 转换成 word2 所使用的最少代价
// 你可以对一个单词进行如下三种操作：
// 插入一个字符，代价a
// 删除一个字符，代价b
// 替换一个字符，代价c
// 测试链接 : https://leetcode.cn/problems/edit-distance/
public class Code02_L72_EditDistance {

	// 已经展示太多次从递归到动态规划了
	// 直接写动态规划吧
	public int minDistance(String word1, String word2) {

		return editDistance2(word1, word2, 1, 1, 1);
	}
/*
整体思路：
dp[i][j] :s1[前缀长度为i]想变成s2[前缀长度为j]，至少付出多少代价
分两大种情况：
一、s1[i-1]参与构成
     a、s1的i-1变成s2的j-1
          1、s1[i - 1] 和 s2[j - 1]相等
               所以只需要搞定s1[i - 2] 和 s2[j - 2]，这部分的长度是i-1，所以对应dp[i - 1][j-1]
            2、s1[i - 1] 和 s2[j - 1]不相等
                  所以同样搞定前面部分s1[i - 2] 和 s2[j - 2]，对应dp[i - 1][j-1],加上替换代价c
      b、3、s1的i-1虽然参与，但是不是通过跟s2最后一个字符j-1进行比较参与的，即不用变成j-1，而是用0到i-1搞定0-j-2
                 这部分对应长度是i和j-1，所以是dp[i][j - 1] + a

二、s1[i-1]不参与构成
4、即要把i-1删掉，用0-i-2去构成0 - j-1，即dp[i - 1][j] +删除b

更近一步优化思考
s1[i - 1] 和 s2[j - 1]相等时，这个结果一定保留，没有比它更优的，所以直接dp[i][j] = dp[i - 1][j - 1]
不等的再有另3种情况

二维dp表
第0行：
什么意思？s1为空，怎么变成分别有0，1，2,...m个s2的长度？不断插入最终（m-1）*a
第0列：
s2为空，s1怎么从0，1，2,...n个变成s2
 */



	// 原初尝试版
	// a : str1中插入1个字符的代价
	// b : str1中删除1个字符的代价
	// c : str1中改变1个字符的代价
	// 返回从str1转化成str2的最低代价
	public static int editDistance1(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;

		int[][] dp = new int[n + 1][m + 1];
		//dp[0][0]:默认是0，就不写了
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i * b;
		}
		for (int j = 1; j <= m; j++) {
			dp[0][j] = j * a;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int p1 = Integer.MAX_VALUE;
				if (s1[i - 1] == s2[j - 1]) {
					p1 = dp[i - 1][j - 1];
				}
				int p2 = Integer.MAX_VALUE;
				if (s1[i - 1] != s2[j - 1]) {
					p2 = dp[i - 1][j - 1] + c;
				}
				int p3 = dp[i][j - 1] + a;
				int p4 = dp[i - 1][j] + b;
				dp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
			}
		}
		return dp[n][m];
	}

	// 枚举小优化版, 但是没有改变时间复杂度
	// a : str1中插入1个字符的代价
	// b : str1中删除1个字符的代价
	// c : str1中改变1个字符的代价
	// 返回从str1转化成str2的最低代价
	public static int editDistance2(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		// dp[i][j] :
		// s1[前缀长度为i]想变成s2[前缀长度为j]，至少付出多少代价
		int[][] dp = new int[n + 1][m + 1];
		//dp[0][0]:默认是0，就不写了
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i * b;
		}
		for (int j = 1; j <= m; j++) {
			dp[0][j] = j * a;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1[i - 1] == s2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j] + b, dp[i][j - 1] + a), dp[i - 1][j - 1] + c);
				}
			}
		}
		return dp[n][m];
	}

	// 空间压缩
	public static int editDistance3(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		int[] dp = new int[m + 1];
		for (int j = 1; j <= m; j++) {
			dp[j] = j * a;
		}
		for (int i = 1, leftUp, backUp; i <= n; i++) {
			leftUp = (i - 1) * b;
			dp[0] = i * b;
			for (int j = 1; j <= m; j++) {
				backUp = dp[j];
				if (s1[i - 1] == s2[j - 1]) {
					dp[j] = leftUp;
				} else {
					dp[j] = Math.min(Math.min(dp[j] + b, dp[j - 1] + a), leftUp + c);
				}
				leftUp = backUp;
			}
		}
		return dp[m];
	}

//时间 O n*m，最大代价就是遍历整个格子，每个格子代价都是O1
	class MySolution {
		public int minDistance(String word1, String word2) {

			return editDistance(word1, word2, 1, 1, 1);
		}

		public int editDistance(String w1, String w2, int a, int b, int c) {
			char[] s = w1.toCharArray();
			char[] t = w2.toCharArray();
			int n = s.length;
			int m = t.length;

			int[][] dp = new int[n + 1][m + 1];
			dp[0][0] = 0;
			for (int i = 1; i <= n; i++) {
				dp[i][0] = b * i;
			}
			for (int j = 1; j <= m; j++) {
				dp[0][j] = j * c;
			}

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					int c1 = Integer.MAX_VALUE;
					if (s[i - 1] == t[j - 1]) {
						c1 = dp[i - 1][j - 1];
					}
					int c2 = Integer.MAX_VALUE;
					if (s[i - 1] != t[j - 1]) {
						c2 = dp[i - 1][j - 1] + c;
					}
					int c3 = dp[i][j - 1] + a;
					int c4 = dp[i - 1][j] + b;
					dp[i][j] = Math.min(Math.min(c1, c2), Math.min(c3, c4));
				}
			}

			return dp[n][m];
		}
	}
}
