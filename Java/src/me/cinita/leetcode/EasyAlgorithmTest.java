package me.cinita.leetcode;

import me.cinita.util.Utils;

import java.util.Arrays;
import java.util.List;

public class EasyAlgorithmTest {

    public static void main(String[] args) {
        int[] a1 = new int[]{0, 0, 1, 1, 2, 3, 3, 5};
        int r1 = EasyAlgorithm.removeDuplicates(a1);
        Utils.println(Arrays.toString(a1));
        Utils.println(r1);

        int[] a2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        EasyAlgorithm.rotate_1(a2, 3);
        Utils.println(Arrays.toString(a2));

        int[] a3 = new int[]{1,2,0,3};
        EasyAlgorithm.moveZeroes(a3);
        Utils.println(Arrays.toString(a3));

        int[] a4 = new int[]{1,2,3};
        List<List<Integer>> result = MediumAlgorithm.subsets_1(a4);
        Utils.print(result.toString());
    }
}
