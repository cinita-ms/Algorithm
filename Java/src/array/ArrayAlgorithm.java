package array;

public class ArrayAlgorithm {

    // 合并两个有序数组
    public static int[] mergeSortedArray(int[] a, int[] b) {
        if (a == null || a.length == 0) {
            return b;
        }

        if (b == null || b.length == 0) {
            return a;
        }

        int[] r = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a.length && j < b.length) {
            r[k++] = a[i] < a[j] ? a[i++] : a[j++];
        }

        int p;
        int q;
        if (i < a.length - 1) {
            p = i;
            q = a.length - 1;
        } else {
            p = j;
            q = b.length - 1;
        }

        while (p <= q) {
            r[k++] = r[p++];
        }

        return r;
    }
}
