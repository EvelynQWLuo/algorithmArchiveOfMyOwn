package HighFrequency01;

import java.util.ArrayDeque;
import java.util.Deque;

// 验证完全二叉树
// 测试链接 : https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
public class Code08_L958_CompletenessOfBinaryTree {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 提交以下的方法
	// 如果测试数据量变大了就修改这个值
	public static int MAXN = 101;

	public static TreeNode[] queue = new TreeNode[MAXN];

	public static int l, r;
/*
完全二叉树判断条件
有且只有2个条件，必需满足：
1、有右无左，false
2、一旦发现某node孩子不全，那么接下来所有node都必需是叶子node，否则false

 */
	public static boolean isCompleteTree(TreeNode h) {
		if (h == null) {
			return true;
		}
		l = r = 0;
		queue[r++] = h;
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		while (l < r) {
			h = queue[l++];
			if ((h.left == null && h.right != null) || (leaf && (h.left != null || h.right != null))) {
				return false;
			}
			if (h.left != null) {
				queue[r++] = h.left;
			}
			if (h.right != null) {
				queue[r++] = h.right;
			}
			if (h.left == null || h.right == null) {
				leaf = true;
			}
		}
		return true;
	}


	class MySolution958 {
		public boolean isCompleteTree(TreeNode root) {
			if (root == null)
				return true;

			Deque<TreeNode> queue = new ArrayDeque<>();
			queue.add(root);
			// 是否遇到过左右两个孩子不双全的节点
			boolean leaf = false;

			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					TreeNode curr = queue.poll();
					//1、有右无左
					if ((curr.left == null && curr.right != null) ||

							//2、一旦发现某node孩子不全，那么接下来所有node都必需是叶子node
							(leaf && (curr.left != null || curr.right != null))) {

						   return false;
					}
					if (curr.left != null) {
						queue.add(curr.left);
					}
					if (curr.right != null) {
						queue.add(curr.right);
					}
					if (curr.left == null || curr.right == null) {
						leaf = true;
					}
				}
			}
			//如果遍历过程中始终没有遇到违规，返回true
			return true;

		}
	}
}
