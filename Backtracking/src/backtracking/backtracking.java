package backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Evelyn
 * @version 1.0
 */
//77. Combinations
class Solution77 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return result;
    }

    private void backtracking(int n, int k , int startIndex){

        if(path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = startIndex; i<=n; i++){
            if(n - i + 1 < k - path.size()) {
                return;
            }
            path.add(i);
            backtracking(n, k, i+1);
            path.remove(path.size()- 1);
        }

    }
}

//216. Combination Sum III
class Solution216 {
    List<List<Integer>> result = new ArrayList<>();
    ArrayDeque path = new ArrayDeque<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k, n, 1, 0);
        return result;
    }

    private void backtracking(int k, int n, int startIndex, int sum) {
        //在组合总和2之前的写法
        // if (sum > n) {
        //     return;
        // }

        // 剪枝1：剩余数字不够
        if (path.size() == k) {
            if (sum == n)
                result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= 9; i++) {
            if ((9 - i + 1) < (k - path.size())) {
                return;
            }
            // 剪枝2：提前判断（类似组合总和II）
            if(sum + i > n) break;

            sum += i;
            path.add(i);
            backtracking(k, n, i + 1, sum);
            path.removeLast();
            sum -= i;
        }
    }
}

//17. Letter Combinations of a Phone Number

class Solution17 {

    List<String> result = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

        backtracking(digits, mapping, 0);
        return result;
    }

    private void backtracking(String digits, String[] mapping, int index) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String str = mapping[digits.charAt(index) - '0'];

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            backtracking(digits, mapping, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

//39. Combination Sum
class Solution39 {
    List<List<Integer>> result = new ArrayList<>();
    ArrayDeque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0);
        return result;

    }

    private void backtracking(int[] candidates,
                              int target,
                              int sum,
                              int startIndex) {

        if (sum > target) {
            return;
        }

        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target)
                break;

            path.addLast(candidates[i]);
            sum += candidates[i];
            backtracking(candidates, target, sum, i);
            path.removeLast();
            sum -= candidates[i];
        }

    }
}

//40. Combination Sum II
class Solution40 {
    List<List<Integer>> result = new ArrayList<>();

    ArrayDeque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);
        return result;
    }

    private void backTracking(int[] candidates, int target, int sum, int start) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // 剪枝Pruning：如果当前元素加上sum已经大于target，后续元素更大，直接break
            if (sum + candidates[i] > target) {
                break; //  终止整个for循环
            }
            //去重Deduplication
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;//跳过当前迭代，继续下一个i
            }

            sum += candidates[i];
            path.addLast(candidates[i]);
            backTracking(candidates, target, sum, i + 1);
            sum -= candidates[i];
            path.removeLast();
        }
    }
}

//131. Palindrome Partitioning
class Solution131 {
    List<List<String>> result = new ArrayList<>();
    ArrayDeque<String> path = new ArrayDeque<>();

    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return result;
    }

    private void backtracking(String s, int startIndex){

        if(startIndex == s.length()){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = startIndex; i < s.length(); i++){

            if(isPalindrome(s, startIndex, i)){

                path.addLast(s.substring(startIndex, i + 1));
                backtracking(s, i + 1);
                path.removeLast();
            }
        }

    }

    private boolean isPalindrome(String s, int left, int right){
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

//93. Restore IP Addresses
class Solution93 {
    List<String> result = new ArrayList<>();
    ArrayDeque<String> path = new ArrayDeque<>();

    public List<String> restoreIpAddresses(String s) {

        if (s.length() > 12 || s.length() < 4) return result;
        backTracking(s, 0);
        return result;
    }

    private void backTracking(String s, int startIndex) {

        if (path.size() == 4 && startIndex == s.length()) {
            result.add(String.join(".", path));
            return;
        }

        //保留这段剪枝，能提高runtime
        if (path.size() == 4 || startIndex == s.length()) {
            return;
        }

        for (int i = startIndex; i < s.length() && i < startIndex + 3; i++) {
            String segment = s.substring(startIndex, i + 1);

            if (isValid(segment)) {
                path.addLast(segment);
                backTracking(s, i + 1);
                path.removeLast();
            }
        }
    }

    private boolean isValid(String segment) {
        // 不能有前导零（除非是"0"本身）
        if (segment.length() > 1 && segment.charAt(0) == '0') {
            return false;
        }

        // 转换为数字并检查范围，String转数字的常用方法，背
        int num = 0;
        for (char c : segment.toCharArray()) {
            num = num * 10 + (c - '0');
            if (num > 255) return false;
        }

        return true;
    }
}

//78. Subsets
class Solution78 {
    //秒了
    List<List<Integer>> result = new ArrayList<>();
    ArrayDeque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        backtracking(nums, 0);
        return result;
    }

    private void backtracking(int[] nums, int startIndex) {

        result.add(new ArrayList<>(path));
        //if(startIndex >= nums.length) return;

        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();

        }
        return;
    }
}

//90. Subsets II
class Solution90 {
    //秒了
    List<List<Integer>> result = new ArrayList<>();
    ArrayDeque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtracking(nums, 0);
        return result;
    }

    private void backtracking(int[] nums, int startIndex){

        result.add(new ArrayList<>(path));
        //if(startIndex >= nums.length) return;

        for(int i = startIndex; i < nums.length; i++){
            //去重Deduplication
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;//跳过当前迭代，继续下一个i
            }
            path.addLast(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();

        }

    }
}
