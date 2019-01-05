import java.util.Arrays;

public class Sort {

    // best O(n), worst O(n^2), average O(n^2).
    public static void bubbleSort(int[] src) {
        if (src == null || src.length <= 1)
            return;

        for (int i = 0; i < src.length; ++i) {
            boolean flag = false;
            for (int j = 0; j < src.length - i - 1; ++j) {
                if (src[j] > src[j + 1]) {
                    int temp = src[j];
                    src[j] = src[j + 1];
                    src[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
    }

    // best O(n), worst O(n^2), average O(n^2).
    public static void insertionSort(int[] src) {
        if (src == null || src.length <= 1)
            return;

        for (int i = 1; i < src.length; i++) {
            int target = src[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (src[j] > target) {
                    src[j + 1] = src[j];
                } else {
                    break;
                }
            }
            src[++j] = target;
        }
    }

    // best O(n^2), worst O(n^2), average O(n^2).
    public static void selectionSort(int[] src) {
        if (src == null || src.length <= 1)
            return;

        for (int i = 0; i < src.length - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < src.length; ++j) {
                if (src[j] < src[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = src[minIndex];
                src[minIndex] = src[i];
                src[i] = temp;
            }
        }
    }

    private static int[] src = new int[] { 3, 13, 12, 15, 4, 8, 19, 5, 15, 9, 13, 14, 15, 17, 3, 3, 9, 11, 6, 11 };

    public static void main(String[] args) {

        int[] s1 = Arrays.copyOf(src, src.length);
        bubbleSort(s1);
        System.out.println(Arrays.toString(s1));

        int[] s2 = Arrays.copyOf(src, src.length);
        insertionSort(s2);
        System.out.println(Arrays.toString(s2));

        int[] s3 = Arrays.copyOf(src, src.length);
        selectionSort(s3);
        System.out.println(Arrays.toString(s3));
    }
}