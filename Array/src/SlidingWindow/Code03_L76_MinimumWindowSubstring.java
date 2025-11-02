package SlidingWindow;

// 最小覆盖子串
// 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串
// 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
// 测试链接 : https://leetcode.cn/problems/minimum-window-substring/
public class Code03_L76_MinimumWindowSubstring {
/*
负债表：target中每个字符有几个，都用负值表示String欠target的个数
总负债：target中总共有几个字符
l停在0不动，r向右前进，不断还债，直到debt为0
然后l向右移动，此时l位置的字符在负债表中为正，才可以删除
 */
	public static String minWindow(String str, String tar) {
		char[] s = str.toCharArray();
		char[] t = tar.toCharArray();
		// 每种字符的欠债情况
		// cnts[i] = 负数，代表字符i有负债
		// cnts[i] = 正数，代表字符i有盈余
		int[] cnts = new int[256];
		for (char cha : t) {
			cnts[cha]--;
		}
		// 最小覆盖子串的长度
		int len = Integer.MAX_VALUE;
		// 从哪个位置开头，发现的最小覆盖子串
		int start = 0;
		// 总债务
		int debt = t.length;
		for (int l = 0, r = 0; r < s.length; r++) {
			// 窗口右边界向右，给出字符
			if (cnts[s[r]]++ < 0) {
				debt--;
			}
			if (debt == 0) {
				// 窗口左边界向右，拿回字符
				while (cnts[s[l]] > 0) {
					cnts[s[l++]]--;
				}
				// 以r位置结尾的达标窗口，更新答案
				if (r - l + 1 < len) {
					len = r - l + 1;
					start = l;
				}
			}
		}
		return len == Integer.MAX_VALUE ? "" : str.substring(start, start + len);
	}

	class MySolution {
		public String minWindow(String s, String t) {
			char[] str = s.toCharArray();
			char[] tar = t.toCharArray();
			int[] counts = new int[256];
			for (char c : tar) {      //统计target最终每个字符出现的次数，因为是欠债所以是数
				counts[c]--;
			}

			int debt = tar.length;
			int start = 0;
			int len = Integer.MAX_VALUE;

			for (int l = 0, r = 0; r < str.length; r++) {
				counts[str[r]]++;     //遍历总String，遇到每个字符，统计出现次数
				if (counts[str[r]] <= 0) {                 //统计完之后，如果有字符的出现次数是负的，证明之前有欠债，且这个欠债已经偿还一部分
					//counts[str[r]]++;//++不能放这，会报错
					debt--;        //所以debt要--

				}
				if (debt == 0) {               //在debt为0的情况下，说明target中所有字符都已经覆盖了
					while (counts[str[l]] > 0) {     //那么在l处字符有盈余的情况下，缩减窗口，看是否能得到更小的窗口
						counts[str[l]]--;
						l++;
					}
					if (r - l + 1 < len) {
						len = r - l + 1;
						start = l;
					}

				}
			}
			return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
		}
	}

}
