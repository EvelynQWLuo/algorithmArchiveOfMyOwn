package Sort;



/**
 * @author Evelyn
 * @version 1.0
 */
/*
1）左部分排好序、右部分排好序、利用merge过程让左右整体有序
2）merge过程：谁小拷贝谁，直到左右两部分所有的数字耗尽，再拷贝回原数组
3）递归实现和非递归实现
4）时间复杂度O(n * logn)
5）需要辅助数组，所以额外空间复杂度O(n)
6）归并排序为什么比O(n^2)的排序快？因为比较行为没有浪费！
7）利用归并排序的便利性可以解决很多问题 - 归并分治

 */
public class L912_MergeSort {

	public static int[] sortArray(int[] nums) {
		if (nums.length > 1) {

			// 用哪个都可以
			mergeSort1(nums);      // mergeSort1为递归方法
			//mergeSort2(nums);    	// mergeSort2为非递归方法
		}
		return nums;
	}

	public static int MAXN = 50001;   //根据题目给的数据量定义

	public static int[] help = new int[MAXN];

	// 归并排序递归版
	public static void mergeSort1(int[] arr) {

		sort(arr, 0, arr.length - 1);
	}

	public static void sort(int[] arr, int l, int r) {
		if (l == r) {  //左右相等，即指向同一个数，返回
			return;
		}
		int m = (l + r) / 2;  //取中点
		sort(arr, l, m);   //分别排序
		sort(arr, m + 1, r);
		merge(arr, l, m, r);//merge
	}

	// 归并排序非递归版
	public static void mergeSort2(int[] arr) {
		int n = arr.length;
		for (int l, m, r, step = 1; step < n; step <<= 1) {
			l = 0;
			while (l < n) {
				m = l + step - 1;
				if (m + 1 >= n) {
					break;
				}
				r = Math.min(l + (step << 1) - 1, n - 1);
				merge(arr, l, m, r);
				l = r + 1;
			}
		}
	}

	public static void merge(int[] arr, int l, int m, int r) {
		int i = l;    //help数组的index
		int a = l;    //左数组的指针
		int b = m + 1;    //右数组的指针
		while (a <= m && b <= r) {
			help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
			//不能破坏稳定性
			//所以是左<=右时，先排左
		}
		//左右两边任意数组已经全部排完，把没排完的继续排进help数组
		while (a <= m) {
			help[i++] = arr[a++];
		}
		while (b <= r) {
			help[i++] = arr[b++];
		}
		//全都排完之后，刷回原数组
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
	}

}