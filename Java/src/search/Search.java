package search;

public class Search {

    public static int binarySearch(int[] src, int target) {
        if (src == null || src.length < 1) {
            return -1;
        }

        int low = 0;
        int high = src.length - 1;

        while (low <= high) {
            int mid = low + (high - low) >> 1;
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

    // TODO: Allow element repeated.
}