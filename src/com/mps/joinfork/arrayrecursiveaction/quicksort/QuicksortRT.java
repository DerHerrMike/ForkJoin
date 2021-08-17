package com.mps.joinfork.arrayrecursiveaction.quicksort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */
public class QuicksortRT extends RecursiveTask<List<Integer>> {

    private List<Integer> data;


    public QuicksortRT(List<Integer> data) {

        this.data = data;
    }


    @Override
    protected List<Integer> compute() {

        if (data.size() <= 1) {
            return data;
        }

        //Wähle Pivot aus
        int pivot = data.get(0);

        //Linke und rechte Liste erzeugen
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Gehe durch data und sortiere in left/right ein
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) < pivot) {
                left.add(data.get(i));
            } else {
                right.add(data.get(i));
            }
        }

        // Left Task
        QuicksortRT leftTask = new QuicksortRT(left);
        leftTask.fork();

        //Right task
        QuicksortRT rightTask = new QuicksortRT(right);
        List<Integer> rightResult = rightTask.compute();

        //Linkes Ergebnis holen
        List<Integer> leftResult = leftTask.join();
        leftResult.add(pivot);
        leftResult.addAll(rightResult);
        List<Integer> totalResult = new ArrayList<>(leftResult);



        return totalResult;
    }
}
