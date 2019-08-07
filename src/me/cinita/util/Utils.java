package me.cinita.util;

public class Utils {

    public static boolean isEmpty(int[] src) {
        return src == null || src.length == 0;
    }

    public static void swap(int[] src, int from, int to) {
        if (src == null || src.length - 1 < from || src.length - 1 < to || from == to) return;

        int temp = src[from];
        src[from] = src[to];
        src[to] = temp;
    }

    public static void swap(char[] src, int from, int to) {
        if (src == null || src.length - 1 < from || src.length - 1 < to || from == to) return;

        char temp = src[from];
        src[from] = src[to];
        src[to] = temp;
    }

    public static void swap(Object[] src, int from, int to) {
        if (src == null || src.length - 1 < from || src.length - 1 < to || from == to) return;

        Object temp = src[from];
        src[from] = src[to];
        src[to] = temp;
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        System.out.println(o);
    }
}
