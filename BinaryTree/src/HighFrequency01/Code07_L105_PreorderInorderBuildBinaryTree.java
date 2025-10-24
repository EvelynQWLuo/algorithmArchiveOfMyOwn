package HighFrequency01;

import java.util.HashMap;

// 利用先序与中序遍历序列构造二叉树
// 测试链接 : https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Code07_L105_PreorderInorderBuildBinaryTree {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}
	public static HashMap<Integer, Integer> map = new HashMap<>();
	// 提交如下的方法
	public static TreeNode buildTree(int[] pre, int[] in) {

		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}

		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		return f(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	public static TreeNode f(int[] pre, int l1, int r1, int[] in, int l2, int r2) {
		if (l1 > r1) {       //无效索引
			return null;
		}
		TreeNode head = new TreeNode(pre[l1]);   //创建出头节点
		if (l1 == r1) {      //只有一个节点
			return head;
		}
		int index = map.get(pre[l1]);      //找到root再inorder中的index
		// pre : l1【........】[.......r1]
		// in  : 【l2......】index [........r2]
		// (...)是左树对应，[...]是右树的对应
		int leftlen = index - l2;

		head.left = f(pre, l1 + 1, l1 + leftlen,
				           in, l2, index - 1);
		head.right = f(pre, l1 + index - l2 + 1, r1,
				              in, index + 1, r2);
		return head;
	}
/*
1、用hashmap存放inorder的值和index，为了不重复遍历inorder去找每个子树的root节点
2、通过前序第一个index找到root，构建root
3、
 */
	class MySolution105 {
		HashMap<Integer, Integer> map = new HashMap<>();

		public TreeNode buildTree(int[] preorder, int[] inorder) {
			for (int i = 0; i < inorder.length; i++) {
				map.put(inorder[i], i);
			}
			return findNode(preorder, 0, preorder.length, inorder, 0, inorder.length);
		}

		public TreeNode findNode(int[] preorder, int preBegin, int preEnd,
								 int[] inorder, int inBegin, int inEnd) {

			if(preBegin>= preEnd || inBegin>= inEnd) return null;
			int rootIndex= map.get(preorder[preBegin]);

			TreeNode root = new TreeNode(preorder[preBegin]);
			int leftLen = rootIndex - inBegin;
			root.left=findNode(preorder, preBegin+1, preBegin+leftLen+1,

					inorder, inBegin, rootIndex);

			root.right= findNode(preorder, preBegin+leftLen+1, preEnd,

					inorder, rootIndex+1, inEnd);
			return root;
		}
	}

}
