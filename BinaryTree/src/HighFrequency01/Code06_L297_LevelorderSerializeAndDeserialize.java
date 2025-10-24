package HighFrequency01;

// 二叉树按层序列化和反序列化
// 测试链接 : https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
public class Code06_L297_LevelorderSerializeAndDeserialize {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

	// 提交这个类
	// 按层序列化
	public class Codec {

		public static int MAXN = 10001;

		public static TreeNode[] queue = new TreeNode[MAXN];

		public static int l, r;

		public String serialize(TreeNode root) {
			StringBuilder sb = new StringBuilder();
			//思路
			//进队列的时候，序列化
			//先序进去，再进队
			//没有怎么办？
			if (root != null) {
				sb.append(root.val + ",");
				l = 0;
				r = 0;
				queue[r++] = root;
				while (l < r) {
					root = queue[l++];
					if (root.left != null) {
						sb.append(root.left.val + ",");
						queue[r++] = root.left;
					} else {
						sb.append("#,");      //没有，不加队列，但是序列化
					}
					if (root.right != null) {
						sb.append(root.right.val + ",");
						queue[r++] = root.right;
					} else {
						sb.append("#,");
					}
				}
			}
			return sb.toString();
		}

		public TreeNode deserialize(String data) {
			if (data.equals("")) {
				return null;
			}
			String[] nodes = data.split(",");
			int index = 0;
			TreeNode root = generate(nodes[index++]);
			l = 0;
			r = 0;
			queue[r++] = root;
			while (l < r) {
				TreeNode cur = queue[l++];
				cur.left = generate(nodes[index++]);
				cur.right = generate(nodes[index++]);
				if (cur.left != null) {
					queue[r++] = cur.left;
				}
				if (cur.right != null) {
					queue[r++] = cur.right;
				}
			}
			return root;
		}

		private TreeNode generate(String val) {
			return val.equals("#") ? null : new TreeNode(Integer.valueOf(val));
		}

	}

}
