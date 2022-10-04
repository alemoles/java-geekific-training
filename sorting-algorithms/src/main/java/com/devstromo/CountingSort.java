package com.devstromo;

import static java.lang.System.arraycopy;

import java.util.Arrays;

// only for integers
public record CountingSort(int[] arr) {

    public void sort() {
        int min = Arrays.stream(arr)
            .min()
            .orElse(0);
        int max = Arrays.stream(arr)
            .max()
            .orElse(Integer.MAX_VALUE);

        int[] countArray = new int[max - min + 1];
        for (int value : arr) {
            countArray[value - min]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < max - min + 1; i++) {
            while (countArray[i] > 0) {
                arr[arrayIndex] = i + min;
                countArray[i]--;
                arrayIndex++;
            }
        }
    }

    public void sortAlt() {
        int min = Arrays.stream(arr)
            .min()
            .orElse(0);
        int max = Arrays.stream(arr)
            .max()
            .orElse(Integer.MAX_VALUE);

        int[] countArray = new int[max - min + 1];
        for (int value : arr) {
            countArray[value - min]++;
        }

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int current = arr[i];
            int positionInArray = countArray[current - min] - 1;
            output[positionInArray] = current;
            countArray[current - min]--;
        }
        arraycopy(output, 0, arr, 0, arr.length);
    }
}
