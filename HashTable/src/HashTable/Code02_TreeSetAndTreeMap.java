package HashTable;

import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Evelyn
 * @version 1.0
 */
/*
有序表的用法（认为是集合，但是有序组织）
TreeSet和TreeMap原理一样，有无伴随数据的区别
增、删、改、查 + 很多和有序相关的操作(floor、ceilling等)，时间为O(log n)
有序表比较相同的东西会去重，如果不想去重就加入更多的比较策略（比较器定制）。堆不会去重。
有序表在java里就是红黑树实现的

 */
public class Code02_TreeSetAndTreeMap {

	public static void main(String[] args) {
		// 底层红黑树
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		treeMap.put(5, "这是5");
		treeMap.put(7, "这是7");
		treeMap.put(1, "这是1");
		treeMap.put(2, "这是2");
		treeMap.put(3, "这是3");
		treeMap.put(4, "这是4");
		treeMap.put(8, "这是8");

		System.out.println(treeMap.containsKey(1));
		System.out.println(treeMap.containsKey(10));
		System.out.println(treeMap.get(4));
		treeMap.put(4, "张三是4");
		System.out.println(treeMap.get(4));

		treeMap.remove(4);
		System.out.println(treeMap.get(4) == null);

		System.out.println("======以下是哈希表不能做到，而有序表可以的========");
		System.out.println(treeMap.firstKey());
		System.out.println(treeMap.lastKey());
		// TreeMap中，所有的key，<= 4且最近的key是什么
		System.out.println(treeMap.floorKey(4));
		// TreeMap中，所有的key，>= 4且最近的key是什么
		System.out.println(treeMap.ceilingKey(4));

		System.out.println("========");

		TreeSet<Integer> set = new TreeSet<>();
		set.add(3);
		set.add(3);
		set.add(4);
		set.add(4);
		System.out.println("有序表大小 : " + set.size());
		while (!set.isEmpty()) {
			System.out.println(set.pollFirst());
			// System.out.println(set.pollLast());
		}

		// 堆，默认小顶堆、如果要大顶堆，定制比较器！
		PriorityQueue<Integer> heap1 = new PriorityQueue<>();
		heap1.add(3);
		heap1.add(3);
		heap1.add(4);
		heap1.add(4);
		System.out.println("堆大小 : " + heap1.size());
		while (!heap1.isEmpty()) {
			System.out.println(heap1.poll());
		}

		// 定制的大顶堆，用比较器！
		PriorityQueue<Integer> heap2 = new PriorityQueue<>((a, b) -> b - a);
		heap2.add(3);
		heap2.add(3);
		heap2.add(4);
		heap2.add(4);
		System.out.println("堆大小 : " + heap2.size());
		while (!heap2.isEmpty()) {
			System.out.println(heap2.poll());
		}

	}

}
