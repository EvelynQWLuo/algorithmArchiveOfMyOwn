package TwoPointers;

import java.util.Arrays;

// 救生艇
// 给定数组 people
// people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit
// 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit
// 返回 承载所有人所需的最小船数
// 测试链接 : https://leetcode.cn/problems/boats-to-save-people/
public class Code04_L881_BoatsToSavePeople {
/*
顺序不影响船的数量，所以先排序
1）l+r>limit，则r单独一个船。why？最大这个和最小都不能一个船了，它肯定要自己一个船
 */
	// 时间复杂度O(n * logn)，因为有排序，额外空间复杂度O(1)
	public static int numRescueBoats(int[] people, int limit) {
		Arrays.sort(people);
		int ans = 0;
		int l = 0;
		int r = people.length - 1;
		int sum = 0;
		while (l <= r) {
			sum = l == r ? people[l] : people[l] + people[r];
			if (sum > limit) {
				r--;
			} else {
				l++;
				r--;
			}
			ans++;
		}
		return ans;
	}

}
