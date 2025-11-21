package Strings;

import java.util.Arrays;

/**
 * @author Evelyn
 * @version 1.0
 */
/*
核心思想
与其逐个比较所有字符串，不如先排序字符串数组，这样最长公共前缀必定是排序后的第一个和最后一个字符串的公共前缀。
为什么这样做？
排序的妙用：

排序后，字典序最小的字符串在前，字典序最大的在后
如果第一个和最后一个字符串有公共前缀，那么中间所有字符串必然都包含这个前缀
这是因为排序保证了中间的字符串在字典序上介于两者之间

反证法理解：
假设有公共前缀被第一个和最后一个字符串共享，但被某个中间字符串违反了。这与排序的字典序性质矛盾。
工作步骤

排序字符串数组 - 按字典序排列
只比较首尾两个字符串 - strs[0] 和 strs[strs.length-1]
逐字符比较 - 从左到右对比这两个字符串
返回结果 - 返回公共前缀部分
 */
public class Code01_L014_LongestCommonPrefix {

    //纯暴力可过，就是硬比较第一个位置的String的每一个字符和后面位置的String的每一个字符
    class BruteforceSolution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";

            // 遍历第一个字符串的每一个字符位置
            for (int i = 0; i < strs[0].length(); i++) {
                char c = strs[0].charAt(i);

                // 拿第一个字符串的第 i 个字符，与其他所有字符串的第 i 个字符对比
                for (int j = 1; j < strs.length; j++) {
                    // 如果其他字符串长度不足 i+1，或者第 i 个字符不匹配
                    if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                        // 公共前缀到此为止
                        return strs[0].substring(0, i);
                    }
                }
            }

            // 如果循环结束都没有返回，说明第一个字符串全是公共前缀
            return strs[0];
        }
    }

    //排序法
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            Arrays.sort(strs); // 排序，时间复杂度 O(n*k*log(n))，k为字符串平均长度
            String s1 = strs[0]; // 字典序最小
            String s2 = strs[strs.length - 1]; // 字典序最大
            int idx = 0;
            while (idx < s1.length() && idx < s2.length()) {
                if (s1.charAt(idx) == s2.charAt(idx)) {
                    idx++; // 字符相同，继续下一个
                } else {
                    break; // 遇到不同字符就停止
                }
            }
            return s1.substring(0, idx); // 返回公共前缀
        }
    }
}
