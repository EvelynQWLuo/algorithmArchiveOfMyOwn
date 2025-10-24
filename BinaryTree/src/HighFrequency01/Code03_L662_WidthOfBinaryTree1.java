package HighFrequency01;

import java.util.ArrayDeque;
import java.util.Deque;

// 二叉树的最大特殊宽度
// 测试链接 : https://leetcode.cn/problems/maximum-width-of-binary-tree/
public class Code03_L662_WidthOfBinaryTree1 {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 提交以下的方法
	// 用每次处理一层的优化bfs就非常容易实现
	// 如果测试数据量变大了就修改这个值
	public static int MAXN = 3001;

	public static TreeNode[] nq = new TreeNode[MAXN];

	public static long[] iq = new long[MAXN];

	public static int l, r;

	public static int widthOfBinaryTree(TreeNode root) {
		int ans = 1;
		l = r = 0;
		nq[r] = root;
		iq[r++] = 1;
		while (l < r) {
			int size = r - l;
			ans = Math.max(ans, (int) (iq[r - 1] - iq[l] + 1));
			for (int i = 0; i < size; i++) {
				TreeNode node = nq[l];
				long id = iq[l++];
				if (node.left != null) {
					nq[r] = node.left;
					iq[r++] = id * 2;
				}
				if (node.right != null) {
					nq[r] = node.right;
					iq[r++] = id * 2 + 1;
				}
			}
		}
		return ans;
	}
/*
依然是我的解法
java原生queue.
两个queue，一个存node，一个存它在树中的序号。
弹出node的时候，同时弹出序号。
最大宽度即是每一层最右节点和最左节点的序号查，这样就不用考虑中间到底有多少个空的节点。
每一层最左和最右，分别在queue的头和尾。
 */
	class MySolution662 {
		public int widthOfBinaryTree(TreeNode root) {
			int ans = 1;
			Deque<TreeNode> nodeq = new ArrayDeque<>();
			Deque<Integer> iq = new ArrayDeque<>();

			nodeq.add(root);
			iq.add(1);

			while (!nodeq.isEmpty()) {
				int size = nodeq.size();
				ans = Math.max(ans, (iq.peekLast() - iq.peekFirst() + 1));
				for (int j = 0; j < size; j++) {
					TreeNode curr = nodeq.poll();
					int i = iq.poll();
					if (curr.left != null) {
						nodeq.add(curr.left);
						iq.add(i * 2);
					}
					if (curr.right != null) {
						nodeq.add(curr.right);
						iq.add(i * 2 + 1);
					}
				}
			}
			return ans;
		}
	}

}
