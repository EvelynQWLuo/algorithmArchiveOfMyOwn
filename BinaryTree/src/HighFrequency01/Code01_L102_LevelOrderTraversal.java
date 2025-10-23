package HighFrequency01;

import java.util.*;

// 二叉树的层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
public class Code01_L102_LevelOrderTraversal {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 提交时把方法名改为levelOrder，此方法为普通bfs，此题不推荐

	public static List<List<Integer>> levelOrder1(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root != null) {
			Deque<TreeNode> queue = new ArrayDeque<>();
			HashMap<TreeNode, Integer> levels = new HashMap<>();
			queue.add(root);
			levels.put(root, 0);
			while (!queue.isEmpty()) {
				TreeNode cur = queue.poll();
				int level = levels.get(cur);
				if (ans.size() == level) {
					ans.add(new ArrayList<>());
				}
				ans.get(level).add(cur.val);
				if (cur.left != null) {
					queue.add(cur.left);
					levels.put(cur.left, level + 1);
				}
				if (cur.right != null) {
					queue.add(cur.right);
					levels.put(cur.right, level + 1);
				}
			}
		}
		return ans;
	}

	// 如果测试数据量变大了就修改这个值
	public static int MAXN = 2001;

	public static TreeNode[] queue = new TreeNode[MAXN];

	public static int l, r;

	// 提交时把方法名改为levelOrder，此方法为每次处理一层的优化bfs，此题推荐
	public static List<List<Integer>> levelOrder2(TreeNode root) {

		List<List<Integer>> ans = new ArrayList<>();
		if (root != null) {
			l = r = 0;
			queue[r++] = root;
			while (l < r) { // 队列里还有东西
				int size = r - l;
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < size; i++) {
					TreeNode cur = queue[l++];
					list.add(cur.val);
					if (cur.left != null) {
						queue[r++] = cur.left;
					}
					if (cur.right != null) {
						queue[r++] = cur.right;
					}
				}
				ans.add(list);
			}
		}
		return ans;
	}


	//我的解法是这两种的结合
	//使用原生queue，但是也通过size来记录每一层的数据
	class Solution102 {
		public List<List<Integer>> levelOrder(TreeNode root) {
			List<List<Integer>> ans = new ArrayList<>();

			if (root == null)
				return ans;

			Deque<TreeNode> queue = new ArrayDeque<>();
			queue.offer(root);      //先将不等于null的root放入queue

			while (!queue.isEmpty()) {        //queue非空循环进行
				int size = queue.size();          //拿到当queue的size就是当前层的节点数
				List<Integer> level = new ArrayList<>();           //用一个list来store当前层的节点
				for (int i = 0; i < size; i++) {                         //for循环遍历当前层
					TreeNode cur = queue.poll();               //弹出一个node

					level.add(cur.val);                             //记录到当前层的list中

					if (cur.left != null) {                        //如果弹出节点的左右不为空，放入queue
						queue.offer(cur.left);
					}

					if (cur.right != null) {
						queue.offer(cur.right);
					}
				}

				ans.add(level);                          //     当前层遍历结束，把记录当前level结果的list放入最终的结果集ans中
			}

			return ans;
		}
	}
}
