package com.mps.joinfork.arrayrecursiveaction.faktorielle;

import com.mps.joinfork.arrayrecursiveaction.quicksort.QuicksortRT;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;


/**
 * / Created by Mike Schwingenschloegl in Aug 2021
 */
public class MainFaktorielle {

    private static final Scanner scanner = new Scanner(System.in);
    private static int value;
    private List<BigInteger> dataList;


    public static void main(String[] args) {

        MainFaktorielle mf = new MainFaktorielle();
        mf.init();
        mf.iterativ(value);
//        mf.rekursiv();






        System.out.println("Brechnung Faktorielle mit Fork/Join");

    }

    private void init(){

        System.out.println();
        System.out.println("Ganzzahligen Startwert eingeben: ");
        this.value = scanner.nextInt();

    }

    private void iterativ(int value){
        BigInteger result = BigInteger.ONE;
        for(int i = 1; i<= value; i++){
           result = result.multiply(BigInteger.valueOf(i));
        }
        System.out.println(result);
    }

    private void rekursiv(){
        System.out.println();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        FaktorielleRT task = new FaktorielleRT(1,value);
        Instant start = Instant.now();
        BigInteger result = pool.invoke(task);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
//        System.out.println(Arrays.toString(dataArray));
//        Arrays.stream(dataArray).forEach(value -> {
//            System.out.println(value +", ");
//        });
//        System.out.println(invoke);
        System.out.println(duration);
        System.out.println(result);
    }



}
