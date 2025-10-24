package HighFrequency02;

// 普通二叉树上寻找两个节点的最近公共祖先
// 测试链接 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
public class Code01_L236_LowestCommonAncestor {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 提交如下的方法
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			// 遇到空，或者p，或者q，直接返回
			return root;
		}
		//左树搜索：遇到空，或者p，或者q，直接返回
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		//右树搜索：遇到空，或者p，或者q，直接返回
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		//其实就两种情况
		//1、彼此包含
		//2、分为两树
		if (left != null && right != null) {
			// 左树也搜到，右树也搜到，返回root
			return root;
		}
		if (left == null && right == null) {
			// 都没搜到返回空
			return null;
		}
		// l和r一个为空，一个不为空
		// 返回不空的那个
		return left != null ? left : right;
	}

}
