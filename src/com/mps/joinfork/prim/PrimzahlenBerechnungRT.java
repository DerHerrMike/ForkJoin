package com.mps.joinfork.prim;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */
public class PrimzahlenBerechnungRT extends RecursiveTask<List<Integer>> {

    private static final int THRESHOLD = 5000;
    private int start, end;


    public PrimzahlenBerechnungRT(int start, int end) {

        this.start = start;
        this.end = end;
    }


    @Override
    protected List<Integer> compute() {

        //ist die Teilaufgabe klein genug? --> direkt lösen
        //  wie groß ist das Teilproblem?
        int length = end - start;
        if (length <= THRESHOLD) {
            List<Integer> primes = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                if (PrimeUtils.isPrime(i)) {
                    primes.add(i);
                }
            }
            return primes;
        }
        //ansonsten in kleinere Teilaufgaben aufteilen
        int mid = start + (end - start) / 2;
        PrimzahlenBerechnungRT leftTask = new PrimzahlenBerechnungRT(start, mid);
        // leftTask im Hinterggrund ausführen lassen
        leftTask.fork();

        PrimzahlenBerechnungRT rightTask = new PrimzahlenBerechnungRT(mid + 1, end);
        List<Integer> rightResult = rightTask.compute();

        //auf linken Teiltask warten und Ergebnis bekommen
        List<Integer> leftResult = leftTask.join();

        //Ergebnis von linkem und rechtem Task kombinieren
        leftResult.addAll(rightResult);

        return leftResult;
    }
}
