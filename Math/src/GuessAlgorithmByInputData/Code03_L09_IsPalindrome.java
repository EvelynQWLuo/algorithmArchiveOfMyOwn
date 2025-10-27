package GuessAlgorithmByInputData;
//L009
// 超级回文数中的一个小函数，本身也是一道题 : 判断一个数字是不是回文数
/*
不把数字转String，直接求回文的方法
 */
// 测试链接 : https://leetcode.cn/problems/palindrome-number/
public class Code03_L09_IsPalindrome {

	public static boolean isPalindrome(int num) {
		if (num < 0) {
			return false;
		}
		int offset = 1;
		// 注意这么写是为了防止溢出
		//得到一个跟需要验证的num对齐的offset
		while (num / offset >= 10) {
			offset *= 10;
		}
		// 首尾判断
		//num:   5 2 7 2 5
 		//offset: 1 0 0 0 0
		while (num != 0) {
			if (num / offset != num % 10) {      //取到首尾数字对比
				return false;
			}
			num = (num % offset) / 10;         //去掉已经对比过的首位数字，继续
			offset /= 100;                              //num去掉了两位，offset也要同步
		}
		return true;
	}

}
