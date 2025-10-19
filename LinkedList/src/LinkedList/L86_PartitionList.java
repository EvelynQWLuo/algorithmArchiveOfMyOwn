package LinkedList;

/**
 * @author Evelyn
 * @version 1.0
 */
// 给你一个链表的头节点 head 和一个特定值 x
// 请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
// 你应当 保留 两个分区中每个节点的初始相对位置

public class L86_PartitionList {


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

	class Solution86 {

		public static ListNode partition(ListNode head, int x) {
			ListNode leftHead = null, leftTail = null;   // < x的区域
			ListNode rightHead = null, rightTail = null;   // >=x的区域
			ListNode next = null;
			while (head != null) {
				// 6     5    3    4    2    1     1     7           x=4
				//h    next
				next = head.next;     //1.依然是next记录head的下一个节点，同反转链表
				head.next = null;        //2.记录完之后，就可以把head.next从原链表中断开了

				if (head.val < x) {
					if (leftHead == null) {  //为空时，头尾都要指向同一个节点head
						leftHead = head;
					} else {
						leftTail.next = head;    //不为空，tail下一个挂上当前head
					}
					leftTail = head;
				} else {
					if (rightHead == null) { //同理，为空时，头尾都要指向同一个节点head
						rightHead = head;
					} else {
						rightTail.next = head;
					}
					rightTail = head;
				}
				head = next;      //每次挂完一个节点之后，最后一步是把head往后移1位
			}
			if (leftHead == null) {
				return rightHead;
			}
			// < x的区域有内容！
			leftTail.next = rightHead;
			return leftHead;
		}

	}

}
