package HighFrequency01;

// 求二叉树的最大、最小深度
public class Code04_DepthOfBinaryTree {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 测试链接 : https://leetcode.cn/problems/maximum-depth-of-binary-tree/
	public static int maxDepth(TreeNode root) {
		return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}

	// 测试链接 : https://leetcode.cn/problems/minimum-depth-of-binary-tree/
	/*
	最小与最大的不同之处，最小要避免空的子树
	 */
	public int minDepth(TreeNode root) {
		if (root == null) {
			// 当前的树是空树
			return 0;
		}
		if (root.left == null && root.right == null) {
			// 当前root是leaf
			return 1;
		}
		int ldeep = Integer.MAX_VALUE;
		int rdeep = Integer.MAX_VALUE;

		if (root.left != null) {
			ldeep = minDepth(root.left);
		}
		if (root.right != null) {
			rdeep = minDepth(root.right);
		}
		return Math.min(ldeep, rdeep) + 1;
	}

}
