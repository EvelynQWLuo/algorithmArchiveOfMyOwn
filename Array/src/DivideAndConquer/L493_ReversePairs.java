package DivideAndConquer;

// 493.翻转对数量
/*
完全就是归并排序的应用，只是多了，统计的部分

原理：
1）思考一个问题在大范围上的答案，是否等于，左部分的答案 + 右部分的答案 + 跨越左右产生的答案
2）计算“跨越左右产生的答案”时，如果加上左、右各自有序这个设定，
      会不会获得计算的便利性（即这个属性下，指针不回退，只从头到尾走一次，肯定是O n）
3）如果以上两点都成立，那么该问题很可能被归并分治解决（话不说满，因为总有很毒的出题人）
4）求解答案的过程中只需要加入归并排序的过程即可，因为要让左、右各自有序，来获得计算的便利性
5）Time & Space 同merge sort

 */

public class L493_ReversePairs {

	public static int MAXN = 50001;

	public static int[] help = new int[MAXN];

	public static int reversePairs(int[] arr) {
		return counts(arr, 0, arr.length - 1);
	}

	// 统计l...r范围上，翻转对的数量，同时l...r范围统计完后变有序
	// 时间复杂度O(n * logn)
	public static int counts(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = (l + r) / 2;
		return counts(arr, l, m) + counts(arr, m + 1, r) + merge(arr, l, m, r);
	}

	public static int merge(int[] arr, int l, int m, int r) {
		// 统计部分
		int ans = 0;
		for (int i = l, j = m + 1; i <= m; i++) {
			while (j <= r && (long) arr[i] > (long) arr[j] * 2) {
				j++;
			}
			ans += j - m - 1;
		}
		// 正常merge
		int i = l;
		int a = l;
		int b = m + 1;
		while (a <= m && b <= r) {
			help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
		}
		while (a <= m) {
			help[i++] = arr[a++];
		}
		while (b <= r) {
			help[i++] = arr[b++];
		}
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
		return ans;
	}

}