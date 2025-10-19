package StackandQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
/**
 * @author Evelyn
 * @version 1.0
 */
// 最小栈min stack 155

	/*
	准备两个stack，一个存所有需要入栈道元素，还有一个只存最小值
	即依次压元素入栈，data是每一个都放，min只放比当前栈顶小的元素，如果是大的元素，就重复放一遍栈顶
	如果栈空，就是最开始的时候，同步压入栈
	 */

public class L155_MinStack {

	// 提交时把类名、构造方法改成MinStack
	class MinStack1 {
		public Deque<Integer> data;
		public Deque<Integer> min;


		public MinStack1() {
			//data = new Stack<Integer>();
			//min = new Stack<Integer>();
			data = new ArrayDeque<>();
			min = new ArrayDeque<>();
		}

		public void push(int val) {
			data.push(val);
			if (min.isEmpty() || val <= min.peek()) {
				min.push(val);
			} else { // !min.isEmpty() && val > min.peek()
				min.push(min.peek());
			}
		}

		public void pop() {
			data.pop();
			min.pop();
		}

		public int top() {
			return data.peek();
		}

		public int getMin() {
			return min.peek();
		}
	}

	// 提交时把类名、构造方法改成MinStack
	class MinStack2 {
		// leetcode的数据在测试时，同时在栈里的数据不超过这个值
		// 这是几次提交实验出来的，哈哈
		// 如果leetcode补测试数据了，超过这个量导致出错，就调大
		public final int MAXN = 8001;

		public int[] data;
		public int[] min;
		int size;

		public MinStack2() {
			data = new int[MAXN];
			min = new int[MAXN];
			size = 0;
		}

		public void push(int val) {
			data[size] = val;
			if (size == 0 || val <= min[size - 1]) {
				min[size] = val;
			} else {
				min[size] = min[size - 1];
			}
			size++;
		}

		public void pop() {
			size--;
		}

		public int top() {
			return data[size - 1];
		}

		public int getMin() {
			return min[size - 1];
		}
	}

}
