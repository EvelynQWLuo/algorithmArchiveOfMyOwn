package HighFrequency01;

// 二叉树前序序列化和反序列化
// 测试链接 : https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
public class Code05_L297_PreorderSerializeAndDeserialize {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

    // 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化
    // 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
    // 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
    // 比如如下两棵树
    //         __2
    //        /
    //       1
    //       和
    //       1__
    //          \
    //           2
    // 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
	// 提交这个类
	public class Codec {

		public String serialize(TreeNode root) {
			StringBuilder sb = new StringBuilder();
			f(root, sb);
			return sb.toString();
		}

      //序列化
		void f(TreeNode root, StringBuilder sb) {
			if (root == null) {                       //如果node为空，只记录一个#，
				sb.append("#,");
			} else {                                    //不为空，记录val + ，然后递归左右
				sb.append(root.val + ",");
				f(root.left, sb);
				f(root.right, sb);
			}
		}


       //反序列化
		public TreeNode deserialize(String data) {
			String[] vals = data.split(",");           //String 按逗号分割成数组
			index = 0;                //计数
			return g(vals);       //传入数组
		}

		// 当前数组消费到哪了
		public static int index;

		TreeNode g(String[] vals) {
			String cur = vals[index++];           //依次取出数组中的每个值
			if (cur.equals("#")) {                    //如果是#，返回null
				return null;
			} else {                              //不为空就要创建新弄的
				TreeNode head = new TreeNode(Integer.valueOf(cur));     //记得String转int
				head.left = g(vals);
				head.right = g(vals);
				return head;
			}
		}

	}

}
