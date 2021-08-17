package com.mps.joinfork.arrayrecursiveaction;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;


/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */
public class ArrayBerechnung {

    private double[] data = new double[500000000];

    public static void main(String[] args) {

        ArrayBerechnung ab = new ArrayBerechnung();
        ab.rekursiv();
    }


    public void iterativ() {

        Instant start = Instant.now();
        for (int i = 0; i < data.length; i++) {
            data[i] = i + Math.tan(Math.sqrt(i));
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration);
    }


    public void rekursiv() {

        //Thread pool
        ForkJoinPool pool = ForkJoinPool.commonPool();

        //Task erzeugen
        ArrayBerechnungRA task = new ArrayBerechnungRA(data,0,data.length);
        Instant start = Instant.now();
        //Task ausführen lassen
        pool.invoke(task); // Invoke wartet bis der Task beendet wurde
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration);



    }

}