package BinarySearch;

/**
 * @author Evelyn
 * @version 1.0
 */
/*
求数字X的平方跟，且向下取整，数学和二分的结合
 */
public class L69_SqrtX {
    public int mySqrt(int x) {

        if (x < 2) {
            return x;
        }

        int l = 1;
        int r = x / 2;

        while (l <= r) {
            int m = l + (r - l) / 2;

            long square = (long) m * m;

            if (square < x) {
                l = m + 1;
            } else if (square > x) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return r;
    }
}
