package HighFrequency01;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 二叉树的锯齿形层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
public class Code02_L103_ZigzagLevelOrderTraversal {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 提交以下的方法
	// 用每次处理一层的优化bfs就非常容易实现
	// 如果测试数据量变大了就修改这个值
	public static int MAXN = 2001;

	public static TreeNode[] queue = new TreeNode[MAXN];

	public static int l, r;

	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root != null) {
			l = r = 0;
			queue[r++] = root;
			// false 代表从左往右
			// true 代表从右往左
			boolean reverse = false; 
			while (l < r) {
				int size = r - l;
				ArrayList<Integer> list = new ArrayList<Integer>();
				// reverse == false, 左 -> 右， l....r-1, 收集size个
				// reverse == true,  右 -> 左， r-1....l, 收集size个
				// 左 -> 右, i = i + 1
				// 右 -> 左, i = i - 1
				for (int i = reverse ? r - 1 : l, j = reverse ? -1 : 1, k = 0; k < size; i += j, k++) {
					TreeNode cur = queue[i];
					list.add(cur.val);
				}
				for (int i = 0; i < size; i++) {
					TreeNode cur = queue[l++];
					if (cur.left != null) {
						queue[r++] = cur.left;
					}
					if (cur.right != null) {
						queue[r++] = cur.right;
					}
				}
				ans.add(list);
				reverse = !reverse;
			}
		}
		return ans;
	}
   /*
   依然是我的解法，还是用java的deque，但是用size统计每层的大小
   reverse是通用的
    */
	class MySolution103 {
		public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
			List<List<Integer>> ans = new ArrayList<>();
			if (root == null)
				return ans;

			Deque<TreeNode> queue = new ArrayDeque<>();
			queue.add(root);
			boolean reverse = false;

			while (!queue.isEmpty()) {
				int size = queue.size();
				List<Integer> level = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					if (!reverse) {
						TreeNode curr = queue.pollFirst();
						level.add(curr.val);
						if (curr.left != null)
							queue.addLast(curr.left);
						if (curr.right != null)
							queue.addLast(curr.right);
					} else {//反过来遍历的层
						TreeNode curr = queue.pollLast();    //从队尾弹出
						level.add(curr.val);
						if (curr.right != null)                     //先放入弹出节点的右节点，且是从队头放入
							queue.addFirst(curr.right);
						if (curr.left != null)
							queue.addFirst(curr.left);
					}
				}
				ans.add(level);
				reverse = !reverse;             //当前层处理完之后，重置reverse
			}

			return ans;
		}
	}

}


