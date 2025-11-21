package TwoPointers;

/**
 * @author Evelyn
 * @version 1.0
 */
/*
快慢指针，思路同27
 */
public class L26_RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = 1; // 从第二个位置开始填充

        for (int fast = 1; fast < nums.length; fast++) {
            // 如果当前元素与前一个元素不同
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        return slow;
    }
}
