package HighFrequency01;

// 求完全二叉树的节点个数
// 测试链接 : https://leetcode.cn/problems/count-complete-tree-nodes/
public class Code09_L222_CountCompleteTreeNodes {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

/*
两种情况考虑
1、先调用cur.right，如果右树一直向左，到达最深层，即cur左树是满的，求左树和+调用cur.right
2、右树没有达到最深层，即它自己是满的，求右树和+调用cur.left
时间复杂度低于O n，因为不是所有node都遍历了一遍，O h^2
所有都遍历一遍，复杂度就是O n
 */
	public static int countNodes(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return f(head, 1, mostLeft(head, 1));
	}

	// cur : 当前来到的节点
	// level :  当前来到的节点在第几层
	// h : 整棵树的高度！！！，不是cur这棵子树的高度
	// 求 : cur这棵子树上有多少节点
	public static int f(TreeNode cur, int level, int h) {
		if (level == h) {      //如果当前层等于最大高度h，即已经到达最大深度，当前节点是leaf，没有其他子节点，返回1
			return 1;
		}
		if (mostLeft(cur.right, level + 1) == h) {
			// cur右树上的最左节点，扎到了最深层

			//2^(h -level)
			//level为什么+1？因为level是cur的层数，递归调用cur.right是cur的下一层
			return (1 << (h - level)) + f(cur.right, level + 1, h);
		} else {
			// cur右树上的最左节点，没扎到最深层
			return (1 << (h - level - 1)) + f(cur.left, level + 1, h);
		}
	}

	// 当前节点是cur，并且它在level层
	// 返回从cur开始不停往左，能扎到几层，这个层数就是树实际的层数，不是该节点的相对高度
	public static int mostLeft(TreeNode cur, int level) {
		while (cur != null) {
			level++;
			cur = cur.left;
		}
		return level - 1;  //遇到空才停，所以要减1
	}

}
