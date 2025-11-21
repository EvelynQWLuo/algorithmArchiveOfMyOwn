package TwoPointers;

/**
 * @author Evelyn
 * @version 1.0
 */
/*
快慢指针入门题
定义快慢指针
      快指针：寻找新数组的元素 ，新数组就是不含有目标元素的数组
      慢指针：指向新数组下标的位置
 */
public class L27_RemoveElement {

    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
