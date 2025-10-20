package UnionFind;


// 并查集模版(牛客)
// 路径压缩 + 小挂大
// 测试链接 : https://www.nowcoder.com/practice/e7ed657974934a30b2010046536a5372
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
/*
描述
给定一个没有重复值的整形数组arr，初始时认为arr中每一个数各自都是一个单独的集合。请设计一种叫UnionFind的结构，并提供以下两个操作。
boolean isSameSet(int a, int b): 查询a和b这两个数是否属于一个集合
void union(int a, int b): 把a所在的集合与b所在的集合合并在一起，原本两个集合各自的元素以后都算作同一个集合
[要求]
如果调用isSameSet和union的总次数逼近或超过O(N)，请做到单次调用isSameSet或union方法的平均时间复杂度为O(1)
 */
/**
 * @author Evelyn
 * @version 1.0
 */
public class Code01_UnionFindNowCoder {

	public static int MAXN = 1000001;

	public static int[] father = new int[MAXN];

	public static int[] size = new int[MAXN];

	public static int[] stack = new int[MAXN];   //用于扁平化

	public static int n;

	//并查集初始化
	public static void build() {
		for (int i = 0; i <= n; i++) {
			father[i] = i;     //每个节点指向自己
			size[i] = 1;       //每个集合中都只有一个元素
		}
	}

	// i号节点，往上一直找，找到代表节点返回！
	/*
	1、先看i是否直接指向代表节点，不指向，压入栈，并把i的值改成它的代表节点的值，因为find需要返回查找元素i的代表节点
	2、查看size是否大于0，大于0即栈中有数据，代表沿路有包括i在内的不是直接指向代表节点的节点，把它们都直接改成指向代表节点
	 */
	public static int find(int i) {
		//  在沿途寻找代表节点的过程中，把所有不是指向自己的节点都放到栈中
		int size = 0;   //栈的大小，即沿途收集了几个节点
		while (i != father[i]) {
			stack[size++] = i;
			i = father[i];
		}
		// 沿途节点收集好了，i已经跳到代表节点了
		while (size > 0) {
			father[stack[--size]] = i;
		}
		return i;
	}

	public static boolean isSameSet(int x, int y) {
		return find(x) == find(y);
	}

	public static void union(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if (fx != fy) {
			// fx是集合的代表：拿大小
			// fy是集合的代表：拿大小
			if (size[fx] >= size[fy]) {
				size[fx] += size[fy];
				father[fy] = fx;
			} else {
				size[fy] += size[fx];
				father[fx] = fy;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			build();
			in.nextToken();
			int m = (int) in.nval;
			for (int i = 0; i < m; i++) {
				in.nextToken();
				int op = (int) in.nval;
				in.nextToken();
				int x = (int) in.nval;
				in.nextToken();
				int y = (int) in.nval;
				if (op == 1) {
					out.println(isSameSet(x, y) ? "Yes" : "No");
				} else {
					union(x, y);
				}
			}
		}
		out.flush();
		out.close();
		br.close();
	}

}
