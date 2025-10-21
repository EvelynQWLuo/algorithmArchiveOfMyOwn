package Sort;

import java.util.ArrayList;
import java.util.PriorityQueue;

// 023合并K个升序链表

public class L023_MergeKSortedLists {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	// 提交以下的方法
	public static ListNode mergeKLists(ListNode[] lists) {
		// 小顶堆
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
		for (ListNode h : lists) {
			// 遍历数组lists，拿出所有的头！
			if (h != null) {
				minHeap.add(h);
			}
		}
		if (minHeap.isEmpty()) {
			return null;
		}

		// 先弹出一个节点，做总头部
		//为什么会有两次压入minHeap的操作？
		//因为没有设置虚拟头节点，头部就需要单独处理。
		ListNode h = minHeap.poll();
		ListNode p = h;
		if (p.next != null) {
			minHeap.add(p.next);
		}

		while (!minHeap.isEmpty()) {
			ListNode curr = minHeap.poll();
			p.next = curr;     //弹出的node挂到新链表上
			p = p.next;       //p移动到下一个
			if (curr.next != null) {
				minHeap.add(curr.next);
			}
		}
		return h;
	}

}
