package ClassicRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
组合问题Combination
怎么区分是不同的组合呢？
[1,1,    2,2,2,   3,3,   4]
1）0个1
2）1个1
3）2个1

每一个数字分布的个数不一样，就是不同的组合
所以基本思路是先排序，然后按组分析，每个数字选不同的个数
 */


//90.子集2虽然叫subsets，但是其实是组合问题
// 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的组合
// 答案 不能 包含重复的组合。返回的答案中，组合可以按 任意顺序 排列
// 注意其实要求返回的不是子集，因为子集一定是不包含相同元素的，要返回的其实是不重复的组合
// 比如输入：nums = [1,2,2]
// 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 测试链接 : https://leetcode.cn/problems/subsets-ii/
public class Code02_Combinations {

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		f(nums, 0, new int[nums.length], 0, ans);
		return ans;
	}

	public static void f(int[] nums, int i, int[] path, int size, List<List<Integer>> ans) {
		if (i == nums.length) {
			ArrayList<Integer> cur = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				cur.add(path[j]);
			}
			ans.add(cur);
		} else {
			// 下一组的第一个数的位置
			int j = i + 1;
			while (j < nums.length && nums[i] == nums[j]) {
				j++;
			}
			// 当前数x，要0个
			f(nums, j, path, size, ans);
			// 当前数x，要1个、要2个、要3个...都尝试
			for (; i < j; i++) {
				path[size++] = nums[i];
				f(nums, j, path, size, ans);
			}
		}
	}

	//90.subsets 2
	class MySolution90 {

		//即对于这题来说，122和221是同一个组合，只需要其中一个即可
		List<List<Integer>> ans = new ArrayList<>();

		public List<List<Integer>> subsetsWithDup(int[] nums) {
			Arrays.sort(nums);
			dfs(nums, 0, new int[nums.length], 0);
			return ans;

		}
		/*
		i：nums的index，但是i指针是按组的边界跳跃的，不是逐个遍历
	    size：path的大小用size来管
	 */
		public void dfs(int[] nums, int i, int[] path, int size) {
			//终止条件
			if (i == nums.length) {     //到达nums结尾，将当前的path添加到一个list结果集中去
				ArrayList<Integer> cur = new ArrayList<>();
				for (int j = 0; j < size; j++) {
					cur.add(path[j]);
				}
				ans.add(cur);
			} else {
				//如果没到end位置
				// 下一组数字的第一个数的位置
				int j = i + 1;
				//[1,   1,   1,   2,   2]
				// i     i+1 i+2 i+3
				//        j
				while (j < nums.length && nums[i] == nums[j]) {
					j++;
				}
				// 当前数x，要0个,size不变进入下一层递归
				dfs(nums, j, path, size);
				// 当前数x，要1个、要2个、要3个...都尝试
				for (; i < j; i++) {
					path[size++] = nums[i];
					dfs(nums, j, path, size);
				}
			}
		}
	}

	//77.combination
	//本题不需要按组处理，所以用标准回溯就可以做
	//时间复杂度: O(n * 2^n)：每个数字两种可能0，1，所以是2^n，数组遍历一遍*n
	//空间复杂度: O(n)
	class Solution77 {

		List<List<Integer>> ans = new ArrayList<>();
		List<Integer> path = new ArrayList<>();

		public List<List<Integer>> combine(int n, int k) {
			dfs(n, k, 1);
			return ans;
		}

		public void dfs(int n, int k, int startIndex) {
			if (path.size() == k) {
				ans.add(new ArrayList<>(path));
				return;
			}

			for (int i = startIndex; i <= n; i++) {
               //剪枝，本题也可以不用剪
				if (n - i + 1 < k - path.size()) {
					return;
				}
				path.add(i);
				dfs(n, k, i + 1);
				path.remove(path.size() - 1);
			}
		}
	}

	//216.combination sum3

	//39.combination sum
	//40.combination sum2
}
