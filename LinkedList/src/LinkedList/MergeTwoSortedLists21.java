package LinkedList;

/**
 * @author Evelyn
 * @version 1.0
 */
// 将两个升序链表合并为一个新的 升序 链表并返回
// 新链表是通过拼接给定的两个链表的所有节点组成的
// 测试链接 :
public class MergeTwoLists {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	/**
	 * 不推荐设置虚拟头节点的做法！想象这么一个场景，工程上的定义了某种链表节点，初始化函数可能很复杂，
	 * 并不像刷题中的节点可以随意构造，此时你要怎么办？
	 * 工程上的节点初始化的时候，内存占用可能很大，并不像刷题中的节点代价那么少，此时你要怎么办？
	 * 不用假节点，目的是基于这样一种追求：
	 * 一个结构的事情，就在这个结构自身上做调整，解决这个结构自身的问题，而不是再引入外部的东西。
	 */
	class Solution21 {

		public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
			if (head1 == null || head2 == null) {
				return head1 == null ? head2 : head1;
			}
			ListNode head = head1.val <= head2.val ? head1 : head2;
			ListNode cur1 = head.next;
			ListNode cur2 = head == head1 ? head2 : head1;
			ListNode pre = head;
			while (cur1 != null && cur2 != null) {
				if (cur1.val <= cur2.val) {
					pre.next = cur1;
					cur1 = cur1.next;
				} else {
					pre.next = cur2;
					cur2 = cur2.next;
				}
				pre = pre.next;
			}
			pre.next = cur1 != null ? cur1 : cur2;
			return head;
		}

	}

}
