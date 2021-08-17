package com.mps.joinfork.arrayrecursiveaction.quicksort;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ForkJoinPool;


/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */
public class QuickMain {

    private int[] dataArray;
    private List<Integer> dataList;

    public static void main(String[] args) {

        QuickMain main = new QuickMain();
        main.init();
        main.iterative();
        main.rekursiv();

    }

    private void init(){
        Random random = new Random();
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            data.add(random.nextInt(1000000));
        }
        int[] dataArray = data.stream().mapToInt(i -> i).toArray();

        this.dataArray = dataArray;
        this.dataList = data;
    }

    private void iterative(){

        System.out.println("Iterativ: ");
        Instant start = Instant.now();

        iterativeQsort(dataArray);

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
//        System.out.println(Arrays.toString(dataArray));
//        Arrays.stream(dataArray).forEach(value -> {
//            System.out.println(value +", ");
//        });

        System.out.println(duration);

    }

    private void rekursiv(){
        System.out.println("Rekursiv: ");
        Instant start = Instant.now();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        QuicksortRT rt = new QuicksortRT(dataList);
        List<Integer> invoke = pool.invoke(rt);

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
//        System.out.println(Arrays.toString(dataArray));
//        Arrays.stream(dataArray).forEach(value -> {
//            System.out.println(value +", ");
//        });
//        System.out.println(invoke);
        System.out.println(duration);
    }

    public static void iterativeQsort(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arr.length);
        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.pop();
            if (end - start < 2) continue;
            int p = start + ((end - start) / 2);
            p = partition(arr, p, start, end);

            stack.push(p + 1);
            stack.push(end);

            stack.push(start);
            stack.push(p);

        }
    }

    private static int partition(int[] arr, int p, int start, int end) {

        int l = start;
        int h = end - 2;
        int piv = arr[p];
        swap(arr, p, end - 1);

        while (l < h) {
            if (arr[l] < piv) {
                l++;
            } else if (arr[h] >= piv) {
                h--;
            } else {
                swap(arr, l, h);
            }
        }
        int idx = h;
        if (arr[h] < piv) idx++;
        swap(arr, end - 1, idx);
        return idx;
    }
    private static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
