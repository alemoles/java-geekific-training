package com.devstromo;

import static java.lang.Math.min;

public record TimSort<T extends Comparable<T>>(T[] arr) {
    private static final int MIN_RUN_SIZE = 4;

    public void sort() {
        // Sort each run with Insertion Sort
        for (int start = 0; start < arr.length; start += MIN_RUN_SIZE) {
            int end = min((start + MIN_RUN_SIZE - 1), (arr.length - 1));
            new InsertionSort<T>(arr).sort(start, end);
        }
        // Merge the sorted runs with the help of MergeSort
        for (int runSize = MIN_RUN_SIZE; runSize < arr.length; runSize *= 2) {
            for (int left = 0; left < arr.length; left += 2 * runSize) {
                int mid = left + runSize - 1;
                int right = min((left + 2 * runSize - 1), (arr.length - 1));
                if (mid < right)
                    new MergeSort<>(arr).merge(left, mid, right);
            }
        }
    }
}
