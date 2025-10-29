package TwoPointers;

/**
 * @author Evelyn
 * @version 1.0
 */
public class L125_ValidPalindrome {
/*
时间复杂度：O(n) - 最优

只遍历一次字符串，每个字符最多被访问一次
无法做得更好（必须至少看一遍所有字符）

空间复杂度：O(1) - 最优

只用两个指针变量
不创建任何额外的数据结构或字符串
 */
    public boolean isPalindrome(String s) {
        if (s.isEmpty())
            return true;
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            } else {
                if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }
}
