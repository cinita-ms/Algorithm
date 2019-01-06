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
            int min = i;
            for (int j = i + 1; j < src.length; ++j) {
                if (src[j] < src[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = src[min];
                src[min] = src[i];
                src[i] = temp;
            }
        }
    }

    // Merge sort begin.
    // all O(nlogn).
    public static void mergeSort(int[] src) {
        if (src == null || src.length <= 1)
            return;

        mergeRecursion(src, 0, src.length - 1);
    }

    private static void mergeRecursion(int[] a, int begin, int end) {
        if (begin >= end)
            return;

        int mid = begin + (end - begin) / 2;
        mergeRecursion(a, begin, mid);
        mergeRecursion(a, mid + 1, end);

        merge(a, begin, mid, end);
    }

    // O(n).
    private static void merge(int[] a, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            temp[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        }

        int p = i;
        int q = mid;
        if (j <= end) {
            p = j;
            q = end;
        }
        while (p <= q) {
            temp[k++] = a[p++];
        }

        k = 0;
        while (k < temp.length) {
            a[start++] = temp[k++];
        }
    }
    // Merge sort end.

    // Quick sort start.
    public static void quickSort() {

    }
    // Quick sort end.

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

        int[] s4 = Arrays.copyOf(src, src.length);
        mergeSort(s4);
        System.out.println(Arrays.toString(s4));
    }
}