package LinkedList;
/**
 * @author Evelyn
 * @version 1.0
 */
// 按值传递、按引用传递
// 从堆栈角度解释链表节点
// 以堆栈视角来看链表反转
public class L206_ReverseLinkedList {

	public static void main(String[] args) {
		// int、long、byte、short
		// char、float、double、boolean
		// 还有String
		// 都是按值传递
		int a = 10;
		f(a);
		System.out.println(a);

		// 其他类型按引用传递
		// 比如下面的Number是自定义的类
		Number b = new Number(5);
		g1(b);
		System.out.println(b.val);
		g2(b);
		System.out.println(b.val);

		// 比如下面的一维数组
		int[] c = { 1, 2, 3, 4 };
		g3(c);
		System.out.println(c[0]);
		g4(c);
		System.out.println(c[0]);
	}

	public static void f(int a) {
		a = 0;
	}

	public static class Number {
		public int val;

		public Number(int v) {
			val = v;
		}
	}

	public static void g1(Number b) {
		b = null;
	}

	public static void g2(Number b) {
		b.val = 6;
	}

	public static void g3(int[] c) {
		c = null;
	}

	public static void g4(int[] c) {
		c[0] = 100;
	}

	// 单向链表singly-linked list
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
    //206
	// 反转单链表测试链接 :https://leetcode.com/problems/reverse-linked-list/description/?envType=problem-list-v2&envId=nxjo4wwd
    //Time：O(n)，Space：O(1)
	class Solution206 {

		//head是一个变量，存储第一个node的内存地址的位置
		public static ListNode reverseList(ListNode head) {
			//两个指针，pre指针改变方向，next指针向后移动
			//链表中 a = b，即指针/节点a 指向 b，赋值操作改指针的走向

			ListNode pre = null; //null不是内存里的区域，它是系统里的一个单独的空间
			ListNode curr = head;

			while (curr != null) {
				ListNode next = head.next;  // 1. next指针指向链表的第2个node，即保存下一个节点

				curr.next = pre;   // 2. 第1个节点指向pre，即指向null，or反转当前节点的指向
                //指针后移
				pre = curr;      // 3. pre指向head即指向第1个节点

				curr = next;     // 4. head指针指向next指针指向的位置即第2个node，即head指针后移1位

			}
			return pre;         //pre就是head
		}

	}

	// 双向链表节点
	public static class DoubleListNode {
		public int value;
		public DoubleListNode last;
		public DoubleListNode next;

		public DoubleListNode(int v) {
			value = v;
		}
	}

	// 反转双链表
	// 没有找到测试链接
	// 如下方法是对的
	public static DoubleListNode reverseDoubleList(DoubleListNode head) {
		DoubleListNode pre = null;
		DoubleListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}

}
