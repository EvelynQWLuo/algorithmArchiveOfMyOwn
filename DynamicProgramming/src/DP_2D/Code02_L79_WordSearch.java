package DP_2D;

// 单词搜索（无法改成动态规划）
// 给定一个 m x n 二维字符网格 board 和一个字符串单词 word
// 如果 word 存在于网格中，返回 true ；否则，返回 false 。
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成
// 其中"相邻"单元格是那些水平相邻或垂直相邻的单元格
// 同一个单元格内的字母不允许被重复使用
// 测试链接 : https://leetcode.cn/problems/word-search/
public class Code02_L79_WordSearch {
//洪水填充+回溯
	public static boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (f(board, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	// 因为board会改其中的字符
	// 用来标记哪些字符无法再用
	// 带路径的递归无法改成动态规划或者说没必要
	// 这个函数的意思：从(i,j)出发，来到k位置w[k]，请问后续即从k开始 能不能把word走出来w[k...]
	public static boolean f(char[][] b, int i, int j, char[] w, int k) {
		if (k == w.length) {
			return true;
		}
		if (i < 0 || i == b.length || j < 0 || j == b[0].length || b[i][j] != w[k]) {
			                                                                       //比较当前的格子里的字母和word当前位置上的字符是否一样
			return false;
		}
		// 不越界，b[i][j] == w[k]
		char tmp = b[i][j];
		b[i][j] = 0;
		boolean ans = f(b, i - 1, j, w, k + 1) 
				|| f(b, i + 1, j, w, k + 1) 
				|| f(b, i, j - 1, w, k + 1)
				|| f(b, i, j + 1, w, k + 1);
		b[i][j] = tmp;
		return ans;
	}
/*
dfs+回溯
每来到一个位置，要确认这个位置是否和String中的对应字符相等，相等置0，继续dfs上下左右
 */
class MySolution {
	public boolean exist(char[][] board, String word) {
		char[] w= word.toCharArray();

		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				if(dfs(board,i, j, w,0)){
					return true;
				}
			}
		}
		return false;
	}

	public boolean dfs(char[][] board, int i, int j, char[] w, int k){
		if(k==w.length){
			return true;
		}
		if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!=w[k]){
			return false;
		}

		char tmp = board[i][j];
		board[i][j]=0;
		boolean ans=dfs(board,i-1,j,w,k+1)||
				dfs(board,i,j+1,w,k+1)||
				dfs(board,i+1,j,w, k+1)||
				dfs(board,i,j-1,w,k+1);
		board[i][j]= tmp;
		return ans;
	}
}
}
