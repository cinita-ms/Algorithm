package sort;

import base.Utils;

public class Sort {

    // | Type      | Time(best, worst, average) | Sorted in place | Stability |
    // | Bubble    | O(n), O(n^2), O(n^2)       | Yes             | Yes       |
    // | Insertion | O(n), O(n^2), O(n^2)       | Yes             | Yes       |
    // | Selection | O(n^2), O(n^2), O(n^2)     | Yes             | No        |
    // | Merge     |
    // | Quick     |

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

        int mid = begin + (end - begin) >>> 2;
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
    // best & average O(nlogn), worst O(n^2).
    public static void quickSort(int[] src) {
        if (src == null || src.length <= 1)
            return;

        partitionRecursion(src, 0, src.length - 1);
    }

    private static void partitionRecursion(int[] a, int start, int end) {
        if (start >= end)
            return;

        int pivot = partition(a, start, end);
        partitionRecursion(a, start, pivot - 1);
        partitionRecursion(a, pivot + 1, end);
    }

    public static int partition(int[] a, int start, int end) {
        int target = a[end];
        int p = start;
        for (int i = start; i < end; ++i) {
            if (a[i] < target) {
                if (i == p) {
                    ++p;
                } else {
                    int temp = a[i];
                    a[i] = a[p];
                    a[p++] = temp;
                }
            }
        }

        int temp = a[end];
        a[end] = a[p];
        a[p] = temp;

        return p;
    }
    // Quick sort end.
}