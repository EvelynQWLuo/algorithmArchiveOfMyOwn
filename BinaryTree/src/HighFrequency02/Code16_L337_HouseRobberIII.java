package HighFrequency02;

// 二叉树打家劫舍问题
// 测试链接 : https://leetcode.cn/problems/house-robber-iii/
public class Code16_L337_HouseRobberIII {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}


	public static int rob(TreeNode root) {
		f(root);
		return Math.max(yes, no);
	}

	// 全局变量，完成了X子树的遍历，返回之后
	// yes变成，X子树在偷头节点的情况下，最大的收益
	public static int yes;

	// 全局变量，完成了X子树的遍历，返回之后
	// no变成，X子树在不偷头节点的情况下，最大的收益
	public static int no;

	public static void f(TreeNode root) {
		if (root == null) {//空树，偷不偷都一样
			yes = 0;
			no = 0;
		} else {    //非空
			int y = root.val;  //当前node自己的yes和no
			int n = 0;
			f(root.left); //跑完左子树，yes和no更新成左树的yes和no
			y += no;
			n += Math.max(yes, no);
			f(root.right); //跑完右子树，yes和no更新成右树的yes和no
			y += no;
			n += Math.max(yes, no);
			yes = y; //都跑完之后，更新全局yes和no
			no = n;
		}
	}

}
