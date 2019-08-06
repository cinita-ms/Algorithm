package me.cinita.sort;

import me.cinita.util.Utils;

public class Sort {

    // |------------------------------------------------------------------------|
    // | Type      | Time(best, worst, average)   | Sorted in place | Stability |
    // |------------------------------------------------------------------------|
    // | Bubble    | O(n), O(n^2), O(n^2)         | Yes             | Yes       |
    // |------------------------------------------------------------------------|
    // | Insertion | O(n), O(n^2), O(n^2)         | Yes             | Yes       |
    // |------------------------------------------------------------------------|
    // | Selection | O(n^2), O(n^2), O(n^2)       | Yes             | No        |
    // |------------------------------------------------------------------------|
    // | Merge     | O(nlogn), O(nlogn), O(nlogn) | No, O(n)        | Yes       |
    // |------------------------------------------------------------------------|
    // | Quick     | O(nlogn), O(nlogn), O(n^2)   | Yes             | Yes       |
    // |------------------------------------------------------------------------|

    public static void bubbleSort(int[] src) {
        if (src == null || src.length <= 1) return;

        for (int i = 0; i < src.length; ++i) {
            boolean flag = false;
            for (int j = 0; j < src.length - i - 1; ++j) {
                if (src[j] > src[j + 1]) {
                    Utils.swap(src, j, j + 1);
                    flag = true;
                }
            }

            if (!flag) return;
        }
    }

    public static void insertionSort(int[] src) {
        if (src == null || src.length <= 1) return;

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

    public static void selectionSort(int[] src) {
        if (src == null || src.length <= 1) return;

        for (int i = 0; i < src.length - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < src.length; ++j) {
                if (src[min] > src[j]) {
                    min = j;
                }
            }

            if (min != i) {
                Utils.swap(src, min, i);
            }
        }
    }

    // region Merge sort.
    public static void mergeSort(int[] src) {
        if (src == null || src.length <= 1) return;

        rMerge(src, 0, src.length - 1);
    }

    private static void rMerge(int[] a, int begin, int end) {
        if (begin >= end) return;

        int mid = ((end - begin) >> 1) + begin;
        rMerge(a, begin, mid);
        rMerge(a, mid + 1, end);

        merge(a, begin, mid, end);
    }

    private static void merge(int[] a, int begin, int mid, int end) {
        assert begin < end;

        int[] temp = new int[end - begin + 1];
        int i = begin;
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
            a[begin++] = temp[k++];
        }
    }
    // endregion Merge sort.

    // region Quick sort.
    public static void quickSort(int[] src) {
        if (src == null || src.length <= 1) return;

        partitionRecursion(src, 0, src.length - 1);
    }

    private static void partitionRecursion(int[] a, int begin, int end) {
        if (begin >= end) return;

        int pivot = partition(a, begin, end);
        partitionRecursion(a, begin, pivot - 1);
        partitionRecursion(a, pivot + 1, end);
    }

    private static int partition(int[] a, int begin, int end) {
        int target = a[end];
        int p = begin;
        for (int i = begin; i < end; ++i) {
            if (a[i] < target) {
                Utils.swap(a, i, p);
                ++p;
            }
        }

        Utils.swap(a, end, p);
        return p;
    }
    // endregion Quick sort.
}