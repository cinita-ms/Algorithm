package me.cinita.sort;

import java.util.Arrays;

class Test {

    private static int[] src = new int[]{3, 13, 12, 15, 4, 8, 19, 5, 15, 9, 13, 14, 15, 17, 3, 3, 9, 11, 6, 11};

    public static void main(String[] args) {

        int[] s1 = Arrays.copyOf(src, src.length);
        Sort.bubbleSort(s1);
        System.out.println(Arrays.toString(s1));

        int[] s2 = Arrays.copyOf(src, src.length);
        Sort.insertionSort(s2);
        System.out.println(Arrays.toString(s2));

        int[] s3 = Arrays.copyOf(src, src.length);
        Sort.selectionSort(s3);
        System.out.println(Arrays.toString(s3));

        int[] s4 = Arrays.copyOf(src, src.length);
        Sort.mergeSort(s4);
        System.out.println(Arrays.toString(s4));

        int[] s5 = Arrays.copyOf(src, src.length);
        Sort.quickSort(s5);
        System.out.println(Arrays.toString(s5));
    }
}
