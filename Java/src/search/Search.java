package search;

public class Search {

    // All elements are different.
    public static int binarySearch(int[] src, int target) {
        if (src == null || src.length < 1) {
            return -1;
        }

        int low = 0;
        int high = src.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (src[mid] == target) {
                return mid;
            } else if (src[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // Allow elements repeated.

    // 查找第一个等于给定值
    public static int binarySearch1(int[] src, int target) {
        if (src == null || src.length < 1) {
            return -1;
        }

        int low = 0;
        int high = src.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (src[mid] < target) {
                low = mid + 1;
            } else if (src[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == 0 || src[mid - 1] != target) {
                    return mid;
                }

                high = mid - 1;
            }
        }

        return -1;
    }

    // 查找最后一个等于给定值
    public static int binarySearch2(int[] src, int target) {
        if (src == null || src.length < 1) {
            return -1;
        }

        int low = 0;
        int high = src.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (src[mid] < target) {
                low = mid + 1;
            } else if (src[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == src.length - 1 || src[mid + 1] != target) {
                    return mid;
                }

                low = mid + 1;
            }
        }

        return -1;
    }
}