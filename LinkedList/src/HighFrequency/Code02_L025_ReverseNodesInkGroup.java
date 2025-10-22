package HighFrequency;

// 每k个节点一组翻转链表
// 测试链接：https://leetcode.cn/problems/reverse-nodes-in-k-group/
public class Code02_L025_ReverseNodesInkGroup {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	// 提交如下的方法
	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode start = head;
		ListNode end = findEnd(start, k);   //找第一组的end
		if (end == null) {      //如果end为空，即链表长度，不足需要反转的k个，不需要反转，直接return原链表
			return head;
		}
		// 第一组很特殊因为牵扯到换头的问题
		head = end;             //第一个节点换成第一组的尾节点，即为新头
		reverse(start, end);    //反转第一组

		// 翻转之后start变成了上一组的结尾节点
		ListNode lastTeamEnd = start;
		//因为在反转过程中，已经让lastTeamEnd连上下一组未反转链表的头节点了，所以要判空
		while (lastTeamEnd.next != null) {
			start = lastTeamEnd.next;
			end = findEnd(start, k);
			if (end == null) {
				return head;
			}
			reverse(start, end);  //反转知识改变了指针的指向，但是原来的start和end还是start和end
			lastTeamEnd.next = end;
			lastTeamEnd = start;
		}
		return head;
	}

	// 当前组的开始节点是node，往下数k个找到当前组的结束节点返回
	public static ListNode findEnd(ListNode node, int k) {
		while (--k != 0 && node != null) {        //--k只能这样写，放循环里就不对
			node = node.next;
		}
		return node;
	}

	// s -> a -> b -> c -> e -> 下一组的开始节点
	// 上面的链表通过如下的reverse方法调整成 : e -> c -> b -> a -> s -> 下一组的开始节点
	/*
	跟206直接反转链表不同的一点是，本题的反转需要将反转后的新的尾节点（也就是原来的start），指向原来的end的下一个节点。
	1、所以，第一步是移动end到它的下一位，方便最后直接指向它
	2、还是同之前206反转链表的3个指针，pre，curr，next
	3、

	 */
	public static void reverse(ListNode start, ListNode end) {
		end = end.next;
		ListNode pre = null, cur = start, next = null;
		while (cur != end) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		start.next = end;

	}

}
