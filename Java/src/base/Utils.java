package base;

public class Utils {

    public static void swap(int[] src, int from, int to) {
        if (src == null || src.length - 1 < from || src.length - 1 < to) return;

        int temp = src[from];
        src[from] = src[to];
        src[to] = temp;
    }

    public static void swap(Object[] src, int from, int to) {
        if (src == null || src.length - 1 < from || src.length - 1 < to) return;

        Object temp = src[from];
        src[from] = src[to];
        src[to] = temp;
    }
}
