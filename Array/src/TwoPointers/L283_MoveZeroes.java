package TwoPointers;

/**
 * @author Evelyn
 * @version 1.0
 */
/*
快慢指针，完全是27的思路，
然后slowIndex之后都是移除元素0的冗余元素，把这些元素都赋值为0就可以了。
 */
public class L283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }
        // 后面的元素全变成 0
        for (int j = slow; j < nums.length; j++) {
            nums[j] = 0;
        }
    }
}
