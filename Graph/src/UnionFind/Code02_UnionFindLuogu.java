package UnionFind;


// 并查集模版(洛谷)
// 本实现用递归函数实现路径压缩，而且省掉了小挂大的优化，一般情况下可以省略
// 测试链接 : https://www.luogu.com.cn/problem/P3367
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * @author Evelyn
 * @version 1.0
 */
public class Code02_UnionFindLuogu {

	public static int MAXN = 200001;

	public static int[] father = new int[MAXN];
	//不用小挂大，那就不需要统计size

	public static int n;

	public static void build() {
		for (int i = 0; i <= n; i++) {
			father[i] = i;
		}
	}
// father[0, 0, 1,  2,   3]
	//    i :0  1   2   3    4
	/*
	1、i !=father[i], 即证明i肯定不指向它自己
	2.  需要把i目前指向的节点father[i]改成，整个链条上的代表节点，通过find（father[i]）来查找i所指向的node的代表node
	3、返回最终的father[i]，就是整个链条上的代表节点
	 */
	public static int find(int i) {
		if (i != father[i]) {
			father[i] = find(father[i]);
		}
		return father[i];
	}

	public static boolean isSameSet(int x, int y) {

		return find(x) == find(y);
	}

	//不用小挂大，随便挂
	public static void union(int x, int y) {

		father[find(x)] = find(y);
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
				int z = (int) in.nval;
				in.nextToken();
				int x = (int) in.nval;
				in.nextToken();
				int y = (int) in.nval;
				if (z == 1) {
					union(x, y);
				} else {
					out.println(isSameSet(x, y) ? "Y" : "N");
				}
			}
		}
		out.flush();
		out.close();
		br.close();
	}

}
