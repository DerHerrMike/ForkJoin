package com.mps.joinfork.prim;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;


/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */



public class PrimzahlenBerechnungMain {

    private int von = 2;
    private int bis = 1000000;




    public static void main(String[] args) {

        PrimzahlenBerechnungMain p = new PrimzahlenBerechnungMain();

        Instant start = Instant.now();
        List<Integer> primzahlen = p.rekursiv();
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        System.out.println(between);
        System.out.println();

//        System.out.println(primzahlen);


    }

    public List<Integer> iterativ(){
        List<Integer> primzahlen = new ArrayList<>();
        for(int zahl = von; zahl<= bis; zahl++){
            if(PrimeUtils.isPrime(zahl)){
                primzahlen.add(zahl);
             }
        }
        return primzahlen;
    }

    public List<Integer> rekursiv(){

       // ForkJoinPool pool = new ForkJoinPool(20);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        List<Integer> primzahlen = pool.invoke((new PrimzahlenBerechnungRT(von, bis)));
        return primzahlen;
    }

}
