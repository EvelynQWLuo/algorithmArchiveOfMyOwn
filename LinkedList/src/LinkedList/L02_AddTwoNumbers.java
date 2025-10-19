package LinkedList;
/**
 * @author Evelyn
 * @version 1.0
 */
// 给你两个 非空 的链表，表示两个非负的整数
// 它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头

public class L02_AddTwoNumbers {


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

	class Solution02 {

		// 也可以复用老链表
		// 不过这个实现没有这么做，都是生成的新节点(为了教学好懂)
		public static ListNode addTwoNumbers(ListNode h1, ListNode h2) {
			ListNode ans = null, curr = null;
			int carry = 0;          //if there is a carry, 是否有进位，carry就是进位的意思
			for (int sum, val;   // 声明变量, sum每位相加的和，val是新链表当前位的数字
					h1 != null || h2 != null;   // 终止条件
					h1 = h1 == null ? null : h1.next,   // 每一步h1的跳转
					h2 = h2 == null ? null : h2.next   // 每一步h2的跳转
					) {
               //求两个链表当前位置的和
				sum = (h1 == null ? 0 : h1.val)
						+ (h2 == null ? 0 : h2.val)
						+ carry;          //


				val = sum % 10;    //通过和得出当前位置的数字值
				carry = sum / 10;     //求当前sum是否有carry
				if (ans == null) {
					ans = new ListNode(val);
					curr = ans;            //curr用来在结果链表上跑
				} else {
					curr.next = new ListNode(val);
					curr = curr.next;
				}
			}
			if (carry == 1) {
				curr.next = new ListNode(1);
			}
			return ans;
		}

	}

}
/**
 * 	•	When adding 9 and 8, we get 17, so we carry 1 to the next digit.
 * 	•	The algorithm handles large integer addition by tracking the carry at each step.
 * 	•	In binary addition, if both bits are 1, a carry is generated.
 * 	•	The CPU sets the carry flag when an arithmetic overflow occurs.
 */
