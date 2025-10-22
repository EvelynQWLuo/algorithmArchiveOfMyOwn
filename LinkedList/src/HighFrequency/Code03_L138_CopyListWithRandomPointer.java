package HighFrequency;

// 复制带随机指针的链表
// 测试链接 : https://leetcode.cn/problems/copy-list-with-random-pointer/
public class Code03_L138_CopyListWithRandomPointer {

	// 不要提交这个类
	public static class Node {
		public int val;
		public Node next;
		public Node random;

		public Node(int v) {
			val = v;
		}
	}

	// 提交如下的方法
	public static Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}

		//两个指针创建新链表
		Node cur = head;
		Node next = null;      //记录cur的下一个节点

		// 1 -> 2 -> 3 -> ...
		// 变成 : 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> ...
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.val);  //指向创建的新节点
			cur.next.next = next;                  //新node连上next
			cur = next;                 //cur也跳到next
		}
		//新链表创建完毕之后，cur继续跳回新链表头，进行random copy
		cur = head;
		Node copy = null;     //copy作为复制链表的指针
		// 利用上面新老节点的结构关系，设置每一个新节点的random指针
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			copy.random = cur.random != null ? cur.random.next : null;   //比如1的random是3，那么 1'的random是 3'，即是1.random.next
			cur = next;
		}

		//最后一步分离链表
		Node ans = head.next;
		cur = head;
		// 新老链表分离 : 老链表重新连在一起，新链表重新连在一起
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			cur.next = next;
			copy.next = next != null ? next.next : null;
			cur = next;
		}
		// 返回新链表的头节点
		return ans;
	}

}
