package CreateGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 拓扑排序模版（Leetcode）
// 邻接表建图（动态方式）
// 课程表II
// 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1
// 给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi]
// 表示在选修课程 ai 前 必须 先选修 bi
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1]
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序
// 你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组
// 测试链接 : https://leetcode.cn/problems/course-schedule-ii/
public class Code02_L210_TopoSortDynamic {

	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		// 邻接表建图，无权，所以直接放Integer
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		// 0 ~ n-1，建立0-n-1个大表
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		// 入度表
		int[] indegree = new int[numCourses];
		//然后把每个节点之间关系的小list依次放进去
		for (int[] edge : prerequisites) {
			graph.get(edge[1]).add(edge[0]);//建图
			indegree[edge[0]]++;                 //统计入度
		}
		int[] queue = new int[numCourses];          //用Q返回拓扑排序顺序
		int l = 0;
		int r = 0;
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				queue[r++] = i;
			}
		}
		int cnt = 0;
		while (l < r) {   //队列不为空
			int cur = queue[l++]; //弹出一个点
			cnt++;
			for (int next : graph.get(cur)) {    //遍历当前弹出节点所指向的大list，并把每个指向的节点的入度都减1
				if (--indegree[next] == 0) {
					queue[r++] = next;
				}
			}
		}
		//队列为空，结束循环
		return cnt == numCourses ? queue : new int[0];
	}
	/*
    1、建图
    2、加边，同时统计入度
    3、建立队列，遍历入度表，取出入度为0的放入队列
    4、建立结果集，弹出队列中节点并加入结果集，并统计数量
    5、同时遍历每个节点都领居，给他们入度减1，同时检查有没有入度为0的，继续放入队列

     */
	class MySolution210 {
		public int[] findOrder(int numCourses, int[][] prerequisites) {
			List<List<Integer>> graph = new ArrayList<>();
			for (int i = 0; i <= numCourses; i++) {
				graph.add(new ArrayList<>());
			}

			int[] indegree = new int[numCourses];
			for (int[] edge : prerequisites) {
				int from = edge[1];
				int to = edge[0];
				graph.get(from).add(to);
				indegree[to]++;
			}

			Deque<Integer> queue = new ArrayDeque<>();

			for (int i = 0; i < numCourses; i++) {
				if (indegree[i] == 0) {
					queue.offer(i);
				}
			}
			int[] ans = new int[numCourses];
			int count = 0;
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				ans[count++] = curr;

				for (int neighbor : graph.get(curr)) {
					indegree[neighbor]--;
					if (indegree[neighbor] == 0) {
						queue.offer(neighbor);
					}
				}
			}
			return count == numCourses ? ans : new int[0];
		}
	}
}
